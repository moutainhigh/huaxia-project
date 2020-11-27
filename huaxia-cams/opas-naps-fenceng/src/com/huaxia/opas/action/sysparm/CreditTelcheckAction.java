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
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.sysparm.CreditTelcheckService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 修复页面显示操作员中文名
 * 
 * @author wangtao
 * 
 * @since 2017-10-10
 * 
 * @version 1.1
 */
public class CreditTelcheckAction implements Action {
	
	@SuppressWarnings("unused")
	private static Logger logger=LoggerFactory.getLogger(CreditTelcheckAction.class);
	
	@Resource(name="creditTelcheckService")
	private CreditTelcheckService creditTelcheckService;

	@Resource(name = "apUserService")
	private ApUserService  apUserService;
	/**
	 * 分页查询征信电话核查白名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	public void queryCreditTelcheckHistoryList(Context ctx)throws CoreException{
		CreditTelcheckList creditTelcheckList = getCreditTelcheck(ctx,"autoId");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = creditTelcheckService.queryCreditTelcheckHistoryList(creditTelcheckList, curPage, pageNum);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 分页查询征信电话核查白名单实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	public void queryCreditTelcheckList(Context ctx) throws CoreException{
		CreditTelcheckList creditTelcheckList = getCreditTelcheck(ctx, "createTime");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = creditTelcheckService.queryCreditTelcheckDomainList(creditTelcheckList, curPage, pageNum);
		ctx.setDataMap(dataMap);	
	}

	/**
	 * 新增征信电话核查白名单实例
	 * 
	 * @author luzhen.ou
	 * */
	public void insertCreditTelcheck(Context ctx) throws CoreException{
		try{
			CreditTelcheckList creditTelcheckList = getCreditTelcheck(ctx);
			checkData(creditTelcheckList);
			creditTelcheckList.setAutoId(SequenceUtil.getUUID());
			creditTelcheckService.insertCreditTelcheck(creditTelcheckList);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}
	
	/**
	 * 修改征信电话核查白名单实例
	 *  
	 * @author luzhen.ou
	 */
	public void updateCreditTelcheck(Context ctx) throws CoreException{
		try{
			CreditTelcheckList creditTelcheckList = getCreditTelcheck(ctx);
			checkData(creditTelcheckList);
			creditTelcheckList.setOpretionId(SequenceUtil.getUUID());
			creditTelcheckService.updateCreditTelcheck(creditTelcheckList);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}	

	/**
	 * 删除征信电话核查白名单实例,通过主键集合ids
	 *  
	 * @author luzhen.ou
	 */	
	public void deleteCreditTelcheck(Context ctx) throws CoreException{
		try {
			CreditTelcheckList creditTelcheckList = getCreditTelcheck(ctx, "ids");
			creditTelcheckService.deleteCreditTelcheck(creditTelcheckList);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}

	/**
	 * 批量修改征信电话核查白名单实例启用状态
	 *  
	 * @author luzhen.ou
	 */	
	public void updateCreditTelcheckActive(Context ctx) throws CoreException{
		try {
			CreditTelcheckList creditTelcheckList = getCreditTelcheck(ctx,"ids","currStatus");
			creditTelcheckService.updateCreditTelcheckActive(creditTelcheckList);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}	

	/*
	*//**
	 * 批量导入征信电话核查白名单实例Excel
	 * 
	 * @author luzhen.ou
	 * *//*
	public void insertCreditTelcheckFileUpload (Context ctx) throws CoreException{
		try {
			InputStream in = null;
			Map<String,Object> map = ctx.getDataMap();
			String fileName = (String) map.get("fileName");
			in = new FileInputStream(fileName);
			ExcelReader excelReader = new ExcelReader();
			List<CreditTelcheckList> list = readExcel2(in,ctx);
			creditTelcheckService.insertCreditTelcheckList(list);
			ctx.setData("isSuccess", true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ctx.setData("isSuccess", false);
		}
	}
	*/

	
	/**
	 * 身征信电话核查白名单实例新增和修改数据校验
	 *  
	 * @author luzhen.ou
	 */	
	public void checkData(CreditTelcheckList creditTelcheckList) throws CoreException{
		
		String txt= "[^_][\\u4e00-\\u9fa5[^\\x00-\\xff][\\w]]*";	
		String code = "[a-zA-Z0-9]{1,16}";
		String tel1 ="\\d{3}-\\d{8}";
		String tel2 ="\\d{4}-\\d{7}";
		
		String tmp = creditTelcheckList.getAppId();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 16) {
				throw new CoreException("申请件编号长度不能超过16个字符!");
			}
			if (!tmp.trim().matches(code) ) {
				throw new CoreException("申请件编号应为英文、数字组成!");
			}
		}
		
		tmp = creditTelcheckList.getCompanyName();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 50) {
				throw new CoreException("单位名称长度不能超过50个字符!");
			}
			if (!tmp.trim().matches(txt) ) {
				throw new CoreException("单位名称应为中英文、数字、下滑线组成，首字母不能为下滑线!");
			}
		}

		tmp = creditTelcheckList.getCompanyTel();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() >13) {
				throw new CoreException("单位电话长度不能超过13个字符!");
			}
		}
		
		
		tmp = creditTelcheckList.getMemo();
		if (tmp != null &&!tmp.trim().isEmpty()) {
			if (tmp.trim().length() > 200) {
				throw new CoreException("备注长度不能超过200个字符!");
			}
		}
		
	}	
	
	/**
	 * 抽取前台页面获取数据的公共方法,并对传入数据进行后台校验
	 *  
	 * @author luzhen.ou
	 */	   
	public CreditTelcheckList getCreditTelcheck(Context ctx,String ...strings) throws CoreException{
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
		Date createTime = null;
		if (list.contains("createTime")) {
			String createDateString = (String)map.get("createTime");
			if(null!=createDateString && !createDateString.trim().isEmpty()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					createTime  = sdf.parse(createDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			map.remove("createTime");
		}
		Gson gson = new Gson();
		CreditTelcheckList creditTelcheckList = gson.fromJson(gson.toJson(map), CreditTelcheckList.class);
		
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		ApUser apUser = apUserService.queryApUserByUserCode(operator);
		creditTelcheckList.setOperator(apUser.getUserName());
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择需要操作的行ids");
					throw e;
				}
				creditTelcheckList.setIds(ids);
				break;
				
			case "currStatus":
				String currStatus = creditTelcheckList.getCurrStatus();
				if( null == currStatus || (!currStatus.trim().equals("1") && !currStatus.trim().equals("0"))){
					CoreException e = new CoreException("启用状态值currStatus非法");
					throw e;
				}
				break;
				
			case "autoId":
				String autoId = creditTelcheckList.getAutoId();
				if( null == autoId || autoId.trim().isEmpty()){
					CoreException e = new CoreException("查询历史操作记录的autoId值为空");
					throw e;
				}
				break;
				
			case "createTime":
			    creditTelcheckList.setCreateTime(createTime);
				break;
				
			default:
				break;
			}
		}
		return creditTelcheckList;
	}
		
}
