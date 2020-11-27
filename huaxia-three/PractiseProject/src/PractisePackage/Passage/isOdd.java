/**
 * Title: isOdd.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日上午9:54:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:isOdd2020年8月18日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日上午9:54:07
 */
public class isOdd {
	
	/**
	 * Constructor
	 */
	public isOdd() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月18日上午9:54:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * @Title: isOdd
	 *@Description: TODO
	 *@param i 判断是否为奇数
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月18日上午9:56:15
	 */
	public boolean isOdd(int i){
		if(i % 2 == 0){
			return false;
		}
		return true;
	}
	/**
	 * @Title: isOdd1
	 *@Description: TODO
	 *@param i 判断是否为奇数,更为简化
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月18日上午9:58:12
	 */
	public boolean isOdd1(int i){
		return i % 2 != 0;
	}
}
