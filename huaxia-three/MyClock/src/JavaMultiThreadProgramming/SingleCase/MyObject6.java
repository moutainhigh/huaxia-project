package JavaMultiThreadProgramming.SingleCase;
/**
 * 双检测机制
 * @author liuwei
 *
 */
public class MyObject6 {
	private static MyObject6 myObject;
	private MyObject6(){
	}
	 public static MyObject6 getInstance(){
		 
		//延迟加载
		if(myObject != null){
			
		}else{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized(MyObject6.class){
				if(myObject == null){
			myObject= new MyObject6();
				}
		}
		 }
		return myObject;
	}
}
