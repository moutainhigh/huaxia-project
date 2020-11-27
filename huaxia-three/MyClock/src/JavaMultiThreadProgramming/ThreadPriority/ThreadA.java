package JavaMultiThreadProgramming.ThreadPriority;

import java.util.Random;

import java.util.Random;

public class ThreadA extends Thread {
	private int count = 0;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void run(){
		while(true){
			count++;
		}
	}
}
