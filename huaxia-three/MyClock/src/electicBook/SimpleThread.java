package electicBook;
/**
 * 测试运行线程的例子。运行线程
 * @author Liuwei
 */
public class SimpleThread extends Thread{
	public SimpleThread(String name){
		setName(name);
	}
	@Override
	public void run(){
		int i =0;
		while(i++<5){
			try{
				System.out.println(getName()+"执行步骤"+i);
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]){
		SimpleThread t1 = new SimpleThread("线程1");
		SimpleThread t2 = new SimpleThread("====线程2");
		t1.start();
		t2.start();
	}
}
