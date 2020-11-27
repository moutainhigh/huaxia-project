/**
 * Title: P9_7.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月17日下午3:02:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:P9_72020年8月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月17日下午3:02:04
 */
public class P9_7 {

	/**
	 * Constructor
	 */
	public P9_7() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月17日下午3:02:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i,n,count;
		n = 10000;
		count = 0;
		System.out.printf("列举1-1000之间所有的素数：\n");
		for(i =1;i<1000;i++)
		{
			if(isPrime(i) == 1)
			{
				System.out.printf("%7d",i);
				count++;
				if(count%10 == 0)
				{
					System.out.printf("\n");
				}
			}
		}
		System.out.printf("\n");
	}
	/**
	 * @Title: isPrime
	 *@Description: TODO 判断是否为素数
	 *@param a
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月17日下午3:03:00
	 */
	public static int isPrime(int a)
	{
		int i;
		for(i = 2;i<a ;i++)
		{
			if( a % i == 0)
				return 0;
		}
		return 1;
	}
}
