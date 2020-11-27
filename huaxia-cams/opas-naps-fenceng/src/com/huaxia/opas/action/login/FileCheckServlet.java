package com.huaxia.opas.action.login;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.util.FileUploadProperties;

public class FileCheckServlet extends HttpServlet {

	private static final long serialVersionUID = 7519593245764443163L;

	private static Logger logger = LoggerFactory.getLogger(FileCheckServlet.class);

	private Map<String, String> paroperMap = FileUploadProperties.getParoperMap();
	//photoPath
	private String savePath = paroperMap.get("SAVE_PATH");
	public FileCheckServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf;charset=UTF-8");
		String fileName=request.getParameter("fileName");
		//文件名中文乱码
		fileName = new String(fileName.getBytes("iso-8859-1"),"utf-8");
		//fileName = URLEncoder.encode(fileName, "UTF-8");
		String pdfPath=savePath+fileName;
		
		if (logger.isDebugEnabled()) {
			logger.debug("PDF路径为[" + pdfPath + "]");
		}
		OutputStream output = response.getOutputStream();
		File file = new File(pdfPath);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("该文档已经不存在  请重新上传！！");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("PDF路径为[" + file + "]");
		}
		FileInputStream fips = new FileInputStream(file);
		byte[] btPdf = readStream(fips);
		output.write(btPdf);
		output.flush();
	}
	public byte[] readStream(InputStream inStream){
		ByteArrayOutputStream bops = new ByteArrayOutputStream();
		int data=-1;
		try{
			while((data = inStream.read())!=-1){
				bops.write(data);
			}
			return bops.toByteArray();
		}catch(Exception e){
			return null;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
