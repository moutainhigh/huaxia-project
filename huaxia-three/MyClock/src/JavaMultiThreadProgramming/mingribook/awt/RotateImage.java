package JavaMultiThreadProgramming.mingribook.awt;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;
/**
 * 图像的旋转
 * @author liuwei
 *
 */
public class RotateImage extends JFrame {
	private Image img;
	private MyCanvas canvasPanel = null;
	public RotateImage(){
		initialize();
	}
	private void initialize(){
		URL imgUrl = ImageZoom.class.getResource("background.jpg");
		img = Toolkit.getDefaultToolkit().getImage(imgUrl);
		canvasPanel = new MyCanvas();
		this.setBounds(100, 100, 400, 350);
		add(canvasPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("图片旋转");
	}
	public static void main(String args[]){
		new RotateImage().setVisible(true);
	}
	class MyCanvas extends Canvas{
		public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(Math.toRadians(5));
		g2.drawImage(img, 70, 10, 300, 200, this);
		g2.rotate(Math.toRadians(5));
		g2.drawImage(img, 70, 10, 300, 200, this);
		g2.rotate(Math.toRadians(5));
		g2.drawImage(img, 70, 10, 300, 200, this);
		}
	}
}
