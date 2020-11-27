/**
 * Title: largess.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月17日下午3:59:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algothm;

/**
 * @class_name:largess2020年8月17日
 * @comments:64棋盘的麦粒数。
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月17日下午3:59:12
 */
public class largess {

	/**
	 * Constructor
	 */
	public largess() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月17日下午3:59:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double t = 1,sum = 1;
		int i ;
		for(i = 2 ;i<=64;i++)
		{
			t = t* 2;
			sum = sum +t;
		}
		System.out.printf("总麦粒数为：%.2f\n",sum);
		System.out.printf("共%.2f吨\n",sum/25380/1000);
	}

}
