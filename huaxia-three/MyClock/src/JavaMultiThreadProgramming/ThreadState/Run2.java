package JavaMultiThreadProgramming.ThreadState;
/**
 * sleep 睡眠状态TIMED_WAITING
 * @author liuwei
 *
 */
public class Run2 {
	public static void main(String args[]){
		try{
			MyThread2 t =new MyThread2();
			t.start();
			Thread.sleep(1000);
			System.out.println("main方法中的状态2："+t.getState());
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
