package com.huaxia.opas.service.sysparm.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.StockcustTelSaleDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.StockcustTelSale;
import com.huaxia.opas.service.sysparm.StockcustTelSaleService;

/**
 * 存量客户电销名单
 * @author 汪涛
 *
 */
public class StockcustTelSaleServiceImpl implements StockcustTelSaleService,Serializable {
	@Resource(name="stockcustTelSaleDao")
	private StockcustTelSaleDao  stockcustTelSaleDao;
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	public BatchFileInfoDao getBatchFileInfoDao() {
		return batchFileInfoDao;
	}
	public void setBatchFileInfoDao(BatchFileInfoDao batchFileInfoDao) {
		this.batchFileInfoDao = batchFileInfoDao;
	}
	public StockcustTelSaleDao getStockcustTelSaleDao() {
		return stockcustTelSaleDao;
	}
	public void setStockcustTelSaleDao(StockcustTelSaleDao stockcustTelSaleDao) {
		this.stockcustTelSaleDao = stockcustTelSaleDao;
	}
	
	protected static Logger logger = LoggerFactory.getLogger(StockcustTelSaleServiceImpl.class);

	//日期校验正则
	private static final String pattDate = "^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\\2(?:29))$";
	//身份证号校验正则
	private static final String	certifiIdPatt = "^\\d{15}(\\d{2}[A-Za-z0-9])?$";
		
	//校验的方法
	public static boolean forRegex(String field,String regex){
		Pattern pattern = Pattern.compile(regex) ;
		Matcher matcher = pattern.matcher(field);
		boolean flag = matcher.matches();
		return flag;
	}
	
