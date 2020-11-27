package com.huaxia.opas.service.riskcheck.impl;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.dao.apply.ApplyLifeCicleDao;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.riskcheck.WSPlatformRiskInvestDao;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.riskcheck.WSPlatformRiskInvest;
import com.huaxia.opas.service.riskcheck.WSPlatformRiskInvestService;
import com.huaxia.opas.service.workflow.TopbpmService;
import com.huaxia.opas.util.CommonProperties;
import com.huaxia.opas.util.StringUtil;

public class WSPlatformRiskInvestServiceImpl implements WSPlatformRiskInvestService, Serializable {
	private static Logger log = LoggerFactory.getLogger(WSPlatformRiskInvestServiceImpl.class);
	private static final long serialVersionUID = 6289960947684678405L;
	@Resource(name = "wSPlatformRiskInvestDao")
	private WSPlatformRiskInvestDao wSPlatformRiskInvestDao;
	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDao;
	@Resource(name = "applyLifeCicleDao")
	private ApplyLifeCicleDao applyLifeCicleDao;
	@Resource(name = "topbpmService")
	private TopbpmService topbpmService;
	
	@Override
	public int queryWSPlatformRiskInvestCount(Map<String, String> params) {
		return wSPlatformRiskInvestDao.queryWSPlatformRiskInvestCount(params);
	}

	@Override
	public List<Map<String, String>> queryWSPlatformRiskInvestList(Map<String, String> params, int curNum,
			int pageNum) {
		return wSPlatformRiskInvestDao.queryWSPlatformRiskInvestList(params, curNum, pageNum);
	}
	
	@Override
	public int queryWSPlatformRiskInvestCountNotParam(Map<String, String> params) {
		return wSPlatformRiskInvestDao.queryWSPlatformRiskInvestCountNotParam(params);
	}
	
	@Override
	public List<Map<String, String>> queryWSPlatformRiskInvestListNotParam(Map<String, String> params, int curNum,
			int pageNum) {
		return wSPlatformRiskInvestDao.queryWSPlatformRiskInvestListNotParam(params, curNum, pageNum);
	}
	
	@Override
	public int queryWSPlatformInvestResultCount(Map<String, String> params) {
		return wSPlatformRiskInvestDao.queryWSPlatformInvestResultCount(params);
	}
	
	@Override
	public List<Map<String, String>> queryWSPlatformInvestResultList(Map<String, String> params, int curNum,
			int pageNum) {
		return wSPlatformRiskInvestDao.queryWSPlatformInvestResultList(params, curNum, pageNum);
	}
	
	@Override
	public String checkRemarkInfoByAppId(Map<String, String> params) {
		return wSPlatformRiskInvestDao.checkRemarkInfoByAppId(params);
	}

	@Override
	public int updateRemark(WSPlatformRiskInvest wspri) throws Exception {
		int result = 0;
		try{
			result = wSPlatformRiskInvestDao.updateBatch(wspri);
			if(result == 0){
				log.info("WSPlatformRiskInvestServiceImpl.updateRemark  变更人工调查结论和备注失败!");
			}
			log.info("WSPlatformRiskInvestServiceImpl.updateRemark  变更人工调查结论和备注成功!");
		}catch(Exception e){
			log.error("申请件拒绝流转异常", e);
		}
		return result;
	}
	
