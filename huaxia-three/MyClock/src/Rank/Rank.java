package Rank;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 创造一个排名系统，并且给自己排名，做到第一。
 * 评价体系如何。世俗的评价，自己如何评价，给世界上的所有人进行排名。符合现实世界。
 * 排名正常可以和显示挂钩。
 * 一旦不知道努力的方向，做什么都不能很快的完成。自己要有努力的方向。
 * 要是不愁吃穿谁还会努力工作？为了衣食住行二奔波
 * 排名系统中的数据从哪来。可以自动调查的机器有没有，没有的话，连个世界上的总人口数目都不知道
 * 自己要进行一些排名，但是也要知道如何排名，没有数据。
 * @author liuwei 
 *
 */
public class Rank extends JFrame{
	Font font = new Font("宋体",Font.BOLD,15);
	public Rank(){
		super("世界排名系统");
		this.setLayout(new GridLayout(16,3));
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
			JLabel jlabel = new JLabel("资产");
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
		for(int i=0;i<42;i++){
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			JLabel jlabel = new JLabel("test");
			jlabel.setBackground(Color.white);
			jlabel.setFont(font);
			panel.add(jlabel,BorderLayout.CENTER);
			this.add(panel);
		}
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
			JLabel jlabel = new JLabel("-10000");
			jlabel.setBackground(Color.white);
			jlabel.setFont(font);
			panel.add(jlabel,BorderLayout.CENTER);
			this.add(panel);
		}{
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			JLabel jlabel = new JLabel("3000000000");
			jlabel.setBackground(Color.white);
			jlabel.setFont(font);
			panel.add(jlabel,BorderLayout.CENTER);
			this.add(panel);
		}
		this.setBackground(Color.white);
		this.setVisible(true);
		this.setLocation(100,100);
		this.setSize(400,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]){
		Rank rank = new Rank();
	}
}
