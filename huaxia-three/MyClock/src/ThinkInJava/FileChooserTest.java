package ThinkInJava;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FileChooserTest extends JFrame{
	private JTextField  fileName = new JTextField(),dir = new JTextField();
	private JButton open = new JButton("open");
	private JButton save = new JButton("save");
	public FileChooserTest(){
		JPanel p = new JPanel();
		open.addActionListener(new OpenL());
		p.add(open);
		save.addActionListener(new SaveL());
		p.add(save);
		add(p,BorderLayout.SOUTH);
		dir.setEditable(false);
		fileName.setEditable(false);
		p = new JPanel();
		p.setLayout(new GridLayout(2,1));
		p.add(fileName);
		p.add(dir);
		add(p,BorderLayout.NORTH);
		
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(250,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class OpenL implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
//			System.out.println("Hello World! open");
			JFileChooser c= new JFileChooser();
			int rVal = c.showOpenDialog(FileChooserTest.this);
			if(rVal == JFileChooser.APPROVE_OPTION){
				fileName.setText(c.getSelectedFile().getName());
				dir.setText(c.getCurrentDirectory().toString());
			}
			if(rVal == JFileChooser.CANCEL_OPTION){
				fileName.setText("Your pressed cancel");
				dir.setText("");
			}
		}
		
	}
	class SaveL implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
//			System.out.println("Hello World! save");
			JFileChooser c= new JFileChooser();
			int rVal = c.showSaveDialog(FileChooserTest.this);
			if(rVal == JFileChooser.APPROVE_OPTION){
				fileName.setText(c.getSelectedFile().getName());
				dir.setText(c.getCurrentDirectory().toString());
			}
			if(rVal == JFileChooser.CANCEL_OPTION){
				fileName.setText("Your pressed cancel");
				dir.setText("");
			}
		}
		
	}
	public static void main(String args[]){
		new FileChooserTest();
	}
}
