package countDown;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import milclock.myPanel;

public class MyDefineCountDown extends JFrame{
	//现在时间的控件
	private JPanel nowTimePanel;
	private myPanel nowTime;
	private JLabel nowtimeLabel ;
	Font font = new Font("宋体",Font.BOLD,20);
	private JPanel testPanel;
	private newTimeDown2 jpanel;
	private static JLabel jlabel ;
	
	private JPanel inputPanel ;
	private JTextField  jtextTitle;
	private JTextField  jtextYear;
	private JTextField  jtextMonth;
	private JTextField  jtextDay;
	private JTextField  jtextHour;
	private JTextField  jtextMinute;
	private JTextField  jtextSecond;
	private JButton jbutton;
	public MyDefineCountDown(String title){
		super(title);
		Calendar c = Calendar.getInstance();//创建当前的一个时间对象
	
		nowTimePanel = new JPanel();
		nowTimePanel.setLayout(new GridLayout(2,1));
		nowTimePanel.setBackground(Color.white);
		nowTime = new myPanel();
		nowTime.setPreferredSize(new Dimension(650, 50));
		nowTime.setBackground(Color.white);
		nowtimeLabel = new JLabel();
		nowtimeLabel.setPreferredSize(new Dimension(650, 50));
		nowtimeLabel.setText("现在时间");
		nowtimeLabel.setBackground(Color.white);
		nowtimeLabel.setFont(font);
		nowTimePanel.add(nowtimeLabel);
		nowTimePanel.add(nowTime);
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(2,1));
		inputPanel.setBackground(Color.white);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.white);
		JLabel titleJLabel = new JLabel("输入任务");
		titleJLabel.setBackground(Color.white);
		jtextTitle = new JTextField(50);
		jtextTitle.setText("自定义倒计时");
		jtextTitle.setBackground(Color.white);
		titlePanel.add(titleJLabel);
		titlePanel.add(jtextTitle);
		
		JPanel datePanel = new JPanel();
		datePanel.setBackground(Color.white);
		JLabel inputYear = new JLabel("年");
		inputYear.setBackground(Color.white);
		jtextYear = new JTextField(5);
		jtextYear.setText(c.get(Calendar.YEAR)+"");
		jtextYear.setBackground(Color.white);
		JLabel inputMonth = new JLabel("月");
		inputMonth.setBackground(Color.white);
		jtextMonth = new JTextField(5);
		jtextMonth.setText((c.get(Calendar.MONTH)+1)+"");
		jtextMonth.setBackground(Color.white);
		JLabel inputDay = new JLabel("日");
		inputDay.setBackground(Color.white);
		jtextDay = new JTextField(5);
		jtextDay.setText(c.get(Calendar.DAY_OF_MONTH)+"");
		jtextDay.setBackground(Color.white);
		JLabel inputHour = new JLabel("时");
		inputHour.setBackground(Color.white);
		jtextHour = new JTextField(5);
		jtextHour.setText((c.get(Calendar.HOUR_OF_DAY)+1)+"");
		jtextHour.setBackground(Color.white);
		JLabel inputMinute = new JLabel("分");
		inputMinute.setBackground(Color.white);
		jtextMinute = new JTextField(5);
		jtextMinute.setText(c.get(Calendar.MINUTE)+"");
		jtextMinute.setBackground(Color.white);
		JLabel inputSecond = new JLabel("秒");
		inputSecond.setBackground(Color.white);
		jtextSecond = new JTextField(5);
		jtextSecond.setText(c.get(Calendar.SECOND)+"");
		jtextSecond.setBackground(Color.white);
		jbutton = new JButton();
		jbutton.setText("确定");
		jbutton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			JPanel	jpanel = new JPanel();
			jpanel.setLayout(new GridLayout(2,1));
			jpanel.setBackground(Color.white);
			String year = jtextYear.getText();
			String month = jtextMonth.getText();
			String day = jtextDay.getText();
			String hour = jtextHour.getText();
			String minute = jtextMinute.getText();
			String second = jtextSecond.getText();
			if(year.equals("")){
				year = "2019";
			}
			if(month.equals("")){
				month = "04";
			}
			if(day.equals("")){
				day = "28";
			}
			if(hour.equals("")){
				hour = "20";
			}
			if(minute.equals("")){
				minute = "00";
			}
			if(second.equals("")){
				second = "00";
			}
			newTimeDown2	myCountDown = new newTimeDown2(year,month,day,hour,minute,second,"000");
			myCountDown.setPreferredSize(new Dimension(650, 50));
			myCountDown.setBackground(Color.white);
			JPanel jp = new JPanel();
			jp.setBackground(Color.white);
			JLabel jabel = new JLabel();
			jabel.setPreferredSize(new Dimension(300, 50));
			String textTitle = jtextTitle.getText();
			if(textTitle.equals("")){
				textTitle = "自定义倒计时";
			}
			jabel.setText(textTitle);
			jabel.setBackground(Color.white);
			jabel.setFont(font);
			JButton jbut = new JButton();
			jbut.setText("删除");
			JButton jbut2 = new JButton();
			jbut2.setText("详细信息");
			JButton jbut3 = new JButton();
			jbut3.setText("完成");
			
			jp.add(jabel);
			jp.add(jbut);
			jp.add(jbut3);	
			jp.add(jbut2);	
				jpanel.add(jp);
				jpanel.add(myCountDown);
			    add(jpanel);
			    myThread t=  new myThread(myCountDown);
			      t.start();
			 jbut.addMouseListener(new MyListener(jpanel,t));
			jbut2.addMouseListener(new MyListenerDetail(year,month,day,hour,minute,second,"000"));
			jbut3.addMouseListener(new ComplementListener(myCountDown));
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		datePanel.add(jtextYear);
		datePanel.add(inputYear);
		datePanel.add(jtextMonth);
		datePanel.add(inputMonth);
		datePanel.add(jtextDay);
		datePanel.add(inputDay);	
		datePanel.add(jtextHour);
		datePanel.add(inputHour);
		datePanel.add(jtextMinute);
		datePanel.add(inputMinute);
		datePanel.add(jtextSecond);
		datePanel.add(inputSecond);
		datePanel.add(jbutton);
		
		inputPanel.add(titlePanel);
		inputPanel.add(datePanel);
		
		testPanel = new JPanel();
		testPanel.setLayout(new GridLayout(2,1));
		testPanel.setBackground(Color.white);
		jpanel = new newTimeDown2("2060","01","01","00","00","00","000");
		jpanel.setPreferredSize(new Dimension(650, 50));
		jpanel.setBackground(Color.white);
		jlabel = new JLabel();
		jlabel.setPreferredSize(new Dimension(650, 100));
		jlabel.setText("生命倒计时,2060年1月1日");
		jlabel.setBackground(Color.white);
		jlabel.setFont(font);
		testPanel.add(jlabel);
		testPanel.add(jpanel);
		
		this.add(inputPanel);
		this.add(nowTimePanel);
