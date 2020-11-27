/**
 * Title: Worker.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日下午5:04:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Worker2020年9月23日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日下午5:04:42
 */
public abstract class Worker {
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Constructor
	 */
	public Worker() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日下午5:04:42
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public abstract int workTime();
}
