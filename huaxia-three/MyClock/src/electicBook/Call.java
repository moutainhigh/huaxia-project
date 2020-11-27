package electicBook;
/**
 * 没有使用线程的共享资源的互斥访问
 * @author Liuwe
 */
 class PhoneCall {
	public static void call(String name){
		try{
			System.out.println("<--"+name+"拨打电话-->");
			Thread.sleep(100);
			System.out.println("<--"+name+"正在通话中。。。-->");
			Thread.sleep(100);
			System.out.println("<--"+name+"挂断电话-->");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
public class  Call extends Thread{
	public Call(String arg0){
		super(arg0);
		System.out.println("<--在这里定义call类的构造方法-->");
	}
	@Override
	public void run(){
		SynPhoneCall.call(getName());
	}
	public static void main(String args[]){
		Call first = new Call("First");
		Call second = new Call("Seconds");
		Call third = new Call("Third");
		first.start();
		second.start();
		third.start();
	}
}
class SynPhoneCall {
	public synchronized static void call(String name){
		try{
			System.out.println("<--"+name+"拨打电话-->");
			Thread.sleep(100);
			System.out.println("<--"+name+"正在通话中。。。-->");
			Thread.sleep(100);
			System.out.println("<--"+name+"挂断电话-->");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}