/**
 * Title: Player.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������10:25:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern;

/**
 * @class_name:Player2020��1��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������10:25:44
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
	 *@Date: 2020��1��7������10:28:01
	 */
	public void next(int index){
		if(successor != null){
			successor.handle(index);
		}else{
		 System.out.println("��Ϸ����");
		}
	}
	public Player getSuccessor() {
		return successor;
	}
	public void setSuccessor(Player successor) {
		this.successor = successor;
	}
	
}
