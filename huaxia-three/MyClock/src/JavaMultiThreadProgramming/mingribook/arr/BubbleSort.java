/**
 * Title: BubbleSort.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月12日下午3:26:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.arr;

/**
 * @class_name:BubbleSort2019年10月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月12日下午3:26:15
 */
public class BubbleSort {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月12日下午3:26:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array ={63,4,24,1,3,15};
		BubbleSort sorter = new BubbleSort();
		sorter.sort(array);
	}
	/**
	 * @Title: sort
	 *@Description: TODO
	 *@param array
	 *@author: LiuWei
	 *@Date: 2019年10月12日下午3:30:04
	 */
	public void sort(int[] array){
		for(int i=1;i<array.length;i++){
			for(int j=0;j<array.length-1;j++){
				if(array[j]>array[j+1]){
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		showArray(array);
	}
	/**
	 * @Title: showArray
	 *@Description: TODO
	 *@param array
	 *@author: LiuWei
	 *@Date: 2019年10月12日下午3:30:32
	 */
	public void showArray(int[] array){
		for(int i:array){
			System.out.println(">"+i);
		}
		System.out.println();
	}
}
