package countDown;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwitchTest {

	private JFrame frame;
	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;
	Font font = new Font("宋体",Font.BOLD,36);
	public SwitchTest() {
		frame = new JFrame("下班倒计时");
		jl1 = new JLabel();
		jl2 = new JLabel();
		jl3 = new JLabel();
		jl1.setFont(font);
		jl2.setFont(font);
		jl3.setFont(font);
		jl1.setBackground(Color.white);
		jl2.setBackground(Color.white);
		jl3.setBackground(Color.white);
		init();
	}

	public static void main(String args[]){
		new SwitchTest().forMethod(0);
	}
	//循环调用的方法
	public  void forMethod(int add){
			Calendar c= Calendar.getInstance();
			long year =  c.get(Calendar.YEAR);
			long month = c.get(Calendar.MONTH)+1;
			long day =  c.get(Calendar.DAY_OF_MONTH)+add;
			long hour =  20;
			long minute =  0;
			long seconds =  0;
			getTime(""+year,""+month,""+day,""+hour,"00","00");
		}
		
	// 初始化
	private void init() {
		JPanel jp = new JPanel();
		jp.add(jl1);
		jp.add(jl2);
		jp.add(jl3);
		frame.add(jp);
		jp.setBackground(Color.white);
		frame.setVisible(true);
		frame.setLocation(300, 400);
		frame.setSize(300, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void getTime(String yea,String mont,String da,String hou,String minu,String secon) {
		long time = time(yea,mont,da,hou,minu,secon);
		long hour = 0;
		long minute = 0;
		long seconds = 0;

		while (time >= 0) {
			
			hour = time / 3600;
			minute = (time - hour * 3600) / 60;
			seconds = time - hour * 3600 - minute * 60;
			jl1.setText(hour + "时");
			jl2.setText(minute + "分");
			jl3.setText(seconds + "秒");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time--;
		}
		{
			new AlterPanel("下班了").alter();
			forMethod(1);
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
