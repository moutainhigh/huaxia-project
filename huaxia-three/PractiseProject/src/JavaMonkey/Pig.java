/**
 * Title: Pg.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月14日上午9:30:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Pg2020年9月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月14日上午9:30:21
 */
public class Pig {
	String name;
	/**
	 * Constructor
	 */
	public Pig(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月14日上午9:30:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pig pig = new Pig("猪八戒");
		pig.eat(); 
	}
	/**
	 * @Title: eat
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年9月14日上午9:33:35
	 */
	public void eat(){
		System.out.println("端米杯汤盘中蔬，得来不易历艰辛，细嚼慢咽用心唱，感谢天下众生恩");
	}
}
