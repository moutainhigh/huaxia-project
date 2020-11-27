/**
 * Title: P6_1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日下午2:15:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:P6_12020年8月10日
 * @comments:  闰年的计算方法，能被4整除，但是不能被100整除，或者能被400 整除
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日下午2:15:37
 */
public class P6_1 {

	/**
	 * Constructor
	 */
	public P6_1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午2:15:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int year ;
		int count = 0;
		System.out.println("2000年到3000年之间所有的闰年如下：\n");
		for(year =2000;year<= 3000;year++)
		{
			if(LeapYear(year))
			{
				System.out.print(year+" ");
				count++;
				if(count % 16 == 0)
					System.out.println();
			}
		}
		System.out.print("\n");
	}
	/**
	 * @Title: LeapYear
	 *@Description: 闰年的计算方法，能被4整除，但是不能被100整除，或者能被400 整除
	 *@param year
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午2:16:10
	 */
	public static boolean LeapYear(int year){
		if((year%400 == 0) || (year%100 !=0  && year %4 ==0))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
