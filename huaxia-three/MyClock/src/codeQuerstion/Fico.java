package codeQuerstion;
/**
 * 斐波那契数列
 * 有一对兔子，三个月每个月都生一对兔子
 * 小兔子三个月后又生一对兔子
 * 假设兔子都不死，计算制定月份的兔子数目
 * 规律1 1 2 3 5 8 13 目前数字等于前两个数字相加
 * @author 刘伟
 *解法一,递归
 */
public class Fico {

	public static void main(String args[]){
		int num = countRabbits(20);
		System.out.println(num);
		Fico2 fico = new Fico2();
		fico.fico();
	}
	public static int countRabbits(int n){
		if(n <= 2) 
			return 1;
		else
			return countRabbits(n-1)+countRabbits(n-2);
	}
}
/**
 * 解法二
 */
class Fico2{
	public static void fico(){
		int s1 = 1,s2 = 1,s,month = 24;
		System.out.println("第1个月的兔子总数：\t"+1);
		System.out.println("第2个月的兔子总数：\t"+1);
		for(int i = 3;i <= month;i++){
			s = s2;
			s2 += s1;
			s1 = s;
			System.out.println("第"+i+"个月的兔子总数：\t"+s2);
		}
	}
}