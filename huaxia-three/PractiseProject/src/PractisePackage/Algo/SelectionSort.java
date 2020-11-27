/**
 * Title: SelectionSort.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������10:12:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:SelectionSort2020��8��10��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������10:12:58
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
	 *@Date: 2020��8��10������10:12:58
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
	 *@Description: TODO ѡ������,ʱ�临�Ӷ�o(n2��
	 *@param array
	 *@author: LiuWei
	 *@Date: 2020��8��10������10:13:48
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
	 *@Description: TODO �������
	 *@param array
	 *@author: LiuWei
	 *@Date: 2020��8��10������10:19:14
	 */
	public static void printArray(int[] array){
		for(int i=0;i<array.length;i++){
			System.out.printf(array[i]+" ");
		}
		System.out.println();
	}
}
