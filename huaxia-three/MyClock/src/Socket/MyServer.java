package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer {
	private BufferedReader reader ;
	private PrintWriter writer;
	private ServerSocket server;
	private Socket socket;
	void getServer(){
		try{
			server = new ServerSocket(8868);
			System.out.println("服务器套接字已经创建成功");
			while(true){
				System.out.println("等待客户机的连接");
				socket = server.accept();
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(),true);
				getClientMessage();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	void getClientMessage(){
		System.out.println("服务器执行了");
		try{
			while(true){
//				writer.println("欢迎您连接服务端");
//				writer.flush();
			
				String iin = reader.readLine();
				
				if(iin != null && iin.length()>0){
					System.out.println("服务器端收到消息："+iin);
				}
				Scanner in = new Scanner(System.in);
				String s = in.next();
//				for(int i =0 ;i<10000;i++)
				writer.println(s);
				writer.flush();
				
//				System.out.println("服务器信息接收："+reader.readLine());
//				writer.println("欢迎您连接服务端");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			if(reader != null){
				reader.close();
			}
			if(writer != null){
				writer.close();
			}
			if(socket != null){
				socket.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		MyServer myserver = new MyServer();
		myserver.getServer();
	}
}
