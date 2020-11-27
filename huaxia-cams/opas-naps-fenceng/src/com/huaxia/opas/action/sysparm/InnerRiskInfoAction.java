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
import com.huaxia.opas.domain.sysparm.InnerRiskInfo;
import com.huaxia.opas.service.sysparm.InnerRiskInfoService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 名单库管理--内部风险信息名单实体类action层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-16
 * 
 * @version 1.0
 */
public class InnerRiskInfoAction implements Action {
	
	@SuppressWarnings("unused")
	private static Logger logger=LoggerFactory.getLogger(InnerRiskInfoAction.class);
	
	@Resource(name="innerRiskInfoService")
	private InnerRiskInfoService innerRiskInfoService;
	
	/**
	 * 分页查询内部风险信息名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	public void queryInnerRiskInfoHistoryList(Context ctx)throws CoreException{
		InnerRiskInfo innerRiskInfo = getInnerRiskInfo(ctx,"autoId");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = innerRiskInfoService.queryInnerRiskInfoHistoryList(innerRiskInfo, curPage, pageNum);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 分页查询内部风险信息名单实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	public void queryInnerRiskInfoList(Context ctx) throws CoreException{
		InnerRiskInfo innerRiskInfo = getInnerRiskInfo(ctx, "createTime", "invalidTime");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = innerRiskInfoService.queryInnerRiskInfoList(innerRiskInfo, curPage, pageNum);
		ctx.setDataMap(dataMap);	
	}

	/**
	 * 新增内部风险信息名单实例
	 * 
	 * @author luzhen.ou
	 * */
	public void insertInnerRiskInfo(Context ctx) throws CoreException{
		try{
			InnerRiskInfo innerRiskInfo = getInnerRiskInfo(ctx, "invalidTime");
			checkData(innerRiskInfo);
			innerRiskInfo.setAutoId(SequenceUtil.getUUID());
			innerRiskInfoService.insertInnerRiskInfo(innerRiskInfo);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}
	
	/**
	 * 修改内部风险信息名单实例
	 *  
	 * @author luzhen.ou
	 */
	public void updateInnerRiskInfo(Context ctx) throws CoreException{
		try{
			InnerRiskInfo innerRiskInfo = getInnerRiskInfo(ctx, "createTime","invalidTime");
			checkData(innerRiskInfo);
			innerRiskInfo.setOpretionId(SequenceUtil.getUUID());
			innerRiskInfoService.updateInnerRiskInfo(innerRiskInfo);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}	

	/**
	 * 删除内部风险信息名单实例,通过主键集合ids
	 *  
	 * @author luzhen.ou
	 */	
	public void deleteInnerRiskInfo(Context ctx) throws CoreException{
		try {
			InnerRiskInfo innerRiskInfo = getInnerRiskInfo(ctx,"ids");
			innerRiskInfoService.deleteInnerRiskInfo(innerRiskInfo);
			ctx.setData("isSuccess",true);
		} catch (CoreException e) {
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}

	/**
	 * 批量修改内部风险信息名单实例启用状态
	 *  
	 * @author luzhen.ou
	 */	
	public void updateInnerRiskInfoActive(Context ctx) throws CoreException{
		try {
			InnerRiskInfo innerRiskInfo = getInnerRiskInfo(ctx,"ids","currStatus");
			innerRiskInfoService.updateInnerRiskInfoActive(innerRiskInfo);
			ctx.setData("isSuccess",true);
		} catch (CoreException e) {
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;			
		}
	}	

	/*
	*//**
	 * 批量导入内部风险信息名单实例Excel
	 * 
	 * @author luzhen.ou
	 * *//*
	public void insertInnerRiskInfoFileUpload (Context ctx) throws CoreException{
		try {
			InputStream in = null;
			Map<String,Object> map = ctx.getDataMap();
			String fileName = (String) map.get("fileName");
			in = new FileInputStream(fileName);
			ExcelReader excelReader = new ExcelReader();
			List<InnerRiskInfo> list = readExcel2(in,ctx);
			innerRiskInfoService.insertInnerRiskInfoList(list);
			ctx.setData("isSuccess", true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ctx.setData("isSuccess", false);
		}
	}
	*/

	
	/**
	 * 身份类风险名单实例新增和修改数据校验
	 *  
	 * @author luzhen.ou
	 */	
	public void checkData(InnerRiskInfo innerRiskInfo) throws CoreException{
		
		String txt= "[^_][\\u4e00-\\u9fa5[^\\x00-\\xff][\\w]]*";	
		String code = "[a-zA-Z0-9]{1,20}";
		String tel1 ="\\d{3}-\\d{8}";
		String tel2 ="\\d{4}-\\d{7}";
		
		String tmp = innerRiskInfo.getCompanyName();
		if ( null != tmp){
			if(tmp.trim().isEmpty()){
				innerRiskInfo.setCompanyName(null);
			}else{
				if(tmp.trim().length() > 50){
					throw new CoreException("单位名称长度不能超过50个字符!");
				}
				if(!tmp.trim().matches(txt)){
					throw new CoreException("单位名称应为中英文、数字、下滑线组成，首字母不能为下滑线!");				
				}
			}
		}
		
//		tmp = innerRiskInfo.getCompanyTel();
//		if ( null != tmp) {
//			if (tmp.trim().isEmpty()) {
//				innerRiskInfo.setCompanyTel(null);
//			}else{
//				if(tmp.trim().length() != 12){
//					throw new CoreException("单位电话长度应为12个字符!");
//				}
//				if(!tmp.trim().matches(tel1) && !tmp.trim().matches(tel2)){
//					throw new CoreException("单位电话应为xxx-xxxxxxxx或xxxx-xxxxxxx格式");
//				}
//			}
//		}		
		
		tmp = innerRiskInfo.getCertifiNo();
		if ( null != tmp) {
			if(tmp.trim().isEmpty()){
				innerRiskInfo.setCertifiNo(null);
			}else{
				if(tmp.trim().length() > 20){
					throw new CoreException("证件号码长度不能超过20个字符!");
				}
				if(!tmp.trim().matches(code)){
					throw new CoreException("证件号码由20位数字或字母组成!");
				}
			}
		}

		
		tmp = innerRiskInfo.getHouseHold();
		if ( null != tmp) {
			if(tmp.trim().isEmpty()){
				innerRiskInfo.setHouseHold(null);
			}else{
				if (tmp.trim().length() > 20) {
					throw new CoreException("户籍长度不能超过20个字符!");
				}
				if (!tmp.trim().matches(txt)) {
					throw new CoreException("户籍应为中英文、数字、下滑线组成，首字母不能为下滑线!");
				}		
			}
		}
		
		tmp = innerRiskInfo.getSpreadOrg();
		if (null != tmp) {
			if(tmp.trim().isEmpty()){
				innerRiskInfo.setSpreadOrg(null);
			}else{
				if(tmp.trim().length() > 32){
					throw new CoreException("推广机构长度不能超过32个字符!");
				}
				if(!tmp.trim().matches(txt)){
					throw new CoreException("推广机构应为中英文、数字、下滑线组成，首字母不能为下滑线!");
				}
			}
		}
		
		tmp = innerRiskInfo.getSpreadPer();
		if (null != tmp) {
			 if(tmp.trim().isEmpty()){
				 innerRiskInfo.setSpreadPer(null);
			 }else{
				 if (tmp.trim().length() > 32) {
					 throw new CoreException("推广人员长度不能超过32个字符!");
				 }
				 if (!tmp.trim().matches(txt)) {
					 throw new CoreException("推广人员应为中英文、数字、下滑线组成，首字母不能为下滑线!");
				 }
			 }
		}		
		
		tmp = innerRiskInfo.getSpreadNo();
		if ( null != tmp) {
			if(tmp.trim().isEmpty()){
				innerRiskInfo.setSpreadNo(null);
			}else{
				if(tmp.trim().length() > 32){
					throw new CoreException("推广人编号长度不能超过32个字符!");
				}
				if(!tmp.trim().matches(code)){
					throw new CoreException("推广人编号应为中英文、数字、下滑线组成，首字母不能为下滑线!");
				}
			}
		}
		
		tmp = innerRiskInfo.getCurrStatus();
		if (!"0".equals(tmp) && !"1".equals(tmp)) {
			throw new CoreException("启用状态格式不正确!");
		}
		
		tmp = innerRiskInfo.getContentDesc();
		if ( null != tmp) {
			if(tmp.trim().isEmpty()){
				innerRiskInfo.setContentDesc(null);
			}else{
				if(tmp.trim().length() > 50){
					throw new CoreException("推内容描述长度不能超过50个字符!");
				}
			}
		}
	
	}
		
	/**
	 * 抽取前台页面获取数据的公共方法,并对传入数据进行后台校验
	 *  
	 * @author luzhen.ou
	 */	   
	public InnerRiskInfo getInnerRiskInfo(Context ctx,String ...strings) throws CoreException{
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
		Date invalidTimeDate = null;
		Date createTimeDate = null;
 		if (list.contains("invalidTime")) {
			String invalidTimeString = (String)map.get("invalidTime");
			if(null!=invalidTimeString && !invalidTimeString.trim().isEmpty()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					invalidTimeDate = sdf.parse(invalidTimeString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		    map.remove("invalidTime");
		}
 		if (list.contains("createTime")) {
			String createTimeString = (String)map.get("createTime");
			if(null!=createTimeString && !createTimeString.trim().isEmpty()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					createTimeDate  = sdf.parse(createTimeString);
					map.put("createTime", createTimeDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			map.remove("createTime");
 		}
 		
		Gson gson = new Gson();
		InnerRiskInfo innerRiskInfo = gson.fromJson(gson.toJson(map), InnerRiskInfo.class);
		
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		innerRiskInfo.setOperator(operator);
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择需要操作的行ids");
					throw e;
				}
				innerRiskInfo.setIds(ids);
				break;
				
			case "currStatus":
				String currStatus = innerRiskInfo.getCurrStatus();
				if( null == currStatus || (!currStatus.trim().equals("1") && !currStatus.trim().equals("0"))){
					CoreException e = new CoreException("启用状态值currStatus非法");
					throw e;
				}
				break;
				
			case "autoId":
				String autoId = innerRiskInfo.getAutoId();
				if( null == autoId || autoId.trim().isEmpty()){
					CoreException e = new CoreException("查询历史操作记录的autoId值为空");
					throw e;
				}
				break;
				
			case "invalidTime":
			    innerRiskInfo.setInvalidTime(invalidTimeDate);
				break;
				
			case "createTime":
			    innerRiskInfo.setCreateTime(createTimeDate);
				break;
				
			default:
				break;
			}
		}
		return innerRiskInfo;
	}
}
