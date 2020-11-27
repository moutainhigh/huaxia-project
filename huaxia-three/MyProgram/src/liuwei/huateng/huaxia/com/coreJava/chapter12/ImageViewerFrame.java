/**
 * Title: ImageViewerFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月17日上午10:47:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter12;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @class_name:ImageViewerFrame2020年1月17日
 * @comments:
 * @param: 测试fileOpen选择器
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月17日上午10:47:27
 */
public class ImageViewerFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private JLabel label;
	private JFileChooser chooser;
	/**
	 * @throws HeadlessException
	 */
	public ImageViewerFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		this.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		//set up menu bar
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		JMenuItem openItem = new JMenuItem("open");
		menu.add(openItem);
		openItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				chooser.setCurrentDirectory(new File("."));
				//show file chooser dialog
				int result = chooser.showOpenDialog(ImageViewerFrame.this);
				if(result == JFileChooser.APPROVE_OPTION)
				{
					String name =chooser.getSelectedFile().getPath();
					label.setIcon(new ImageIcon(name));
					pack();
				}
			}
			
		});
		JMenuItem exitItem =new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		label =new JLabel();
		this.add(label);
		//set up file chooser
		chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files","jpg","jpeg","gif");
		chooser.setFileFilter(filter);
		
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
	 *@Date: 2020年1月17日上午10:47:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new ImageViewerFrame();
//		frame.add(new CalculatorPanel());
		frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		frame.setTitle("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
