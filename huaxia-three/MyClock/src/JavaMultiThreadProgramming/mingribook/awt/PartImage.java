package JavaMultiThreadProgramming.mingribook.awt;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 图片的水平和垂直翻转
 * @author liuwei
 *
 */
public class PartImage extends JFrame {
	private Image img;
	private int dx1,dy1,dx2,dy2;
	private int sx1,sy1,sx2,sy2;
	private JButton jButton;
	private JButton jButton1;
	private MyCanvas canvasPanel = null;
	public PartImage(){
		dx2=sx2=300;
		dy2=sy2=200;
		initialize();
	}
	private void initialize(){
		URL imgUrl = ImageZoom.class.getResource("background.jpg");
		img = Toolkit.getDefaultToolkit().getImage(imgUrl);
		canvasPanel = new MyCanvas();
		this.setBounds(100, 100, 800, 600);
		this.setContentPane(getContentPane());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("绘制图片");
		jButton=getJButton();
		jButton1=getJButton1();
		getContentPane().add(canvasPanel,BorderLayout.CENTER);
		getContentPane().add(jButton,BorderLayout.NORTH);
		getContentPane().add(jButton1,BorderLayout.SOUTH);
	}
	private JButton getJButton(){
		if(jButton == null){
			jButton = new JButton();
			jButton.setText("水平翻转");
			jButton.addActionListener(new java.awt.event.ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					sx1 = Math.abs(sx1-300);
					sx2 = Math.abs(sx2-300);
					canvasPanel.repaint();
				}
			});
		}
		return jButton;
	}
	private JButton getJButton1(){
		if(jButton1 == null){
			jButton1 = new JButton();
			jButton1.setText("垂直翻转");
			jButton1.addActionListener(new java.awt.event.ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					sy1 = Math.abs(sy1-200);
					sy2 = Math.abs(sy2-200);
					canvasPanel.repaint();
				}
			});
		}
		return jButton1;
	}
	public static void main(String args[]){
		new PartImage().setVisible(true);
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
			g.drawImage(img, dx1,dy1,dx2,dy2,sx1,sy1,sx2,sy2,this);
		}
	}
}
