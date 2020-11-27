package codeQuerstion;

import java.util.Scanner;
/**
 * 判断输入的一个数字是不是回文数
 * @author Liuwei
 *
 */
public class numberBack {

	//使用trycatch，使程序报错了但是可以正常执行。
	public static void main(String args[]){
		while(true){
			try{
		System.out.println("请输入一个五位数");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		int arr[] = new int[5];
		int i=4;
		do{
			arr[i]= input%10;
			input/=10;
			i--;
		}while(i>=0);
		if(arr[0]==arr[4]&& arr[1]==arr[3]){
			System.out.println("输入的数字是回文数!");
		}else{
			System.out.println("输入的数字不是回文数!");
		}
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	}
}
