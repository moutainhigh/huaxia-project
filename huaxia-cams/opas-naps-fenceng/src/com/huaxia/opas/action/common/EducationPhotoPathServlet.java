package com.huaxia.opas.action.common;

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

import com.huaxia.opas.action.thirdparty.PoliceAction;
import com.huaxia.opas.util.FileUploadProperties;

/**
 * 学历 图片查询   gaohui
 */
public class EducationPhotoPathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(EducationPhotoPathServlet.class);
	private Map<String, String> paroperMap = FileUploadProperties.getParoperMap();
	private String photoPath = paroperMap.get("photo_path_education");
    public EducationPhotoPathServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpg");
		String appId=request.getParameter("appId");
		String imagePath=photoPath+"/"+appId+".jpg";
		if (logger.isDebugEnabled()) {
			logger.debug("学历照片路径为[" + imagePath + "]");
		}
		OutputStream output = response.getOutputStream();
		File file = new File(imagePath);
		if(!file.exists()){
			file = new File(photoPath+"/nophoto.jpg");
		}
		FileInputStream fips = new FileInputStream(file);
		byte[] btImg = readStream(fips);
		output.write(btImg);
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
