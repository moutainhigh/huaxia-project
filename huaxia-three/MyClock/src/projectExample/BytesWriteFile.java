package projectExample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BytesWriteFile {

	public static void main(String args[]){
		 String fileName = "C:/Users/User/Desktop/JavaFile/myFile.txt";
		 System.out.println("以字节为单位写文件");
		 String content = "Hello World!你好，美女";
		 BytesWriteFile.writeFileByBytes(fileName,content);
	}
	//写文件的类
	public static void writeFileByBytes(String fileName,String content){
		File file = new File(fileName);
		OutputStream out = null;
		try{
			out = new FileOutputStream(file);
			
			byte[] bytes = content.getBytes();
			out.write(bytes);
			System.out.println("写文件"+file.getAbsolutePath()+"成功！");
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			//关闭输出流
			if(out != null){
				try{
					out.close();
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
				
	}
}
