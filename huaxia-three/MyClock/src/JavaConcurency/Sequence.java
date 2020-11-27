package JavaConcurency;
/**
 * 线程安全的数值生成器
 * 线程安全就是多个线程执行同一个对象的方法，不会发生数值错我，实现机制是synchronized枷锁机制
 * @author liuwei
 */
public class Sequence {
	private int Value;
	public synchronized int getNext(){
		return Value++;
	}
	public static void main(String args[]){
		final Sequence safeThead = new Sequence();
		Thread one = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					System.out.println(safeThead.getNext());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		Thread two = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					System.out.println(safeThead.getNext());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		one.start();
		two.start();
	}
}
