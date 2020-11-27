/**
 * Title: SingleTo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��6������3:43:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Volatile;

/**
 * @class_name:SingleTo2019��12��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��6������3:43:47
 */
public class SingleTo {

	private byte[] data = new byte[1024];
	/**
	 * 
	 * @class_name:Holder2019��12��6��
	 * @comments:
	 * @param:
	 * @return:
	 * @author ��ΰ/liuwei
	 * @createtime:2019��12��6������3:44:47
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
	 *@Date: 2019��12��6������3:43:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
