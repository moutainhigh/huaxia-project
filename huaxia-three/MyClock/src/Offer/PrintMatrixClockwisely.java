/**
 * Title: PrintMatrixClockwisely.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月8日上午11:07:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:PrintMatrixClockwisely2019年11月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月8日上午11:07:52
 */
public class PrintMatrixClockwisely {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月8日上午11:07:52
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World!!");
		System.out.println("Hello World!!");
		System.out.println("Hello World!!");
		System.out.println("Hello World!!");
		System.out.println("Hello World!!");
		System.out.println("Hello World!!");
		System.out.println("Hello World!!");
		System.out.println("Hello World!!");
		System.out.println("Hello World!!");
	}
	/**
	 * @Title: PrintMatrixClockwisely
	 *@Description: TODO
	 *@param numbers
	 *@param columns
	 *@param rows
	 *@author: LiuWei
	 *@Date: 2019年11月8日上午11:08:49
	 */
	static void PrintMatrixClockwisely(int[] numbers,int columns,int rows)
	{
		if(numbers == null || columns<=0|| rows<=0)
			return;
		int start =0;
		while(columns>start*2 && rows>start*2)
		{
//			PrintMatrixClockwisely(numbers,columns,rows,start);
			++start;
		}
	}
}
