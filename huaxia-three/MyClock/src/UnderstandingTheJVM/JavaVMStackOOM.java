package UnderstandingTheJVM;
/**
 * 创建线程导致内存溢出异常
 * @author liuwei
 *
 */
public class JavaVMStackOOM {
	private void dontStop(){
		 System.out.println(Thread.currentThread().getName()+"执行了");
		 while(true){
			 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
	public void stackLeakByThread(){
		while(true){
			Thread thread = new Thread(new Runnable(){
				@Override
				public void run() {
					dontStop();
				}	
			});
			thread.start();
		}
	}
	public static void main(String args[])throws Throwable{
		JavaVMStackOOM com = new JavaVMStackOOM();
		com.stackLeakByThread();
	}
}
