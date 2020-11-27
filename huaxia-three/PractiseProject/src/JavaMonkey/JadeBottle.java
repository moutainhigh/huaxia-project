/**
 * Title: JadeBottle.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��15������3:45:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:JadeBottle2020��9��15��
 * @comments:
 * @param: ��ƿֻ��һ��ʵ���ķ�ʽ�����췽����˽�еģ������ٷ�����new������
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��15������3:45:23
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
	 *@Date: 2020��9��15������3:45:23
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * @Title: getInstance
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��15������4:38:02
	 */
	public static JadeBottle getInstance(){
		return jadeBottle;
	}
	/**
	 * @Title: pourIn
	 *@Description: TODO
	 *@param water
	 *@author: LiuWei
	 *@Date: 2020��9��15������4:39:24
	 */
	public void pourIn(int water){
		this.water += water;
	}
	/**
	 * @Title: pourOut
	 *@Description: TODO
	 *@param water
	 *@author: LiuWei
	 *@Date: 2020��9��15������4:39:58
	 */
	public void pourOut(int water){
		this.water -= water;
	}
}
