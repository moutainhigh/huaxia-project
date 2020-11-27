package Rank;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 自己负债，自己基本上处于穷人的行列
 * @author liuwei
 */
public class SocialAndMoneyRank extends JFrame{
	Font font = new Font("宋体",Font.BOLD,15);
	public SocialAndMoneyRank(){
		super("社会地位和资产排名系统");
		this.setLayout(new GridLayout(2,3));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			JLabel jlabel = new JLabel("姓名");
			jlabel.setBackground(Color.white);
			jlabel.setFont(font);
			panel.add(jlabel,BorderLayout.CENTER);
			this.add(panel);
		}{
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			JLabel jlabel = new JLabel("社会地位和资产");
			jlabel.setBackground(Color.white);
			jlabel.setFont(font);
			panel.add(jlabel,BorderLayout.CENTER);
			this.add(panel);
		}{
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			JLabel jlabel = new JLabel("排行");
			jlabel.setBackground(Color.white);
			jlabel.setFont(font);
			panel.add(jlabel,BorderLayout.CENTER);
			this.add(panel);
		}
		//一些数据排名
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			JLabel jlabel = new JLabel("刘伟");
			jlabel.setBackground(Color.white);
			jlabel.setFont(font);
			panel.add(jlabel,BorderLayout.CENTER);
			this.add(panel);
		}{
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			JLabel jlabel = new JLabel("威望");
			jlabel.setBackground(Color.white);
			jlabel.setFont(font);
			panel.add(jlabel,BorderLayout.CENTER);
			this.add(panel);
		}{
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			JLabel jlabel = new JLabel("7600000000");
			jlabel.setBackground(Color.white);
			jlabel.setFont(font);
			panel.add(jlabel,BorderLayout.CENTER);
			this.add(panel);
		}
		this.setBackground(Color.white);
		this.setVisible(true);
		this.setLocation(100,100);
		this.setSize(400,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]){
		SocialAndMoneyRank rank = new SocialAndMoneyRank();
	}
}
