package JavaMultiThreadProgramming.ThreadState.ThreadGroup;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.el.parser.ParseException;

public class MyThread2 extends Thread {
	private SimpleDateFormat sdf;
	private String dateString;
	public MyThread2(SimpleDateFormat sdf ,String dateString){
		super();
		this.sdf = sdf;
		this.dateString = dateString;
	}
	@Override
	public void run(){
		try{
			Date dateRef = DateTools.parse("yyyy-MM-dd",dateString);
			String newDateString = DateTools.format("yyyy-MM-dd",dateRef).toString();
			if(! newDateString.equals(dateString)){
				System.out.println("ThreadName"+Thread.currentThread().getName()+" 报错了 日期字符串："+dateString +" 转换成日期为"+newDateString);
			}
		}catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
