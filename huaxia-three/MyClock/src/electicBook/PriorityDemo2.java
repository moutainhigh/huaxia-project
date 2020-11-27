package electicBook;
/**
 * 测试线程的yield的方法，实现一些方法测试运行
 * @author Liuwei
 */
public class PriorityDemo2 implements Runnable {

	@Override
	public void run() {
		for(int i =0;i<5 ;i++){
			System.out.println(Thread.currentThread().getName()+"运行，i="+i);
			if(i==3){
				System.out.println("线程礼让：");
				Thread.currentThread().yield();//线程礼让
			}
		}
	}
	public static void main(String args[]){
		PriorityDemo2 my = new PriorityDemo2();
		Thread t1= new Thread(my,"线程1");
		Thread t2 = new Thread(my,"线程2");
		t1.start();
		t2.start();
	}
}
