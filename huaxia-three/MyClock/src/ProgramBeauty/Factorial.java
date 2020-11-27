package ProgramBeauty;
/**
 * 阶乘的某位0的个数
 * @author liuwei
 *
 */
public class Factorial {
	public static int fact(int n){
		if(n == 1 || n == 0) return 1;
		else 
			return fact(n-1) *n;
	}
	public static void fact0(int n){
		long result;
		long result2;
		int count;
		for(int i=1;i<=n;i++){
			result = fact(i);
			result2= result;
			count = 0;
			while( result%10 == 0 ){
				result/=10;
				count++;
			}
			System.out.println(result2);
			System.out.println("有"+count+"个0");
		}
	}
	public static void main(String args[]){
		fact0(100);
	}
}
