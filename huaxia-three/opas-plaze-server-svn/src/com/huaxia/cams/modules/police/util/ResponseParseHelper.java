package com.huaxia.cams.modules.police.util;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.cams.modules.police.domain.PoliceXpInfo;

public class ResponseParseHelper {

	private static final Logger logger = LoggerFactory.getLogger(ResponseParseHelper.class);
	public static PoliceXpInfo parseXml(String bodyStr) {
		PoliceXpInfo xpInfo = new PoliceXpInfo();
		if(bodyStr == null || "".equals(bodyStr)){
			return null;
		}
		Document document = null;
		
		try {
			document = DocumentHelper.parseText(bodyStr);
		} catch (DocumentException e) {
			logger.error("【人像比对】构建xml出错{}", e.getMessage());
			e.printStackTrace();
			return null;
		}
		try {
			Node responseRow = document.selectSingleNode("/RESPONSE/ROWS/ROW");
			if( responseRow !=null ){
				// 验证响应报文是否异常？
				// 错误代码
				Node errorCodeNode = responseRow.selectSingleNode("ErrorCode");
				if (errorCodeNode != null) {
						String errorCode = errorCodeNode.getText();
						// 错误描述
						Node errorMsgNode = responseRow.selectSingleNode("ErrorMsg");
						String errorMsg = errorMsgNode.getText();
						if (logger.isInfoEnabled()) {
							 logger.info(" 人像比对错误信息 ,message:{}","ErrorCode:"+errorCode+" ErrorMsg:"+errorMsg);
						}
						return null;
				}
			}
			
			Node ROW = document.selectSingleNode("/ROWS/ROW");
			if (ROW != null) {

				// 解析INPUT节点
				doInputWalk(ROW, xpInfo);

				// 解析OUTPUT节点
				doOutputWalkPoc(ROW, xpInfo);

				return xpInfo;
			}
		} catch (Exception e) {
			logger.error("【人像比对】解析原始报文时出错：", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	private static void doOutputWalkPoc(Node ROW, PoliceXpInfo police) {
		Node outputNode = ROW.selectSingleNode("OUTPUT");
		if (outputNode != null) {
			Node xNode = null;
			@SuppressWarnings("unchecked")
			List<Element> itemList =  outputNode.selectNodes("ITEM");
			if( itemList != null && itemList.size() > 0 ){
				int itemSize = itemList.size();
				if( itemSize >=2 ){
						
					Element item0 = itemList.get(0);
					if( item0 != null ){
						xNode = item0.selectSingleNode("result_gmsfhm");//公民身份号码核查结果
						if (xNode != null) {
							police.setResultGmsfhm(xNode.getText());
						}
						xNode = item0.selectSingleNode("errormesage");
						if (xNode != null) {
							police.setErrorMessage(xNode.getText());
						}
					}
						
					Element item1 = itemList.get(1);
					if( item1 != null ){
						xNode = item1.selectSingleNode("result_xm");//姓名比对结果 
						if (xNode != null) {
							police.setResultXm(xNode.getText());
						}
						xNode = item1.selectSingleNode("errormesagecol");
						if (xNode != null) {
							police.setErrorMessageCol(xNode.getText());
						}
					}
				}	
						
				if( itemSize >=3 ){
					Element item2 = itemList.get(2);
					if( item2 != null ){
						xNode = item2.selectSingleNode("xp");
						if (xNode != null) {
							police.setXp(xNode.getText());
						}
						xNode = item2.selectSingleNode("result_fx");//系统分析结果
						if (xNode != null) {
							police.setResultFx(xNode.getText());
						}
						xNode = item2.selectSingleNode("result_fs");//比对分数
						if (xNode != null) {
							police.setResultFs(xNode.getText());
						}
					}
				}
					
			}
			xNode = null;
		}
	}

	private static void doInputWalk(Node ROW, PoliceXpInfo police) {
		Node inputNode = ROW.selectSingleNode("INPUT");
		if (inputNode != null) {
				Node xNode = null;
				xNode = inputNode.selectSingleNode("gmsfhm");
				if (xNode != null) {
					police.setGmsfhm(xNode.getText());
				}
				xNode = inputNode.selectSingleNode("xm");
				if (xNode != null) {
					police.setXm(xNode.getText());
				}
				xNode = null;
		}
	}
}
