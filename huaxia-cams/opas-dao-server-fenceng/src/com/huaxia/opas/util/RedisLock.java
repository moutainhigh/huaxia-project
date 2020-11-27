package com.huaxia.opas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author user
 *
 */
public class RedisLock {
	
	private static Logger logger=LoggerFactory.getLogger(RedisLock.class);
	private RedisTemplate redisTemplate;
	
	private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS=100;
	
	private String lockKey;
	
	/**
	 * 锁超时时间,防止线程在入锁以后,无限的执行等待
	 */
	private int expireMsecs=60*1000;
	
	/**
	 * 锁等待时间,防止线程饥饿
	 */
	private int timeoutMsecs=10*1000;
	
	private volatile boolean locked=false;

	public RedisLock(RedisTemplate redisTemplate, String lockKey) {
		this.redisTemplate = redisTemplate;
		this.lockKey = lockKey+"_lock";
	}

	public RedisLock(RedisTemplate redisTemplate, String lockKey, int timeoutMsecs) {
		this(redisTemplate,lockKey);
		this.timeoutMsecs = timeoutMsecs;
	}

	public RedisLock(RedisTemplate redisTemplate, String lockKey, int expireMsecs, int timeoutMsecs) {
		this(redisTemplate,lockKey,timeoutMsecs);
		this.expireMsecs = expireMsecs;
	}

	public String getLockKey() {
		return lockKey;
	}

	public void setLockKey(String lockKey) {
		this.lockKey = lockKey;
	}
	
	private String get(final String key){
		Object obj=null;
		try{
			obj=redisTemplate.execute(new RedisCallback<Object>(){

				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					StringRedisSerializer serializer=new StringRedisSerializer();
					byte[] data=connection.get(serializer.serialize(key));
					connection.close();
					if(data==null){
						return null;
					}
					return serializer.deserialize(data);
				}
				
			});
		}catch(Exception e){
			logger.error("get redis error,key:{}",key);
		}
		return obj!=null?obj.toString():null;
	}
	
	private boolean setNX(final String key, final String value){
		Object obj=null;
		try{
			obj=redisTemplate.execute(new RedisCallback<Object>(){

				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					StringRedisSerializer serializer=new StringRedisSerializer();
					Boolean success=connection.setNX(serializer.serialize(key), serializer.serialize(value));
					connection.close();
					return success;
				}
				
			});
		}catch(Exception e){
			logger.error("setNX redis error,key:{}",key);
		}
		return obj!=null?(Boolean)obj:false;
	}
	
	private String getSet(final String key,final String value){
		Object obj=null;
		try{
			obj=redisTemplate.execute(new RedisCallback<Object>(){

				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					StringRedisSerializer serializer=new StringRedisSerializer();
					byte[] ret=connection.getSet(serializer.serialize(key), serializer.serialize(value));
					connection.close();
					return serializer.deserialize(ret);
				}
				
			});
		}catch(Exception e){
			logger.error("getSet redis error ,key :{}",key);
		}
		return obj!=null?(String) obj:null;
	}
	
	/**
	 * 获得锁  使用redis的setNx命令  缓存锁
	 * redis 缓存的key 是锁的所有 key所有的共享,vlaue是锁的到期时间 (注意：这里把过期时间放在了value,没有时间上设置其超时时间)
	 * 执行过程：1、通过setnx尝试了设置某个key的值 ,成功(当前没有这个锁)则返回,成功获得锁
	 * 2、锁已经存在则获取锁的到期时间,和当前时间比较,超时的话，则设置新的值
	 * @return
	 * @throws InterruptedException 
	 */
	public synchronized boolean lock() throws InterruptedException{
		int timeout=timeoutMsecs;
		while(timeout>=0){
			long expires=System.currentTimeMillis()+expireMsecs+1;
			//锁到期时间
			String expiresStr=String.valueOf(expires);
			if(this.setNX(lockKey, expiresStr)){
				locked=true;
				return true;
			}
			//redis里的时间
			String currentValueStr=this.get(lockKey);
			if(currentValueStr!=null&&Long.parseLong(currentValueStr)<System.currentTimeMillis()){
				//判断是否为空,不为空的情况下,如果被其他线程设置了值,则第二个条件判断过不去
				
				String oldValueStr=this.getSet(lockKey, expiresStr);
				//获取上一个锁到期时间,并设置现在的锁到期时间
				//只有一个线程才能获取上一个线上的设置时间,因为jedis.getSet是同步的
				
				if(oldValueStr!=null&&oldValueStr.endsWith(currentValueStr)){
					//防止误删(覆盖，因为key是相同的 )了他人的锁
					//多个线程恰好都到了这里,但是只有一个线程的设置值和当前值相同,才有权利获取锁
					locked=true;
					return true;
				}
			}
			timeout=DEFAULT_ACQUIRY_RESOLUTION_MILLIS;
			//延迟 防止饥饿进程的出现   
			Thread.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
		}
		return false;
	}
	
	public synchronized void unlock(){
		if(locked){
			redisTemplate.delete(lockKey);
			locked=false;
		}
	}
}
