package ProgramBeauty;
import java.util.Scanner;
/**
 * 输出所有的回文数
 * 
 * @author liuwei
 *
 */
public class numberBack {
	// 使用trycatch，使程序报错了但是可以正常执行。
	public static void main(String args[]) {
		while (true) {
			try {
//				System.out.println("请输入一个五位数");
//				Scanner sc = new Scanner(System.in);
//				long input = sc.nextInt();
				for(long i=1;true;i++){
					method(i);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void method(long number){
		long returnNumber = number;
		String num = String.valueOf(number);
		long arr[] = new long[num.length()];
		int i = num.length()-1;
		do {
			arr[i] = number % 10;
			number /= 10;
			i--;
		} while (i >= 0);
		int j;
		for(j = 0;j<num.length();j++){
			if (arr[j] == arr[num.length()-j-1]) {
				continue;
			}else{
				break;
			}
		}
		if(j == num.length()){
			System.out.println("输入的数字是回文数!::"+returnNumber);
		}else{
//			System.out.println("输入的数字不是回文数!");
		}
	}
}
