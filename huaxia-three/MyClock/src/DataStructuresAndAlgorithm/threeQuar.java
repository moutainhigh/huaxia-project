package DataStructuresAndAlgorithm;
/**
 * 计算三次方的求和
 * 1的3次方 +2 的3次方 +3的3次方+4的3次方 =100
 * @author User
 *
 */
public class threeQuar {
	public static int sum(int n){
		int partialSum;
		partialSum =0;
		for(int i=1;i<=n;i++){
			partialSum += i*i*i;
		}
		return partialSum;
	}
	public static int sum4(int n){
		int partialSum;
		partialSum =0;
		for(int i=1;i<=n;i++){
			partialSum += i*i*i*i;
		}
		return partialSum;
	}
	public static void main(String args[]){
//		System.out.println(sum(3));
		for(int i=0;i<20;i++){
			System.out.println(sum(i));
			System.err.println(sum4(i));
		}
	}
}
