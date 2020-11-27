package MyCalender;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MyCalender.Calen.ClickListener;
import projectExample.BytesWriteFile;

public class showCalendar extends JPanel {

	private final static int BLOCKWIDTH = 45;// 每个方格的宽度

	private final static int BLOCKHEIGHT = 23;// 每个方格的长度

	static final String head[] = { "周次", "周一", "周二", "周三", "周四", "周五", "周六", "周七" };
	static int weekCount = 0;
	static String str = "";
	private  int yearFile;// 文件中用到的年份
	private  int monthFile;// 文件中用到的月份
	private  int dayFile;// 文件中用到的日份
	public showCalendar(int year, int month, int width, int height) {
		this.yearFile = year;
		this.monthFile = month;
		JLabel m = new JLabel();
		m.setText(year + "年" + (month + 1) + "月份");
		;
		m.setPreferredSize(new Dimension(400, 25));
		m.setBorder(BorderFactory.createLineBorder(Color.gray));
		// 添加监听器
		m.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String	fileName = "C:/Users/User/Desktop/JavaFile/calendar/"+ yearFile  +"/"+ (monthFile+1)  +"/"+yearFile+"年" + (monthFile+1) + "月" + ".txt";
				String returnString = readFileByLine(fileName);
				JFrame frame = new JFrame();
				JPanel japenl = new JPanel();
				JLabel jlabel = new JLabel();
				TextArea textArea = new TextArea(50, 120);
				textArea.setText(returnString);
				textArea.addTextListener(new ClickListener(fileName));
				jlabel.setText(yearFile + "年" + (monthFile+1) + "月");
				japenl.add(jlabel, BorderLayout.NORTH);
				japenl.add(textArea, BorderLayout.CENTER);
				frame.setSize(900, 900);
				frame.add(japenl, BorderLayout.CENTER);
				frame.setVisible(true);
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
		this.add(m);
		showYear(year, month);

		this.setPreferredSize(new Dimension(width, height));
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		// System.out.println("请输入年份：");
		// int year = in.nextInt();
		// System.out.println("请输入月份：");
		// int month = in.nextInt() - 1;//月份从0开始
		in.close();
		// new showCalendar().showYear(2019);
		String fileName = "C:/Users/User/Desktop/JavaFile/myFile.txt";
		System.out.println("以字节为单位写文件");
		BytesWriteFile.writeFileByBytes(fileName, str);
		// System.out.println(str);
		// int year = 2019;
		// int month = 0;

	}

	public void showYear(int year, int month) {
		weekCount = 1;
		for (int i = 0; i < 12; i++) {
			str += "\r\n" + (i + 1) + "月份" + "\r\n";
			System.out.println((i + 1) + "月份");
			showMonth(year, i, month);
			System.out.println();
		}

	}

