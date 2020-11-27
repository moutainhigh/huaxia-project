/**
 * Title: Fact.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月6日下午5:21:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:Fact2020年8月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午5:21:49
 */
public class Fact {

	/**
	 * Constructor
	 */
	public Fact() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午5:21:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(fact(4));
	}
	/**
	 * @Title: fact 递归解决阶乘问题
	 *@Description: TODO
	 *@param n 
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午5:23:10
	 */
	public static int fact(int n){
		if(n<0) 
			return 0;
		else if(n ==0)
			return 1;
		else if(n ==1)
			return 1;
		else 
			return n * fact(n-1);
	}
}
