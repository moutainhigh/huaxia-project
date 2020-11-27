package com.huaxia.opas.action.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.action.thirdparty.PoliceAction;
import com.huaxia.opas.util.FileUploadProperties;

/**
 * Servlet implementation class PolicePhoto
 */
public class PolicePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static Logger logger = LoggerFactory.getLogger(PolicePhoto.class);
	private Map<String, String> paroperMap = FileUploadProperties.getParoperMap();
	private String photoPath = paroperMap.get("photo_path_police");
    public PolicePhoto() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpg");
		/*String identityNo=request.getParameter("identity_no");
		String imagePath=photoPath+"/"+identityNo+".jpg";*/
		String appId=request.getParameter("appId");
		String imagePath=photoPath+"/"+appId+".jpg";
	/*	if (logger.isDebugEnabled()) {
			logger.debug("公安照片路径为[" + imagePath + "]");
		}*/
		
		File file = new File(imagePath);
		if(!file.exists()){
		/*	if (logger.isInfoEnabled()) {
				logger.info("police photo not exist");
			}*/
			//file = new File(photoPath+"/nophoto.jpg");
		}else{
			OutputStream output = response.getOutputStream();
			FileInputStream fips = new FileInputStream(file);
			byte[] btImg = readStream(fips);
			output.write(btImg);
			output.flush();
		}

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
