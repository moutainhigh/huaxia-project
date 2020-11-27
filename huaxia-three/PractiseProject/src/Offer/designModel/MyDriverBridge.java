/**
 * Title: MyDriverBridge.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月30日上午11:23:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

import java.sql.DriverManager;

/**
 * @class_name:MyDriverBridge2020年9月30日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月30日上午11:23:23
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
	 *@Date: 2020年9月30日上午11:24:09
	 */
	public void execute(){
		getDriver().executeSQL();
	}
}
