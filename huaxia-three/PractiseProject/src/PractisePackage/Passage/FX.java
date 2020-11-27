/**
 * Title: FX.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月19日上午9:46:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:FX2020年8月19日
 * @comments:结果不符，差之毫厘谬以千里，计算机小数是有限位数存储的。
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月19日上午9:46:01
 */
public class FX {
	/**
	 * @Title: fx
	 *@Description: TODO  计算数学函数
	 *@param x
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月19日上午9:47:03
	 */
	public static double fx(double x){
		if( x< 0.5)
			return 2*x;
		else
			return 2*x -1;
	}
	/**
	 * Constructor
	 */
	public FX() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月19日上午9:46:01
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x = 0.1;
		for(int i=0;i<80;i++){
			System.out.println(x);
			x = fx(x);
		}
	}
}
