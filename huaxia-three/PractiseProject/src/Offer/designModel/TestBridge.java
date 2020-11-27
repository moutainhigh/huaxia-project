/**
 * Title: TestBridge.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��30������11:24:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestBridge2020��9��30��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��30������11:24:44
 */
public class TestBridge {

	/**
	 * Constructor
	 */
	public TestBridge() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��30������11:24:44
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DriverManagerBridge driverManagerBridge = new MyDriverBridge();
		driverManagerBridge.setDriver(new MySqlDriver());
		driverManagerBridge.execute();
		driverManagerBridge.setDriver(new OracleDriver());
		driverManagerBridge.execute();
	}

}
