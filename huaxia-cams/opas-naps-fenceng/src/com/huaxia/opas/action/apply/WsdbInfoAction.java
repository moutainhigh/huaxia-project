package com.huaxia.opas.action.apply;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.input.FicoSdBlaze;
import com.huaxia.opas.service.apply.ApplyInfoService;
import com.huaxia.opas.service.decision.SysDecisionService;
import com.huaxia.opas.service.decision.SysDecisionYdjService;

/**
 * 网申短表页面展示
 * 
 * @author yuanquan
 *
 */
public class WsdbInfoAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(WsdbInfoAction.class);

	@Resource(name = "applyInfoService")
	private ApplyInfoService applyInfoService;
	public ApplyInfoService getApplyInfoService() {
		return applyInfoService;
	}
	@Resource(name = "sysDecisionService")
	private SysDecisionService sysDecisionService;
	@Resource(name = "sysDecisionYdjService")
	private SysDecisionYdjService sysDecisionYdjService;
	/**
	 * 网申短表信息查询
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void queryWsdbInfoList(Context ctx) throws Exception {

		Map dataMap = ctx.getDataMap();
		// 参数
		Map<String, Object> params = new HashMap<String, Object>();
		String appId = dataMap.get("appId").toString();
		if(appId!=null&&!"".equals(appId)){
			appId = appId.trim().toUpperCase();
		}
		String certNo = (String) dataMap.get("certNo");
		if(certNo!=null&&!"".equals(certNo)){
			certNo = certNo.trim().toUpperCase();
		}
		String name = (String) dataMap.get("name");
		if(name!=null&&!"".equals(name)){
			name = name.trim();
		}
		String mobile = (String) dataMap.get("mobile");
		if(mobile!=null&&!"".equals(mobile)){
			mobile = mobile.trim();
		}
		params.put("appId", appId);
		params.put("name", name);
		params.put("certNo",certNo);
		params.put("mobile", mobile);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		int count = 0;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		count = getApplyInfoService().queryWsdbInfoCount(params);
		if (count > 0) {
			list = getApplyInfoService().queryWsdbInfoMap(params, curNum, pageNum);
		}
		
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}


	/**
	 * 申请件流程节点显示
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void queryWsdbNode(Context ctx) throws Exception {

		Map dataMap = ctx.getDataMap();
		// 参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", (String) dataMap.get("appId"));
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		int count = getApplyInfoService().queryWsdbNodeCount(params);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (count > 0) {
			list = getApplyInfoService().selectWsdbNodeList(params, curNum, pageNum);
		}
		// 创建 dataMap1 存储分页数据
		Map<String, Object> dataMap1 = new HashMap<String, Object>();

		dataMap1.put("total", count);
		dataMap1.put("rows", list);
		ctx.setDataMap(dataMap1);

	}
	
	public void queryWsdbDecisionInfoByAppID(Context ctx) throws Exception {
		Map map=ctx.getDataMap();
		String appId=(String) map.get("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		/**主卡决策结果   附属卡决策结果   参考数据OPAS_FICO_SD_BLAZE**/
		FicoSdBlaze sysResultInfo = (FicoSdBlaze)sysDecisionYdjService.selectSystemDecisionByAppId(appId, "2");
		String jsonsysInfo="{\"\":\"\"}";
		if(null!=sysResultInfo){
			sysResultInfo.setAppId(null);  //设置为NULL，因为这5个实体类都含有，生成JSON后在前台LOAD时会有冲突
			jsonsysInfo=JSON.toJSONString(sysResultInfo);
			Date date = sysResultInfo.getCrtTime();
			if(date!=null){
				String ctrStr = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
				ctx.setData("ctrStr", ctrStr);
			}
			Date fqzRstTime = sysResultInfo.getFqzRstTime();
			if(fqzRstTime!=null){
				String fqzRstDate = (new SimpleDateFormat("yyyy-MM-dd")).format(fqzRstTime);
				ctx.setData("fqzRstDate", fqzRstDate);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("主卡决策结果[" + jsonsysInfo + "]");
		}
		//反欺诈
		Map<String, Object> dataMap = sysDecisionYdjService.selectFqzRstDespByAppId(appId);
		/**网申短表客户信息**/
		Map<String, Object> wsdbMap = getApplyInfoService().selectWsdbInfoByAppId(appId);
		ctx.setData("jsonStr", jsonsysInfo.substring(0,jsonsysInfo.length()));
		ctx.setData("dataMap", dataMap);
		ctx.setData("wsdbMap", wsdbMap);
	}
	

}
