/**
 * Title: BallComponent.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��21������10:03:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @class_name:BallComponent2020��8��21��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��21������10:03:16
 */
public class BallComponent extends JPanel {
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 350;
	private List<Ball> balls = new ArrayList<>();
	/**
	 * Constructor
	 */
	public BallComponent() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param b
	 *@author: LiuWei
	 *@Date: 2020��8��21������10:05:27
	 */
	public void add(Ball b){
		balls.add(b);
	}
	/**
	 * 
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(Ball b :balls){
			g2.fill(b.getShape());
		}
	}
	/**
	 * 
	 */
	public Dimension getPreferredSize(){
		return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
	/**
	 * @param arg0Constructor
	 */
	public BallComponent(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0Constructor
	 */
	public BallComponent(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public BallComponent(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��21������10:03:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
