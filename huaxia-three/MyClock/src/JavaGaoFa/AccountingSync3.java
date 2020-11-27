package JavaGaoFa;
/**
 * 不线程同步，数据不一致
 * 不同的实例，不同的实例方法，可以改为静态方法，可以线程同步
 * @author liuwei
 *
 */
public class AccountingSync3 implements Runnable {
	static AccountingSync3 instance = new AccountingSync3();
	static volatile int i=0;
	public static synchronized  void increase(){
		i++;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
			for(int j=0;j<10000000;j++){
				increase();
		}
	}
	public static void main(String args[]){
		Thread t1 = new Thread(new AccountingSync3());
		Thread t2 = new Thread(new AccountingSync3());
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
