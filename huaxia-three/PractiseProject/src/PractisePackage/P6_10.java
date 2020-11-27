/**
 * Title: P6_10.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日下午3:03:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage;

/**
 * @class_name:P6_102020年8月10日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日下午3:03:49
 */
public class P6_10 {
	
	/**
	 * Constructor
	 */
	public P6_10() {
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
		int i,m,n;
		double[] r = {5.0};
		m= 100;
		n = 200;
		System.out.printf("产生10个[100,200]之间的随机数\n");
		for(i =0;i<10;i++)
		{
			System.out.printf("%d\n",m+(int)((n-m)*rand01(r)));
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
}
