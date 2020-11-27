/**
 * Title: P5_2.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������1:55:35
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P5_22020��8��10��
 * @comments:���ֲ��ҷ�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������1:55:35
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
	 *@Date: 2020��8��10������1:55:35
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] shuzu = new int[N];
		int x,n,i;
		for(i=0;i<N;i++)
		{
			shuzu[i] = (int)(100+Math.random()*(100+1));
		}
		System.out.print("���ֲ����㷨��ʾ��	\n");
		System.out.print("����ǰ�������У�\n");
		for(i =0;i<N;i++){
			System.out.print(" "+shuzu[i]);
		}
		System.out.print("\n\n");
		selectionSort(shuzu);
		System.out.print("������������У�\n");
		for(i =0;i<N;i++){
			System.out.print(" "+shuzu[i]);
		}
		System.out.print("\n\n");
		System.out.print("����Ҫ���ҵ�����");
		Scanner input = new Scanner(System.in);
		x = input.nextInt();
		n = searchFun(shuzu,N,x);
		if(n<0){
			System.out.println("û�ҵ����ݣ�"+x);
		}
		else{
			System.out.println("����"+x+" λ������ĵ�"+(n+1)+" ��Ԫ�ش���");
		}
	}
	/**
	 * @Title: searchFun
	 *@Description: TODO ���ֲ��ҷ�
	 *@param a
	 *@param n
	 *@param x
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��10������1:57:17
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
}
