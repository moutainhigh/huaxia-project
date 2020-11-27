/**
 * Title: ThreadPriority.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月5日下午4:19:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

/**
 * @class_name:ThreadPriority2019年12月5日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月5日下午4:19:23
 */
public class ThreadPriority {

	/**
	 * 
	 */
	public ThreadPriority() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月5日下午4:19:23
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(
				new Runnable(){
					@Override
					public void run(){
						while(true){
							System.out.print("t1");
						}
					}
				}
				);
		t1.setPriority(3);
		Thread t2 = new Thread(
				new Runnable(){
					@Override
					public void run(){
						while(true){
							System.err.print("t2");
						}
					}
				}
				);
		t2.setPriority(3);
		t1.start();
		t2.start();
	}

}
