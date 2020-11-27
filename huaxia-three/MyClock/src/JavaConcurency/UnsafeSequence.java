package JavaConcurency;
/**
 * 非线程安全的方法，测试
 * @author liuwei
 */
public class UnsafeSequence {
	private int value;
	/** 返回一个唯一的数值 **/
	public int getNext(){
		return value++;
	}
	public static void main(String args[]){
		final UnsafeSequence unsafe = new UnsafeSequence();
		Thread one = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					System.out.println(unsafe.getNext());
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
					System.out.println(unsafe.getNext());
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
