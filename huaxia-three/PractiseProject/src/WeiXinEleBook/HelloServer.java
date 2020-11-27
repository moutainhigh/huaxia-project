/**
 * Title: HelloServer.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������11:18:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @class_name:HelloServer2020��9��11��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������11:18:51
 */
public class HelloServer {

	/**
	 * Constructor
	 */
	public HelloServer() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��11������11:18:51
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serversocket = null;
		PrintWriter out = null;
		try{
		serversocket = new ServerSocket(9999);
		}catch(IOException e){
			System.err.println("Could not listen prot:9999.");
			System.exit(1);
		}
		Socket clientsocket = null;
		try{
			clientsocket = serversocket.accept();
		}catch(IOException e){
			System.err.println("Accept failed.");
			System.exit(1);
		}
		try {
			out = new PrintWriter(clientsocket.getOutputStream(),true);
			out.println("Hello world!");
			clientsocket.close();
			serversocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
