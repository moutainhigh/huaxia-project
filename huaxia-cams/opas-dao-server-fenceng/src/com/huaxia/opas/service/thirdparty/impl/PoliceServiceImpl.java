package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.thirdparty.PoliceDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.PoliceService;

/**
 * 公安服务实现类
 * 
 * @author zhiguo.li
 *
 */
public class PoliceServiceImpl extends AbstractService implements PoliceService , Serializable{

	private static final long serialVersionUID = 5724215842117802697L;
	@Resource(name = "policeDao")
	private PoliceDao policeDao;

	@Override
	public Map<String, String> selectSummaryInfo(Map<String, String> params) {
		return getPoliceDao().selectSummaryInfo(params);
	}

	public PoliceDao getPoliceDao() {
		return policeDao;
	}

	public void setPoliceDao(PoliceDao policeDao) {
		this.policeDao = policeDao;
	}

	@Override
	public Map<String, String> selectDetailInfo(Map<String, String> params) {
		return getPoliceDao().selectDetailInfo(params);
	}

	@Override
	public List<Object> selectDetailList(Map<String, String> params) {
		return getPoliceDao().selectDetailList(params);
	}

	@Override
	public Map<String, String> querytaskStatusInfoByAppId(String appId) {
		return getPoliceDao().querytaskStatusInfoByAppId(appId);
	}

	@Override
	public Map<String, String> selectPoliceDetailInfo(Map<String, String> params) {
		Map<String, String> policeDetailInfo = null;
		String appId = params.get("appId");
		String cardFlag = params.get("cardFlag");
		Map<String, String> c1Map = null;
		c1Map = getPoliceDao().selectC1Idtype(appId);
		String c1Idtype = "";
		//网申短表条码在C1表无信息 固定证件类型为身份证 20200908  胡宝川
		if(null==c1Map||c1Map.size()==0){
			c1Idtype="01";
		}else{
			String c1c2Flag = c1Map.get("c1c2Flag");
			if("0".equals(cardFlag)){
				c1Idtype = c1Map.get("c1Idtype");
			}else if ("1".equals(cardFlag)){
				c1Map = getPoliceDao().selectC2Idtype(appId);
				if(null!=c1Map) {
					c1Idtype = c1Map.get("c2Idtype");
				}
				
			}
		}
		
		if(("04".equals(c1Idtype)||"07".equals(c1Idtype)||"90".equals(c1Idtype)||"91".equals(c1Idtype))){
			//外国人公安nciic_foreigner_info
			policeDetailInfo = getPoliceDao().selectNciicForeignerInfo(params);
			if(null!=policeDetailInfo){
				policeDetailInfo.put("name",policeDetailInfo.get("xm"));
				policeDetailInfo.put("identityNo",policeDetailInfo.get("zjhm"));
				policeDetailInfo.put("recStatusValue",policeDetailInfo.get("resultCode"));
			}else {
				policeDetailInfo= new HashMap<String, String>();
			}
		}else{
			//增强公安信息
			policeDetailInfo = getPoliceDao().selectPoliceDetailInfo(params);
			if(null==policeDetailInfo){
				policeDetailInfo = new HashMap<String, String>();
			}
		}
		
		return policeDetailInfo;
	}

	@Override
	public int queryPoliceCountByAppId(String appId) {
		return getPoliceDao().selectPoliceCountByAppId(appId);
	}

	@Override
	public Map<String, String> selectPoliceXpInfo(String appId) {
		return getPoliceDao().selectPoliceXpInfo(appId);
	}

	@Override
	public Map<String, String> selectpoliceSummaryInfo(Map<String, String> param){
		Map<String, String> policeSummaryInfo = null;
		String appId = param.get("appId");
		String cardFlag = param.get("cardFlag");
		String c1Idtype = "";
		String c1c2Flag ="";
		Map<String, String>  c1Map = getPoliceDao().selectC1Idtype(appId);
		//网申短表条码在C1表无信息 固定证件类型为身份证 20200908  胡宝川
		if(null==c1Map||c1Map.size()==0){
			c1Idtype="01";
		}else{
			c1c2Flag = c1Map.get("c1c2Flag");
			//1主附同申 2单独申请附卡3单独申请主卡
			if(("1".equals(c1c2Flag)||"3".equals(c1c2Flag))&&"0".equals(cardFlag)){
				c1Idtype = c1Map.get("c1Idtype");
			}else if (("1".equals(c1c2Flag)||"2".equals(c1c2Flag))&&("1".equals(cardFlag))){
				c1Map = getPoliceDao().selectC2Idtype(appId);
				if(null!=c1Map) {
					c1Idtype = c1Map.get("c2Idtype");
				}
			}
		}
		
		if(("04".equals(c1Idtype)||"07".equals(c1Idtype)||"90".equals(c1Idtype)||"91".equals(c1Idtype))){
			//外国人公安nciic_foreigner_info
			policeSummaryInfo = getPoliceDao().selectNciicForeignerInfo(param);
		
			if(null!=policeSummaryInfo){
				policeSummaryInfo.put("name",policeSummaryInfo.get("xm"));
				policeSummaryInfo.put("identityNo",policeSummaryInfo.get("zjhm"));
				policeSummaryInfo.put("identityNoCheckRst",policeSummaryInfo.get("resultCode"));
			}else {
				policeSummaryInfo = new HashMap<String, String>();
			}
		}else{
			//增强公安信息
			policeSummaryInfo = getPoliceDao().selectpoliceSummaryInfo(param);
			if(null==policeSummaryInfo){
				policeSummaryInfo = new HashMap<String, String>();
			}
		}
		if("1".equals(c1c2Flag)||"3".equals(c1c2Flag)){
			policeSummaryInfo.put("c1Idtype",c1Idtype);
		}
		if("".equals(c1c2Flag)){
			policeSummaryInfo.put("c1Idtype","01");
		}
		policeSummaryInfo.put("c1c2Flag",c1c2Flag);
		return policeSummaryInfo;
	}

	@Override
	public Map<String, String> selectPoliceXpDetail(String appId) {
		return getPoliceDao().selectPoliceXpDetail(appId);
	}

	@Override
	public Map<String, String> querySummaryByAppId(Map<String, Object> map) {
		return getPoliceDao().querySummaryByAppId(map);
	}

	@Override
	public Map<String, String> selectAuthResultByAppid(String appId) {
		return getPoliceDao().selectAuthResultByAppid(appId);
	}


}
