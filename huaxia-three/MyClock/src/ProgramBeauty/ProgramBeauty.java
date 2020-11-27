package ProgramBeauty;
/**
 * 占用CPU资源
 * @author liuwei
 *
 */
public class ProgramBeauty {

	public static void main(String args[]){
		//测试4个内核的机器运行的CUP占用率。一个for循环占用了一个cpu内核
//		new Thread(){
//			public void run(){
//				int i = 0;
//				 while(true){
//					 i++;
//				 }
//			}
//		}.start();
//		new Thread(){
//			public void run(){
//				int i = 0;
//				 while(true){
//					 i++;
//				 }
//			}
//		}.start();
//		new Thread(){
//			public void run(){
//				int i = 0;
//				 while(true){
//					 i++;
//				 }
//			}
//		}.start();
		new Thread(){
			public void run(){
				for(;;){
					for(long i=0;i<96000000000000000L;i++){
						
					}
//			   Thread t =	new Thread(){
//						public void run(){
//							int i = 0;
//							 while(true){
//								 i++;
//							 }
//						}
//					};
//					t.start();
					try {
						Thread.sleep(100);
//						t.stop();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
