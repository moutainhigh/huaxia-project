/**
 * Title: largess.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��17������3:59:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algothm;

/**
 * @class_name:largess2020��8��17��
 * @comments:64���̵���������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��17������3:59:12
 */
public class largess {

	/**
	 * Constructor
	 */
	public largess() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��17������3:59:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double t = 1,sum = 1;
		int i ;
		for(i = 2 ;i<=64;i++)
		{
			t = t* 2;
			sum = sum +t;
		}
		System.out.printf("��������Ϊ��%.2f\n",sum);
		System.out.printf("��%.2f��\n",sum/25380/1000);
	}

}
