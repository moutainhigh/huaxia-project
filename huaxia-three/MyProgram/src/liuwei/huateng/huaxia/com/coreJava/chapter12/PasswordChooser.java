/**
 * Title: PasswordChooser.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月15日上午10:01:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter12;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @class_name:PasswordChooser2020年1月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月15日上午10:01:32
 */
public class PasswordChooser extends JPanel {
	private JTextField username;
	private JPasswordField password;
	private JButton okButton;
	private boolean ok;
	private JDialog dialog;
	/**
	 * 
	 */
	public PasswordChooser() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		panel.add(new JLabel("User name:"));
		panel.add(username = new JTextField(""));
		panel.add(new JLabel("Password:"));
		panel.add(password = new JPasswordField(""));
		this.add(panel,BorderLayout.CENTER);
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ok = true;
				dialog.setVisible(false);
			}
			
		});
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dialog.setVisible(false);
			}
			
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		this.add(buttonPanel,BorderLayout.SOUTH);
	}
	/**
	 * @Title: setUser
	 *@Description: TODO
	 *@param u
	 *@author: LiuWei
	 *@Date: 2020年1月15日上午11:22:14
	 */
	public void setUser(User u)
	{
		username.setText(u.getUsername());
	}
	/**
	 * @Title: showDialog
	 *@Description: TODO
	 *@param parent
	 *@param title
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月15日下午2:15:54
	 */
	public boolean showDialog(Component parent,String title){
		ok = false;
		//locate  the owner frame
		Frame owner = null;
		if(parent instanceof Frame)
			owner = (Frame) parent;
		else
			owner = (Frame)SwingUtilities.getAncestorOfClass(Frame.class, parent);
		//if first time ,or if owner ha changed ,make new dialog
		if(dialog == null || dialog.getOwner() != owner)
		{
			dialog = new JDialog(owner,true);
			dialog.add(this);
			dialog.getRootPane().setDefaultButton(okButton);
			dialog.pack();
		}
		//set title and show dialog
		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}
	/**
	 * @Title: getUser
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月15日上午11:23:04
	 */
	public User getUser()
	{
		return new User(username.getText(),password.getPassword().toString());
	}
	/**
	 * @param arg0
	 */
	public PasswordChooser(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public PasswordChooser(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public PasswordChooser(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月15日上午10:01:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
