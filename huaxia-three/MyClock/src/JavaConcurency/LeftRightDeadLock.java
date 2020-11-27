package JavaConcurency;
/**
 * 线程死锁的例子,简单的顺讯死锁
 * @author liwuei
 */
public class LeftRightDeadLock {
	private final Object left = new Object();
	private final Object right = new Object();
	public void leftRight(){
		synchronized(left){
			synchronized(right){
				System.out.println("leftRight");
			}
		}
	}
	public void rightleft(){
		synchronized(right){
			synchronized(left){
				System.out.println("rightleft");
			}
		}
	}
	public  static void main(String args[]){
		final LeftRightDeadLock lock = new LeftRightDeadLock();
		Thread one = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					lock.leftRight();
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
					lock.rightleft();
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
