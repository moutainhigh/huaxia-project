package JavaGaoFa;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的异常处理
 * submit 不提示报错信息
 * execute 提示报错信息
 * @author liuwei
 *
 */
public class DivTask implements Runnable{
	int a,b;
	public DivTask(int a,int b){
		this.a = a;
		this.b = b;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		double re = a/b;
		System.out.println(re);
	}
	public static void main(String args[]){
		ThreadPoolExecutor pools = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0L,TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
		for(int i=0;i<5;i++){
//			pools.submit(new DivTask(100,i));
			pools.execute(new DivTask(100,i));
		}
	}
}
