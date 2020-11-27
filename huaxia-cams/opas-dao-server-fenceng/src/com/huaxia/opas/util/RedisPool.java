package com.huaxia.opas.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
	private static JedisPool pool=null;
	
	static{
		JedisPoolConfig config=new JedisPoolConfig();
		//设置最大连接数
		config.setMaxTotal(200);
		//设置最大空闲数
		config.setMaxIdle(8);
		//设置最大等待时间
		config.setMaxWaitMillis(1000*300);
		//在borrow一个jedis实例时,是否需要验证 若为true 则所有jedis实例均是可用的
		config.setTestOnBorrow(true);
		Map<String, String> streamMap = JdbcUtil.getParoperMap();
		String jedisHost=StringUtils.trimToEmpty(streamMap.get("jedisHost"));
		String jedisPort=StringUtils.trimToEmpty(streamMap.get("jedisPort"));
		if(!"".equals(jedisHost)&&!"".equals(jedisPort)){
			pool=new JedisPool(config,jedisHost,Integer.valueOf(jedisPort),3000);
		}
	}
	
	DistributedLock lock=new DistributedLock(pool);
	
	int n=500;
	
	public void seckill(){
		//返回锁的value值 供释放 锁时候进行判断
		String indentifier=lock.lockWithTimeout("allotRedis", 300000, 100000);
		
		System.out.println(Thread.currentThread().getName()+"获得了锁");
		System.out.println(--n);
		lock.releaseLock("allotRedis", indentifier);
	}
	
	//获取锁
	public String getLockkey(){
		//返回锁的value值 供释放 锁时候进行判断
	    return	lock.lockWithTimeout("allotRedis", 300000, 100000);
	}
	//杀死锁
	public void killLockkey(String indentifier){
		lock.releaseLock("allotRedis", indentifier);
	}
}
