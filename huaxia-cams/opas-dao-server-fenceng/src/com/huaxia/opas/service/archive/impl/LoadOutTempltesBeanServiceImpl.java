package com.huaxia.opas.service.archive.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.huaxia.opas.domain.archive.InpKeyField;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.archive.LoadOutTempltesBeanService;
//import com.huaxia.opas.domain.input.InpKeyField;
//import com.huaxia.opas.service.output.LoadOutTempltesBeanService;
import com.huaxia.opas.util.CommonUtilForExport;
import com.huaxia.opas.util.Constant;


/**
 * 
 * Description: 定义监听，服务启动时加载模板到容器，供解析使用 Copyright: Copyright (c) 2016 Company:
 * HUATENG Author 李涛 Version 1.0 Created at 2016-4-13
 */
public class LoadOutTempltesBeanServiceImpl extends AbstractService implements LoadOutTempltesBeanService {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2826539676413815437L;

	/**
	 * 定义模板装载器
	 */
	private static List<InpKeyField> temOutList = new ArrayList<InpKeyField>();

	/**
	 * 模板解析
	 */
	private SAXReader reader = new SAXReader();
	private Document document = null;
	private Element root = null;

	private Properties ps = null;

	/**
	 * 定义文件模型
	 */
	private File tems = null;
	@SuppressWarnings("rawtypes")
	private List<InpKeyField> temList = new ArrayList<InpKeyField>();
	/**
	 * 定义实体
	 */
	private InpKeyField entity = null;
	/**
	 * 当前文件及装载器下标
	 */
	private static int index = 0;

	/**
	 * 当前访问元素
	 */
	private Element curElement = null;

	public static List<InpKeyField> gettemOutList() {
		return temOutList;
	}

	public static void settemOutList(List<InpKeyField> temOutList) {
		LoadOutTempltesBeanServiceImpl.temOutList = temOutList;
	}

	/**
	 * 
	 * 初始化模板加载器，目前可以保持不变，后期建议三个模板（卡一、二，通用）一并初始化 字段若无确定，可以随意按结构定义几个，也方便后续参照添加
	 * 作者：李涛
	 * 
	 * @version 2016-4-13
	 */
	@SuppressWarnings("rawtypes")
	public List init() throws Exception {
		// ps = CommonUtil.readProperties(LoadInpTempltesBean.class, null);
		ps = CommonUtilForExport.readProperties();
		tems = new File(ps.getProperty("TEM_CARD_ONE"));
		temList = temOutList;
		document = reader.read(tems);
		root = document.getRootElement();
		parseXml(root);
		return temList;
	}

	/**
	 * 
	 * 模板解析 作者：李涛
	 * 
	 * @version 2016-4-13
	 */
	@SuppressWarnings("unchecked")
	private void parseXml(Element node) throws Exception {
		Iterator<Element> iterator = node.elementIterator(Constant.FIELD);
		String pointLenStr = null;
		String requiredStr = null;
		String card2Str = null;
		boolean required = false;
		boolean card2 = false;
		String toCharStr = null;
		while (iterator.hasNext()) {
			entity = new InpKeyField();
			curElement = iterator.next();
			entity.setName(curElement.elementText(Constant.NAME).trim());
			entity.setComments(curElement.elementText(Constant.COMMENTS).trim());
			toCharStr = curElement.elementText(Constant.TO_TYPE);
			toCharStr = (toCharStr == null) ? Constant.NULL_STRING : toCharStr.trim();
			entity.setToType(toCharStr.isEmpty() ? Constant.S : toCharStr.charAt(0));
			entity.setLength(Integer.parseInt(curElement.elementText(Constant.LENGTH).trim()));

			// 字段小数点位数，默认:0
			pointLenStr = curElement.elementText(Constant.POINT_LEN);
			pointLenStr = (pointLenStr == null) ? Constant.ZERO_STRING : pointLenStr.trim();
			if (!pointLenStr.equals(Constant.ZERO_STRING)) {
				int locPl = Integer.parseInt(pointLenStr);
				if (locPl > 0) {
					entity.setPointLen(locPl);
				}
			}
			entity.setColBeginIndex(Integer.parseInt(curElement.elementText(Constant.COL_BEGIN_INDEX).trim()));
			// 字段是否必须, 取值：true/false，默认:false
			temList.add(entity);
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new LoadOutTempltesBeanServiceImpl().init();

	}

}
