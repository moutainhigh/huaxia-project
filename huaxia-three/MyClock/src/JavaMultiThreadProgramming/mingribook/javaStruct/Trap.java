package JavaMultiThreadProgramming.mingribook.javaStruct;

import java.util.Date;

/**
 * 二维数组的使用
 * @author liuwei
 *
 */
public class Trap {
	public static void main(String args[]){
		int b[][]=new int[][]{{1},{2,3},{4,5,6}};
		for(int i=0;i<b.length;i++){
			for(int j=0;j<b[i].length;j++){
				System.out.print(b[i][j]);
			}
			System.out.println();
		}
	}
}
