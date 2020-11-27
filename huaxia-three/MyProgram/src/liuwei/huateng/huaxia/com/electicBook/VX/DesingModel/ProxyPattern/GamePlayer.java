/**
 * Title: GamePlayer.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������3:37:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ProxyPattern;

/**
 * @class_name:GamePlayer2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������3:37:26
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
		System.out.println(this.name +"�ڴ��!");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ProxyPattern.IGamePlayer#upGrade()
	 */
	@Override
	public void upGrade() {
		// TODO Auto-generated method stub
		System.out.println(this.name +"�ɹ�����1����");
	}

}
