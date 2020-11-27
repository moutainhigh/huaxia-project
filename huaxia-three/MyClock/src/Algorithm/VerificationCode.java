//package Algorithm;
//
//import java.util.Random;
///**
// * 随机数生成验证码
// * @author liuwei
// */
//public class VerificationCode {
//	static Random rd = new Random();
//	public static void numCoce(){
//		System.out.print("获取五位数字验证码：");
//		for(int i=0;i<5;i++){
//			int n= rd.nextInt(10);
//			System.out.print(n+" ");
//		}
//		System.out.println();
//	}
//	public static void charCode(){
//		System.out.print("\n获取五位字符验证码：");
//		for(int i=0;i<5;i++){
//			int n=65 + rd.nextInt(58);
//			System.out.print((char)n+" ");
//		}
//		System.out.println();
//	}
//	public static void chineseCode(){
//		System.out.print("\n获取五位汉字验证码：");
//		for(int i=0;i<5;i++){
//			int n=20000+ rd.nextInt(10000);
//			System.out.print((char)n+" ");
//		}
//		System.out.println();
//	}
//	public static void mixCode(){
//		System.out.print("\n获取五位混合验证码：");
//		for(int i=0;i<5;i++){
//			int n= rd.nextInt(123);
//			if(n<65){
//				System.out.print(n%10+" ");
//			}else{
//				System.out.print((char)n+" ");
//			}
//		}
//		System.out.println();
//	}
//	public static void main(String args[]){
//		numCoce();
//		charCode();
//		chineseCode();
//		mixCode();
//	}
//}
