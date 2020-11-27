package com.huaxia.opas.action.thirdparty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.AntService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 第三方-蚂蚁
 * 
 * @author zhiguo.li
 *
 */
public class AntAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(AntAction.class);

	@Resource(name = "antService")
	private AntService antService;
	public AntService getAntService() {
		return antService;
	}

	public void setAntService(AntService antService) {
		this.antService = antService;
	}
	/**
	 * 查询蚂蚁摘要信息
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void querySummaryInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]蚂蚁摘要信息");
		}
		Map<String, String> antSummaryInfo = antService.selectSummaryInfo(appId);
		if (antSummaryInfo != null) {
			if("NAME_Mismatch".equals(antSummaryInfo.get("codeNameEnglish"))){//蚂蚁 姓名 是否变红的依据。  高风险变红
				antSummaryInfo.put("codeNameEnglish", "1");//变红
			}else{
				antSummaryInfo.put("codeNameEnglish", "0");
			}
            if( "CERTNO_Match_Trust_Other_Sharing_Good".equals(antSummaryInfo.get("codeCertnoEnglish"))||
            	"CERTNO_Match_Trust_Other_Sharing_Bad".equals(antSummaryInfo.get("codeCertnoEnglish"))||
            	"CERTNO_Match_Trust_Other_Recency_Good".equals(antSummaryInfo.get("codeCertnoEnglish"))||
            	"CERTNO_Match_Trust_Other_Recency_Bad".equals(antSummaryInfo.get("codeCertnoEnglish"))||
            	"CERTNO_Match_Trust_Other_Reliability_Good".equals(antSummaryInfo.get("codeCertnoEnglish"))||
            	"CERTNO_Match_Trust_Other_Reliability_Bad".equals(antSummaryInfo.get("codeCertnoEnglish"))||
            	"CERTNO_Mismatch".equals(antSummaryInfo.get("codeCertnoEnglish"))||
            	"CERTNO_History_NegativeList".equals(antSummaryInfo.get("codeCertnoEnglish"))
            	) {//蚂蚁 证件号 是否变红的依据。  高风险变红
            	antSummaryInfo.put("codeCertnoEnglish", "1");//变红
			  } else {
				antSummaryInfo.put("codeCertnoEnglish", "0");
			}
            if("PHONE_Mismatch".equals(antSummaryInfo.get("codePhoenEnglish"))||
               "PHONE_History_NegativeList".equals(antSummaryInfo.get("codePhoenEnglish"))){//蚂蚁 手机号 是否变红的依据。  高风险变红
            	antSummaryInfo.put("codePhoenEnglish", "1");//变红
			}else{
				antSummaryInfo.put("codePhoenEnglish", "0");
			}
            if("ADDR_Mismatch".equals(antSummaryInfo.get("codeAddrEnglish"))){//蚂蚁 地址 是否变红的依据。  高风险变红
            	antSummaryInfo.put("codeAddrEnglish", "1");//变红
			}else{
				antSummaryInfo.put("codeAddrEnglish", "0");
			}
			Map<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put("appId",appId);
			List<Map<String,Object>> riskTypes= antService.findZMRiskTypesGroup(paramMap);
			String useRiskType="";
			for (int i = 0; i < riskTypes.size(); i++) {
				if(riskTypes.get(i)!=null&&riskTypes.get(i).get("RISK_TYPE")!=null&&!"".equals(riskTypes.get(i).get("RISK_TYPE"))){
				  if(i==0){
					 useRiskType="1."+useRiskType;
				  }else{
					 useRiskType+="  "+(i+1)+".";
				  }
				String riskType=riskTypes.get(i).get("RISK_TYPE").toString();
				if("AA001".equals(riskType)){
					useRiskType+="金融信贷类逾期未还款";
					continue;
				}
				if("AA002".equals(riskType)){
					useRiskType+="金融信贷类套现";
					continue;
				}
				if("AB001".equals(riskType)){
					useRiskType+="公检法被执行人";
					continue;
				}
				if("AC001".equals(riskType)){
					useRiskType+="支付行业盗卡者/盗卡者同伙";
					continue;
				}
				if("AC002".equals(riskType)){
					useRiskType+="支付行业欺诈者/欺诈同伙";
					continue;
				}
				if("AC003".equals(riskType)){
					useRiskType+="支付行业盗用操作/盗用者同伙";
					continue;
				}
				if("AC004".equals(riskType)){
					useRiskType+="支付行业盗用支出/盗用者同伙";
					continue;
				}
				if("AC005".equals(riskType)){
					useRiskType+="支付行业骗赔";
					continue;
				}
				if("AC007".equals(riskType)){
					useRiskType+="支付行业案件库黑名单";
					continue;
				}
				if("AD001".equals(riskType)){
					useRiskType+="出行行业汽车租赁逾期未付款";
					continue;
				}
				if("AD002".equals(riskType)){
					useRiskType+="出行行业汽车租赁逾期未还车";
					continue;
				}
				if("AD003".equals(riskType)){
					useRiskType+="出行行业汽车租赁违章逾期未付款";
					continue;
				}
				if("AD004".equals(riskType)){
					useRiskType+="出行行业汽车租赁其他逾期未付款";
					continue;
				}
				if("AD005".equals(riskType)){
					useRiskType+="出行行业单车租赁逾期未付款";
					continue;
				}
				if("AD006".equals(riskType)){
					useRiskType+="出行行业单车租赁逾期未还车";
					continue;
				}
				if("AD007".equals(riskType)){
					useRiskType+="出行行业交通工具乘用费逾期未付款";
					continue;
				}
				if("AD008".equals(riskType)){
					useRiskType+="出行行业路桥费逾期未付款";
					continue;
				}
				if("AD009".equals(riskType)){
					useRiskType+="出行行业停车费逾期未付款";
					continue;
				}
				if("AD010".equals(riskType)){
					useRiskType+="出行行业相关违约";
					continue;
				}
				if("AE001".equals(riskType)){
					useRiskType+="酒店行业逾期未付款";
					continue;
				}
				if("AF001".equals(riskType)){
					useRiskType+="电商行业虚假交易";
					continue;
				}
				if("AF002".equals(riskType)){
					useRiskType+="电商行业恶意差评";
					continue;
				}
				if("AF003".equals(riskType)){
					useRiskType+="电商行业涉嫌售假";
					continue;
				}
				if("AF004".equals(riskType)){
					useRiskType+="电商行业卖家套现";
					continue;
				}
				if("AF005".equals(riskType)){
					useRiskType+="电商行业逾期未还款";
					continue;
				}
				if("AG001".equals(riskType)){
					useRiskType+="租房行业房租逾期未付款";
					continue;
				}
				if("AG002".equals(riskType)){
					useRiskType+="租房行业杂费逾期未付款";
					continue;
				}
				if("AG003".equals(riskType)){
					useRiskType+="租房行业其他违约未付款";
					continue;
				}
				if("AG004".equals(riskType)){
					useRiskType+="租房行业租客其他违约";
					continue;
				}
				if("AG005".equals(riskType)){
					useRiskType+="租房行业非正常解约";
					continue;
				}
				if("AH001".equals(riskType)){
					useRiskType+="运营商行业逾期未还款";
					continue;
				}
				if("AI003".equals(riskType)){
					useRiskType+="保险行业逾期未还款";
					continue;
				}
				if("AK001".equals(riskType)){
					useRiskType+="租赁行业逾期未付款";
					continue;
				}
				if("AK002".equals(riskType)){
					useRiskType+="租赁行业逾期未归还";
					continue;
				}
				useRiskType+=riskType;
				continue;
				}
			}
			antSummaryInfo.put("riskTypes", useRiskType);
			JSONObject jsonObject = JSONObject.fromObject(antSummaryInfo);
			context.setData("success", true);
			context.setData("applyAntSummary", jsonObject.toString());
			return;
		}

		context.setData("success", false);
	}
	/**
	 *@Title:queryDetailInfo
	 *@Description:蚂蚁详情查询
	 *@param context
	 *@throws Exception
	 *@author: gaohui
	 *@Date:2017年7月31日下午3:03:28
	 */
	public void queryDetailInfo(Context context) throws Exception {
		Map map = context.getDataMap();
		String appId = (String) context.getData("appId");
		String str_sta = "";
		String str_statem = "";
		/*Assert.notNull(appId, "请求申请件编号为空!");
		
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]蚂蚁摘要信息");
		}*/
		Map<String,String> taskRequestInfo= antService.findThreePartiesTaskRequestInfo(map);//三方 任务结果表信息 
		String searchStatus="未查询";
        if(taskRequestInfo!=null){// reqAntScore   reqAntIvs   reqAntRisklist   0 未查询    1 查询成功    2 查询失败
        	if((taskRequestInfo.get("reqAntScore")!=null&&"1".equals(taskRequestInfo.get("reqAntScore")))||
        	   (taskRequestInfo.get("reqAntIvs")!=null&&"1".equals(taskRequestInfo.get("reqAntIvs")))||
        	   (taskRequestInfo.get("reqAntIvs")!=null&&"1".equals(taskRequestInfo.get("reqAntIvs")))){
        		searchStatus="查询成功";
        	}
        	if((taskRequestInfo.get("reqAntScore")!=null&&"2".equals(taskRequestInfo.get("reqAntScore")))||
              (taskRequestInfo.get("reqAntIvs")!=null&&"2".equals(taskRequestInfo.get("reqAntIvs")))||
              (taskRequestInfo.get("reqAntIvs")!=null&&"2".equals(taskRequestInfo.get("reqAntIvs")))){
             	searchStatus="查询失败";
            }
        	
		}
		Map<String, String> antDetailInfo = new  LinkedHashMap<String,String>();
		Map<String, String> antDetailData=	antService.selectDetailInfo(appId);
		if(antDetailData!=null){
			antDetailInfo.putAll(antDetailData);
		}
		antDetailInfo.put("searchStatus", searchStatus);
		//是否列入失信被执行人名单
		String str_status = antDetailInfo.get("status");
		if (str_status!=null&&!"".equals(str_status)){
			str_sta = "是";
		}else{
			str_sta = "否";
		}
		//用户对已处理异议是否仍有异议
		String str_statement = antDetailInfo.get("statement");
		if (str_statement!=null&&!"".equals(str_statement)){
			str_statem = "是";
		}else{
			str_statem = "否";
		}
		antDetailInfo.put("status", str_sta);
		antDetailInfo.put("statement", str_statem);
	//	antDetailInfo.put("recStatus", str_app);
		JSONObject jsonObject = JSONObject.fromObject(antDetailInfo);
		context.setData("success", true);
		context.setData("applyAntDetail", jsonObject.toString());
	}
	/**
	 *@Title:findZMriskListMessage
	 *@Description:蚂蚁详情的风险名单信息
	 *@param context
	 *@author: gaohui
	 *@Date:2017年7月19日上午10:03:25
	 */
	public void findZMriskListMessage(Context ctx){
		Map map=ctx.getDataMap();
		/*int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}*/

		List<Map<String,String>> zMriskListMessageList = new ArrayList<Map<String,String>>();
	//	int count = 0;
		try {
		   // count = antService.findZMriskListMessageCount(map);
		  //  if(count>0){
		    	zMriskListMessageList =antService.findZMriskListMessageData(map);
		   // }
		   
		/*for (int i = 0; i < zMriskListMessageList.size(); i++) {
			 StringBuilder existObjection=new StringBuilder();
			if(zMriskListMessageList.get(i)!=null){
				String extendinfo= zMriskListMessageList.get(i).get("extendinfo");
				if(extendinfo==null){
					continue;
				}
				
				JSONArray jsonArray=JSONArray.fromObject(extendinfo);
				for (Object jsonObject : jsonArray) {
					Map<String,Object> extendinfoMap=(Map<String,Object>)jsonObject;
					//System.err.println(extendinfoMap.get("extendinfo"));
					//duty   gist_cid ext_str_2   event_where  performance_new   gist_unit
					if(extendinfoMap!=null){
						if(extendinfoMap.get("extendinfo")==null){
							continue;
						}
					 Map<String,Object> extendinfoMapinfo=(Map<String,Object>)extendinfoMap.get("extendinfo");
					 if(extendinfoMapinfo!=null){
						 Map<String,Object> extendinfoMapDutyProper=(Map<String,Object>)extendinfoMapinfo.get("duty");
						if(extendinfoMapDutyProper!=null){
							if(extendinfoMapDutyProper.get("name")!=null){
								  existObjection.append(extendinfoMapDutyProper.get("name").toString()+": ");
								if(extendinfoMapDutyProper.get("value")!=null){
									 existObjection.append(extendinfoMapDutyProper.get("value").toString()+"<br>");
								}
							}
						}
						 Map<String,Object> extendinfoMapGistcidProper=(Map<String,Object>)extendinfoMapinfo.get("gist_cid");
							if(extendinfoMapGistcidProper!=null){
								if(extendinfoMapGistcidProper.get("name")!=null){
									  existObjection.append(extendinfoMapGistcidProper.get("name").toString()+": ");
									if(extendinfoMapGistcidProper.get("value")!=null){
										 existObjection.append(extendinfoMapGistcidProper.get("value").toString()+"<br>");
									}
								}
							}
							 Map<String,Object> extendinfoMapEventwhereProper=(Map<String,Object>)extendinfoMapinfo.get("event_where");
								if(extendinfoMapEventwhereProper!=null){
									if(extendinfoMapEventwhereProper.get("name")!=null){
										  existObjection.append(extendinfoMapEventwhereProper.get("name").toString()+": ");
										if(extendinfoMapEventwhereProper.get("value")!=null){
											 existObjection.append(extendinfoMapEventwhereProper.get("value").toString()+"<br>");
										}
									}
								}
								Map<String,Object> extendinfoMapPerformancenewProper=(Map<String,Object>)extendinfoMapinfo.get("performance_new");
								if(extendinfoMapPerformancenewProper!=null){
									if(extendinfoMapPerformancenewProper.get("name")!=null){
										  existObjection.append(extendinfoMapPerformancenewProper.get("name").toString()+": ");
										if(extendinfoMapPerformancenewProper.get("value")!=null){
											 existObjection.append(extendinfoMapPerformancenewProper.get("value").toString()+"<br>");
										}
									}
								}
								Map<String,Object> extendinfoMapGistunitProper=(Map<String,Object>)extendinfoMapinfo.get("gist_unit");
								if(extendinfoMapGistunitProper!=null){
									if(extendinfoMapGistunitProper.get("name")!=null){
										  existObjection.append(extendinfoMapGistunitProper.get("name").toString()+": ");
										if(extendinfoMapGistunitProper.get("value")!=null){
											 existObjection.append(extendinfoMapGistunitProper.get("value").toString()+"<br>");
										}
									}
								}
						//=======
					 }
					}	
				}
			}
			//==
			zMriskListMessageList.get(i).put("existObjection", existObjection.toString());//已存在异议 展示 
			//zMriskListMessageList.get(i).put("disposedObjection", disposedObjection.toString());//已处理异议 展示  目前没找到该字段  20170720 
		}*/
		    Map<String, Object> dataMap = new HashMap<String, Object>();
			//dataMap.put("total", count);
			dataMap.put("rows", zMriskListMessageList);
			ctx.setDataMap(dataMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
