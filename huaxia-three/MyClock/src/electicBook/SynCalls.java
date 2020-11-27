package electicBook;
/**
 * 对变量对象进行一些synchronized关键字的使用，互斥访问
 * @author Liuwei
 */
public class SynCalls implements Runnable {
	private String name = "";
	private PhoneCalls phone = null;
	private Thread thread = null;
	public SynCalls(String name,PhoneCalls phone){
		this.name = name;
		this.phone = phone;
		this.thread = new Thread(this);
	}
	public void start(){
		thread.start();
	}
	@Override
	public void run() {
		synchronized(this.phone){
			this.phone.call(this.name);
		}
	}
	public static void main(String args[]){
		PhoneCalls phone = new PhoneCalls("营部");
		SynCalls first = new SynCalls("First",phone);
		SynCalls second = new SynCalls("Second",phone);
		SynCalls third = new SynCalls("Thread",phone);
		first.start();
		second.start();
		third.start();
	}
}
class PhoneCalls {
	private String phoneName = "";
	public PhoneCalls(String name){
		this.phoneName = name;
	}
	public  void call(String name){
		try{
			System.out.println("<--"+name+"拨打"+this.phoneName+"电话-->");
			Thread.sleep(100);
			System.out.println("<--"+name+"正在通话中。。。-->");
			Thread.sleep(100);
			System.out.println("<--"+name+"挂断"+this.phoneName+"电话-->");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}