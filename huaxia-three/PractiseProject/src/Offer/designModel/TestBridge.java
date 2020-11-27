/**
 * Title: TestBridge.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月30日上午11:24:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestBridge2020年9月30日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月30日上午11:24:44
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
	 *@Date: 2020年9月30日上午11:24:44
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
