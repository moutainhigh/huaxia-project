package com.huaxia.opas.action.thirdparty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.thirdparty.QiYeBasic;
import com.huaxia.opas.domain.thirdparty.QiYeOrgbasic;
import com.huaxia.opas.domain.thirdparty.QiYeOrgdetail;
import com.huaxia.opas.domain.thirdparty.QiYePerson;
import com.huaxia.opas.domain.thirdparty.QiYePunishbreak;
import com.huaxia.opas.domain.thirdparty.QiYeShareholder;
import com.huaxia.opas.service.thirdparty.QiYeService;


/**
 * 第三方 企业及行业信息
 * 
 * @author lipengfei
 *
 */
public class QiYeAction implements Action {
	@Resource(name = "qiYeService")
	private QiYeService qiYeService;

	public QiYeService getQiYeService() {
		return qiYeService;
	}

	public void setQiYeService(QiYeService qiYeService) {
		this.qiYeService = qiYeService;
	}

	/**
	 * 查询企业及行业信息,摘要部分
	 * 
	 */
	public void queryQiyeInfo(Context context) {
		String appId = context.getData("appId");
		try {
			Map<String, String> qiyeCode = queryQiYeCode(appId);
			// 查询详细信息
			Map<String, String> responseMap = qiYeService.queryQiYeInfoByAppId(appId);
			context.setData("success", true);
			context.setData("qiYeCode", qiyeCode);
			context.setData("qiYe", responseMap);
		} catch (Exception e) {
			e.printStackTrace();
			context.setData("success", false);
		}
	}

	/**
	 * 查询企业及行业信息,详情部分
	 * 
	 */
	public void queryQiyeDetail(Context context) throws Exception {
		String appId = context.getData("appId");
		try {
			Map<String, String> qiyeCode = queryQiYeCode(appId); 
			// 查询详细信息
			Map<String, String> qiYedatails = qiYeService.queryQiYeDatailsByAppId(appId);

			/*
			 * 备用的查询方法,service和dao有全套的查询方法,有需要解开注释就能用 // 查询照面信息 List<QiYeBasic>
			 * qiYeBasic = qiYeService.queryQiYeBasic(appId); // 查询主要管理人员(多个)
			 * List<QiYePerson> qiYePerson = qiYeService.queryQiYePerson(appId);
			 * // 查询失信被执行人信息(多个) List<QiYePunishbreak> qiYePunishbreak =
			 * qiYeService.queryQiYePunishbreak(appId); // 查询股东及出资人信息(多个)
			 * List<QiYeShareholder> qiYeShareholder =
			 * qiYeService.queryQiYeShareholder(appId); // 查询组织机构列表(多个)
			 * List<QiYeOrgbasic> qiYeQrgbasic =
			 * qiYeService.queryQiYeOrgbasic(appId); // 查询组织机构详情
			 * List<QiYeOrgdetail> qiYeOrgdateil =
			 * qiYeService.queryQiYeQrgdetail(appId);
			 */

			context.setData("success", true);
			context.setData("qiYeCode", qiyeCode);
			context.setData("qiYedatails", qiYedatails);

		} catch (Exception e) {
			e.printStackTrace();
			context.setData("success", false);
		}

	}
	
	/**
	 * 企业行业查询成功次数详情显示
	 */
	public void queryQiYeNumber(Context context) throws Exception {		
		String yearNumber = context.getData("queryYear");
		try{
			Map<String,String> yearMap = qiYeService.queryQiYeNumber(yearNumber);
			context.setData("success", true);
			if(yearMap != null){
				context.setData("yearMap", yearMap);
			}else{
				yearMap = new HashMap<String,String>();
				yearMap.put("JAN", "没有查询数据");
				yearMap.put("FEB", "没有查询数据");
				yearMap.put("MAR", "没有查询数据");
				yearMap.put("APR", "没有查询数据");
				yearMap.put("MAY", "没有查询数据");
				yearMap.put("JUN", "没有查询数据");
				yearMap.put("JUL", "没有查询数据");
				yearMap.put("AUG", "没有查询数据");
				yearMap.put("SEP", "没有查询数据");
				yearMap.put("OCT", "没有查询数据");
				yearMap.put("NOV", "没有查询数据");
				yearMap.put("DEC", "没有查询数据");
				context.setData("yearMap", yearMap);
			}
		}catch (Exception e) {
			e.printStackTrace();
			context.setData("success", false);		
		}
		
	}
	
	/**
	 * 企业及行业信息,查询结果
	 */

	public  Map<String, String> queryQiYeCode(String appId){
		Map<String, String> qiYecodeMap = qiYeService.queryQiYecode(appId);
		if (qiYecodeMap != null) {
			String code = qiYecodeMap.get("code");
			if ("200".equals(code)) {
				qiYecodeMap.put("code", "查询成功");
			} else if ("404".equals(code)) {
				qiYecodeMap.put("code", "查询失败");
			} else if ("445".equals(code)) {
				qiYecodeMap.put("code", "查询失败");
			} else if ("444".equals(code)) {
				qiYecodeMap.put("code", "查询失败");
			}else {
				qiYecodeMap.put("code", "查询异常");
			}
		} else{
			qiYecodeMap = new HashMap<String, String>();
			qiYecodeMap.put("code", "未发起查询");
		}
		return qiYecodeMap;
	}
}
