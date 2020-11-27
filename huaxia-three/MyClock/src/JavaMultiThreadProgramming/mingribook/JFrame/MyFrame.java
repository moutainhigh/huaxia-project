/**
 * Title: MyFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午3:27:11
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * @class_name:MyFrame2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午3:27:11
 */
public class MyFrame extends JFrame{

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午3:27:11
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame();
	}
	/**
	 * 构造方法
	 */
	public MyFrame(){
		Container container = getContentPane();
		container.setLayout(null);
		JLabel jl = new JLabel("这是一个JFrame窗体");
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(jl);
		container.setBackground(Color.WHITE);
		JButton bl = new JButton("弹出对话框"	);
		bl.setBounds(10,10,100,21);
		bl.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new MyJDialog(MyFrame.this).setVisible(true);
			}
		});
		container.add(bl);
		this.setVisible(true);
		this.setSize(200,150);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	/**
	 * 
	 * @class_name:MyJDialog2019年10月14日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2019年10月14日下午3:39:18
	 */
	class MyJDialog extends JDialog{
		public MyJDialog(MyFrame frame){
			super(frame,"第一个JDialog窗体",true);
			Container container = getContentPane();
			container.add(new JLabel("这是一个对话框"));
			setBounds(120,120,100,100);
		}
	}
}
