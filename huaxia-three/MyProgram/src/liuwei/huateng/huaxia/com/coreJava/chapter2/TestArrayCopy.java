/**
 * Title: TestArrayCopy.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��26������10:55:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

import java.util.Arrays;

/**
 * @class_name:TestArrayCopy2019��12��26��
 * @comments: �����copy,ʹ��Arrays.copy�����������ȵĺ�����ͨ���������������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��26������10:55:51
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
	 *@Date: 2019��12��26������10:55:51
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
