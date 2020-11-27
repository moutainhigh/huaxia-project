package JavaMultiThreadProgramming.ext.exthread;

import JavaMultiThreadProgramming.ext.ThreadLocalExt2;
import JavaMultiThreadProgramming.tools.Tools;

public class ThreadA extends Thread{
	public static ThreadLocalExt2 t1 = new ThreadLocalExt2();
	@Override
	public void run(){
		try{
		for(int i=0;i<100;i++){
			Tools.t1.set("ThreadA"+(i+1));
			System.out.println("ThreadA get Value="+t1.get());
			Thread.sleep(200);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
