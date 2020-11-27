/**
 * Title: Demo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月2日上午11:18:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.csdn.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * @class_name:Demo2020年1月2日
 * @comments:测试图形心形
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月2日上午11:18:04
 */
public class Demo extends JFrame implements Runnable{
	//定义加载窗口的大小
	public static final int GAME_WIDTH = 500;
	public static final int GAME_HEIGHT = 500;
	//获取屏幕窗口大小
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	/**
	 * 
	 */
	public Demo() {
		// TODO Auto-generated constructor stub
		this.setTitle("心形曲线");
		this.setLocation((WIDTH-GAME_WIDTH)/2,(HEIGHT-GAME_HEIGHT)/2);
		this.setSize(GAME_WIDTH,GAME_HEIGHT);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	/**
	 * 
	 */
	@Override
	public void paint(Graphics g){
		double x,y,r;
		Image OffScreen = createImage(GAME_WIDTH,GAME_HEIGHT);
		Graphics drawOffScreen = OffScreen.getGraphics();
		for(int i=0;i<90;i++){
			for(int j=0;j<90;j++)
			{
				r = Math.PI/45*i*(1-Math.sin(Math.PI/45*j))*18;
				x = r*Math.cos(Math.PI/45*j)*Math.sin(Math.PI/45*i)+GAME_WIDTH/2;
				y = -r*Math.sin(Math.PI/45*j)+GAME_HEIGHT/4;
				drawOffScreen.setColor(Color.PINK);
				drawOffScreen.fillOval((int)x, (int)y, 2, 2);
			}
			//生成图片
			g.drawImage(OffScreen, 0, 0, this);
		}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月2日上午11:18:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo demo = new Demo();
		Thread t = new Thread(demo);
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(2000);
				this.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
