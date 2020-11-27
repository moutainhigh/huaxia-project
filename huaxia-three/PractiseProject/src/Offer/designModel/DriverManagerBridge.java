/**
 * Title: DriverManagerBridge.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��30������11:16:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:DriverManagerBridge2020��9��30��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��30������11:16:12
 */
public abstract class DriverManagerBridge {
	private Driver driver;
	/**
	 * Constructor
	 */
	public DriverManagerBridge() {
		// TODO Auto-generated constructor stub
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	/**
	 * @Title: execute
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��30������11:27:43
	 */
	public void execute(){
		this.driver.executeSQL();
	}
}
