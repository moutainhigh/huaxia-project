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
import com.huaxia.opas.domain.decision.TeamdealList;
import com.huaxia.opas.service.sysparm.TeamdealService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 名单库管理_易达金团办名单action层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-19
 * 
 * @version 1.0
 */
public class TeamdealAction implements Action {
	
	@SuppressWarnings("unused")
	private static Logger logger=LoggerFactory.getLogger(TeamdealAction.class);
	
	@Resource(name="teamdealService")
	private TeamdealService teamdealService;
	
	/**
	 * 分页查询易达金团办名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	public void queryTeamdealHistoryList(Context ctx)throws CoreException{
		TeamdealList teamdealList = getTeamdeal(ctx,"autoId");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = teamdealService.queryTeamdealHistoryList(teamdealList, curPage, pageNum);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 分页查询易达金团办名单实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	public void queryTeamdealList(Context ctx) throws CoreException{
		TeamdealList teamdealList = getTeamdeal(ctx, "createTime");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = teamdealService.queryTeamdealList(teamdealList, curPage, pageNum);
		ctx.setDataMap(dataMap);	
	}

	/**
	 * 新增易达金团办名单实例
	 * 
	 * @author luzhen.ou
	 * */
	public void insertTeamdeal(Context ctx) throws CoreException{
		try{
			TeamdealList teamdealList = getTeamdeal(ctx);
			checkData(teamdealList);
			teamdealList.setAutoId(SequenceUtil.getUUID());
			teamdealService.save(teamdealList);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}
	
	/**
	 * 修改易达金团办名单实例
	 *  
	 * @author luzhen.ou
	 */
	public void updateTeamdeal(Context ctx) throws CoreException{
		try{
			TeamdealList teamdealList = getTeamdeal(ctx);
			checkData(teamdealList);
			teamdealList.setOpretionId(SequenceUtil.getUUID());
			teamdealService.update(teamdealList);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}	

	/**
	 * 删除易达金团办名单实例,通过主键集合ids
	 *  
	 * @author luzhen.ou
	 */	
	public void deleteTeamdeal(Context ctx) throws CoreException{
		try{
			TeamdealList teamdealList = getTeamdeal(ctx, "ids");
			teamdealService.remove(teamdealList);
			ctx.setData("isSuccess",true);
		} catch (CoreException e) {
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}

	/**
	 * 批量修改易达金团办名单实例启用状态
	 *  
	 * @author luzhen.ou
	 */	
	public void updateTeamdealActive(Context ctx) throws CoreException{
		try{
			TeamdealList teamdealList = getTeamdeal(ctx,"ids","currStatus");
			teamdealService.updateTeamdealActive(teamdealList);
			ctx.setData("isSuccess",true);
			} catch (CoreException e) {
				ctx.setData("exMsg",e.getMessage());
				ctx.setData("isSuccess",false);
				throw e;
			}
	}	

	/*
	*//**
	 * 批量导入易达金团办名单实例Excel
	 * 
	 * @author luzhen.ou
	 * *//*
	public void insertTeamdealFileUpload (Context ctx) throws CoreException{
		try {
			InputStream in = null;
			Map<String,Object> map = ctx.getDataMap();
			String fileName = (String) map.get("fileName");
			in = new FileInputStream(fileName);
			ExcelReader excelReader = new ExcelReader();
			List<TeamdealList> list = readExcel2(in,ctx);
			teamdealService.insertTeamdealList(list);
			ctx.setData("isSuccess", true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ctx.setData("isSuccess", false);
		}
	}
	*/

	
	/**
	 * 易达金团办名单实例新增和修改数据校验
	 *  
	 * @author luzhen.ou
	 */	
	public void checkData(TeamdealList teamdealList) throws CoreException{
		
		String txt= "[^_][\\u4e00-\\u9fa5[^\\x00-\\xff][\\w]]*";	
		String code = "[a-zA-Z0-9]{1,20}";
		String date = "[1-9][0-9]{3}-[0-1][1-9]-[0-3][0-9]";
		
		String tmp = teamdealList.getTeamdealType();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 10) {
			throw new CoreException("团办类型长度不能超过10个字符!");
			}
			if (!tmp.trim().matches(txt) ) {
			throw new CoreException("团办类型应为中英文、数字、下滑线组成，首字母不能为下滑线!");
			}
		}
		
		tmp = teamdealList.getTeamdealCode();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 10) {
			throw new CoreException("团办码长度不能超过10个字符!");
			}
			if (!tmp.trim().matches(code) ) {
			throw new CoreException("团办码应为英文、数字组成!");
			}
		}
		
		tmp = teamdealList.getApproveDate();
//		if ( null != tmp && !tmp.trim().isEmpty() ){
//			 if ( tmp.trim().length() != 10 ) {
//				 throw new CoreException("批复日期格式不正确，应为yyyy-MM-dd1");
//			 }
//			 if (!tmp.trim().matches(date)) {
//				 throw new CoreException("批复日期格式不正确，应为yyyy-MM-dd2");	
//			 }
//		}
		
		tmp = teamdealList.getReportingOrg();
		if ( null != tmp &&! tmp.trim().isEmpty()) {
			 if (tmp.trim().length() > 20) {
				 throw new CoreException("申报机构长度不能超过20个字符!");
			 }
			 if (!tmp.trim().matches(txt) ) {
				 throw new CoreException("申报机构应为中英文、数字、下滑线组成，首字母不能为下滑线!");
			 }	
		}
		
		tmp = teamdealList.getCompanyName();
		if ( null != tmp &&!tmp.trim().isEmpty()) {
			if (tmp.trim().length() > 2000) {
			throw new CoreException("申报机构长度不能超过2000个字符!");
			}
		}	
		
		tmp = teamdealList.getCompanyType();
		if ( null != tmp &&!tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 10) {
				throw new CoreException("企业类型长度不能超过10个字符!");
			}
			if (!tmp.trim().matches(txt) ) {
				throw new CoreException("企业类型应为中英文、数字、下滑线组成，首字母不能为下滑线!");
			}
		}	
		
		tmp = teamdealList.getApplyRequest();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 1000) {
				throw new CoreException("进件要求长度不能超过1000个字符!");
			}
		}	
		
	}
	
	/**
	 * 抽取前台页面获取数据的公共方法,并对传入数据进行后台校验
	 *  
	 * @author luzhen.ou
	 */	   
	public TeamdealList getTeamdeal(Context ctx,String ...strings) throws CoreException{
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
					CoreException oreException = new CoreException("创建时间格式不正确");
					throw oreException;
				}
			}
			map.remove("createTime");
		}
		
		Gson gson = new Gson();
		TeamdealList teamdealList = gson.fromJson(gson.toJson(map), TeamdealList.class);	
		
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("登录用户不能为空，请先登录");
			throw e;
		}
		teamdealList.setOperator(operator);
		 
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择要操作的行!");
					throw e;
				}
				teamdealList.setIds(ids);
				break;
				
			case "currStatus":
				String currStatus = teamdealList.getCurrStatus();
				if( null == currStatus || (!currStatus.trim().equals("1") && !currStatus.trim().equals("0"))){
					CoreException e = new CoreException("启用状态格式不正确!");
					throw e;
				}
				break;
				
			case "autoId":
				String autoId = teamdealList.getAutoId();
				if( null == autoId || autoId.trim().isEmpty()){
					CoreException e = new CoreException("请选择要查询历史记录的行!");
					throw e;
				}
				break;
				
			case "createTime":
			    teamdealList.setCreateTime(createTimeDate);
				break;
				
			default:
				break;
			}
		}
		return teamdealList;
	}

}
