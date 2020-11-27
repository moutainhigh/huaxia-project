/**
 * Title: InversePairs.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月7日下午1:17:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:InversePairs2019年11月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月7日下午1:17:58
 */
public class InversePairs {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月7日下午1:17:58
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = {7,5,6,4};
	System.out.println(	InversePair(data,data.length));
	}
	/**
	 * @Title: InversePair
	 *@Description: TODO
	 *@param data
	 *@param length
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月7日下午1:20:32
	 */
	static int InversePair(int[] data,int length){
		if(data == null || length <0){
			return 0;
		}
		int[] copy = new int[length];
		for(int i=0;i<length;i++)
			copy[i] = data[i];
		int count = InversePairsCore(data,copy,0,length-1);
		
		return count;
	}
	/**
	 * @Title: InversePairsCore
	 *@Description: TODO
	 *@param data
	 *@param copy
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月7日下午1:20:38
	 */
	static int InversePairsCore(int[] data,int[] copy,int start,int end){
		if(start == end)
		{
			copy[start] = data[start];
			return 0;
		}
		int length = (end -start)/2;
		int left = InversePairsCore(copy,data,start,start+length);
		int right = InversePairsCore(copy,data,start+length+1,end);
		int i= start+length;
		int j= end;
		int indexCopy = end;
		int count = 0;
		if(i>=start && j>=start+length+1){
			if(data[i] >data[j]){
				copy[indexCopy--] = data[i--];
				count+= j-start-length;
			}
			else
			{
				copy[indexCopy--] =data[j--];
			}
		}
		for(;i>=start;--i)
			copy[indexCopy--] = data[i];
		for(;j>=start+length+1;--j)
			copy[indexCopy--] = data[j];
		return left+right+count;
	}
}
