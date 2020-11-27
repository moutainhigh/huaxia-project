package ThinkInJava;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class TextPane extends JFrame{
	private JButton b = new JButton("Add Text");
	private JTextPane tp = new JTextPane();
//	private static Generator sg = new RandomGenerator.String(7);
	public TextPane(){
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for(int i=1;i<10;i++){
					tp.setText(tp.getText()+"Hello World!"+"\n");
				}
			}
			
		});
		add(new JScrollPane(tp));
		add(BorderLayout.SOUTH,b);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(475,425);
		this.setVisible(true);
	}
	public static void main(String args[]){
	new TextPane();
	}
	
}