	public void showMonth(int year, int month, int mon) {
		int i;
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		// 获取这个月的天数
		int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// System.out.println(totalDays);
		// 获取星期几
		int startDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (startDay == 0)
			startDay = 7; // 国外的第一天是星期日
		// System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		// System.out.println(startDay);
		// System.out.println(startDay);
		// 输出头部星期几
		for (i = 0; i < head.length; i++) {
			str += head[i] + "	";
			System.out.print(head[i] + "	");
			if (month == mon) {
				JLabel btn = new JLabel();
				btn.setBorder(BorderFactory.createLineBorder(Color.gray));
				// 每个小方格的边界
				// btn.setBounds(i*BLOCKWIDTH,month*BLOCKHEIGHT,BLOCKWIDTH,BLOCKHEIGHT);
				btn.setPreferredSize(new Dimension(BLOCKWIDTH, BLOCKHEIGHT));
				btn.setText(head[i] + "	");
				this.add(btn);
			}
		}
		str += "\r\n";
		System.out.println();
		// 输出一天前的空格
		str += new String(weekCount + " 	");
		if (month == mon) {
			JLabel btn2 = new JLabel();
			// 每个小方格的边界
			btn2.setBorder(BorderFactory.createLineBorder(Color.gray));
			btn2.setPreferredSize(new Dimension(BLOCKWIDTH, BLOCKHEIGHT));
			btn2.setText(weekCount + "");
			// 添加监听器
			btn2.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					int week = Integer.parseInt(((JLabel)arg0.getSource()).getText());
					String fileName = "C:/Users/User/Desktop/JavaFile/calendar/"+ yearFile  +"/"+ (monthFile+1)  +"/" + yearFile + "年" + (monthFile+1) + "月" +week+"周"+ ".txt";
					String returnString = readFileByLine(fileName);
					JFrame frame = new JFrame();
					JPanel japenl = new JPanel();
					JLabel jlabel = new JLabel();
					TextArea textArea = new TextArea(50, 120);
					textArea.setText(returnString);
					textArea.addTextListener(new ClickListener(fileName));
					jlabel.setText(yearFile + "年" + (monthFile+1) + "月" +week+"周");
					japenl.add(jlabel, BorderLayout.NORTH);
					japenl.add(textArea, BorderLayout.CENTER);
					frame.setSize(900, 900);
					frame.add(japenl, BorderLayout.CENTER);
					frame.setVisible(true);
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
			this.add(btn2);
		}
		System.out.printf("%2d	", weekCount);
		for (i = 1; i < startDay; i++) {
			str += "	";
			System.out.print("	");
			if (month == mon) {
				JLabel btn = new JLabel();
				// 每个小方格的边界
				btn.setBorder(BorderFactory.createLineBorder(Color.gray));
				btn.setPreferredSize(new Dimension(BLOCKWIDTH, BLOCKHEIGHT));
				btn.setText("  ");
				this.add(btn);
			}
		}
		// 依次输出每一天

		for (int day = 1; day <= totalDays; day++) {
		
			str += new String(day + " 	");
			System.out.printf("%2d	", day);
			if (month == mon) {
				JLabel btn = new JLabel();
				// 每个小方格的边界
				btn.setBorder(BorderFactory.createLineBorder(Color.gray));
				btn.setPreferredSize(new Dimension(BLOCKWIDTH, BLOCKHEIGHT));
				btn.setText(day+"");
				// 添加监听器
				btn.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						dayFile =Integer.parseInt(((JLabel) arg0.getSource()).getText());
						// TODO Auto-generated method stub
						String fileName = "C:/Users/User/Desktop/JavaFile/calendar/"+ yearFile  +"/"+ (monthFile+1)  +"/" + yearFile + "年" + (monthFile+1) + "月" +dayFile+"日"+ ".txt";
						String returnString = readFileByLine(fileName);
						JFrame frame = new JFrame();
						JPanel japenl = new JPanel();
						JLabel jlabel = new JLabel();
						TextArea textArea = new TextArea(50, 120);
						textArea.setText(returnString);
						textArea.addTextListener(new ClickListener(fileName));
						jlabel.setText(yearFile + "年" + (monthFile+1) + "月" +dayFile+"日");
						japenl.add(jlabel, BorderLayout.NORTH);
						japenl.add(textArea, BorderLayout.CENTER);
						frame.setSize(900, 900);
						frame.add(japenl, BorderLayout.CENTER);
						frame.setVisible(true);
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
				this.add(btn);
			}
			if (i == 7) {
				weekCount++;
				if ((day + 1) <= totalDays) {// 处理一下每月月末最后一天正好是周末的情况
					str += "\r\n";
					System.out.println();
					str += new String(weekCount + " 	");
					System.out.printf("%2d	", weekCount);
					if (month == mon) {
						JLabel btn3 = new JLabel();
						// 每个小方格的边界
						btn3.setBorder(BorderFactory.createLineBorder(Color.gray));
						btn3.setPreferredSize(new Dimension(BLOCKWIDTH, BLOCKHEIGHT));
						btn3.setText(weekCount + "");
						// 添加监听器
						btn3.addMouseListener(new MouseListener() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								int week = Integer.parseInt(((JLabel)arg0.getSource()).getText());
								String	fileName = "C:/Users/User/Desktop/JavaFile/calendar/"+ yearFile  +"/"+ (monthFile+1)  +"/" + yearFile + "年" + (monthFile+1) + "月" +week+"周"+ ".txt";
								String returnString = readFileByLine(fileName);
								JFrame frame = new JFrame();
								JPanel japenl = new JPanel();
								JLabel jlabel = new JLabel();
								TextArea textArea = new TextArea(50, 120);
								textArea.setText(returnString);
								textArea.addTextListener(new ClickListener(fileName));
								jlabel.setText( yearFile + "年" + (monthFile+1) + "月" +week+"周");
								japenl.add(jlabel, BorderLayout.NORTH);
								japenl.add(textArea, BorderLayout.CENTER);
								frame.setSize(900, 900);
								frame.add(japenl, BorderLayout.CENTER);
								frame.setVisible(true);
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
						this.add(btn3);
					}
				} else {
					i = 97;
					continue;
				}
				i = 0;

			}
			i++;
		}
		int temp = i;
		for (; i <= 7; i++) {
			if (month == mon) {
				JLabel btn = new JLabel();
				// 每个小方格的边界
				btn.setBorder(BorderFactory.createLineBorder(Color.gray));
				btn.setPreferredSize(new Dimension(BLOCKWIDTH, BLOCKHEIGHT));
				btn.setText("  ");
				this.add(btn);
			}
		}
		i = temp;

	}

	// 用行读取文件
			public  String readFileByLine(String fileName) {
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
			public void writeFileByBytes(String fileName, String content) {
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
			 class ClickListener implements TextListener{
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
