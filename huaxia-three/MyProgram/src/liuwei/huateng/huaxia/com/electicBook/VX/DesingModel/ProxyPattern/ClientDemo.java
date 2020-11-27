/**
 * Title: ClientDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午3:48:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ProxyPattern;

/**
 * @class_name:ClientDemo2020年1月6日
 * @comments:测试代理模式，代理模式就是，在原有的基础上继续组合更大的对象。
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午3:48:38
 */
public class ClientDemo {
	
	/**
	 * 
	 */
	public ClientDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月6日下午3:48:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IGamePlayer player = new GamePlayer("李逍遥");
		IGamePlayer proxy = new GamePlayerProxy(player);
		proxy.killBoss();
		proxy.upGrade();
	}

}
