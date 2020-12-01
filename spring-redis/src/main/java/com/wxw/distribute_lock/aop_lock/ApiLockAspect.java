package com.wxw.distribute_lock.aop_lock;

import com.wxw.common.Result;
import com.wxw.common.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @ Author ：wxw.
 * @ Date ： 11:27 2020/9/28
 * @ Description：AOP实现锁限流
 * @ Version:   v_0.0.1
 */
@Slf4j
@Aspect
@Component
public class ApiLockAspect {

    private ExpressionParser parser = new SpelExpressionParser();

    private ParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Resource
    public RedissonClient redissonClient;

    @Pointcut("execution(* com.wxw.controller..*(..))")
    public void requestMapping() {
    }

    @Around("requestMapping()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        ApiLock apiLock = AnnotationUtils.getAnnotation(method,ApiLock.class);
        if (apiLock == null) {
            return joinPoint.proceed();
        } else {
            String key = apiLock.value();
            Object[] args = joinPoint.getArgs();
            key = parse(key, method, args);
            key = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "(" + key + ")";
            RLock lock = getLock(key, apiLock);
            long waitTime = apiLock.waitTime();
            long leaseTime = apiLock.leaseTime();
            TimeUnit unit = apiLock.unit();
            if (waitTime >= 0) {
                //以下是尝试获取锁，不会阻塞，在等待时间后，获取不到锁，返回false
                if (lock.tryLock(waitTime, leaseTime, unit)) {
                    try {
                        return joinPoint.proceed();
                    } catch (Exception e) {
                        throw e;
                    } finally {
                        lock.unlock();
                    }
                } else {
                    throw new ServiceException(Result.EXCEPTIONMESSAGE, "正在处理，请稍候重试，不要重复提交");
                }
            } else {
                //以下会等待获取锁，线程阻塞
                lock.lock(leaseTime, unit);
                try {
                    return joinPoint.proceed();
                } catch (Exception e) {
                    throw e;
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private String parse(String key, Method method, Object[] args) {
        String[] params = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }

    private RLock getLock(String key, ApiLock apiLock) {
        switch (apiLock.lockType()) {
            case REENTRANT_LOCK:
                return redissonClient.getLock(key);
            case FAIR_LOCK:
                return redissonClient.getFairLock(key);
            case READ_LOCK:
                return redissonClient.getReadWriteLock(key).readLock();
            case WRITE_LOCK:
                return redissonClient.getReadWriteLock(key).writeLock();
            default:
                throw new ServiceException("不支持锁的类型:" + apiLock.lockType().name());
        }
    }

}
