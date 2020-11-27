/**
 * Title: CurrentThread.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月5日下午4:40:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

/**
 * @class_name:CurrentThread2019年12月5日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月5日下午4:40:27
 */
public class CurrentThread {
	public CurrentThread() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月5日下午4:40:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread = new Thread(){
			@Override
			public void run(){
				System.out.println(Thread.currentThread() == this);
			}
		};
		thread.start();
		String name = Thread.currentThread().getName();
		System.out.println(name.equals("main"));
	}

}
