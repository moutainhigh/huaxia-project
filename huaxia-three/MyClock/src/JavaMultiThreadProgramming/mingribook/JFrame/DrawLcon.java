/**
 * Title: DrawLcon.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午3:57:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JFrame;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * @class_name:DrawLcon2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午3:57:02
 */
public class DrawLcon implements Icon {
	private int width ;
	private int height;
	public DrawLcon(int width,int height){
		this.width = width;
		this.height = height;
	}
	/* (non-Javadoc)
	 * @see javax.swing.Icon#getIconHeight()
	 */
	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.Icon#getIconWidth()
	 */
	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics, int, int)
	 */
	@Override
	public void paintIcon(Component arg0, Graphics arg1, int x, int y) {
		// TODO Auto-generated method stub
		arg1.fillOval(x, y, width, height);
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午3:57:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawLcon icon = new DrawLcon(15,15);
		JLabel j = new JLabel("测试",icon,SwingConstants.CENTER);
		JFrame jf = new JFrame();
		Container container = jf.getContentPane();
		container.add(j);
		jf.setVisible(true);
		jf.setSize(200,150);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
}
