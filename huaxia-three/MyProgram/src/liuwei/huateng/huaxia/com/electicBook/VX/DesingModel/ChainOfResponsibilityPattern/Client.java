/**
 * Title: Client.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日上午9:58:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern;

/**
 * @class_name:Client2020年1月7日
 * @comments:责任链模式的应用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日上午9:58:09
 */
public class Client {

	/**
	 * 
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月7日上午9:58:09
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Handler h1 = new ConcreteHandler();
		Handler h2 = new ConcreteHandler();
		h1.setSuccessor(h2);
		h1.handleRequest();
	}

}
