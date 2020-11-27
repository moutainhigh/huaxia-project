package JavaMultiThreadProgramming.mingribook.javaStruct;

import java.util.Arrays;

public class Repaeat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = new int[]{23,42,12,84,10} ;
		int newArr[] = Arrays.copyOfRange(arr, 0,3);
		for(int i=0;i<newArr.length;i++){
			System.out.println("第"+i+"个元素是："+newArr[i]);
		}
	}

}
