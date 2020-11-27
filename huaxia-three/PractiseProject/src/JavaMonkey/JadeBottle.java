/**
 * Title: JadeBottle.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月15日下午3:45:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:JadeBottle2020年9月15日
 * @comments:
 * @param: 玉净瓶只有一个实例的方式，构造方法是私有的，不能再方法外new产生。
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月15日下午3:45:23
 */
public class JadeBottle {
	private int water ;
	private static JadeBottle jadeBottle  = new JadeBottle();
	/**
	 * Constructor
	 */
	private JadeBottle() {
		// TODO Auto-generated constructor stub
		water = 10000;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月15日下午3:45:23
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * @Title: getInstance
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月15日下午4:38:02
	 */
	public static JadeBottle getInstance(){
		return jadeBottle;
	}
	/**
	 * @Title: pourIn
	 *@Description: TODO
	 *@param water
	 *@author: LiuWei
	 *@Date: 2020年9月15日下午4:39:24
	 */
	public void pourIn(int water){
		this.water += water;
	}
	/**
	 * @Title: pourOut
	 *@Description: TODO
	 *@param water
	 *@author: LiuWei
	 *@Date: 2020年9月15日下午4:39:58
	 */
	public void pourOut(int water){
		this.water -= water;
	}
}
