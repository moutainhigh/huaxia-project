/**
 * Title: Player.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日上午10:25:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern;

/**
 * @class_name:Player2020年1月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日上午10:25:44
 */
public abstract class Player {
	private Player successor;
	public abstract void handle(int i);
	/**
	 * 
	 */
	protected void  Player(Player aSuccessor) {
		// TODO Auto-generated constructor stub
		this.successor = aSuccessor;
	}
	/**
	 * @Title: next
	 *@Description: TODO
	 *@param index
	 *@author: LiuWei
	 *@Date: 2020年1月7日上午10:28:01
	 */
	public void next(int index){
		if(successor != null){
			successor.handle(index);
		}else{
		 System.out.println("游戏结束");
		}
	}
	public Player getSuccessor() {
		return successor;
	}
	public void setSuccessor(Player successor) {
		this.successor = successor;
	}
	
}
