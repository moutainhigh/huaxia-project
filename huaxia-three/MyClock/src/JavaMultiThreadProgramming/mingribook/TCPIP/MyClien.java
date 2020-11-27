/**
 * Title: MyClien.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月25日下午1:41:11
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.TCPIP;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 * @class_name:MyClien2019年10月25日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月25日下午1:41:11
 */
public class MyClien extends JFrame {
	private PrintWriter writer;
	Socket socket ;
	private JTextArea ta = new JTextArea();
	private JTextField tf = new JTextField();
	Container cc;
	/**
	 * @throws HeadlessException
	 */
	public MyClien() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public MyClien(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public MyClien(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cc = this.getContentPane();
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		scrollPane.setViewportView(ta);
		cc.add(tf,"South");
		tf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				writer.println(tf.getText());
				ta.append(tf.getText()+"\n");
				ta.setSelectionEnd(ta.getText().length());
				tf.setText("");
			}
		});
	}

	/**
	 * @Title: connect
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019年10月25日下午2:00:32
	 */
	private void connect(){
		ta.append("尝试链接\n");
		try{
			socket = new Socket("127.0.0.1",8998);
			writer = new PrintWriter(socket.getOutputStream(),true);
			ta.append("完成链接\n");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * @param arg0
	 * @param arg1
	 */
	public MyClien(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月25日下午1:41:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClien clien = new MyClien("向服务器送数据");
		clien.setSize(200,200);
		clien.setVisible(true);
		clien.connect();
	}

}
