/**
 * Title: Handler.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日上午9:53:59
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern;

/**
 * @class_name:Handler2020年1月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日上午9:53:59
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
	 *@Description: TODO 处理方法
	 *@author: LiuWei
	 *@Date: 2020年1月7日上午9:55:21
	 */
	public abstract void handleRequest();
	public Handler getSuccessor() {
		return successor;
	}
	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}
	
}
