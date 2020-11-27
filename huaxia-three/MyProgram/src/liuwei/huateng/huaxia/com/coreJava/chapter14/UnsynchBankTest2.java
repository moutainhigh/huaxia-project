/**
 * Title: UnsynchBankTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月17日下午3:49:43
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter14;

/**
 * @class_name:UnsynchBankTest2020年1月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月17日下午3:49:43
 */
public class UnsynchBankTest2 {
	public static final int NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;
	public static final double MAX_AMOUNT = 1000;
	public static final int DELAY = 10;
	/**
	 * 
	 */
	public UnsynchBankTest2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月17日下午3:49:43
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Bank2 bank = new Bank2(NACCOUNTS,INITIAL_BALANCE);
		for(int i=0;i<NACCOUNTS;i++)
		{
			final int fromAccount = i;
			Runnable r = new Runnable(){
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(true){
						int toAccount = (int)(bank.size()*Math.random());
						double amount = MAX_AMOUNT * Math.random();
						bank.transfer(fromAccount, toAccount, amount);
					}
				}
				
			};
			Thread t = new Thread(r);
			t.start();
		}
	}

}
