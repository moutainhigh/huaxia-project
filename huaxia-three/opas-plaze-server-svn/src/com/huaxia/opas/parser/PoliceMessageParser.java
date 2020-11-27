package com.huaxia.opas.parser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.domain.Police;
import com.huaxia.opas.domain.Police.Input;
import com.huaxia.opas.domain.Police.Output;
import com.huaxia.opas.domain.Police.Rts;
import com.huaxia.opas.domain.Police.Rts.Row;
import com.huaxia.opas.domain.Police.Rts.Row.RowInput;
import com.huaxia.opas.domain.Police.Rts.Row.RowOutput;
import com.huaxia.opas.interfaces.in.MessageParser;

/**
 * 增强公安报文解析器
 * 
 * @author zhiguo.li
 *
 */
public class PoliceMessageParser implements MessageParser<Police> {

	private final static Logger logger = LoggerFactory.getLogger(PoliceMessageParser.class);

	@Override
	public Police parse(String message) throws Exception {
		if (message == null) {
			throw new IllegalArgumentException("公安报文为空");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("[公安数据解析] 原始报文：{}", message);
		}

		message = message.replace("<ITEM>", "").replace("</ITEM>", "").replace("<RT>", "").replace("</RT>", "");

		if (logger.isDebugEnabled()) {
			logger.debug("[公安数据解析] 格式化报文：{}", message);
		}

		InputStream input = new ByteArrayInputStream(message.getBytes());
		Reader reader = new InputStreamReader(input, "GBK");

		Document document = null;
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(reader);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("[公安数据解析] 构建XML解析异常：{}", e.getMessage());
			}
			e.printStackTrace();
			throw e;
		}

		Node ROW = document.selectSingleNode("/ROWS/ROW");
		if (ROW != null) {
			Police police = new Police();
			
			// 解析INPUT节点
			doInputWalk(ROW, police);

			// 解析OUTPUT节点
			doOutputWalk(ROW, police);

			// 解析RTS节点
			doRtsWalk(ROW, police);

			return police;
		}

		return null;
	}

	private void doRtsWalk(Node ROW, Police police) {
		Node rtsNode = ROW.selectSingleNode("RTS");
		if (rtsNode != null) {
			Node xNode = null;
			Rts rtsObject = police.new Rts();

			xNode = rtsNode.selectSingleNode("DN");
			if (xNode != null) {
				rtsObject.setDn(xNode.getText());
			}

			Node rowsNode = rtsNode.selectSingleNode("ROWS");
			if (rowsNode != null) {
				List<Node> rowNodeList = rowsNode.selectNodes("ROW");
				if (rowNodeList != null && rowNodeList.size() > 0) {
					List<Row> rowList = new ArrayList<Row>();
					for (Node rowNode : rowNodeList) {
						Row row = rtsObject.new Row();
						Node rowInputNode = rowNode.selectSingleNode("INPUT");
						if (rowInputNode != null) {
							RowInput rowInput = row.new RowInput();
							row.setRowInput(rowInput);
						}

						Node rowOutputNode = rowNode.selectSingleNode("OUTPUT");
						if (rowOutputNode != null) {
							RowOutput rowOutput = row.new RowOutput();
							
							xNode = rowOutputNode.selectSingleNode("result_name");
							if (xNode != null) {
								rowOutput.setResult_name(xNode.getText());
							}
							
							xNode = rowOutputNode.selectSingleNode("result_cardnum");
							if (xNode != null) {
								rowOutput.setResult_cardnum(xNode.getText());
							}
							
							xNode = rowOutputNode.selectSingleNode("result_case_code");
							if (xNode != null) {
								rowOutput.setResult_case_code(xNode.getText());
							}
							
							xNode = rowOutputNode.selectSingleNode("result_gist_unit");
							if (xNode != null) {
								rowOutput.setResult_gist_unit(xNode.getText());
							}
							
							xNode = rowOutputNode.selectSingleNode("result_area_name");
							if (xNode != null) {
								rowOutput.setResult_area_name(xNode.getText());
							}
							
							xNode = rowOutputNode.selectSingleNode("result_performance");
							if (xNode != null) {
								rowOutput.setResult_performance(xNode.getText());
							}
							
							xNode = rowOutputNode.selectSingleNode("result_disreput_type_name");
							if (xNode != null) {
								rowOutput.setResult_disreput_type_name(xNode.getText());
							}
							
							xNode = rowOutputNode.selectSingleNode("result_publish_date");
							if (xNode != null) {
								rowOutput.setResult_publish_date(xNode.getText());
							}
							
							row.setRowOutput(rowOutput);
						}
						rowList.add(row);
					}
					rtsObject.setRowList(rowList);
				}
			}

			police.setRts(rtsObject);
			xNode = null;
		}
	}

	private void doOutputWalk(Node ROW, Police police) {
		Node outputNode = ROW.selectSingleNode("OUTPUT");
		if (outputNode != null) {
			Node xNode = null;
			Output output = police.new Output();

			xNode = outputNode.selectSingleNode("gmsfhm");
			if (xNode != null) {
				output.setGmsfhm(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_gmsfhm");
			if (xNode != null) {
				output.setResult_gmsfhm(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("xm");
			if (xNode != null) {
				output.setXm(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_xm");
			if (xNode != null) {
				output.setResult_xm(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("cym");
			if (xNode != null) {
				output.setCym(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_cym");
			if (xNode != null) {
				output.setResult_cym(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("xb");
			if (xNode != null) {
				output.setXb(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_xb");
			if (xNode != null) {
				output.setResult_xb(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("mz");
			if (xNode != null) {
				output.setMz(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_mz");
			if (xNode != null) {
				output.setResult_mz(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("csrq");
			if (xNode != null) {
				output.setCsrq(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_csrq");
			if (xNode != null) {
				output.setResult_csrq(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("ssssxq");
			if (xNode != null) {
				output.setSsssxq(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_ssssxq");
			if (xNode != null) {
				output.setResult_ssssxq(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("jgssx");
			if (xNode != null) {
				output.setJgssx(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_jgssx");
			if (xNode != null) {
				output.setResult_jgssx(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("csdssx");
			if (xNode != null) {
				output.setCsdssx(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_csdssx");
			if (xNode != null) {
				output.setResult_csdssx(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("zz");
			if (xNode != null) {
				output.setZz(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_zz");
			if (xNode != null) {
				output.setResult_zz(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("pcsmc");
			if (xNode != null) {
				output.setPcsmc(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_pcsmc");
			if (xNode != null) {
				output.setResult_pcsmc(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("fwcs");
			if (xNode != null) {
				output.setFwcs(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_fwcs");
			if (xNode != null) {
				output.setResult_fwcs(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("hyzk");
			if (xNode != null) {
				output.setHyzk(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_hyzk");
			if (xNode != null) {
				output.setResult_hyzk(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("whcd");
			if (xNode != null) {
				output.setWhcd(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("result_whcd");
			if (xNode != null) {
				output.setResult_whcd(xNode.getText());
			}

			xNode = outputNode.selectSingleNode("xp");
			if (xNode != null) {
				output.setXp(xNode.getText());
			}

			police.setOutput(output);
			xNode = null;
		}
	}

	private void doInputWalk(Node ROW, Police police) {
		Node inputNode = ROW.selectSingleNode("INPUT");
		if (inputNode != null) {
			Node xNode = null;
			Input input = police.new Input();

			xNode = inputNode.selectSingleNode("gmsfhm");
			if (xNode != null) {
				input.setGmsfhm(xNode.getText());
			}

			xNode = inputNode.selectSingleNode("xm");
			if (xNode != null) {
				input.setXm(xNode.getText());
			}

			police.setInput(input);
			xNode = null;
		}
	}

	public static void main(String[] args) {
		String message = null;
		String appId = "1234567890123456";
		File file = new File("D:/thirdparty/Police/510224196305181754.xml");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
			if (reader != null) {
				message = reader.readLine();
				// List<Police> policeList = new
				// PoliceMessageParser().parse(message);
				// System.out.println(policeList);

				Police police = new PoliceMessageParser().parse(message);
				System.out.println(police);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}

	}

}
