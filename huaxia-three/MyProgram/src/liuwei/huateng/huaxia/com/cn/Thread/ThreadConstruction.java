/**
 * Title: ThreadConstruction.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月9日上午9:36:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Thread;

/**
 * @class_name:ThreadConstruction2019年12月9日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月9日上午9:36:04
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
	 *@Date: 2019年12月9日上午9:36:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread("t1");
		ThreadGroup group = new ThreadGroup("TestGroup");
		Thread t2 = new Thread(group,"t2");
		ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
		System.out.println("Main thread belong group"+mainThreadGroup.getName());
		System.out.println("t1 and main belong the sam group:"+(mainThreadGroup == t1.getThreadGroup()));
		System.out.println("t2 thread group not  belong the main group:"+(mainThreadGroup == t2.getThreadGroup()));
		System.out.println("t2 thread group  belong the main TestGroup:"+(group == t2.getThreadGroup()));
	}

}
