/**
 * Title: Guess.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��7������10:14:20
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:Guess2020��8��7��
 * @comments:�����ֵ���Ϸ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��7������10:14:20
 */
public class Guess {

	/**
	 * Constructor
	 */
	public Guess() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��7������10:14:20
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int oldprice,price =0,i=0;
		System.out.println("������������Ʒ����ʵ�۸�");
		Scanner sc = new Scanner(System.in);
		oldprice = sc.nextInt();
		System.out.println("�������Բµļ۸�");
		while(oldprice != price)
		{
			i++;
			System.out.println("�����ߣ�");
			price = sc.nextInt();
			System.out.println("�����ˣ�");
			if(price > oldprice)
			{
				System.out.println("����");
			}
			else if(price < oldprice)
			{
				System.out.println("����");
			}else{
				System.out.println("��ϲ�㣬����ˣ�����Ʒ�������ˣ�\n\n��һ���Բ���"+i+"��");
			}
		}
	}

}
