/**
 * Title: Worker.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��23������5:04:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Worker2020��9��23��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��23������5:04:42
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
	 *@Date: 2020��9��23������5:04:42
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public abstract int workTime();
}
