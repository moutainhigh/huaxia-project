/**
 * Title: ticker.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月7日下午2:45:34
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:ticker2020年8月7日
 * @comments:假设有一种彩票，每个数字是7个数字组成1-29，7个数字不能相同，写出所有的号码组合
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月7日下午2:45:34
 */
public class ticker2 {
	private static int MAXN = 7;
	private static int NUM = 29;
	private static int num[] = new int[NUM];
	private static int lottery[]= new int[MAXN];
	/**
	 * Constructor
	 */
	public ticker2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月7日下午2:45:34
	 */
	public static void main(String[] args) {
		int i,j;
		for(i=0;i<NUM;i++){
			num[i] = i+1;
		}
		for(i=0;i<MAXN;i++)
			lottery[i] = 0;
		combine(NUM,MAXN);
		
	}
	/**
	 * @Title: combine
	 *@Description: TODO
	 *@param n递归解决 彩票问题
	 *@param m
	 *@author: LiuWei
	 *@Date: 2020年8月7日下午3:21:55
	 */
	private static void combine(int n,int m){
		int i,j;
		for(i = n;i>=m ;i--){
			 lottery[m-1] = num[i-1];//保存
			 if(m>1)
				 combine(i-1,m-1);
			 else{
				 for(j=MAXN-1;j>=0;j--)
					 System.out.printf("%3d", lottery[j]);
				 System.out.println();
			 }
		}
	}
}
