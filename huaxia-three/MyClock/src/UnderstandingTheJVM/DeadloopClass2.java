package UnderstandingTheJVM;
/**
 * 模拟阻塞操作
 * @author liuwei
 *
 */
public  class DeadloopClass2 {
	static  class DeadloopClass {
		static{
			if(true){
				System.out.println(Thread.currentThread()+"imit DeadloopClass");
				while(true){
				}
			}
		}
	}
	public static void main(String args[]){
		Runnable script = new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread()+"start");
				DeadloopClass dlc =new DeadloopClass();
				System.out.println(Thread.currentThread()+"run over");
			}
		};
		Thread thread1= new Thread(script);
		Thread thread2= new Thread(script);
		thread1.start();
		thread2.start();
	}
}
