/**
 * Title: SimpleFrameTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��13������10:39:17
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter10;

import javax.swing.JFrame;

/**
 * @class_name:SimpleFrameTest2020��1��13��
 * @comments:һ���յĿ�ܲ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��13������10:39:17
 */
public class SimpleFrameTest {

	/**
	 * 
	 */
	public SimpleFrameTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��13������10:39:17
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleFrame frame = new SimpleFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
/**
 * 
 * @class_name:SimpleFrame2020��1��13��
 * @comments: һ���յĿ�ܲ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��13������10:41:03
 */
class SimpleFrame extends  JFrame
{
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	public SimpleFrame(){
		this.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
}