/**
 * Title: Example1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午2:52:59
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JFrame;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * @class_name:Example12019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午2:52:59
 */
public class Example1 extends JFrame {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:52:59
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Example1().CreateJFrame("创建一个JFrame窗体");
	}
	/**
	 * @Title: CreateJFrame
	 *@Description: TODO
	 *@param title
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:58:09
	 */
	public void CreateJFrame(String title){
		JFrame jf = new JFrame(title);
		Container container = jf.getContentPane();
		JLabel jl = new JLabel("这是一个JFrame窗体");
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(jl);
		container.setBackground(Color.WHITE);
		jf.setVisible(true);
		jf.setSize(200,150);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
