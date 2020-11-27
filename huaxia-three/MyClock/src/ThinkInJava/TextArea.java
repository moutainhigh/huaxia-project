package ThinkInJava;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * 开发一些awt的程序
 * @author User
 *
 */
public class TextArea extends JFrame {
	private JButton b= new JButton("Add Data"),c= new JButton("Clear Data");
	private JTextArea t= new JTextArea(20,40);
	private Map<String,String> m = new HashMap<String,String>();
	public TextArea(){
		m.put("beijing","北京");
		m.put("shanghai","上海");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for(Map.Entry me:m.entrySet()){
					t.append(me.getKey()+":"+me.getValue()+"\n");
				}
			}
		});
		c.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				t.setText("");
			}
		});
		this.setLayout(new FlowLayout());
		this.add(new JScrollPane(t));
		this.add(b);
		this.add(c);
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(475,425);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String args[]){
		TextArea textarea = new TextArea();
	}
}
