/**
 * Title: P6_7.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日下午2:38:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Random;

/**
 * @class_name:P6_72020年8月10日
 * @comments:生成随机数
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日下午2:38:33
 */
public class P6_7 {
	
	/**
	 * Constructor
	 */
	public P6_7() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午2:38:33
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i,j;
		Random r =new Random();
		for(j =0;j<10;j++)
		{
			for(i =0;i< 10;i++)
			{
				System.out.printf("%13d ",r.nextInt(100));
			}
			System.out.print("\n");
		}
	}

}
