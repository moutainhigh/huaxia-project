package UnderstandingTheJVM;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * integer类型自动增长
 * AtomicInteger 实现自动增长，然后是线程安全的，数据是同步的。
 * @author liuwei
 */
public class AtomicTest {
	public static AtomicInteger race = new AtomicInteger();
	public static void increase(){
		race.incrementAndGet();
	}
	private static final int THREADS_COUNT = 20;
	public static void main(String args[]) throws Exception{
		Thread[] threads = new Thread[THREADS_COUNT];
		for(int  i=0;i<THREADS_COUNT;i++){
			threads[i] = new Thread(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for(int i=0;i<10000;i++)
						increase();
				}
			});
			threads[i].start();
			}
		while(Thread.activeCount() >1)
			Thread.yield();
		System.out.println(race);
		}
}
