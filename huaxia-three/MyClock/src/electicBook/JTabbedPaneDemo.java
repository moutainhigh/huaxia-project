package electicBook;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * 类似导航的页面的标签显示，一个面板的实现方式
 * @author Liuwei
 */
public class JTabbedPaneDemo {
	public static void main(String args[]){
		new MyTabbedPane();
	}
}
class MyTabbedPane extends JFrame implements ChangeListener,ActionListener{
	JTabbedPane jt;
	JButton jb[];
	int index = 0;
	public MyTabbedPane(){
		super("使用标签面板容器");
		jt= new JTabbedPane();
		jb = new JButton[5];
		for(int i=0;i<5;i++){
			jb[i] = new JButton("第"+i+"页面板");
			jb[i].addActionListener(this);
			jt.addTab("页标签"+i,jb[i]);
		}
		jt.addChangeListener(this);
		this.add(jt,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,200);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.setTitle("响应单击"+((JButton)arg0.getSource()).getText());	
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
			if(arg0.getSource() == jt){
				int i= ((JTabbedPane)arg0.getSource()).getSelectedIndex();
				jb[index].setVisible(false);
				jb[i].setVisible(true);
				index = i;
			}
	}
}