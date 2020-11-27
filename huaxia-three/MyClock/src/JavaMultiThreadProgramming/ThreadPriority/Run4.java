package JavaMultiThreadProgramming.ThreadPriority;
/**
 * 线程优先级高的线程首先执行
 * @author liuwei
 *
 */
public class Run4 {
	public static void main(String args[]){
		try{
			ThreadA a = new ThreadA();
			a.setPriority(Thread.NORM_PRIORITY -3);
			a.start();
			ThreadA b = new ThreadA();
			b.setPriority(Thread.NORM_PRIORITY +3);
			b.start();
			Thread.sleep(10000);
			a.stop();
			b.stop();
			System.out.println("a="+a.getCount());
			System.out.println("b="+b.getCount());
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
