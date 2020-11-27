/**
 * Title: AbstractFactory.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��28������4:46:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:AbstractFactory2020��9��28��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��28������4:46:26
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
