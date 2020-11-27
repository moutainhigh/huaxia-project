/**
 * Title: SingleTo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月6日下午3:43:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Volatile;

/**
 * @class_name:SingleTo2019年12月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月6日下午3:43:47
 */
public class SingleTo {

	private byte[] data = new byte[1024];
	/**
	 * 
	 * @class_name:Holder2019年12月6日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2019年12月6日下午3:44:47
	 */
	private static class Holder
	{
		private static SingleTo instance = new SingleTo();
	}
	/**
	 * 
	 */
	public SingleTo() {
		// TODO Auto-generated constructor stub
	}
	public static SingleTo getInstance(){
		return Holder.instance;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月6日下午3:43:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
