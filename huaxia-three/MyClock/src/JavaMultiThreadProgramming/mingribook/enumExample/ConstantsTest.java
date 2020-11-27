/**
 * Title: ConstantsTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月16日上午9:37:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.enumExample;
/**
 * 
 * @class_name:Constants2019年10月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月16日上午9:38:16
 */
interface Constants{
	public static final int Constatnts_A = 1;
	public static final int Constatnts_B = 12;
}
/**
 * @class_name:ConstantsTest2019年10月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月16日上午9:37:24
 */
public class ConstantsTest {
	/**
	 * 
	 * @class_name:Constants22019年10月16日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2019年10月16日上午9:39:10
	 */
	enum Constants2{
		Constants_A,Constants_B
	}
	/**
	 * @Title: doit
	 *@Description: TODO
	 *@param c
	 *@author: LiuWei
	 *@Date: 2019年10月16日上午9:42:11
	 */
	public static void doit(int c){
		switch(c){
		case Constants.Constatnts_A:
			System.out.println("doit() Constants_A");
			break;
		case Constants.Constatnts_B:
			System.out.println("doit() Constants_B");
			break;
		}
	}
	/**
	 * @Title: doit
	 *@Description: TODO
	 *@param c
	 *@author: LiuWei
	 *@Date: 2019年10月16日上午9:42:18
	 */
	public static void doit2(Constants2 c){
		switch(c){
		case Constants_A:
			System.out.println("doit2() Constants_A");
			break;
		case Constants_B:
			System.out.println("doit2() Constants_B");
			break;
		}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月16日上午9:37:24
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConstantsTest.doit(Constants.Constatnts_A);
		ConstantsTest.doit2(Constants2.Constants_A);
		ConstantsTest.doit2(Constants2.Constants_B);
		ConstantsTest.doit(3);
	}
	
}
