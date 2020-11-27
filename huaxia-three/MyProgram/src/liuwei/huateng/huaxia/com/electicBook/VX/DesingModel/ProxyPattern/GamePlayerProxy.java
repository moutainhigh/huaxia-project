/**
 * Title: GamePlayerProxy.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午3:44:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ProxyPattern;

import java.util.Date;

/**
 * @class_name:GamePlayerProxy2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午3:44:01
 */
public class GamePlayerProxy implements IGamePlayer {
	private IGamePlayer player = null;
	/**
	 * 
	 */
	public GamePlayerProxy(IGamePlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	/**
	 * @Title: log
	 *@Description: TODO 记录打怪时间
	 *@author: LiuWei
	 *@Date: 2020年1月6日下午3:46:21
	 */
	public void log(){
		System.out.println("打怪时间"+new Date().toString());
	}
	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ProxyPattern.IGamePlayer#killBoss()
	 */
	@Override
	public void killBoss() {
		// TODO Auto-generated method stub
		this.log();
		player.killBoss();
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ProxyPattern.IGamePlayer#upGrade()
	 */
	@Override
	public void upGrade() {
		// TODO Auto-generated method stub
		player.upGrade();
		this.count();
	}
	/**
	 * @Title: count
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年1月6日下午3:47:54
	 */
	private void count()
	{
		System.out.println("升1级消耗50小时！");
	}
}
