package com.huaxia.opas.action.collect;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.collect.PatchboltSpyInfo;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.FicoYdjBlaze;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.service.collect.PatchboltSpyInfoService;
import com.huaxia.opas.service.decision.SysDecisionYdjService;
import com.huaxia.opas.util.SequenceUtil;

public class PatchboltSpyInfoAction implements Action {

	private static Logger logger = LoggerFactory
			.getLogger(PatchboltSpyInfoAction.class);

	@Resource(name = "patchboltSpyInfoService")
	private PatchboltSpyInfoService patchboltSpyInfoService;
	@Resource(name = "sysDecisionYdjService")
	private SysDecisionYdjService sysDecisionYdjService;
	public void addPatchboltSpyInfo(Context ctx) throws CoreException {
		String msg = "操作失败，请重试或联系管理员";
		Gson json = new Gson();
		PatchboltSpyInfo patchboltSpyInfo = json.fromJson(json.toJson(ctx.getDataMap()), PatchboltSpyInfo.class);
		String appId = patchboltSpyInfo.getAppId();
		String privateEntprseCpaperType=patchboltSpyInfo.getPrivateEntprseCpaperType();
		if(privateEntprseCpaperType!=null&&!"".equals(privateEntprseCpaperType)){
			patchboltSpyInfo.setPrivateEntprseClientPaper("01");
		}else{
			patchboltSpyInfo.setPrivateEntprseClientPaper("02");
		}
		patchboltSpyInfo.setCrtDate(new Date());
		try {
			PatchboltSpyInfo patchboltSpy = patchboltSpyInfoService.selectPatchboltSpyInfo(appId);
			if (patchboltSpy == null) {
				patchboltSpyInfo.setAutoId(SequenceUtil.getUUID());
				patchboltSpyInfoService.addPatchboltSpyInfo(patchboltSpyInfo);
			} else {
				patchboltSpyInfoService.updatePatchboltSpyInfo(patchboltSpyInfo);
			}
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			e.printStackTrace();
		}
		ctx.setData("isSuccess", true);
	}

	@SuppressWarnings("unchecked")
	public void listPatchboltSpyInfo(Context ctx) {
		List<PatchboltSpyInfo> list = new ArrayList<PatchboltSpyInfo>();
		Map map=ctx.getDataMap();
		String appId=(String) map.get("appId");
		map.put("appId",appId);
		PatchboltSpyInfo patchboltSpyInfo=new PatchboltSpyInfo();
		FicoYdjBlaze ficoYdjBlaze=new FicoYdjBlaze();
		BizInpApplC1 appl = null;
		try {
			patchboltSpyInfo=patchboltSpyInfoService.selectPatchboltSpyInfo(appId);
			appl = sysDecisionYdjService.selectBizInpApplC1ByAppId(appId);
			appl = appl == null ? new BizInpApplC1() : appl;// 避免空指针异常
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			e.printStackTrace();
		}
		String eduProvRst = "";
		if(patchboltSpyInfo !=null && patchboltSpyInfo.getEducationBackgroundCheck()!=null 
				&& !"".equals(patchboltSpyInfo.getEducationBackgroundCheck())){
			eduProvRst = patchboltSpyInfo.getEducationBackgroundCheck();
		}else{
			try {
				ficoYdjBlaze=patchboltSpyInfoService.selectByPrimaryKey(appId);
			} catch (CoreException e) {
				ctx.setData("exMsg", e.getMessage());
				e.printStackTrace();
			}
			eduProvRst = ficoYdjBlaze.getEduProvRst(); 
		}
		String jsonpatchboltSpyInfo=JSON.toJSONString(patchboltSpyInfo);
		//申请书团办编号的后两位*1000=商品金额
		String C4_APBATCH = appl.getC4Apbatch();
		if(C4_APBATCH!=null&&!"".equals(C4_APBATCH)&&C4_APBATCH.length()==10){
			String str = C4_APBATCH.substring(C4_APBATCH.length()-2, C4_APBATCH.length());
			int param = 0;
			try {
				param = Integer.parseInt(str);
			} catch (Exception e) {
				logger.info("团办号最后两位不是数字，故而无法解析");
			}
			ctx.setData("productPrice", new BigDecimal(param).multiply(new BigDecimal(1000)).toString());
		}else{
			ctx.setData("productPrice", "0");
		}
		ctx.setData("eduProvRst", eduProvRst);
		ctx.setData("jsonpatchboltSpyInfo", jsonpatchboltSpyInfo);
		ctx.setData("patchboltSpyInfo", patchboltSpyInfo);
	}
	
	/**
	 * 商品易达金反显
	 * @param ctx
	 */
	public void queryPatchboltSpyInfo(Context ctx) {
		
		Map map=ctx.getDataMap();
		String appId=(String) map.get("appId");
		map.put("appId",appId);
		PatchboltSpyInfo patchboltSpyInfo=new PatchboltSpyInfo();
		
		try {
			patchboltSpyInfo=patchboltSpyInfoService.selectPatchboltSpyInfo(appId);
			
			patchboltSpyInfo = patchboltSpyInfo == null ? new PatchboltSpyInfo() : patchboltSpyInfo;// 避免空指针异常
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			e.printStackTrace();
		}
		
		//申请书团办编号的后两位*1000=商品金额
		
		String jsonpatchboltSpyInfo=JSON.toJSONString(patchboltSpyInfo);
		ctx.setData("jsonpatchboltSpyInfo", jsonpatchboltSpyInfo);
		ctx.setData("patchboltSpyInfo", patchboltSpyInfo);
	}
}
