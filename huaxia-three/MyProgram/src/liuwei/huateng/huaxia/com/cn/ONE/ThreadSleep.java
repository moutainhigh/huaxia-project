/**
 * Title: ThreadSleep.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月5日下午4:07:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

/**
 * @class_name:ThreadSleep2019年12月5日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月5日下午4:07:02
 */
public class ThreadSleep {

	/**
	 * 
	 */
	public ThreadSleep() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月5日下午4:07:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(
				new Runnable(){
					@Override
					public void run(){
						long startTime = System.currentTimeMillis();
						sleep(2_000L);
						long endTime = System.currentTimeMillis();
						System.out.println(String.format("Total spend %d ms",(endTime -startTime)))	;
					}
				}
				).start();;
				long startTime = System.currentTimeMillis();
				sleep(3_000L);
				long endTime = System.currentTimeMillis();
				System.out.println(String.format("Total spend %d ms",(endTime -startTime)))	;
	}
	/**
	 * @Title: sleep
	 *@Description: TODO
	 *@param ms
	 *@author: LiuWei
	 *@Date: 2019年12月5日下午4:13:18
	 */
	private static void sleep(long ms){
		try{
			Thread.sleep(ms);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
