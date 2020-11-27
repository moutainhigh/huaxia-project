/**
 * Title: ThreadDemo_1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月27日下午3:44:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;

/**
 * @class_name:ThreadDemo_12020年8月27日
 * @comments:单一线程的运行流程
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日下午3:44:37
 */
public class ThreadDemo_1 {

	/**
	 * Constructor
	 */
	public ThreadDemo_1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月27日下午3:44:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestThread().run();
		for(int i=0;i<5;++i)
		{
			System.out.println("main线程运行");
		}
	}

}
/**
 * @class_name:TestThread2020年8月27日
 * @comments: Thread.run()。相当于run方法运行
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日下午3:46:33
 */
class TestThread
{
	public void run(){
		for(int i=0;i<5;++i)
		{
			System.out.println("TestThread在运行");
		}
	}
}