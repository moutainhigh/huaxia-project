/**
 * Title: P6_1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������2:15:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:P6_12020��8��10��
 * @comments:  ����ļ��㷽�����ܱ�4���������ǲ��ܱ�100�����������ܱ�400 ����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������2:15:37
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
	 *@Date: 2020��8��10������2:15:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int year ;
		int count = 0;
		System.out.println("2000�굽3000��֮�����е��������£�\n");
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
	 *@Description: ����ļ��㷽�����ܱ�4���������ǲ��ܱ�100�����������ܱ�400 ����
	 *@param year
	 *@author: LiuWei
	 *@Date: 2020��8��10������2:16:10
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
