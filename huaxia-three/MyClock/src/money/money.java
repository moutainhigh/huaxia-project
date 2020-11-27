package money;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;

public class money extends JFrame{

	private TextArea ta ;
	String	fileName = "C:/Users/User/Desktop/JavaFile/money.txt";
	public money(String name){
		super(name);
		String returnString = readFileByLine(fileName);
		ta = new TextArea(10,20);
		ta.setText(returnString);
		ta.addTextListener(new TextListener(){//写文件
			@Override
			public void textValueChanged(TextEvent e) {
				// TODO Auto-generated method stub
			
				writeFileByBytes(fileName,((TextArea)e.getSource()).getText());
			}
		});
		this.add(ta,BorderLayout.CENTER);
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(300,300);
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]){
		money money = new money("我的账单");
	}
	
	// 用行读取文件
		public String readFileByLine(String fileName) {
			ArrayList<String> arrayList = new ArrayList<>();
			String returnString = "";
			try {
				System.out.println("用行读取文件");
				File file = new File(fileName);
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
			File file = new File(fileName);
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
}
