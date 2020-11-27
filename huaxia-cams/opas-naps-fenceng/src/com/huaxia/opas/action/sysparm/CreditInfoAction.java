package com.huaxia.opas.action.sysparm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.sysparm.CreditInfoService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 名单库管理_标准卡征信信息名单action层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-16
 * 
 * @version 1.0
 */
public class CreditInfoAction implements Action {
	
	@SuppressWarnings("unused")
	private static Logger logger=LoggerFactory.getLogger(CreditInfoAction.class);
	
	@Resource(name="creditInfoService")
	private CreditInfoService creditInfoService;
	@Resource(name = "apUserService")
	private ApUserService  apUserService;
	
	/**
	 * 分页查询标准卡征信信息名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	public void queryCreditInfoHistoryList(Context ctx)throws CoreException{
		CreditWhiteList creditWhiteList = getCreditInfo(ctx,"autoId");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?1:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?10:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = creditInfoService.queryCreditInfoHistoryList(creditWhiteList, curPage, pageNum);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 分页查询标准卡征信信息名单实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	public void queryCreditInfoList(Context ctx) throws CoreException{
		CreditWhiteList creditWhiteList = getCreditInfo(ctx, "createTime");
		creditWhiteList.setOperator((String)ctx.getDataMap().get("operator"));
		if(creditWhiteList.getAppId()!=null&&!"".equals(creditWhiteList.getAppId())){
			creditWhiteList.setAppId(creditWhiteList.getAppId().toUpperCase());
		}
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = creditInfoService.queryCreditInfoList(creditWhiteList, curPage, pageNum);
		ctx.setDataMap(dataMap);	
	}

	/**
	 * 新增标准卡征信信息名单实例
	 * 
	 * @author luzhen.ou
	 * */
	public void insertCreditInfo(Context ctx) throws CoreException{
		try{
			CreditWhiteList creditWhiteList = getCreditInfo(ctx);
			checkData(creditWhiteList);
			creditWhiteList.setAutoId(SequenceUtil.getUUID());
			creditInfoService.insertCreditInfo(creditWhiteList);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}
	
	/**
	 * 修改标准卡征信信息名单实例
	 *  
	 * @author luzhen.ou
	 */
	public void updateCreditInfo(Context ctx) throws CoreException{
		try{
			CreditWhiteList creditWhiteList = getCreditInfo(ctx);
			checkData(creditWhiteList);
			creditWhiteList.setOpretionId(SequenceUtil.getUUID());
			creditInfoService.updateCreditInfo(creditWhiteList);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}	

	/**
	 * 删除标准卡征信信息名单实例,通过主键集合ids
	 *  
	 * @author luzhen.ou
	 */	
	public void deleteCreditInfo(Context ctx) throws CoreException{
		try {
			CreditWhiteList creditWhiteList = getCreditInfo(ctx, "ids");
			creditInfoService.deleteCreditInfo(creditWhiteList);
			ctx.setData("isSuccess",true);
		} catch (CoreException e) {
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}

	/**
	 * 批量修改标准卡征信信息名单实例启用状态
	 *  
	 * @author luzhen.ou
	 */	
	public void updateCreditInfoActive(Context ctx) throws CoreException{
		try {
			CreditWhiteList creditWhiteList = getCreditInfo(ctx,"ids","currStatus");
			creditInfoService.updateCreditInfoActive(creditWhiteList);
			ctx.setData("isSuccess",true);
		} catch (CoreException e) {
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}	

	/*
	*//**
	 * 批量导入标准卡征信信息名单实例Excel
	 * 
	 * @author luzhen.ou
	 * *//*
	public void insertCreditInfoFileUpload (Context ctx) throws CoreException{
		try {
			InputStream in = null;
			Map<String,Object> map = ctx.getDataMap();
			String fileName = (String) map.get("fileName");
			in = new FileInputStream(fileName);
			ExcelReader excelReader = new ExcelReader();
			List<CreditWhiteList> list = readExcel2(in,ctx);
			creditInfoService.insertCreditInfoList(list);
			ctx.setData("isSuccess", true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ctx.setData("isSuccess", false);
		}
	}
	*/
	
	public void listCredit(Context ctx){
		Map<String, Object> dataMap = ctx.getDataMap();
		String userCode = ctx.getData("userCode");
		try {
			if(userCode !=null && !"".equals(userCode)){
				ApUser apUser = apUserService.queryApUserByUserCode(userCode);
				String userName = apUser.getUserName();
				ctx.setData("isSuccess", true);
				ctx.setData("userName", userName);
			}
		} catch (CoreException e) {
			e.printStackTrace();
			ctx.setData("isSuccess", false);
		}
	}
	
	/**
	 * 标准卡征信信息名单实例新增和修改数据校验
	 *  
	 * @author luzhen.ou
	 */	
	public void checkData(CreditWhiteList creditWhiteList) throws CoreException{
		
		String txt= "[^_][\\u4e00-\\u9fa5[^\\x00-\\xff][\\w]]*";	
		String code = "[a-zA-Z0-9]{1,20}";
		String tel1 ="\\d{3}-\\d{8}";
		String tel2 ="\\d{4}-\\d{7}";
		
		String tmp = creditWhiteList.getAppId();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 16) {
				throw new CoreException("申请件编号长度不能超过16个字符!");
			}
//			if (!tmp.trim().matches(code) ) {
//				throw new CoreException("申请件编号应为英文、数字组成!");
//			}
		}	
		
		tmp = creditWhiteList.getCompanyName();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 50) {  
				throw new CoreException("姓名长度不能超过50个字符!");
			}
//			if (!tmp.trim().matches(txt) ) {
//				throw new CoreException("姓名应为中英文、数字、下滑线组成，首字母不能为下滑线!");
//			}
		}	
		
		tmp = creditWhiteList.getCompanyTel();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() >13) {
				throw new CoreException("单位电话长度不能超过13个字符!");
			}
		}
		
		tmp = creditWhiteList.getCompanyAddr();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 100) {
				throw new CoreException("单位地址长度不能超过100个字符!");
			}
		}			
		
		tmp = creditWhiteList.getLegaler();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 32) {
				throw new CoreException("法人长度不能超过32个字符!");
			}
