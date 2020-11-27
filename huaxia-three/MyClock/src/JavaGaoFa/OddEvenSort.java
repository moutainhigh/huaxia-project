package JavaGaoFa;
/**
 * 奇数和偶数交换排序
 * @author liuwei
 *
 */
public class OddEvenSort {
	public static void oddEvenSort(Integer[] arr){
		int exchFlag = 1,start = 0;
		while(exchFlag == 1 || start == 1){
			exchFlag = 0;
			for(int i=start;i<arr.length-1;i+=2){
				if(arr[i] >arr[i+1]){
					int temp = arr[i];
					arr[i] =arr[i+1];
					arr[i+1] = temp;
					exchFlag = 1;
				}
			}
			if(start == 0)
				start = 1;
			else
				start =0;
		}
	}
	public static void main(String args[]){
		Integer arr[] = {1,2,3,4,5,6,7,8,9,10};
		oddEvenSort(arr);
		for(int i=0;i<10;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
	}
}
