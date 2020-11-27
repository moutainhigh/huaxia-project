/**
 * Title: ImageTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月13日下午3:53:09
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
 * @class_name:ImageTest2020年1月13日
 * @comments:使用JSwing绘制图形
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日下午3:53:09
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
	 *@Date: 2020年1月13日下午3:53:09
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
 * @class_name:ImageFrame2020年1月13日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日下午3:53:41
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
 * @class_name:ImageComponent2020年1月13日
 * @comments:自定义组件，图像组件，绘图程序
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日下午3:54:20
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