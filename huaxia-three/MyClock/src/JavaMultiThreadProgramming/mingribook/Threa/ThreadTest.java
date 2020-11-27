/**
 * Title: ThreadTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月16日下午4:42:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.Threa;

/**
 * @class_name:ThreadTest2019年10月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月16日下午4:42:48
 */
public class ThreadTest extends Thread{
	private int count =10;
	@Override
	public void run(){
		while(true){
			System.out.println(count+" ");
			if(--count == 0){
				return;
			}
		}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月16日下午4:42:48
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ThreadTest().start();
	}

}
