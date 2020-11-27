/**
 * Title: FindNumbersWithSum.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月31日上午11:00:17
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:FindNumbersWithSum2019年10月31日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月31日上午11:00:17
 */
public class FindNumbersWithSum {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月31日上午11:00:17
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] ={1,2,4,7,11,15};
		int arr2[] = new int[5];
		int arr3[] = new int[5];
		System.out.println(FindNumbersWithSum(arr,arr.length,15,arr2,arr3)+"位置"+arr2[0]+","+arr3[0]);
	}
	/**
	 * @Title: FindNumbersWithSum
	 *@Description: TODO
	 *@param data
	 *@param length
	 *@param sum
	 *@param num1
	 *@param num2
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月31日上午11:01:45
	 */
	static boolean FindNumbersWithSum(int data[],int length,int sum,int[] num1,int[] num2){
		boolean found = false;
		if(length <1||num1 == null ||num2 == null)
		return found;
		int ahead = length -1;
		int behind = 0;
		while(ahead>behind){
			long curSum = data[ahead]+data[behind];
			if(curSum == sum){
				num1[0]= data[behind];
				num2[0]= data[ahead];
				found = true;
				break;
			}
			else if(curSum >sum){
				ahead--; 
			}
			else
				behind ++;
		}
		return found;
	}
}
