/**
 * Title: P8_3.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��17������2:24:56
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:P8_32020��8��17��
 * @comments:  ����ˮ�ɻ���,���Ǹ������ֵ�n�η����������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��17������2:24:56
 */
public class P8_3 {

	/**
	 * Constructor
	 */
	public P8_3() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��17������2:24:56
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		n = 3;
		System.out.printf("�о�%dλ��ˮ�ɻ�����\n", n);
		NarcissusNum(n);
		n = 4;
		System.out.printf("�о�%dλ��ˮ�ɻ�����\n", n);
		NarcissusNum(n);
	}
	/**
	 * @Title: NarcissusNum
	 *@Description: TODO ����ˮ�ɻ���
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020��8��17������2:28:38
	 */
	public static void NarcissusNum(int n)
	{
		long i,start,end,temp,num,sum;
		int j;
		start = (long)Math.pow(10, n-1);
		end = (long) Math.pow(10, n)-1;
		for(i=start;i<=end;i++)
		{
			temp = 0;
			num = i;
			sum = 0;
			for(j = 0;j<n;j++)
			{
				temp = num%10;
				sum = sum+(long)Math.pow(temp,n);
				num = (num - temp)/10;
			}
			if(sum == i)
			{
				System.out.printf("%d\n", i);
			}
		}
	}
}
