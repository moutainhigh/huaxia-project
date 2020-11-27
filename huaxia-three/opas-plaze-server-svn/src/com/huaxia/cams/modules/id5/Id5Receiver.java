package com.huaxia.cams.modules.id5;

import java.io.File;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huaxia.opas.domain.Education;
import com.huaxia.opas.service.EducationService;
import com.huaxia.opas.service.log.LogRecordResultService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.CommonUtil;
import com.huaxia.opas.util.ImageGenUtil;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import cn.id5.gboss.client.api.codec.HexCodec;
import cn.id5.gboss.client.api.codec.security.AesCodec;
import cn.id5.gboss.client.api.codec.security.Base64;
import cn.id5.gboss.client.api.codec.security.RsaCodec;
import cn.id5.gboss.client.api.codec.StringUtils;

/**
 * 学历处理适配器
 * 
 * @author zhangtingyang
 *
 */
public class Id5Receiver {

	private static final Logger logger = LoggerFactory.getLogger(Id5Receiver.class);

	private TaskCallStatusService taskCallStatusService;

	private LogRecordResultService logRecordResultService;

	private EducationService educationService;

	// 报文处理
	// private static MessageOperator<Education> messageOperator = new
	// EducationMessageOperator();

	// 学历照片文件名后缀
	private  String photoSubfix;

	// 学历照片生成路径
	private  String photoSavePath;
	// 报文存储路径
	private String xmlSavePath;

	// 学历任务类型
	private String taskType;

	private static RsaCodec rsa;
	private static HexCodec hex;
	private static AesCodec aes;

	// 初始化加密算法
	static {
		rsa = new RsaCodec();
		hex = new HexCodec();
		aes = new AesCodec();
	}

	 

