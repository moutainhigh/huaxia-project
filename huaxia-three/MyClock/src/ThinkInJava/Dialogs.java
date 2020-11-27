package ThinkInJava;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
class MyDialog extends JDialog {
	public MyDialog(JFrame parent){
		super(parent,"My dialog",true);
		add(new JLabel("Here is my dialog"));
		JButton ok = new JButton("ok");
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		this.add(ok);
		this.setSize(150,125);
	}
	
}
public class Dialogs extends JFrame{
	private JButton b1 = new JButton("Dialog Box");
	private MyDialog dig = new MyDialog(null);
	public Dialogs(){
		b1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dig.setVisible(true);
			}
			
		});
		this.add(b1);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(125,75);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]){
		new Dialogs();
	}
}