package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

	private PrintWriter writer;
	private BufferedReader reader;
	Socket socket;
	public void connet(){
		System.out.println("尝试连接");
		try{
			socket = new Socket("127.0.0.1",8868);
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("连接成功");
//			while(true)
//			writer.println("您好，来自客户端的连接");
			getServerMessage();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	void getServerMessage(){
		System.out.println("执行了");
		try{
			while(true){
				Scanner in = new Scanner(System.in);
				String s = in.next();
//				for(int i =0 ;i<10000;i++)
				writer.println(s);
				writer.flush();
				String iin = reader.readLine();
				if(iin != null && iin.length()>0){
					System.out.println("客户端端收到消息："+iin);
				}
			
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
		MyClient myClient =new MyClient();
		myClient.connet();
	}
}
