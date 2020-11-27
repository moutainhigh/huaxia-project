/**
 * Title: FlowLayoutPosition.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午4:18:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JFrame;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @class_name:FlowLayoutPosition2019年10月14日
 * @comments：流布局管理器
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午4:18:44
 */
public class FlowLayoutPosition extends JFrame{
	public FlowLayoutPosition(){
		this.setTitle("本窗体使用流布局管理器");
		Container c = this.getContentPane();
		this.setLayout(new FlowLayout(2,10,10));
		for(int i=0;i<32;i++){
			c.add(new JButton("button"+i));
		}
		this.setSize(300,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午4:18:44
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FlowLayoutPosition();
	}

}
