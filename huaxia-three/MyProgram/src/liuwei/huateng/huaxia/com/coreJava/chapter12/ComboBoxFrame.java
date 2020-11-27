/**
 * Title: ComboBoxFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月14日下午4:28:04
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

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @class_name:ComboBoxFrame2020年1月14日
 * @comments: 符合框单选
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月14日下午4:28:04
 */
public class ComboBoxFrame extends JFrame {
	private JComboBox<String> faceCombo;
	private JLabel label;
	private static final int DEFAULT_SIZE = 24;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	/**
	 * @throws HeadlessException
	 */
	public ComboBoxFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		label.setFont(new Font("Serif",Font.PLAIN,DEFAULT_SIZE));
		this.add(label,BorderLayout.CENTER);
		//make a combo box and add face names
		faceCombo = new JComboBox<>();
		faceCombo.addItem("Serif");
		faceCombo.addItem("SansSerif");
		faceCombo.addItem("Monospaced");
		faceCombo.addItem("Dialog");
		faceCombo.addItem("DialogInput");
        faceCombo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				label.setFont(new Font(faceCombo.getItemAt(faceCombo.getSelectedIndex()),Font.PLAIN,DEFAULT_SIZE));
			}
        	
        });
        JPanel comboPanel = new JPanel();
        comboPanel.add(faceCombo);
        this.add(comboPanel,BorderLayout.SOUTH);
	}

	/**
	 * @param arg0
	 */
	public ComboBoxFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public ComboBoxFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ComboBoxFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月14日下午4:28:04
	 */
	public static void main(String[] args) {
				JFrame frame = new ComboBoxFrame();
//				frame.add(new CalculatorPanel());
				frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
				frame.setTitle("test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
	}

}
