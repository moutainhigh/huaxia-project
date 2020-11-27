package ProgramBeauty;
/**
 * 计算连加和
 * 计算出1+2=3
 * 4+5 = 9
 * 2+3+4 =9这样的数字
 * 计算出100内这样的数字
 * 算法1 使用递归
 * 方程式，出口，公式。
 * 算法一：长度2位，3位，4位
 * 2位的话
 *  1+2 2+3 3+4 
 * 3位的话
 * 1+2+3 2+3+4 3+4+5
 * 位数最多到这个数的大小
 * 
 * @author liuwei
 *
 */
public class addSum {
	public static void add(int n){
		int sum = 0;
		for(int i =2;i<=n;i++){//几位连续整数 2,3,4..位数
			for(int j = 1;j<n;j++){//从1 开始的连续和  个数,控制起点
				for(int k=j;k<=i;k++){
					sum+=k;
				}
				if(sum == n){
					int k= j;
					for(;k<i;k++){
						System.out.print(k+"+");
					}
					System.out.print(k);
					System.out.println("="+sum);
				}
				sum = 0;
			}
		}
	}
	public static void main(String args[]){
		for(int i=3;true;i++){
			add(i);
		}
		
	}
}
