package com.huaxia.opas.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PDF2SWFUtil {
	/**
	 * 判断是否是windows操作系统
	 * @author iori
	 * @return
	 */
	private static boolean isWindowsSystem() {
		String p = System.getProperty("os.name");
		return p.toLowerCase().indexOf("windows") >= 0 ? true:false;
	}
	
	public static synchronized Map<String,String> pdf2swf(String fileDir, String exePath,String linuxDir,String projWorkSpace) throws IOException, InterruptedException{
		Map<String, String> paroperMap = FileUploadProperties.getParoperMap();
		//文件路径
		String flashfile = projWorkSpace+"\\flashfile.swf";
		//String linuxDir = CommonUtil.FirstSwfPath;//\\sd.pdf
		String filePath = fileDir.substring(0, fileDir.lastIndexOf("\\"));
		//文件名，不带后缀
		String fileName = fileDir.substring((filePath.length() + 1), fileDir.lastIndexOf("."));
		//swfcombine
		String swfcombineExePath = exePath.substring(0, exePath.lastIndexOf("\\"))+"\\swfcombine.exe";
		//生成的第一个swf文件保存地址
		String swffilePath = linuxDir+"\\"+fileName;
		Process pro = null;
		if(isWindowsSystem()){
			//如果是windows系统
			String cmd = exePath + " \"" + fileDir + "\" -o \"" + swffilePath + ".swf\" -T 9";
			System.out.println("cmd="+cmd);
			//Runtime执行后返回创建的进程对象
			pro = Runtime.getRuntime().exec(cmd);
			pro.waitFor();
			String cmd2 = swfcombineExePath + " -o " +projWorkSpace+"\\"+fileName+".swf "+flashfile+" viewport="+swffilePath+".swf";
			System.out.println("cmd2="+cmd2);
			Runtime.getRuntime().exec(cmd2);
		}else{
			//如果是linux系统,路径不能有空格，而且一定不能用双引号，否则无法创建进程
			String[] cmd = new String[3];
			cmd[0] = exePath;
			cmd[1] = fileDir;
			cmd[2] = swffilePath + ".swf";
			//Runtime执行后返回创建的进程对象
			pro = Runtime.getRuntime().exec(cmd);
			pro.waitFor();
			String[] cmd2 = new String[4];
			cmd[0] = swfcombineExePath;
			cmd[1] = projWorkSpace+"\\"+fileName+".swf";
			cmd[2] = flashfile;
			cmd[3] = "viewport="+swffilePath+".swf";
			Runtime.getRuntime().exec(cmd2);
		}
		System.err.println("****swf转换成功，文件输出****");
		//非要读取一遍cmd的输出，要不不会flush生成文件（多线程）
		new DoOutput(pro.getInputStream()).start();
		new DoOutput(pro.getErrorStream()).start();
		Map<String,String> resMa= new HashMap<String,String>();
		try {
			//调用waitFor方法，是为了阻塞当前进程，直到cmd执行完
			pro.waitFor();
			resMa.put("res", "1");
		} catch (InterruptedException e) {
			resMa.put("res", "2");
			e.printStackTrace();
		}
		return resMa;
	}
	private static class DoOutput extends Thread {
		 public InputStream is;
		 
		 //构造方法
		 public DoOutput(InputStream is) {
			 this.is = is;
		 }
		 
		 public void run() {
			 BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
			 String str = null;
			 try {
				 //这里并没有对流的内容进行处理，只是读了一遍
				 while ((str = br.readLine()) != null);
			 } catch (IOException e) {
				 e.printStackTrace();
			 } finally {
				 if (br != null) {
					 try {
						 br.close();
					 } catch (IOException e) {
						 e.printStackTrace();
					 }
				 }
			 }
		 }
	 }
	public static void main(String[] args) throws InterruptedException {
		//转换器安装路径cmd 
		
		
		String exePath = "D:\\swf\\pdf2swf.exe";
		try {
			Map<String,String> resMa = PDF2SWFUtil.pdf2swf("D:\\documentFile\\upload\\Manual.swf", exePath,"D:\\documentFile\\upload","D:\\documentFile\\upload\\swf");
			System.out.println(resMa.get("res"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ok");
	}
}
