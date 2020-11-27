/**
 * Title: p10_1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月17日上午10:33:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:p10_12020年8月17日
 * @comments:百钱求百鸡算法，公鸡5文钱母鸡3文钱小鸡3只一文钱，共100文钱买100只鸡
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月17日上午10:33:36
 */
public class p10_1 {

	/**
	 * Constructor
	 */
	public p10_1() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: BQBJ
	 *@Description: TODO
	 *@param m
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020年8月17日上午10:37:46
	 */
	static void BQBJ(int m,int n){
		int x,y,z;
		for(x = 0;x<=n;x++)
		{
			for(y = 0;y<=n;y++)
			{
				z =n-x-y;
				if(z>0 && z %3 == 0 && 5*x+y*3 +z/3 ==m)
				{
					System.out.printf("公鸡 :%d只，母鸡:%d只，小鸡%d只\n",x,y,z);
				}
			}
		}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月17日上午10:33:36
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m,n;
		m = 100;
		n = 100;
		System.out.printf("%d钱买%d鸡问题求解结果为:\n", m,n);
		BQBJ(m,n);
	}
}
