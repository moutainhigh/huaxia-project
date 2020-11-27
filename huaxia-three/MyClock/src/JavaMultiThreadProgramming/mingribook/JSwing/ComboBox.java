/**
 * Title: ComboBox.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月18日下午4:44:46
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JSwing;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * @class_name:ComboBox2019年10月18日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月18日下午4:44:46
 */
public class ComboBox extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public ComboBox() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		this.setTitle("下拉菜单");
		this.setBounds(100,100,240,150);;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		JComboBox comboBox = new JComboBox();
		for(int i=1;i<6;i++){
			comboBox.addItem("选项"+i);
		}
		comboBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int stateChange = e.getStateChange();
				String item = e.getItem().toString();
				if(stateChange == ItemEvent.SELECTED){
					System.out.println("此次事件由   选中  选项"+item+"触发！");
				}else if(stateChange == ItemEvent.DESELECTED){
					System.out.println("此次事件由  取消 选中  选项"+item+"触发！");
				}else {
					System.out.println("此次事件由其他原因触发！");
				}
			}
			
		});
		getContentPane().add(comboBox,BorderLayout.CENTER);
	}

	/**
	 * @param arg0
	 */
	public ComboBox(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public ComboBox(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ComboBox(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月18日下午4:44:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ComboBox();
	}

}
