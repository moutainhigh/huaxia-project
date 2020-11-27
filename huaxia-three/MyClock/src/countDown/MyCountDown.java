package countDown;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;

import milclock.myPanel;

public class MyCountDown extends JFrame {
	static MyCountDown myCountDown ;
	Font font = new Font("宋体",Font.BOLD,20);
	private myPanel nowTime;
	private newTimeDown2 jpanel;
	private newTimeDown2 jpanel2020;
	private newTimeDown2 jpanelNextMonth;
	private newTimeDown2 jpanelWorkOver;
	static JLabel jlabel ;
	static JLabel jlabel2;
	static JLabel jlabel3;
	static JLabel jlabel4;
	static JLabel jlabel5;
	public MyCountDown() {
		super("时间安排");
		Calendar c = Calendar.getInstance();//创建当前的一个时间对象
		nowTime = new myPanel();
		nowTime.setPreferredSize(new Dimension(650, 50));
		nowTime.setBackground(Color.white);
		jlabel4 = new JLabel();
		jlabel4.setPreferredSize(new Dimension(650, 100));
		jlabel4.setText("现在时间");
		jlabel4.setBackground(Color.white);
		jlabel4.setFont(font);
		
		jpanel = new newTimeDown2("2060","01","01","00","00","00","000");
		jpanel.setPreferredSize(new Dimension(650, 50));
		jpanel.setBackground(Color.white);
		jlabel = new JLabel();
		jlabel.setPreferredSize(new Dimension(650, 100));
		jlabel.setText("生命倒计时,2060年1月1日");
		jlabel.setBackground(Color.white);
		jlabel.setFont(font);
		
		jpanel2020 = new newTimeDown2(((c.get(Calendar.YEAR)+1))+"","01","01","00","00","00","000");
		jpanel2020.setPreferredSize(new Dimension(650, 50));
		jpanel2020.setBackground(Color.white);
		jlabel2 = new JLabel();
		jlabel2.setPreferredSize(new Dimension(650, 100));
		jlabel2.setText((c.get(Calendar.YEAR)+1)+"年1月1日倒计时");
		jlabel2.setBackground(Color.white);
		jlabel2.setFont(font);
		
		Calendar c2 = Calendar.getInstance();//创建当前的一个时间对象
		c2.set(Calendar.YEAR,c.get(Calendar.YEAR));
		c2.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);

		jpanelNextMonth = new newTimeDown2(c2.get(Calendar.YEAR)+"",(c2.get(Calendar.MONTH)+1)+"","01","00","00","00","000");
		jpanelNextMonth.setPreferredSize(new Dimension(650, 50));
		jpanelNextMonth.setBackground(Color.white);
		jlabel3 = new JLabel();
		jlabel3.setPreferredSize(new Dimension(650, 100));
		jlabel3.setText("月份"+(c2.get(Calendar.YEAR)+"年"+(c2.get(Calendar.MONTH)+1)+"月1日倒计时"));
		jlabel3.setBackground(Color.white);
		jlabel3.setFont(font);
		
		jpanelWorkOver = new newTimeDown2(c.get(Calendar.YEAR)+"",(c.get(Calendar.MONTH)+1)+"",c.get(Calendar.DAY_OF_MONTH)+"","20","00","00","000");
		jpanelWorkOver.setPreferredSize(new Dimension(650, 50));
		jpanelWorkOver.setBackground(Color.white);
		jlabel5 = new JLabel();
		jlabel5.setPreferredSize(new Dimension(650, 100));
		jlabel5.setText("8点下班倒计时");
		jlabel5.setBackground(Color.white);
		jlabel5.setFont(font);
		
