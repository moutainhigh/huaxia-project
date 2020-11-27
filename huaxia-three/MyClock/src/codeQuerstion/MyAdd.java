package codeQuerstion;

import java.util.Scanner;

/**
 *计算s= a+aa+aaa+aaaa
 *参数n,和项数n
 * @author Liuwei
 *
 */
public class MyAdd {

	private static Scanner sc;
	public static void main(String args[]){
		sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = a;
		int n = sc.nextInt();
		int sum = 0;
		for(int i=0 ;i<n ;i++){
			sum = sum +a;
			a=10*a+b;
		}
		System.out.println(sum);
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
	}
}
