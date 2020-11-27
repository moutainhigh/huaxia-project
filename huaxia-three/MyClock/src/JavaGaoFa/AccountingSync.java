package JavaGaoFa;
/**
 * 线程同步，数据一致
 * @author liuwei
 *
 */
public class AccountingSync implements Runnable {
	static AccountingSync instance = new AccountingSync();
	static volatile int i=0;
	public static void increase(){
		i++;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(instance){
			for(int j=0;j<10000000;j++){
				increase();
			}
		}
	}
	public static void main(String args[]){
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);
	}

}
