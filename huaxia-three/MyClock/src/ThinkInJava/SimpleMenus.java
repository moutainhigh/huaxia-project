package ThinkInJava;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
/**
 * 菜单栏联系程序
 * @author Liuwei
 */
public class SimpleMenus extends JFrame{
	private JTextField t = new JTextField(15);
	private ActionListener al =new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			t.setText(((JMenuItem)arg0.getSource()).getText());
		}
	};
	private JMenu[] menus = {
			new JMenu("winken"),
			new JMenu("Blinken"),
			new JMenu("Nod")
	};
	private JMenuItem[] items = {
		new JMenuItem("Fee"),
		new JMenuItem("Fi"),
		new JMenuItem("Fo"),
		new JMenuItem("Zip"),
		new JMenuItem("Zap"),
		new JMenuItem("Zot"),
		new JMenuItem("Olly"),
		new JMenuItem("Oxen"),
		new JMenuItem("Free")
	};
	public SimpleMenus(){
		for(int i =0;i<items.length;i++){
			items[i].addActionListener(al);
			menus[i%3].add(items[i]);
		}
		JMenuBar mb = new JMenuBar();
		for(JMenu jm : menus)
			mb.add(jm);
		this.add(t);
		this.setJMenuBar(mb);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(200,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]){
		new SimpleMenus();
	}
}
