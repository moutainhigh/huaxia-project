package JavaMultiThreadProgramming.ThreadPriority;

import java.util.Random;

import java.util.Random;

public class MyThread3 extends Thread {
	private int i=0;
	@Override
	public void run(){
		try{
			while(true){
				i++;
				System.out.println("i="+i);
				Thread.sleep(1000);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
