package milclock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class myPanel extends JPanel{

	JLabel jlabel;
	Font font = new Font("宋体",Font.BOLD,36);
	public myPanel(){
		jlabel = new JLabel("Hello World!");
		jlabel.setFont(font);
		jlabel.setBackground(Color.white);
		this.setBackground(Color.white);
		configTimeArea();
		this.add(jlabel);
	
		this.setPreferredSize(new Dimension(650, 100));
	}
	
	public static void main(String args[]){
		myPanel pan = new myPanel();
		
		pan.setPreferredSize(new Dimension(650, 100));

	}
	public void configTimeArea(){
		Timer tmr = new Timer();
		tmr.scheduleAtFixedRate(new JLabelTimerTask(), new Date(), 1);
	}
	

class JLabelTimerTask extends TimerTask{
	SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss SSS毫秒");
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		jlabel.setBackground(Color.white);
		jlabel.setText(format.format(new Date()));
	}
	
}
}
