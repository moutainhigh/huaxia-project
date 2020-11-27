package electicBook;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
/**
 * 测试键盘的输入
 * @author Liuwei
 */
public class KeyEventDemo extends JFrame{
	private JPanel contentPane;
	public static void main(String args[]){
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				try{
					KeyEventDemo frame = new KeyEventDemo();
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	public KeyEventDemo(){
		this.setTitle("控制键盘的输出");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,450,300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0,0));
		this.setContentPane(contentPane);
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane,BorderLayout.CENTER);
		final JTextArea textArea = new JTextArea();
		textArea.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				//按键被按下时候触发
				String keyText = KeyEvent.getKeyText(arg0.getKeyCode());
				if(arg0.isActionKey()){
					System.out.println("您按下的是动作键");
				}else{
					System.out.println("您按下的是非动作键");
					int keyCode = arg0.getKeyCode();
					switch(keyCode){
					case KeyEvent.VK_CONTROL:
						System.out.println("，Ctrl键被按下");
						break;
					case KeyEvent.VK_ALT:
						System.out.println("，Alt键被按下");
						break;
					case KeyEvent.VK_SHIFT:
						System.out.println("，Shift键被按下");
						break;
					}
					System.out.println();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				//按键释放的时候触发
				System.out.println("此次输入的是\""+arg0.getKeyChar()+"\"");
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				//发生击键的时候触发
				String keyText = KeyEvent.getKeyText(arg0.getKeyCode());
				System.out.println("您释放的是\""+arg0.getKeyChar()+"\"键");
			}
		});
		textArea.setFont(new Font("微软雅黑",Font.PLAIN,20));
		scrollPane.setViewportView(textArea);
	}
}
