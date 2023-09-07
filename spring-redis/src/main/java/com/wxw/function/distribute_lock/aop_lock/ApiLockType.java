package com.wxw.function.distribute_lock.aop_lock;

/**
 * 锁类型
 */
public enum ApiLockType {
 
	/** 可重入锁*/
	REENTRANT_LOCK,
	
	/** 公平锁*/
	FAIR_LOCK,
	
	/** 读锁*/
	READ_LOCK,
	
	/** 写锁*/
	WRITE_LOCK,

	/** 事务锁*/
	TRANSACTIONAL_LOCK;
}