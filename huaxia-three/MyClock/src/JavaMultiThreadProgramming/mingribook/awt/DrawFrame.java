package JavaMultiThreadProgramming.mingribook.awt;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 画图程序
 * @author liuwei
 *
 */
public class DrawFrame extends JFrame {
	public DrawFrame(){
		super();
		initialize();
	}
	private void initialize(){
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new CanvasPanel());
		this.setTitle("绘图示例2");
	}
	public static void main(String args[]){
		new DrawFrame().setVisible(true);
	}
	class CanvasPanel extends JPanel{
		public void paint(Graphics g){
			super.paint(g);
			Graphics2D g2 =(Graphics2D)g;
			Shape[] shapes = new Shape[4];
			shapes[0] = new Ellipse2D.Double(5,5,100,100)	;//创建圆形对象
			shapes[1] = new Rectangle2D.Double(110,5,100,100)	;//创建矩形对象
			shapes[2] = new Rectangle2D.Double(15,15,80,80)	;//创建矩形对象
			shapes[3] = new Ellipse2D.Double(120,15,80,80)	;//创建圆形对象
			for(Shape shape:shapes){
				Rectangle2D bounds = shape.getBounds2D();
				if(bounds.getWidth() == 80){
					g2.fill(shape);
				}else{
					g2.draw(shape);
				}
			}
		}
	}
}
