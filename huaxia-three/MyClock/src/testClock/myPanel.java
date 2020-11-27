package testClock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class myPanel extends JFrame{

	JPanel jpanel ;
	JLabel jlabel;
	Font font = new Font("宋体",Font.BOLD,36);
	public myPanel(){
		jpanel = new JPanel();
		jlabel = new JLabel("Hello World!");
		jlabel.setFont(font);
		jlabel.setBackground(Color.white);
		jpanel.setBackground(Color.white);
		configTimeArea();
		jpanel.add(jlabel);
		this.add(jpanel,BorderLayout.CENTER);
	}
	
	public static void main(String args[]){
		myPanel pan = new myPanel();
		
		pan.setSize(500,100);
		pan.setLocation(500,300);
		pan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan.setResizable(false);
		pan.setVisible(true);
	}
	public void configTimeArea(){
		Timer tmr = new Timer();
		tmr.scheduleAtFixedRate(new JLabelTimerTask(), new Date(), 1000);
	}
	

class JLabelTimerTask extends TimerTask{
	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		jlabel.setBackground(Color.white);
		jlabel.setText(format.format(new Date()));
	}
	
}
}
