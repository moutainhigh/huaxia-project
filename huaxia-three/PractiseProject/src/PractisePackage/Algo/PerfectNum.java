/**
 * Title: PerfectNum.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��7������5:09:10
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:PerfectNum2020��8��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��7������5:09:10
 */
public class PerfectNum {

	/**
	 * Constructor
	 */
	public PerfectNum() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��7������5:09:10
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long p[] = new long[300];//����ֽ�����
		long i,num,count,s,c=0;
		for(num = 2;num <=10000;num++){
			count =0;
			s = num;
			for(i=1;i<num;i++)
			{
				if(num%i == 0)
				{
					p[(int) count++] = i;
					s-=i;
				}
			}
			if(s ==0)
			{
				System.out.printf("%4d��һ��������������",num);
				System.out.printf("%d=%d", num,p[0]);
				for(i=1;i<count;i++)
					System.out.printf("+%d", p[(int) i]);
				System.out.println();
				c++;
			}
		}
		System.out.printf("\n���ҵ�%d��������\n", c);
	}

}
