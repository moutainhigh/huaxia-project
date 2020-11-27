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
import com.huaxia.opas.domain.sysparm.IdentityRisk;
import com.huaxia.opas.service.sysparm.IdentityRiskService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 系统参数--身份类风险名单action层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-02-28
 * 
 * @version 1.0
 */
public class IdentityRiskAction implements Action {
	
	@SuppressWarnings("unused")
	private static Logger logger=LoggerFactory.getLogger(IdentityRiskAction.class);
	
	@Resource(name="identityRiskService")
	private IdentityRiskService identityRiskService;
	
	/**
	 * 分页查询身份类风险名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	public void queryIdentityRiskHistoryList(Context ctx)throws CoreException{
		IdentityRisk identityRisk = getIdentityRisk(ctx,"autoId");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = identityRiskService.queryIdentityRiskHistoryList(identityRisk, curPage, pageNum);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 分页查询身份类风险名单实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	public void queryIdentityRiskList(Context ctx) throws CoreException{
		IdentityRisk identityRisk = getIdentityRisk(ctx, "createTime");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = identityRiskService.queryIdentityRiskList(identityRisk, curPage, pageNum);
		ctx.setDataMap(dataMap);	
	}

	/**
	 * 新增身份类风险名单实例
	 * 
	 * @author luzhen.ou
	 * */
	public void insertIdentityRisk(Context ctx) throws CoreException{
		try{
			IdentityRisk identityRisk = getIdentityRisk(ctx, "invalidTime");
			checkData(identityRisk);
			identityRisk.setAutoId(SequenceUtil.getUUID());
			identityRiskService.save(identityRisk);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());			
			ctx.setData("isSuccess",false);
			throw e;
		}
	}
	
	/**
	 * 修改身份类风险名单实例
	 *  
	 * @author luzhen.ou
	 */
	public void updateIdentityRisk(Context ctx) throws CoreException{
		try{
			IdentityRisk identityRisk = getIdentityRisk(ctx,"invalidTime");
			checkData(identityRisk);
			identityRisk.setOpretionId(SequenceUtil.getUUID());
			identityRiskService.update(identityRisk);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}	

	/**
	 * 删除身份类风险名单实例,通过主键集合ids
	 *  
	 * @author luzhen.ou
	 */	
	public void deleteIdentityRisk(Context ctx) throws CoreException{
		try {
			IdentityRisk identityRisk = getIdentityRisk(ctx,"ids");
			identityRiskService.remove(identityRisk);
			ctx.setData("isSuccess",true);
		} catch (CoreException e) {
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}

	/**
	 * 批量修改身份类风险名单实例启用状态
	 *  
	 * @author luzhen.ou
	 */	
	public void updateIdentityRiskActive(Context ctx) throws CoreException{
		try {
			IdentityRisk identityRisk = getIdentityRisk(ctx,"ids","currStatus");
			identityRiskService.updateIdentityRiskActive(identityRisk);
			ctx.setData("isSuccess",true);
		} catch (CoreException e) {
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}	

	/*
 		批量导入身份类风险名单实例Excel
	public void insertIdentityRiskFileUpload (Context ctx) throws CoreException{
		try {
			InputStream in = null;
			Map<String,Object> map = ctx.getDataMap();
			String fileName = (String) map.get("fileName");
			in = new FileInputStream(fileName);
			ExcelReader excelReader = new ExcelReader();
			List<IdentityRisk> list = readExcel2(in,ctx);
			identityRiskService.insertIdentityRiskList(list);
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
	public void checkData(IdentityRisk identityRisk) throws CoreException{
		
		String txt= "[^_][\\u4e00-\\u9fa5[^\\x00-\\xff][\\w]]*";	
		String IDCard18 = "^[1-9]\\d{5}(18|19|([29]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
//		String IDCard15 = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$";
		String IDCard15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
		String tmp = identityRisk.getName();
		if ( null != tmp && !tmp.trim().isEmpty() ) {
			if (tmp.trim().length() > 32) {
			throw new CoreException("姓名长度不能超过32个字符!");
			}
			if (!tmp.trim().matches(txt) ) {
			throw new CoreException("姓名应为中英文、数字、下滑线组成，首字母不能为下滑线!");
			}
		}
		
		tmp = identityRisk.getCertifiType();
		if ( null == tmp || tmp.trim().length() != 2) {
			throw new CoreException("证件类型格式不正确!");
		}else if("06".equals(tmp)){
			tmp = identityRisk.getCertifiTypeExtra();
			if (tmp == null || tmp.trim().isEmpty()) {
				throw new CoreException("其他证件类型不能为空!");
			}else if (tmp.trim().length() > 32) {
				throw new CoreException("其他证件类型长度不能超过32个字符!");
			}else if (!tmp.trim().matches(txt) ) {
				throw new CoreException("其他证件类型应为中英文、数字、下滑线组成，首字母不能为下滑线!");
			}
		}
		
		String tmpNo = identityRisk.getCertifiNo();
		if ( null != tmpNo && !tmpNo.trim().isEmpty() ) {
			 if (tmpNo.trim().length() > 20) {
				 throw new CoreException("证件号长度不能超过20个字符!");
			 }
			if("01".equals(tmp) ){
				if(!tmpNo.matches(IDCard18)){
					throw new CoreException("18位身份证格式不正确!");
				}
			}else if("02".equals(tmp) ){
				if(!tmpNo.matches(IDCard15)){
					throw new CoreException("15位身份证格式不正确!");
				}
			}
		}
		
		tmp = identityRisk.getInfoChannel();
		if ( null == tmp || tmp.trim().length() != 1) {
			throw new CoreException("信息来源格式不正确!");
		}else if("6".equals(tmp)){
			tmp = identityRisk.getInfoChannelExtra();
			if (tmp == null || tmp.trim().isEmpty()) {
				throw new CoreException("其他信息来源不能为空!");
			}else if (tmp.trim().length() > 32) {
				throw new CoreException("其他信息来源长度不能超过32个字符!");
			}else if (!tmp.trim().matches(txt) ) {
				throw new CoreException("其他信息来源应为中英文、数字、下滑线组成，首字母不能为下滑线!");
			}
		}		
		
		tmp = identityRisk.getFraudType();
		if ( null == tmp || tmp.trim().length() != 1) {
			throw new CoreException("欺诈类型格式不正确!");
		}else if("4".equals(tmp)){
			tmp = identityRisk.getFraudTypeExtra();
			if (tmp == null || tmp.trim().isEmpty()) {
				throw new CoreException("欺诈类型不能为空!");
			}else if (tmp.trim().length() > 32) {
				throw new CoreException("欺诈类型长度不能超过32个字符!");
			}else if (!tmp.trim().matches(txt) ) {
				throw new CoreException("欺诈类型应为中英文、数字、下滑线组成，首字母不能为下滑线!");
			}	
		}		
		
		tmp = identityRisk.getCurrStatus();
		if (!"0".equals(tmp) && !"1".equals(tmp)) {
			throw new CoreException("启用状态格式不正确!");
		}
		
		tmp = identityRisk.getMemo();
		if (null != tmp) {
			if(tmp.trim().length() > 200){
				throw new CoreException("备注长度不能超过200个字符!");
			}
			if(tmp.trim().isEmpty()){
				identityRisk.setMemo(null);
			}
		}
		
	}
	
	/**
	 * 抽取前台页面获取数据的公共方法,并对传入数据进行后台校验
	 *  
	 * @author luzhen.ou
	 */	   
	public IdentityRisk getIdentityRisk(Context ctx,String ...strings) throws CoreException{
		
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
					CoreException oreException = new CoreException("失效时间格式不正确");
					throw oreException;
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
				} catch (ParseException e) {
					e.printStackTrace();
					CoreException oreException = new CoreException("创建时间格式不正确");
					throw oreException;
				}
			}
			map.remove("createTime");
		}
		
		Gson gson = new Gson();
		IdentityRisk identityRisk = gson.fromJson(gson.toJson(map), IdentityRisk.class);

		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("登录用户不能为空，请先登录");
			throw e;
		}
		identityRisk.setOperator(operator);
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择要操作的行!");
					throw e;
				}
				identityRisk.setIds(ids);
				break;
				
			case "currStatus":
				String currStatus = identityRisk.getCurrStatus();
				if( null == currStatus || (!currStatus.trim().equals("1") && !currStatus.trim().equals("0"))){
					CoreException e = new CoreException("启用状态格式不正确!");
					throw e;
				}
				break;
				
			case "autoId":
				String autoId = identityRisk.getAutoId();
				if( null == autoId || autoId.trim().isEmpty()){
					CoreException e = new CoreException("请选择要查询历史记录的行!");
					throw e;
				}
				break;
				
			case "invalidTime":
			    identityRisk.setInvalidTime(invalidTimeDate);
				break;
				
			case "createTime":
			    identityRisk.setCreateTime(createTimeDate);
				break;
				
			default:
				break;
			}
		}
		return identityRisk;
	}
	
}
