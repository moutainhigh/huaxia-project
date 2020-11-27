package DataStructuresAndAlgorithm;

/**
 * 测试动态规划算法
 * 计算量不是一个数量级别
 * @author liuwei
 *
 */
public class DynamicProgramming {
	/**
	 * @Title: fib
	 *@Description: 递归方法计算fibnacci数列
	 *@param n
	 *@return
	 *@author: LiuWei
	 */
	public static int fib(int n){
		if(n<=1)
			return 1;
		else
			return fib(n-1)+fib(n-2);
	}
	/**
	 * @Title: fiboncci
	 *@Description: 使用动态规划计算fibonacci数列，比较器大数据量下的运行时间。
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月5日上午10:11:14
	 */
	public static int fiboncci(int n){
		if(n <+ 1)
			return 1;
		int last = 1;
		int nextTolast = 1;
		int answer = 1;
		for(int i = 2;i<= n;i++){
			answer = last + nextTolast;
			nextTolast = last;
			last = answer;
		}
		return answer;
	}
	public static void main(String args[]){
		int n = 30;
		Long start2 = System.currentTimeMillis();
		int f2 = fiboncci(n);
		Long end2 = System.currentTimeMillis();
		System.out.println("f2()="+f2+" 动态规划花费时间"+(end2-start2));
		
		Long start1 = System.currentTimeMillis();
		int f1 = fib(n);
		Long end1 = System.currentTimeMillis();
		System.out.println("f1()="+f1+" 递归花费时间"+(end1-start1));
	}
}
