/**
 * Title: DataExchangeFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月15日上午9:55:45
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter12;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @class_name:DataExchangeFrame2020年1月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月15日上午9:55:45
 */
public class DataExchangeFrame extends JFrame {
	public static final int TEXT_ROWS = 20;
	public static final int TEXT_COLUMNS = 40;
	private PasswordChooser dialog = null;
	private JTextArea textArea;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	/**
	 * @throws HeadlessException
	 */
	public DataExchangeFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		JMenuBar mbar = new JMenuBar();
		this.setJMenuBar(mbar);
		JMenu fileMenu = new JMenu("File");
		mbar.add(fileMenu);
		//add Connect and exit menu item
		JMenuItem connectItem = new JMenuItem("Connection");
		connectItem.addActionListener(new ConnectAction());
		fileMenu.add(connectItem);
		JMenuItem exitItem= new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		fileMenu.add(exitItem);
		textArea= new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
		add(new JScrollPane(textArea),BorderLayout.CENTER);
		pack();
	}
	private class ConnectAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// if first time,consttruct dialog
			if(dialog == null)
				dialog = new PasswordChooser();
			dialog.setUser(new User("yourname",null)); 
			if(dialog.showDialog(DataExchangeFrame.this, "Connect"))
			{
				User u= dialog.getUser();
				textArea.append("User name ="+u.getUsername()+",password="+new String(u.getPassword())+"\n");
			}
		}
	
		
	}
	/**
	 * @param arg0
	 */
	public DataExchangeFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public DataExchangeFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DataExchangeFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月15日上午9:55:45
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new DataExchangeFrame();
//		frame.add(new CalculatorPanel());
		frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		frame.setTitle("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
