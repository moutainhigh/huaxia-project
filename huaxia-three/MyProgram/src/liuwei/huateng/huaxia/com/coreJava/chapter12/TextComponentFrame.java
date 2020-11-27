/**
 * Title: TextComponentFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月14日下午2:05:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter12;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @class_name:TextComponentFrame2020年1月14日
 * @comments:  测试组件
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月14日下午2:05:50
 */
public class TextComponentFrame extends JFrame {
	public static final int TEXTAREA_ROWS = 8;
	public static final int TEXTAREA_COLUMN = 20;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	/**
	 * @throws HeadlessException
	 */
	public TextComponentFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		final JTextField textField = new JTextField();
		final JPasswordField passwordField = new JPasswordField();
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2,2));
		northPanel.add(new JLabel("User name:",SwingConstants.RIGHT));
		northPanel.add(textField);
		northPanel.add(new JLabel("Password:"+SwingConstants.RIGHT));
		northPanel.add(passwordField);
		this.add(northPanel,BorderLayout.NORTH);
		final JTextArea textArea = new JTextArea(TEXTAREA_ROWS,TEXTAREA_COLUMN);
		JScrollPane scrollPane = new JScrollPane(textArea);
		this.add(scrollPane,BorderLayout.CENTER);
		//add button to append text into the text area
		JPanel southPanel = new JPanel();
		JButton insertButton  = new JButton("Insert");
		southPanel.add(insertButton);
		insertButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textArea.append("User name:"+textField.getText()+ " PassWord: "+new String(passwordField.getPassword())+"\n");
			
			}
			
		});
		this.add(southPanel,BorderLayout.SOUTH);
		pack();
	}

	/**
	 * @param arg0
	 */
	public TextComponentFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public TextComponentFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TextComponentFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月14日下午2:05:50
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				JFrame frame = new TextComponentFrame();
//				frame.add(new TextComponentFrame());
				frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
				frame.setTitle("test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
	}

}
