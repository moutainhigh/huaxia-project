package com.huaxia.opas.action.archive;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.archive.ArExport;
import com.huaxia.opas.domain.archive.BizOutAppl;
import com.huaxia.opas.domain.archive.InpKeyField;
import com.huaxia.opas.service.archive.ArExportService;
import com.huaxia.opas.service.archive.LoadOutTempltesBeanService;
//import com.huaxia.opas.service.output.BizOutApplService;
import com.huaxia.opas.util.ConstantForExport;
import com.huaxia.opas.util.FilePathUtil;

public class ArExportAction implements Action{
	private static Logger logger = LoggerFactory.getLogger(ArExportAction.class);
	
	@Resource(name = "arExportService")
	private ArExportService arExportService;
	
	@Resource(name = "loadOutTempltesBeanService")
	private LoadOutTempltesBeanService loadOutTempltesBeanService;
	
//	@Resource(name = "bizOutApplService")
//	private BizOutApplService bizOutApplService;
//	
	private List<InpKeyField> temList;
	private String filePath = "";
	public List<InpKeyField> getTemList() {
		return temList;
	}

	public void setTemList(List<InpKeyField> temList) {
		this.temList = temList;
	}

	/**
	 * 文件导出主方法
	 * @throws ParseException 
	 */
	public void exportToFile(Context ctx){
		Gson gson = new Gson();
		Map map = ctx.getDataMap();
		String AppId = map.get("appId")==null?"":(String)map.get("appId");
		//查询对应的进件渠道
		String inputChanne = arExportService.queryinputChannel(AppId);
		//将appId取前15位
		String AppId2 = AppId.substring(0,15).toUpperCase();	
		try {
//			String fileName = bizOutApplService.fastCreateAppln(AppId2,inputChanne);
//			if(!"".equals(fileName) && null != fileName){
//				ctx.setData("isSucc", true);
//				ctx.setData("fileName", fileName);		
//			}else{
//				ctx.setData("error", "此流水号没有对应的数据");
//			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}
	
	/**工具方法：获取rushFlag
	 * */
	public String getRushFlag(String fast,String vip,String normal){
		StringBuffer target = new StringBuffer();
		String targetStr = "";
		if(!fast.equals(""))
			target.append(fast);
		if(!vip.equals(""))
			target.append(",").append(vip);
		if(!normal.equals(""))
			target.append(",").append(normal);
		targetStr = target.toString();
		if(targetStr.startsWith(","))
			targetStr = targetStr.substring(1,targetStr.length());
		
		return targetStr;
	}
	
	/**工具方法：获取appId 并将其转换为sql可查格式
	 * */
	public String getAppIds(List appIdList){
		StringBuffer appIdSb = new StringBuffer();
		String targetStr = "";
		for(int i = 0;i<appIdList.size();i++){
			ArExport ar = new ArExport();
			ar =(ArExport) appIdList.get(i);
			appIdSb.append("'");
			appIdSb.append(ar.getAppId());
			appIdSb.append("',");
		}
		targetStr = appIdSb.toString();
		if(targetStr.endsWith(","))
			targetStr = targetStr.substring(0,targetStr.length()-1);
		return targetStr;
	}
	
	/**生成文件，根据模板写入内容
	 * */
	public String createApplnFile(List appIdList) {
		StringBuffer sb = null;
		int length = 0;
		int pointLen = 0;
		String stringValue = null;
		String stringform = null;
		Integer intValue = 0;
		BigDecimal bdValue = null;
		Long longValue;
		Date dateValue = null;
		Object object = null;
		File file = null;
		int count;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		count = appIdList.size();
		if (count>0) {
			String name1 = "6304-APPLN-2";
			String name2 = getRandomAndDate();//获得五位随机数+YYYYMMDD日期格式的文件名后半部
			String fileName = name1 + name2;
			//字符串构建器,与stringbuilder同类,前者支持多线程,后者单线程更好
			sb = new StringBuffer();
			String appIds = getAppIds(appIdList);
			// 上送发卡文件命名
			try {
				filePath = FilePathUtil.getFilePath("arExportPath_local");
			} catch (IOException e2) {
				logger.info("读取配置文件出错！");
				e2.printStackTrace();
			}
			File path = new File(filePath);
			if(!path.exists())
				path.mkdir();
			file = new File(filePath+fileName);
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error("送发卡文件创建失败：", e);
			}
			try {
				temList = loadOutTempltesBeanService.init();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			//获取导出的内容
			List<BizOutAppl> noSendList = arExportService.queryNoSendList(appIds);
			for (BizOutAppl bizOutAppl : noSendList) {
				// 如果产品编号不为空
				if (bizOutAppl.getAppProd() != null) {
					//暂时把申请状态流程标志写死为"1"
					bizOutAppl.setAppFlowst(1);
					//如果申请金卡未核准,降级的卡产品编号设置为0
					if(bizOutAppl.getC4Gtoc() == 0){
						bizOutAppl.setC4Downpd("0");
					}
					/**
					 * 暂时修改C4_VRCODE  核准原因
					 *       C4_VCCRED  RMB额度
					 *       C4_VFCRED  外币额度
					 *       C4_VPACRED 分期额度
					 *与自动导出保持一致
					 */
					List<Map<String, Object>> sendCardColunm = arExportService.querySendCardUpdateColunm(bizOutAppl.getAppId());
					if (sendCardColunm.size() > 0) {
						Map<String, Object> paraMap = sendCardColunm.get(0);
						System.out.println("RESULT_CODE:"+paraMap.get("RESULT_CODE")
											+" REASON_CODE"+paraMap.get("REASON_CODE")
											+" CREDIT_LIMIT:"+paraMap.get("CREDIT_LIMIT"));
						//申请核准
						bizOutAppl.setC4Vcode((String) paraMap.get("RESULT_CODE"));
						//申请核准原因
						bizOutAppl.setC4Vrcode(Integer.valueOf((String) paraMap.get("REASON_CODE")));
	 					//申请的RMB的额度
						bizOutAppl.setC4Vccred(((BigDecimal)paraMap.get("CREDIT_LIMIT")).intValue());
					} else {
						logger.info("申请流水号：" + bizOutAppl.getAppId() + "查不到审批结果！");
					}

					
					
					for (InpKeyField kty : temList) {
						try {
							//获取字段名称
							String fieldName = kty.getName();
							//将字段首字母切换成大写
							String firstChar = fieldName.substring(0, 1).toUpperCase();
							//拼接get方法名称
							String methodName = "get" + firstChar + fieldName.substring(1, fieldName.length());
							//反射,拿到get方法的原型
							Method method = bizOutAppl.getClass().getDeclaredMethod(methodName);
							//反射拿到get方法的值
							object = method.invoke(bizOutAppl);
							//获取模板设定的字段
							length = kty.getLength();
							System.out.println(fieldName + ":" + object);
						} catch (Exception e) {
							logger.error("送发卡报文解析异常：", e);
						}
						
						// 解析数据，拼接成appln记录
						try {
							//判断数据的类型
							switch (kty.getToType()) {
							case ConstantForExport.S:
								if (object != null) {
									stringValue = (String) object;
								} else {
									stringValue = "";
								}
								stringform = formatStr(stringValue, length);
								break;
							case ConstantForExport.I:
								if (object != null) {
									intValue = (Integer) object;
									stringform = formatNum(intValue.toString(), length);
								} else {
									stringform = formatNum("", length);
								}
								stringform = formatNum(intValue.toString(), length);
								break;
							case ConstantForExport.L:
								if (object != null) {
									longValue = (Long) object;
								} else {
									longValue = Long.parseLong("0");
								}
								stringform = formatNum(longValue.toString(), length);
								break;
							case ConstantForExport.B:
								if (object != null) {
									bdValue = (BigDecimal) object;
								} else {
									bdValue = new BigDecimal("0");
								}
								pointLen = kty.getPointLen();
								stringform = formatBd(bdValue.toString(), length, pointLen);
								break;
							case ConstantForExport.D:
								if (object != null) {
									dateValue = (Date) object;
									stringform = sdf.format(dateValue);
								} else {
									stringform = "00000000";
								}
								break;
							case ConstantForExport.M:
								if (object != null) {
									stringValue = (String) object;
									stringform = formatNum(stringValue, length);
								} else {
									stringform = formatNum("", length);
								}
								break;
							}

						} catch (Exception e) {
							logger.error("实体类" + kty.getName() + "属性解析异常：", e);
							e.printStackTrace();
						}
						sb.append(stringform);
						stringform = null;
					}
					// 加换行符
					sb.append("\n");

				}

			}

			// 将拼接好appln数据写入文件
			try {
				FileOutputStream fos = new FileOutputStream(file);
				//将sb转换成字符串,用GBK的格式完成解码 
				fos.write(sb.toString().getBytes("GBK"));
				fos.flush();
				fos.close();
				sb = null;
				return file.getName();
			} catch (IOException e) {
				logger.error("写文件异常", e);
			}
		}else{
			
		}
		return null;
	}
	
	/**
	 * 
	 * 左补零 作者：张志宽
	 * 
	 * @version 2017-3-5
	 */
	public String formatNum(String str, int length) {
		int strLen = str.length();
		if (strLen == length) {
			return str;
		} else if (strLen < length) {
			int temp = length - strLen;
			String tem = "";
			for (int i = 0; i < temp; i++) {
				tem = "0" + tem;
			}
			return tem + str;
		} else {
			return str.substring(0, length);
		}
	}

	/**
	 * 
	 * 右补空格 作者：wangkai
	 * 
	 * @version 2016-5-24
	 */
	public String formatStr(String str, int length) {
		byte[] returnByte = new byte[length];
		if (str == null) {
			str = "";
		}
		int strLen = 0;
		try {
			//将字符串用GBK转码的方式转换成字节数组求出长度,下面有补全.
			strLen = str.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if (strLen == length) {
			return str;
		} else if (strLen < length) {
			int temp = length - strLen;
			String tem = "";
			for (int i = 0; i < temp; i++) {
				tem = tem + " ";
			}
			return str + tem;
		} else {
			for (int i = 0; i < length; i++) {
				try {
					returnByte[i] = str.getBytes("GBK")[i];
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			String returnStr = null;
			try {
				returnStr = new String(returnByte, "GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return returnStr;
			// return str.substring(0,length - 1);
		}
	}
	
	/**
	 * 
	 * 左补零 作者：张志宽
	 * 
	 * @version 2017-3-5
	 */
	public String formatBd(String str, int length, int pointLen) {
		int intLen = length - pointLen;
		String intStr = null;
		String decStr = null;
		if (str.contains(".")) {
			String[] intDecStr = str.split("\\.");
			intStr = formatNum(intDecStr[0], intLen);
			decStr = formatDec(intDecStr[1], pointLen);
		} else {
			intStr = formatNum(str, intLen);
			decStr = formatDec(decStr, pointLen);
		}
		return intStr + decStr;
	}

	/**
	 * 
	 * 小数位右补零 作者：张志宽
	 * 
	 * @version 2017-3-6
	 */
	public String formatDec(String str, int length) {
		if (str == null) {
			str = "";
		}
		int strLen = str.length();
		if (strLen == length) {
			return str;
		} else if (strLen < length) {
			int temp = length - strLen;
			String tem = "";
			for (int i = 0; i < temp; i++) {
				tem = tem + "0";
			}
			return str + tem;
		} else {
			return str.substring(0, length);
		}
	}

	/**获得五位随机数+YYYYMMDD日期格式的文件名后半部
	 * */
	public String getRandomAndDate(){
		SimpleDateFormat adf = new SimpleDateFormat("yyyyMMdd");
		Random random= new Random();
		Date currDate = new Date();
		String dateStr = adf.format(currDate);
		int rannum = (int)(random.nextDouble() * (99999-10000+1))+10000;
		return rannum+"-"+dateStr;
	}
}
