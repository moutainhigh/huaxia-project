/**
 * Title: AboutDialog.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月14日下午5:26:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter12;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @class_name:AboutDialog2020年1月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月14日下午5:26:12
 */
public class AboutDialog extends JDialog {

	/**
	 * 
	 */
	public AboutDialog() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public AboutDialog(Frame owner) {
		super(owner,"About DialogTest",true);
		// TODO Auto-generated constructor stub
		add(new JLabel("<html><h1><i>core Java</i></h1><h1>By Cay HorstMann</html>"),BorderLayout.CENTER);
		//ok button closes the dialog
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AboutDialog.this.setVisible(false);
			}
			
		});
		JPanel panel = new JPanel();
		panel.add(ok);
		this.add(panel,BorderLayout.SOUTH);
		pack();
	}

	/**
	 * @param arg0
	 */
	public AboutDialog(Dialog arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public AboutDialog(Window arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public AboutDialog(Frame arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public AboutDialog(Frame arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public AboutDialog(Dialog arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public AboutDialog(Dialog arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public AboutDialog(Window arg0, ModalityType arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public AboutDialog(Window arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public AboutDialog(Frame arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public AboutDialog(Dialog arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public AboutDialog(Window arg0, String arg1, ModalityType arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public AboutDialog(Frame arg0, String arg1, boolean arg2, GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public AboutDialog(Dialog arg0, String arg1, boolean arg2, GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public AboutDialog(Window arg0, String arg1, ModalityType arg2, GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月14日下午5:26:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
