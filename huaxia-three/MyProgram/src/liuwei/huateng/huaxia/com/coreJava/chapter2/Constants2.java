/**
 * Title: Constants.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��25������4:04:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

/**
 * @class_name:Constants2019��12��25��
 * @comments:�ೣ���������������ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��25������4:04:31
 */
public class Constants2 {
	public static final double CM_PER_INCH = 2.54;
	/**
	 * 
	 */
	public Constants2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��25������4:04:31
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double paperWidth = 8.5;
		double paperHeight = 11;
		System.out.println("Paper size in centimeters:"+paperWidth * CM_PER_INCH+" by "+paperHeight*CM_PER_INCH);
	}

}
