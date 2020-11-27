/**
 * Title: CompoundInterest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月26日下午2:33:20
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

/**
 * @class_name:CompoundInterest2019年12月26日
 * @comments:测试二维数组
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月26日下午2:33:20
 */
public class CompoundInterest {

	/**
	 * 
	 */
	public CompoundInterest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月26日下午2:33:20
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final double STARTRATE = 10;
		final int NRATES  = 6;
		final int NYEARS = 10;
		//set interest rates to 10...15%
		double[] interestRate = new double[NRATES];
		for(int j = 0 ;j<interestRate.length;j++)
			interestRate[j] = (STARTRATE+j)/100.0;
		double[][] balances = new double[NYEARS][NRATES];
		//set initial balances to 10000
		for(int j=0;j<balances[0].length;j++)
			balances[0][j] = 10000;
		//compute interest for future years
		for(int i=1;i<balances.length;i++)
		{
			for(int j=0;j<balances[i].length;j++)
			{
				//get last years balances from previous row
				double oldBalance = balances[i-1][j];
				//compute interest
				double interest = oldBalance * interestRate[j];
				//compute this years balances
				balances[i][j] = oldBalance + interest;
			}
		}
		//print ont row of interest rates
		for(int j=0;j<interestRate.length;j++)
			System.out.printf("%9.0f%%", 100*interestRate[j]);
		System.out.println();
		//print balance table
		for(double[] row:balances)
		{
			//print table row
			for(double b :row)
				System.out.printf("%10.2f",b);
			System.out.println();
		}
	}

}
