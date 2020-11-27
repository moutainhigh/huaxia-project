package JavaMultiThreadProgramming.mingribook.javaStruct;

import java.util.Arrays;

public class Diaplace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = new int[]{45,12,2,10} ;
		Arrays.fill(arr, 1,2,8);
		for(int i=0;i<arr.length;i++){
			System.out.println("第"+i+"个元素是："+arr[i]);
		}
	}

}
