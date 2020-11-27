/**
 * Title: JiShuPI.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������4:53:08
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:JiShuPI2020��8��10��
 * @comments: ����ʽ����PI
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������4:53:08
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
	 *@Date: 2020��8��10������4:53:08
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double PI;
		PI = JiShuPI();
		System.out.printf("PI=%f\n",PI);
	}
	/**
	 * @Title: JiShuPI
	 *@Description: TODO ����ʽ����PI
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��10������4:57:25
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
