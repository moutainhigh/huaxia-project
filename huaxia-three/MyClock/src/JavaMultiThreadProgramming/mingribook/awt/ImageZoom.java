package JavaMultiThreadProgramming.mingribook.awt;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
/**
 * 图像缩放,响应事件
 * @author liuwei
 *
 */
public class ImageZoom extends JFrame {
	Image img;
	Canvas canvas;
	JSlider jSlider;
	int imgWidth;
	int imgHeight;
	int newW;
	int newH;
	public ImageZoom(){
		initialize();
	}
	private void initialize(){
		URL imgUrl = ImageZoom.class.getResource("background.jpg");
		img = Toolkit.getDefaultToolkit().getImage(imgUrl);
		canvas = new MyCanvas();
		this.setBounds(100, 100, 800, 600);
		this.setContentPane(getContentPane());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("绘制图片");
		jSlider=getJSlider();
		getContentPane().add(canvas,BorderLayout.CENTER);
		getContentPane().add(jSlider,BorderLayout.SOUTH);
	}
	//获取滑块组件
	private JSlider getJSlider(){
		if(jSlider == null){
			jSlider = new JSlider();
			jSlider.setMaximum(1000);
			jSlider.setValue(100);
			jSlider.setMinimum(1);
			jSlider.addChangeListener(new javax.swing.event.ChangeListener(){
				@Override
				public void stateChanged(ChangeEvent arg0) {
					canvas.repaint();
				}
			});
		}
		return jSlider;
	}
	/*@Overrider
	public Panel getContentPane(){
		
	}*/
	public static void main(String args[]){
		new ImageZoom().setVisible(true);
	}
	class CanvasPanel extends JPanel{
		public void paint(Graphics g){
			super.paint(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.drawImage(img, 0, 0, this);
		}
	}
	class MyCanvas extends Canvas{
		public void paint(Graphics g){
			int newW =0,newH =0;
			imgWidth =img.getWidth(this);
			imgHeight = img.getHeight(this);
			float value = jSlider.getValue();
			newW = (int) (imgWidth*value/100);
			newH = (int)(imgHeight*value/100);
			g.drawImage(img, 0, 0, newW, newH, this);
		}
	}
}
