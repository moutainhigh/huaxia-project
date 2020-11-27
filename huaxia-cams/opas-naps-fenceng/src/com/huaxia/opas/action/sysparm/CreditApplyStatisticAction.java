package com.huaxia.opas.action.sysparm;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.service.sysparm.CreditApplyStatisticService;
import com.huaxia.opas.util.TransDateUtil;

public class CreditApplyStatisticAction implements Action {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CreditApplyStatisticAction.class);

	@Resource(name = "creditApplyStatisticService")
	private CreditApplyStatisticService creditApplyStatisticService;


	// 查询当天的工作量
	public void queryCreditApplyStatistic(Context ctx) throws CoreException, ParseException {
		String userId = (String)ctx.getData("userId");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("startTime", TransDateUtil.String2Date((String)ctx.getData("startTime"), "yyyy-MM-dd"));
		map.put("endTime", TransDateUtil.String2Date((String)ctx.getData("endTime"), "yyyy-MM-dd"));
		map.put("userId", userId);
		//map.put("userId", "lisi");
		int creditApplyCount = creditApplyStatisticService.queryCreditApplyCount(map);
		int seniorApplyCount = creditApplyStatisticService.querySeniorApplyCount(map);
		int finishedCount = creditApplyStatisticService.queryFinishedCount(map);
		ctx.setData("creditApplyCount", creditApplyCount+"");
		ctx.setData("seniorApplyCount", seniorApplyCount+"");
		ctx.setData("finishedCount", finishedCount+"");
	}
	

}
