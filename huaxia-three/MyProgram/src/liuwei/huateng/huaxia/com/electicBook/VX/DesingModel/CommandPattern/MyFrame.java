/**
 * Title: MyFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午5:41:34
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.CommandPattern;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @class_name:MyFrame2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午5:41:34
 */
public class MyFrame extends JFrame implements ActionListener {
	private JPanel p;
	private YellowCommand btnYellow;
	private RedCommand btnRed;
	private ExitCommand btnExit;
	/**
	 * @throws HeadlessException
	 */
	public MyFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super("命令模式");
		p = new JPanel();
		this.add(p);
		btnYellow = new YellowCommand("黄色",p);
		btnRed = new RedCommand("红色",p);
		btnExit = new ExitCommand("退出");
		p.add(btnYellow);
		p.add(btnRed);
		p.add(btnExit);
		btnYellow.addActionListener(this);
		btnRed.addActionListener(this);
		btnExit.addActionListener(this);
		this.setSize(400,300);
		this.setVisible(true);
	}

	/**
	 * @param arg0
	 */
	public MyFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public MyFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public MyFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MyCommand object= (MyCommand)e.getSource();
		object.execute();
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月6日下午5:41:34
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame();
	}

}
