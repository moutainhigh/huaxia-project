/**
 * Title: SortSequence.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������4:49:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Arrays;
import java.util.Random;

/**
 * @class_name:SortSequence2020��9��18��
 * @comments:ʹ�����򹤾�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������4:49:32
 */
public class SortSequence {
	
	/**
	 * Constructor
	 */
	public SortSequence() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��18������4:49:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rd  = new Random();
		int[] array = new int[5];
		System.out.println("û��ʹ��sort����ǰ�����飺");
		for(int i=0;i<array.length;i++)
		{
			array[i] = rd.nextInt(20);
			System.out.print(" "+array[i]);
			if((i+1) % 5 == 0)
				System.out.println();
		}
		Arrays.sort(array);
		System.out.println("\nʹ��sort����������飺");
		for(int i=0;i<array.length;i++)
		{
			System.out.print(" "+array[i]);
			if((i+1) % 5 == 0)
				System.out.println();
		}
	}
}
