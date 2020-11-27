package ThinkInJava;

import java.util.concurrent.TimeUnit;
/**
 * 测试后台想成的运行，自己可以设置一些后台线程，守护线程
 * @author User
 *
 */
public class SimpleDaemons implements Runnable {

	public SimpleDaemons() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			TimeUnit.MILLISECONDS.sleep(1000);
			System.out.println(Thread.currentThread()+" "+this);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args)throws Exception{
		for(int i=0;i<10;i++){
			Thread daemon= new Thread(new SimpleDaemons());
			daemon.setDaemon(true);//Must call before start()
			daemon.start();
		}
		System.out.println("All daemons started");
		TimeUnit.MINUTES.sleep(175);
	}
}
