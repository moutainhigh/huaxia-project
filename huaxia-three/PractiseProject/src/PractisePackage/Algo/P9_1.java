/**
 * Title: P9_1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��17������2:03:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:P9_12020��8��17��
 * @comments:��ȫ����һЩ������Ȼ��������ȫ�����������ӵĺ�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��17������2:03:15
 */
public class P9_1 {

	/**
	 * Constructor
	 */
	public P9_1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��17������2:03:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long fanwei;
		fanwei = 100000;
		System.out.printf("����%d֮�ڵ���ȫ��\n",fanwei);
		Perfectnum(fanwei);
	}
	/**
	 * @Title: Perfectnum
	 *@Description: TODO ��������
	 *@param fanwei
	 *@author: LiuWei
	 *@Date: 2020��8��17������2:03:47
	 */
	public static void Perfectnum(long fanwei){
		long[] p  = new long[300];
		long i,j,sum,num;
		int k,count;
		for(i = 1;i<fanwei;i++)
		{
			count= 0;
			num = i;
			sum = num;
			for(j = 1;j<num;j++)
			{
				if(num % j == 0)
				{
					p[count++] = j;
					sum = sum-j;
				}
			}
			if(sum == 0)
			{
				System.out.printf("%4d��һ����ȫ����������", num);
				System.out.printf("%d = %d", num,p[0]);
				for(k = 1;k<count;k++)
				{
					System.out.printf("+%d",p[k]);
				}
				System.out.printf("\n");
			}
		}
	}
}
