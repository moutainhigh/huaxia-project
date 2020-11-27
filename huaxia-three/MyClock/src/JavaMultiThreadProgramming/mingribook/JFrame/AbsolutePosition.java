/**
 * Title: AbsolutePosition.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午4:05:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JFrame;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @class_name:AbsolutePosition2019年10月14日
 * @comments:绝对布局的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午4:05:19
 */
public class AbsolutePosition extends JFrame {
	public AbsolutePosition(){
		this.setTitle("本窗体使用绝对布局");
		this.setLayout(null);
		this.setBounds(0,0,200,150);
		Container c= this.getContentPane();
		JButton b1 = new JButton("按钮1");
		JButton b2 = new JButton("按钮2");
		b1.setBounds(10,30,80,30);
		b2.setBounds(60,70,100,20);
		c.add(b1);
		c.add(b2);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午4:05:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AbsolutePosition();
	}

}
