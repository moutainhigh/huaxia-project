package JavaMultiThreadProgramming.ThreadState;
/**
 * BLOCKED 出现在线程等待锁的时候
 * @author liuwei
 *
 */
public class Run4 {
	public static void main(String args[]){
		try{
			MyThread3 t =new MyThread3();
			t.setName("a");
			t.start();
			MyThread4 t2 =new MyThread4();
			t2.setName("b");
			t2.start();
			Thread.sleep(1000);
			System.out.println("main方法中的状态："+t2.getState());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
