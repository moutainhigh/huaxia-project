package MyCalender;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.MyButton;

public class Calen extends JFrame implements ActionListener,ItemListener{

	private final static int BLOCKWIDTH = 45;// 每个方格的宽度

	private final static int BLOCKHEIGHT = 23;// 每个方格的长度
	private Date date = new Date();
	private GregorianCalendar gregorianCalendar = new GregorianCalendar();
	private static int width  = 650;
	private static int height = 340;
	public static int year;
	public Calen(int year2){
		super("年度周历表");
		this.setBounds(0,0,1280,950);
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(1,2,0,0));
		this.year= year2;
		MyButton btn = new MyButton();
		btn.setText("下一年");
		pan.add(btn);
		//添加监听器
		btn.addActionListener(new  ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				year++;
				Calen frame = new Calen(year);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			
		});
		MyButton btn2 = new MyButton();
		btn2.setText("上一年");
		pan.add(btn2);
		//添加监听器
		btn2.addActionListener(new  ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				year--;
				Calen frame = new Calen(year);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}	
		});
		
		JPanel c = new JPanel();
	
		this.add(pan,BorderLayout.NORTH);
		this.add(c,BorderLayout.CENTER);
		showCalendar month1 = new showCalendar(year,0,width,height);
		showCalendar month2 = new showCalendar(year,1,width,height);
		showCalendar month3 = new showCalendar(year,2,width,height);
		showCalendar month4 = new showCalendar(year,3,width,height);
		showCalendar month5 = new showCalendar(year,4,width,height);
		showCalendar month6 = new showCalendar(year,5,width,height);
		showCalendar month7 = new showCalendar(year,6,width,height);
		showCalendar month8 = new showCalendar(year,7,width,height);
		showCalendar month9 = new showCalendar(year,8,width,height);
		showCalendar month10 = new showCalendar(year,9,width,height);
		showCalendar month11 = new showCalendar(year,10,width,height);
		showCalendar month12 = new showCalendar(year,11,width,height);
		
		c.setLayout(new GridLayout(4,3,0,0));
		c.add(month1);
		c.add(month5);
		c.add(month9);
		c.add(month2);
		c.add(month6);
		c.add(month10);
		c.add(month3);
		c.add(month7);
		c.add(month11);
		c.add(month4);
		c.add(month8);
		c.add(month12);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String args[]){
		newJFrame();
//		
//		try{
//			Calen frame = new Calen(2019);
//			frame.setVisible(true);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}
	public static void newJFrame(){
		JFrame frame = new JFrame("时间管理软件");
		int year= 1990;
		for(int i=0;i<11;i++){

			JLabel btn2 = new JLabel();
			btn2.setBorder(BorderFactory.createLineBorder(Color.gray));
			// 每个小方格的边界
			// btn.setBounds(i*BLOCKWIDTH,month*BLOCKHEIGHT,BLOCKWIDTH,BLOCKHEIGHT);
			btn2.setPreferredSize(new Dimension(BLOCKWIDTH, BLOCKHEIGHT));
			btn2.setOpaque(true);
			btn2.setText(year + "-"+(year+10));
			Calendar c2 = Calendar.getInstance();//创建当前的一个时间对象
			
			if(year>=2060||(year+10) <c2.get(Calendar.YEAR)){
				
				// 添加监听器
				btn2.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						String str =String.valueOf(((JLabel)arg0.getSource()).getText());
						String fileName = "C:/Users/User/Desktop/JavaFile/calendar/" + str + ".txt";
						String returnString = readFileByLine(fileName);
						JFrame Jframe = new JFrame();
						JPanel japenl = new JPanel();
						JLabel jlabel = new JLabel();
						TextArea textArea = new TextArea(50, 120);
						textArea.setText(returnString);
						textArea.addTextListener(new ClickListener(fileName));
						jlabel.setText(str);
						japenl.add(jlabel, BorderLayout.NORTH);
						japenl.add(textArea, BorderLayout.CENTER);
						Jframe.setSize(900, 900);
						Jframe.add(japenl, BorderLayout.CENTER);
						Jframe.setVisible(true);
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
			}else{
				btn2.setBackground(Color.white);
				// 添加监听器
				btn2.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						String str =String.valueOf(((JLabel)arg0.getSource()).getText());
						String fileName = "C:/Users/User/Desktop/JavaFile/calendar/" + str + ".txt";
						String returnString = readFileByLine(fileName);
						JFrame Jframe = new JFrame();
						JPanel japenl = new JPanel();
						JLabel jlabel = new JLabel();
						TextArea textArea = new TextArea(50, 120);
						textArea.setText(returnString);
						textArea.addTextListener(new ClickListener(fileName));
						jlabel.setText(str);
						japenl.add(jlabel, BorderLayout.NORTH);
						japenl.add(textArea, BorderLayout.CENTER);
						Jframe.setSize(900, 900);
						Jframe.add(japenl, BorderLayout.CENTER);
						Jframe.setVisible(true);
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
			}
			frame.add(btn2);
			for(int j=0;j<10;j++){
				JLabel btn = new JLabel();
				btn.setBorder(BorderFactory.createLineBorder(Color.gray));
				// 每个小方格的边界
				// btn.setBounds(i*BLOCKWIDTH,month*BLOCKHEIGHT,BLOCKWIDTH,BLOCKHEIGHT);
				btn.setPreferredSize(new Dimension(BLOCKWIDTH, BLOCKHEIGHT));
				btn.setOpaque(true);
				btn.setText(year + "");
				Calendar c = Calendar.getInstance();//创建当前的一个时间对象
				
				if(year>=2060||year <c.get(Calendar.YEAR)){
					// 添加监听器
					btn.addMouseListener(new MouseListener() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							int y = Integer.parseInt(String.valueOf(((JLabel)arg0.getSource()).getText()));
							Calen frame = new Calen(y);
							frame.setVisible(true);
							//弹出框
							String str =String.valueOf(((JLabel)arg0.getSource()).getText());
							String fileName = "C:/Users/User/Desktop/JavaFile/calendar/" + str + "/"+str +".txt";
							String returnString = readFileByLine(fileName);
							JFrame Jframe = new JFrame();
							JPanel japenl = new JPanel();
							JLabel jlabel = new JLabel();
							TextArea textArea = new TextArea(50, 120);
							textArea.setText(returnString);
							textArea.addTextListener(new ClickListener(fileName));
							jlabel.setText(str);
							japenl.add(jlabel, BorderLayout.NORTH);
							japenl.add(textArea, BorderLayout.CENTER);
							Jframe.setSize(900, 900);
							Jframe.add(japenl, BorderLayout.CENTER);
							Jframe.setVisible(true);
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
				}else{
					btn.setBackground(Color.white);
					// 添加监听器
					btn.addMouseListener(new MouseListener() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							int y = Integer.parseInt(String.valueOf(((JLabel)arg0.getSource()).getText()));
							Calen frame = new Calen(y);
							frame.setVisible(true);
							//时间计划
							String str =String.valueOf(((JLabel)arg0.getSource()).getText());
							String fileName = "C:/Users/User/Desktop/JavaFile/calendar/" + str + "/"+str +".txt";
							String returnString = readFileByLine(fileName);
							JFrame Jframe = new JFrame();
							JPanel japenl = new JPanel();
							JLabel jlabel = new JLabel();
							TextArea textArea = new TextArea(50, 120);
							textArea.setText(returnString);
							textArea.addTextListener(new ClickListener(fileName));
							jlabel.setText(str);
							japenl.add(jlabel, BorderLayout.NORTH);
							japenl.add(textArea, BorderLayout.CENTER);
							Jframe.setSize(900, 900);
							Jframe.add(japenl, BorderLayout.CENTER);
							Jframe.setVisible(true);
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
				}
				frame.add(btn);
				year++;
			}
		}
		frame.setBackground(Color.white);
		frame.setLayout(new GridLayout(11,11));
		frame.setBounds(0,0,1000,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	// 用行读取文件
				public static  String readFileByLine(String fileName) {
					//如果目录不存在，创建新的目录
					File file = new File(fileName);
					File fileParenet = file.getParentFile();
					if(!fileParenet.exists()){
						fileParenet.mkdirs();
					}
					try {
						file.createNewFile();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					ArrayList<String> arrayList = new ArrayList<>();
					String returnString = "";
					try {
						System.out.println("用行读取文件");
					
						if (file.exists()) {
							System.out.println("文件存在！");
						} else {
							file.createNewFile();
						}
						FileReader ft = new FileReader(fileName);
						BufferedReader bf = new BufferedReader(ft);
						String str;
						while ((str = bf.readLine()) != null) {
							arrayList.add(str);
						}
						bf.close();
						ft.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					for (int i = 0; i < arrayList.size(); i++) {
						String s = arrayList.get(i);
						returnString += s + "\r\n";
					}
					return returnString;
				}

				// 写文件的类
				public static void writeFileByBytes(String fileName, String content) {
					//如果目录不存在，创建新的目录
					File file = new File(fileName);
					File fileParenet = file.getParentFile();
					if(!fileParenet.exists()){
						fileParenet.mkdirs();
					}
					try {
						file.createNewFile();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					OutputStream out = null;
					try {
						out = new FileOutputStream(file);

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
static class ClickListener implements TextListener{
	private String fileName ;
	public ClickListener(String fileName){
		this.fileName = fileName;
	}
	@Override
	public void textValueChanged(TextEvent e) {
		// TODO Auto-generated method stub
		writeFileByBytes(fileName,((TextArea)e.getSource()).getText());
	}
}
}