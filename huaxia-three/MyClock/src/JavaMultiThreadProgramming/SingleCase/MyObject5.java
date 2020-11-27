package JavaMultiThreadProgramming.SingleCase;

public class MyObject5 {
	private static MyObject5 myObject;
	private MyObject5(){
	}
	 public static MyObject5 getInstance(){
		 synchronized(MyObject5.class){
		//延迟加载
		if(myObject != null){
			
		}else{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myObject= new MyObject5();
		}
		 }
		return myObject;
	}
}
