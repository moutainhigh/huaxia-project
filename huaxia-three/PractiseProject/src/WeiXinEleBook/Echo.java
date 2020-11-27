/**
 * Title: EchoServer.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午1:46:15
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
 * @class_name:EchoServer2020年9月11日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午1:46:15
 */
public class Echo {

	/**
	 * Constructor
	 */
	public Echo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午1:46:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try{
			serverSocket = new ServerSocket(1111);
		}catch(IOException e){
			System.err.println("Count not listen port:1111");
			System.exit(1);
		}
		Socket incoming = null;
		while(true){
			try {
				incoming = serverSocket.accept();
				out =new PrintWriter(incoming.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
				out.println("Hello...");
				out.println("Enter BYE to exit");
				out.flush();
				while(true){
					String str = in.readLine();
					if(str == null){
						break;
					}else{
						out.println("Echo:"+str);
						out.flush();
						if(str.trim().equalsIgnoreCase("BYE")) 
							break;
					}
				}
				out.close();
				in.close();
				incoming.close();
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
