/**
 * Title: Test.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月13日下午1:57:41
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

/**
 * @class_name:Test2020年10月13日
 * @comments:
 * 无异常和报错，但是给出了一个错误的结果
 * 1073741827
	1431655768
	avg = -894784850
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月13日下午1:57:41
 */
public class Test {

	/**
	 * Constructor
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月13日下午1:57:41
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int  v1 = 1073741827;
		int v2 = 1431655768;
		System.out.println(v1);
		System.out.println(v2);
		int avg = (v1+v2)/2;
		System.out.println("avg = "+avg);
	}

}
