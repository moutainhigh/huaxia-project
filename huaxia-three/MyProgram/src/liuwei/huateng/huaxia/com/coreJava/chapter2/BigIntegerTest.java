/**
 * Title: Test6.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��26������10:36:41
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @class_name:Test62019��12��26��
 * @comments:BigInteger ������������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��26������10:36:41
 */
public class BigIntegerTest {

	/**
	 * 
	 */
	public BigIntegerTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��26������10:36:41
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("How many numberrs do you need to draw?");
		int k= in.nextInt();
		System.out.println("What is the highest number you can draw?");
		int n = in.nextInt();
		BigInteger lotteryOdds = BigInteger.valueOf(1);
		for(int i=1;i<=k;i++)
			lotteryOdds= lotteryOdds.multiply(BigInteger.valueOf(n-i+1)).divide(BigInteger.valueOf(i));
		System.out.println("Your odds are 1 in "+lotteryOdds+". Good luck��");
	}

}
