/**
 * Title: PerfectNum.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月7日下午5:09:10
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:PerfectNum2020年8月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月7日下午5:09:10
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
	 *@Date: 2020年8月7日下午5:09:10
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long p[] = new long[300];//保存分解因子
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
				System.out.printf("%4d是一个完数，因子是",num);
				System.out.printf("%d=%d", num,p[0]);
				for(i=1;i<count;i++)
					System.out.printf("+%d", p[(int) i]);
				System.out.println();
				c++;
			}
		}
		System.out.printf("\n共找到%d个完数。\n", c);
	}

}
