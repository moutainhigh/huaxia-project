/**
 * Title: P6_8.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������2:46:35
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:P6_82020��8��10��
 * @comments:ȡ������ȡ�����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������2:46:35
 */
public class P6_8 {
	/**
	 * Constructor
	 */
	public P6_8() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��10������2:46:35
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		double[] r = {5.0};
		System.out.printf("����10��[0,1]֮��������\n");
		for(i =0;i<10;i++)
		{
			System.out.printf("%10.5f\n",rand01(r));
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
