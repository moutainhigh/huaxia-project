package countDown;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 我的算法实现，根绝人得计算方法进行计算。
 * 要做什么，要提前想好
 * @author Liuwei
 *
 */
/**
 * 算法原理
 * 再计算时分秒毫秒
2021 3 24 10:30:31 888
2019 5 28 11:31:32 999
2020 5 28 11:31:32 999  算年 year= 1
2021 2 28 11:31:32 999	算月 month = 9
2021 3 23 11:31:32 999	算天 day = 23 
2021 3 24 9:31:32 999	算时 hour = 22
2021 3 24 10:29:32 999	算分钟 mintue= 58
2021 3 24 10:30:30 999	算秒  seconds = 58
2021 3 24 10:30:31 999	算毫秒 millisecond = 889 

倒计时
 1  9 23 22:58:58 889
 算法可行：再进行细化
 算年： 实例化时间对象，初始化，进行获取毫秒数进行计算。
 未来设定的时间 - 当前时间年+1 其他时间不变 >0 year+1；
 未来设定的时间 - 当前时间年+2 其他时间不变 >0 year+2；
 算月
 未来设定的时间 - 当前时间年+1当前月份+2  其他时间不变 >0 month+1；
 未来设定的时间 - 当前时间年+year 当前月份+2 其他时间不变 >0 month+2；类推
 
倒计时
 1  9 23 22:58:58 889
 算法可行：再进行细化
 算年： 实例化时间对象，初始化，进行获取毫秒数进行计算。
 未来设定的时间 - 当前时间年+1 其他时间不变 >0 year+1；
 未来设定的时间 - 当前时间年+2 其他时间不变 >0 year+2；
 算年没问题
 算月 出现问题，月份不能超多12个月，循环，还需判断day是否超出了月份
  统计monythCount = 0；
 if(当前月份+i  > 12)  {
	需要当前计算年份+1  ，从1月份开始算 
 }
 monthCount++;
 if(day > 当前时间年+1当前月份+2 的最大日期 ，day= 最大日期)  月份好复杂
 未来设定的时间 - 当前时间年+1当前月份+2  其他时间不变 >0 month+1；
 未来设定的时间 - 当前时间年+year 当前月份+2 其他时间不变 >0 month+2；类推
 算月份的算法二：
	判断这个是否是设定的这个月还是下个月，判断日子是否超出
 今年剩余的月份+ 下一年剩余的月份
 如果是12月份，如果是一月份。判断
 13秒 15秒
 
 算法有问题，待解决，估计不能解决，太多的问题，要解决早都做出来了
 * @author 刘伟
 *
 */
public class newTimeDown {

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
	private String  endYear = "2021";
	private String  endMonth = "3";
	private String  endDay = "24";
	private String  endHour = "10";
	private String  endMinute = "30";
	private String  endSeconds = "31";
	private String endMilliseconds ="888";
	
	private String  startYear = "2019";
	private String  startMonth = "5";
	private String  startDay = "28";
	private String  startHour = "11";
	private String  startMinute = "31";
	private String  startSeconds = "32";
	private String startMilliseconds ="999";
	
	private long endtime = 0;
	private long startTime = 0;
	
