/**
 * Title: Client.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午2:18:59
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StatePattern;

/**
 * @class_name:Client2020年1月8日
 * @comments: 状态模式
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午2:18:59
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
	 *@Date: 2020年1月8日下午2:18:59
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Context context = new Context();
		if(context.getCurrentState() != null){
			System.out.println(context.getCurrentState().toString());
		}
		context.handle1();
//		if(context.getCurrentState() != null){
//			System.out.println(context.getCurrentState().toString());
//		}
		context.handle2();
//		if(context.getCurrentState() != null){
//			System.out.println(context.getCurrentState().toString());
//		}
	}

}
