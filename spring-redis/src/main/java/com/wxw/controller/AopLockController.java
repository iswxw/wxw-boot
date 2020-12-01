package com.wxw.controller;

import com.wxw.distribute_lock.aop_lock.ApiLock;
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
            }catch(Exception e){
                log.error(e.getMessage(),e);
            }finally {
                lock.unlock();
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
        }catch(Exception e){
            log.error(e.getMessage(),e);
        }finally {
            lock2.unlock();
        }
    }

    @RequestMapping("/lock3")
    @ApiLock("#testParam.name + ',' + #testParam.nickName")
    public void testData03(){
        /*基于注解实现*/
    }
}
