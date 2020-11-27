/**
 * Title: PlatFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月13日下午4:32:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter11;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * @class_name:PlatFrame2020年1月13日
 * @comments:改变外观的测试类
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日下午4:32:19
 */
public class PlatFrame extends JFrame{
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	/**
	 * @throws HeadlessException
	 */
	public PlatFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		buttonPanel = new JPanel();
		buttonPanel.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for(UIManager.LookAndFeelInfo info :infos)
		{
			makeButton(info.getName(),info.getClassName());
		}
		this.add(buttonPanel);
		pack();
	}
	/**
	 * @Title: makeButton
	 *@Description: TODO 制作一个按钮
	 *@param name
	 *@param className
	 *@author: LiuWei
	 *@Date: 2020年1月13日下午4:33:12
	 */
	private void makeButton(String name, String className)
	{
		//add button to panel
		JButton button = new JButton(name);
		buttonPanel.add(button);
		//set button action
		button.addActionListener(new TestAction(name,className));
	}
	/**
	 * @param arg0
	 */
	public PlatFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public PlatFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public PlatFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月13日下午4:32:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new PlatFrame();
		frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		frame.setTitle("pLatTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	/**
	 * 
	 * @class_name:TestAction2020年1月13日
	 * @comments: 事件响应类
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年1月13日下午4:45:25
	 */
	class TestAction implements ActionListener{
		String name;
		String className;

		public TestAction(String name, String className){
			this.name = name ;
			this.className = className;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try{
				UIManager.setLookAndFeel(className);
				SwingUtilities.updateComponentTreeUI(PlatFrame.this);
				pack();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}

