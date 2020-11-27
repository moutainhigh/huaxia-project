/**
 * Title: SelectionSort.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日上午10:12:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:SelectionSort2020年8月10日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日上午10:12:58
 */
public class SelectionSort {
	
	/**
	 * Constructor
	 */
	public SelectionSort() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午10:12:58
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {9,8,7,6,5,4,3,2,1};
		printArray(arr);
		selectionSort(arr);
		printArray(arr);
	}
	/**
	 * @Title: selectionSort
	 *@Description: TODO 选择排序,时间复杂度o(n2）
	 *@param array
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午10:13:48
	 */
	private static void selectionSort(int[] array){
		for(int i=0; i< array.length;i++){
			int lowestNumberIndex = i;
			for(int j = i+1;j<array.length;j++){
				if(array[j]<array[lowestNumberIndex]){
					lowestNumberIndex = j;
				}
			}
			if(lowestNumberIndex != i){
				int temp = array[i] ;
				array[i] = array[lowestNumberIndex];
				array[lowestNumberIndex] = temp;
			}
		}
	}
	/**
	 * @Title: printArray
	 *@Description: TODO 输出数组
	 *@param array
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午10:19:14
	 */
	public static void printArray(int[] array){
		for(int i=0;i<array.length;i++){
			System.out.printf(array[i]+" ");
		}
		System.out.println();
	}
}
