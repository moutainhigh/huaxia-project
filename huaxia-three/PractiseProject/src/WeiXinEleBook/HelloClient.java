/**
 * Title: HelloClient.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日上午11:25:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @class_name:HelloClient2020年9月11日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日上午11:25:49
 */
public class HelloClient {

	/**
	 * Constructor
	 */
	public HelloClient() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日上午11:25:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket hellosocket = null;
		BufferedReader in = null;
		try{
			hellosocket = new Socket("localhost",9999);
			in = new BufferedReader(new InputStreamReader(hellosocket.getInputStream()));
			System.out.println(in.readLine());
		}catch(UnknownHostException e){
			System.out.println("Don not konw about host:localhost!");
			System.exit(1);
		}catch(IOException e){
			System.err.println("Could not get I/O  for the connection.");
			System.exit(1);
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				hellosocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
