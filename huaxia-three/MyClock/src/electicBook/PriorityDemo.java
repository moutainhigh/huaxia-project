package electicBook;
/**
 * 测试线程的优先级。进行优先级的使用
 * @author Liuwei
 */
public class PriorityDemo implements Runnable {
	@Override
	public void run() {
		for(int i =0;i<5;i++){
			try{
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"运行，i="+i);
		}
	}
	public static void main(String args[]){
		Thread t1= new Thread(new PriorityDemo(),"线程1");
		Thread t2= new Thread(new PriorityDemo(),"线程2");
		Thread t3= new Thread(new PriorityDemo(),"线程3");
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		t3.setPriority(Thread.NORM_PRIORITY);
		t1.start();
		t2.start();
		t3.start();
	}
}
