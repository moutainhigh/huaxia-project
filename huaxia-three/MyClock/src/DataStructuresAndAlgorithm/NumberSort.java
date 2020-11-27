package DataStructuresAndAlgorithm;
/**
 * 数字排序
 * @author liuwei
 *
 */
public class NumberSort {
	/**
	 * @Title: shellSort
	 *@Description: 希尔排序
	 *@param r
	 *@param n
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午6:24:40
	 */
	static void shellSort(int[] r,int n){
		int j;
		for(int d=n/2;d>=1;d=d/2){
			for(int i=d+1;i<=n;i++){
				r[0] = r[i];
				for(j=i-d;j>0&&r[0]<r[j];j=j-d)
					r[j+d]= r[j];
				r[j+d] = r[0];
			}
		}
	}
	public static void main(String args[]){
		int[] r={7,6,5,4,3,2,1,10};
		shellSort(r,7);
		for(int i=0;i<r.length;i++){
			System.out.print(r[i]+" ");
		}
	}
}
