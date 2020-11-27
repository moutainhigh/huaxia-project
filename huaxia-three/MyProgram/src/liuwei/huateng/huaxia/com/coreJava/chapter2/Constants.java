/**
 * Title: Constants.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月25日下午4:04:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

/**
 * @class_name:Constants2019年12月25日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月25日下午4:04:31
 */
public class Constants {

	/**
	 * 
	 */
	public Constants() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月25日下午4:04:31
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final double CM_PER_INCH = 2.54;
		double paperWidth = 8.5;
		double paperHeight = 11;
		System.out.println("Paper size in centimeters:"+paperWidth * CM_PER_INCH+" by "+paperHeight*CM_PER_INCH);
	}

}
