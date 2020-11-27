package JavaMultiThreadProgramming.SingleCase;

public class MyObject2 {
	private static MyObject2 myObject;
	private MyObject2(){
	}
	public static MyObject2 getInstance(){
		//延迟加载
		if(myObject != null){
			
		}else{
			myObject= new MyObject2();
		}
		return myObject;
	}
}
