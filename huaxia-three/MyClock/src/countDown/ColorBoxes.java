package countDown;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;

class CBox extends JPanel implements Runnable{
	private int pause;
	private static Random rand = new Random();
	private Color color = new Color(0);
	public void paintComponent(Graphics g){
		g.setColor(color);
		Dimension s = this.getSize();
		g.fillRect(0, 0, s.width, s.height);
	}
	public CBox(int pause){
		this.pause = pause;
	}
	@Override
	public void run() {
		while(!Thread.interrupted()){
			try {
				color = new Color(rand.nextInt(0xFFFFFF));
				repaint();
				TimeUnit.MILLISECONDS.sleep(pause);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class ColorBoxes extends JFrame {
	private int grid = 12;
	private int pause = 50;
	private static ExecutorService exec = Executors.newCachedThreadPool();
	public void setUp(){
		this.setLayout(new GridLayout(grid,grid));
		for(int i =0;i<grid*grid;i++){
			CBox cb = new CBox(pause);
			add(cb);
			exec.execute(cb);
		}
	}
	public ColorBoxes(){
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(500,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColorBoxes boxes = new ColorBoxes();
		boxes.setUp();
	}
}
