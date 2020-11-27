package JavaMultiThreadProgramming.one.test;

public class MyThread extends Thread{
	@Override
	public void run(){
		long beginTime = System.currentTimeMillis();
				int count = 1;
		for(int i=0;i<500000000;i++){
			Thread.yield();
			count= count+(i+1);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("用时："+(endTime-beginTime)+"毫秒");
	}
}
