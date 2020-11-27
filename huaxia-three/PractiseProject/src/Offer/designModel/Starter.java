/**
 * Title: Starter.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月29日下午4:18:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Starter2020年9月29日
 * @comments:外观模式
 * @param:	启动面板类
 * car begin startup
engine startup...
dashboard startup...
 start check finish...
car start finished
************************************
car begin shutdown
engine shutdown...
dashboard shutdown...
 shutdown check finish...
car shutdown finished
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月29日下午4:18:33
 */
public class Starter {
	private Dashboard dashboard;
	private Engine engine;
	private SelfCheck selfCheck;
	/**
	 * Constructor
	 */
	public Starter() {
		// TODO Auto-generated constructor stub
		this.dashboard = new Dashboard();
		this.engine = new Engine();
		this.selfCheck = new SelfCheck();
	}
	/**
	 * @Title: startup
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年9月29日下午4:21:58
	 */
	public void startup(){
		System.out.println("car begin startup");
		engine.startup();
		dashboard.startup();
		selfCheck.startup();
		System.out.println("car start finished");
	}
	/**
	 * @Title: shutdown
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年9月29日下午4:23:04
	 */
	public void shutdown(){
		System.out.println("car begin shutdown");
		engine.shutdown();
		dashboard.shutdown();
		selfCheck.shutdown();
		System.out.println("car shutdown finished");
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月29日下午4:18:33
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Starter starter = new Starter();
		starter.startup();
		System.out.println("***************************** *******");
		starter.shutdown();
	}
}
