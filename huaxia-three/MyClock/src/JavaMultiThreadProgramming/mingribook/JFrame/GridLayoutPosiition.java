/**
 * Title: GridLayoutPosiition.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日上午9:38:10
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JFrame;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @class_name:GridLayoutPosiition2019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日上午9:38:10
 */
public class GridLayoutPosiition extends JFrame {
	public GridLayoutPosiition(){
		this.setTitle("这个窗体使用网格布局管理器的窗体");
		Container c = this.getContentPane();
		this.setLayout(new GridLayout(7,3,5,5));
		for(int i=0;i<20;i++){
			c.add(new JButton("button"+i));
		}
		this.setSize(300,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日上午9:38:10
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GridLayoutPosiition();
	}

}
