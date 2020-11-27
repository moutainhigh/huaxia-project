/**
 * Title: Handler.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������9:53:59
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern;

/**
 * @class_name:Handler2020��1��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������9:53:59
 */
public abstract class Handler {

	/**
	 * 
	 */
	public Handler() {
		// TODO Auto-generated constructor stub
	}
	private Handler successor;
	/**
	 * @Title: handleRequest
	 *@Description: TODO ������
	 *@author: LiuWei
	 *@Date: 2020��1��7������9:55:21
	 */
	public abstract void handleRequest();
	public Handler getSuccessor() {
		return successor;
	}
	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}
	
}
