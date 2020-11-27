/**
 * Title: ImageViewerFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月25日下午3:21:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter1;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @class_name:ImageViewerFrame2019年12月25日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月25日下午3:21:04
 */
public class ImageViewerFrame extends JFrame {

	private JLabel label;
	private JFileChooser chooser;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	
	/**
	 * @throws HeadlessException
	 */
	public ImageViewerFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		this.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		label = new JLabel("Hello World!");
		this.add(label);
		//set up the file chooser
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		//set up the menu bar
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("file");
		menuBar.add(menu);
		JMenuItem ipenItem = new JMenuItem("Open");
		menuBar.add(ipenItem);
		ipenItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int result = chooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION)
				{
					String name = chooser.getSelectedFile().getPath();
//					label.setName(name);
					label.setText(name);
					label.setIcon(new ImageIcon(name));
					
				}
			}
		});
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}

	/**
	 * @param arg0
	 */
	public ImageViewerFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public ImageViewerFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ImageViewerFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月25日下午3:21:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
