/**
 * Title: Pi.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月21日上午11:24:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:Pi2020年9月21日
 * @comments: 随机数计算pi的值
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月21日上午11:24:38
 */
public class Pi {

	/**
	 * Constructor
	 */
	public Pi() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月21日上午11:24:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 100000;
		int m = 0;
		double x,y;
		for(int i=0;i<n;i++){
			x = Math.random();
			y = Math.random();
			if(x*x + y*y <= 1)
				m++;
		}
		System.out.println("根据随机数计算pi的值");
		System.out.println("\tpi="+(double)m/n*4);
	}
}
