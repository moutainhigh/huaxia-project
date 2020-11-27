/**
 * Title: Partition.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月18日下午12:50:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

import Offer.util.RandomInRange;

/**
 * @class_name:Partition2019年11月18日
 * @comments:快速排序
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月18日下午12:50:52
 */
public class  Partition {

	/**
	 * 
	 */
	public Partition() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月18日下午12:50:52
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = {1 ,4,3,2,6,5};
		for(int i=0;i<data.length;i++)
			System.out.print(data[i]+" ");
		System.out.println();
		QuickSort(data,data.length,0,data.length-1);
		for(int i=0;i<data.length;i++)
			System.out.print(data[i]+" ");
		System.out.println();
		
	}
	/**
	 * @Title: QuickSort
	 *@Description: TODO
	 *@param data
	 *@param length
	 *@param start
	 *@param end
	 *@author: LiuWei
	 *@Date: 2019年11月21日上午10:12:23
	 */
	public static void QuickSort(int data[],int length,int start,int end)
	{
		if(start == end)
			return;
		int index = -1;
		try {
			index = Partition(data,length,start,end);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(index > start)
			QuickSort(data,length,start,index-1);
		if(index < end)
			QuickSort(data,length,index+1,end);
	}
	/**
	 * @Title: Partition
	 *@Description: TODO 快速排序算法，选择一个数，必数字小的移到数组左边，大的右边
	 *@param data
	 *@param length
	 *@param start
	 *@param end
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月18日下午12:51:29
	 */
	public static int Partition(int[] data,int length,int start,int end) throws Exception{
		if(data == null || length <0|| start <0 || end >= length)
			throw new Exception("不符合条件");
		int index = RandomInRange.RandomInRange(start, end);
		Swap(data,index,end);
		int small = start -1;
		for(index = start;index<end;++index)
		{
			if(data[index]<data[end])
			{
				++small;
				if(small != index)
					Swap(data,index,small);
			}
		}
		return small;
	}
	/**
	 * @Title: Swap
	 *@Description: TODO
	 *@param a
	 *@param b
	 *@author: LiuWei
	 *@Date: 2019年11月21日上午10:02:30
	 */
	public static void Swap(int[] arr,int a,int b){
		int temp ;
		temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
