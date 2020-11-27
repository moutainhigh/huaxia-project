package Rank;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 测试工作中技术的排名
 * 工作技术衡量标准，技术的新颖度，工作数量，工作质量。这个需要公司进行计算
 * 2014年全球有2900万技术工人，1100万专业开发人员，还有大约750万开发爱好者
 * 按照两千万进行计算。自己的水平只是出于中低端，要做到至少占1000万吧
 * @author liuwei
 */
public class WorkTechnology extends JFrame{
	Font font = new Font("宋体",Font.BOLD,15);
	public WorkTechnology(){
		super("工作技术排名系统");
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
			JLabel jlabel = new JLabel("软件工程");
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
			JLabel jlabel = new JLabel("Java");
			jlabel.setBackground(Color.white);
			jlabel.setFont(font);
			panel.add(jlabel,BorderLayout.CENTER);
			this.add(panel);
		}{
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			JLabel jlabel = new JLabel("10000000");
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
		WorkTechnology workTechnology = new WorkTechnology();
	}
}
