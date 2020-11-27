package com.huaxia.opas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Utils {

	public static String getMapperColumnByProperty(String fileName, String id, String property) {
		try {
			SAXReader saxReader = new SAXReader();
//			File f = new File(fileName);
//			FileInputStream ff = new FileInputStream(f);
			 Document document = saxReader.read(Utils.class.getClassLoader().getResourceAsStream(fileName));
			//Document document = saxReader.read(f);
			if (document != null) {
				Element root = document.getRootElement();
				if (root != null) {
					@SuppressWarnings("unchecked")
					List<Element> resultMaps = root.elements("resultMap");
					for (Element resultMap : resultMaps) {
						if (resultMap != null && resultMap.attributeValue("id").equals(id)) {
							@SuppressWarnings("unchecked")
							List<Element> properties = resultMap.elements();
							for (Element prop : properties) {
								if (prop != null && prop.attributeValue("property").equals(property)) {
									return prop.attributeValue("column");
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static Element getResultMapElement(String fileName, String id) {
		try {
			SAXReader saxReader = new SAXReader();
			File f = new File("fileName");
			// Document document =
			// saxReader.read(Utils.class.getClassLoader().getResourceAsStream(fileName));
			Document document = saxReader.read(f);
			if (document != null) {
				Element root = document.getRootElement();
				if (root != null) {
					@SuppressWarnings("unchecked")
					List<Element> resultMaps = root.elements("resultMap");
					for (Element resultMap : resultMaps) {
						if (resultMap != null && resultMap.attributeValue("id").equals(id)) {
							return resultMap;
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static String getMapperColumnByElement(Element resultMapElement, String property) {
		try {
			if (resultMapElement != null) {
				@SuppressWarnings("unchecked")
				List<Element> properties = resultMapElement.elements();
				for (Element prop : properties) {
					if (prop != null && prop.attributeValue("property").equals(property)) {
						return prop.attributeValue("column");
					}
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		long startTime = new Date().getTime();
		/// opas-dao-server/resource/config/sqlmap/BizInpApplC1-Mapping.xml
		// D:\newworkspace_uat\opas-dao-server\resource\config\sqlmap
		File a = new File("resource/config/sqlmap/BizInpAppl.xml");
		String canonicalPath = a.getCanonicalPath();
		System.out.println(canonicalPath);
		String mapperColumnByProperty = getMapperColumnByProperty(
				"config/sqlmap/BizInpAppl.xml",
				"BizInpApplicationResultMap", "appId");

		System.out.println(mapperColumnByProperty);

		long endTime = new Date().getTime();
		System.out.println("====" + (endTime - startTime));

	}

}
