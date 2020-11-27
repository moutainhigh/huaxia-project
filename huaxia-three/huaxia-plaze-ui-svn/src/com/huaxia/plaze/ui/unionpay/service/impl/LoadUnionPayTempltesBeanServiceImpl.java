package com.huaxia.plaze.ui.unionpay.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.huaxia.plaze.common.Constant;
import com.huaxia.plaze.ui.unionpay.domain.InpKeyField;
import com.huaxia.plaze.ui.unionpay.service.LoadUnionPayTempltesBeanService;

/**
 * 
 * @description: 定义监听，服务启动时加载模板到容器，供解析使用
 * @author liyanan
 * @since 2018年12月29日
 * @version 1.0
 * @lastUpdateDate
 * @lastUpdateUser
 */
@Service("loadUnionPayTempltesBeanService")
public class LoadUnionPayTempltesBeanServiceImpl implements LoadUnionPayTempltesBeanService{

	/**
	 * 定义模板装载器
      */
	private static List<InpKeyField> tem3118List = new ArrayList<InpKeyField>();
	

	/**
	 * 模板解析
	 */
	private SAXReader reader = new SAXReader();
	private Document document = null;
	private Element root = null;


	/**
	 * 定义文件模型
	 */
	private File tems = null;
	private List<InpKeyField> temList3118 = new ArrayList<InpKeyField>();

	/**
	 * 定义实体
	 */
	private InpKeyField entity = null;
	/**
	 * 当前文件及装载器下标
	 */

	/**
	 * 当前访问元素
	 */
	private Element curElement = null;

	
	public static List<InpKeyField> getTem3118List() {
		return tem3118List;
	}

	public static void setTem3118List(List<InpKeyField> tem3118List) {
		LoadUnionPayTempltesBeanServiceImpl.tem3118List = tem3118List;
	}

	
	
	/**
	 * 
	 * @Description：初始化3118接口模板 加载器
	 * @author 张志宽
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List init3118() throws Exception {
		String path = this.getClass().getResource("/").toString() + "UNIONPAY_3118.xml";
		path = "//"+path.substring(6);
		tems = new File(path);
		temList3118 = tem3118List;
		document = reader.read(tems);
		root = document.getRootElement();
		parseXml(root, "3118");
		return temList3118;
	}

	/**
	 * 
	 * @Description: 解析模板文件
	 * @author 张志宽
	 * @param node
	 * @param flag
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void parseXml(Element node, String flag) throws Exception {
		@SuppressWarnings("rawtypes")
		List temList = new ArrayList();
		Iterator<Element> iterator = node.elementIterator(Constant.FIELD);
		String pointLenStr = null;
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
			temList.add(entity);
		}
		if (flag == "3118") {
			temList3118 = temList;
		}
	}

	
}