	@Override
	public int updateRefuse(WSPlatformRiskInvest wspri) throws Exception {
		int result = 0;
		Map<String, Object> refuseMap = new HashMap<String, Object>();
		try{
			result = wSPlatformRiskInvestDao.updateBatchRefuse(wspri);
			if(result > 0){
				refuseMap.put("appId", wspri.getAppId()); //申请件流水号
				//根据AppID查询所需的参数
				Map<String,String> paramMap = wSPlatformRiskInvestDao.queryRefuseParamByAppId(refuseMap);
				refuseMap.put("applyCard", paramMap.get("APP_PROD"));//申请产品
				
				//TODO 申请件插入归档表
				int archiveResult = wSPlatformRiskInvestDao.insertArchiveFlag(refuseMap);
				if(archiveResult == 0){
					log.info("wSPlatformRiskInvestDao.insertArchiveFlag 申请件插入归档表失败!", wspri.getAppId());
				}
				log.info("wSPlatformRiskInvestDao.insertArchiveFlag 申请件插入归档表成功!", wspri.getAppId());
				
				//TODO 申请件插入审批表
				refuseMap.put("autoId", paramMap.get("INSIDE_APP_NO"));//内审编号
				refuseMap.put("approveResult", "0"); //审批结论 0-拒绝，1-通过
				refuseMap.put("refuseCode1", wspri.getRefuseCirculateReason()); //审批拒绝码1
				int approvalResult = wSPlatformRiskInvestDao.insertBizInpApprovalForRefuse(refuseMap);
				if(approvalResult == 0){
					log.info("wSPlatformRiskInvestDao.insertBizInpApprovalForRefuse 申请件插入审批表失败!", wspri.getAppId());
				}
				log.info("wSPlatformRiskInvestDao.insertBizInpApprovalForRefuse 申请件插入审批表成功!", wspri.getAppId());
				
				Map<String,Object> userMap = new HashMap<String,Object>();//全流程记录
				userMap.put("userCode", wspri.getLastOptUser());
				Map<String,Object> apUserMap = bizInpApplDao.selectApUserByUuIdUserCode(userMap);
				String userCode = apUserMap.get("userCode").toString();
				String userName = apUserMap.get("userName").toString();
				ApplyLifeCicle a = new ApplyLifeCicle();
				a.setAppId(wspri.getAppId());
				a.setOperateResult("完成");
				a.setCrtDate(new Date());
				a.setUuid(StringUtil.randomUUIDPlainString());
				if(!"".equals(userCode)){
					a.setOperater(userName+"-"+userCode);
					a.setCrtUser(userCode);
				}else{
					a.setOperater("systemTb");
					a.setCrtUser("systemTb");
				}
				a.setOperateDesc(userName+"拒绝流转申请件");
				applyLifeCicleDao.addApplyLifeCicle(a);
				log.info("WSPlatformRiskInvestServiceImpl.updateRefuse 申请件拒绝流转成功!");
				return result;
			}else{
				log.info("WSPlatformRiskInvestServiceImpl.updateRefuse 申请件拒绝流转失败!");
			}
		}catch(Exception e){
			log.error("申请件拒绝流转异常", e);
		}
		return result;
	}
	
	@Override
	public int updateContinueCirculateSubmit(WSPlatformRiskInvest wspri) throws Exception {
		int result = 0;
		try{
			result = wSPlatformRiskInvestDao.updateContinueCirculateSubmit(wspri);
			if(result > 0){
				int resultFlag = wSPlatformRiskInvestDao.updateTempAppIdFlag(wspri);
				if(resultFlag > 0){
					log.info("wSPlatformRiskInvestDao.updateTempAppIdFlag 修改异步临时表的标识成功!");
				}else{
					log.info("wSPlatformRiskInvestDao.updateTempAppIdFlag 修改异步临时表的标识失败!");
				}
				Map<String,Object> userMap = new HashMap<String,Object>();//全流程记录
				userMap.put("userCode", wspri.getLastOptUser());
				Map<String,Object> apUserMap = bizInpApplDao.selectApUserByUuIdUserCode(userMap);
				String userCode = apUserMap.get("userCode").toString();
				String userName = apUserMap.get("userName").toString();
				ApplyLifeCicle a = new ApplyLifeCicle();
				a.setAppId(wspri.getAppId());
				a.setOperateResult("完成");
				a.setCrtDate(new Date());
				a.setUuid(StringUtil.randomUUIDPlainString());
				if(!"".equals(userCode)){
					a.setOperater(userName+"-"+userCode);
					a.setCrtUser(userCode);
				}else{
					a.setOperater("systemTb");
					a.setCrtUser("systemTb");
				}
				a.setOperateDesc(userName+"申请件继续流转");
				applyLifeCicleDao.addApplyLifeCicle(a);
				log.info("WSPlatformRiskInvestServiceImpl.updateContinueCirculateSubmit 申请件继续流转成功!");
			}else{
				log.info("WSPlatformRiskInvestServiceImpl.updateContinueCirculateSubmit 申请件继续流转失败!");
			}
		}catch(Exception e){
			log.error("申请件继续流转异常", e);
		}
		return result;
	}

