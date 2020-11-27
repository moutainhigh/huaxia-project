package com.huaxia.opas.action.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.util.PadProperties;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * 存量客户电销的txt文件上传至服务器
 * 
 * @author wangtao
 *
 */
public class StockUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(StockUploadServlet.class);

	public StockUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paroperMap = PadProperties.getParoperMap();
		//配置文件中获取上传的路径
		String savePath = paroperMap.get("stockPath");
		//指定上传的文件路径
//		String savePath = "/app/opas/";
		File file = new File(savePath);
		if(!file.exists()&&!file.isDirectory()){
			if(logger.isInfoEnabled()){
				logger.info("目录不存在，需要创建=================="+savePath);
			}
			file.mkdir();
		}
		String message = "";
		response.setContentType("text/html;charset=GB2312");
		PrintWriter writer = response.getWriter();
		InputStream in = null;
		FileOutputStream  out = null;
		try{
			//创建一个DiskFileItemFactory
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//1.创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			//2.开始读取上传信息
			int formDataLength = request.getContentLength();
			if(-1 == formDataLength){
				throw new Exception("tartIframe监听事件并非异常，可忽略");
			}
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				String filename = item.getName();
				if(filename!=null){
//					if(filename.getBytes().length!=filename.length()){
//						throw new Exception("上传文件名称不能含有中文");
//					}
//					filename = filename.substring(0, filename.indexOf(".txt"))+ new SimpleDateFormat("yyyyMMdd").format(new Date())+".txt";
					filename = "stockcust_telsale_list" + new SimpleDateFormat("yyyyMMdd").format(new Date())+".txt";
				}
				if(logger.isInfoEnabled()){
					logger.info("获取的上传的文件名称=================="+filename);
				}
				if(filename==null || filename.trim().equals("")){
					continue;
				}
				//处理不同浏览器提交的文件名不一致问题
				filename = filename.substring(filename.lastIndexOf("\\")+1);
				//获取item中商船文件的输入流
			    in = item.getInputStream();
				//创建一个文件的输出流
				out = new FileOutputStream(savePath + filename);
				//创建一个缓冲区
				byte buffer[] = new byte[1024];
				//判断输入流是否读完的标识
				int len = 0;
				while((len=in.read(buffer))>0){
					out.write(buffer, 0, len);
				}
				message = "文件上传成功！";
				writer.print("success_"+message);
				//删除处理文件上传时生成的临时文件
				item.delete();
			}
		}catch(Exception e){
			message = e.getMessage();
			if(!"tartIframe监听事件并非异常，可忽略".equals(message)){
				writer.print("false_"+message);
			}
			writer.close();
			e.printStackTrace();
		}finally{
			if(in!=null){
				in.close();
			}
			if(out!=null){
				out.close();
			}
			writer.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
