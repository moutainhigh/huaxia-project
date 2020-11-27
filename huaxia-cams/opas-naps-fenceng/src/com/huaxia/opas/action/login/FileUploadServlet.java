package com.huaxia.opas.action.login;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.util.FileUploadProperties;

public class FileUploadServlet extends HttpServlet {
 
	private static final long serialVersionUID = -4452023075162015088L;
	
	Map<String, String> paroperMap = FileUploadProperties.getParoperMap();
	String savePath = paroperMap.get("report_path");
	
	@Value("${upload.newpath}")
	public  String uploadPath;

	public FileUploadServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
		// Put your code here
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CoreException {
		FileInputStream in = null;
		File file = null;
		File newfile = null;
		FileOutputStream out = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			// 读取请求Body
			byte[] body = readBody(request);
			// 取得所有Body内容的字符串表示
			String textBody = new String(body, "ISO-8859-1");
			// 取得上传的文件名称
			String fileName = new String(getFileName(textBody).getBytes("ISO-8859-1"), "UTF-8");
			// 取得文件开始与结束位置
			Position p = getFilePosition(request, textBody);
			// 输出至文件
			writeTo(fileName, body, p);
			// 名单批量上传文件信息实体类相关的参数
			Map<String, String> map = getOperatorInfo(textBody);
			File filePath = new File(savePdfPath());
			if (!filePath.exists() && !filePath.isDirectory()) {
				filePath.mkdirs();
			}
			file = new File(savePath.concat(fileName));
			in = new FileInputStream(file);
			newfile = new File(savePdfPath() + File.separator + fileName);
			out = new FileOutputStream(newfile);
			byte by[] = new byte[512];
			int len = 0;
			while ((len = in.read(by)) > 0) {
				out.write(by);
			}
			System.out.println("=============文档上传完成=================");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				in.close();
			}
			if(null != out){
				out.close();
			}
			if (null != file) {
				file.delete();
			}
			if (null != newfile) {
				file.delete();
			}
		}
	}
	/**
	 * 获得pdf文件的上传路径
	 */
	public String savePdfPath() {
		Map<String, String> paroperMap = FileUploadProperties.getParoperMap();
		String path = paroperMap.get("SAVE_PATH");
		if (path == null || "".equals(path)) {
			return "D:" + File.separator + "documentFile" + File.separator + "upload" + File.separator;
		} else {
			return path;
		}
	}
	// 构造类
	class Position {
		int begin;
		int end;

		public Position(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
	}

	private byte[] readBody(HttpServletRequest request) throws IOException, CoreException {
		// 获取请求文本字节长度
		int formDataLength = request.getContentLength();
		if (-1 == formDataLength) {
			throw new CoreException("文件监听事件并非异常，可忽略");
		}
		// 取得ServletInputStream输入流对象
		DataInputStream dataStream = new DataInputStream(request.getInputStream());
		byte body[] = new byte[formDataLength];
		int totalBytes = 0;
		while (totalBytes < formDataLength) {
			int bytes = dataStream.read(body, totalBytes, formDataLength);
			totalBytes += bytes;
		}
		return body;
	}

	private Position getFilePosition(HttpServletRequest request, String textBody) throws IOException {
		// 取得文件区段边界信息
		String contentType = request.getContentType();
		String boundaryText = contentType.substring(contentType.lastIndexOf("=") + 1, contentType.length());
		// 取得实际上传文件的气势与结束位置
		int pos = textBody.indexOf("filename=\"");
		pos = textBody.indexOf("\n", pos) + 1;
		pos = textBody.indexOf("\n", pos) + 1;
		pos = textBody.indexOf("\n", pos) + 1;
		int boundaryLoc = textBody.indexOf(boundaryText, pos) - 4;
		int begin = ((textBody.substring(0, pos)).getBytes("ISO-8859-1")).length;
		int end = ((textBody.substring(0, boundaryLoc)).getBytes("ISO-8859-1")).length;
		return new Position(begin, end);
	}

	private String getFileName(String requestBody) {
		String fileName = requestBody.substring(requestBody.indexOf("filename=\"") + 10);
		fileName = fileName.substring(0, fileName.indexOf("\n"));
		fileName = fileName.substring(fileName.indexOf("\n") + 1, fileName.indexOf("\""));
		return fileName;
	}

	private void writeTo(String fileName, byte[] body, Position p) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(savePath + fileName);
		fileOutputStream.write(body, p.begin, (p.end - p.begin));
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
	// the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}

	/**
	 * 获取上传操作用户名(userName)和用户代码(userCode)的MAP集合方法
	 * 
	 * @param String
	 *            requestBody 请求体
	 * @return Map<String,String> 上传的文件名
	 */
	private Map<String, String> getOperatorInfo(String requestBody) {
		Map<String, String> hashMap = new HashMap<>();
		// 获取用户名
		String userName = requestBody.substring(requestBody.indexOf("userName\"") + 9);
		String[] split = userName.split("-");
		userName = split[0].trim();
		// 获取用户名
		String userCode = requestBody.substring(requestBody.indexOf("userCode\"") + 9);
		split = userCode.split("-");
		userCode = split[0].trim();

		hashMap.put("userName", userName);
		hashMap.put("userCode", userCode);
		return hashMap;
	}
}
