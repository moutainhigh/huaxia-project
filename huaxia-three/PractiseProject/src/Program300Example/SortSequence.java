/**
 * Title: SortSequence.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日下午4:49:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Arrays;
import java.util.Random;

/**
 * @class_name:SortSequence2020年9月18日
 * @comments:使用排序工具
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日下午4:49:32
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
	 *@Date: 2020年9月18日下午4:49:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rd  = new Random();
		int[] array = new int[5];
		System.out.println("没有使用sort方法前的数组：");
		for(int i=0;i<array.length;i++)
		{
			array[i] = rd.nextInt(20);
			System.out.print(" "+array[i]);
			if((i+1) % 5 == 0)
				System.out.println();
		}
		Arrays.sort(array);
		System.out.println("\n使用sort方法后的数组：");
		for(int i=0;i<array.length;i++)
		{
			System.out.print(" "+array[i]);
			if((i+1) % 5 == 0)
				System.out.println();
		}
	}
}
