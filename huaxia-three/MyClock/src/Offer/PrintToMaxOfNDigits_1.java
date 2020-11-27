/**
 * Title: PrintToMaxOfNDigits_1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月31日下午4:09:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:PrintToMaxOfNDigits_12019年10月31日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月31日下午4:09:01
 */
public class PrintToMaxOfNDigits_1 {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月31日下午4:09:01
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintToMaxOfNDigits_2(3);
	}
	/**
	 * @Title: PrintToMaxOfNDigits_2
	 *@Description: TODO
	 *@param n
	 *@author: LiuWei
	 *@Date: 2019年10月31日下午4:11:22
	 */
	static void PrintToMaxOfNDigits_2(int n){
		int number = 1;
		int i=0;
		while(i++<n)
			number*=10;
		for( i=1;i<number;i++){
			System.out.printf("%d\t", i);
		}
	}
}
