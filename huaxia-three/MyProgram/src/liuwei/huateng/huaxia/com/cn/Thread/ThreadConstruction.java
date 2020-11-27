/**
 * Title: ThreadConstruction.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��9������9:36:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Thread;

/**
 * @class_name:ThreadConstruction2019��12��9��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��9������9:36:04
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
	 *@Date: 2019��12��9������9:36:04
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