//			if (!tmp.trim().matches(txt) ) {
//				throw new CoreException("法人应为中英文、数字、下滑线组成，首字母不能为下滑线!");
//			}
		}			
		
		tmp = creditWhiteList.getRegisterFund();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 32) {
				throw new CoreException("注册资金长度不能超过32个字符!");
			}
//			if (!tmp.trim().matches(txt) ) {
//				throw new CoreException("注册资金应为中英文、数字、下滑线组成，首字母不能为下滑线!");
//			}
		}			
		
		tmp = creditWhiteList.getRegisterAddr();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 100) {
				throw new CoreException("注册地址长度不能超过100个字符!");
			}
		}		
		
		tmp = creditWhiteList.getManageStatus();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 30) {
				throw new CoreException("经营状态长度不能超过30个字符!");
			}
//			if (!tmp.trim().matches(txt) ) {
//				throw new CoreException("经营状态应为中英文、数字、下滑线组成，首字母不能为下滑线!");
//			}
		}	
		
		tmp = creditWhiteList.getPartnerInfo();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 100) {
				throw new CoreException("股东信息长度不能超过100个字符!");
			}
//			if (!tmp.trim().matches(txt) ) {
//				throw new CoreException("股东信息应为中英文、数字、下滑线组成，首字母不能为下滑线!");
//			}
		}			
		
		tmp = creditWhiteList.getManageExceptionInfo();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 200) {
				throw new CoreException("经营异常信息长度不能超过200个字符!");
			}
		}			
		
	}
	
	/**
	 * 抽取前台页面获取数据的公共方法,并对传入数据进行后台校验
	 *  
	 * @author luzhen.ou
	 */	   
	public CreditWhiteList getCreditInfo(Context ctx,String ...strings) throws CoreException{
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
		Date createTimeDate = null;
		if (list.contains("createTime")) {
			String createTimeString = (String)map.get("createTime");
			if(null!=createTimeString && !createTimeString.trim().isEmpty()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					createTimeDate  = sdf.parse(createTimeString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			map.remove("createTime");
		}
		Gson gson = new Gson();
		CreditWhiteList creditWhiteList = gson.fromJson(gson.toJson(map), CreditWhiteList.class);
		
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		creditWhiteList.setOperator(operator);
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择需要操作的行ids");
					throw e;
				}
				creditWhiteList.setIds(ids);
				break;
				
			case "currStatus":
				String currStatus = creditWhiteList.getCurrStatus();
				if( null == currStatus || (!currStatus.trim().equals("1") && !currStatus.trim().equals("0"))){
					CoreException e = new CoreException("启用状态值currStatus非法");
					throw e;
				}
				break;
				
			case "autoId":
				String autoId = creditWhiteList.getAutoId();
				if( null == autoId || autoId.trim().isEmpty()){
					CoreException e = new CoreException("查询历史操作记录的autoId值为空");
					throw e;
				}
				break;
				
			case "createTime":
			    creditWhiteList.setCreateTime(createTimeDate);
				break;
				
			default:
				break;
			}
		}
		return creditWhiteList;
	}
		
}
