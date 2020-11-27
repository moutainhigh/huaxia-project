package ThinkInJava;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * 测试布局管理器，东西南北和中间
 * @author User
 *
 */
public class BorderLayout1 extends JFrame{
	public BorderLayout1() {
		// TODO Auto-generated constructor stub
		this.add(BorderLayout.NORTH,new JButton("North"));
		this.add(BorderLayout.SOUTH,new JButton("SOUTH"));
		this.add(BorderLayout.EAST,new JButton("EAST"));
		this.add(BorderLayout.WEST,new JButton("WEST"));
		this.add(BorderLayout.CENTER,new JButton("CENTER"));
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(300,250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String args[]){
		BorderLayout1 borderlayour = new BorderLayout1();
	}
}
