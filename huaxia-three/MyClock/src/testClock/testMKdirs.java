package testClock;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class testMKdirs {
	public static void main(String args[]){
	String	fileName = "C:/Users/User/Desktop/JavaFile/2019/7/" + 2019 + "年" + (4+1) + "月" + ".txt";
		writeFileByBytes(fileName,"Hello World");
		readFileByLine(fileName);
	}
	// 用行读取文件
		public static String readFileByLine(String fileName) {
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
}
