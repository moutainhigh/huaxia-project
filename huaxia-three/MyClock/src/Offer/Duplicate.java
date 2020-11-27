/**
 * Title: Duplicate.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月12日下午2:00:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Duplicate2019年11月12日
 * @comments:找出重复日志
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月12日下午2:00:47
 */
public class Duplicate {
	private static int firstDuplicate = -1;
	/**
	 * 
	 */
	public Duplicate() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月12日下午2:00:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {2,3,1,0,2,5,3};
		printArr(arr);
		System.out.println(duplicate(arr,arr.length)+"的重复数字是："+firstDuplicate);
		printArr(arr);
	}
	/**
	 * @Title: duplicate
	 *@Description: TODO
	 *@param numbers
	 *@param length
	 *@param duplication
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月12日下午2:02:16
	 */
	static boolean duplicate(int numbers[],int length){
		if(numbers == null || length <=0)
			return false;
		for(int i=0;i<length;i++)
		{
			if(numbers[i] < 0 || numbers[i] >length-1)
				return false;
		}
		for(int i=0;i<length;++i)
		{
			while(numbers[i] != i){
				if(numbers[i] == numbers[numbers[i]])
				{
					firstDuplicate = numbers[i];
					return true;
				}
				int temp = numbers[i] ;
				numbers[i] = numbers[temp];
				numbers[temp] = temp;
			}
		}
		return true;
	}
	/**
	 * @Title: printArr
	 *@Description: TODO
	 *@param arr
	 *@author: LiuWei
	 *@Date: 2019年11月12日下午2:32:03
	 */
	public static void printArr(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
