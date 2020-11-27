/**
 * Title: PlayerA.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������10:28:25
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern;

/**
 * @class_name:PlayerA2020��1��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������10:28:25
 */
public class PlayerA extends Player {

	/**
	 * 
	 */
	public PlayerA(Player successor) {
		// TODO Auto-generated constructor stub
		this.setSuccessor(successor);
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern.Player#handle(int)
	 */
	@Override
	public void handle(int i) {
		// TODO Auto-generated method stub
		if(i==1){
			System.out.println("PlayerA �Ⱦ�");
		}else{
			System.out.println("PlayerA �ѻ����´��ݣ�");
			next(i);
		}
	}

}
