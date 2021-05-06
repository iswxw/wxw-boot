package com.wxw.controller;

import com.wxw.distribute_lock.aop_lock.ApiLock;
import com.wxw.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author ：wxw.
 * @date ： 10:58 2020/12/1
 * @description：基于AOP和注解实现分布式锁 分布式锁限流测试
 * @version: v_0.0.1
 */
@RestController
@Slf4j
public class AopLockController {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private OrderService orderService;

    /*********************主要是以下两种方式*********************/
    //锁定接口层，不让前端操作，例如：限制前端保留1000这个学校
    @RequestMapping("/lock1")
    public void testData01() throws InterruptedException {
        String lockKey = "SchoolController.keep(1000)";
        //第一种：最多等待30秒时间，如果没有获取锁，先不处理，但一定要限定时间释放锁
        RLock lock = redissonClient.getLock(lockKey);
        if(lock.tryLock(3000, 6000, TimeUnit.MICROSECONDS)){
            try{
                //获取锁后，执行业务代码
                orderService.createOrder();
            }catch(Exception e){
                log.error(e.getMessage(),e);
            }finally {
                RLock unlock = redissonClient.getLock(lockKey);
                if(unlock.isLocked()){  // 是否处于锁定状态
                    if(unlock.isHeldByCurrentThread()){  // 是当前执行线程持有的锁
                        lock.unlock(); // 释放锁
                    }
                }
            }
        }else{
            //没有获取锁
        }
    }

    @RequestMapping("/lock2")
    public void testData02(){
        /*第二种：阻塞，一定要获取锁，同时要限定时间释放锁****************************/
        String lockKey2 = "SchoolController.keep(2000)";
        RLock lock2 = redissonClient.getLock(lockKey2);
        lock2.lock(6000, TimeUnit.MICROSECONDS);
        try{
            //获取锁后，执行业务代码
            orderService.createOrder();
        }catch(Exception e){
            log.error(e.getMessage(),e);
        }finally {
            /**
             * attempt to unlock lock, not locked by current thread by node id
             * 线程1 进来获得锁后，但它的业务逻辑需要执行2秒，在 线程1 执行1秒后，这个锁就自动过期了，那么这个时候
             * 线程2 进来了获得了锁。在线程1去解锁就会抛上面这个异常（因为解锁和当前锁已经不是同一线程了）
             */
            RLock unlock = redissonClient.getLock(lockKey2);
            if(unlock.isLocked()){  // 是否处于锁定状态
                if(unlock.isHeldByCurrentThread()){  // 是当前执行线程持有的锁
                    lock2.unlock(); // 释放锁
                }
            }
        }
    }

    /**
     *
     */
    @RequestMapping("/lock3")
//    @ApiLock("#testParam.name + ',' + #testParam.nickName")
    @ApiLock()
    public void testData03(){
        /*基于注解实现*/
        //获取锁后，执行业务代码
        orderService.createOrder();
    }
}
