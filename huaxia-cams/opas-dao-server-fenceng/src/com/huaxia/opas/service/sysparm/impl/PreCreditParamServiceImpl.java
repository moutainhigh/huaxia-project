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

import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.PreCreditParamDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.PreCreditParam;
import com.huaxia.opas.service.sysparm.PreCreditParamService;
/**
 * 
 * @author 汪涛
 *
 */
public class PreCreditParamServiceImpl implements PreCreditParamService, Serializable {

	@Resource(name = "preCreditParamDao")
	private PreCreditParamDao preCreditParamDao;
	
	public PreCreditParamDao getPreCreditParamDao() {
		return preCreditParamDao;
	}

	public void setPreCreditParamDao(PreCreditParamDao preCreditParamDao) {
		this.preCreditParamDao = preCreditParamDao;
	}
	
	protected static Logger logger = LoggerFactory.getLogger(PreCreditParamServiceImpl.class);
	
	//日期校验的正则
	private static final String pattDate = "^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\\2(?:29))$";
	//身份证号校验的正则
	private static final String	certifiIdPatt = "^\\d{15}(\\d{2}[A-Za-z0-9])?$";
	//手机号校验的正则表达式
	private static final String	phoneNumPatt = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]$";
	//正则校验的方法
	public static boolean forRegex(String field,String regex){
		Pattern pattern = Pattern.compile(regex) ;
		Matcher matcher = pattern.matcher(field);
		boolean flag = matcher.matches();
		return flag;
	}
	
	//预授信信息的后台校验
	public static void validate(PreCreditParam preCreditParam) throws Exception{
		 if(preCreditParam.getCustID() == null||"".equals(preCreditParam.getCustID().trim())){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的客户ID custID不能为空");
			 throw new Exception("客户ID不能为空");
		 }
		 if(preCreditParam.getCustID().length() > 32){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的客户ID custID长度不能超过32位");	 
			 throw new Exception("客户ID长度不能超过32位");
		 }
		 if(String.valueOf(preCreditParam.getPreSellLimit()) == null ||"".equals(String.valueOf(preCreditParam.getPreSellLimit()).trim())){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的预筛选额度 preSellLimit不能为空");
			 throw new Exception("预筛选额度不能为空");
		 }
		 if(String.valueOf(preCreditParam.getPreSellLimit()).length() > 10 ){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的预筛选额度 preSellLimit的值最高为10个字符");
			 throw new Exception("预筛选额度不能超过10个数字");
		 }
		 if(preCreditParam.getStartDate() == null||"".equals(new SimpleDateFormat("yyyy-MM-dd").format(preCreditParam.getStartDate()).trim())){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的开始日期 startDate不能为空");
			 throw new Exception("开始日期不能为空");
		 }
		 if(preCreditParam.getEndDate() == null||"".equals(new SimpleDateFormat("yyyy-MM-dd").format(preCreditParam.getEndDate()).trim())){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的结束日期 endDate不能为空");
			 throw new Exception("结束日期不能为空");
		 }
		 if(preCreditParam.getEndDate().before(preCreditParam.getStartDate())){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的开始日期晚于结束日期");	 
			 throw new Exception("开始日期晚于结束日期");
		 }
		 if(preCreditParam.getCustName() == null || "".equals(preCreditParam.getCustName().trim())){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的客户姓名 custName不能为空");
			 throw new Exception("客户姓名不能为空");
		 }
		 if(preCreditParam.getCustName().length() > 32){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的客户姓名 custName长度不能超过32位");
			 throw new Exception("客户姓名不能超过32位");
		 }
		 if(preCreditParam.getCertifiID() == null || "".equals(preCreditParam.getCertifiID().trim())){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的身份证号 certifiID不能为空");
			 throw new Exception("身份证号不能为空");
		 }
		 if(preCreditParam.getCertifiID().length() > 32){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的身份证号 certifiID长度不能超过32位");
			 throw new Exception("身份证号长度不能超过32位");
		 }
		 if(preCreditParam.getPhoneNum() == null || "".equals(preCreditParam.getPhoneNum().trim())){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的手机号 phoneNum不能为空");
			 throw new Exception("手机号不能为空");
		 }
		 if(preCreditParam.getPhoneNum().length() > 11){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的手机号 phoneNum长度不能超过11位");
			 throw new Exception("手机号长度不能超过11位");
		 }
		 if(preCreditParam.getAcctType() == null || "".equals(preCreditParam.getAcctType().trim())){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的帐户类型 acctType不能为空");
			 throw new Exception("帐户类型不能为空");
		 }
		 if(preCreditParam.getStatus() == null || "".equals(preCreditParam.getStatus().trim())){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的启用状态 status不能为空");
			 throw new Exception("启用状态不能为空");
		 }
		 if(!PreCreditParamServiceImpl.forRegex(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(preCreditParam.getStartDate())),pattDate)){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的开始日期格式不正确");
			 throw new Exception("开始日期格式不正确");
		 }
		 if(!PreCreditParamServiceImpl.forRegex(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(preCreditParam.getEndDate())),pattDate)){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的结束日期格式不正确");
			 throw new Exception("结束日期格式不正确");
		 }
		 if(!PreCreditParamServiceImpl.forRegex(preCreditParam.getCertifiID(),certifiIdPatt)){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的身份证格式不正确");
			 throw new Exception("身份证格式不正确");
		 }
		 /*if(!PreCreditParamServiceImpl.forRegex(preCreditParam.getPhoneNum(),phoneNumPatt)){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的手机号格式不正确");
			 throw new Exception("手机号格式不正确");
		 }*/
	}
	
	//预授信信息的后台校验
		public static void validate1(PreCreditParam preCreditParam) throws Exception{
			 if(null!=preCreditParam.getCustID()&&!("".equals(preCreditParam.getCustID().trim()))){
				 if(preCreditParam.getCustID().length() > 32){
					 logger.error("PreCreditParamServiceImpl 预授信的service实现类的客户ID custID长度不能超过32位");	 
					 throw new Exception("客户ID长度不能超过32位");
				 }
			 }
			 if(String.valueOf(preCreditParam.getPreSellLimit()) == null ||"".equals(String.valueOf(preCreditParam.getPreSellLimit()).trim())){
				 logger.error("PreCreditParamServiceImpl 预授信的service实现类的预筛选额度 preSellLimit不能为空");
				 throw new Exception("预筛选额度不能为空");
			 }
			 if(String.valueOf(preCreditParam.getPreSellLimit()).length() > 10 ){
				 logger.error("PreCreditParamServiceImpl 预授信的service实现类的预筛选额度 preSellLimit的值最高为10个字符");
				 throw new Exception("预筛选额度不能超过10个数字");
			 }
			 if(preCreditParam.getStartDate() == null||"".equals(new SimpleDateFormat("yyyy-MM-dd").format(preCreditParam.getStartDate()).trim())){
				 logger.error("PreCreditParamServiceImpl 预授信的service实现类的开始日期 startDate不能为空");
				 throw new Exception("开始日期不能为空");
			 }
			 if(preCreditParam.getEndDate() == null||"".equals(new SimpleDateFormat("yyyy-MM-dd").format(preCreditParam.getEndDate()).trim())){
				 logger.error("PreCreditParamServiceImpl 预授信的service实现类的结束日期 endDate不能为空");
				 throw new Exception("结束日期不能为空");
			 }
			 if(preCreditParam.getEndDate().before(preCreditParam.getStartDate())){
				 logger.error("PreCreditParamServiceImpl 预授信的service实现类的开始日期晚于结束日期");	 
				 throw new Exception("开始日期晚于结束日期");
			 }
			 if(preCreditParam.getCustName() != null && !("".equals(preCreditParam.getCustName().trim()))){
				 if(preCreditParam.getCustName().length() > 32){
					 logger.error("PreCreditParamServiceImpl 预授信的service实现类的客户姓名 custName长度不能超过32位");
					 throw new Exception("客户姓名不能超过32位");
				 }
			 }
			 if(preCreditParam.getCertifiID() != null && !("".equals(preCreditParam.getCertifiID().trim()))){
				 if(preCreditParam.getCertifiID().length() > 32){
					 logger.error("PreCreditParamServiceImpl 预授信的service实现类的身份证号 certifiID长度不能超过32位");
					 throw new Exception("身份证号长度不能超过32位");
				 }
			 }
			 if(preCreditParam.getAcctType() == null || "".equals(preCreditParam.getAcctType().trim())){
				 logger.error("PreCreditParamServiceImpl 预授信的service实现类的帐户类型 acctType不能为空");
				 throw new Exception("帐户类型不能为空");
			 }
			 if(preCreditParam.getStatus() == null || "".equals(preCreditParam.getStatus().trim())){
				 logger.error("PreCreditParamServiceImpl 预授信的service实现类的启用状态 status不能为空");
				 throw new Exception("启用状态不能为空");
			 }
			 if(!PreCreditParamServiceImpl.forRegex(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(preCreditParam.getStartDate())),pattDate)){
				 logger.error("PreCreditParamServiceImpl 预授信的service实现类的开始日期格式不正确");
				 throw new Exception("开始日期格式不正确");
			 }
			 if(!PreCreditParamServiceImpl.forRegex(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(preCreditParam.getEndDate())),pattDate)){
				 logger.error("PreCreditParamServiceImpl 预授信的service实现类的结束日期格式不正确");
				 throw new Exception("结束日期格式不正确");
			 }
			 if(("".equals(preCreditParam.getCustID())||preCreditParam.getCustID() ==null)&&("".equals(preCreditParam.getCustName())||preCreditParam.getCustName() ==null)
					 &&("".equals(preCreditParam.getCertifiID())||preCreditParam.getCertifiID() ==null)){
				 logger.error("PreCreditParamServiceImpl 预授信的service实现类的客户ID,客户姓名，身份证号至少有一样不为空！");
				 throw new Exception("客户ID客户姓名身份证号有一样不为空！");
			 }
			 if(!PreCreditParamServiceImpl.forRegex(preCreditParam.getCertifiID(),certifiIdPatt)){
				 logger.error("PreCreditParamServiceImpl 预授信的service实现类的身份证格式不正确");
				 throw new Exception("身份证格式不正确");
			 }
		}
	
	@Override
	public int queryPreCreditParamCount(PreCreditParam preCreditParam) {
		return preCreditParamDao.queryPreCreditParamCount(preCreditParam);
	}
	//预授信信息的查询显示
	@Override
	public List<PreCreditParam> queryPreCreditParamList(
			PreCreditParam preCreditParam, int curNum, int pageNum) {
		return preCreditParamDao.queryPreCreditParamList(preCreditParam,
				curNum, pageNum);
	}
	//预授信信息的修改
	@Override
	public int updatePreCreditParam(PreCreditParam preCreditParam) throws Exception {
		//对修改后的预授信信息进行校验
		PreCreditParamServiceImpl.validate(preCreditParam);
		PreCreditParam preCreditParamHis = preCreditParamDao.selectById(preCreditParam.getAutoID());
		int result = preCreditParamDao.updatePreCreditParam(preCreditParam);
		preCreditParamHis.setOperationID(UUID.randomUUID().toString().replace("-", ""));
		preCreditParamDao.insertPreCreditParamHis(preCreditParamHis);
		return result;
	}
	//预授信信息的删除（包含在批量删除之内）
