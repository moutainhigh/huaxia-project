package countDown;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlterPanel extends JFrame{
	Font font = new Font("宋体",Font.BOLD,36);
	public AlterPanel(String title){
		super(title);
	}
	public void alter(){
		JPanel jp = new JPanel();
		JLabel jlabel2 = new JLabel();
		jlabel2.setText("倒计时结束");
		jlabel2.setBackground(Color.white);
		jlabel2.setFont(font);
		jp.add(jlabel2,BorderLayout.NORTH);
		
		JLabel jlabel = new JLabel();
		jlabel.setText(this.getTitle());
		jlabel.setBackground(Color.white);
		jlabel.setFont(font);
		jp.add(jlabel,BorderLayout.CENTER);
		this.add(jp);
		jp.setBackground(Color.white);
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(900,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String args[]){
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss SSS毫秒");
		new AlterPanel("倒计时结束"+format.format(new Date())).alter();
//		new AlterPanel("15分钟倒计时结束").alter();
	}
}
