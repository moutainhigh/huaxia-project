package JavaMultiThreadProgramming.Thread;

public class MyThread2 extends Thread{
	private int i=0;
	@Override
	public void run(){
		try{
			this.stop();
		}catch(ThreadDeath e){
			System.out.println("进入了catch（）方法！");
			e.printStackTrace();
		}
	}
}
