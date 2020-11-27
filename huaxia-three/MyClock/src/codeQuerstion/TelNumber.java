package codeQuerstion;

import java.util.Scanner;
/**
 * 进行一些判断数字为3的清除到数组。最后一个留下来
 * @author User
 *
 */
public class TelNumber {

	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入总人数：");
		int num = scanner.nextInt();
		int[] arr = new int[num];
		for(int i=0 ;i<num;i++){
			arr[i]=1;
		}
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
		int index =0;
		int sum =0;
		while(num > 1){
			if(arr[index] == 1){
				sum++;
				if(sum ==3){
					sum =0 ;
					arr[index] = 0;
					num--;
				}
			}
			index++;
			if(index == arr.length){
				index =0;
			}
		}
		for(int i=0;i< arr.length; i++){
			System.out.println(arr[i]);
		}
		for(int i=0;i<arr.length;i++){
			if(arr[i]== 1){
				System.out.println("第"+(i+1)+"留了下来");
			}
		}
	}
}
