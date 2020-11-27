/**
 * Title: ChatServer.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月16日下午2:41:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ThreadPerMessage;

import java.io.IOException;
import java.net.ServerSocket;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

/**
 * @class_name:ChatServer2019年12月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月16日下午2:41:47
 */
public class ChatServer {
	private int port;
	private ThreadPool threadPool;
	//服务端Socket
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
		//创建线程池
//		this.threadPool = new BasicThreadPool(1,4,2,1000);	
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月16日下午2:41:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
