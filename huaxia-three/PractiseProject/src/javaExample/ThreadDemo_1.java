/**
 * Title: ThreadDemo_1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��27������3:44:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;

/**
 * @class_name:ThreadDemo_12020��8��27��
 * @comments:��һ�̵߳���������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������3:44:37
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
	 *@Date: 2020��8��27������3:44:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestThread().run();
		for(int i=0;i<5;++i)
		{
			System.out.println("main�߳�����");
		}
	}

}
/**
 * @class_name:TestThread2020��8��27��
 * @comments: Thread.run()���൱��run��������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������3:46:33
 */
class TestThread
{
	public void run(){
		for(int i=0;i<5;++i)
		{
			System.out.println("TestThread������");
		}
	}
}