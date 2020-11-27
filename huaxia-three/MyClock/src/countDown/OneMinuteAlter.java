package countDown;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OneMinuteAlter {

	public JFrame frame;
	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;
	private JLabel jl4;
	private JLabel jl5;
	private JLabel jl6;
	Font font = new Font("宋体",Font.BOLD,36);
	
	public OneMinuteAlter(){
		frame = new JFrame("15分钟倒计时");
		jl1 = new JLabel();
		jl2 = new JLabel();
		jl3 = new JLabel();
		jl4 = new JLabel();
		jl5 = new JLabel();
		jl6 = new JLabel();
		jl1.setFont(font);
		jl2.setFont(font);
		jl3.setFont(font);
		jl4.setFont(font);
		jl5.setFont(font);
		jl6.setFont(font);
		jl1.setBackground(Color.white);
		jl2.setBackground(Color.white);
		jl3.setBackground(Color.white);
		jl4.setBackground(Color.white);
		jl5.setBackground(Color.white);
		jl6.setBackground(Color.white);
		init();
	}
	public static void main(String args[]){
		new OneMinuteAlter().forMethod();
	}
	//循环调用的方法
	public  void forMethod(){
		Calendar c= Calendar.getInstance();
		long year =  c.get(Calendar.YEAR);
		long month = c.get(Calendar.MONTH)+1;
		long day =  c.get(Calendar.DAY_OF_MONTH);
		long hour =  c.get(Calendar.HOUR_OF_DAY);
		long minute =  c.get(Calendar.MINUTE)+1;
		long seconds =  c.get(Calendar.SECOND);
		getTime(""+year,""+month,""+day,""+hour,minute+"","00");
	}
	
	// 初始化
	private void init() {
		JPanel jp = new JPanel();
		jp.add(jl1);
		jp.add(jl2);
		jp.add(jl3);
		jp.add(jl4);
		jp.add(jl5);
		jp.add(jl6);
		frame.add(jp);
		jp.setBackground(Color.white);
		frame.setVisible(true);
		frame.setLocation(300, 400);
		frame.setSize(300, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void getTime(String yea,String mont,String da,String hou,String minu,String secon) {
		Long time = time(yea,mont,da,hou,minu,secon);
//		Long time = time("2019","4","24","17","00","00");
		Calendar c = Calendar.getInstance();//创建一个时间对象
		c.setTimeInMillis(time);;
		long year = 0;
		long month =0;
		long day = 0;
		long hour = 0;
		long minute = 0;
		long seconds = 0;
		int  timeLong = 0;
		
		while (time >= 0) {
			day = time/(60*60*24);
			hour =(time-(day*24*60*60)) / 3600;
			minute = (time -(day*24*60*60) - hour * 3600) / 60;
			if(minute>=0 && minute<15){
				seconds = time -(day*24*60*60) - hour * 3600 - minute * 60;
				minute= minute;
			}else if(minute>=15 && minute<30){
				seconds = time -(day*24*60*60) - hour * 3600 - minute * 60;
				minute -= 15;
			}else if(minute>=30 && minute<45){
				seconds = time -(day*24*60*60) - hour * 3600 - minute * 60;
				minute -= 30;
			}else if(minute>=45){
				seconds = time -(day*24*60*60) - hour * 3600 - minute * 60;
				minute -= 45;
			}
			
			jl4.setText(hour + "时");
			jl5.setText(minute + "分");
			jl6.setText(seconds + "秒");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(minute == 0 && seconds == 0){
				new AlterPanel("15分钟倒计时结束").alter();
			}
			time=time-1;
		}
		{
			forMethod();
		}
	}
	//设置截止时间
	private Long setEndTime(String year,String month,String day,String hour,String minute,String second){
		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month)-1;
		int d = Integer.parseInt(day);
		int h = Integer.parseInt(hour);
		int minu = Integer.parseInt(minute);
		int s = Integer.parseInt(second);
		Calendar c= Calendar.getInstance();
		c.set(Calendar.YEAR,y);
		c.set(Calendar.MONTH, m);
		c.set(Calendar.DAY_OF_MONTH,d);
		c.set(Calendar.HOUR_OF_DAY,h);
		c.set(Calendar.MINUTE, minu);
		c.set(Calendar.SECOND,s);
		return c.getTimeInMillis();
	}
	//获取当前时间和截止时间的时间差
	private Long time(String year,String month,String day,String hour,String minute,String second){
		long startTime = (new Date()).getTime();
		return (setEndTime(year,month,day,hour,minute,second)-startTime)/1000;
	}
}