//		this.add(testPanel);
		this.setBackground(Color.white);
		this.setLayout(new GridLayout(8,1));
		this.setVisible(true);
		this.setLocation(200, 000);
		this.setSize(650, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static MyDefineCountDown myDefineCountDown;
	public static void main(String args[]){
		myDefineCountDown = new MyDefineCountDown("自定义倒计时");
	
	
	}
	//删除按钮对应的事件
	class MyListener implements MouseListener{
		JPanel jpanel ;
		myThread  t ;
		public MyListener(JPanel jpanel,myThread t){
			this.jpanel = jpanel;
			this.t = t;
		}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t.stop();
				remove(jpanel);
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
	//详情按钮对应的事件
	class MyListenerDetail implements MouseListener{
		private String  endYear = "2021";
		private String  endMonth = "3";
		private String  endDay = "24";
		private String  endHour = "10";
		private String  endMinute = "30";
		private String  endSeconds = "31";
		private String endMilliseconds ="888";
		public MyListenerDetail(String year,String month,String day,String hour,String minute,String second,String milliSecond){
			this.endYear =year;
			this.endMonth = month;
			this.endDay = day;
			this.endHour = hour;
			this.endMinute = minute;
			this.endSeconds = second;
			this.endMilliseconds =milliSecond;
		}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				calculDate cal = new calculDate(endYear,endMonth,endDay,endHour,endMinute,endSeconds,endMilliseconds);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
	//完成按钮对应的响应事件
	class ComplementListener implements MouseListener{
		newTimeDown2 NewTimeDown2;
		public ComplementListener(newTimeDown2 NewTimeDown2){
			this.NewTimeDown2 = NewTimeDown2;
		}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Calendar c = Calendar.getInstance();//创建当前的一个时间对象
				NewTimeDown2.setEndYear(String.valueOf(c.get(Calendar.YEAR)));
				NewTimeDown2.setEndMonth(String.valueOf(c.get(Calendar.MONTH)+1));
				NewTimeDown2.setEndDay(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
				NewTimeDown2.setEndHour(String.valueOf(c.get(Calendar.HOUR_OF_DAY)));
				NewTimeDown2.setEndMinute(String.valueOf(c.get(Calendar.MINUTE)));
				NewTimeDown2.setEndSeconds(String.valueOf(c.get(Calendar.SECOND)));
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
class myThread extends Thread{
	newTimeDown2 NewTimeDown2;
	public myThread(newTimeDown2 NewTimeDown){
		this.NewTimeDown2 = NewTimeDown;
	}
	public void run(){
		//开始时间和结束时间，倒计时结束，添加日志
		Calendar c = Calendar.getInstance();//创建当前的一个时间对象
		String content =jtextTitle.getText()+"：" ;
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒  SSS毫秒");
		content += format.format(new Date());
		this.NewTimeDown2.getTime();
		content +="------"+format.format(new Date());
		content +="\r\n";
		String fileName = "C:/Users/User/Desktop/JavaFile/日志统计" + ".txt";
		writeFileByBytes(fileName,content);
	}
	// 写文件的类
			public void writeFileByBytes(String fileName, String content) {
				File file = new File(fileName);
				OutputStream out = null;
				try {
					out = new FileOutputStream(file,true);

					byte[] bytes = content.getBytes();
					out.write(bytes);
					System.out.println("写文件" + file.getAbsolutePath() + "成功！");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// 关闭输出流
					if (out != null) {
						try {
							out.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}

			}
}
}