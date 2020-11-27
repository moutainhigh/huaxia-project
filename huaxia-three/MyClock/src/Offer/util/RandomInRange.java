/**
 * Title: RandomInRange.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月18日下午1:00:18
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.util;

import java.util.Random;

/**
 * @class_name:RandomInRange2019年11月18日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月18日下午1:00:18
 */
public class RandomInRange {

	/**
	 * 
	 */
	public RandomInRange() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月18日下午1:00:18
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++)
			System.out.println(RandomInRange(10,20));
	}

	/**
	 * @Title: RandomInRange
	 *@Description: 在指定范围内产生一个随机数字
	 *@param start
	 *@param end
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月18日下午1:01:09
	 */
	public static int RandomInRange(int start,int end){
		
		return new Random().nextInt((end-start))+start;
	}
}
