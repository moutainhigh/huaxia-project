/**
 * Title: P10_16.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��13������10:21:57
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P10_162020��8��13��
 * @comments: ��ʤ�������� ÿ��ֻҪȡ5�ı������ɻ�ʤ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��13������10:21:57
 */
public class P10_16 {
	static int computer,user,last;
	/**
	 * @Title: jiangjun
	 *@Description: TODO ��ʤ��������
	 *@author: LiuWei
	 *@Date: 2020��8��13������10:33:36
	 */
	static void jiangjun()
	{
		while(true)
		{
			System.out.printf("----------Ŀǰ���л��%d��----------\n",last);
			System.out.printf("�û�ȡ���������");
			Scanner input = new Scanner(System.in);
			user = input.nextInt();
			if(user<1 || user>4 || user>last)
			{
				System.out.printf("��Υ���ˣ���ȡ�Ļ���������⣡\n\n");
				continue;
			}
			last = last - user;
			if(last == 0)
			{
				System.out.printf("\n�û�ȡ�����һ�������˼����Ӯ�ˣ�\n");
				break;
			}
			else
			{
				computer = 5- user;
				last = last - computer;
				System.out.printf("�����ȡ���������%d", computer);
				if(last == 0)
				{
					System.out.printf("\n�����ȡ�����һ���������û�Ӯ�ˣ�\n");
					break;
				}
			}
		}
	}
	/**
	 * Constructor
	 */
	public P10_16() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��13������10:21:57
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num;
		System.out.printf("��ʤ����������⣡\n");
		System.out.printf("���������������Ϊ��\n");
		Scanner sc =new Scanner(System.in);
		num = sc.nextInt();
		System.out.printf("��������Ϊ%d��", num);
		last = num;
		jiangjun();
	}

}
