/**
 * Title: Contral.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月21日上午10:53:00
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.Test;

/**
 * @class_name:Contral2019年11月21日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月21日上午10:53:00
 */
public class Contral {

	/**
	 * 
	 */
	public Contral() {
		// TODO Auto-generated constructor stub
	}
	
	public void invoke(int flag){
		TestPrint  user = new TestPrint();
		switch(flag){
		case 0:
			try {
				user.use(TestPrint.class.getMethod("printA"));
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				user.use(TestPrint.class.getMethod("printB", null));
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
}
