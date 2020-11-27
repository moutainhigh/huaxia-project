/**
 * Title: Weather.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月25日下午2:12:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.TCPIP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @class_name:Weather2019年10月25日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月25日下午2:12:31
 */
public class Weather extends Thread {
	String weather = "节目预报：八点由大型晚会，请收听";
	int port = 9898;
	InetAddress iaddress =null;
	MulticastSocket socket= null; //声明多点广播套接字
	/**
	 * 
	 */
	public Weather() {
		// TODO Auto-generated constructor stub
		try{
			iaddress = InetAddress.getByName("106.128.31.230");
			socket = new MulticastSocket(port);
			socket.setTimeToLive(1);//发送范围是本地网络
			socket.joinGroup(iaddress);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @param arg0
	 */
	public Weather(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public Weather(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public Weather(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public Weather(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public Weather(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public Weather(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public Weather(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月25日下午2:12:31
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Weather w = new Weather();
		w.start();
	}
	
	public void run(){
		while(true){
			DatagramPacket packet = null;
			byte data[] = weather.getBytes();
			packet = new DatagramPacket(data,data.length,iaddress,port);
			System.out.println(new String(data));
			 try {
				socket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
