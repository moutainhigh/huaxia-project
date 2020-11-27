package com.huaxia.opas.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;

public class XmlUtil {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FileInputStream fis = null;

		try {
			fis = new FileInputStream("D://1.xml");
			byte[] b = new byte[fis.available()];
			fis.read(b);
			String str = new String(b, "GBK");

			System.out.println(getXmlToMap(str));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static Map<String, Object> getXmlToMap(String requestXml) throws Exception {
		LinkedHashMap<String, Object> attMap = new LinkedHashMap<String, Object>();
		ArrayList<String> reasonList = new ArrayList<String>();
		Document doc = DocumentHelper.parseText(requestXml);
		Element rootElement = doc.getRootElement();
		if (rootElement.attributes().size() > 0) {
			List list = rootElement.attributes();
			for (int i = 0; i < list.size(); i++) {
				String attValue = rootElement.attributeValue(((DefaultAttribute) list.get(i)).getName());
				addValue2Map(attMap,
						(rootElement.getPath() + "." + ((DefaultAttribute) list.get(i)).getName()).substring(1,
								(rootElement.getPath() + "." + ((DefaultAttribute) list.get(i)).getName()).length()),
						attValue);
			}
		}
		loopAllElements(rootElement, attMap, reasonList);

		return attMap;// keyReplace(attMap);
	}

	public static Map keyReplace(Map<String, Object> input) {

		Map result = new LinkedHashMap<String, Object>();
		String key = null;
		String[] array = null;

		for (Map.Entry<String, Object> entry : input.entrySet()) {
			array = entry.getKey().split("/");
			key = array[array.length - 1];
			result.put(array[array.length - 2] + "." + key, entry.getValue());
		}

		return result;
	}

	/**
	 * descr:指定规则XML标签解析 tim:2015-8-6 17:05:27
	 * 
	 * @param el
	 * @param dataElementName
	 *            Data结点名称
	 */
	@SuppressWarnings("unchecked")
	public static void listNodes(Element el, String rootName, LinkedHashMap<String, Object> attMap,
			ArrayList<String> reasonList) {
		Iterator<Element> to = el.elementIterator();
		int tempNo = 0;
		while (to.hasNext()) {
			Element node = (Element) to.next();
			if (node.getName() != null) {
				List<Attribute> attList = ((Element) node).attributes();
				for (int i = 0; i < attList.size() && attList.size() != 0; i++) {
					if (attMap.containsKey(rootName + "/" + node.getName() + "/" + attList.get(i).getName())) {
						// 包含该key则key值+1
						// attMap.put(rootName+"/"+node.getName()+"/"+attList.get(i).getName()+(++tempNo),
						// attList.get(i).getText());
						addValue2Map(attMap,
								rootName + "/" + node.getName() + "." + attList.get(i).getName() + (++tempNo),
								attList.get(i).getText());
					} else {
						// attMap.put(rootName+"/"+node.getName()+"/"+attList.get(i).getName(),
						// attList.get(i).getText());
						addValue2Map(attMap, rootName + "/" + node.getName() + "." + attList.get(i).getName(),
								attList.get(i).getText());
					}

				}
				// if(node.getName().equals("ReasonText")){
				// reasonList.add(node.getText());
				// attMap.put(rootName+"/"+node.getName(),reasonList );

				addValue2Map(attMap, rootName + "." + node.getName(), node.getText());

				// }
			}
			listNodes(node, rootName + "/" + node.getName(), attMap, reasonList);
		}
	}

	/**
	 * descr:迭代xml中所有元素
	 * 
	 * @param el
	 * @param dataElementName
	 *            Data结点名称 LinkedHashMap<String,Object> attMap = new
	 *            LinkedHashMap<String,Object>(); ArrayList<String> reasonList =
	 *            new ArrayList<String>();
	 */
	private static void loopAllElements(Element el, LinkedHashMap<String, Object> attMap, ArrayList<String> reasonList)
			throws Exception {
		Iterator itor = el.elementIterator();
		while (itor.hasNext()) {
			Element element = (Element) itor.next();
			if (element.attributes().size() > 0) {
				List list = element.attributes();
				for (int i = 0; i < list.size(); i++) {
					String attValue = element.attributeValue(((DefaultAttribute) list.get(i)).getName());
					// System.out.println("element.getNamespaceURI():"+element.getPath());
					addValue2Map(attMap,
							(element.getPath() + "." + ((DefaultAttribute) list.get(i)).getName()).substring(1,
									(element.getPath() + "." + ((DefaultAttribute) list.get(i)).getName()).length()),
							attValue);
				}
			}
			if (!"".equals(element.getText().trim())) {
				addValue2Map(attMap, element.getPath(), element.getText().trim());
			}

			if (element.elementIterator().hasNext())
				loopAllElements(element, attMap, reasonList);
		}
	}

	@SuppressWarnings("unchecked")
	private static void addValue2Map(Map<String, Object> map, String key, String value) {
		if (StringUtils.isNotBlank(value)) {
			Object obj = map.get(key);

			if (obj == null) {
				// null
				map.put(key, value);
			} else if (obj instanceof List) {
				// List
				((List) obj).add(value);
			} else if (obj instanceof String) {
				// Object
				List<String> list = new ArrayList<String>();
				list.add((String) obj);
				list.add(value);

				map.put(key, list);

			} else {
				System.err
						.println("key=" + key + " value=" + value + "  obj=" + obj + " obj.getClass=" + obj.getClass());
			}
		}
	}
}
