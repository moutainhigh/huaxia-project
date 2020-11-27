package SimpleBook;

import java.net.InetAddress;
/**
 * 测试IP地址
 * @author liuwei
 *
 */
public class GetIp {
	public static void main(String args[]){
		InetAddress address = null;
		try{
//			address = InetAddress.getByName("www.javatutorial.com");
			address = InetAddress.getByName("localhost");
		}catch(Exception e){
			e.printStackTrace();
			System.exit(2);
		}
		System.out.println(address.getHostName()+"="+address.getHostAddress());
	}
}
