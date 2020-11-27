/**
 * 
 */
package com.huaxia.opas.service.report.impl;

import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.AbstractService;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.ApplyInfoDao;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.report.KeyMessageModifyDao;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.baseinfo.BaseCustInfo;
import com.huaxia.opas.domain.input.BizInpAppC1Spec;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplC2;
import com.huaxia.opas.domain.report.KeyMessageModify;
import com.huaxia.opas.domain.report.TaskCallStatus;
import com.huaxia.opas.service.apply.ApplyInfoService;
import com.huaxia.opas.service.apply.impl.ApplyInfoServiceImpl;
import com.huaxia.opas.service.report.KeyMessageModifyService;
import com.huaxia.opas.service.workflow.TopbpmService;
import com.huaxia.opas.util.StringUtil;
import com.huaxia.opas.util.UUIDGenerator;

import oracle.sql.TIMESTAMP;

/**
 * @author gaohui 关键信息修改表
 *
 */
public class KeyMessageModifyServiceImpl extends AbstractService implements KeyMessageModifyService {
	private static final Logger logger = LoggerFactory.getLogger(KeyMessageModifyServiceImpl.class);
	@Resource(name = "keyMessageModifyDao")
	private KeyMessageModifyDao keyMessageModifyDao;

	public KeyMessageModifyDao getKeyMessageModifyDao() {
		return keyMessageModifyDao;
	}

	public void setKeyMessageModifyDao(KeyMessageModifyDao keyMessageModifyDao) {
		this.keyMessageModifyDao = keyMessageModifyDao;
	}

	@Resource(name = "applyInfoDao")
	private ApplyInfoDao applyInfoDao;

	public ApplyInfoDao getApplyInfoDao() {
		return applyInfoDao;
	}

	public void setApplyInfoDao(ApplyInfoDao applyInfoDao) {
		this.applyInfoDao = applyInfoDao;
	}

	@Resource(name = "applyInfoServiceImpl")
	private ApplyInfoService applyService;
	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDao;

	@Resource(name = "topbpmService")
	private TopbpmService topbpmService;
	
	public BizInpApplDao getBizInpApplDao() {
		return bizInpApplDao;
	}

	public void setBizInpApplDao(BizInpApplDao bizInpApplDao) {
		this.bizInpApplDao = bizInpApplDao;
	}

	@Override
	protected void doStart() {
	}

	@Override
	protected void doStop() {
	}