	public Id5Receiver() {
		// 获取配置参数
		AppConfig config = AppConfig.getInstance();
		taskType = config.getValue("id5_TrnCode_code");
		setPhotoSubfix(config.getValue("PLAZE_EDU_PHOTO_SUBFIX"));
		setPhotoSavePath(config.getValue("PLAZE_EDU_PHOTO_GENERATE_PATH"));
		xmlSavePath=config.getValue("PLAZE_EDUCATION_HTTP_XML_PATH");
		// 初始化服务接口
		setTaskCallStatusService((TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService"));
		setEducationService((EducationService) SpringContextUtil.getBean("educationService"));
		setLogRecordResultService((LogRecordResultService) SpringContextUtil.getBean("logRecordResultService"));
	}

	public void getResult(String parameter, String APP_ID) {
		String bodyStr = "";
		String responseCode = "";
		String responseDesc = "";
		// 分解返回参数参数
		JSONObject jsonResponse = JSON.parseObject(parameter);
		if (jsonResponse.containsKey("RESPONSE") && jsonResponse.getString("RESPONSE") != null
				&& !"".equals(jsonResponse.getString("RESPONSE"))) {
			JSONObject jsonRes = JSON.parseObject(jsonResponse.getString("RESPONSE"));

			if (jsonRes.containsKey("HEAD") && jsonRes.getString("HEAD") != null
					&& !"".equals(jsonRes.getString("HEAD"))) {
				JSONObject jsonHead = JSON.parseObject(jsonRes.getString("HEAD"));

			}

			if (jsonRes.containsKey("BODY") && jsonRes.getString("BODY") != null
					&& !"".equals(jsonRes.getString("BODY"))) {
				JSONObject jsonBody = JSON.parseObject(jsonRes.getString("BODY"));
				if (jsonBody.containsKey("RESPONSE_BODY") && jsonBody.getString("RESPONSE_BODY") != null
						&& !"".equals(jsonBody.getString("RESPONSE_BODY"))) {
					bodyStr = jsonBody.getString("RESPONSE_BODY");
				}
				if (jsonBody.containsKey("RESPONSE_DESC")
						&& jsonBody.getString("RESPONSE_DESC") != null
						&& !"".equals(jsonBody.getString("RESPONSE_DESC"))) {
					responseDesc = jsonBody.getString("RESPONSE_DESC");
				}
				if (jsonBody.containsKey("RESPONSE_CODE") && jsonBody.getString("RESPONSE_CODE") != null
						&& !"".equals(jsonBody.getString("RESPONSE_CODE"))) {
					responseCode = jsonBody.getString("RESPONSE_CODE");
					// 三方平台数量达到上限后者时间不在范围内，不进行数据入库，执行另一个任务
					if (responseCode.equals("999994") || responseCode.equals("999993")) {
						taskCallStatusService.saveTaskStatusHistory(APP_ID, taskType);
						taskCallStatusService.updateSingleTaskStatus(APP_ID,
								TaskStatusUtil.QUERY_LIMIT, taskType, null,
								TaskStatusUtil.CURR_USER, null, "1");
						return;
					}	
				}
			}

		}

		// 解析响应消息并获取响应报文
		String responseXml = bodyStr;

		// 生成报文 文件
		// 此处响应信息还未解密 生产报文位置需要更改============
//		CommonUtil.downLoadMessageContent(responseXml, xmlSavePath, APP_ID, null, ".json", "学历未解密信息");
		// 删除 原先存在的学历照片
		CommonUtil.rmHardDiskFile(getPhotoSavePath() + File.separator, APP_ID + getPhotoSubfix(), APP_ID, "学历照片");

		taskCallStatusService.saveTaskStatusHistory(APP_ID, taskType);
		
		try {
			Document documentjudge = DocumentHelper.parseText(responseXml);
			Node eduNodeKeyinfo=documentjudge.selectSingleNode("/xjxl/errorhx03");
			if(eduNodeKeyinfo!=null){
				//对方服务响应异常
				taskCallStatusService.updateSingleTaskStatus(APP_ID, TaskStatusUtil.SERVE_RESPOSE_ERROE, taskType, null,
						TaskStatusUtil.CURR_USER, null, "1");
			return ;
			}
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		Education education = parseEducationChsi(responseXml);

		try {
			if (education != null) {
				education.setAppId(APP_ID);
				educationService.saveEducationUpdateDataAdapterAction(education, APP_ID, taskType);
				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & 学历] 报文数据持久化成功, 申请件编号: {}", APP_ID);
				}
				// 针对学历中相片生成在指定配置目录中
				String photo = education.getPhoto();
				if (photo != null && !"".equals(photo)) {
					String fileSavePath = getPhotoSavePath() + File.separator + APP_ID + getPhotoSubfix();
					boolean imageGenerateResult = ImageGenUtil.generate(photo, fileSavePath);
					if (imageGenerateResult) {
						if (logger.isDebugEnabled()) {
							logger.debug("[客户端 & 学历] 学历照片生成成功, 照片路径为: {}", fileSavePath);
						}
					} else {
						if (logger.isWarnEnabled()) {
							logger.warn("[客户端 & 学历] 学历照片生成失败, 申请件编号: {}", education.getAppId());
						}
					}
				}
				taskCallStatusService.updateSingleTaskStatus(APP_ID, TaskStatusUtil.SUCCESS, taskType, null,
						TaskStatusUtil.CURR_USER, null, null);
			} else {
				// 解析异常
				taskCallStatusService.updateSingleTaskStatus(APP_ID, TaskStatusUtil.PARSE_ERROE, taskType, null,
						TaskStatusUtil.CURR_USER, null, "1");
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("[客户端 & 学历] 入库异常   " + APP_ID + " Exception:{}", e);
			}
			// 入库异常
			taskCallStatusService.updateSingleTaskStatus(APP_ID, TaskStatusUtil.SAVE_ERROE, taskType, null,
					TaskStatusUtil.CURR_USER, null, "1");
		}
	}

	/**
	 * @Title:parseEducationChsi
	 * @Description:解析学信网返回的加密报文
	 * @param message
	 * @return
	 * @author: gaohui
	 * @Date:2018年7月31日上午11:05:15
	 */
	private static Education parseEducationChsi(String message) {

		Education education = new Education();
		try {
			Document document = DocumentHelper.parseText(message);
			Node eduNodeKeyinfo = document.selectSingleNode("/xjxl/keyinfo");
			if (eduNodeKeyinfo != null) {// 有学历时
				String keyinfoKey = document.selectSingleNode("/xjxl/keyinfo/key").getText();
				String keydata = AppConfig.getInstance().getValue("PLAZE_EDU_PRIVATE_KEY");
				// 使用rsa对私钥密文解密
				PrivateKey privateKey = rsa.loadPrivateKey(keydata);
				byte[] decdata = rsa.decrypt(Base64.decode(keyinfoKey), privateKey, 1024, "");
				String enEduData = document.selectSingleNode("/xjxl/xlinfos").getText();
				byte[] eduDataArray = hex.hexStringToByte(enEduData);
				// 使用aes对学历密文进行解密
				byte[] eduData = aes.decrypt(eduDataArray, Base64.decode(new String(decdata)));
				String decEduData = StringUtils.newString(eduData, "UTF-8");
				// System.err.println(decEduData);
				Document dom = DocumentHelper.parseText(decEduData);
				Node eduNodeXL = dom.selectSingleNode("/xl");
				if (eduNodeXL != null) {
					Node eduNodeXm = eduNodeXL.selectSingleNode("xm");
					// 姓名 USERNAME
					if (eduNodeXm != null) {
						education.setXm(eduNodeXm.getText());
					}
					// 证件号码 IDENTITYCARD
					Node eduNodeZjhm = eduNodeXL.selectSingleNode("zjhm");
					if (eduNodeZjhm != null) {
						education.setZjhm(eduNodeZjhm.getText());
					}

					// 学历层次 EDUCATIONDEGREE
					Node eduNodeCc = eduNodeXL.selectSingleNode("cc");
					if (eduNodeCc != null) {
						education.setCc(eduNodeCc.getText());
					}
					// 院校名称 GRADUATE
					Node eduNodeYxmc = eduNodeXL.selectSingleNode("yxmc");
					if (eduNodeYxmc != null) {
						education.setYxmc(eduNodeYxmc.getText());
					}
					// 毕结业结论 STUDYRESULT
					Node eduNodeBjyjl = eduNodeXL.selectSingleNode("bjyjl");
					if (eduNodeBjyjl != null) {
						education.setBjyjl(eduNodeBjyjl.getText());
					}
					// 毕业年份 GRADUATETIME
					Node eduNodeByrq = eduNodeXL.selectSingleNode("byrq");
					if (eduNodeByrq != null) {
						education.setByrq(eduNodeByrq.getText());
					}
					// 学习形式 DSTUDYSTYLE
					Node eduNodeXxxs = eduNodeXL.selectSingleNode("xxxs");
					if (eduNodeXxxs != null) {
						education.setXxxs(eduNodeXxxs.getText());
					}
					// 学历照片
					Node eduNodePhoto = eduNodeXL.selectSingleNode("photo");
					if (eduNodePhoto != null) {
						education.setPhoto(eduNodePhoto.getText());
					}
					education.setCrtUser("system");
					education.setQueryStatus("0");
					education.setQueryResult("查询成功");
				}
			} else {// 无学历时
				education.setCrtUser("system");
				education.setQueryStatus("1");
				education.setQueryResult("未查到数据");
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("[客户端 & 学历] 学历报文解析异常   " + education.getAppId() + " Exception:{}", e);
			}
			return null;
		}
		return education;
	}



	/*
	 * public static MessageOperator<Education> getMessageOperator() { return
	 * messageOperator; }
	 * 
	 * public static void setMessageOperator(MessageOperator<Education>
	 * messageOperator) { EducationHandlerAdapter.messageOperator =
	 * messageOperator; }
	 */

	public TaskCallStatusService getTaskCallStatusService() {
		return taskCallStatusService;
	}

	public void setTaskCallStatusService(TaskCallStatusService taskCallStatusService) {
		this.taskCallStatusService = taskCallStatusService;
	}

	public EducationService getEducationService() {
		return educationService;
	}

	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}

