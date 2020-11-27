/**
 * Title: BasicSort.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��20������10:11:40
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.javaAlogo;

/**
 * @class_name:BasicSort2020��1��20��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��20������10:11:40
 */
public class BasicSort {
	/**
	 * @Title: selextionSort
	 *@Description: TODO 
	 * ѡ������
	 *@param number
	 *@author: LiuWei
	 *@Date: 2020��1��20������10:19:16
	 */
	public static void selextionSort(int[] number){
		for(int i=0;i<number.length - 1;i++){
			int m = i;
			for(int j= i+1;j<number.length;j++)
				if(number[j] < number[m] ) m= j;
			if(i != m) swap(number,i,m);
		}	
	}
	/**
	 * @Title: swap
	 *@Description: TODO
	 *�������������ֵ
	 *@param number
	 *@param i
	 *@param j
	 *@author: LiuWei
	 *@Date: 2020��1��20������10:18:16
	 */
	private static void swap(int[] number,int i,int j)
	{
		int t;
		t = number[i];
		number[i] = number[j];
		number[j] = t;
	}
	/**
	 * @Title: injectionSort
	 *@Description: TODO
	 *@param number
	 *@author: LiuWei
	 *@Date: 2020��1��20������10:22:01
	 */
	public static void injectionSort(int[] number)
	{
		for(int j = 1;j<number.length;j++)
		{
			int tmp = number[j];
			int i = j-1;
			while(tmp < number[i]){
				number[i+1] = number[i] ;
				i --;
				if(i == -1)
					break;
			}
			number[i+1] = tmp;
		}
	}
	public static void bubbleSort(int[] number){
		boolean flag = true;
		for(int i=0;i<number.length-1 && flag ;i++)
		{
			flag = false;
			for(int j=0;j<number.length-i-1;j++)
			{
				if(number[j+1]<number[j])
				{
					swap(number,j+1,j);
					flag = true;
				}
			}
		}
	}
	/**
	 * 
	 */
	public BasicSort() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��20������10:11:41
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {10,9,1,100,20,200,39,45,23,18,2,2,15};
		//����ѡ������
		System.out.println("ѡ������ǰa:");
		for(int x:a)
			System.out.print(x+" ");
		System.out.println();
		int[] b = new int[a.length];
		b = a;
		System.out.println("ѡ������ǰb:");
		for(int x:b)
			System.out.print(x+" ");
		System.out.println();

		selextionSort(b);
		System.out.println("ѡ�������b:");
		for(int x:b)
			System.out.print(x+" ");
		System.out.println();
		System.out.println("ѡ�������a:");
		for(int x:a)
			System.out.print(x+" ");
		System.out.println();
	}

}
