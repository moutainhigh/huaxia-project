/**
 * Title: CharClass_1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日上午10:38:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:CharClass_12020年9月23日
 * @comments:trycatchfinally捕获异常
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午10:38:39
 */
public class CharClass_1 {

	/**
	 * Constructor
	 */
	public CharClass_1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午10:38:39
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubThread_1 s1 =new SubThread_1();
		SubThread_1 s2 = new SubThread_1();
		SubThread_1 s3 = new SubThread_1();
		s1.start();
		s2.start();
		s3.start();
	}
}
/***
 * 
 * @class_name:SubThread_12020年9月23日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午10:39:48
 */
class SubThread_1 extends Thread{
	public void run(){
		try {
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"!");
	}
}