/**
 * Title: ComputerDirector.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月29日上午10:12:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:ComputerDirector2020年9月29日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月29日上午10:12:07
 */
public class ComputerDirector {
	/**
	 * @Title: constructComputer
	 *@Description: TODO
	 *@param computerBuilder
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月29日上午10:13:37
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
