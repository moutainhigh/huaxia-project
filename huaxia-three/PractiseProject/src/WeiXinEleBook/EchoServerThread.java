/**
 * Title: EchoServer.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������1:46:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @class_name:EchoServer2020��9��11��
 * @comments: ���̵߳Ĵ�����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������1:46:15
 */
public class EchoServerThread {

	/**
	 * Constructor
	 */
	public EchoServerThread() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��11������1:46:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		boolean listening = true;
		try{
			serverSocket = new ServerSocket(1111);
		}catch(IOException e){
			System.err.println("Count not listen port:1111");
			System.exit(1);
		}
		while(listening)
		{
			try {
				new EchoMultiServerThread(serverSocket.accept()).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
