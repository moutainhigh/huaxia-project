/**
 * Title: MyDriverBridge.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��30������11:23:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

import java.sql.DriverManager;

/**
 * @class_name:MyDriverBridge2020��9��30��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��30������11:23:23
 */
public class MyDriverBridge extends DriverManagerBridge {

	/**
	 * Constructor
	 */
	public MyDriverBridge() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: execute
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��30������11:24:09
	 */
	public void execute(){
		getDriver().executeSQL();
	}
}
