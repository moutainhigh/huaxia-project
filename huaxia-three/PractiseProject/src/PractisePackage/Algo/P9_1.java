/**
 * Title: P9_1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月17日下午2:03:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:P9_12020年8月17日
 * @comments:完全数是一些特殊自然整数，完全数等于其因子的和
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月17日下午2:03:15
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
	 *@Date: 2020年8月17日下午2:03:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long fanwei;
		fanwei = 100000;
		System.out.printf("查找%d之内的完全数\n",fanwei);
		Perfectnum(fanwei);
	}
	/**
	 * @Title: Perfectnum
	 *@Description: TODO 完数程序
	 *@param fanwei
	 *@author: LiuWei
	 *@Date: 2020年8月17日下午2:03:47
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
				System.out.printf("%4d是一个完全数，因子是", num);
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
