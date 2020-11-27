/**
 * Title: Rakel.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月12日下午3:22:20
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.arr;

import java.util.Arrays;

/**
 * @class_name:Rakel2019年10月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月12日下午3:22:20
 */
public class Rakel {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月12日下午3:22:20
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str[] = new String[]{"ab","cd","ef","yz"};
		Arrays.sort(str);
		int index = Arrays.binarySearch(str, 0,2,"cd");
		System.out.println("数组是："+Arrays.toString(str));
		System.out.println("cd的索引位置是："+index);
	}

}
