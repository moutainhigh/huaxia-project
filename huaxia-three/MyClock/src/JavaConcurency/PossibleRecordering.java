package JavaConcurency;
/**
 * 测试线程同步的一些数据问题
 * @author liuwei
 */
public class PossibleRecordering {
	static String x="0",y="0";
	static String a ="0",b="0";
	public static void main(String args[]){
		Thread three = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					synchronized(a){
						synchronized(b){
							synchronized(x){
								synchronized(y){
									x="0";y="0";
									a ="0";b="0";
								}
							}
						}
					}
					 try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				 
			}
		});
		Thread one = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					synchronized(a){
						synchronized(x){
							synchronized(b){
								a ="1";
								x="1";
							}
						}
					}
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		Thread other = new Thread(new Runnable(){
			@Override
			public void run() {
			while(true){
				
				synchronized(b){
					synchronized(y){
						synchronized(a){
							b = "1";
							y = "1";
						}
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		});
		one.start(); 
		other.start();
		three.start();
//		try {
//			one.join();
//			other.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		while(true){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			 System.out.println("("+x+","+y+")");
//		if(x==1&&y==1)
//			 System.err.println("("+x+","+y+")");
//		}
//		if(x.equals("1")&& y.equals("1"))
//			 System.err.println("("+x+","+y+")");
//		}
//		if(x==0&&y==1)
//			 System.err.println("("+x+","+y+")");
//		}
		}
	}
}
