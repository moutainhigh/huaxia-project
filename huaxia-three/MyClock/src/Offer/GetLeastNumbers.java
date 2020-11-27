/**
 * Title: GetLeastNumbers.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月18日下午12:39:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:GetLeastNumbers2019年11月18日
 * @comments:找出最小的k个数
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月18日下午12:39:38
 */
public class GetLeastNumbers {

	/**
	 * 
	 */
	public GetLeastNumbers() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月18日下午12:39:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k=4;
		int[] input = {4 ,5,1,6,2,7,3,8};
		int[] output = new int[k];
		for(int i=0;i<input.length;i++)
			System.out.print(input[i]+" ");
		System.out.println();
		for(int i=0;i<output.length;i++)
			System.out.print(output[i]+" ");
		System.out.println();
		GetLeastNumbers(input,input.length,output,k);
		for(int i=0;i<input.length;i++)
			System.out.print(input[i]+" ");
		System.out.println();
		for(int i=0;i<output.length;i++)
			System.out.print(output[i]+" ");
		System.out.println();
	}
	/**
	 * @Title: GetLeastNumbers
	 *@Description: TODO
	 *@param input
	 *@param n
	 *@param output
	 *@param k
	 *@author: LiuWei
	 *@Date: 2019年11月18日下午12:40:24
	 */
	public static void GetLeastNumbers(int[] input,int n,int[] output,int k){
		if(input == null || output == null || k>n ||n<=0 || k<=0)
			return;
		int start =0;
		int end = n-1;
		try {
			int index = Partition.Partition(input, n, start, end);
			while(index != k-1)
			{
				if(index >k-1)
				{
					end = index -1;
					index = Partition.Partition(input, n, start, end);
				}
				else 
				{
					start = index +1;
					index = Partition.Partition(input, n, start, end);
				}
			}
			for(int i=0;i<k;i++)
			{
				output[i] = input[i];
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
