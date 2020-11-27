/**
 * Title: DialogFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月14日下午5:24:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter12;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @class_name:DialogFrame2020年1月14日
 * @comments:测试弹出框和菜单栏
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月14日下午5:24:01
 */
public class DialogFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private AboutDialog dialog;
	/**
	 * @throws HeadlessException
	 */
	public DialogFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("file");
		menuBar.add(fileMenu);
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(dialog == null)
					dialog = new AboutDialog(DialogFrame.this);
				dialog.setVisible(true);;
			}
			
		});
		fileMenu.add(aboutItem);
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		fileMenu.add(exitItem);
	}

	/**
	 * @param arg0
	 */
	public DialogFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public DialogFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DialogFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月14日下午5:24:01
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new DialogFrame();
//		frame.add(new CalculatorPanel());
		frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		frame.setTitle("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