	public String getPhotoSubfix() {
		return photoSubfix;
	}

	public void setPhotoSubfix(String photoSubfix) {
		this.photoSubfix = photoSubfix;
	}

	public String getPhotoSavePath() {
		return photoSavePath;
	}

	public void setPhotoSavePath(String photoSavePath) {
		this.photoSavePath = photoSavePath;
	}

	public LogRecordResultService getLogRecordResultService() {
		return logRecordResultService;
	}

	public void setLogRecordResultService(LogRecordResultService logRecordResultService) {
		this.logRecordResultService = logRecordResultService;
	}

	protected Map<String, String> parseResponseMessage(String responseMessage) {
		String[] spliter = responseMessage.split("##,##");

		String taskType = spliter[0].trim();
		Map<String, String> response = new HashMap<String, String>();
		response.put("TASK_TYPE", taskType);
		response.put("APP_ID", spliter[1].trim());
		response.put("RESPONSE_BODY", spliter[2].trim());
		// 针对fico大数据
		if (spliter.length == 4 && spliter[3].trim().equals("true")) {
			response.put("FLAG", spliter[3].trim());
		}

		// 针对前海征信特殊处理 & 查询原因
		if (spliter.length == 4) {
			if (taskType.startsWith("08") && !taskType.equals("0804")) {
				response.put("QHZX_REASON_CODE", spliter[3].trim());
			}
		}

		return response;
	}

}
