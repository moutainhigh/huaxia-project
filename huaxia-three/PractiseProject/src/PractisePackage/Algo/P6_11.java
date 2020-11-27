/**
 * Title: P6_10.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日下午3:03:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:P6_102020年8月10日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日下午3:03:49
 */
public class P6_11 {
	
	/**
	 * Constructor
	 */
	public P6_11() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午3:03:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		double u,t;
		double[] r = {5.0};
		u = 2.0;
		t = 3.5;
		System.out.printf("产生10个正态分布的随机数\n");
		for(i =0;i<10;i++)
		{
			System.out.printf("%10.5f\n",randZT(u,t,r));
		}
		System.out.println();
	}
	/**
	 * @Title: rand01
	 *@Description: TODO 随机数算法
	 *@param r
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午2:47:30
	 */
	public static double rand01(double[] r){
		double base,u,v,p,temp1,temp2,temp3;
		base = 256.0;
		u = 17.0;
		v = 139.0;
		 
		temp1 = u*(r[0])+v;
		temp2 = (int)(temp1/base);
		temp3 = temp1-temp2*base;
		r[0] = temp3;
		p= r[0]/base;
		return p;
	}
	/**
	 * @Title: randZT
	 *@Description: TODO 正态分布随机数
	 *@param u
	 *@param t
	 *@param r
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午3:15:04
	 */
	public static double randZT(double u,double t,double[] r){
		int i;
		double total = 0.0;
		double result;
		for(i =0;i<12;i++){
			total+=rand01(r);
		}
		result = u+t*(total-6.0);
		return result;
	}
}
