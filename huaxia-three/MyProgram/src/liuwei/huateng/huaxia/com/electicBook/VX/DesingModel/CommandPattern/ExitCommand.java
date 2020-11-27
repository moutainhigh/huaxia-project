/**
 * Title: YellowCommand.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������5:37:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.CommandPattern;

import java.awt.Color;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @class_name:YellowCommand2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������5:37:05
 */
public class ExitCommand extends JButton implements MyCommand {
	private JPanel p;
	/**
	 * 
	 */
	public ExitCommand() {
		// TODO Auto-generated constructor stub
	}
	public ExitCommand(String name,JPanel p) {
		// TODO Auto-generated constructor stub
		super(name);
		this.p = p;
	}
	/**
	 * @param arg0
	 */
	public ExitCommand(Icon arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ExitCommand(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ExitCommand(Action arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ExitCommand(String arg0, Icon arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.CommandPattern.MyCommand#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
