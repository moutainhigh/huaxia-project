/**
 * Title: P5_2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日下午1:55:35
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P5_22020年8月10日
 * @comments:二分查找法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日下午1:55:35
 */
public class P5_2 {
	static final int N = 15;
	/**
	 * Constructor
	 */
	public P5_2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午1:55:35
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] shuzu = new int[N];
		int x,n,i;
		for(i=0;i<N;i++)
		{
			shuzu[i] = (int)(100+Math.random()*(100+1));
		}
		System.out.print("二分查找算法演示！	\n");
		System.out.print("排序前数据序列：\n");
		for(i =0;i<N;i++){
			System.out.print(" "+shuzu[i]);
		}
		System.out.print("\n\n");
		selectionSort(shuzu);
		System.out.print("排序后数据序列：\n");
		for(i =0;i<N;i++){
			System.out.print(" "+shuzu[i]);
		}
		System.out.print("\n\n");
		System.out.print("输入要查找的数：");
		Scanner input = new Scanner(System.in);
		x = input.nextInt();
		n = searchFun(shuzu,N,x);
		if(n<0){
			System.out.println("没找到数据："+x);
		}
		else{
			System.out.println("数据"+x+" 位于数组的第"+(n+1)+" 个元素处。");
		}
	}
	/**
	 * @Title: searchFun
	 *@Description: TODO 二分查找法
	 *@param a
	 *@param n
	 *@param x
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午1:57:17
	 */
	public static int searchFun(int  a[],int n,int x){
		int mid,low,high;
		low = 0;
		high = n-1;
		while(low <= high)
		{
			mid = (low+high)/2;
			if(a[mid] == x)
				return mid;
			else if(a[mid]>x)
				high = mid -1;
			else
				low = mid+1;
		}
		return -1;
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
}
