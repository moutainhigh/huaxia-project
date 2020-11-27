/**
 * Title: P6_25.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������5:17:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:P6_252020��8��10��
 * @comments:�������ĳ˷�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������5:17:49
 */
public class P6_25 {

	/**
	 * Constructor
	 */
	public P6_25() {
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
		int m,n,k,i,j;
		m=3;
		n=3;
		k=3;
		System.out.println("����A��B��˵Ľ��Ϊ��");
		MatrixMul(A,B,m,n,k,C);
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
	 *@Description: TODO �������ĳ˷�
	 *@param A
	 *@param B
	 *@param m
	 *@param n
	 *@param C
	 *@author: LiuWei
	 *@Date: 2020��8��10������5:03:50
	 */
	public static void MatrixMul(double A[][],double B[][],int m,int n,int k,double C[][]){
		int i,j,l;
		for(i =0;i<m;i++){
			for(j=0;j<n;j++){
			 C[i][j]= 0;
			 for(l =0;l<k;l++)
			 {
				 C[i][j] += (A[i][l]*B[l][j]);
			 }
			}
		}
	}

}
