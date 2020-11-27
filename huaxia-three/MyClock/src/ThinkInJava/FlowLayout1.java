package ThinkInJava;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * 测试flowLayout
 * @author User
 *
 */
public class FlowLayout1 extends JFrame {

	public FlowLayout1(){
		this.setLayout(new FlowLayout());
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
		new FlowLayout1();
	}
}
