package DataStructuresAndAlgorithm;
/**
 * 测试一些时间复杂度的运算
 * 内层循环减少计算量就能提高时间
 * @author User
 *
 */
public class RunTime {
	public static void main(String args[]){
		int sum = 0;
		int n  = 100;
		long start = System.currentTimeMillis();
		for(int i=0;i < n;i++)
			sum++;
		long end = System.currentTimeMillis();
		System.out.println("o(n)的执行时间"+(end-start));
		sum =0 ;
		start = System.currentTimeMillis();
		for(int i=0;i < n;i++)
			for(int j=0;j<n;j++)
			sum++;
		 end = System.currentTimeMillis();
		System.out.println("o(n2)的执行时间"+(end-start));
		sum =0 ;
		start = System.currentTimeMillis();
		for(int i=0;i < n;i++)
			for(int j=0;j<n*n;j++)
			sum++;
		 end = System.currentTimeMillis();
		System.out.println("o(n2)的执行时间"+(end-start));
		sum =0 ;
		start = System.currentTimeMillis();
		for(int i=0;i < n;i++)
			for(int j=0;j<i*i;j++)
				for(int k=0;k<j;k++)
			sum++;
		 end = System.currentTimeMillis();
		System.out.println("o(n3)的执行时间"+(end-start));
		sum =0 ;
		start = System.currentTimeMillis();
		for(int i=0;i < n;i++)
			for(int j=0;j<i*i;j++)
				if(j%i==0)
				for(int k=0;k<j;k++)
			sum++;
		 end = System.currentTimeMillis();
		System.out.println("o(n3)的执行时间"+(end-start));
	}
}
