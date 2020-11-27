package ProgramBeauty;
/**
 * 求两个数的最大公约数
 * 利用撵转相除法
 * f(x,y) = f(y,x%y),(x>=y>0)
 * @author Liuwei
 *
 */
public class GCD {
	//方法一利用递归,大问题转化为小问题，使用了递归
	public static int gcd(int x,int y){
		return (y == 0)?x:gcd(y,x%y);
	}
	public static void main(String args[]){
		System.out.println(gcd(42,30));
	}
}
