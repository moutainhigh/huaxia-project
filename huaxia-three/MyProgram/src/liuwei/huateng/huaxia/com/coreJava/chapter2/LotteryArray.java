/**
 * Title: LotteryArray.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��26������2:50:56
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

/**
 * @class_name:LotteryArray2019��12��26��
 * @comments: ���Ȼ�仯�Ķ�ά����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��26������2:50:56
 */
public class LotteryArray {

	/**
	 * 
	 */
	public LotteryArray() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��26������2:50:56
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int NMAX = 10;
		//allocate triangular array
		int[][] odds = new int[NMAX+1][];
		for(int n=0;n<=NMAX;n++)
			odds[n] = new int[n+1];
		//fill triangular array
		for(int n=0;n<odds.length;n++)
		for(int k=0;k<odds[n].length;k++)
		{
			int lotteryOdds = 1;
			for(int i=1;i<= k;i++)
				lotteryOdds = lotteryOdds*(n-i+1)/i;
			odds[n][k] = lotteryOdds;
		}
		 //print triangulat array
		for(int[] row:odds)
		{
			for(int odd:row){
				System.out.printf("%4d", odd);
			}
			System.out.println();
		}
	}

}
