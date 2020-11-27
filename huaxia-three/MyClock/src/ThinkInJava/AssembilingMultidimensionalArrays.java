package ThinkInJava;

import java.util.Arrays;
/**
 * 测试多维数组的使用
 * @author User
 *
 */
public class AssembilingMultidimensionalArrays {
	public static void main(String args[]){
		Integer[][] a;
		a = new Integer[3][];
		for(int i=0;i<a.length;i++){
			a[i] = new Integer[3];
			for(int j=0;j<a[i].length;j++){
				a[i][j] = i*j;
			}
		}
		System.out.println(Arrays.deepToString(a));
	}
}
