/**
 * Title: Power.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月12日下午3:19:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Power2019年11月12日
 * @comments: 幂运算
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月12日下午3:19:49
 */
public class Power {

	/**
	 * 
	 */
	public Power() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月12日下午3:19:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Power(2,3));
		System.out.println(PowerWithUnsignedExponent(2,4));
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
	}
	/**
	 * @Title: Power
	 *@Description: TODO
	 *@param base
	 *@param exponent
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月12日下午3:20:35
	 */
	static double Power(double base,int exponent){
		double result = 1.0;
		for(int i=1;i<=exponent;i++)
			result *= base;
		return result;
	}
	/**
	 * @Title: PowerWithUnsignedExponent
	 *@Description: TODO
	 *@param base
	 *@param exponent
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月12日下午3:25:24
	 */
	static double PowerWithUnsignedExponent(double base,int exponent)
	{
		if(exponent == 0)
			return 1;
		if(exponent == 1)
			return base;
		double result = PowerWithUnsignedExponent(base,exponent-1);
		result *= result;
		return result;
	}
}
