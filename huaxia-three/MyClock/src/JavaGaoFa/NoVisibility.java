package JavaGaoFa;
/**
 * 变量的不可见性
 * 一些修改变量，不能再run方法中体现出来，数据不能同步，可以使用volatile变量声明，同步
 * @author liuwei
 *
 */
public class NoVisibility {
	private volatile static boolean ready;
	private static int number;
	private static class ReaderThread extends Thread{
		@Override
		public void run(){
			while(!ready);
			System.out.println(number);
		}
	}
	public static void main(String args[]){
		new ReaderThread().start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		number = 42;
		ready = true;
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
