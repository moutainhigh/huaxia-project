package com.huaxia.opas.parser;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.domain.SimplifyPolice;
import com.huaxia.opas.interfaces.in.MessageParser;

/**
 * 简项公安报文解析器
 * 
 * @author zhiguo.li
 *
 */
public class SimplifyPoliceMessageParser implements MessageParser<SimplifyPolice> {

	private final static Logger logger = LoggerFactory.getLogger(SimplifyPoliceMessageParser.class);

	@Override
	public SimplifyPolice parse(String message) throws Exception {
		if (message == null || "".equals(message)) {
			return null;
			//throw new IllegalArgumentException("简项公安报文为空");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("[公安数据解析] 原始报文：{}", message);
		}

		message = message.replace("<ITEM>", "").replace("</ITEM>", "").replace("<RT>", "").replace("</RT>", "");

		if (logger.isDebugEnabled()) {
			logger.debug("[公安数据解析] 格式化报文：{}", message);
		}

		Document document = null;
		try {
			document = DocumentHelper.parseText(message);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("[公安数据解析] 构建XML解析异常：{}", e.getMessage());
			}
			e.printStackTrace();
			return null;
		}

		Node ROW = document.selectSingleNode("/ROWS/ROW");
		if (ROW != null) {
			SimplifyPolice police = new SimplifyPolice();

			// 验证响应报文是否异常？
			// 错误代码
			Node errorCodeNode = ROW.selectSingleNode("ErrorCode");
			if (errorCodeNode != null) {
				police.setErrorCode(errorCodeNode.getText());

				// 错误描述
				Node errorMsgNode = ROW.selectSingleNode("ErrorMsg");
				police.setErrorMessage(errorMsgNode.getText());

				return police;
			}

			// 解析INPUT节点
			doInputWalk(ROW, police);

			// 解析OUTPUT节点
			doOutputWalk(ROW, police);

			return police;
		}

		return null;
	}

	private void doOutputWalk(Node ROW, SimplifyPolice police) {
		Node outputNode = ROW.selectSingleNode("OUTPUT");
		if (outputNode != null) {
			Node xNode = null;

			// xNode = outputNode.selectSingleNode("gmsfhm");
			// if (xNode != null) {
			// police.setGmsfhm(xNode.getText());
			// }

			xNode = outputNode.selectSingleNode("result_gmsfhm");
			if (xNode != null) {
				police.setGmsfhmResult(xNode.getText());
			}

			// xNode = outputNode.selectSingleNode("xm");
			// if (xNode != null) {
			// police.setXm(xNode.getText());
			// }

			xNode = outputNode.selectSingleNode("result_xm");
			if (xNode != null) {
				police.setXmResult(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("xp");
			if (xNode != null) {
				police.setXp(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("errormesage");
			if (xNode != null) {
				police.setErrorMessage(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("errormesagecol");
			if (xNode != null) {
				police.setErrorMessageCol(xNode.getText());
			}

			xNode = null;
		}
	}

	private void doInputWalk(Node ROW, SimplifyPolice police) {
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
