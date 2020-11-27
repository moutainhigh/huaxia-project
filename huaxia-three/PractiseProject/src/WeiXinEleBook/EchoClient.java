/**
 * Title: EchoClient.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午2:02:08
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @class_name:EchoClient2020年9月11日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午2:02:08
 */
public class EchoClient {

	/**
	 * Constructor
	 */
	public EchoClient() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午2:02:08
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try{
				echoSocket = new Socket("localhost",1111);
				out  = new PrintWriter(echoSocket.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()))	;
				System.out.println(in.readLine());
				System.out.println(in.readLine());
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				String userInput; 
				while((userInput = stdIn.readLine())!= null)
				{
					out.println(userInput);
					System.out.println(in.readLine());
				}
		}catch(UnknownHostException e){
			System.err.println("Don not konw about host:localhost.");
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				echoSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