	/**
	 * 继续流转调用工作流方法
	 */
	@Override
	public String continueCirculateInvokeBpm(Map<String,Object> paramMap){
		List<Map<String, Object>> list = new ArrayList<>();
		//boolean invokeSuccess = false;
		String msg = "";
		if (paramMap.size() > 0) {
			/*Client client = null;
			try {
				client = getClient(client);
			} catch (Exception e) {
				log.error("重拉工作流,创建webService客户端异常：", e);
			}*/
			list = wSPlatformRiskInvestDao.queryInvokeBpmAppId(paramMap);
			for (Map<String, Object> map : list) {
				String appId = map.get("appId").toString();
				StringBuffer sbXml = new StringBuffer(200);
				try {
					sbXml.append("{\"app_id\":\"").append(appId).append("\",\"ydjflag\":\"")
					.append(map.get("ydjFlag")).append("\",\"taoFlag\":\"").append(map.get("taoFlag"))
					.append("\",\"c1C2Flag\":\"").append(map.get("c1C2Flag")).append("\",\"idType\":\"")
					.append(map.get("idType")).append("\",\"name\":\"").append(map.get("name"))
					.append("\",\"mobile\":\"").append(map.get("mobile")).append("\",\"idNbr\":\"")
					.append(map.get("idNbr")).append("\",\"quick_card_flag\":\"").append(map.get("quickCardFlag"))
					.append("\"}");
					log.info("重拉报文:" + sbXml.toString());
					Map<String, String> streamMap = CommonProperties.getParoperMap();
					if ("2".equals(map.get("ydjFlag"))) {
						//invokeSuccess = false;
						/*Object[] obj = new Object[] { sbXml.toString() };
						String bpm_bzk_start_method = streamMap.get("bpm_bzk_start_method").toString();//获取工作流标准卡启动方法参数
						client.invoke(bpm_bzk_start_method, obj);*/
						String result = topbpmService.StartProcessBzk(sbXml.toString());
						log.info("标准卡工作流正常重新调用! 当前流水号：【{}】", appId);
						if (result != null && "SUCCESS".equals(result)) {// 工作流返回机制:SUCCESS-》成功,其它-》失败
							msg = "1";
						} else {
							msg = "2";
						}
						//invokeSuccess = true;
					} else {
						//invokeSuccess = false;
						/*Object[] obj = new Object[] { sbXml.toString() };
						String bpm_ydj_start_method = streamMap.get("bpm_ydj_start_method").toString();//获取工作流易达金启动方法参数
						client.invoke(bpm_ydj_start_method, obj);*/
						String result = topbpmService.StartProcessYdj(sbXml.toString());
						log.info("易达金工作流正常重新调用! 当前流水号：【{}】", appId);
						if (result != null && "SUCCESS".equals(result)) {// 工作流返回机制:SUCCESS-》成功,其它-》失败
							msg = "1";
						} else {
							msg = "2";
						}
						//invokeSuccess = true;
					}
				} catch (Exception e) {
					if (msg == "2") {
						log.error("工作流再次调起失败，流水号为：{}", appId, e);
						msg = "2";
					} else {
						log.error("工作流再次调起标志更新失败，流水号为：{}", appId, e);
					}
				}

			}
		}
		return msg;
	}
	
	/**
	 * 防止重复操作申请件的过滤方法
	 */
	@Override
	public List<Map<String, String>> selectAppIds(List<String> appIdList) {
		return wSPlatformRiskInvestDao.selectAppIds(appIdList);
	}
	
	/**
	 * 批量把申请件插入异步临时表的方法
	 */
	@Override
	public int insertTempBatchAppId(List<String> list) throws Exception {
		return wSPlatformRiskInvestDao.insertTempBatchAppId(list);
	}
	
	/**
	 * 获取异步临时表中申请件数据的方法
	 */
	@Override
	public List<Map<String, String>> selectTempAppIds() {
		return wSPlatformRiskInvestDao.selectTempAppIds();
	}
	
	/**
	 * 删除异步临时申请表的申请件方法
	 */
	@Override
	public int deleteTempAppId(String appId) throws Exception {
		return wSPlatformRiskInvestDao.deleteTempAppId(appId);
	}
	
	/**
	 * 自动删除异步临时申请表的申请件方法
	 */
	@Override
	public int autoDeleteTempAppIdByFlag() throws Exception {
		return wSPlatformRiskInvestDao.autoDeleteTempAppIdByFlag();
	}
	
	/**
	 * 获取Client连接
	 * @param client
	 * @return
	 */
	public Client getClient(Client client){
		try {
			if(client==null){
				Map<String, String> streamMap = CommonProperties.getParoperMap();
				String processIp= streamMap.get("process_IP").toString();//服务ip地址
				client = new Client(new URL("http://" + processIp+ "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
			}
		}  catch (Exception e) {
			log.error("连接工作流失败，失败原因为", e);
			throw new RuntimeException("连接工作流失败"+e.getMessage());
		}
		return client;
	}
	
	
}
