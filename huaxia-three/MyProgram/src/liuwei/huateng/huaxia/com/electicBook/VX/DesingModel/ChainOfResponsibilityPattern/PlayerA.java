/**
 * Title: PlayerA.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日上午10:28:25
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern;

/**
 * @class_name:PlayerA2020年1月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日上午10:28:25
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
			System.out.println("PlayerA 喝酒");
		}else{
			System.out.println("PlayerA 把花向下传递！");
			next(i);
		}
	}

}
