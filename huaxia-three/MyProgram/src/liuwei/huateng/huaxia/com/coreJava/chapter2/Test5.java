/**
 * Title: Test5.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��26������10:06:18
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

import java.util.Scanner;

/**
 * @class_name:Test52019��12��26��
 * @comments:break read_data; �൱��goto��䣬
 * ��������������ǩ�������ĩβ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��26������10:06:18
 */
public class Test5 {

	/**
	 * 
	 */
	public Test5() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��26������10:06:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = 0;
		read_data:
		{
			System.out.println("������whileѭ��");
			while(n>-10){
				System.out.println("������whileѭ��");
				for(int i=0;i<3;i++){
					System.out.println("forѭ����i��ֵ"+i);
					System.out.print("Enter a number >= 0:");
					n = in.nextInt();
					if(n<0)
						break read_data;
				}
			}
			System.out.println("�������");
		}
		System.out.println("while ѭ��������");
		if(n<0){
			System.out.println("nС��0");
		}
		else
		{
			System.out.println("n����0");
		}
	}

}
