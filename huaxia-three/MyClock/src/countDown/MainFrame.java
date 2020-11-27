package countDown;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	public MainFrame(){
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(300,300);
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]){
		MainFrame frame = new MainFrame();
		fiftyTimeDown f = new fiftyTimeDown();
		f.forMethod();
		frame.add(f.frame);
	}
}
