/**
 * Title: ChatServer.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��16������2:41:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ThreadPerMessage;

import java.io.IOException;
import java.net.ServerSocket;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

/**
 * @class_name:ChatServer2019��12��16��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��16������2:41:47
 */
public class ChatServer {
	private int port;
	private ThreadPool threadPool;
	//�����Socket
	private ServerSocket serverSocket;
	public ChatServer(int port) {
		// TODO Auto-generated constructor stub
		this.port = port;
	}

	/**
	 * 
	 */
	public ChatServer() {
		// TODO Auto-generated constructor stub
		this(13312);
	}
	
	public void startServer() throws IOException{
		//�����̳߳�
//		this.threadPool = new BasicThreadPool(1,4,2,1000);	
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��16������2:41:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
