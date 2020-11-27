package JavaGaoFa;
/**
 * volatile声明是一个经常被线程修改的变量，需要小心处理
 * volatile并不是一个锁变量，synchronize和lock即可
 * @author volatile
 *
 */
public class Testvolatile {

	static volatile int i = 0;
	public static class PlusTask implements Runnable{
		@Override
		public void run(){
			for(int k= 0;k<10000;k++)
				i++;
		}
	}
	public static void main(String args[]){
		Thread[] threads = new Thread[10];
		for(int i=0;i<10;i++){
			threads[i] = new Thread(new PlusTask());
			threads[i].start();
		}
		for(int i=0;i<10;i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(i);
	}
}
