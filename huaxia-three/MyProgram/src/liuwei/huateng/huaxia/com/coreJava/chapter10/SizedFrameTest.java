/**
 * Title: SizedFrameTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��13������10:48:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter10;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * @class_name:SizedFrameTest2020��1��13��
 * @comments: ���Գ�ʼλ�úʹ��ڴ�С
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��13������10:48:23
 */
public class SizedFrameTest {

	/**
	 * 
	 */
	public SizedFrameTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��13������10:48:23
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new SizedFrame();
		frame.setTitle("SizedFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
class SizedFrame extends JFrame
{
	public SizedFrame(){
		//get screen dimensions
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenSizeHeight = screenSize.height;
		int screenSizeWidth = screenSize.width;
		//set frame width,height and let platform pick screen location
		this.setSize(screenSizeWidth/2,screenSizeHeight/2);
		this.setLocationByPlatform(true);
		this.setLocation(screenSizeWidth/2,screenSizeHeight/2);
	}
}