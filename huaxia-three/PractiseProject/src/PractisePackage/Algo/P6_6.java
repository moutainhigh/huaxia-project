/**
 * Title: P6_6.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日下午2:26:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Random;

/**
 * @class_name:P6_62020年8月10日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日下午2:26:09
 */
public class P6_6 {

	/**
	 * Constructor
	 */
	public P6_6() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午2:26:09
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i,j;
		Random r = new Random(10);
		for(j = 0;j<10;j++)
		{
			for( i = 0;i< 10;i++)
			{
				System.out.printf("%11d", r.nextInt());
			}
			System.out.println();
		}
	}

}
