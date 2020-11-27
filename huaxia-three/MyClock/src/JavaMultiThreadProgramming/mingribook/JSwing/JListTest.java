/**
 * Title: JListTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月17日下午4:20:35
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JSwing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @class_name:JListTest2019年10月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月17日下午4:20:35
 */
public class JListTest extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public JListTest() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		this.setTitle("JList测试");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = this.getContentPane();
		cp.setLayout(null);
		JList<String> jl = new JList<>(new MyListModel());
		JScrollPane scrollPane = new JScrollPane(jl);
		scrollPane.setBounds(10,10,100,100);;
		this.setBounds(10,10,100,100);;
		cp.add(scrollPane,BorderLayout.CENTER);
	}
	/**
	 * @class_name:MyListModel2019年10月17日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2019年10月17日下午4:24:00
	 */
	class MyListModel extends AbstractListModel<String>{
		private String[] contents ={"列表1","列表2","列表3","列表4","列表5","列表6"};
		@Override
		public String getElementAt(int x) {
			// TODO Auto-generated method stub
			if(x<contents.length){
				return contents[x++];
			}
			return null;
		}

		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return contents.length;
		}
		
	}
	/**
	 * @param arg0
	 */
	public JListTest(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public JListTest(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public JListTest(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月17日下午4:20:35
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JListTest frame = new JListTest();
		frame.setVisible(true);
	}

}
