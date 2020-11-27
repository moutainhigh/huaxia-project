package JavaMultiThreadProgramming.ThreadState;
/**
 * wait 出现在是WAITING状态
 * @author liuwei
 *
 */
public class Run5 {
	public static void main(String args[]){
		try{
			MyThread5 t =new MyThread5();
			t.setName("a");
			t.start();
			Thread.sleep(1000);
			System.out.println("main方法中的状态："+t.getState());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
