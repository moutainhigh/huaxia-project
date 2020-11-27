package JavaMultiThreadProgramming.mingribook.awt;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 绘制一个五环图形
 * @author liuwei
 *
 */
public class DrawCircle2 extends JFrame {
	private final int OVAL_WIDTH = 80;
	private final int OVAL_HEIGHT = 80;
	public DrawCircle2(){
		super();
		initialize();
	}
	private void initialize(){
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new DrawPanel());
		this.setTitle("绘图示例1");
	}
	public static void main(String args[]){
		new DrawCircle2().setVisible(true);
	}
	class DrawPanel extends JPanel{
		public void paint(Graphics g){
			super.paint(g);
			g.fillOval(10, 10, OVAL_WIDTH, OVAL_HEIGHT);
			g.fillOval(80, 10, OVAL_WIDTH, OVAL_HEIGHT);
			g.fillOval(150, 10, OVAL_WIDTH, OVAL_HEIGHT);
			g.fillOval(50, 70, OVAL_WIDTH, OVAL_HEIGHT);
			g.fillOval(120, 70, OVAL_WIDTH, OVAL_HEIGHT);
		}
	}
}