		this.setBackground(Color.white);
		this.add(jlabel4);
		this.add(nowTime);
		this.add(jlabel5);
		this.add(jpanelWorkOver);
		this.add(jlabel3);
		this.add(jpanelNextMonth);
		this.add(jlabel2);
		this.add(jpanel2020);
		this.add(jlabel);
		this.add(jpanel);
		this.setLayout(new GridLayout(10,1));
		this.setVisible(true);
		this.setLocation(300, 000);
		this.setSize(650, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		myCountDown = new MyCountDown();
		//getTime线程执行循环，无法跳出，使用线程执行。利用了for循环实现了无限执行的功能
		new Thread(){
			public void run(){
				while(true){
					myCountDown.jpanel.getTime();
					Calendar c = Calendar.getInstance();//创建当前的一个时间对象
					Calendar c2 = Calendar.getInstance();//创建当前的一个时间对象
					c2.set(Calendar.YEAR,c.get(Calendar.YEAR)+100);
					c2.set(Calendar.MONTH, c.get(Calendar.MONTH));
					c2.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
					c2.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
					c2.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
					c2.set(Calendar.SECOND, c.get(Calendar.SECOND));
					c2.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND));
					myCountDown.jpanel.setEndYear(String.valueOf( c2.get(Calendar.YEAR)));
					myCountDown.jpanel.setEndMonth("01");
					myCountDown.jpanel.setEndDay("01");
					myCountDown.jpanel.setEndHour("00");
					myCountDown.jpanel.setEndMinute("00");
					myCountDown.jpanel.setEndSeconds("00");
					myCountDown.jpanel.setEndMilliseconds(String.valueOf("000"));
					jlabel.setText("生命倒计时,"+ (c2.get(Calendar.YEAR))+"年1月1日");
					
				}
			}
		}.start();
		new Thread(){
			public void run(){
				while(true){
					myCountDown.jpanel2020.getTime();
					Calendar c = Calendar.getInstance();//创建当前的一个时间对象
					Calendar c2 = Calendar.getInstance();//创建当前的一个时间对象
					c2.set(Calendar.YEAR,c.get(Calendar.YEAR)+10);
					c2.set(Calendar.MONTH, c.get(Calendar.MONTH));
					c2.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
					c2.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
					c2.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
					c2.set(Calendar.SECOND, c.get(Calendar.SECOND));
					c2.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND));
					myCountDown.jpanel2020.setEndYear(String.valueOf( c2.get(Calendar.YEAR)));
					myCountDown.jpanel2020.setEndMonth("01");
					myCountDown.jpanel2020.setEndDay("01");
					myCountDown.jpanel2020.setEndHour("00");
					myCountDown.jpanel2020.setEndMinute("00");
					myCountDown.jpanel2020.setEndSeconds("00");
					myCountDown.jpanel2020.setEndMilliseconds(String.valueOf("000"));
					jlabel2.setText(c2.get(Calendar.YEAR)+"年1月1日倒计时");
				}
				
			}
		}.start();
		new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					myCountDown.jpanelNextMonth.getTime();
					Calendar c = Calendar.getInstance();//创建当前的一个时间对象	
					Calendar c2 = Calendar.getInstance();//创建当前的一个时间对象
					c2.set(Calendar.YEAR,c.get(Calendar.YEAR));
					c2.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
					c2.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
					c2.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
			
					c2.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
					c2.set(Calendar.SECOND, c.get(Calendar.SECOND));
					c2.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND));
					myCountDown.jpanelNextMonth.setEndYear(String.valueOf( c2.get(Calendar.YEAR)));
					myCountDown.jpanelNextMonth.setEndMonth(String.valueOf( c2.get(Calendar.MONTH)+1));
					myCountDown.jpanelNextMonth.setEndDay("01");
					myCountDown.jpanelNextMonth.setEndHour("00");
					myCountDown.jpanelNextMonth.setEndMinute("00");
					myCountDown.jpanelNextMonth.setEndSeconds("00");
					myCountDown.jpanelNextMonth.setEndMilliseconds(String.valueOf("000"));
					
					jlabel3.setText("月份"+(c2.get(Calendar.YEAR)+"年"+(c2.get(Calendar.MONTH)+1)+"月1日倒计时"));
				}
				
			}
		}.start();
		new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					myCountDown.jpanelWorkOver.getTime();
					Calendar c = Calendar.getInstance();//创建当前的一个时间对象
					Calendar c2 = Calendar.getInstance();//创建当前的一个时间对象
					c2.set(Calendar.YEAR,c.get(Calendar.YEAR));
					c2.set(Calendar.MONTH, c.get(Calendar.MONTH));
					c2.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)+1);
					c2.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
					c2.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
					c2.set(Calendar.SECOND, c.get(Calendar.SECOND));
					c2.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND));
					System.out.println(c2.get(Calendar.DAY_OF_MONTH));
					myCountDown.jpanelWorkOver.setEndYear(String.valueOf( c2.get(Calendar.YEAR)));
					myCountDown.jpanelWorkOver.setEndMonth(String.valueOf( c2.get(Calendar.MONTH)+1));
					myCountDown.jpanelWorkOver.setEndDay(String.valueOf( c2.get(Calendar.DAY_OF_MONTH)));
					myCountDown.jpanelWorkOver.setEndHour("20");
					myCountDown.jpanelWorkOver.setEndMinute("00");
					myCountDown.jpanelWorkOver.setEndSeconds("00");
					myCountDown.jpanelWorkOver.setEndMilliseconds(String.valueOf("000"));
				}
			}
		}.start();
	}
}
