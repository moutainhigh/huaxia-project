/**
 * Title: InsertionSort.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������11:25:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:InsertionSort2020��8��10��
 * @comments:�������������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������11:25:29
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
	 *@Date: 2020��8��10������11:25:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] shuzu = new int[SIZE];
		int i;
		for(i =0;i<SIZE;i++)
		{
			shuzu[i] = (int)(100+Math.random()*(100+1))	;
		}
		System.out.print("����ǰ������Ϊ��\n");
		for(i =0;i<SIZE;i++){
			System.out.print(shuzu[i]+" ");
		}
		System.out.println();
		insertionSort(shuzu);
		System.out.print("����������Ϊ��\n");
		for(i =0;i<SIZE;i++){
			System.out.print(shuzu[i]+" ");
		}
		System.out.println();
	}
	/**
	 * @Title: insertionSort
	 *@Description: TODO ��������
	 *@param a
	 *@author: LiuWei
	 *@Date: 2020��8��10������12:13:04
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
			System.out.println("��"+i+"��������");
			for(h = 0;h<a.length;h++)
			{
				System.out.print(" "+a[h]);
			}
			System.out.println();
		}
	}
}
