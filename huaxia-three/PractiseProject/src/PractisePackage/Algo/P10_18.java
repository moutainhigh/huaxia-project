/**
 * Title: P10_18.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��13������10:41:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P10_182020��8��13��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��13������10:41:27
 */
public class P10_18 {
	/**
	 * @Title: threeball
	 *@Description: TODO threeball ��ɫ������
	 *@param red
	 *@param yellow
	 *@param green
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020��8��13������10:47:45
	 */
	public static void threeball(int red,int yellow,int green,int n)
	{
		int i,j,k;
		System.out.printf("�ܹ������¼��ֿ��ܣ�\n");
		System.out.printf("\t����\t����\t����\n");
		for(i = 0;i<=3;i++){
			for(j=0;j<=3;j++)
			{
				for(k=0;k<=6;k++)
				{
					if(i+j+k == n)
					{
						System.out.printf("\t%d\t%d\t%d\n",i,j,k);
					}
				}
			}
		}
	}
	/**
	 * Constructor
	 */
	public P10_18() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��13������10:41:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int red,yellow,green;
		int n;
		System.out.printf("��ɫ��������⣡\n");
		System.out.printf("����������������Ϊ��");
		Scanner input = new Scanner(System.in);
		red = input.nextInt();
		System.out.printf("������������������");
		yellow = input.nextInt();
		System.out.printf("�������������������");
		green = input.nextInt();
		System.out.printf("��������ȡ���������Ϊ��");
		n = input.nextInt();
		threeball(red,yellow,green,n);
	}

}
