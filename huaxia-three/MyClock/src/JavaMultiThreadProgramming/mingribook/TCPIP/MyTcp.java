/**
 * Title: MyTcp.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月25日上午10:49:00
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.TCPIP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @class_name:MyTcp2019年10月25日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月25日上午10:49:00
 */
public class MyTcp {
	private BufferedReader reader;
	private ServerSocket server;
	private Socket socket;
	/**
	 * @Title: getserver
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019年10月25日上午11:22:16
	 */
	void getserver(){
		try{
			server = new ServerSocket(8998);
			System.out.println("服务套接字已经创建成功");
			while(true){
				System.out.println("等待客户机的链接");
				socket = server.accept();
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				getClientMessage();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * @Title: getClientMessage
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019年10月25日上午11:22:20
	 */
	private void getClientMessage(){
		try{
			while(true){
				System.out.println("客户机："+reader.readLine());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			if(reader != null){
				reader.close();
			}
			if(socket != null){
				socket.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public MyTcp() {
		// TODO Auto-generated constructor stub
		
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月25日上午10:49:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTcp tcp= new MyTcp();
			tcp.getserver();
	}

}
