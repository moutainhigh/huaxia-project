/**
 * Title: monkeyeat_peach.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��17������3:50:30
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algothm;

import java.util.Scanner;

/**
 * @class_name:monkeyeat_peach2020��8��17��
 * @comments:�ݹ������ӳ������⣬ÿһ�춼��һ���һ������10��֮��1������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��17������3:50:30
 */
public class monkeyeat_peach {

	/**
	 * Constructor
	 */
	public monkeyeat_peach() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��17������3:50:30
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int days;
		long sum;
		System.out.println("����������");
		Scanner input = new Scanner(System.in);
		days = input.nextInt();
		sum = peach(days);
		System.out.println("�����������Ŀ��"+sum);
	}
	/**
	 * @Title: peach
	 *@Description: TODO �ݹ������ӳ������⣬ÿһ�춼��һ���һ������10��֮��1������
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��17������3:51:24
	 */
	public static int peach(int n)
	{
		if(n == 1)
			return 1;
		else 
			return (peach(n-1)+1)*2;
	}
}
