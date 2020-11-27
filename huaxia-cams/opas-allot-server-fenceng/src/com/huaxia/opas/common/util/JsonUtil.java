package com.huaxia.opas.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

public class JsonUtil {

	private static ObjectMapper jsonMapper = new ObjectMapper();
	
	/**
	 * <p>Description: FROM JSON TO MAP</p>
	 * @param json
	 * @return MAP
	 * @author wang.jf
	 * @version 1.0
	 */
	public static Map<String,Object> fromJSON(String json) {
		Map<String,Object> result = null;
		try {
			result = (Map<String,Object>) jsonMapper.readValue(json, Map.class);
		} catch (Exception ex) {
			
		}
		return result;
	}
	
	/**
	 * <p>Description: to JSON</p>
	 * @param jsonMap
	 * @return JSON String
	 * @author wang.jf
	 * @version 1.0
	 */
	public static String toJSON(Map<String,Object> jsonMap) {
		String json = null;
		try {
			json = jsonMapper.writeValueAsString(jsonMap);
		} catch (Exception ex) {
		}
		return json;
	}
	
	/**
	 * <p>Description: to JSON</p>
	 * @param jsonMap
	 * @return JSON String
	 * @author wang.jf
	 * @version 1.0
	 */
	public static String toJSONClient(Map<String,Object> inputMap) {

		
		Map<String,Object> jsonHead = new LinkedHashMap<String,Object>();
		Map<String,Object> jsonBody = inputMap;
		Map<String,Object> jsonTotal = new LinkedHashMap<String,Object>();
		
		jsonHead.put("method", inputMap.get("method"));
		jsonHead.put("timestamp", inputMap.get("timestamp"));
		jsonHead.put("Sig", inputMap.get("Sig"));
		
		//jsonBody.put("FILE_REALNAME", "FILE_DOWNLOAD_NAMES");
		//jsonBody.put("UPLOAD_SUBDIRS", "D:\\");
		//jsonBody.put("applicationid", inputMap.get("applicationid"));
		//jsonBody.put("sourcetype", inputMap.get("sourcetype"));
		
		jsonBody.remove("method");
		jsonBody.remove("timestamp");
		jsonBody.remove("Sig");
		
		jsonTotal.put("head", jsonHead);
		jsonTotal.put("body", jsonBody);
		
		Gson gson = new Gson();
		String json = null;
		try {
			json = gson.toJson(jsonTotal);
		} catch (Exception ex) {
		}
		return json;
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		String s = null;
		try {
			fis = new FileInputStream(new File("D://json.txt"));
			byte [] b = new byte[1024];
			fis.read(b);
			s = new String(b,"GBK");
			System.out.println(s);
			System.out.println(fromJSON(s));
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
		
		
	}

}
