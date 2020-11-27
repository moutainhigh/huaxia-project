package DataStructuresAndAlgorithm;
/**
 * 使用递归算法输出算法
 * @author User
 *
 */
public class printDigit {
	public static void printOut(int n){
		if(n >= 10)
			printOut(n/10);
		System.out.print(n%10);
	}
	public static void main(String args[]){
		printOut(76234);
		printOut(12345675);
	}
}
