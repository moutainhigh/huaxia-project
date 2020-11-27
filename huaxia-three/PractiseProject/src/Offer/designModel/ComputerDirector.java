/**
 * Title: ComputerDirector.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��29������10:12:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:ComputerDirector2020��9��29��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��29������10:12:07
 */
public class ComputerDirector {
	/**
	 * @Title: constructComputer
	 *@Description: TODO
	 *@param computerBuilder
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��29������10:13:37
	 */
	public Computer2 constructComputer(ComputerBuilder computerBuilder){
		computerBuilder.buildcpu();
		computerBuilder.buildDisk();
		computerBuilder.buildemory();
		return computerBuilder.buildCommputer();
	}
	/**
	 * Constructor
	 */
	public ComputerDirector() {
		// TODO Auto-generated constructor stub
	}

}
