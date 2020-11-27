/**
 * Title: FIbonacci.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月31日下午3:36:08
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:FIbonacci2019年10月31日
 * @comments:递归不是罪最快的方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月31日下午3:36:08
 */
public class FIbonacci {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月31日下午3:36:08
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(FIbonacci2(100));
	}
	/**
	 * @Title: Fibonacci
	 *@Description: TODO
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月31日下午3:37:24
	 */
	static long  Fibonacci(int n){
		if(n<=0){
			return 0;
		}
		if(n == 1)
			return 1;
		return Fibonacci(n-1)+Fibonacci(n-2);
	}
	/**
	 * @Title: FIbonacci2
	 *@Description: TODO
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月31日下午3:38:45
	 */
	static long FIbonacci2(int n){
		int result[] = {0,1};
		if(n<2) return result[n];
		long fibNMinusOne = 1;
		long fibNMinusTwo = 0;
		long fibN =0;
		for(int i=2;i<=n;i++){
			fibN= fibNMinusOne + fibNMinusTwo;
			fibNMinusTwo = fibNMinusOne;
			fibNMinusOne = fibN;
		}
		
		return fibN;
	}
}
