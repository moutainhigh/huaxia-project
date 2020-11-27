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

import com.huateng.neofp.core.Context;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.SameIndustryRiskDao;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.SameIndustryRisk;
import com.huaxia.opas.service.sysparm.SameIndustryRiskService;

/**
 * 同业关注风险名单库
 * @author 汪涛
 *
 */
public class SameIndustryRiskServiceImpl implements SameIndustryRiskService , Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3086650227811446161L;
	@Resource(name="sameIndustryRiskDao")
	private SameIndustryRiskDao sameIndustryRiskDao;
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	public BatchFileInfoDao getBatchFileInfoDao() {
		return batchFileInfoDao;
	}
	public void setBatchFileInfoDao(BatchFileInfoDao batchFileInfoDao) {
		this.batchFileInfoDao = batchFileInfoDao;
	}
	public SameIndustryRiskDao getSameIndustryRiskDao() {
		return sameIndustryRiskDao;
	}
	public void setSameIndustryRiskDao(SameIndustryRiskDao sameIndustryRiskDao) {
		this.sameIndustryRiskDao = sameIndustryRiskDao;
	}
	
	protected static Logger logger = LoggerFactory.getLogger(SameIndustryRiskServiceImpl.class);
	
	//日期校验的正则
	private static final String pattDate = "^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\\2(?:29))$";
	//15位身份证号校验地正则
	private static final String	certifiIdPatt = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	//居民身份证校验（18位）
	private static final String	certifiIdPatt1 = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
	//校验的方法
	public static boolean forRegex(String field,String regex){
		Pattern pattern = Pattern.compile(regex) ;
		Matcher matcher = pattern.matcher(field);
		boolean flag = matcher.matches();
		return flag;
	}
	
	//后台校验的方法
	public static void validate(SameIndustryRisk sameIndustryRisk) throws Exception{
		 if(sameIndustryRisk.getName() != null&&!("".equals(sameIndustryRisk.getName().trim()))){
			 if(sameIndustryRisk.getName().length() > 32){
				 logger.error("SameIndustryRiskServiceImpl 同业关注风险名单的service实现类的姓名 name不能超过32位");	 
				 throw new Exception("姓名不能超过32位");
			 }
		 }
		 if(sameIndustryRisk.getCertifiType() == null||"".equals(sameIndustryRisk.getCertifiType().trim())){
			 logger.error("SameIndustryRiskServiceImpl 同业关注风险名单的service实现类的证件类型 certifiType为空");
			 throw new Exception("证件类型不能为空");
		 }
		 if(sameIndustryRisk.getCertifiNo() != null&&!("".equals(sameIndustryRisk.getCertifiNo().trim()))){
			 if("01".equals(sameIndustryRisk.getCertifiType())){
				 if(!SameIndustryRiskServiceImpl.forRegex(sameIndustryRisk.getCertifiNo(),certifiIdPatt1)){
					 logger.error("SameIndustryRiskServiceImpl 同业关注风险名单的service实现类的身份证格式不正确");
					 throw new Exception("身份证格式不正确");
				 }
			 }
			 if("02".equals(sameIndustryRisk.getCertifiType())){
				 if(!SameIndustryRiskServiceImpl.forRegex(sameIndustryRisk.getCertifiNo(),certifiIdPatt)){
					 logger.error("SameIndustryRiskServiceImpl 同业关注风险名单的service实现类的身份证格式不正确");
					 throw new Exception("身份证格式不正确");
				 }
			 }
			 if(sameIndustryRisk.getCertifiNo().length() > 30){
				 logger.error("SameIndustryRiskServiceImpl 同业关注风险名单的service实现类的证件号码 certifiNo不能超过30位");
				 throw new Exception("证件号码不能超过30位");
			 }
		 }
		 if(sameIndustryRisk.getSubmitBank() != null&&!("".equals(sameIndustryRisk.getSubmitBank().trim()))){
			 if(sameIndustryRisk.getSubmitBank().length() > 20){
				 logger.error("SameIndustryRiskServiceImpl 同业关注风险名单的service实现类的报送行 submitBank不能超过20位");
				 throw new Exception("报送行不能超过20位");
			 }
		 }
		 if(sameIndustryRisk.getStarLevle() != null&& !("".equals(sameIndustryRisk.getStarLevle().trim()))){
			 if(sameIndustryRisk.getStarLevle().length() > 1){
				 logger.error("SameIndustryRiskServiceImpl 同业关注风险名单的service实现类的星级 starLevle不能超过1位");
				 throw new Exception("星级 不能超过1位");
			 }
		 }
		 if(sameIndustryRisk.getInfoChannel() == null || "".equals(sameIndustryRisk.getInfoChannel())){
			 logger.error("SameIndustryRiskServiceImpl 同业关注风险名单的service实现类的信息来源 infoChannel为空");	 
			 throw new Exception("信息来源不能为空");
		 }
		 if(sameIndustryRisk.getFraudType() == null || "".equals(sameIndustryRisk.getFraudType())){
			 logger.error("SameIndustryRiskServiceImpl 同业关注风险名单的service实现类的欺诈类型 fraudType为空");	 
			 throw new Exception("欺诈类型不能为空");
		 }
		 if(sameIndustryRisk.getCurrStatus() == null || "".equals(sameIndustryRisk.getCurrStatus())){
			 logger.error("SameIndustryRiskServiceImpl 同业关注风险名单的service实现类的当前状态  currStatus为空");
			 throw new Exception("当前状态不能为空");
		 }
		 if(sameIndustryRisk.getMemo()!= null && !("".equals(sameIndustryRisk.getMemo()))){
			 if(sameIndustryRisk.getMemo().length() > 200){
				 logger.error("SameIndustryRiskServiceImpl 同业关注风险名单的service实现类的备注信息  memo的字数不能超过200个字");	
				 throw new Exception("备注信息 字数不能超过200个字");
			 }
		 }
		 if(!SameIndustryRiskServiceImpl.forRegex(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(sameIndustryRisk.getInvalidTime())),pattDate)){
			 logger.error("SameIndustryRiskServiceImpl 同业关注风险名单的service实现类的结束日期格式不正确");
			 throw new Exception("结束日期格式不正确");
		 }
	}
	
	@Override
	public int querySameIndustryRiskCount(SameIndustryRisk sameIndustryRisk) {
		return sameIndustryRiskDao.querySameIndustryRiskCount(sameIndustryRisk);
	}
	/**
	 * 同业关注风险名单的查询列表显示
	 */
	@Override
	public List<SameIndustryRisk> querySameIndustryRiskList(SameIndustryRisk sameIndustryRisk, int curNum, int pageNum) {
		return sameIndustryRiskDao.querySameIndustryRiskList(sameIndustryRisk,curNum,pageNum);
	}
	/**
	 * 同业关注风险名单的新增
	 * @throws Exception 
	 */
	@Override
	public int insertSameIndustryRisk(SameIndustryRisk sameIndustryRisk) throws Exception {
		//对新增同业风险名单的校验
		SameIndustryRiskServiceImpl.validate(sameIndustryRisk);
		return 	sameIndustryRiskDao.insertSameIndustryRisk(sameIndustryRisk);
	}
	/**
	 * 同业关注风险名单的修改
	 * @throws Exception 
	 */
	@Override
	public int updateSameIndustryRisk(SameIndustryRisk sameIndustryRisk) throws Exception {
		//对修改同业风险名单的校验
		SameIndustryRiskServiceImpl.validate(sameIndustryRisk);
		return sameIndustryRiskDao.updateSameIndustryRisk(sameIndustryRisk);
	}
	/**
	 * 同业关注风险名单的删除
	 */
	@Override
	public int deleteSameIndustryRisk(String auto_id) {
		return sameIndustryRiskDao.deleteSameIndustryRisk(auto_id);
	}
	/**
	 * 同业关注风险名单的批量启用
	 */
	@Override
	public void batchStartSameIndustryRisk(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		List<Map<Object,Object>> sameIndustryRiskMaps =   (List<Map<Object, Object>>) map.get("riskObj");
		String userId = ctx.getData("userId");
		for (Map<Object,Object> sameIndustryRiskMap : sameIndustryRiskMaps) {
			sameIndustryRiskMap.put("id",UUID.randomUUID().toString().replace("-", ""));
			if(sameIndustryRiskMap.get("invalidTime")!=null&&!"".equals(sameIndustryRiskMap.get("invalidTime"))){
				try {
					sameIndustryRiskMap.put("invalidTime", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) sameIndustryRiskMap.get("invalidTime")));
					sameIndustryRiskMap.put("createTime", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) sameIndustryRiskMap.get("createTime")));
				} catch (ParseException e) {
				}
			}
			sameIndustryRiskMap.put("currStatus","1");
			sameIndustryRiskMap.put("operator",userId);
			sameIndustryRiskMap.put("operatType", "批量启用");
		}
		sameIndustryRiskDao.batchStartSameIndustryRisk(sameIndustryRiskMaps);
		sameIndustryRiskDao.insertSameIndustryRiskHisOfBatch(sameIndustryRiskMaps);
	
	}
	/**
	 * 同业关注风险名单的批量禁用
	 */
	@Override
	public void batchStopSameIndustryRisk(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		List<Map<Object,Object>> sameIndustryRiskMaps =   (List<Map<Object, Object>>) map.get("riskObj");
		String userId = ctx.getData("userId");
		for (Map<Object,Object> sameIndustryRiskMap : sameIndustryRiskMaps) {
			sameIndustryRiskMap.put("id",UUID.randomUUID().toString().replace("-", ""));
			if(sameIndustryRiskMap.get("invalidTime")!=null&&!"".equals(sameIndustryRiskMap.get("invalidTime"))){
				try {
					sameIndustryRiskMap.put("invalidTime", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) sameIndustryRiskMap.get("invalidTime")));
					sameIndustryRiskMap.put("createTime", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) sameIndustryRiskMap.get("createTime")));
				} catch (ParseException e) {
				}
			}
			sameIndustryRiskMap.put("currStatus","0");
			sameIndustryRiskMap.put("operator",userId);
			sameIndustryRiskMap.put("operatType", "批量停用");
		}
		try{
			sameIndustryRiskDao.batchStartSameIndustryRisk(sameIndustryRiskMaps);
			sameIndustryRiskDao.insertSameIndustryRiskHisOfBatch(sameIndustryRiskMaps);
		}catch(Exception e){
			logger.error("同业关注风险名单的批量禁用异常：{}", new Object[] { e.getMessage() }, e);
		}
	}
	/**
	 * 同业关注风险名单的批量删除
	 */
	@Override
	public void batchDeleteSameIndustryRisk(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		List<String> autoIds = (List<String>) map.get("autoIds");
		sameIndustryRiskDao.batchDeleteSameIndustryRisk(autoIds);
	}
	/**
	 * 同业关注风险名单的历史纪录新增
	 */
	@Override
	public int insertSameIndustryRiskHistory(SameIndustryRisk sameIndustryRisk) {
		return sameIndustryRiskDao.insertSameIndustryRiskHistory(sameIndustryRisk);
	}
	/**
	 * 同业关注风险名单的历史纪录的列表显示
	 */
	@Override
	public List<SameIndustryRisk> querySameIndustryRiskHistoryList(String auto_id, int curNum, int pageNum) {
		return sameIndustryRiskDao.querySameIndustryRiskHistoryList(auto_id,curNum,pageNum);
	}

	@Override
	public int querySameIndustryRiskHistoryCount(String auto_id) {
		return sameIndustryRiskDao.querySameIndustryRiskHistoryCount(auto_id);
	}
	/**
	 * 同业关注风险名单的批量导入
	 */
	@Override
	public int insertSIRiskImportFile(List<SameIndustryRisk> sameIndustryRiskList,BatchfileInfo batchfileInfo) throws Exception {
		int inputCounts =  sameIndustryRiskDao.insertSIRiskImportFile(sameIndustryRiskList);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insertRist(batchfileInfo);
		return inputCounts;
	}
	
}
