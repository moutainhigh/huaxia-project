package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.huaxia.opas.dao.thirdparty.TianYuDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.TianYuService;

/**
 * 腾讯天御分
 * 
 * @author gaoh
 *
 */
public class TianYuServiceImpl extends AbstractService implements TianYuService , Serializable{

	private static final long serialVersionUID = -7263606131246981700L;

	@Resource(name = "tianYuDao")
	private TianYuDao tianYuDao;

	public TianYuDao getTianYuDao() {
		return tianYuDao;
	}
	public void setTianYuDao(TianYuDao tianYuDao) {
		this.tianYuDao = tianYuDao;
	}

	@Override
	public Map<String, String> queryTianYuSummaryInfoByAppId(String appId) {
    Map<String,String> responseMap=new HashMap<String, String>();
	Map<String,String>	scoreInfoMap= tianYuDao.selectTianYuScoreInfoByAppId(appId);
	if(scoreInfoMap!=null){
		String found=scoreInfoMap.get("found");//表示该条记录能否查到,1为能查到，-1为查不到
		if("1".equals(found)){
			responseMap.put("queryResult", "查询成功");
		}
		if("-1".equals(found)){
			responseMap.put("queryResult", "未查得");
		}
		if(found==null||"".equals(found)){
			responseMap.put("queryResult", "异常");
		}
		responseMap.put("riskScore", scoreInfoMap.get("riskScore"));
		List<Map<String,String>>riskCodeMapList=tianYuDao.selectTianYuRiskCodeByAppId(appId);
		String riskCodeCombine="";
		for (Map<String, String> riskCodeMap : riskCodeMapList) {
			String riskCode="";
			String riskCodeValue="";
			if(riskCodeMap!=null){
				riskCode=riskCodeMap.get("riskCode");
				riskCodeValue=riskCodeMap.get("riskCodeValue");
				if("1".equals(riskCode)){
					riskCodeCombine+="1-信贷中介";
					
				}
				if("2".equals(riskCode)){
					riskCodeCombine+="2-不法分子";
					
				}
				if("3".equals(riskCode)){
					riskCodeCombine+="3-虚假资料";
					
				}
				if("4".equals(riskCode)){
					riskCodeCombine+="4-羊毛党";
					
				}
				if("5".equals(riskCode)){
					riskCodeCombine+="5-身份认证失败";
					
				}
				if("6".equals(riskCode)){
					riskCodeCombine+="6-疑似恶意欺诈";
					
				}
				if("7".equals(riskCode)){
					riskCodeCombine+="7-失信名单";
					
				}
				if("8".equals(riskCode)){
					riskCodeCombine+="8-异常支付行为";
					
				}
				if("301".equals(riskCode)){
					riskCodeCombine+="301-恶意环境";
					
				}
				if("503".equals(riskCode)){
					riskCodeCombine+="503-其他异常行为";
		
				}
				if("1".equals(riskCodeValue)){
					riskCodeCombine+="-低风险 ";
					continue;
				}
				if("2".equals(riskCodeValue)){
					riskCodeCombine+="-中风险 ";
					continue;
				}
				if("3".equals(riskCodeValue)){
					riskCodeCombine+="-高风险 ";
					continue;
				}
				
			}
			riskCodeCombine+=riskCode+" ";//未知风险码
		}
		responseMap.put("riskCodeCombine", riskCodeCombine);
	}else{
		responseMap.put("queryResult", "未发起查询");
	}
		return responseMap;
	}

}