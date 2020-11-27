package countDown;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class yearMonthDayDown {

	public JFrame frame;
	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;
	private JLabel jl4;
	private JLabel jl5;
	private JLabel jl6;
	private JLabel jl7;
	private JLabel jl8;
	Font font = new Font("宋体",Font.BOLD,36);
	
	public yearMonthDayDown(){
		frame = new JFrame("18点倒计时");
		jl1 = new JLabel();
		jl2 = new JLabel();
		jl3 = new JLabel();
		jl4 = new JLabel();
		jl5 = new JLabel();
		jl6 = new JLabel();
		jl7 = new JLabel();
		jl8 = new JLabel();
		jl1.setFont(font);
		jl2.setFont(font);
		jl3.setFont(font);
		jl4.setFont(font);
		jl5.setFont(font);
		jl6.setFont(font);
		jl7.setFont(font);
		jl8.setFont(font);
		jl7.setPreferredSize(new Dimension(60,50));
		jl1.setBackground(Color.white);
		jl2.setBackground(Color.white);
		jl3.setBackground(Color.white);
		jl4.setBackground(Color.white);
		jl5.setBackground(Color.white);
		jl6.setBackground(Color.white);
		jl7.setBackground(Color.white);
		jl8.setBackground(Color.white);
		init();
	}
	public static void main(String args[]){
		new yearMonthDayDown().forMethod();
	}
	//循环调用的方法
	public  void forMethod(){
		Calendar c= Calendar.getInstance();
		long year =  c.get(Calendar.YEAR);
		long month = c.get(Calendar.MONTH)+1;
		long day =  c.get(Calendar.DAY_OF_MONTH);
		long hour =  c.get(Calendar.HOUR_OF_DAY)+1;
		long minute =  c.get(Calendar.MINUTE);
		long seconds =  c.get(Calendar.SECOND);
//		getTime(""+year,""+month,""+day,""+hour,"00","00");
		getTime("2019","4","25","18","00","00");
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
		jp.add(jl7);
		jp.add(jl8);
		frame.add(jp);
		jp.setBackground(Color.white);
		frame.setVisible(true);
		frame.setLocation(300, 400);
		frame.setSize(650, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void getTime(String yea,String mont,String da,String hou,String minu,String secon) {
		Long time = time(yea,mont,da,hou,minu,secon);
//		Long time = time("2019","4","24","17","00","00");
		Calendar c = Calendar.getInstance();//创建一个时间对象
		c.setTimeInMillis(time);;
		long dayCount =  0;
		long monthCount = 0;
		long yearCount = 0;
		long year = 0;
		long month =0;
		long day = 0;
		long hour = 0;
		long minute = 0;
		long seconds = 0;
		long milliseconds = 0;
		int  timeLong = 0;
		int y,m ,d;
		while (time >= 0) {
			dayCount = time/(1000*60*60*24);
			monthCount =  initMonth();
			yearCount = initYear();
			year = 0;
			month = 0;
			day = initDay();
			y =0;
			m = 0;
			d =0;
			//总的天数
			for (int i = 0; i < dayCount; i++) {
				day++;
				d++;
				// 如果day到了月末

				if (day == monthCount(String.valueOf(yearCount), String.valueOf(monthCount))) {
					System.out.println(monthCount(String.valueOf(year), String.valueOf(monthCount)));
						monthCount++; m++;
					//如果到了年底
					if (day == 31 && monthCount == 12) {
						System.out.println("Hello World");
						yearCount++;
						day = 0;
						
						monthCount = 0;
					}
					
					day = 0;// 新的一月开始
					
				}
			}
			try{
			y = (m)/12;
			}catch(Exception e){
				y = 0;
				e.printStackTrace();
			}
//			d = (int) (monthCount(String.valueOf(initYear()), String.valueOf(initMonth())) -initDay());
			d = (int) (day - initDay());
			m = m %12;
			month = monthCount - initMonth();
			day = day - initDay();
			year = yearCount - initYear();
			
			hour =(time-(dayCount*1000*24*60*60)) / (3600*1000);
			minute = (time -(dayCount*1000*24*60*60) - hour * 1000* 3600) / (60*1000);
			seconds = (time -(dayCount*1000*24*60*60) - hour * 1000* 3600 - minute * 1000* 60)/1000;
			milliseconds = time -(dayCount*1000*24*60*60) - hour * 1000* 3600 - minute * 1000* 60 - seconds * 1000;
			
			String s = y + "年"+m + "月"+d + "日"+hour + "时"+minute + "分"+seconds + "秒"+milliseconds + "毫秒";
			jl1.setText(y + "年");
			jl2.setText(m + "月");
			jl3.setText(d + "日");
			jl4.setText(hour + "时");
			jl5.setText(minute + "分");
			jl6.setText(seconds + "秒");
			jl7.setText(milliseconds+"");
			jl8.setText("毫秒");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(minute == 0 && seconds == 0){
				new AlterPanel("15分钟倒计时结束").alter();
			}
			time=time-1;
		}
		{
//			forMethod();
		}
	}
	public long  monthCount(String year,String month){
		int y = Integer.parseInt(year);
//		int m = Integer.parseInt(month)+now.get(Calendar.MONTH);
//		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month);
		
		Calendar c= Calendar.getInstance();
		c.set(Calendar.YEAR,y);
		c.set(Calendar.MONTH, m);
		long totalDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return totalDays;
	}
	
	public Long  initMonth(){
		Calendar c= Calendar.getInstance();
	
		return  (long) c.get(Calendar.MONTH);
	}
	public Long  initDay(){
		Calendar c= Calendar.getInstance();
	
		return  (long) c.get(Calendar.DAY_OF_MONTH);
	}
	public Long  initYear(){
		Calendar c= Calendar.getInstance();
	
		return  (long) c.get(Calendar.YEAR);
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
		return (setEndTime(year,month,day,hour,minute,second)-startTime);
	}
}
