package com.huaxia.opas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
//压缩单个文件
public class ZipMultiFile {

	public static void main(String[] args) {
		/*File srcFiles = (new File("D:\\1.xml"));
		File zipFile = new File("D:\\d.zip");
		zipFiles(srcFiles,zipFile);*/
	}
	
	
	public static void zipFile(File srcFiles, File zipFile) {
		
		//判断压缩后的文件存在不，不存在则创建
		if (! zipFile.exists()){
			try {
				zipFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//
		FileOutputStream fileOutputStream = null;
		
		//
		ZipOutputStream zipOutputStream = null;
		
		//
		FileInputStream fileInputStream = null;
		try {
			fileOutputStream = new FileOutputStream(zipFile);
			zipOutputStream = new ZipOutputStream(fileOutputStream);
			ZipEntry zipEntry = null;
			
			//for (int i = 0; i <= srcFiles.length; i++) {
			fileInputStream = new FileInputStream(srcFiles);
			zipEntry = new ZipEntry(srcFiles.getName());
			zipOutputStream.putNextEntry(zipEntry);
			
			int len;
			byte[] buffer = new byte[1024];
			while((len=fileInputStream.read(buffer))>0){
				zipOutputStream.write(buffer,0,len);
			}
			//}
			zipOutputStream.closeEntry();
			zipOutputStream.close();
			fileInputStream.close();
			fileOutputStream.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//压缩多个
	public static void zipFiles(File[] srcFiles, File zipFile) {
		
		//判断压缩后的文件存在不，不存在则创建
		if (! zipFile.exists()){
			try {
				zipFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//
		FileOutputStream fileOutputStream = null;
		
		//
		ZipOutputStream zipOutputStream = null;
		
		//
		FileInputStream fileInputStream = null;
		try {
			fileOutputStream = new FileOutputStream(zipFile);
			zipOutputStream = new ZipOutputStream(fileOutputStream);
			ZipEntry zipEntry = null;
			
			for (int i = 0; i <= srcFiles.length; i++) {
				fileInputStream = new FileInputStream(srcFiles[i]);
				zipEntry = new ZipEntry(srcFiles[i].getName());
				zipOutputStream.putNextEntry(zipEntry);
				
				int len;
				byte[] buffer = new byte[1024];
				while((len=fileInputStream.read(buffer))>0){
					zipOutputStream.write(buffer,0,len);
				}
			}
			zipOutputStream.closeEntry();
			zipOutputStream.close();
			fileInputStream.close();
			fileOutputStream.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
