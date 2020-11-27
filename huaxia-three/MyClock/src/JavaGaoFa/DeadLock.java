package JavaGaoFa;
/**
 * 哲学家就餐问题。造成死锁问题
 * @author liuwei
 *
 */
public class DeadLock extends Thread{
	protected Object tool;
	static Object fork1 =new Object();
	static Object fork2  = new Object();
	public DeadLock(Object obj){
		this.tool = obj;
		if(tool == fork1){
			this.setName("哲学家A");
		}
		if(tool == fork2){
			this.setName("哲学家B");
		}
	}
	@Override
	public void run(){
		if(tool == fork1){
			synchronized(fork1){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(fork2){
					System.out.println("哲学家A开始吃饭了");
				}
			}
		}
		if(tool == fork2){
				synchronized(fork2){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized(fork1){
						System.out.println("哲学家A开始吃饭了");
					}
				}
		}
	}
	public static void main(String args[]){
		DeadLock 哲学家A = new DeadLock(fork1);
		DeadLock 哲学家B = new DeadLock(fork2);
		哲学家A.start();
		哲学家B.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
