package com.huaxia.opas.util;

public class RedisTest extends Thread{
	private RedisPool redisPool;
	
	public RedisTest(RedisPool redisPool){
		this.redisPool=redisPool;
	}

	@Override
	public void run() {
		redisPool.seckill();
	}
	
	public static void main(String[] args) {
		RedisPool redisPool=new  RedisPool();
		for(int i=0;i<50;i++){
			RedisTest redisTest=new RedisTest(redisPool);
			redisTest.start();
		}
	}
}
