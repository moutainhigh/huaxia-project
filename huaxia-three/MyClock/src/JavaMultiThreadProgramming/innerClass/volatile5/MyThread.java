package JavaMultiThreadProgramming.innerClass.volatile5;
/**
 * volatile 保证可见性，不能保证原子性
 * 可见性是指，多个线程是否可以更改同一个变量
 * @author User
 *
 */
public class MyThread extends Thread{
	volatile public static int count ;
	private static void addCount(){
		for(int i=0;i<100;i++){
			count++;
		}
		System.out.println("count="+count);
	}
	@Override
	public void run(){
		addCount();
	}
}