	public static void main(String args[]){
		new newTimeDown("2060","02","01","00","00","00","000").getTime();
	}
	public newTimeDown(String year,String month,String day,String hour,String minute,String second,String milliSecond){
//		endTime("2042","10","08","17","36","46");
		endTime(year,month,day,hour,minute,second,milliSecond);
//		endTime("2060","2","1","0","0","0");
//		endTime("2250","1","1","0","0","0");
//		endTime("2100","1","1","0","0","0");
//		endTime("2020","2","29","0","0","0");
//		endTime("2030","1","1","0","0","10");
//		endTime("2036","10","16","15","22","17","000");
		Calendar c = Calendar.getInstance();//创建当前的一个时间对象
		this.startYear =String.valueOf(c.get(Calendar.YEAR));
		this.startMonth = String.valueOf(c.get(Calendar.MONTH)+1);
		this.startDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		this.startHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		this.startMinute = String.valueOf(c.get(Calendar.MINUTE));
		this.startSeconds = String.valueOf(c.get(Calendar.SECOND));
		this.startMilliseconds =String.valueOf(c.get(Calendar.MILLISECOND));
//		startTime("2020","1","1","0","0","0","00");
		startTime(startYear,startMonth,startDay,startHour,startMinute,startSeconds,startMilliseconds);
		endtime = setEndtime(endYear,endMonth,endDay,endHour,endMinute,endSeconds,endMilliseconds);
		startTime = setStrarttime(startYear,startMonth,startDay,startHour,startMinute,startSeconds,startMilliseconds);
//		startTime = setStrarttime("2020","1","1","0","0","0");
		frame = new JFrame("年月日时分秒倒计时");
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
	private void endTime(String year,String month,String day,String hour,String minute,String second,String milliSecond){
		this.endYear =year;
		this.endMonth = month;
		this.endDay = day;
		this.endHour = hour;
		this.endMinute = minute;
		this.endSeconds = second;
		this.endMilliseconds =milliSecond;
	}
	private void startTime(String year,String month,String day,String hour,String minute,String second,String millisecond){
		this.startYear =year;
		this.startMonth = month;
		this.startDay = day;
		this.startHour = hour;
		this.startMinute = minute;
		this.startSeconds = second;
		this.startMilliseconds = millisecond;
	}
	
	
	//循环调用的方法
	public  long forMethod(){
		endtime = setEndtime(endYear,endMonth,endDay,endHour,endMinute,endSeconds,endMilliseconds);
		startTime = setStrarttime(startYear,startMonth,startDay,startHour,startMinute,startSeconds,startMilliseconds);
		return timeLong(endtime,startTime);
	}
	//设定结束时间
	private Long setEndtime(String year,String month,String day,String hour,String minute,String second,String millionSecond){
		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month)-1;
		int d = Integer.parseInt(day);
		int h = Integer.parseInt(hour);
		int minu = Integer.parseInt(minute);
		int s = Integer.parseInt(second);
		int millis = Integer.parseInt(millionSecond);
		Calendar c= Calendar.getInstance();
		c.set(Calendar.YEAR,y);
		c.set(Calendar.MONTH, m);
		c.set(Calendar.DAY_OF_MONTH,d);
		c.set(Calendar.HOUR_OF_DAY,h);
		c.set(Calendar.MINUTE, minu);
		c.set(Calendar.SECOND,s);
		c.set(Calendar.MILLISECOND, millis);
		return c.getTimeInMillis();
	}
	//设置开始时间
	private Long setStrarttime(String year,String month,String day,String hour,String minute,String second,String millionSecond){
		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month)-1;
		int d = Integer.parseInt(day);
		int h = Integer.parseInt(hour);
		int minu = Integer.parseInt(minute);
		int s = Integer.parseInt(second);
		int millis = Integer.parseInt(millionSecond);
		Calendar c= Calendar.getInstance();
		c.set(Calendar.YEAR,y);
		c.set(Calendar.MONTH, m);
		c.set(Calendar.DAY_OF_MONTH,d);
		c.set(Calendar.HOUR_OF_DAY,h);
		c.set(Calendar.MINUTE, minu);
		c.set(Calendar.SECOND,s);
		c.set(Calendar.MILLISECOND, millis);
		return c.getTimeInMillis();
	}
	//获取当前时间和截止时间的时间差,时间长度，
		private Long timeLong(long end,long start){
	
			return end -start;
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
	private void getTime() {
		
		Long time = forMethod();
//		Long time = time("2019","4","24","17","00","00");
		Calendar c = Calendar.getInstance();//创建一个时间对象
		c.setTimeInMillis(time);;
		long dayCount =  0;
		long downYear = 0;
		long downMonth = 0;
		long downDay = 0;
		long hour1 = 0;
		long minute1 = 0;
		long seconds = 0;
		long milliseconds = 0;

		while (time >= 0) {
			time = forMethod();
			downYear = calculYear();
			downMonth = calculMonth();
			downDay = calculDay();
			dayCount = time/(1000*60*60*24);
			
			hour1 =(time-(dayCount*1000*24*60*60)) / (3600*1000);
			minute1 = (time -(dayCount*1000*24*60*60) - hour1 * 1000* 3600) / (60*1000);
			seconds = (time -(dayCount*1000*24*60*60) - hour1 * 1000* 3600 - minute1 * 1000* 60)/1000;
			milliseconds = time -(dayCount*1000*24*60*60) - hour1 * 1000* 3600 - minute1 * 1000* 60 - seconds * 1000;
		

			jl1.setText(downYear + "年");
			jl2.setText(downMonth + "月");
			jl3.setText(downDay + "日");
			jl4.setText(hour1 + "时");
			jl5.setText(minute1 + "分");
			jl6.setText(seconds + "秒");
			jl7.setText(milliseconds+"");
			jl8.setText("毫秒");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			time=time-1;
			//开始时间前进
//			moveStartTime(startYear,startMonth,startDay,startHour,startMinute,startSeconds,startMilliseconds);
			nowTime();
//			System.out.println(this.startSeconds+","+this.startMilliseconds);
		}
	}
	//startTime进行向前推进
	public void moveStartTime(String year,String month,String day,String hour,String minute,String second,String millisecond){
		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month)-1;
		int d = Integer.parseInt(day);
		int h = Integer.parseInt(hour);
		int minu = Integer.parseInt(minute);
		int s = Integer.parseInt(second);
		int millis = Integer.parseInt(millisecond);
		Calendar c= Calendar.getInstance();
		c.set(Calendar.YEAR,y);
		c.set(Calendar.MONTH, m);
		c.set(Calendar.DAY_OF_MONTH,d);
		c.set(Calendar.HOUR_OF_DAY,h);
		c.set(Calendar.MINUTE, minu);
		c.set(Calendar.SECOND,s);
		c.set(Calendar.MILLISECOND, millis);
		long milli = c.getTimeInMillis();
		c.set(Calendar.MILLISECOND, millis+1);
		this.startYear =String.valueOf(c.get(Calendar.YEAR));
		this.startMonth = String.valueOf(c.get(Calendar.MONTH)+1);
		this.startDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		this.startHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		this.startMinute = String.valueOf(c.get(Calendar.MINUTE));
		this.startSeconds = String.valueOf(c.get(Calendar.SECOND));
		this.startMilliseconds =String.valueOf(c.get(Calendar.MILLISECOND));
	}
	public void nowTime(){
		Calendar c = Calendar.getInstance();//创建当前的一个时间对象
		this.startYear =String.valueOf(c.get(Calendar.YEAR));
		this.startMonth = String.valueOf(c.get(Calendar.MONTH)+1);
		this.startDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		this.startHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		this.startMinute = String.valueOf(c.get(Calendar.MINUTE));
		this.startSeconds = String.valueOf(c.get(Calendar.SECOND));
		this.startMilliseconds =String.valueOf(c.get(Calendar.MILLISECOND));
	}
	//计算年
	public long calculYear(){
		long yea = Integer.parseInt(startYear);
		long result = 0;
		long returnlong = 0;
		for(long i=0 ;true;i++){
			result = endtime -  setStrarttime((yea+i)+"",startMonth,startDay,startHour,startMinute,startSeconds,startMilliseconds);
			if(result>=0){
				continue;
			}else{
				returnlong = i-1;
				break;
			}
		}
		return returnlong;
	}
	//计算月
		public long calculMonth(){
			long yea = Integer.parseInt(startYear)+calculYear();
			long mon = Integer.parseInt(startMonth);
			long result = 0;
			long returnlong = 0;
			long mongthCount = 0;//统计月份
			for(long i=0 ;true;i++,mongthCount++){
				//需要进行判断 
				//超过12个月,重新从一月份开始
				if((mon+i)>12){
					yea ++;
					i = 0;
					mon = 1;
				}
				//判断当前的日子是否超过这个月的最大值
				Calendar c1= Calendar.getInstance();
				c1.set(Calendar.YEAR,(int) yea);
				c1.set(Calendar.MONTH, (int) (mon+i));
				int totalDays = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
				if(Integer.parseInt(startDay) >totalDays){
					result = endtime -  setStrarttime(String.valueOf(yea),(mon+i)+"",totalDays+"",startHour,startMinute,startSeconds,startMilliseconds);
				}else{
					result = endtime -  setStrarttime(String.valueOf(yea),(mon+i)+"",startDay,startHour,startMinute,startSeconds,startMilliseconds);
				}
				
				if(result>=0){
					continue;
				}else{
					returnlong = mongthCount-1;
					break;
				}
			}
			return returnlong;
		}
		//计算天
		public long calculDay(){
			long yea = Integer.parseInt(startYear)+calculYear();
			long mon = Integer.parseInt(startMonth)+calculMonth();
			//判断月份是否是下一年的月份
			if(mon>12){
				yea++;
				mon %=12;
			}
			long da = Integer.parseInt(startDay);
			long result = 0;
			long returnlong = 0;
			for(long i=0 ;true;i++){
				result = endtime -  setStrarttime(String.valueOf(yea),mon+"",(da+i)+"",startHour,startMinute,startSeconds,startMilliseconds);
				if(result>=0){
					continue;
				}else{
					returnlong = i-1;
					break;
				}
			}
			return returnlong;
		}

}
