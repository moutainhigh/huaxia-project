package DataStructuresAndAlgorithm;
/**
 * 幂运算的算法,递归实现幂运算
 * @author liuwei
 *
 */
public class Pow {
	public static boolean isEven(int n){
		if(n % 2 == 0){
			return true;
		}
		else 
			return false;
	}
	 public static long pow(long x,int n){
		 if(n==0)
			 return 1;
		 if(n == 1)
			 return x;
		 if(isEven(n)){
			 return pow(x*x,n/2);
		 }else{
			 return pow(x*x,n/2)*x;
		 }
	 }
	 public static void main(String args[]){
		 for(int i=0;i<10;i++)
			 System.out.println(pow(2,i));
	 }
}
