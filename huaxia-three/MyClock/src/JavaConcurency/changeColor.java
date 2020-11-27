package JavaConcurency;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * 点击按钮改变颜色
 * @author liuwei
 */
public class changeColor extends JFrame {
	final Random random = new Random();
	final JButton jbutton = new JButton("Change color");
		public changeColor(){
			this.add(jbutton);
			this.setVisible(true);
//			frame.setPreferredSize(new Dimension(1000,1000));
			this.setLocation(100,300);
			this.setSize(900,300);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jbutton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					jbutton.setBackground(new Color(random.nextInt()));
				}
			});
		}
		public static void main(String args[]){
			new changeColor();
		}
}
