package JavaMultiThreadProgramming.ext.test;
import JavaMultiThreadProgramming.ext.ThreadLocalExt2;
import JavaMultiThreadProgramming.ext.exthread.ThreadA;
import JavaMultiThreadProgramming.tools.Tools;
/**
 * 测试默认值
 * @author liuwei
 *
 */
public class Run2 {
	public static ThreadLocalExt2 t1 = new ThreadLocalExt2();
	public static void main(String args[]){
		try{
		for(int i=0;i<10;i++){
			System.out.println(" 在main线程中取值="+t1.get());
			Thread.sleep(100);
		}
		Thread.sleep(5000);
		ThreadA a = new ThreadA();
		a.start();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(t1.get());
		System.out.println(t1.get());
	}
}
