package JavaMultiThreadProgramming.ThreadPriority;
/**
 * 线程优先级高的线程首先执行
 * @author liuwei
 *
 */
public class Run3 {
	public static void main(String args[]){
		try{
			MyThread3 mythread = new MyThread3();
			mythread.setDaemon(true);
			mythread.start();
			Thread.sleep(5000);
			System.out.println("main离开，mythread对象也不再打印了，是守护线程，也就是线程停止了。");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
