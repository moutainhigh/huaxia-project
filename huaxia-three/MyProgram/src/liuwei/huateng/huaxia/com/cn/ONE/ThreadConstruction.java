/**
 * Title: ThreadConstruction.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月5日下午3:35:28
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

/**
 * @class_name:ThreadConstruction2019年12月5日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月5日下午3:35:28
 */
public class ThreadConstruction {

	/**
	 * 
	 */
	public ThreadConstruction() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月5日下午3:35:28
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length < 1)
		{
			System.out.println("please enter the stack size.");
			System.exit(1);
		}
		ThreadGroup group = new ThreadGroup("TestGroup");
		Runnable runnable = new Runnable(){
			final int MAX = Integer.MAX_VALUE;
			@Override
			public void run(){
				int i=0;
				recurse(i);
			}
			private void recurse(int i){
				System.out.println(i);
				if(i<MAX){
					recurse(i+1);
				}
			}
		};
		Thread thread = new Thread(group,runnable,"Test",Integer.parseInt(args[0]));
		thread.start();
	}

}
