package com.meishubao.sample.config.redis;

/**
 * Redis分布式锁回调接口
 *
 * @author lilu
 */
public interface RedisLockCallback {

	/**
	 * 在获得锁以后执行的业务逻辑
	 *
	 * @return
	 */
	Object doInRedisLock();

}
