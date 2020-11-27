/**
 * Title: ReorderOaddEven.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月31日下午4:26:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

import java.lang.reflect.Array;

/**
 * @class_name:ReorderOaddEven2019年10月31日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月31日下午4:26:15
 */
public class ReorderOaddEven {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月31日下午4:26:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr= {1,2,3,4,5};
		ReorderOddEven(arr,arr.length);
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
	}
	/**
	 * @Title: ReorderOddEven
	 *@Description: TODO
	 *@param pData
	 *@param length
	 *@author: LiuWei
	 *@Date: 2019年10月31日下午5:01:08
	 */
	static void ReorderOddEven(int[] pData,int length)
	{
		if(pData == null || length ==0)
			return ;
		int pBegin = 0;
		int pEnd = 0+length-1;
		while(pBegin <pEnd){
			while(pBegin <pEnd && (pData[pBegin] %2==0))
				pBegin++;
			while(pBegin <pEnd && (pData[pEnd] %2==1))
				pEnd--;
			if(pBegin <pEnd){
				int temp = pData[pBegin];
				pData[pBegin] = pData[pEnd];
				pData[pEnd] = temp;
			}
		}
	}
}
