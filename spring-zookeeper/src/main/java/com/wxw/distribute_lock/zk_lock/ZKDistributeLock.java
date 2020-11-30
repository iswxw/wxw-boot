package com.wxw.distribute_lock.zk_lock;

import com.wxw.common.tools.MyZkSerializer;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 基于 zk的分布式锁 临时节点  产生惊群效应
 * @author: wxw
 * @date: 2020-11-28-21:02
 */
public class ZKDistributeLock implements Lock {

    private String lockPath;

    private ZkClient client;

    // 创建一把锁
    public ZKDistributeLock(String lockPath){
        super();
        this.lockPath = lockPath;
        client = new ZkClient("localhost:2181");
        client.setZkSerializer(new SerializableSerializer());
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
        // 创建节点
        try {
            // 创建临时节点
            client.createEphemeral(lockPath);
            return true;
        } catch (ZkNodeExistsException e) {
            return false;
        }
    }

    // 等待 并注册监听
    private void waitForLock() {
        CountDownLatch cdl = new CountDownLatch(1);
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                // 监听节点改变
            }
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("======收到节点被删除了");
                cdl.countDown();
            }
        };
        client.subscribeDataChanges(lockPath,listener);
        // 阻塞自己
        if (this.client.exists(lockPath)){
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 取消注册
        client.unsubscribeDataChanges(lockPath,listener);
    }

    // 删除节点
    @Override
    public void unlock() {
        client.delete(lockPath);
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
