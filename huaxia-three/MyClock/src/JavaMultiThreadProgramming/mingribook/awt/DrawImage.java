package JavaMultiThreadProgramming.mingribook.awt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 绘制图片
 * @author liuwei
 *
 */
public class DrawImage extends JFrame {
	Image img;
	public DrawImage(){
		URL imgUrl = DrawImage.class.getResource("background.jpg");
		img = Toolkit.getDefaultToolkit().getImage(imgUrl);
		this.setSize(440,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new CanvasPanel());
		this.setTitle("绘图文本");
	}
	public static void main(String args[]){
		new DrawImage().setVisible(true);
	}
	class CanvasPanel extends JPanel{
		public void paint(Graphics g){
			super.paint(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.drawImage(img, 0, 0, this);
		}
	}
}
