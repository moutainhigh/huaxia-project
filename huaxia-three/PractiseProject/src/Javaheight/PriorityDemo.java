/**
 * Title: PriorityDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月13日上午11:00:46
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

/**
 * @class_name:PriorityDemo2020年10月13日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月13日上午11:00:46
 */
public class PriorityDemo {

	/**
	 * Constructor
	 */
	public PriorityDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月13日上午11:00:46
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread high = new HightPriority();
		Thread low = new LowPriority();
		high.setPriority(Thread.MAX_PRIORITY);
		low.setPriority(Thread.MIN_PRIORITY);
		low.start();
		high.start();
	}
	/**
	 * 
	 * @class_name:HightPriority2020年10月13日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年10月13日上午11:01:27
	 */
	public static class HightPriority extends Thread{
		static int count  =0;
		public void run(){
			while(true){
				synchronized(PriorityDemo.class){
					count++;
					if(count > 100000000){
						System.out.println("HightPriority  is complete");
						break;
					}
				}
			}
		}
	}
	/**
	 * 
	 * @class_name:LowPriority2020年10月13日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年10月13日上午11:03:52
	 */
	public static class LowPriority extends Thread{
		static int count  =0;
		public void run(){
			while(true){
				synchronized(PriorityDemo.class){
					count++;
					if(count > 100000000){
						System.out.println("LowPriority  is complete");
						break;
					}
				}
			}
		}
	}
}
