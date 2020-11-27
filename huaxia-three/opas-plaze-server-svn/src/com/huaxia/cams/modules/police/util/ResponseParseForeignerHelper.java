package com.huaxia.cams.modules.police.util;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.cams.modules.police.domain.NciicForeignerInfo;

public class ResponseParseForeignerHelper {

	private static final Logger logger = LoggerFactory.getLogger(ResponseParseForeignerHelper.class);
	public static NciicForeignerInfo parseXml(String bodyStr) {
		NciicForeignerInfo nciicForeignerInfo= new NciicForeignerInfo();
		if(bodyStr == null || "".equals(bodyStr)){
			return null;
		}
		Document document = null;

		try {
			document = DocumentHelper.parseText(bodyStr);
		} catch (DocumentException e) {
			logger.error("【外国人永久居留证】构建xml出错{}", e);
			e.printStackTrace();
			return null;
		}
		try {
			Node responseRow = document.selectSingleNode("/RESPONSE/ROWS/ROW");
			if (responseRow != null) {
				// 验证响应报文是否异常？
				// 错误代码
				Node errorCodeNode = responseRow.selectSingleNode("ErrorCode");
				if (errorCodeNode != null) {
					String errorCode = errorCodeNode.getText();
					nciicForeignerInfo.setErrorCode(errorCode);
					// 错误描述
					Node errorMsgNode = responseRow.selectSingleNode("ErrorMsg");
					String errorMsg = errorMsgNode.getText();
					nciicForeignerInfo.setErrorMessage(errorMsg);
					if (logger.isInfoEnabled()) {
						logger.info(" 外国人永久居留证错误信息 ,message:{}", "ErrorCode:" + errorCode + " ErrorMsg:" + errorMsg);
					}
					return nciicForeignerInfo;
				}
			}

			Node ROW = document.selectSingleNode("/ROWS/ROW");
			if (ROW != null) {

				// 解析INPUT节点
				doInputWalk(ROW, nciicForeignerInfo);

				// 解析OUTPUT节点
				doOutputWalkPoc(ROW, nciicForeignerInfo);

				return nciicForeignerInfo;
			}
		} catch (Exception e) {
			logger.error("【外国人永久居留证】解析原始报文时出错：", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	private static void doOutputWalkPoc(Node ROW, NciicForeignerInfo nciicForeignerInfo) {
		Node outputNode = ROW.selectSingleNode("OUTPUT");
		if (outputNode != null) {
			Node xNode = null;
			 xNode = outputNode.selectSingleNode("result_code");
			 if (xNode != null) {
					nciicForeignerInfo.setResultCode(xNode.getText());
			}
			@SuppressWarnings("unchecked")
			List<Element> itemList = outputNode.selectNodes("ITEM");
			if (itemList != null && itemList.size() > 0) {
				Element item0 = itemList.get(0);
				if (item0 != null) {
					xNode = item0.selectSingleNode("errormesage");
					if (xNode != null) {
						nciicForeignerInfo.setErrorMessage(xNode.getText());
					}
				}
				
				Element item1 = itemList.get(1);
				if (item1 != null) {
					xNode = item1.selectSingleNode("errormesagecol");
					if (xNode != null) {
						nciicForeignerInfo.setErrorMessageCol(xNode.getText());
					}
				}
			}
			xNode = null;
		}
	}

	private static void doInputWalk(Node ROW, NciicForeignerInfo nciicForeignerInfo) {
		Node inputNode = ROW.selectSingleNode("INPUT");
		if (inputNode != null) {
			Node xNode = null;
			xNode = inputNode.selectSingleNode("zjhm");
			if (xNode != null) {
				nciicForeignerInfo.setZjhm(xNode.getText());
			}
			xNode = inputNode.selectSingleNode("ywxm");
			if (xNode != null) {
				nciicForeignerInfo.setXm(xNode.getText());
			}
			xNode = inputNode.selectSingleNode("csrq");
			if (xNode != null) {
				nciicForeignerInfo.setCsrq(xNode.getText());
			}
			xNode = inputNode.selectSingleNode("zjyxqz");
			if (xNode != null) {
				nciicForeignerInfo.setZjyxqr(xNode.getText());
			}
			xNode = null;
		}
	}
}
