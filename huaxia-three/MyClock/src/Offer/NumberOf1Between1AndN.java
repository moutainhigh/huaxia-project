/**
 * Title: NumberOf1Between1AndN.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月30日下午4:23:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:NumberOf1Between1AndN2019年10月30日
 * @comments:从1到n中1出现的次数
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月30日下午4:23:02
 */
public class NumberOf1Between1AndN {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月30日下午4:23:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(NumberOf1Between1AndN(11));
	}
	/**
	 * @Title: NumberOf1Between1AndN
	 *@Description: TODO
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月30日下午4:54:47
	 */
	static int NumberOf1Between1AndN( int n){
		int number = 0;
		for(int i=1;i <= n;i++){
			number += NumberOf1(i);
		}
		return number;
	}
	/**
	 * @Title: NumberOf1
	 *@Description: TODO
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月30日下午4:54:44
	 */
	static int NumberOf1(int n){
		int number = 0;
		while(n>0){
			if(n%10 ==1){
				number++;
			}
			n=n/10;
		}
		return number;
	}
}