	@Override
	public Map<String, Object> findListKeyMessageModifyByDate(Map<String, Object> paramsMap, int pageNum,
			int pageRows) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Map<String, Object>> keyMessageModifyList = new ArrayList<Map<String, Object>>();
		int total = 0;
		String totalNum = keyMessageModifyDao.getKeyMessageModifyCountByDate(paramsMap);
		if (totalNum != null) {
			total = Integer.parseInt(totalNum);
			keyMessageModifyList = keyMessageModifyDao.findKeyMessageModifyListByDate(paramsMap, pageNum, pageRows);
			if (!keyMessageModifyList.isEmpty()) {
				int listSize = keyMessageModifyList.size();
				Map<String, Object> userMap = new HashMap<String, Object>();
				for (int i = 0; i < listSize; i++) {
					if (keyMessageModifyList.get(i) != null) {
						String operator = keyMessageModifyList.get(i).get("operator").toString();
						if (operator != null && !"".equals(operator)) {
							userMap.put("userCode", operator);
							Map<String, Object> apUserMap = bizInpApplDao.selectApUserByUuIdUserCode(userMap);
							if (apUserMap != null) {
								if (apUserMap.get("userName") != null) {
									String userName = apUserMap.get("userName").toString();
									keyMessageModifyList.get(i).put("operator", userName);
								}
							}
						}
						String reviewer = keyMessageModifyList.get(i).get("reviewer").toString();
						if (reviewer != null && !"".equals(reviewer)) {
							userMap.put("userCode", reviewer);
							Map<String, Object> apUserMap = bizInpApplDao.selectApUserByUuIdUserCode(userMap);
							if (apUserMap != null) {
								if (apUserMap.get("userName") != null) {
									String userName = apUserMap.get("userName").toString();
									keyMessageModifyList.get(i).put("reviewer", userName);
								}
							}
						}
					}
				}
			}
		}
		dataMap.put("rows", keyMessageModifyList);
		dataMap.put("total", total);
		return dataMap;
	}

	@Override
	public List<Map<Object, Object>> queryList(Map<String, Object> dataMap, int curNum, int pageNum)
			throws CoreException {
		return keyMessageModifyDao.queryList(dataMap, curNum, pageNum);
	}

	@Override
	public List<Map<Object, Object>> findListKeyMessageByAppId(String appId) throws CoreException {
		return keyMessageModifyDao.findListKeyMessageByAppId(appId);
	}

	@Override
	public Integer queryKeyParamsByProperty(String column) throws CoreException {
		return keyMessageModifyDao.queryKeyParamsByProperty(column);
	}

	@Override
	public List<Map<Object, Object>> findAllMesByAppId(String string) throws CoreException {
		return keyMessageModifyDao.findAllMesByAppId(string);
	}

	@Override
	public void insertKeyMessage(List<KeyMessageModify> keyList) throws CoreException {
		keyMessageModifyDao.insertKeyMessage(keyList);

	}

	@Override
	public void updateModifyLogFlag(String appId) throws CoreException {
		AllotApply allot = applyInfoDao.queryApplyAllotByAppId(appId);
		String appIdsub = appId.substring(0, appId.length() - 1);
		keyMessageModifyDao.updateModifyLogFlag(appIdsub);
		Map mapParms = new HashMap<String, String>();
		mapParms.put("appId", appIdsub);
		if ("02".equals(allot.getCurrNode())) { // 征信
			if ("A".equals(allot.getDelStatus())) {
				mapParms.put("currNode", "0");
			}
			if ("B".equals(allot.getDelStatus())) {
				mapParms.put("currNode", "3");
			}
			if ("C".equals(allot.getDelStatus())) {
				mapParms.put("currNode", "2");
			}
		}
		if ("03".equals(allot.getCurrNode())) { // 审批
			if ("A".equals(allot.getDelStatus())) {
				mapParms.put("currNode", "0");
			}
			if ("B".equals(allot.getDelStatus())) {
				mapParms.put("currNode", "3");
			}
			if ("C".equals(allot.getDelStatus())) {
				mapParms.put("currNode", "2");
			}
		}
		applyInfoDao.updateAllotApply(mapParms);
	}

	@Override
	public int queryCount() throws CoreException {

		return keyMessageModifyDao.queryCount();
	}

	@Override
	public void keyMessageRecheckPass(String appId, String jsonString, Map<String, String> paroperMap)
			throws CoreException {
		String userId = paroperMap.get("userId");
		String opear = paroperMap.get("opear");
		String process_IP = paroperMap.get("process_IP");
		BizInpAppC1Spec keyMesageApply1 = JSON.parseObject(jsonString, BizInpAppC1Spec.class);
		BizInpApplC2 keyMesageApply2 = JSON.parseObject(jsonString, BizInpApplC2.class);
		JSONObject parseObject = JSON.parseObject(jsonString);
		String remark = (String) parseObject.get("remark");
		// 查询申请件信息修改记录表 -->关键信息修改表
		List<Map<Object, Object>> list = keyMessageModifyDao.findAllMesByAppId(appId.substring(0, appId.length() - 1));
		// 查询分件表
		AllotApply allot = applyService.queryApplyAllotByAppId(appId);
		String ydjFlag = allot.getYdjFlag();
		String matchingCardFlag = allot.getMatchingCardFlag();
		AllotApplyAllotHis query1 = new AllotApplyAllotHis();
		AllotApplyAllotHis query2 = new AllotApplyAllotHis();
		query1.setAppId(appId.substring(0, appId.length() - 1) + "2");
		query2.setAppId(appId.substring(0, appId.length() - 1) + "1");
		if ("1".equals(ydjFlag)) { // YDJ
			query1.setCurrNode("02");
			query2.setCurrNode("02");
		} else {
			query1.setCurrNode("01");
			query2.setCurrNode("01");
		}
		// 修改分件历史表
		String autoId = keyMessageModifyDao.queryHisAllotByAppId(query1);
		String autoId2 = keyMessageModifyDao.queryHisAllotByAppId(query2);
		// 根据uuid更新历史分件表最后操作人
		AllotApplyAllotHis aaaH = new AllotApplyAllotHis();
		aaaH.setId(autoId);
		aaaH.setLastOptUser(userId);
		AllotApplyAllotHis aaaH2 = new AllotApplyAllotHis();
		aaaH2.setId(autoId2);
		aaaH2.setLastOptUser(userId);
		keyMessageModifyDao.updateHisAllotByUuid(aaaH);
		keyMessageModifyDao.updateHisAllotByUuid(aaaH2);
		int cnameflag = 0;
		int c1Idnbrflag = 0;
		int cnameflag2 = 0;
		int c1Idnbrflag2 = 0;
		int c6Gatnbr1flag = 0;
		int c6Gatnbr2flag = 0;
		for (int i = 0; i < list.size(); i++) {
			String fieldname = (String) list.get(i).get("fieldName");
			if ("c1Cname".equals(fieldname)) {
				cnameflag++;
			} else if ("c1Idnbr".equals(fieldname)) {
				c1Idnbrflag++;
			} else if ("c6Gatnbr1".equals(fieldname)){
				c6Gatnbr1flag++;
			}
			if ("c2Cname".equals(fieldname)) {
				cnameflag2++;
			} else if ("c2Idnbr".equals(fieldname)) {
				c1Idnbrflag2++;
			}else if ("c6Gatnbr2".equals(fieldname)){
				c6Gatnbr2flag++;
			}
		}
		if (cnameflag <= 0 && "".equals(keyMesageApply1.getC1Cname())) {
			keyMesageApply1.setC1Cname(null);
		}
		if (c1Idnbrflag <= 0 && "".equals(keyMesageApply1.getC1Idnbr())) {
			keyMesageApply1.setC1Idnbr(null);
		}
		if (c6Gatnbr1flag <= 0 && "".equals(keyMesageApply1.getC6Gatnbr1())) {
			keyMesageApply1.setC6Gatnbr1(null);
		}
		if (cnameflag2 <= 0 && "".equals(keyMesageApply2.getC2Cname())) {
			keyMesageApply2.setC2Cname(null);
		}
		if (c1Idnbrflag2 <= 0 && "".equals(keyMesageApply2.getC2Idnbr())) {
			keyMesageApply2.setC2Idnbr(null);
		}
		if (c6Gatnbr2flag <= 0 && "".equals(keyMesageApply2.getC6Gatnbr2())) {
			keyMesageApply2.setC6Gatnbr2(null);
		}
		// 循环 -- 关键信息修改表
		List<KeyMessageModify> keyList = new ArrayList<KeyMessageModify>();

		BizInpAppC1Spec t = new BizInpAppC1Spec();
		t.setAppId(appId);
		try {
			t = applyService.get2(t);
		} catch (CoreException e1) {
		}
		for (Map<Object, Object> map : list) {
			// 拼接信息 复核人没有
			KeyMessageModify kmm = new KeyMessageModify();
			kmm.setAutoId((String) map.get("autoId"));
			TIMESTAMP timestamp = (TIMESTAMP) map.get("crtDate");
			java.sql.Date date = null;
			Date modsfyDate = null;
			if (timestamp != null) {
				try {
					date = timestamp.dateValue();
					long time = date.getTime();
					modsfyDate = new Date(time);
				} catch (SQLException e) {
				}
			}
			kmm.setModifyDate(modsfyDate);
			kmm.setOperator((String) map.get("crtUser"));
			kmm.setAppId((String) map.get("appId"));
			kmm.setKeyWordsId((String) map.get("fieldName"));
			kmm.setCurrValue((String) map.get("fieldOldValue"));
			kmm.setModifyValue((String) map.get("fieldNewValue"));
			kmm.setReviewStatus("1");
			kmm.setCrtDate(new Date());
			kmm.setReviewer(opear);
			kmm.setRemark(remark);
			kmm.setYdjFlag(t.getYdjFlag());
			kmm.setApplyName((String) map.get("applyName"));
			kmm.setCretifiId((String) map.get("cretifiId"));
			kmm.setCretifiType((String) map.get("cretifiType"));
			keyList.add(kmm);
		}
		Client client = null;
		try {
			keyMesageApply1.setAppId(appId.substring(0, appId.length() - 1));
			keyMesageApply2.setAppId(appId);
			applyService.updateBizInfoKeyMes(keyMesageApply1);
			applyService.updateBizInfoKeyMes2(keyMesageApply2);
			List<BizInpApplC1> bizList = applyInfoDao.queryBizAppToFish(appId.substring(0, appId.length() - 1));
			client = new Client(
					new URL("http://" + process_IP + "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
			for (BizInpApplC1 biz : bizList) {
				if(("1").equals(biz.getMatchingCardFlag())){
					continue;
				}
				ArrayList<String> list1 = new ArrayList<String>();
				String ydjflag = biz.getYdjFlag();
				String taoFlag = biz.getMatchingCardFlag();
				String idNbr = biz.getC1Idnbr();
				String c1C2Flag = biz.getC1c2Flag().toString();
				String appId2 = biz.getAppId();
				String name = biz.getC1Cname();
				String c1Idtype = biz.getC1Idtype();
				String c1Mobile = biz.getC1Mobile();
				String matchingCardFlag2 = biz.getMatchingCardFlag();
				String keyMessageFlag = "1";
				// 删除三方数据
				Map param = new HashMap<>();
				param.put("i_app_id", appId);
				keyMessageModifyDao.executeProceDeletePbocAndFico(param);
				// keyMessageModifyDao.executeProceDeletePboc(param);
				if ("1".equals(ydjflag) && !"0".equals(matchingCardFlag)) {
					Map param2 = new HashMap<>();
					String appIdPlus = "1".equals(appId.substring(appId.length() - 1, appId.length()))
							? appId.substring(0, appId.length() - 1) + "2"
							: appId.substring(0, appId.length() - 1) + "1";
					param2.put("i_app_id", appIdPlus);
					keyMessageModifyDao.executeProceDeletePbocAndFico(param2);
					// keyMessageModifyDao.executeProceDeletePboc(param2);
				}
				// 判断 主附卡
				if ("2".equals(c1C2Flag)) { // 单附
					BizInpApplC2 biz2 = applyInfoDao.findBiz2info(appId);
					idNbr = biz2.getC2Idnbr();
					appId2 = biz2.getAppId();
					name = biz2.getC2Cname();
					c1Idtype = biz2.getC2Idtype();
					c1Mobile = biz2.getC2Mobile();
				}
				logger.info("[关键信息修改]-----------appId:" + appId2);
				StringBuilder logList = new StringBuilder();
				if ("2".equals(ydjflag)) {
//					if ("1".equals(matchingCardFlag2)) {
//						appId2 = "1".equals(appId2.substring(appId2.length() - 1, appId2.length()))
//								? appId2.substring(0, appId2.length() - 1) + "2"
//								: appId2.substring(0, appId2.length() - 1) + "1";
//					}
					list1.add("{'app_id':'" + appId2 + "','ydjflag':'" + ydjflag + "','taoFlag':'" + taoFlag
							+ "','c1C2Flag':'" + c1C2Flag + "','idNbr':'" + idNbr + "','name':'" + name
							+ "','idType':'" + c1Idtype + "','mobile':'" + c1Mobile + "','keyMessageFlag':'"
							+ keyMessageFlag + "'}");
					//记录日志
					logList.append("{'app_id':'" + appId2 + "','ydjflag':'" + ydjflag + "','taoFlag':'" + taoFlag
							+ "','c1C2Flag':'" + c1C2Flag + "','idNbr':'*','name':'*','idType':'"
							+ c1Idtype + "','mobile':'*','keyMessageFlag':'" + keyMessageFlag + "'}");
				} else if ("1".equals(ydjflag)) {
//					if ("1".equals(matchingCardFlag2)) {
//						appId2 = "1".equals(appId2.substring(appId2.length() - 1, appId2.length()))
//								? appId2.substring(0, appId2.length() - 1) + "2"
//								: appId2.substring(0, appId2.length() - 1) + "1";
//					}
					list1.add("{'app_id':'" + appId2 + "','ydjflag':'" + ydjflag + "','taoFlag':'" + taoFlag
							+ "','c1C2Flag':'" + c1C2Flag + "','idNbr':'" + idNbr + "',name:'" + name
							+ "','idType':'" + c1Idtype + "','mobile':'" + c1Mobile + "','keyMessageFlag':'"
							+ keyMessageFlag + "'}");
					//记录日志
					logList.append("{'app_id':'" + appId2 + "','ydjflag':'" + ydjflag + "','taoFlag':'" + taoFlag
							+ "','c1C2Flag':'" + c1C2Flag + "','idNbr':'*',name:'*','idType':'"
							+ c1Idtype + "','mobile':'*','keyMessageFlag':'" + keyMessageFlag + "'}");
				}
				logger.info("关键信息修改------工作流传参为"+logList);
				for (int i = 0; i < list1.size(); i++) {
//					Object[] obj = new Object[] { list1.get(i) };
//					Object[] result = null;
					//工作流dubbo引用改造
					String obj = list1.get(i);
					String result = "";
					logger.info("[关键信息修改]---------[调工作流开始-----]--appId:" + appId2);
					if ("2".equals(ydjflag)) {
//						result = client.invoke("StartProcessBzk", obj);// .toString();

						result = topbpmService.StartProcessBzk(obj).toString();
					} else if ("1".equals(ydjflag)) {
//						result = client.invoke("StartProcessYdj", obj);// .toString();
						result = topbpmService.StartProcessYdj(obj).toString();
					}
					logger.info("[关键信息修改]---------[调工作流结束-----]--appId:",appId2);
					logger.info("startProcess==============",result);

				}
			}

			keyMessageModifyDao.insertKeyMessage(keyList);
			keyMessageModifyDao.updateModifyLogFlag(appId.substring(0, appId.length() - 1));
			// 删除分件表数据 appId
			keyMessageModifyDao.deleteAllot(appId.substring(0, appId.length() - 1));
		} catch (Exception e) {
			logger.error("[关键信息修改]---------[调工作流异常或者删除分件表异常-----]--appId:" + appId);
			throw new RuntimeException();
		} finally {
			if (client != null) {
				client.close();
				client = null;
			}
		}
	}

	@Override
	public Map<Object, Object> queryKeyMessageInBizC1AndBizC2(String appId) {
		return keyMessageModifyDao.queryKeyMessageInBizC1AndBizC2(appId);
	}

	@Override
	public String qeurUserNameByUserCode(String crtUser) {
		return keyMessageModifyDao.qeurUserNameByUserCode(crtUser);
	}
	
	@Override
	public String qeurUserNameByUserCode1(String crtUser) {
		return keyMessageModifyDao.qeurUserNameByUserCode1(crtUser);
	}

	@Override
	public List<Map<Object, Object>> queryDetialMessage(List<Map<Object, Object>> listAppId) {
		return keyMessageModifyDao.queryDetialMessage(listAppId);
	}

	@Override
	public void executeProceCLEAN_INTERFACE_UNION(Map param) {
		keyMessageModifyDao.executeProceCLEAN_INTERFACE_UNION(param);
	}

	@Override
	public String selectDelStatus(String appId) {
		return keyMessageModifyDao.queryDelStatus(appId);
	}

	@Override
	public void saveKxSFRZPass(String appId,ApplyLifeCicle alc) {
		
		try {
			//查询关键信息
			List<BizInpApplC1> bizList = applyInfoDao.queryBizAppToFish(appId);
			if(bizList.size()>0){
				BizInpApplC1 oldBiz = bizList.get(0);
				//清除可信任务表
				keyMessageModifyDao.deleteKxTask(appId);
				//清除可新信息表
				keyMessageModifyDao.deleteKxSFRZData(appId);
				//创建任务表
				TaskCallStatus taskCallStatus = new TaskCallStatus();
				taskCallStatus.setUuid(UUIDGenerator.getUUID());
				taskCallStatus.setTaskId(UUIDGenerator.getUUID());
				taskCallStatus.setCrtTime(new Date());
				taskCallStatus.setUpdTime(new Date());
				taskCallStatus.setAppId(appId);
				taskCallStatus.setCertNo(oldBiz.getC1Idnbr());
				taskCallStatus.setName(oldBiz.getC1Cname());
				taskCallStatus.setCertType(oldBiz.getC1Idtype());
				taskCallStatus.setMobile(oldBiz.getC1Mobile());
				taskCallStatus.setQuickFlag("1");
				taskCallStatus.setTaskStatus("0");
				taskCallStatus.setTaskType("002100");
				taskCallStatus.setUpdUser("CAMS");
				taskCallStatus.setRequestNum((short) 0);
				taskCallStatus.setFailureNum((short) 0);
				keyMessageModifyDao.insertkxTask(taskCallStatus);
				//插入声明周期
				applyInfoDao.insertApplyLifeCicle(alc);
				logger.info("可信信息重查发起成功，appid = [ {} ]",appId);
			}
		} catch (Exception e) {
			logger.error("可信信息数据库操作异常,appid = [ {} ],异常信息为：[ {} ]",new Object[]{appId,e});
			throw new RuntimeException();
		}
		
		
	}


}
