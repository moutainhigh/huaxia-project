package JavaMultiThreadProgramming.test;

import JavaMultiThreadProgramming.extthread.ThreadA;
import JavaMultiThreadProgramming.extthread.ThreadB;
import JavaMultiThreadProgramming.tools.Tools;

/**
 * static 实现不同对象之间值的共享
 * ThreadLocal 也是实现变量值的共享
 * @author liuwei
 *
 */
public class Run2 {
	public static void main(String args[]){
		try{
		ThreadA a = new ThreadA();
		ThreadB b = new ThreadB();
		a.start();
		b.start();
		for(int i=0;i<100;i++){
			Tools.t1.set("Main"+(i+1));
			System.out.println("Main get Value="+Tools.t1.get());
			Thread.sleep(200);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
