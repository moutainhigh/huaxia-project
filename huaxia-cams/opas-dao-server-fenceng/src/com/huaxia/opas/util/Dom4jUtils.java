package com.huaxia.opas.util;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * @author 王金华
 * May 9, 2014
 * 9:22:23 AM
 *
 */
public class Dom4jUtils {

	public Element getRootElement(Document doc) {
		return doc.getRootElement();
	}

	public List<Element> getAllNodeByName(Document doc, final String name) {
		final List<Element> result=new LinkedList<Element>();
		
		doc.accept(new VisitorSupport() {

			public void visit(Element node) {
				if(node.getName().equalsIgnoreCase(name)){
					result.add(node);
				}
			}

		});
		
		return result;
	}
	
	/**
	 * 将字符串转换为Document
	 * 
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public Document stringToDoc(String xml) throws DocumentException {
		return DocumentHelper.parseText(xml);
	}

	/**
	 * 将Document转换为字符串
	 * 
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public String docToString(Document doc) throws Exception {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		format.setIndent(true);
		format.setIndent("\t");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		XMLWriter writer = new XMLWriter(bos, format);
		writer.write(doc);
		writer.close();
		String temp = bos.toString();
		bos.close();
		return temp.substring(temp.indexOf("?>") + 3).trim();
	}

}
