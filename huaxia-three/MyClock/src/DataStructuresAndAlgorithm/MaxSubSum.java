package DataStructuresAndAlgorithm;
/**
 * 最大子序列求和问题算法
 * @author liuwei
 *
 */
public class MaxSubSum {
	static int x=0;
	static int y=0;
	public static int maxSubSum1(int[] a){
		int maxSum = 0;
		
		for(int i=0;i<a.length;i++){
			for(int j=i;j<a.length;j++){
				int thisSum =0;
				for(int k=i;k<=j;k++){
					thisSum += a[k];
				}
//				System.out.println(thisSum);	
				if(thisSum > maxSum){
					x=i;
					y=j;
					maxSum = thisSum;
				}
					
			}
		}
		return maxSum;
	}
	public static void main(String args[]){
		int[] a= {4,-3,5,-2,-1,2,6,-2};
		System.out.println(maxSubSum1(a));
		System.out.println("i="+x+",j="+y);
	}
}
