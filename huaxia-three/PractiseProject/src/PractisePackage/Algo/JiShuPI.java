/**
 * Title: JiShuPI.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日下午4:53:08
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:JiShuPI2020年8月10日
 * @comments: 多项式计算PI
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日下午4:53:08
 */
public class JiShuPI {

	/**
	 * Constructor
	 */
	public JiShuPI() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午4:53:08
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double PI;
		PI = JiShuPI();
		System.out.printf("PI=%f\n",PI);
	}
	/**
	 * @Title: JiShuPI
	 *@Description: TODO 多项式计算PI
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午4:57:25
	 */
	private static double JiShuPI(){
		double PI,temp;
		int n,m;
		n = 1;
		m = 3;
		temp = 2;
		PI = 2;
		while(temp>1e-300){
			temp = temp*n/m;
			PI+=temp;
			n++;
			m+=2;
		}
		return PI;
	}
}