	//校验的方法
	public static void validate(StockcustTelSale stockcustTelSale) throws Exception{
		if(stockcustTelSale.getAcctNo() != null&& !("".equals(stockcustTelSale.getAcctNo().trim()))){
			if(stockcustTelSale.getAcctNo().length() > 20){
				 logger.error("StockcustTelSaleServiceImpl 存量客户电销的service实现类的帐号 acctNo不能超过20位");
				 throw new Exception("帐号不能超过20位");
			 }
		}
		if(stockcustTelSale.getCertifiNo() != null&& !("".equals(stockcustTelSale.getCertifiNo().trim()))){
			if(stockcustTelSale.getCertifiNo().length() > 20){
				 logger.error("StockcustTelSaleServiceImpl 存量客户电销的service实现类的身份证号码 certifiNo不能超过20位");	 
				 throw new Exception("身份证号码不能超过20位");
			 }
			if(!StockcustTelSaleServiceImpl.forRegex(stockcustTelSale.getCertifiNo(),certifiIdPatt)){
				 logger.error("SameIndustryRiskServiceImpl 存量客户电销的service实现类的身份证格式不正确");
				 throw new Exception("身份证号码格式不正确");
			}
		}
		if(stockcustTelSale.getCustName() != null&& !("".equals(stockcustTelSale.getCustName().trim()))){
			if(stockcustTelSale.getCustName().length() > 32){
				 logger.error("StockcustTelSaleServiceImpl 存量客户电销的service实现类的客户姓名 custName不能超过32字 ");	 
				 throw new Exception("客户姓名不能超过32个字");
			 }
		}
		if(String.valueOf(stockcustTelSale.getTelSaleLimit()) != null&& !("".equals(String.valueOf(stockcustTelSale.getTelSaleLimit()).trim()))){
			if(String.valueOf(stockcustTelSale.getTelSaleLimit()).length() > 10){
				 logger.error("StockcustTelSaleServiceImpl 存量客户电销的service实现类的电销额度 telSaleLimit不能超过10位数");
				 throw new Exception("预授信额度不能超过10位数");
			 }
		}
		if(stockcustTelSale.getAdjustProcess() != null&& !("".equals(stockcustTelSale.getAdjustProcess().trim()))){
			if(stockcustTelSale.getAdjustProcess().length() > 200){
				 logger.error("StockcustTelSaleServiceImpl 存量客户电销的service实现类的调额过程 adjustProcess不能超过200字");
				 throw new Exception("调额过程不能超过200字");
			 }
		}
		if(String.valueOf(stockcustTelSale.getPeopleBankScore()) != null&& !("".equals(String.valueOf(stockcustTelSale.getPeopleBankScore()).trim()))){
			if(String.valueOf(stockcustTelSale.getPeopleBankScore()).length() > 10){
				 logger.error("StockcustTelSaleServiceImpl 存量客户电销的service实现类的贷后人行评分 peopleBankScore不能超过10位数");	 
				 throw new Exception("贷后人行评分不能超过10位数");
			 }
		}
		if(stockcustTelSale.getCreateTime() != null&& !("".equals(new SimpleDateFormat("yyyy-MM-dd").format(stockcustTelSale.getCreateTime()).trim()))){
			if(!StockcustTelSaleServiceImpl.forRegex(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(stockcustTelSale.getCreateTime())),pattDate)){
				 logger.error("SameIndustryRiskServiceImpl 存量客户电销的service实现类的创建日期格式不正确");
				 throw new Exception("创建日期格式不正确");
			}
		}
	}
	
	
	@Override
	public int queryStockcustTelSaleCount(StockcustTelSale stockcustTelSale) {
		return stockcustTelSaleDao.queryStockcustTelSaleCount(stockcustTelSale);
	}
	@Override
	public List<StockcustTelSale> queryStockcustTelSaleList(StockcustTelSale stockcustTelSale, int curNum,
			int pageNum) {
		return stockcustTelSaleDao.queryStockcustTelSaleList(stockcustTelSale,curNum,pageNum);
	}
	/**
	 * 存量客户电销的新增
	 * @throws Exception 
	 */
	@Override
	public void insertStockcustTelSale(Context ctx) throws Exception {
		
		Gson gson = new Gson();
		Map<String,Object> map = ctx.getDataMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if("".equals(map.get("createTime"))|| null == map.get("createTime")){
				map.put("createTime",new Date());
			}else{
				Date createTime = sdf.parse((String) map.get("createTime"));
				map.put("createTime", createTime);
			}
		} catch (ParseException e) {
			logger.error("日期格式转换异常：{}", new Object[] { e.getMessage() }, e);
		}
		if("".equals(map.get("telSaleLimit"))){
			map.put("telSaleLimit", 0);
		}
		if("".equals(map.get("peopleBankScore"))){
			map.put("peopleBankScore", 0);
		}
		StockcustTelSale stockcustTelSale = gson.fromJson(gson.toJson(map),StockcustTelSale.class);
		
		//新增存量客户电销的验证
		StockcustTelSaleServiceImpl.validate(stockcustTelSale);
		
		//给客户设置autoId
		stockcustTelSale.setAutoId(UUID.randomUUID().toString().replace("-", ""));
		
		String userId = ctx.getData("userId");
		stockcustTelSale.setOperator(userId);
		
		stockcustTelSaleDao.insertStockcustTelSale(stockcustTelSale);
		//新增成功后插入一条历史记录
		stockcustTelSale.setOperatContent("身份证号:"+stockcustTelSale.getCertifiNo());
		stockcustTelSale.setOperatDo("新增");
		stockcustTelSaleDao.insertStockcustTelSaleHis(stockcustTelSale);
	}
	/**
	 * 存量客户电销的修改
	 * @throws Exception 
	 */
	@Override
	public void updateStockcustTelSale(Context ctx) throws Exception {
		Gson json = new Gson();
		Map<String,Object> map = ctx.getDataMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if("".equals(map.get("createTime"))|| null==map.get("createTime")){
				map.remove("createTime");
			}else{
				Date createTime = sdf.parse((String) map.get("createTime"));
				map.put("createTime", createTime);
			}
		} catch (ParseException e) {
			logger.error("日期格式转换异常：{}", new Object[] { e.getMessage() }, e);
		}
		if("".equals(map.get("telSaleLimit"))){
			map.put("telSaleLimit", 0);
		}
		if("".equals(map.get("peopleBankScore"))){
			map.put("peopleBankScore", 0);
		}
		StockcustTelSale stockcustTelSale = json.fromJson(json.toJson(map),StockcustTelSale.class);
		//修改存量客户电销的验证
		StockcustTelSaleServiceImpl.validate(stockcustTelSale);
		String userId = ctx.getData("userId");
		stockcustTelSale.setOperator(userId);
		
		stockcustTelSaleDao.updateStockcustTelSale(stockcustTelSale);
		//修改成功后添加一条历史记录
		stockcustTelSale.setOperatContent("身份证号:"+stockcustTelSale.getCertifiNo());
		stockcustTelSale.setOperatDo("修改");
		stockcustTelSaleDao.insertStockcustTelSaleHis(stockcustTelSale);
	}
	/**
	 * 存量客户电销的删除
	 */
	@Override
	public void deleteStockcustTelSale(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		List<String> autoIds = (List<String>) map.get("autoIds");
		stockcustTelSaleDao.deleteStockcustTelSale(autoIds);
	}
	@Override
	public void batchStartStockcustTelSale(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		List<Map<Object,Object>> stockcustTelSaleMaps =   (List<Map<Object, Object>>) map.get("stockObj");
		String userId = ctx.getData("userId");
		for (Map<Object,Object> stockcustTelSaleMap : stockcustTelSaleMaps) {
			stockcustTelSaleMap.put("currStatus","1");
			stockcustTelSaleMap.put("operator",userId);
			stockcustTelSaleMap.put("operatDo", "批量启用");
			stockcustTelSaleMap.put("operatContent", "身份证号 ："+stockcustTelSaleMap.get("certifiNo"));
		}
		stockcustTelSaleDao.batchStartStockcustTelSale(stockcustTelSaleMaps);
	
		stockcustTelSaleDao.insertStockcustTelSaleHisOfBatch(stockcustTelSaleMaps);
		
	}
	/**
	 * 批量禁用
	 */
	@Override
	public void batchStopStockcustTelSale(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		List<Map<Object,Object>> stockcustTelSaleMaps =   (List<Map<Object, Object>>) map.get("stockObj");
		String userId = ctx.getData("userId");
		for (Map<Object,Object> stockcustTelSaleMap : stockcustTelSaleMaps) {
			stockcustTelSaleMap.put("currStatus","0");
			stockcustTelSaleMap.put("operator",userId);
			stockcustTelSaleMap.put("operatDo", "批量启用");
			stockcustTelSaleMap.put("operatContent", "身份证号 ："+stockcustTelSaleMap.get("certifiNo"));
		}
		stockcustTelSaleDao.batchStopStockcustTelSale(stockcustTelSaleMaps);
	
		stockcustTelSaleDao.insertStockcustTelSaleHisOfBatch(stockcustTelSaleMaps);
	}
	
	@Override
	public int insertStockImportFile(List<StockcustTelSale> stockcustTelSaleList,BatchfileInfo batchfileInfo) throws Exception {
		int inputCounts =  stockcustTelSaleDao.insertStockImportFile(stockcustTelSaleList);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
		
	}
	
	@Override
	public int stockcustTelSaleHistoryCount(String autoId) {
		return stockcustTelSaleDao.stockcustTelSaleHistoryCount(autoId);
	}
	@Override
	public List<StockcustTelSale> stockcustTelSaleHistory(String autoId, int curNum, int pageNum) {
		return stockcustTelSaleDao.stockcustTelSaleHistory(autoId,curNum,pageNum);
	}
	
}
