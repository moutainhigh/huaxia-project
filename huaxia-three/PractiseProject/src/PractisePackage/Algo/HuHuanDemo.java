/**
 * Title: HuHuanDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��13������3:07:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @class_name:HuHuanDemo2020��8��13��
 * @comments:����Ԫ�ػ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��13������3:07:49
 */
public class HuHuanDemo {

	/**
	 * Constructor
	 */
	public HuHuanDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��13������3:07:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print();
	}
	/**
	 * @Title: write
	 *@Description: TODO
	 *@return ��������
	 *@author: LiuWei
	 *@Date: 2020��8��13������3:10:17
	 */
	public static int[] write(){
		BufferedReader[] buf = new BufferedReader[10];//���뻺������
		int n;//���ر���
		int array[] = new int[10];
		for(int i=0;i<10;i++)
		{
			buf[i] = new BufferedReader(new InputStreamReader(System.in));
			do{
				n = 1;
				System.out.print("�������"+(i+1)+"��������");
				try {
					array[i] = Integer.parseInt(buf[i].readLine());
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					System.out.println("���������������������");
					n=0;
					e.printStackTrace();
				}
			}while(n == 0);
		}
		return array;
	}
	/**
	 * @Title: print
	 *@Description: TODO�������
	 *@author: LiuWei
	 *@Date: 2020��8��13������3:29:34
	 */
	public static void print(){
		int[] ary = write();
		int s;
		System.out.println("\n������������ǣ�");
		for(int i=0;i<10;i++)
		{
			System.out.print(ary[i] +" ");
		}
		for(int i=0;i<5;i++)
		{
			s = ary[i];
			ary[i] = ary[9-i];
			ary[9-i] = s;
		}
		System.out.println("\n�Ի���������ǣ�");
		for(int i=0;i<10;i++)
		{
			System.out.print(ary[i]+" ");
		}
		System.out.println();
	}
}
