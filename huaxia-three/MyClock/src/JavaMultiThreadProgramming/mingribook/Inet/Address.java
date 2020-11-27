/**
 * Title: Address.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月17日上午10:58:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.Inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @class_name:Address2019年10月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月17日上午10:58:15
 */
public class Address {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月17日上午10:58:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InetAddress ip;
		try{
			ip = InetAddress.getLocalHost();
			String localname = ip.getHostName();
			String localip = ip.getHostAddress();
			System.out.println("本机名："+localname);
			System.out.println("本机IP地址:"+localip);
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
	}

}
