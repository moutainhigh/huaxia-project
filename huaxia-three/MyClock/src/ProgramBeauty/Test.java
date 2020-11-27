package ProgramBeauty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CPU的正选曲线
 * @author liuwei
 *
 */
public class Test {
	public static void main(String args[]){
		Runtime r = Runtime.getRuntime();//获取当前cpu的数量,创建对应的线程
		System.out.println(r.availableProcessors());
		ExecutorService pool= Executors.newFixedThreadPool(r.availableProcessors());
		for(int i=0;i<r.availableProcessors()-1;i++){
			pool.execute(new Loop());
		}
		pool.shutdown();
	}
}
class Loop implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		int i=0;
//		while(true){
//			i++;
//		}
		final double SPLIT = 0.01;
		final int COUNT = (int)(2/SPLIT);
		final double PI = Math.PI;
		
		final int INTERVAL = 100;
		long[]  busySpan = new long[COUNT];
		long[] idleSpan = new long[COUNT];
		int half = INTERVAL/2;
		double radian = 0.0;
		for(int i=0;i<COUNT;i++){
			busySpan[i]= (long)(half+(Math.sin(PI*radian)*half));
			idleSpan[i] = INTERVAL-busySpan[i];
			radian +=SPLIT;
		}
		long startTime =0;
		int j=0;
		while(true){
			j=j%COUNT;
			startTime = System.currentTimeMillis();
			while(System.currentTimeMillis()-startTime < busySpan[j])
				;
			try {
				Thread.sleep(idleSpan[j]);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			j++;
		}
	}
	
}
