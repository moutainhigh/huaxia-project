/**
 * Title: BigIntegerDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午12:38:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.arr;

import java.math.BigInteger;

/**
 * @class_name:BigIntegerDemo2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午12:38:21
 */
public class BigIntegerDemo {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午12:38:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger bigInstance = new BigInteger("4");
		System.out.println("加法动作"+bigInstance.add(new BigInteger("2")));
		System.out.println("减法动作"+bigInstance.subtract(new BigInteger("2")));
		System.out.println("乘法动作"+bigInstance.multiply(new BigInteger("2")));
		System.out.println("除法动作"+bigInstance.divide(new BigInteger("2")));
		System.out.println("取商动作"+bigInstance.divideAndRemainder(new BigInteger("3"))[0]);
		System.out.println("取余动作"+bigInstance.divideAndRemainder(new BigInteger("3"))[1]);
		System.out.println("2次方动作"+bigInstance.pow(2));
		System.out.println("相反数动作"+bigInstance.negate());
	}

}
