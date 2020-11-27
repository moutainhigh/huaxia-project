package projectExample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;

public class ByteReadFile {

	public static void main(String args[]){
		 String fileName = "C:/Users/User/Desktop/JavaFile/myFile2.txt";
		 System.out.println("以字节为单位读取文件");
		 ByteReadFile.readFielByBytes(fileName);
		 ByteReadFile.readFileByChars(fileName);
		 ByteReadFile.readFileByLine(fileName);
	}
	
	public static void readFielByBytes(String fileName){
		File file = new File(fileName);
		FileInputStream in = null;
		try{
			System.out.println("以字节为单位进行文件的读取，一次读取一个字节：");
			in = new FileInputStream(fileName);
			String s= "";
			int tempbyte;
			while((tempbyte = in.read()) != -1){
				s+=(byte)tempbyte;
				System.out.write(tempbyte);
			}
			System.out.flush();
			System.out.println("s="+s);
			System.out.println();
			in.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		//一次读取多个字节
		try{
		
			System.out.println("以字节己为单位进行文件的读取，一次读取多个字节：");
			byte[]  tempbytes = new byte[100];
			//byteread 为一次读取的字符数目
			int byteread = 0 ;
			in = new FileInputStream(fileName);
			ByteReadFile.showAvailableBytes(in);
			while((byteread = in.read(tempbytes)) != -1){
				System.out.write(tempbytes,0,byteread);
			}
			System.out.flush();
			System.out.println();
			in.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	private static void showAvailableBytes(InputStream in){
		//显示输入流中还剩的字节数目
		try{
			System.out.println("当前字节输入流中的字节数为："+in.available());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	//下面是以字符为单位进行文件的读取，例如汉字占用了两个字节，可以用字符进行读取
	public static void readFileByChars(String fileName){
		File file =new File(fileName);
		Reader read = null;
		try{
			System.out.println("以字符为单位读取文件内容，一次读一个字符:");
			read = new FileReader(file);
			int tempchar;
			while((tempchar = read.read()) != -1){
				System.out.print((char)tempchar);
			}
			System.out.println();
			read.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			System.out.println("以字符为单位读取文件内容，一次读多个个字符:");
			char[]  tempchars = new char[1024];
			//byteread 为一次读取的字符数目
			int charread = 0 ;
			String s= "";
			read = new FileReader(file);
			
			while((charread = read.read(tempchars)) != -1){
				s += tempchars;
				System.out.print(tempchars);
			}
			System.out.flush();
			
			System.out.println();
			System.out.println("s="+s.toString());
			read.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//用行读取文件
	public static void readFileByLine(String fileName){
		ArrayList<String> arrayList = new ArrayList<>();
		String returnString = "";
		try{
			System.out.println("用行读取文件");
			File file =new File(fileName);
			if(file.exists()){
				System.out.println("文件存在！");
			}else{
				file.createNewFile();
			}
			FileReader ft= new FileReader(fileName);
			
			BufferedReader bf = new BufferedReader(ft);
			String str;
			while((str=bf.readLine()) != null){
				arrayList.add(str);
			}
			bf.close();
			ft.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		for(int i = 0 ;i< arrayList.size();i++){
			String s= arrayList.get(i);
			returnString+=s+"\r\n";
		}
		System.out.println(returnString);
	}
}
