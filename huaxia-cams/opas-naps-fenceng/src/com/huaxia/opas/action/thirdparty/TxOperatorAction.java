package com.huaxia.opas.action.thirdparty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.TxOperatorService;

/**
 * 第三方-通讯运营商
 * 
 * @author gaoh
 *
 */
public class TxOperatorAction implements Action {

//	private static Logger logger = LoggerFactory.getLogger(TxOperatorAction.class);

	@Resource(name = "txOperatorService")
	private TxOperatorService txOperatorService;

	public TxOperatorService getTxOperatorService() {
		return txOperatorService;
	}

	public void setTxOperatorService(TxOperatorService txOperatorService) {
		this.txOperatorService = txOperatorService;
	}

	/**
	 *@Title:queryTxOperSummaryInfo
	 *@Description:查询通讯运营商摘要信息
	 *@param context
	 *@author: gaohui
	 *@Date:2018年7月13日上午10:28:01
	 */
	public void queryTxOperSummaryInfo(Context context) {
		String appId =context.getData("appId");
		try {
			 Map<String,String> responseMap=txOperatorService.queryTxOperSummaryInfoByAppId(appId);
			 context.setData("success", true);
			 context.setData("txOperatorSummary", responseMap);
		} catch (Exception e) {
			e.printStackTrace();
			context.setData("success", false);
		}
	}
	
	/**
	 *@Title:queryUnicomAddress
	 *@Description:查询联通地址类信息
	 *@param context
	 *@author: ad
	 */
	public void queryUnicomAddress(Context context) {
		String appId =context.getData("appId");
		try {
			 Map<String,String> unAddressInfo=txOperatorService.queryUnicomAddressInfoByAppId(appId);
			 context.setData("success", true);
			 context.setData("unAddressInfo", unAddressInfo);
		} catch (Exception e) {
			e.printStackTrace();
			context.setData("success", false);
		}
	}
	
	/**
	 *@Title:queryUnicomAddress
	 *@Description:查询联通地址类信息
	 *@param context
	 *@author: ad
	 */
	public void findunicomListMessage(Context context) {
		String appId =context.getData("appId");
		List<Map<String,String>> unicomList = new ArrayList<Map<String,String>>();
		try {
			unicomList =txOperatorService.queryUnicomListMessage(appId);
		    Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("rows", unicomList);
			context.setDataMap(dataMap);
			} catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	/**
	 *@Title:querySfrzBaseData
	 *@Description:可信身份证
	 *@param context
	 *@author: ad
	 */
	public void querySfrzBaseData(Context context) {
		String appId =context.getData("appId");
		try {
			 Map<String,String> sfrzBase=txOperatorService.querySfrzBaseData(appId);
			 context.setData("success", true);
			 context.setData("SfrzBase", sfrzBase);
		} catch (Exception e) {
			e.printStackTrace();
			context.setData("success", false);
		}
	}
	
	
	/**
	 *@Title:queryNbBaseInfo
	 *@Description:宁波公积金
	 *@param context
	 *@author: ad
	 */
	public void queryNbBaseInfo(Context context) {
		String appId =context.getData("appId");
		try {
			 Map<String,String> gjjInfo=txOperatorService.queryNbBaseInfo(appId);
			 context.setData("success", true);
			 context.setData("SfrzBase", gjjInfo);
		} catch (Exception e) {
			e.printStackTrace();
			context.setData("success", false);
		}
	}
}