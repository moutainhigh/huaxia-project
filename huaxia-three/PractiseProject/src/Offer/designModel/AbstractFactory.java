/**
 * Title: AbstractFactory.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月28日下午4:46:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:AbstractFactory2020年9月28日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月28日下午4:46:26
 */
public abstract class AbstractFactory {

	/**
	 * Constructor
	 */
	public AbstractFactory() {
		// TODO Auto-generated constructor stub
	}
	public abstract Phone2 createPhone(String brand);
	public abstract Computer createComputer(String brand);
}
