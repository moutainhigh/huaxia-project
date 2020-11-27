package DataStructuresAndAlgorithm;
/**
 * 计算矩阵的乘法。利用矩阵的乘法规则
 * @author liuwei
 *
 */
public class Multiply {
	public static int[][] multiply(int[][] a,int[][] b){
		int n = a.length;
		int[][] c = new int[n][n];
		for(int i =0;i<n;i++)
			for(int j=0;j<n;j++)
				c[i][j] = 0;
		for(int i =0;i<n;i++)
			for(int j=0;j<n;j++)
				for(int k=0;k<n;k++)
					c[i][j] += a[i][k]*b[k][j];
		return c;
	}
	public static void main(String args[]){
		int n = 3;
		int[][] a = new int[n][n];
		int[][] b = new int[n][n];
		for(int i =0;i<n;i++)
			for(int j=0;j<n;j++)
				a[i][j] = 1;
		for(int i =0;i<n;i++)
			for(int j=0;j<n;j++)
				b[i][j] = 1;
		int[][] c = multiply(a,b);
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++)
				System.out.print(c[i][j]+" ");
		   System.out.println();
		}
	}
}
