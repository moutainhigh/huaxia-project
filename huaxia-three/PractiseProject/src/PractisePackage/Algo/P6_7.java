/**
 * Title: P6_7.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������2:38:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Random;

/**
 * @class_name:P6_72020��8��10��
 * @comments:���������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������2:38:33
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
	 *@Date: 2020��8��10������2:38:33
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
