/**
 * Title: ImageTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��13������3:53:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter10;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @class_name:ImageTest2020��1��13��
 * @comments:ʹ��JSwing����ͼ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��13������3:53:09
 */
public class ImageTest {

	/**
	 * 
	 */
	public ImageTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��13������3:53:09
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new ImageFrame();
		frame.setTitle("ImageTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
/**
 * 
 * @class_name:ImageFrame2020��1��13��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��13������3:53:41
 */
class ImageFrame extends JFrame
{
	public ImageFrame()
	{
		this.add(new ImageComponent());
		pack();
	}
}
/**
 * @class_name:ImageComponent2020��1��13��
 * @comments:�Զ��������ͼ���������ͼ����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��13������3:54:20
 */
class ImageComponent extends JComponent
{
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	private Image image;
	public ImageComponent(){
		image = new ImageIcon("C:/Users/User/Desktop/temp/background.jpg").getImage();	
	}
	/**
	 * 
	 */
	public void paintComponent(Graphics g)
	{
		if(image == null)
			return;
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);
		//draw the image in the upper-left corner
		g.drawImage(image, 0, 0, null);
	}
	/**
	 * 
	 */
	public Dimension getPreferredSize(){
		return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
}