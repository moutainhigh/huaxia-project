/**
 * Title: Ugly.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月18日上午10:29:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Ugly2019年11月18日
 * @comments: 判断是否是丑数，只包含因子2、3、5的数称做丑数
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月18日上午10:29:09
 */
public class Ugly {

	/**
	 * 
	 */
	public Ugly() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月18日上午10:29:10
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(IsUUgly(6));
		System.out.println(IsUUgly(8));
		System.out.println(IsUUgly(14));
		System.out.println(GetUglyNumber(7));
	}
	/**
	 * @Title: IsUgly
	 *@Description: TODO
	 *@param number
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月18日上午10:30:44
	 */
	static boolean IsUUgly(int number){
		while(number%2==0)
			number/=2;
		while(number%3 == 0)
			number/=3;
		while(number%5 == 0)
			number/=5;
		return (number == 1) ?true:false;
	}
	/**
	 * @Title: GetUglyNumber
	 *@Description: TODO
	 *@param index
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月18日上午10:41:39
	 */
	static int GetUglyNumber(int index)
	{
		if(index <= 0)
			return 0;
		int number = 0;
		int uglyFound = 0;
		while(uglyFound < index)
		{
			++number;
			if(IsUUgly(number))
			{
				++uglyFound;
			}
		}
		return number;
	}
}
