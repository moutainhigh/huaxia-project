package ThinkInJava;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * 测试gridLayout
 * @author User
 *
 */
public class GridLayout1 extends JFrame {
	public GridLayout1(){
		this.setLayout(new GridLayout(7,3));
		for(int i=0;i<20;i++){
			this.add(new JButton("Button "+i));
		}
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(300,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String args[]){
		new GridLayout1();
	}
}
