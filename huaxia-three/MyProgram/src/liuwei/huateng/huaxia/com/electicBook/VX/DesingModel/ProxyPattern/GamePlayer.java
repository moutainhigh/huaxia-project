/**
 * Title: GamePlayer.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午3:37:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ProxyPattern;

/**
 * @class_name:GamePlayer2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午3:37:26
 */
public class GamePlayer implements IGamePlayer {
	private String name = "";
	/**
	 * 
	 */
	public GamePlayer(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ProxyPattern.IGamePlayer#killBoss()
	 */
	@Override
	public void killBoss() {
		// TODO Auto-generated method stub
		System.out.println(this.name +"在打怪!");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ProxyPattern.IGamePlayer#upGrade()
	 */
	@Override
	public void upGrade() {
		// TODO Auto-generated method stub
		System.out.println(this.name +"成功升了1级！");
	}

}
