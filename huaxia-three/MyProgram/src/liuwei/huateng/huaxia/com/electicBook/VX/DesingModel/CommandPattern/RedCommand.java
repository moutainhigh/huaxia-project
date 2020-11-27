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
public class RedCommand extends JButton implements MyCommand {
	
	private JPanel p;
	/**
	 * 
	 */
	public RedCommand() {
		// TODO Auto-generated constructor stub
	}
	public RedCommand(String name,JPanel p) {
		// TODO Auto-generated constructor stub
		super(name);
		this.p = p;
	}
	/**
	 * @param arg0
	 */
	public RedCommand(Icon arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param arg0
	 */
	public RedCommand(Action arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public RedCommand(String arg0, Icon arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.CommandPattern.MyCommand#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		p.setBackground(Color.RED);
	}

}
