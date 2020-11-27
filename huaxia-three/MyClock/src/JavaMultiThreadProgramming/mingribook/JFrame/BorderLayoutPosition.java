/**
 * Title: BorderLayoutPosition.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午4:33:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JFrame;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @class_name:BorderLayoutPosition2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午4:33:15
 */
public class BorderLayoutPosition extends JFrame{
	String[] border ={BorderLayout.CENTER,BorderLayout.NORTH,BorderLayout.SOUTH,BorderLayout.WEST,BorderLayout.EAST};
	String[] buttonName = {"center button","north button","south button","west button","east button"};
	/**
	 * 构造方法
	 */
	public BorderLayoutPosition(){
		this.setTitle("这个窗体使用边界布局管理器");
		Container c = this.getContentPane();
		this.setLayout(new BorderLayout());
		for(int i=0;i<border.length;i++){
			c.add(border[i],new JButton(buttonName[i]));
		}
		this.setSize(350,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午4:33:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BorderLayoutPosition();
	}

}
