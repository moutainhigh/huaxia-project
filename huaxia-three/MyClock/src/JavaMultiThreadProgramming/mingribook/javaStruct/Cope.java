package JavaMultiThreadProgramming.mingribook.javaStruct;

import java.util.Arrays;

public class Cope {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = new int[]{23,42,12} ;
		int newArr[] = Arrays.copyOf(arr, 5);
		for(int i=0;i<newArr.length;i++){
			System.out.println("第"+i+"个元素是："+newArr[i]);
		}
	}

}
