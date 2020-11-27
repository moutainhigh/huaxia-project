package Socket.UDP;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sender2 extends JPanel{
	private static String name = "刘伟";
	private static DatagramSocket send;//套接字UDP
	//缓冲区
	private static byte[] buff;
	//接受的包
	private static DatagramPacket packetReceived;
	//聊天中对方的IP地址
	private static InetAddress inetAddress;
	static TextArea textArea;
	TextField textFiled ;
	public Sender2() throws Exception{
		buff = new byte[1024];
		packetReceived = new DatagramPacket(buff,buff.length);
		inetAddress = InetAddress.getByName("localhost");
		//端口号的，不同端口号可以进行通信
		send = new DatagramSocket(4000);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300,200));
		textArea = new TextArea(10,30);
		panel.add(textArea,BorderLayout.CENTER);
		JLabel jlabel = new JLabel();
		 textFiled = new TextField();
		textFiled.setPreferredSize(new Dimension(250,20));
		textFiled.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					String str = ((TextField)arg0.getSource()).getText();
					try {
						send(str);
						textArea.setText(textArea.getText()+"\r\n"+name+"说："+"\r\n"+str);
						textArea.setCaretPosition(textArea.getText().length());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
			
		});
		jlabel.setText(name);
		this.add(jlabel,BorderLayout.NORTH);
		this.add(panel,BorderLayout.CENTER);
		this.add(textFiled,BorderLayout.SOUTH);
		
	}
	//发送信息
	private  String send(String sendString) throws IOException{
	
		byte[] buff = sendString.getBytes();
		//数据报文
		DatagramPacket datagramPacket = new DatagramPacket(buff,buff.length,inetAddress,5000);
		//发送
		send.send(datagramPacket);
		return sendString;
	}
	//接受信息
	private  String receive() throws IOException{
		send.receive(packetReceived);
		String str = new String(packetReceived.getData(),0,packetReceived.getLength());
		return str;
	}
	static Sender2 sender ;
	public static void main(String args[]){
		
		try{
			 sender = new Sender2();
			
			JFrame w = new JFrame();
			w.setSize(300,300);
			Container c= w.getContentPane();
			c.add(sender);
			w.setVisible(true);
			
			new Thread(){
				@Override
				public void run(){
					while(true){
						String msg= null;
						try{
							msg =sender.receive();
							textArea.setText(textArea.getText()+"\r\n"+"魏丹丹"+"说："+"\r\n"+msg);
							textArea.setCaretPosition(textArea.getText().length());
						}catch(IOException e){
							e.printStackTrace();
						}
						if(msg.equals("bye")){
							send.close();
							System.out.println("本次聊天结束！");
							System.exit(0);
						}
					}
				}
			}.start();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
