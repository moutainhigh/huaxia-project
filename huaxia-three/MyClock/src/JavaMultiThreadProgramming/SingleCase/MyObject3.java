package JavaMultiThreadProgramming.SingleCase;

public class MyObject3 {
	private static MyObject3 myObject;
	private MyObject3(){
	}
	public static MyObject3 getInstance(){
		//延迟加载
		if(myObject != null){
			
		}else{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myObject= new MyObject3();
		}
		return myObject;
	}
}
