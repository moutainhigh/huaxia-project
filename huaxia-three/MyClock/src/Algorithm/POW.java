package Algorithm;
/**
 * 计算幂运算，x的31次方= x的15次方的平方*x
 * x的62次方 = x的31次方的平方
 * 优化计算次数
 * @author Liuwei
 */
public class POW {
	public static long  pow(long x ,int n){
		if(n == 0)
			return 1;
		if(n == 1)
			return x;
		if(n%2 == 0)
			return pow(x*x,n/2);
		else
			return pow(x*x,n/2)*x;
	}
	public static void main(String args[]){
		System.out.println(pow(3,4));
	}
}
