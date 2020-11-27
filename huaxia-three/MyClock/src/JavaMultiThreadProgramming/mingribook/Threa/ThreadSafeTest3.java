/**
 * Title: ThreadSafeTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月16日下午4:46:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.Threa;

/**
 * @class_name:ThreadSafeTest2019年10月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月16日下午4:46:49
 */
public class ThreadSafeTest3 implements Runnable {
	int num =10;
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			doit();
		}
	}
	public synchronized void doit(){
			if(num>0){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("tickets"+num--);
			}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月16日下午4:46:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadSafeTest3 t =new ThreadSafeTest3();
		Thread ta = new Thread(t);
		Thread tb = new Thread(t);
		Thread tc = new Thread(t);
		Thread td = new Thread(t);
		Thread te = new Thread(t);
		Thread tf = new Thread(t);
		ta.start();
		tb.start();
		tc.start();
		td.start();
		te.start();
		tf.start();
	}

}
