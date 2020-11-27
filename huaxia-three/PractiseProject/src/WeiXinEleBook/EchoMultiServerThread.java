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
 * @comments: ���̵߳ĵ����������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������1:46:15
 */
public class EchoMultiServerThread extends Thread{
	private Socket socket = null;
	/**
	 * Constructor
	 */
	public EchoMultiServerThread(Socket socket) {
		// TODO Auto-generated constructor stub
		super("EchoMultiServerThead");
		this.socket = socket;
	}
	@Override
	public void run(){
		// TODO Auto-generated method stub
		PrintWriter out = null;
		BufferedReader in = null;
		while(true){
			try {
				out =new PrintWriter(socket.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��11������1:46:15
	 */
	public static void main(String[] args) {
		
	}
}
