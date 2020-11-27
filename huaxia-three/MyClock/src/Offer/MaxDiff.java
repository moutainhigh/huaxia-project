/**
 * Title: MaxDiff.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月8日下午3:04:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:MaxDiff2019年11月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月8日下午3:04:05
 */
public class MaxDiff {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月8日下午3:04:05
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr= {9,11,8,5,7,12,16,14};
		System.out.println(MaxDiff(arr,arr.length));
	}
	/**
	 * @Title: MaxDiff
	 *@Description: TODO
	 *@param numbers
	 *@param length
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月8日下午3:04:43
	 */
	static int MaxDiff(int[] numbers,int length){
		if(numbers == null && length <2)
			return 0;
		int min = numbers[0];
		int maxDiff = numbers[1]-min;
		for(int i=2;i<length;i++){
			if(numbers[i-1]<min)
				min = numbers[i-1];
			int currentDiff = numbers[i]-min;
			if(currentDiff >maxDiff)
				maxDiff = currentDiff;
		}
		return maxDiff;
	}
}
