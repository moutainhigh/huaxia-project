/**
 * Title: p10_1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��17������10:33:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:p10_12020��8��17��
 * @comments:��Ǯ��ټ��㷨������5��Ǯĸ��3��ǮС��3ֻһ��Ǯ����100��Ǯ��100ֻ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��17������10:33:36
 */
public class p10_1 {

	/**
	 * Constructor
	 */
	public p10_1() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: BQBJ
	 *@Description: TODO
	 *@param m
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020��8��17������10:37:46
	 */
	static void BQBJ(int m,int n){
		int x,y,z;
		for(x = 0;x<=n;x++)
		{
			for(y = 0;y<=n;y++)
			{
				z =n-x-y;
				if(z>0 && z %3 == 0 && 5*x+y*3 +z/3 ==m)
				{
					System.out.printf("���� :%dֻ��ĸ��:%dֻ��С��%dֻ\n",x,y,z);
				}
			}
		}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��17������10:33:36
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m,n;
		m = 100;
		n = 100;
		System.out.printf("%dǮ��%d�����������Ϊ:\n", m,n);
		BQBJ(m,n);
	}
}
