/**
 * Title: P6_23.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������5:02:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:P6_232020��8��10��
 * @comments:����ļӷ�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������5:02:05
 */
public class P6_23 {

	/**
	 * Constructor
	 */
	public P6_23() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��10������5:02:05
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double A[][] ={
				{1.0,2.0,3.0},
				{4.0,5.0,6.0},
				{7.0,8.0,9.0}
		};
		double B[][] ={
				{1.0,2.0,3.0},
				{4.0,5.0,6.0},
				{7.0,8.0,9.0}
		};
		double[][] C = new double[3][3];
		int m,n,i,j;
		m=3;
		n=3;
		System.out.println("����A��B��ӵĽ��Ϊ��");
		MatrixPlus(A,B,m,n,C);
		for(i=0;i<m;i++)
		{
			for(j=0;j<n;j++)
			{
				System.out.printf("%10.6f ",C[i][j]);
			}
			System.out.println();
		}
	}
	/**
	 * @Title: MatrixPlus
	 *@Description: TODO �������ļӷ�
	 *@param A
	 *@param B
	 *@param m
	 *@param n
	 *@param C
	 *@author: LiuWei
	 *@Date: 2020��8��10������5:03:50
	 */
	public static void MatrixPlus(double A[][],double B[][],int m,int n,double C[][]){
		int i,j;
		for(i =0;i<m;i++){
			for(j=0;j<n;j++){
				C[i][j] = A[i][j]+B[i][j];
			}
		}
	}
}
