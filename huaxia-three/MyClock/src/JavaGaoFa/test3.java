package JavaGaoFa;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试一些共享和变量和threadLocal对象的使用
 * @author liuwei
 *
 */
public class test3 {
	public static final int GET_COUNT = 10000000;
	public static final int THREAD_COUNT = 4;
	static ExecutorService exe = Executors.newFixedThreadPool(THREAD_COUNT);
	public static Random rnd =new Random(123);
	public static ThreadLocal<Random> tRnd = new ThreadLocal<Random>(){
		@Override
		protected Random initialValue(){
			return new Random(123);
		}
	};
	public static class RndTask implements Callable<Long>{
		private int mode = 0;
		public RndTask(int mode){
			this.mode = mode;
		}
		public Random getRandom(){
			if(mode == 0){
				return rnd;
			}else  if(mode == 1){
				return tRnd.get();
			}else{
				return null;
			}
		}
		@Override
		public Long call() throws Exception {
			// TODO Auto-generated method stub
			long b = System.currentTimeMillis();
			for(long i=0;i<GET_COUNT;i++){
				getRandom().nextInt();
			}
			long e = System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName()+" spend"+(e-b)+"ms");
			return e-b;
		}	
	}
	public static void main(String args[]){
		Future<Long>[] futs = new Future[THREAD_COUNT];
		for(int i=0;i<THREAD_COUNT;i++){
			futs[i] =exe.submit(new RndTask(0));
		}
		long totaltime = 0;
		for(int i=0;i<THREAD_COUNT;i++){
			try {
				totaltime += futs[i].get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("多线程访问同一个Random实例："+totaltime+"ms");
		//ThreadLocal的情况
		for(int i=0;i<THREAD_COUNT;i++){
			futs[i] =exe.submit(new RndTask(1));
		}
		 totaltime = 0;
		for(int i=0;i<THREAD_COUNT;i++){
			try {
				totaltime += futs[i].get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("使用ThreadLocal包装Random实例："+totaltime+"ms");
		exe.shutdown();
	}
}
