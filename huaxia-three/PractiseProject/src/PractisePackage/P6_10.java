/**
 * Title: P6_10.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������3:03:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage;

/**
 * @class_name:P6_102020��8��10��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������3:03:49
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
	 *@Date: 2020��8��10������3:03:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i,m,n;
		double[] r = {5.0};
		m= 100;
		n = 200;
		System.out.printf("����10��[100,200]֮��������\n");
		for(i =0;i<10;i++)
		{
			System.out.printf("%d\n",m+(int)((n-m)*rand01(r)));
		}
		System.out.println();
	}
	/**
	 * @Title: rand01
	 *@Description: TODO ������㷨
	 *@param r
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��10������2:47:30
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
