package ThinkInJava;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
/**
 * 弹出式菜单的使用
 * @author Liuiwei
 */
public class Popup extends JFrame {
	private JPopupMenu popup = new JPopupMenu();
	private JTextField t = new JTextField(15);
	public Popup() {
		add(t);
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				t.setText(((JMenuItem) arg0.getSource()).getText());
			}
		};
		JMenuItem m = new JMenuItem("Hither");
		m.addActionListener(al);
		popup.add(m);
		 m = new JMenuItem("Yon");
		m.addActionListener(al);
		popup.add(m);
		 m = new JMenuItem("Afar");
			m.addActionListener(al);
			popup.add(m);
		popup.addSeparator();
			 m = new JMenuItem("Stay Here");
				m.addActionListener(al);
				popup.add(m);
	   PopupListener pl= new PopupListener();
	   this.addMouseListener(pl);
	   t.addMouseListener(pl);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

class PopupListener extends MouseAdapter{
	@Override
	public void mousePressed(MouseEvent e){
		maybeShowPopup(e);
	}
	@Override
	public void mouseReleased(MouseEvent e){
		maybeShowPopup(e);
	}
	private void maybeShowPopup(MouseEvent e){
		if(e.isPopupTrigger())
			popup.show(e.getComponent(),e.getX(),e.getY());
	}
}
public static void main(String args[]){
	new Popup();
}
}