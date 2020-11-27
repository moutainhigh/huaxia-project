/**
 * Title: TestArrayCopy.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月26日上午10:55:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

import java.util.Arrays;

/**
 * @class_name:TestArrayCopy2019年12月26日
 * @comments: 数组的copy,使用Arrays.copy方法。带长度的函数，通常用于数组的扩充
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月26日上午10:55:51
 */
public class TestArrayCopy {

	/**
	 * 
	 */
	public TestArrayCopy() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月26日上午10:55:51
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] smallPrimes={2,3,5,7,11,13};
		for(int i=0;i<smallPrimes.length;i++)
			System.out.print(" "+smallPrimes[i]);
		System.out.println();
		int[] luckyNumbers = smallPrimes;
		luckyNumbers[5] = 12;
		for(int i=0;i<smallPrimes.length;i++)
			System.out.print(" "+smallPrimes[i]);
		System.out.println();
		for(int i=0;i<luckyNumbers.length;i++)
			System.out.print(" "+luckyNumbers[i]);
		System.out.println();
		int[]  copiedLuckyNumbers = Arrays.copyOf(luckyNumbers, 2*luckyNumbers.length);
		for(int i=0;i<copiedLuckyNumbers.length;i++)
			System.out.print(" "+copiedLuckyNumbers[i]);
		System.out.println();
	}
}
