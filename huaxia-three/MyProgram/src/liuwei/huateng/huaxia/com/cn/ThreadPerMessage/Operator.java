/**
 * Title: Operator.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月16日上午11:27:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ThreadPerMessage;

/**
 * @class_name:Operator2019年12月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月16日上午11:27:07
 */
public class Operator {
	
	/**
	 * 
	 */
	public Operator() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月16日上午11:27:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			call(i+"");
		}
	}
	/**
	 * @Title: call
	 *@Description: TODO
	 *@param business
	 *@author: LiuWei
	 *@Date: 2019年12月16日上午11:27:28
	 */
	private static void call(String business){
		TaskHandler taskHandler = new TaskHandler(new Request(business));
		new Thread(taskHandler,business).start();
	}
}
