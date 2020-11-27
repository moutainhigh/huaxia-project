package ThinkInJava;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * 实现了Swing的和HTML兼容
 * @author liuwei
 *
 */
public class HTMLButton extends JFrame{
	private JButton b = new JButton("<html><b><font size=+2>"+"<center>Hello!<br><i>Press me now!");
	public HTMLButton() {
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				add(new JLabel("<html>"+"<i><font size=+4>kapow!"));
				validate();
			}
			
		});
		this.setLayout(new FlowLayout());
		add(b);
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(200,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		new HTMLButton();
	}
}
