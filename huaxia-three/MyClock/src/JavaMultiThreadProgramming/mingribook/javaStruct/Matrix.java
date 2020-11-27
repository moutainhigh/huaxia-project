package JavaMultiThreadProgramming.mingribook.javaStruct;

import java.util.Date;

/**
 * 二维数组的使用
 * @author liuwei
 *
 */
public class Matrix {
	public static void main(String args[]){
		int a[][] = new int[3][4];
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[i].length;j++){
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
	}
}
