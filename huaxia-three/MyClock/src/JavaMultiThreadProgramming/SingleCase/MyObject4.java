package JavaMultiThreadProgramming.SingleCase;

public class MyObject4 {
	private static MyObject4 myObject;
	private MyObject4(){
	}
	synchronized public static MyObject4 getInstance(){
		//延迟加载
		if(myObject != null){
			
		}else{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myObject= new MyObject4();
		}
		return myObject;
	}
}
