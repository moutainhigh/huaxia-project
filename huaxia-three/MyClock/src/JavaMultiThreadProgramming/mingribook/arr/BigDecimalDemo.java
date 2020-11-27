/**
 * Title: BigDecimalDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午1:46:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.arr;

import java.math.BigDecimal;

/**
 * @class_name:BigDecimalDemo2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午1:46:39
 */
public class BigDecimalDemo {
	static final int location =10;
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午1:46:39
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimalDemo b = new BigDecimalDemo();
		System.out.println("两个数字相加的结果是："+b.add(-7.5, 8.9));
		System.out.println("两个数字相减的结果是："+b.sub(-7.5, 8.9));
		System.out.println("两个数字相乘的结果是："+b.mul(-7.5, 8.9));
		System.out.println("两个数字相除的结果是：小数保留后10位"+b.div(10,2));
		System.out.println("两个数字相加的结果是：小数保留后5位"+b.div(-7.5, 8.9,5));

	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param value1
	 *@param value2
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午1:48:23
	 */
	public BigDecimal add(double value1,double value2){
		BigDecimal  b1 = new BigDecimal(Double.toString(value1));
		BigDecimal  b2 = new BigDecimal(Double.toString(value2));
		return b1.add(b2);
	}
	/**
	 * @Title: sub
	 *@Description: TODO
	 *@param value1
	 *@param value2
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午1:49:25
	 */
	public BigDecimal sub(double value1,double value2){
		BigDecimal  b1 = new BigDecimal(Double.toString(value1));
		BigDecimal  b2 = new BigDecimal(Double.toString(value2));
		return b1.subtract(b2);
	}
	/**
	 * @Title: mul
	 *@Description: TODO
	 *@param value1
	 *@param value2
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午1:49:54
	 */
	public BigDecimal mul(double value1,double value2){
		BigDecimal  b1 = new BigDecimal(Double.toString(value1));
		BigDecimal  b2 = new BigDecimal(Double.toString(value2));
		return b1.multiply(b2);
	}
	/**
	 * @Title: div
	 *@Description: TODO
	 *@param value1
	 *@param value2
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午1:57:08
	 */
	public BigDecimal div(double value1,double value2){
		return div(value1,value2,location);
	}
	/**
	 * @Title: div
	 *@Description: TODO
	 *@param value1
	 *@param value2
	 *@param b
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午1:57:16
	 */
	public BigDecimal div(double value1,double value2,int b){
		if(b<0){
			System.out.println("b值必须大于等于0");
		}
		BigDecimal  b1 = new BigDecimal(Double.toString(value1));
		BigDecimal  b2 = new BigDecimal(Double.toString(value2));
		//调用除法方法，商小数点后保留b位，并将结果进行四舍五入动作
		return b1.divide(b2,b,BigDecimal.ROUND_HALF_UP);
	}
}
