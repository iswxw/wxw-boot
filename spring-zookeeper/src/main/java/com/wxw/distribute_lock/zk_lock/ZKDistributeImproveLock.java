package com.wxw.distribute_lock.zk_lock;

import com.wxw.common.tools.MyZkSerializer;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * https://blog.csdn.net/u014082714/article/details/83653691
 * 基于Zookeeper 临时顺序节点
 * @author: wxw
 * @date: 2020-11-28-22:10
 */
public class ZKDistributeImproveLock implements Lock {
    /**
     * 利用临时顺序节点来实现分布式锁
     * 获取锁：取排队号（创建自己的临时顺序节点），然后判断自己是否是最小号，如是则获得锁，不是，则注册前一个节点watch
     * 释放锁：删除自己创建的临时顺序节点
     */
    private String lockPath;

    private ZkClient client;

    private String currentPath;

    private String beforePath;

    // 创建一把锁
    public ZKDistributeImproveLock(String lockPath){
        super();
        this.lockPath = lockPath;
        client = new ZkClient("localhost:2181");
        client.setZkSerializer(new MyZkSerializer());
        if (!this.client.exists(lockPath)){
            try {
                this.client.createPersistent(lockPath);
            } catch (ZkNodeExistsException e) {
                // e.printStackTrace();
            }
        }
    }


    @Override
    public void lock() {  // 如果获取不到则阻塞等待
        if (!tryLock()){
            // 没获得锁，阻塞自己 注册watch
            waitForLock();
            // 再次尝试
            lock();
        }
    }

    @Override
    public boolean tryLock() { // 不会阻塞
        if (this.currentPath == null){
            currentPath = this.client.createEphemeralSequential(lockPath+"/","zk-lock");
        }
        // 获取所有的子节点
        List<String> children = client.getChildren(lockPath);
        //排序List
        Collections.sort(children);
        // 判断当前节点是否是最小的
        if (currentPath.equals(lockPath+"/"+children.get(0))){
            return true;
        }else {
            // 取到前一个
            // 得到字节的索引号
            int curIndex = children.indexOf(currentPath.substring(lockPath.length() + 1));
            beforePath = lockPath + "/" + children.get(curIndex-1);
        }
        return false;
    }

    // 等待 并注册监听
    private void waitForLock() {
        CountDownLatch cdl = new CountDownLatch(1);
        // 注册一个watcher 监听
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                // 监听节点改变
            }
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("======收到节点被删除了："+ dataPath);
                cdl.countDown();
            }
        };
        client.subscribeDataChanges(this.beforePath,listener);
        // 怎么让自己阻塞
        if (this.client.exists(this.beforePath)){
            try {
                cdl.await();
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
        // 取消注册
        client.unsubscribeDataChanges(this.beforePath,listener);
    }


    // 删除节点
    @Override
    public void unlock() {
        this.client.delete(currentPath);
    }

    @Override
    public boolean tryLock(long l, TimeUnit timeUnit) throws InterruptedException {
        return false;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