//	@Override
//	public int deletePreCreditParam(String autoID) {
//		return preCreditParamDao.deletePreCreditParam(autoID);
//	}
	//当新增时，设置状态为启用
	@Override
	public int insertPreCreditParamStart(PreCreditParam preCreditParam) throws Exception {
		//新增比修改多个字段是批次号，单独加个校验
		 if(preCreditParam.getBatchNo() == null||"".equals(preCreditParam.getBatchNo().trim())){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的批次号 batchNo不能为空");
		 }
		 if(preCreditParam.getBatchNo().length() > 32){
			 logger.error("PreCreditParamServiceImpl 预授信的service实现类的批次号 batchNo长度不能超过32位");	 
		 }
		//对新增的预授信信息进行校验
		PreCreditParamServiceImpl.validate(preCreditParam);
		
	    int result = preCreditParamDao.insertPreCreditParamStart(preCreditParam);
		return result;
	}
	//新增时，设置状态为禁用
	@Override
	public int insertPreCreditParamEnd(PreCreditParam preCreditParam) throws Exception {
		 //对新增的预授信信息进行校验
		PreCreditParamServiceImpl.validate(preCreditParam);
			
		int result = preCreditParamDao.insertPreCreditParamEnd(preCreditParam);
		return result;
	}
	//预授信信息的批量启用
	@Override
	public int batchStartPreCreditParam(List<Map<Object,Object>> preCreditParamMaps) throws ParseException {
		for (Map<Object, Object> preCreditParamMap : preCreditParamMaps) {
			preCreditParamMap.put("operationID", UUID.randomUUID().toString().replace("-", ""));
			if(preCreditParamMap.get("importDate")!=null&&!"".equals(preCreditParamMap.get("importDate"))){
				preCreditParamMap.put("importDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("importDate")));
			}
			if(preCreditParamMap.get("startDate")!=null&&!"".equals(preCreditParamMap.get("startDate"))){
				preCreditParamMap.put("startDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("startDate")));
			}
			if(preCreditParamMap.get("endDate")!=null&&!"".equals(preCreditParamMap.get("endDate"))){
				preCreditParamMap.put("endDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("endDate")));
			}
			if(preCreditParamMap.get("beginDate")!=null&&!"".equals(preCreditParamMap.get("beginDate"))){
				preCreditParamMap.put("beginDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("beginDate")));
			}
			if(preCreditParamMap.get("stopDate")!=null&&!"".equals(preCreditParamMap.get("stopDate"))){
				preCreditParamMap.put("stopDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("stopDate")));
			}
			if(preCreditParamMap.get("crtDate")!=null&&!"".equals(preCreditParamMap.get("crtDate"))){
				preCreditParamMap.put("crtDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("crtDate")));
			}
			if(preCreditParamMap.get("lstUpdDate")!=null&&!"".equals(preCreditParamMap.get("lstUpdDate"))){
				preCreditParamMap.put("lstUpdDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("lstUpdDate")));
			}
		}
		int result = preCreditParamDao.batchStartPreCreditParam(preCreditParamMaps);
		for (Map<Object, Object> preCreditParamMap : preCreditParamMaps) {
			preCreditParamMap.put("status","0");
		}
		preCreditParamDao.insertPreCreditParamHisOfBatch(preCreditParamMaps);
		return result;
	}
	//预授信信息的批量禁用
	@Override
	public int batchStopPreCreditParam(List<Map<Object,Object>> preCreditParamMaps) throws ParseException {
		for (Map<Object, Object> preCreditParamMap : preCreditParamMaps) {
			preCreditParamMap.put("operationID", UUID.randomUUID().toString().replace("-", ""));
			if(preCreditParamMap.get("importDate")!=null&&!"".equals(preCreditParamMap.get("importDate"))){
				preCreditParamMap.put("importDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("importDate")));
			}
			if(preCreditParamMap.get("startDate")!=null&&!"".equals(preCreditParamMap.get("startDate"))){
				preCreditParamMap.put("startDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("startDate")));
			}
			if(preCreditParamMap.get("endDate")!=null&&!"".equals(preCreditParamMap.get("endDate"))){
				preCreditParamMap.put("endDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("endDate")));
			}
			if(preCreditParamMap.get("beginDate")!=null&&!"".equals(preCreditParamMap.get("beginDate"))){
				preCreditParamMap.put("beginDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("beginDate")));
			}
			if(preCreditParamMap.get("stopDate")!=null&&!"".equals(preCreditParamMap.get("stopDate"))){
				preCreditParamMap.put("stopDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("stopDate")));
			}
			if(preCreditParamMap.get("crtDate")!=null&&!"".equals(preCreditParamMap.get("crtDate"))){
				preCreditParamMap.put("crtDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("crtDate")));
			}
			if(preCreditParamMap.get("lstUpdDate")!=null&&!"".equals(preCreditParamMap.get("lstUpdDate"))){
				preCreditParamMap.put("lstUpdDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) preCreditParamMap.get("lstUpdDate")));
			}
		}
		int result = preCreditParamDao.batchStopPreCreditParam(preCreditParamMaps);
		for (Map<Object, Object> preCreditParamMap : preCreditParamMaps) {
			preCreditParamMap.put("status","1");
		}
		preCreditParamDao.insertPreCreditParamHisOfBatch(preCreditParamMaps);
		return result;
	}
	//预授信信息的删除及批量删除
	@Override
	public int batchDeletePreCreditParam(List<String> autoIds) {
		return preCreditParamDao.batchDeletePreCreditParam(autoIds);
	}
	//批量导入
	@Override
	public int insertPreCreditParamImportFile(List<PreCreditParam> preCreditParamList, BatchfileInfo batchfileInfo) throws Exception {
		int inputCounts =  preCreditParamDao.insertPreCreditParamImportFile(preCreditParamList);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		preCreditParamDao.insertCredit(batchfileInfo);
		return inputCounts;
	}
	
	
	
	//点击查看超链接显示预授信信息历史纪录
	@Override
	public int queryPreCreditParamHistoryCount(String autoID) {
		return preCreditParamDao.queryPreCreditParamHistoryCount(autoID);
	}

	@Override
	public List<PreCreditParam> queryPreCreditParamHistoryList(String autoID, int curNum, int pageNum) {
		return preCreditParamDao.queryPreCreditParamHistoryList(autoID,curNum,pageNum);
	}

	@Override
	public int insertCredit(BatchfileInfo batchfileInfo) {
		return preCreditParamDao.insertCredit(batchfileInfo);
	}
	//预授信批量导入列表展示
	@Override
	public int queryPreCreditParamUploadCount(BatchfileInfo batchfileInfo) {
		return preCreditParamDao.queryPreCreditParamUploadCount(batchfileInfo);
	}

	@Override
	public List<BatchfileInfo> queryPreCreditParamUpload(BatchfileInfo batchfileInfo, int curNum, int pageNum) {
		return preCreditParamDao.queryPreCreditParamUpload(batchfileInfo,curNum,pageNum);
	}

	@Override
	public String queryPreCreditParamStatus(String autoID) {
		return preCreditParamDao.queryPreCreditParamStatus(autoID);
	}

	@Override
	public int updatePreCreditParamWithoutStatus(PreCreditParam preCreditParam) throws Exception {
		//对修改后的预授信信息进行校验
		PreCreditParamServiceImpl.validate(preCreditParam);
		PreCreditParam preCreditParamHis = preCreditParamDao.selectById(preCreditParam.getAutoID());
		int result = preCreditParamDao.updatePreCreditParamWithoutStatus(preCreditParam);
		preCreditParamHis.setOperationID(UUID.randomUUID().toString().replace("-", ""));
		preCreditParamDao.insertPreCreditParamHis(preCreditParamHis);
		return result;
	}

}
