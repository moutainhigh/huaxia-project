/**
 * Title: Client.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������2:18:59
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StatePattern;

/**
 * @class_name:Client2020��1��8��
 * @comments: ״̬ģʽ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������2:18:59
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
	 *@Date: 2020��1��8������2:18:59
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
