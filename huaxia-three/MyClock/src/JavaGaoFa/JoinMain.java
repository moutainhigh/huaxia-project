package JavaGaoFa;
/**
 * 测试Join方法
 * join方法，让调用线程在当前线程等待
 * @author liuwei
 *
 */
public class JoinMain {
  public volatile static int i = 0;
  public static class AddThread extends Thread{
	  @Override
	  public void run(){
		  for( i=0;i<10000000;i++);
	  }
  }
  public static void main(String args[]){
	  AddThread at =new AddThread();
	  at.start();
	  try {
		at.join();
		  Thread.sleep(1);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  System.out.println(i);
  }
}
