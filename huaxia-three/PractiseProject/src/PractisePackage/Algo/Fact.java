/**
 * Title: Fact.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��6������5:21:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:Fact2020��8��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������5:21:49
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
	 *@Date: 2020��8��6������5:21:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(fact(4));
	}
	/**
	 * @Title: fact �ݹ����׳�����
	 *@Description: TODO
	 *@param n 
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��6������5:23:10
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
