/**
 * Title: Request.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��16������11:19:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ThreadPerMessage;

/**
 * @class_name:Request2019��12��16��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��16������11:19:55
 */
public class Request {

	private  String business;
	
	/**
	 * 
	 */
	public Request(String business) {
		// TODO Auto-generated constructor stub
		this.business = business;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��16������11:19:55
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString(){
		return business;
	}
}
