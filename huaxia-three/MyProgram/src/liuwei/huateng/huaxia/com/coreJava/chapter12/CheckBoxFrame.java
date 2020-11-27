/**
 * Title: CheckBoxFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月14日下午2:46:14
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter12;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @class_name:CheckBoxFrame2020年1月14日
 * @comments: 动态改变文本字体
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月14日下午2:46:14
 */
public class CheckBoxFrame extends JFrame {	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private JLabel label;
	private JCheckBox bold;
	private JCheckBox italic;
	private static final int FONTSIZE = 24;
	
	/**
	 * @throws HeadlessException
	 */
	public CheckBoxFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		label.setFont(new Font("Serif",Font.BOLD,FONTSIZE));
		this.add(label,BorderLayout.CENTER);
		ActionListener listener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int mode = 0;
				if(bold.isSelected()) 
					mode+=Font.BOLD;
				if(italic.isSelected())
					mode += Font.ITALIC;
				label.setFont(new Font("Serif",mode,FONTSIZE));
			}
			
		};
		JPanel buttonPanel = new JPanel();
		
		bold = new JCheckBox("Bold");
		bold.addActionListener(listener);
		bold.setSelected(true);
		buttonPanel.add(bold);
		
		italic = new JCheckBox("Italic");
		italic.addActionListener(listener);
		buttonPanel.add(italic);
		this.add(buttonPanel,BorderLayout.SOUTH);
		pack();
	}

	/**
	 * @param arg0
	 */
	public CheckBoxFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public CheckBoxFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CheckBoxFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月14日下午2:46:14
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				JFrame frame = new CheckBoxFrame();
//				frame.add(new CalculatorPanel());
				frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
				frame.setTitle("test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
	}

}
