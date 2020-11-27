package com.huaxia.opas.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadProperties {
private static final Logger logger = LoggerFactory.getLogger(FileUploadProperties.class);
	
	private Properties property;
	
	private static Map<String, String> paroperMap = new HashMap<String, String>();

	public void init() {
		/*String pdf2swfPath = (String) property.get("Pdf2swfPath");
		String firstSwfPath = (String) property.get("FirstSwfPath");
		String projWorkSpace = (String) property.get("ProjWorkSpace");*/
		String savePath = (String) property.get("SavePath");
		String photoPathPolice = (String) property.get("photo_path_police");
		String photoPathEducation=(String)property.get("photo_path_education");
		String reportPath = (String) property.get("report_path");
		/*if (pdf2swfPath != null) {
			paroperMap.put("PDF2SWF_PATH", pdf2swfPath);
		}
		if(firstSwfPath !=null){
			paroperMap.put("FIRST_SWF_PATH", firstSwfPath);
		}
		if(projWorkSpace!=null){
			paroperMap.put("PROJ_WORK_SPACE", projWorkSpace);
		}*/
		if(savePath!=null){
			paroperMap.put("SAVE_PATH", savePath);
		}
		if(photoPathPolice!=null){
			paroperMap.put("photo_path_police", photoPathPolice);
		}
		if(photoPathEducation!=null){
			paroperMap.put("photo_path_education", photoPathEducation);
		}
		if(reportPath!=null){
			paroperMap.put("report_path", reportPath);
		}
		/*logger.info("PDF2SWF_PATH="+pdf2swfPath);
		logger.info("FIRST_SWF_PATH="+firstSwfPath);
		logger.info("PROJ_WORK_SPACE="+projWorkSpace);*/
		logger.info("SAVE_PATH="+savePath);
		logger.info("photo_path_police="+photoPathPolice);
		logger.info("photo_path_education="+photoPathEducation);
		logger.info("report_path="+reportPath);
	}

	public Properties getProperty() {
		return property;
	}

	public void setProperty(Properties property) {
		this.property = property;
	}

	public static Map<String, String> getParoperMap() {
		return paroperMap;
	}

	public static void setParoperMap(Map<String, String> paroperMap) {
		FileUploadProperties.paroperMap = paroperMap;
	}
}
