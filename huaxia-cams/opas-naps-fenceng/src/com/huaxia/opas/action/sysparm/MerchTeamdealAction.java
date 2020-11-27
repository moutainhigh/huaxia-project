package com.huaxia.opas.action.sysparm;

import java.math.BigDecimal;
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
import com.huaxia.opas.domain.decision.MerchTeamdealList;
import com.huaxia.opas.service.sysparm.MerchTeamdealService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 名单库管理_商户团办名单action层接口的相关方法
 * @author luzhen.ou
 * 
 * @since 2017-03-18
 * 
 * @version 1.0
 */
public class MerchTeamdealAction implements Action {
	
	@SuppressWarnings("unused")
	private static Logger logger=LoggerFactory.getLogger(MerchTeamdealAction.class);
	
	@Resource(name="merchTeamdealService")
	private MerchTeamdealService merchTeamdealService;
	
	/**
	 * 分页查询商户团办名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	public void queryMerchTeamdealHistoryList(Context ctx)throws CoreException{
		MerchTeamdealList merchTeamdealList = getMerchTeamdeal(ctx,"autoId");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = merchTeamdealService.queryMerchTeamdealHistoryList(merchTeamdealList, curPage, pageNum);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 分页查询商户团办名单实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	public void queryMerchTeamdealList(Context ctx) throws CoreException{
		MerchTeamdealList merchTeamdealList = getMerchTeamdeal(ctx, "createTime");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = merchTeamdealService.queryMerchTeamdealList(merchTeamdealList, curPage, pageNum);
		ctx.setDataMap(dataMap);	
	}

	/**
	 * 新增商户团办名单实例
	 * 
	 * @author luzhen.ou
	 * */
	public void insertMerchTeamdeal(Context ctx) throws CoreException{
		try{
			MerchTeamdealList merchTeamdealList = getMerchTeamdeal(ctx);
			checkData(merchTeamdealList);
			merchTeamdealList.setAutoId(SequenceUtil.getUUID());
			merchTeamdealService.insertMerchTeamdeal(merchTeamdealList);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}
	
	/**
	 * 修改商户团办名单实例
	 *  
	 * @author luzhen.ou
	 */
	public void updateMerchTeamdeal(Context ctx) throws CoreException{
		try{
			MerchTeamdealList merchTeamdealList = getMerchTeamdeal(ctx);
			checkData(merchTeamdealList);
			merchTeamdealList.setOpretionId(SequenceUtil.getUUID());
			String producePriceNoZero = merchTeamdealList.getProducePrice().stripTrailingZeros().toPlainString();
			merchTeamdealList.setProducePrice(new BigDecimal(producePriceNoZero));
			merchTeamdealService.updateMerchTeamdeal(merchTeamdealList);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}	

	/**
	 * 删除商户团办名单实例,通过主键集合ids
	 *  
	 * @author luzhen.ou
	 */	
	public void deleteMerchTeamdeal(Context ctx) throws CoreException{
		try {
			MerchTeamdealList merchTeamdealList = getMerchTeamdeal(ctx, "ids");
			merchTeamdealService.deleteMerchTeamdeal(merchTeamdealList);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}

	/**
	 * 批量修改商户团办名单实例启用状态
	 *  
	 * @author luzhen.ou
	 */	
	public void updateMerchTeamdealActive(Context ctx) throws CoreException{
		try {
			MerchTeamdealList merchTeamdealList = getMerchTeamdeal(ctx,"ids","currStatus");
			merchTeamdealService.updateMerchTeamdealActive(merchTeamdealList);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}	

	/*
	*//**
	 * 批量导入商户团办名单实例Excel
	 * 
	 * @author luzhen.ou
	 * *//*
	public void insertMerchTeamdealFileUpload (Context ctx) throws CoreException{
		try {
			InputStream in = null;
			Map<String,Object> map = ctx.getDataMap();
			String fileName = (String) map.get("fileName");
			in = new FileInputStream(fileName);
			ExcelReader excelReader = new ExcelReader();
			List<MerchTeamdealList> list = readExcel2(in,ctx);
			merchTeamdealService.insertMerchTeamdealList(list);
			ctx.setData("isSuccess", true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ctx.setData("isSuccess", false);
		}
	}
	*/
	
	
	/**
	 * 商户团办名单新增和修改数据校验
	 *  
	 * @author luzhen.ou
	 */	
	public void checkData(MerchTeamdealList merchTeamdealList) throws CoreException{
		
		String txt= "[^_][\\u4e00-\\u9fa5[^\\x00-\\xff][\\w]]*";	
		String code = "[a-zA-Z0-9]{1,20}";
		
		String tmp = merchTeamdealList.getTeamdealNo();
		if ( null != tmp ) {
			if(tmp.trim().isEmpty()){
				merchTeamdealList.setTeamdealNo(null);
			}else{
				if(tmp.trim().length() > 10){
					throw new CoreException("团办号长度不能超过10个字符!");
				}
				if(!tmp.trim().matches(txt)){
					throw new CoreException("单位名称应为中英文、数字、下滑线组成，首字母不能为下滑线!");				
				}
			}
		}
		
		tmp = merchTeamdealList.getMerchName();
		if ( null != tmp ) {
			if(tmp.trim().isEmpty()){
				merchTeamdealList.setMerchName(null);
			}else{
				if (tmp.trim().length() > 50) {
					throw new CoreException("合作商户名称长度不能超过50个字符!");
				}
			}
		}
		
		BigDecimal producePrice = null;
		producePrice = merchTeamdealList.getProducePrice();
		if ( null != producePrice ) {
			if(producePrice.intValue() < 0){
				throw new CoreException("商品价格不能为负数!");	
			}
		}
		
		tmp = merchTeamdealList.getProduceName();
		if ( null != tmp ) {
			if(tmp.trim().isEmpty()){
				merchTeamdealList.setProduceName(null);
			}else{
				if (tmp.trim().length() > 100) {
					throw new CoreException("商品名称长度不能超过100个字符!");
				}
			}
		}
	}
	
	/**
	 * 抽取前台页面获取数据的公共方法,并对传入数据进行后台校验
	 *  
	 * @author luzhen.ou
	 */	   
	public MerchTeamdealList getMerchTeamdeal(Context ctx,String ...strings) throws CoreException{
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
		Date createTime = null;
		if (list.contains("createTime")) {
			String createTimeString = (String)map.get("createTime");
			if(null!=createTimeString && !createTimeString.trim().isEmpty()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					createTime  = sdf.parse(createTimeString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			map.remove("createTime");
		}
		if("".equals(map.get("producePrice"))){
			map.put("producePrice", new BigDecimal(0));
		}
		Gson gson = new Gson();
		MerchTeamdealList merchTeamdealList = gson.fromJson(gson.toJson(map), MerchTeamdealList.class);
	
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		merchTeamdealList.setOperator(operator);
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择需要操作的行ids");
					throw e;
				}
				merchTeamdealList.setIds(ids);
				break;
				
			case "currStatus":
				String currStatus = merchTeamdealList.getCurrStatus();
				if( null == currStatus || (!currStatus.trim().equals("1") && !currStatus.trim().equals("0"))){
					CoreException e = new CoreException("启用状态值currStatus非法");
					throw e;
				}
				break;
				
			case "autoId":
				String autoId = merchTeamdealList.getAutoId();
				if( null == autoId || autoId.trim().isEmpty()){
					CoreException e = new CoreException("查询历史操作记录的autoId值为空");
					throw e;
				}
				break;
				
			case "createTime":
			    merchTeamdealList.setCreateTime(createTime);
				break;
				
			default:
				break;
			}
		}
		return merchTeamdealList;
	}
		
}
