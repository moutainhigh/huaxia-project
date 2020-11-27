/**
 * Title: MinDifference.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月8日下午3:52:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package ProgramBeauty;

/**
 * @class_name:MinDifference2019年11月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月8日下午3:52:44
 */
public class MinDifference {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月8日下午3:52:44
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] arr={1,3,5,7,9,10};
		System.out.println(MinDifference(arr,arr.length));
		System.out.println("HEllo World!");
		System.out.println("HEllo World!");
		System.out.println("HEllo World!");
		System.out.println("HEllo World!");
		System.out.println("HEllo World!");
		System.out.println("HEllo World!");
		System.out.println("HEllo World!");
		System.out.println("HEllo World!");
		System.out.println("HEllo World!");
		System.out.println("HEllo World!");
	}
	/**
	 * @Title: MinDifference
	 *@Description: 寻找最临界点数对
	 *@param arr
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月8日下午3:53:20
	 */
	static double MinDifference(double arr[],int n){
		if(n<2) 
			return 0;
		double fMinDiff = Math.abs(arr[0]-arr[1]);
		for(int i=0;i<n;i++)
			for(int j= i+1;j<n;++j)
			{
				double tmp = Math.abs(arr[i]-arr[j]);
				if(fMinDiff>tmp)
				{
					fMinDiff= tmp;
				}
			}
		return fMinDiff;
	}
}
