/**
 * Title: GamePlayerProxy.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������3:44:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ProxyPattern;

import java.util.Date;

/**
 * @class_name:GamePlayerProxy2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������3:44:01
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
	 *@Description: TODO ��¼���ʱ��
	 *@author: LiuWei
	 *@Date: 2020��1��6������3:46:21
	 */
	public void log(){
		System.out.println("���ʱ��"+new Date().toString());
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
	 *@Date: 2020��1��6������3:47:54
	 */
	private void count()
	{
		System.out.println("��1������50Сʱ��");
	}
}
