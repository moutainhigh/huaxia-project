/**
 * Title: ExampleFrame_02.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月18日下午2:14:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JSwing;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

/**
 * @class_name:ExampleFrame_022019年10月18日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月18日下午2:14:09
 */
public class ExampleFrame_02 extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public ExampleFrame_02() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		this.setTitle("分割面板");
		this.setBounds(100,100,500,375);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JSplitPane hSplitPane = new JSplitPane();
		hSplitPane.setDividerLocation(40);//设置分隔条左侧宽度40像素
		getContentPane().add(hSplitPane,BorderLayout.CENTER);
		hSplitPane.setLeftComponent(new JLabel("     1"));
		//创建一个垂直方向的分割面板
		final JSplitPane vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vSplitPane.setDividerLocation(30);
		vSplitPane.setDividerSize(8);
		vSplitPane.setOneTouchExpandable(true);
		vSplitPane.setContinuousLayout(true);
		hSplitPane.setRightComponent(vSplitPane);
		vSplitPane.setLeftComponent(new JLabel("   2"));
		vSplitPane.setRightComponent(new JLabel("    3"));
	}

	/**
	 * @param arg0
	 */
	public ExampleFrame_02(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public ExampleFrame_02(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ExampleFrame_02(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月18日下午2:14:09
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExampleFrame_02 frame = new ExampleFrame_02();
		frame.setVisible(true);
	}

}
