package com.huaxia.opas.service.baseinfo.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.baseinfo.BaseCustInfoDao;
import com.huaxia.opas.dao.credit.PatchboltDao;
import com.huaxia.opas.domain.baseinfo.BaseCustInfo;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.service.baseinfo.BaseCustInfoService;
import com.huaxia.opas.util.UUIDGenerator;

public class BaseCustInfoServiceImpl implements BaseCustInfoService,Serializable  {
	
	private static Logger logger = LoggerFactory.getLogger(BaseCustInfoServiceImpl.class);
	
	@Resource(name = "baseCustInfoDao")
	private BaseCustInfoDao baseCustInfoDao;
	
	@Resource(name = "patchboltDao")
	private PatchboltDao patchboltDao;
	
	@Override
	public int save(BaseCustInfo t) throws CoreException {
		return 0;
	}

	@Override
	public int remove(BaseCustInfo t) throws CoreException {
		return 0;
	}

	@Override
	public int update(BaseCustInfo t) throws CoreException {
		return 0;
	}

	@Override
	public BaseCustInfo get(BaseCustInfo t) {
		return null;
	}

	@Override
	public void insertCustInfo(String appId) throws CoreException {


		BaseCustInfo baseCustInfo = new BaseCustInfo();

		Map<String, Object> map = baseCustInfoDao.queryFromBizInpApplC1(appId);
		
		if (map != null) {
			baseCustInfo.setAutoId(UUIDGenerator.getUUID());
			baseCustInfo.setAppId(appId);
			baseCustInfo.setCustName((String) map.get("custName"));
			baseCustInfo.setSex((String) map.get("sex"));
			baseCustInfo.setNationaity((String) map.get("nationaity"));
			baseCustInfo.setAge((BigDecimal)map.get("age"));
			baseCustInfo.setCertifiType((String) map.get("certifiType"));
			baseCustInfo.setCertifiNo((String) map.get("certifiNo"));//证件号码
			baseCustInfo.setHousePhone((String) map.get("housePhone"));
			baseCustInfo.setWorkCompany((String) map.get("workCompany"));
			baseCustInfo.setOfficeTel((String) map.get("officeTel"));
			baseCustInfo.setMobileNo((String) map.get("mobileNo"));
			baseCustInfo.setApplyCard((String)map.get("applyCard"));
		} else {
			logger.error(">>>>>>>>>>>>>>>>>>>所给appId未查到任何相关信息");
		}
		

		/*
		 * //获取团办号 String c4Apbatch = (String) map.get("c4Apbatch");
		 * if(c4Apbatch != null && !"".equals(c4Apbatch)){ //查询团办号是否匹配 //查标卡团办名单
		 * int count1 = baseCustInfoDao.queryFromTeamList(c4Apbatch); //查易达金团办名单
		 * int count2 = baseCustInfoDao.queryFromTeamDealList(c4Apbatch);
		 * if(count1 == 1){
		 * 
		 * } if(count2 == 1){
		 * 
		 * } }
		 */
		// 查询公安状态
		String idNbr = "";
		if(map != null && !"".equals(map)){
			idNbr = (map.get("certifiNo") == null ? "" : map.get("certifiNo").toString());
		}
		if(!"".equals(idNbr)){
			map = baseCustInfoDao.queryPoliceStatus(idNbr);
			
			if(map != null){
				
				String idEntityNoCheckRst = (String) map.get("idEntityNoCheckRst");
				String nameCheckRst = (String) map.get("nameCheckRst");
				if ("1".equals(idEntityNoCheckRst) && "1".equals(nameCheckRst)) {
					// 完全匹配
					baseCustInfo.setPoliceStatus("0");
				} else if ("0".equals(idEntityNoCheckRst) && "0".equals(nameCheckRst)) {
					// 库中无此号
					baseCustInfo.setPoliceStatus("1");
				} else if ("1".equals(idEntityNoCheckRst) && "0".equals(nameCheckRst)) {
					// 身份证匹配,姓名不匹配
					baseCustInfo.setPoliceStatus("2");
				} else if ("0".equals(idEntityNoCheckRst) && "1".equals(nameCheckRst)) {
					// 姓名匹配,身份证不匹配
					baseCustInfo.setPoliceStatus("3");
				}
			}else{
				
				logger.error(">>>>>>>>>>>>>>>所给--证件号--未查到任何公安相关信息");
			}
		}
		
		// 调用crm系统查询是否为我行客户

		/*
		 * //判断是否是已持卡客户 int count = baseCustInfoDao.queryIsHaveCard(appId);
		 * if(count == 1){ baseCustInfo.setIsHavecardCust("1"); }else{
		 * baseCustInfo.setIsHavecardCust("0"); }
		 */
		String str = null;
		try {
			str = baseCustInfoDao.selectCustInfo(appId);
			if (str == null || "".equals(str)) {
				// 执行插入操作
				baseCustInfoDao.insertCustInfo(baseCustInfo);
				
				//根据appId判断该申请件是否为套卡
				//根据appId查询进件卡
				BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId);
				//0-无套卡标识  1-主卡  2-套卡
				char[] arr = appId.toCharArray();
				if(bizinpapplc1 != null){
					
					if(!"0".equals(bizinpapplc1.getMatchingCardFlag())){
						if(arr.length==16&&arr[15]=='1'){
							arr[15]='2';
							baseCustInfo.setAppId(new String(arr));
							baseCustInfo.setAutoId(UUIDGenerator.getUUID());
							// 执行插入操作
							baseCustInfoDao.insertCustInfo(baseCustInfo);
						}else{
							arr[15]='1';
							baseCustInfo.setAppId(new String(arr));
							baseCustInfo.setAutoId(UUIDGenerator.getUUID());
							// 执行插入操作
							baseCustInfoDao.insertCustInfo(baseCustInfo);
						}
					}
				}
			} else {
				// 执行更新操作
				baseCustInfoDao.updateCustInfo(baseCustInfo);
				
				//根据appId判断该申请件是否为套卡
				//根据appId查询进件卡
				BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId);
				//0-无套卡标识  1-主卡  2-套卡
				char[] arr = appId.toCharArray();
				if(bizinpapplc1 != null){
					
					if(!"0".equals(bizinpapplc1.getMatchingCardFlag())){
						if(arr.length==16&&arr[15]=='1'){
							arr[15]='2';
							baseCustInfo.setAppId(new String(arr));
							// 执行更新操作
							baseCustInfoDao.updateCustInfo(baseCustInfo);
						}else{
							arr[15]='1';
							baseCustInfo.setAppId(new String(arr));
							// 执行更新操作
							baseCustInfoDao.updateCustInfo(baseCustInfo);
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return;
	}

}
