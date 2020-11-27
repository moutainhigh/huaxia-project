/**
 * Title: FontTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��13������3:31:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter10;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @class_name:FontTest2020��1��13��
 * @comments: ��ͼ����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��13������3:31:24
 */
public class FontTest {

	/**
	 * 
	 */
	public FontTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��13������3:31:24
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new FontFrame();
		frame.setTitle("FontTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
/**
 * 
 * @class_name:FontFrame2020��1��13��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��13������3:32:06
 */
class FontFrame extends JFrame
{
	public FontFrame()
	{
		this.add(new FontComponent());
		pack();
	}
}
/**
 * 
 * @class_name:FontComponent2020��1��13��
 * @comments:�Զ��������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��13������3:32:56
 */
class FontComponent extends JComponent
{
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	/**
	 * 
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		String message = "Hello World!";
		Font f = new Font("Serif",Font.BOLD,36);
		g2.setFont(f);
		//measure the size of the message
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(message, context);
		//set(x,y) = top left corner of text
		double x = (getWidth()-bounds.getWidth())/2;
		double y = (getWidth()-bounds.getHeight())/2;
		//add ascent to y to reach the baseline
		double ascent = -bounds.getY();
		double baseY = y + ascent;
		//draw the message
		 g2.drawString(message, (int)x, (int) baseY);
		 g2.setPaint(Color.LIGHT_GRAY);
		 //draw the baseline
		 g2.draw(new Line2D.Double(x,baseY,x+bounds.getWidth(),baseY));
		 //draw the enclosing rectangle
		 Rectangle2D rect = new Rectangle2D.Double(x,y,bounds.getWidth(),bounds.getHeight());
		 g2.draw(rect);
	}
	/**
	 * 
	 */
	public Dimension getPreferredSize(){
		return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
}