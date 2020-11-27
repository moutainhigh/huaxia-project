/**
 * Title: InsertionSort.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日上午11:25:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:InsertionSort2020年8月10日
 * @comments:插入排序的例子
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日上午11:25:29
 */
public class InsertionSort {
	static final int SIZE = 10;
	/**
	 * Constructor
	 */
	public InsertionSort() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午11:25:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] shuzu = new int[SIZE];
		int i;
		for(i =0;i<SIZE;i++)
		{
			shuzu[i] = (int)(100+Math.random()*(100+1))	;
		}
		System.out.print("排序前的数组为：\n");
		for(i =0;i<SIZE;i++){
			System.out.print(shuzu[i]+" ");
		}
		System.out.println();
		insertionSort(shuzu);
		System.out.print("排序后的数组为：\n");
		for(i =0;i<SIZE;i++){
			System.out.print(shuzu[i]+" ");
		}
		System.out.println();
	}
	/**
	 * @Title: insertionSort
	 *@Description: TODO 插入排序
	 *@param a
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午12:13:04
	 */
	private static  void insertionSort(int[] a){
		int i,j,t,h;
		for(i =1;i<a.length;i++	)
		{
			t = a[i];
			j = i-1;
			while(j >=0 && t<a[j])
			{
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = t;
			System.out.println("第"+i+"步排序结果");
			for(h = 0;h<a.length;h++)
			{
				System.out.print(" "+a[h]);
			}
			System.out.println();
		}
	}
}
