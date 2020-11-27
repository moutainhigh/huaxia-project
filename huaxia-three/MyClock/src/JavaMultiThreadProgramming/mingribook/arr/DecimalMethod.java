/**
 * Title: DecimalMethod.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日上午9:42:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.arr;

import java.text.DecimalFormat;

/**
 * @class_name:DecimalMethod2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日上午9:42:53
 */
public class DecimalMethod {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日上午9:42:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecimalFormat myFormat = new DecimalFormat();
		myFormat.setGroupingSize(3);
		String output = myFormat.format(123456.789);
		System.out.println("将数字以每两个数字分组"+output);
		myFormat.setGroupingUsed(false);
		String output2 = myFormat.format(123456.789);
		System.out.println("不容许数字分组"+output2);
	}

}
