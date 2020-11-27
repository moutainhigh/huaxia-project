/**
 * Title: Example.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月12日下午3:11:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.arr;

import java.util.Arrays;

/**
 * @class_name:Example2019年10月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月12日下午3:11:37
 */
public class Example {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月12日下午3:11:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ia[]= new int[]{1,8,9,4,5};
		Arrays.sort(ia);
		for(int i=0;i<ia.length;i++){
			System.out.println(ia[i]);
		}
		System.out.println("4的索引位置是："+Arrays.binarySearch(ia,4));
	}

}
