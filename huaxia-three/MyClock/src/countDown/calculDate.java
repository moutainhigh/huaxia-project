package countDown;

import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * 弹出面板，显示计算的时间
 * @author liuwei
 *
 */
public class calculDate extends JFrame{
	private String  endYear = "2021";
	private String  endMonth = "3";
	private String  endDay = "24";
	private String  endHour = "10";
	private String  endMinute = "30";
	private String  endSeconds = "31";
	private String endMilliseconds ="888";
	
	private JLabel resultJLabel ;
	public calculDate(String year,String month,String day,String hour,String minute,String second,String milliSecond){
		this.endYear =year;
		this.endMonth = month;
		this.endDay = day;
		this.endHour = hour;
		this.endMinute = minute;
		this.endSeconds = second;
		this.endMilliseconds =milliSecond;
		
		this.setVisible(true);
		this.setLocation(400,200);
		this.setSize(300,500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String args[]){
		Calendar c = Calendar.getInstance();//创建当前的一个时间对象
		calculDate cal = new calculDate(String.valueOf(c.get(Calendar.YEAR)),String.valueOf(c.get(Calendar.MONTH)+1),String.valueOf(c.get(Calendar.DAY_OF_MONTH)),String.valueOf(c.get(Calendar.HOUR_OF_DAY)),String.valueOf(c.get(Calendar.MINUTE)),String.valueOf(c.get(Calendar.SECOND)),"000");
	}
}
