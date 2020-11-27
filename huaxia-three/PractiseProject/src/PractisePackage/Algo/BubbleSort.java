/**
 * Title: BubbleSort.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月7日下午4:10:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:BubbleSort2020年8月7日
 * @comments:冒泡排序的两种写法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月7日下午4:10:36
 */
public class BubbleSort {
	
	/**
	 * Constructor
	 */
	public BubbleSort() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月7日下午4:10:36
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = {9,8,7,6,5,4,3,2,1};
		for(int i=0;i<a.length;i++){
			System.out.printf("%2d", a[i]);
		}
		System.out.println();
		BubbleSort2(a,a.length);
		for(int i=0;i<a.length;i++){
			System.out.printf("%2d", a[i]);
		}
		System.out.println();
	}
	/**
	 * @Title: BubbleSort
	 *@Description: TODO冒泡排序1
	 *@param a
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020年8月7日下午4:18:45
	 */
	public static void BubbleSort(int a[],int n){
		int i,j,t;
		for(i =0;i<n-1;i++){
			for(j= n-1;j>i;j--)
			{
				if(a[j-1]>a[j]){
					t = a[j-1];
					a[j-1] = a[j];
					a[j] = t;
				}
			}
			System.out.printf("第%2d遍：",(i+1));
			for(j=0;j<n;j++){
				System.out.printf("%d ",a[j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * @Title: BubbleSort
	 *@Description: TODO冒泡排序1
	 *@param a
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020年8月7日下午4:18:45
	 */
	public static void BubbleSort2(int a[],int n){
		int i,j,t;
		for(i =0;i<n-1;i++){
			for(j=0;j<n-i-1;j++)
			{
				if(a[j]>a[j+1]){
					t = a[j+1];
					a[j+1] = a[j];
					a[j] = t;
				}
			}
			System.out.printf("第%2d遍：",(i+1));
			for(j=0;j<n;j++){
				System.out.printf("%d ",a[j]);
			}
			System.out.println();
		}
	}
}
