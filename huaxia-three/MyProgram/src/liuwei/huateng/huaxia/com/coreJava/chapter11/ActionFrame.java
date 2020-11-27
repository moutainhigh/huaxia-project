/**
 * Title: ActionFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月13日下午5:01:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter11;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * @class_name:ActionFrame2020年1月13日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日下午5:01:55
 */
public class ActionFrame extends JFrame {
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	/**
	 * @throws HeadlessException
	 */
	public ActionFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		this.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		buttonPanel = new JPanel();
		//define actions
		Action yellowAction = new ColorAction("Yellow",new ImageIcon("C:/Users/User/Desktop/temp/blank.gif"), Color.YELLOW);
		Action buleAction = new ColorAction("Blue",new ImageIcon("C:/Users/User/Desktop/temp/load.gif"), Color.BLUE);
		Action redAction = new ColorAction("Red",new ImageIcon("C:/Users/User/Desktop/temp/loading.gif"), Color.RED);
		buttonPanel.add(new JButton(yellowAction));
		buttonPanel.add(new JButton(buleAction));
		buttonPanel.add(new JButton(redAction));
		//add panel to frame
		add(buttonPanel);
		//associate the Y,B,and R keys with names
		InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
		//associate the names with actions
		ActionMap amap = buttonPanel.getActionMap();
		amap.put("panel.yellow", yellowAction);
		amap.put("panel.blue", buleAction);
		amap.put("panel.red", redAction);
	}
	/**
	 * 
	 * @class_name:ColorAction2020年1月13日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年1月13日下午5:06:33
	 */
	public class ColorAction extends AbstractAction
	{
		public ColorAction(String name,Icon icon,Color c){
			putValue(Action.NAME,name);
			putValue(Action.SMALL_ICON,icon);
			putValue(Action.SHORT_DESCRIPTION,"Set panel color to "+name.toLowerCase());
			putValue("color",c);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Color c = (Color) getValue("color");
			buttonPanel.setBackground(c);
		}
		
	}
	
	/**
	 * @param arg0
	 */
	public ActionFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public ActionFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ActionFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月13日下午5:01:55
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new ActionFrame();
		frame.setTitle("ActionTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
