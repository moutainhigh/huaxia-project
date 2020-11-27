package com.huaxia.opas.action.credit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.BizInpAppl;
import com.huaxia.opas.service.allot.AllotCommonService;
import com.huaxia.opas.service.credit.TeamPhoneService;
import com.huaxia.opas.service.thirdparty.PBOCService;
import com.huaxia.opas.service.thirdparty.PoliceService;
import com.huaxia.opas.util.UUIDGenerator;
/**
 * 集体电核
 * @author wangdebin
 * @date  2017年3月24日
 * @version v1.1(初始v1.0)
 * @history 修改历史记录
 * ----------------------------------
 * 2017/11/09 修改套卡公安无显示标识问题
 * ----------------------------------
 */
public class TeamPhoneAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(TeamPhoneAction.class);

	@Resource(name = "teamPhoneService")
	private TeamPhoneService teamPhoneService;
	
	@Resource(name="allotCommonService")
	private AllotCommonService allotCommonService;
	
	@Resource(name = "pbocService")
	private PBOCService pbocService;
	
	@Resource(name = "policeService")
	private PoliceService policeService;
	public PBOCService getPbocService() {
		return pbocService;
	}

	public void setPbocService(PBOCService pbocService) {
		this.pbocService = pbocService;
	}
	/*************************
	 *@describe:展示同一家公司征信环节电核情况
	 *@param ctx
	 *@throws CoreException
	 *@throws ParseException 
	 *@author 王德彬
	 *@date:2017-03-24
	 *************************/
	public void listBizInpAppl(Context ctx) {
		Gson gson = new Gson();
		BizInpAppl bizInpAppl = gson.fromJson(gson.toJson(ctx.getDataMap()), BizInpAppl.class);
		Map map = ctx.getDataMap();
		String c4ApBatch = (String) map.get("c4ApBatch");
		String c1Coname = (String) map.get("c1Coname");
		String c1Cotel = (String) map.get("c1Cotel");
		String ydjFlag = (String) map.get("ydjFlag");
		
		bizInpAppl.setYdjFlag(ydjFlag);
		if (c4ApBatch != null && !"".equals(c4ApBatch)) {
			bizInpAppl.setC4ApBatch(c4ApBatch);
		}else{
			bizInpAppl.setC4ApBatch(null);
		}
		if (c1Coname != null && !"".equals(c1Coname)) {
			bizInpAppl.setC1Coname(c1Coname);
		}else{
			bizInpAppl.setC1Coname(null);
		}
		if (c1Cotel != null && !"".equals(c1Cotel)) {
			bizInpAppl.setC1Cotel(c1Cotel);
		}else{
			bizInpAppl.setC1Cotel(null);
		}
		//bizInpAppl.setCurrNode("02");//征信调查
		bizInpAppl.setDelStatus("0");//未完成
		//bizInpAppl.setCurrStatus("3");//
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<BizInpAppl> list = new ArrayList<BizInpAppl>();
		int count = 0;
		try {
			count = teamPhoneService.countAppList(bizInpAppl);
			if (count > 0) {
				list = teamPhoneService.queryAppList(bizInpAppl, curNum, pageNum);
				String martchingCardFlag=null;
				for(int i=0;i<list.size();i++){
					String appId=list.get(i).getAppId();
					martchingCardFlag=list.get(i).getMatchingCardFlag();
					//易达金查询人行信息不能以尾数2查询,要以易达金卡查询  套卡情况
					if("1".equals(ydjFlag)&&"1".equals(martchingCardFlag)){
						if(appId.endsWith("1")){
							appId=appId.substring(0,15)+"2";
						}else if(appId.endsWith("2")){
							appId=appId.substring(0,15)+"1";
						}
					}
					Integer patchStatusR=pbocService.findCountPbocPersonInfoByAppId(appId);
					Integer patchStatusP=policeService.queryPoliceCountByAppId(appId);
					list.get(i).setPatchStatusR(patchStatusR);
					list.get(i).setPatchStatusP(patchStatusP);
					BizInpAppl biz=new BizInpAppl();
					biz=list.get(i);
					String currUser="";
					String userName="";
					if(biz!=null){
						//currUser=biz.getCurrUser();
						currUser=biz.getLastOptUser();
						if(currUser!=null){
							AllotCommon allotCommon=allotCommonService.queryGroupByUserCode(currUser);
							if(allotCommon!=null){
								userName=allotCommon.getUserName();
								if(userName!=null){
									biz.setUserName(userName);
								}
							}
						}
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	/*************************
	 *@describe:集体电核
	 *@param ctx
	 *@throws CoreException
	 *@throws ParseException 
	 *@author 王德彬
	 *@date:2017-03-24
	 *************************/
	public void saveTeamPhoneRemark(Context ctx) throws CoreException, ParseException{
		logger.info("TeamPhoneAction.saveTeamPhoneRemark Enter");
		Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		linkedHashMap = ctx.getData("jsondata");
		Gson gson = new Gson();
		//所有参加集体电核的件、已经主卡的套卡注纪内容一样
		String userCode=(String)ctx.getData("userCode")==null?"":(String)ctx.getData("userCode");
		String ydjFlag = (String)ctx.getData("ydjFlag")==null?"":(String)ctx.getData("ydjFlag");
		String bigMemo=ctx.getData("bigMemo")==null?"":(String)ctx.getData("bigMemo");
		String telCheckStatus=ctx.getData("telCheckStatus")==null?"":(String)ctx.getData("telCheckStatus");
		logger.info("TeamPhoneAction.saveTeamPhoneRemark 电核情况为"+telCheckStatus);
		//集体电核共用uuId
		String winId=ctx.getData("winId");
		if("NA".equals(telCheckStatus)||"NC".equals(telCheckStatus)||"关机".equals(telCheckStatus)
				||"".equals(telCheckStatus)||"停机".equals(telCheckStatus)||"无效电话".equals(telCheckStatus)
				||"空号".equals(telCheckStatus)||"其它".equals(telCheckStatus)||"宅电OK".equals(telCheckStatus)
				||"提供其他电话".equals(telCheckStatus)||("--请选择--".equals(telCheckStatus))
				){
			telCheckStatus="3";
		}else{
			telCheckStatus="2";
		}
		Map<String,Object> map = ctx.getDataMap();
		List<String> tem=(List<String>) map.get("list");
		logger.info("TeamPhoneAction.saveTeamPhoneRemark 申请件集合为"+tem.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		List<TelcheckAddnote> list=new ArrayList<TelcheckAddnote>();
		int num=0;
		//插入时候  产生共用uuid  talId
		String talId=UUIDGenerator.getUUID();
		List<String> ydjList=new ArrayList<String>();
		int count=tem.size();
		String matchingCardFlag=null;
			while(count>0){
				String appid=tem.get(count-1).trim();
				if("1".equals(ydjFlag)){
					//易达金matchingCardFlag查询  
					matchingCardFlag=teamPhoneService.selectMatchingCardFlag(appid);
					if(!"0".equals(matchingCardFlag)&&appid.endsWith("2")){
						TelcheckAddnote taokaNote = new TelcheckAddnote();
						String taokaId = appid.substring(0,15)+"1";
						taokaNote.setAppId(taokaId);
						taokaNote.setAutoId(UUIDGenerator.getUUID());
						taokaNote.setBigMemo(bigMemo.trim());
						taokaNote.setCrtUser(userCode);
						taokaNote.setCrtDate(nowDate);
						taokaNote.setMemo(StringUtils.trimToEmpty(linkedHashMap.get("memo")).trim());
						taokaNote.setNoteObject(StringUtils.trimToEmpty(linkedHashMap.get("noteObject")));
						taokaNote.setTeamTelCheckStatus(telCheckStatus);
						taokaNote.setTelcheckResult(StringUtils.trimToEmpty(linkedHashMap.get("telcheckReuslt")));
						taokaNote.setTelNo(StringUtils.trimToEmpty(linkedHashMap.get("telNo")));
						taokaNote.setTelSource(StringUtils.trimToEmpty(linkedHashMap.get("telSource")));
						taokaNote.setTelType(StringUtils.trimToEmpty(linkedHashMap.get("telType")));
						taokaNote.setTypeFlag("4");
						taokaNote.setWinId(winId);
						taokaNote.setTalId(talId);
						list.add(taokaNote);
						if(tem.contains(taokaId)){//两者都有
							ydjList.add(taokaId);
							count--;
						}
					}else if(!"0".equals(matchingCardFlag)&&appid.endsWith("1")){
						TelcheckAddnote zhukaNote=new TelcheckAddnote();
						String zhukaId=appid.substring(0,15)+"2";
						zhukaNote.setAppId(zhukaId);
						zhukaNote.setAutoId(UUIDGenerator.getUUID());
						zhukaNote.setBigMemo(bigMemo.trim());
						zhukaNote.setCrtUser(userCode);
						zhukaNote.setCrtDate(nowDate);
						zhukaNote.setMemo(StringUtils.trimToEmpty(linkedHashMap.get("memo")).trim());
						zhukaNote.setNoteObject(StringUtils.trimToEmpty(linkedHashMap.get("noteObject")));
						zhukaNote.setTeamTelCheckStatus(telCheckStatus);
						zhukaNote.setTelcheckResult(StringUtils.trimToEmpty(linkedHashMap.get("telcheckReuslt")));
						zhukaNote.setTelNo(StringUtils.trimToEmpty(linkedHashMap.get("telNo")));
						zhukaNote.setTelSource(StringUtils.trimToEmpty(linkedHashMap.get("telSource")));
						zhukaNote.setTelType(StringUtils.trimToEmpty(linkedHashMap.get("telType")));
						zhukaNote.setTypeFlag("4");
						zhukaNote.setWinId(winId);
						zhukaNote.setTalId(talId);
						list.add(zhukaNote);
						if(tem.contains(zhukaId)){//两者都有
							ydjList.add(zhukaId);
							count--;
						}
					}
				}
					TelcheckAddnote zhukaNote=new TelcheckAddnote();
					zhukaNote.setAppId(appid.trim());
					zhukaNote.setAutoId(UUIDGenerator.getUUID());
					zhukaNote.setBigMemo(bigMemo.trim());
					zhukaNote.setCrtUser(userCode);
					zhukaNote.setCrtDate(nowDate);
					zhukaNote.setMemo(StringUtils.trimToEmpty(linkedHashMap.get("memo")).trim());
					zhukaNote.setNoteObject(StringUtils.trimToEmpty(linkedHashMap.get("noteObject")));
					zhukaNote.setTeamTelCheckStatus(telCheckStatus);
					zhukaNote.setTelcheckResult(StringUtils.trimToEmpty(linkedHashMap.get("telcheckReuslt")));
					zhukaNote.setTelNo(StringUtils.trimToEmpty(linkedHashMap.get("telNo")));
					zhukaNote.setTelSource(StringUtils.trimToEmpty(linkedHashMap.get("telSource")));
					zhukaNote.setTelType(StringUtils.trimToEmpty(linkedHashMap.get("telType")));
					zhukaNote.setTypeFlag("4");
					zhukaNote.setWinId(winId);
					zhukaNote.setTalId(talId);
					list.add(zhukaNote);
					ydjList.add(appid);
					tem.removeAll(ydjList);
					count--;
			}
		if(list.size()>0&&list!=null){
			num=teamPhoneService.saveTeamPhoneAddnote(list); 
			if (num > 0) {
				logger.info("征信集体电核注纪【添加】成功");
				ctx.setData("isSuccess", true);
				ctx.setData("nowDate",sdf.format(nowDate));
			} else {
				logger.info("征信集体电核注纪【添加】失败");
				ctx.setData("isSuccess", false);
				ctx.setData("exMsg","集体电核异常");
			}
			logger.info("TeamPhoneAction.saveTeamPhoneRemark Out");
		}
	}
	public void findTeamPhone(Context ctx) throws CoreException, ParseException{
		String winId=(String)ctx.getData("winId");
		Map<String,Object> map=new HashMap<String,Object>();
		int count=0;
		map.put("winId", winId);
		count=teamPhoneService.queryCountByWinId(map);
		List<TelcheckAddnote> appList=new ArrayList<TelcheckAddnote>();
		List<TelcheckAddnote> appList2=new ArrayList<TelcheckAddnote>();
		if(count>0){
			appList= teamPhoneService.queryTeamPhoneByWinId(map);
			String crtUser="";
			String crtUserName="";
			for(TelcheckAddnote tel:appList){
				crtUser=tel.getCrtUser()==null?"":tel.getCrtUser();
				AllotCommon ac=new AllotCommon();
				if(!"".equals(crtUser)){
					ac=allotCommonService.queryGroupByUserCode(crtUser);
					if(ac!=null){
						crtUserName=ac.getUserName()==null?"":ac.getUserName();
						tel.setCrtUserName(crtUserName);
					}
				}
				appList2.add(tel);
			}
		}
		Map<String, Object> appMap=new HashMap<String, Object>();
		appMap.put("rows", appList2);
		ctx.setDataMap(appMap);
	}
	public void updateTeamPhoneByTabId(Context ctx) throws CoreException, ParseException{
			String bigMemo=(String)ctx.getData("bigMemo")==null?"":(String)ctx.getData("bigMemo");
			String winId=(String)ctx.getData("winId")==null?"":(String)ctx.getData("winId");
			String tabId=(String)ctx.getData("tabId");
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("talId", tabId);
			map.put("winId", winId);
			map.put("bigMemo", bigMemo);
			int count=0;
			int result=10000;
			count=teamPhoneService.queryCountByTabId(map);
			if(count>0){
				result= teamPhoneService.updateTeamPhoneByTabId(map);
			}
			if(result==10000){
				ctx.setData("isSuccess", false);
				ctx.setData("exMsg", "注记更新失败");
			}else{
				ctx.setData("isSuccess", true);
			}
		}
	/**
	* @Description: 删除
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void delTeamPhone(Context context){
		logger.info("TeamPhoneAction.delTeamPhone Enter");
		logger.info(context.toString());
		String id=(String)context.getData("id")==null?"":(String)context.getData("id");
		Map<String,Object> delMap=new HashMap<String,Object>();
		String tabId="";
		if("1".equals(id)){
			tabId=(String)context.getData("tabId")==null?"":(String)context.getData("tabId");
			delMap.put("tabId", tabId);
		}
		String winId=(String)context.getData("winId")==null?"":(String)context.getData("winId");
		delMap.put("winId", winId);
		int result=99999;
		try {
			if("1".equals(id)){
				result=teamPhoneService.deleteByTabId(delMap);
			}else if("2".equals(id)){
				result=teamPhoneService.deleteByWinId(delMap);
			}
		} catch (CoreException e) {
			context.setData("exMsg",e.getMessage());
			logger.error("TeamPhoneAction.delTeamPhone occur error"+e);
		}
		if(result==99999){
			context.setData("isSuccess",false);
		}else{
			context.setData("isSuccess",true);
		}
		logger.info(context.toString());
		logger.info("TeamPhoneAction.delTeamPhone Out");
	}
	public void findNoteByTime(Context ctx) throws CoreException, ParseException{
		
		String d =ctx.getData("date").toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(d);
		
		List<TelcheckAddnote> noteList= teamPhoneService.queryNoteByTime(date);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		
		
		dataMap.put("rows", noteList);
		ctx.setDataMap(dataMap);
	}
	
	
	/**
	 *@Title:findPhoneSourceData
	 *@Description:获取电话来源参数表信息
	 *@param ctx
	 *@author: gaohui
	 *@Date:2017年4月18日下午7:53:00
	 */
	public void findPhoneSourceData(Context ctx){
		Map<String,Object> paramMap=ctx.getDataMap();
		paramMap.put("status", "1");//是否启用标识
		List<Map<String,Object>>htmlListMap=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>listMap= teamPhoneService.findPhoneSourceData(paramMap);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("dictDetailCode", "");
		map.put("dictDetailName", "--请选择--");
		htmlListMap.add(map);
		if(!listMap.isEmpty()){
			htmlListMap.addAll(listMap);
		}
		ctx.setData("listMap", htmlListMap);
	}
	/**
	 *@Title:findPhoneTypeData
	 *@Description:获取电话类型   电话号码参数表
	 *@param ctx
	 *@author: gaohui
	 *@Date:2017年4月21日上午10:10:25
	 */
	public void findPhoneTypeData(Context ctx){
		Map<String,Object> paramMap=ctx.getDataMap();
		paramMap.put("status", "1");//是否启用标识
		List<Map<String,Object>>htmlListMap=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>listMap= teamPhoneService.findPhoneTypeData(paramMap);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("dictDetailCode", "");
		map.put("dictDetailName", "--请选择--");
		htmlListMap.add(map);
		if(!listMap.isEmpty()){
			htmlListMap.addAll(listMap);
		}
		ctx.setData("listMap", htmlListMap);
	}
	/**
	 *@Title:findPhoneNoteObjectData
	 *@Description:获取 照会对象  照会对象参数表
	 *@param ctx
	 *@author: gaohui
	 *@Date:2017年4月21日上午10:12:58
	 */
	public void findPhoneNoteObjectData(Context ctx){
		Map<String,Object> paramMap=ctx.getDataMap();
		paramMap.put("status", "1");//是否启用标识
		List<Map<String,Object>>htmlListMap=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>listMap= teamPhoneService.findPhoneNoteObjectData(paramMap);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("dictDetailCode", "");
		map.put("dictDetailName", "--请选择--");
		htmlListMap.add(map);
		if(!listMap.isEmpty()){
			htmlListMap.addAll(listMap);
		}
		ctx.setData("listMap", htmlListMap);
	}
	/**
	 *@Title:findPhoneCheckResultData
	 *@Description:获取 电核结论  电核结论参数表
	 *@param ctx
	 *@author: gaohui
	 *@Date:2017年4月21日上午10:14:00
	 */
	public void findPhoneCheckResultData(Context ctx){
		Map<String,Object> paramMap=ctx.getDataMap();
		paramMap.put("status", "1");//是否启用标识
		List<Map<String,Object>>htmlListMap=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>>listMap= teamPhoneService.findPhoneCheckResultData(paramMap);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("dictDetailCode", "");
		map.put("dictDetailName", "--请选择--");
		htmlListMap.add(map);
		if(!listMap.isEmpty()){
			htmlListMap.addAll(listMap);
		}
		ctx.setData("listMap", htmlListMap);
	}
	/**
	 *@Title:findPhoneSourceTypeComboboxData
	 *@Description:返回 电话类型、 照会对象等四个下拉框的内容
	 *@param ctx
	 *@author: gaohui
	 *@Date:2017年4月21日下午6:22:39
	 */
	public void findPhoneSourceTypeComboboxData(Context ctx){
		Map<String,Object> paramMap=ctx.getDataMap();
		String acctType = (String) ctx.getData("acctType");
		paramMap.put("acctType", acctType);
		paramMap.put("acctType1", acctType);
		paramMap.put("status", "1");//是否启用标识
		Map<String,Object>listMap= teamPhoneService.findPhoneSourceTypeComboboxData(paramMap);
		ctx.setData("listMap", listMap);
	}
	
}
