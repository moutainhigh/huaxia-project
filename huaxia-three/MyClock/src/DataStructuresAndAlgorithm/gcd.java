package DataStructuresAndAlgorithm;
/**
 * 欧几里得算法，计算两个数的最大公约数
 * @author liuwei
 *
 */
public class gcd {
	public static long gcd(long m,long n){
		while(n!=0){
			long rem = m%n;
			m= n;
			n= rem;
		}
		return m;
	}
	public static void main(String args[]){
		System.out.println(gcd(1989,1590));
	}
}
