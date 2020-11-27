/**
 * Title: FindContinuousSequence.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月31日上午11:23:30
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:FindContinuousSequence2019年10月31日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月31日上午11:23:30
 */
public class FindContinuousSequence {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月31日上午11:23:30
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindContinuousSequence(15);
	}
	/**
	 * @Title: FindContinuousSequence
	 *@Description: TODO
	 *@param sum
	 *@author: LiuWei
	 *@Date: 2019年10月31日上午11:23:54
	 */
	static void FindContinuousSequence(int sum){
		if(sum <3){
			return;
		}
		int small = 1;
		int big =2;
		int middle=(1+sum)/2;
		int curSum = small + big;
		
		while(small <middle){
			if(curSum == sum){
				PrintContinuousSequence(small,big);
			}
			while(curSum>sum && small < middle){
				curSum -= small;
				small++;
				if(curSum == sum){
					PrintContinuousSequence(small,big);
				}
			}
			big++;
			curSum+=big;
		}
	}
	/**
	 * @Title: PrintContinuousSequence
	 *@Description: TODO
	 *@param small
	 *@param big
	 *@author: LiuWei
	 *@Date: 2019年10月31日下午1:54:18
	 */
	static void PrintContinuousSequence(int small,int big){
		for(int i=small;i<=big;++i){
			System.out.printf("%d", i);
		}
		System.out.println();
	}
}
