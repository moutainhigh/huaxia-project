package UnderstandingTheJVM;
/**
 * 线程死锁监控
 * 出现死锁的原因是，String.valueOf
 * 在-128和127之间的数，会被缓存
 * 即执行了2000次，造成资源的死锁
 * @author liuwei
 *
 */
public class ThreadLockExple {
	static class SynAddRunable implements Runnable{
		int a,b ;
		public SynAddRunable(int a,int b){
			this.a = a;
			this.b = b;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized(Integer.valueOf(a)){
				synchronized(Integer.valueOf(b)){
					System.out.println(a+b);
				}
			}
		}
	}
	public static void main(String args[]){
		for(int i=0;true;i++){
			new Thread(new SynAddRunable(1,2)).start();
			new Thread(new SynAddRunable(2,1)).start();
		}
	}
}
