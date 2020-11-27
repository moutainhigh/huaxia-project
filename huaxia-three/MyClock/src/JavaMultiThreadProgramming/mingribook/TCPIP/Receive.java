/**
 * Title: Receive.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月25日下午3:03:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.TCPIP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * @class_name:Receive2019年10月25日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月25日下午3:03:05
 */
public class Receive extends JFrame implements Runnable, ActionListener {
	int port;
	InetAddress group = null;
	MulticastSocket socket = null;
	JButton ince  = new JButton("开始接受");
	JButton stop = new JButton("停止接受");
	JTextArea inceAr = new JTextArea(10,10);
	JTextArea inced = new JTextArea(10,10);
	Thread thread;
	boolean b = false;
	/**
	 * @throws HeadlessException
	 */
	public Receive() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super("广播数据报");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		thread = new Thread(this);
		ince.addActionListener(this);
		stop.addActionListener(this);
		inceAr.setForeground(Color.blue);
		JPanel north = new JPanel();
		north.add(ince);
		north.add(stop);
		this.add(north,BorderLayout.NORTH);
		JPanel center  = new JPanel();
		center.add(inceAr);
		center.add(inced);
		this.add(center,BorderLayout.CENTER);
		validate();
		port = 9898;
		try{
			group = InetAddress.getByName("106.128.31.230");
			socket = new MulticastSocket(port);
			socket.joinGroup(group);
		}catch(Exception e){
			e.printStackTrace();
		}
		this.setBounds(100,50,260,380);
		this.setVisible(true);
		
	}

	/**
	 * @param arg0
	 */
	public Receive(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public Receive(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public Receive(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == ince){
			ince.setBackground(Color.red);
			stop.setBackground(Color.yellow);
			if(!(thread.isAlive())){
				thread = new Thread(this);
			}
			thread.start();
			b = false;
		}
		if(e.getSource() == stop){
			ince.setBackground(Color.yellow);
			stop.setBackground(Color.red);
			b = true;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			byte data[] = new byte[1024];
			DatagramPacket packet = null;
			packet = new DatagramPacket(data,data.length,group,port);
			try{
				socket.receive(packet);
				String message = new String(packet.getData(),0,packet.getLength());
				inceAr.setText("正在接受的内容：\n"+message);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(b == true){
				break;
			}
		}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月25日下午3:03:05
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Receive rec = new Receive();
		rec.setSize(460,200);
	}

}
