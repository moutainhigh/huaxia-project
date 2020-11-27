package com.huaxia.opas.action.collect;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.cache.service.address.AddresMachLevService;
import com.huaxia.opas.common.SysConst;
import com.huaxia.opas.domain.collect.EnhanceInfo;
import com.huaxia.opas.domain.collect.SalaryAdoptItem;
import com.huaxia.opas.domain.collect.SalaryCompute;
import com.huaxia.opas.service.apply.ApplyInfoService;
import com.huaxia.opas.service.collect.EnhanceCollectService;
import com.huaxia.opas.service.collect.InfoCollectService;
import com.huaxia.opas.service.collect.SalaryAdoptItemService;
import com.huaxia.opas.service.collect.SalaryComputeService;
import com.huaxia.opas.service.fico.FicoService;
import com.huaxia.opas.service.sysparm.MBASchoolService;
import com.huaxia.opas.service.thirdparty.EducationService;
import com.huaxia.opas.util.SequenceUtil;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * 增强信息采集
 * 
 * @author zhiguo.li
 *
 */
public class EnhanceCollectAction implements Action {

	private static Logger logger = Logger.getLogger(EnhanceCollectAction.class);

	@Resource(name = "enhanceCollectService")
	private EnhanceCollectService enhanceCollectService;

	@Resource(name = "salaryComputeService")
	private SalaryComputeService salaryComputeService;

	@Resource(name = "salaryAdoptItemService")
	private SalaryAdoptItemService salaryAdoptItemService;

	@Resource(name = "MBASchoolService")
	private MBASchoolService MBASchoolService;

	@Resource(name = "applyInfoService")
	private ApplyInfoService applyInfoService;

	@Resource(name = "addressMachLevService")
	private AddresMachLevService addressMachLevService;

	@Resource(name = "ficoService")
	private FicoService ficoService;

	@Resource(name = "infoCollectService")
	private InfoCollectService infoCollectService;
	// 学历服务接口
	@Resource(name = "educationService")
	private EducationService educationService;

	/** 存储器定义 */
	public static final String STORAGE = "storage";

	/** 计数器定义 */
	public static final String COUNTER = "counter";

	/** 默认日期格式化样式 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	public DateFormat dateFormat;

	public EnhanceCollectAction() {
		setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT));
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * 加载增强采集信息
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void loadEnhanceCollectInfo(Context context) throws Exception {
		String appId = context.getData("appId");

		EnhanceInfo enhanceInfo = getEnhanceCollectService().getByAppId(appId) == null?new EnhanceInfo():getEnhanceCollectService().getByAppId(appId);
		SalaryCompute computeInfo = getSalaryComputeService().getByAppId(appId);
		SalaryAdoptItem adoptInfo = new SalaryAdoptItem();
		int coyr  = getEnhanceCollectService().selectC1CoyrByAppId(appId);
		if(coyr!=0){		
			Date entryYear = getEnhanceCollectService().selectEntryYearByAppId(appId);
			if(enhanceInfo.getCompanyEntryDate()==null||"".equals(enhanceInfo.getCompanyEntryDate())){
				enhanceInfo.setCompanyEntryDate(entryYear);
			}
		}
		// 地址行政级别匹配
		Map<String, String> applyCompanyInfo = getApplyInfoService().queryApplyCompanyInfo(appId);
		
		//5151公积金基数查询
		String depositBase = getSalaryAdoptItemService().selectDepositBaseByAppId(appId);
		
		//51公积金月缴额查询
		String depositAmount = getSalaryAdoptItemService().selectDepositAmountByAppId(appId);
		
		Double ndepositBase = null ;
		Double ndepositAmount = null;
		
		 

		// 不能获取"是否事业单位"，默认送0。
		String workType = "0";
		String address = null;
		String companyName = null;
		if (applyCompanyInfo != null) {
			workType = applyCompanyInfo.get("C1_COCODE");
			address = applyCompanyInfo.get("C1_COADDRESS");
			companyName = applyCompanyInfo.get("C1_CONAME");
		}

		if (logger.isInfoEnabled()) {
			logger.info("申请件[" + appId + "]地址行政级别是否事业单位：" + workType);
		}

		String level = getAddressMachLevService().getMachResult(workType, address, companyName);
		if (level != null && !"".equals(level)) {
			level = "0" + level;
		} else {
			level = "05";
		}
		
		
		if (logger.isInfoEnabled()) {
			logger.info("地址行政级别匹配结果：" + level);
		}

		// 初始申请件操作标志
		boolean initLoad = false;
		String type = getEnhanceCollectService().getRequestTypeByAppId(appId);
		if (type !=null ) {
			initLoad = false;
			
			if (enhanceInfo.getExecutiveLevel() == null || "".equals(enhanceInfo.getExecutiveLevel())) {
				enhanceInfo.setExecutiveLevel("05");
			}
			
			String adoptItemId = enhanceInfo.getAdoptItemId();
			
			if (adoptItemId != null) {
				SalaryAdoptItem searchObject = new SalaryAdoptItem();
				searchObject.setAdoptItemId(adoptItemId);
				adoptInfo = getSalaryAdoptItemService().get(searchObject);
				
				String adoptObject = JSON.toJSONStringWithDateFormat(adoptInfo, "yyyy-MM-dd",
						SerializerFeature.WriteDateUseDateFormat);
				context.setData("salaryAdoptItemInfo", adoptObject);
			}
			
			String enhanceObject = JSON.toJSONStringWithDateFormat(enhanceInfo, "yyyy-MM-dd",
					SerializerFeature.WriteDateUseDateFormat);
			String computeObject = JSON.toJSONStringWithDateFormat(computeInfo, "yyyy-MM-dd",
					SerializerFeature.WriteDateUseDateFormat);

			context.setData("initLoad", initLoad);
			context.setData("enhanceCollectInfo", enhanceObject);
			context.setData("salaryComputeInfo", computeObject);
			
		} else {
			initLoad = true;

			if (level != null && !"".equals(level)) {
				enhanceInfo.setExecutiveLevel(level);
			}
			// 未匹配到任何值则默认"其他"
			else {
				enhanceInfo.setExecutiveLevel("05");
			}
			String enhanceObject = JSON.toJSONStringWithDateFormat(enhanceInfo, "yyyy-MM-dd",
					SerializerFeature.WriteDateUseDateFormat);
			context.setData("enhanceCollectInfo", enhanceObject);
			String adoptItemId = enhanceInfo.getAdoptItemId();
			if (adoptItemId != null) {
				SalaryAdoptItem searchObject = new SalaryAdoptItem();
				searchObject.setAdoptItemId(adoptItemId);
				adoptInfo = getSalaryAdoptItemService().get(searchObject);
				adoptInfo.setFundBase(ndepositBase);
				adoptInfo.setFundMonthPay(ndepositAmount);
				String adoptObject = JSON.toJSONStringWithDateFormat(adoptInfo, "yyyy-MM-dd",
						SerializerFeature.WriteDateUseDateFormat);
				context.setData("salaryAdoptItemInfo", adoptObject);
			}
			if(depositBase != null){
				
				if(depositBase != null){				
					ndepositBase = Double.parseDouble(depositBase);
					adoptInfo.setFundBase(ndepositBase);
				}
				if(depositAmount != null){
					ndepositAmount = Double.parseDouble(depositAmount);
					adoptInfo.setFundMonthPay(ndepositAmount);
				}
				String adoptObject = JSON.toJSONStringWithDateFormat(adoptInfo, "yyyy-MM-dd",
					SerializerFeature.WriteDateUseDateFormat);
				context.setData("salaryAdoptItemInfo", adoptObject);
			}

			// 申请件初始加载时查询第三方学历信息并进行反显
			Map<String, String> educationInfo = getEducationService().selectDetailInfo(appId);
			if (educationInfo != null) {
				context.setData("educationInfo", JSON.toJSONString(educationInfo));
			}
			//用于接收区域公积金中“满足区域公积金收入要求”判断为“是”的“区域公积金月收入”
			Map<String,String> accfundincome = new HashMap<>();
			accfundincome.put("accfundincomexm", "");//默认没有满足区域公积金收入要求”判断为“是”的
			//申请件初始加载时查询易达金决策表中“区域公积金月收入”信息并反显（目前查的是厦门和银川）
			Map<String,String> fundInfo = infoCollectService.queryRegionalReserveFundByAppid(appId);
			/**
			对于“是否满足厦门区域公积金收入要求”判断为：“是”的，决策将“厦门区域公积金月收入”值反显至审核系统增强页面“公积金基数”栏位旁边，
			提示：厦门区域公积金月收入（区域数据）：XX元。
			由于后面会接入其他的公积金数据，要求在页面上显示的是“区域公积金月收入”
			 */	
			/*if(fundInfo != null && fundInfo.get("isaccfundincomexm") != null){
				if("N".equals(fundInfo.get("isaccfundincomexm")) || 
				   "n".equals(fundInfo.get("isaccfundincomexm"))){
					fundInfo.put("accfundincomexm", "");
				}
			}*/
			//厦门区域公积金后又接入银川区域公积金，因此需要判断是哪个地区公积金有值，有值的返现到页面上（只可能其中一个公积金有值）
			if(fundInfo != null){
				if(fundInfo.get("isaccfundincomexm") != null){//厦门
					if("Y".equals(fundInfo.get("isaccfundincomexm")) || 
					   "y".equals(fundInfo.get("isaccfundincomexm"))){
						accfundincome.put("accfundincomexm", String.valueOf(fundInfo.get("accfundincomexm")));
					}
				}else if(fundInfo.get("isaccfundincomeyc") != null){//银川
					if("Y".equals(fundInfo.get("isaccfundincomeyc")) || 
					   "y".equals(fundInfo.get("isaccfundincomeyc"))){
						accfundincome.put("accfundincomexm", String.valueOf(fundInfo.get("accfundincomeyc")));
					}
				}
			}
			String fundInfoObject = JSON.toJSONStringWithDateFormat(accfundincome, "yyyy-MM-dd",
					SerializerFeature.WriteDateUseDateFormat);
			context.setData("fundInfo", fundInfoObject);
			Map <String,String> codeInfo = null;
			Map<String, Object> map = context.getDataMap();
			if(appId.substring(15, 16).equals("1")){
				map.put("appId",appId.substring(0, 15)+"2");
				codeInfo = infoCollectService.queryEnteringPageNeedDataByAppId(map);
				//map.put("appId",appId.substring(0, 15)+"1");
			}else{
				map.put("appId",appId.substring(0, 15)+"1");
				codeInfo = infoCollectService.queryEnteringPageNeedDataByAppId(map);
				//map.put("appId",appId.substring(0, 15)+"2");
			}
			String codeObject = JSON.toJSONStringWithDateFormat(codeInfo, "yyyy-MM-dd",
					SerializerFeature.WriteDateUseDateFormat);
			context.setData("initLoad", initLoad);
			context.setData("codeInfo", codeObject);
		}

		if (logger.isInfoEnabled()) {
			logger.info("[易达金-增强采集] 是否初始申请件？" + initLoad);
		}
		context.setData("success", true);
	}

	// 根据交易码获取交易执行结果信息
	private String getTrnSaveResult(int code) {
		String message = null;
		switch (code) {
		case 1:
			message = "1-交易保存成功（增强采集信息）";
			break;
		case 2:
			message = "2-交易保存成功（增强采集信息+可采纳项信息）";
			break;
		case 4:
			message = "4-交易保存成功（增强采集信息+可采纳项信息+收入计算信息）";
			break;
		default:
			message = "0-交易保存失败";
		}
		return message;
	}

	// 根据交易码获取交易执行结果信息
	private String getTrnMergeResult(int code) {
		String message = null;
		switch (code) {
		case 1:
			message = "1-交易合并成功（增强采集信息）";
			break;
		case 21:
			message = "2-1-交易保存成功（增强采集信息+可采纳项信息）";
			break;
		case 22:
			message = "2-2-交易合并成功（增强采集信息+可采纳项信息）";
			break;
		case 41:
			message = "4-1-交易保存成功（增强采集信息+可采纳项信息+收入计算信息）";
			break;
		case 42:
			message = "4-2-交易保存成功（增强采集信息+可采纳项信息+收入计算信息）";
			break;
		default:
			message = "0-交易合并失败";
		}
		return message;
	}

	/**
	 * 保存增强采集信息
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void saveEnhanceCollectInfo(Context context) throws Exception {
		JSONObject jsonObject = JSONObject.fromObject(context.getDataMap());

		// 日期格式化设置
		String[] formats = new String[] { DEFAULT_DATE_FORMAT };
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(formats));

		int num = 0;
		// String userId = context.getUser().getUserId();

		// 申请件编号
		String appId = jsonObject.getString("appId");

		if (logger.isInfoEnabled()) {
			logger.info("[标准易达金增强采集][saveEnhanceCollectInfo] 申请件编号:" + appId);
		}

		// 实体映射转化
		if ("".equals(jsonObject.get("companyEstablishDate"))) {
			jsonObject.remove("companyEstablishDate");
		}

		if ("".equals(jsonObject.get("companyEntryDate"))) {
			jsonObject.remove("companyEntryDate");
		}

		EnhanceInfo enhanceObject = (EnhanceInfo) JSONObject.toBean(jsonObject, EnhanceInfo.class);
		// enhanceObject.setCreateUser(userId);
		// enhanceObject.setLastUpdateUser(userId);

		SalaryAdoptItem adoptObject = (SalaryAdoptItem) JSONObject.toBean(jsonObject, SalaryAdoptItem.class);
		// adoptObject.setCreateUser(userId);
		// adoptObject.setLastUpdateUser(userId);

		SalaryCompute computeObject = new SalaryCompute();
		// computeObject.setCreateUser(userId);
		// computeObject.setLastUpdateUser(userId);

		// 判断是否已经存在该申请件记录
		// save or update & merge
		EnhanceInfo enhanceEntity = getEnhanceCollectService().getByAppId(appId);
		if (enhanceEntity == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			// 增强采集信息
			enhanceObject.setInsideAppNo(SequenceUtil.getUUID());

			// 判断是否勾选可采纳项信息
			if (jsonObject.containsKey("adoptItem")) {
				String adoptItemId = SequenceUtil.getUUID();
				adoptObject.setAdoptItemId(adoptItemId);
				enhanceObject.setAdoptItemId(adoptItemId);
				map.put(SysConst.YDJ_OBJECT_ADOPT, adoptObject);
			}

			// 判断是否执行表格计算（收入信息）
			if (jsonObject.containsKey(STORAGE)) {
				computeObject.setInsideAppNo(SequenceUtil.getUUID());
				computeObject.setAppId(appId);
				computeObject.setStorage(jsonObject.getString(STORAGE));
				computeObject.setCounter(jsonObject.getString(COUNTER));
				map.put(SysConst.YDJ_OBJECT_COMPUTE, computeObject);
			}

			map.put(SysConst.YDJ_OBJECT_ENHANCE, enhanceObject);
			num = getEnhanceCollectService().saveAll(map);
			if (num == -1) {
				context.setData("success", false);
				return;
			} else {
				context.setData("success", true);
				context.setData("message", getTrnSaveResult(num));
				return;
			}
		} else {
			// 收入可采纳项主键（关联）
			String adoptItemId = enhanceEntity.getAdoptItemId();
			
			// 判断是否勾选可采纳项信息
			Map<String, Object> map = new HashMap<String, Object>();
			if (jsonObject.containsKey("adoptItem")) {
				if (enhanceEntity.getAdoptItemId() == null) {
					adoptItemId = SequenceUtil.getUUID();
					adoptObject.setAdoptItemId(adoptItemId);
				} else {
					adoptObject.setAdoptItemId(adoptItemId);
				}
				map.put(SysConst.YDJ_OBJECT_ADOPT, adoptObject);
			}
			
			// 与增强采集信息建立关联
			enhanceObject.setAdoptItemId(adoptItemId);
			map.put(SysConst.YDJ_OBJECT_ENHANCE, enhanceObject);
			
			// 判断是否进行表格计算
			if (jsonObject.containsKey(STORAGE)) {
				SalaryCompute computeEntity = getSalaryComputeService().getByAppId(appId);
				if (computeEntity == null) {
					computeObject.setInsideAppNo(SequenceUtil.getUUID());
				}
				computeObject.setAppId(appId);
				computeObject.setStorage(jsonObject.getString(STORAGE));
				computeObject.setCounter(jsonObject.getString(COUNTER));
				map.put(SysConst.YDJ_OBJECT_COMPUTE, computeObject);
			}

			num = getEnhanceCollectService().mergeAll(map);
			if (num == -1) {
				context.setData("success", false);
				return;
			} else {
				context.setData("success", true);
				context.setData("message", getTrnMergeResult(num));
				return;
			}
		}
	}

	/**
	 * 调用BLAZE接口获取增强信息计算结果
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void getEnhanceCollectFromBlaze(Context context) throws Exception {
		JSONObject jsonParams = JSONObject.fromObject(context.getDataMap());
		String appId = jsonParams.getString("appId");

		if (logger.isDebugEnabled()) {
			logger.debug("调用BLAZE接口[" + appId + "]");
		}

		// 调用BLAZE接口[YDJ1008]
		String result = getFicoService().ficoRequest(SysConst.STRATEGY_TYPE_YDJ_1008, appId, "1");

		if (logger.isDebugEnabled()) {
			logger.debug("调用BLAZE接口响应结果[" + result + "]");
		}

		// result =
		// "{'spouseMonthIncome':'12.4563','pbocCreditDebt':'78.5412','pbocUnmontgageOpen':'98.2354','adoptMonthIncome':'63.5412','houseMonthIncome':'36.5413','workMonthIncome':'95.3654','fundMonthIncome':'41.5684','remark':'这个是备注内容','custGroupType':'49.2513','certifiMaterial':'证明材料一,证明材料二,证明材料三,证明材料四,证明材料五','pbocMontgageDebt':'19.5413','realMonthIncome':'85.6482','creditOpen':'81.6425','appId':'1234567890123456','pbocUnMontgageDebt':'94.2516','rankMonthIncome':'743.5132','taxMonthIncome':'945.1252','socialSecurityMonthIncome':'64.2589','realGroup':'46.1283','salaryMonthIncome':'64.8916'}";

		// 易达金调用BLAZE规则响应结果
		if (result == null) {
			context.setData("success", false);
		} else {
			context.setData("success", true);
		}
		context.setData("blazeResult", result);
		return;
	}

	/**
	 * 加载MBA名单库
	 * 
	 * @param ctx
	 */
	public void loadAllMBASchool(Context ctx) {
		List<Map<String, String>> schools = getMBASchoolService().queryAllMBASchool();
		if (schools != null) {
			JSONArray jsonArray = JSONArray.fromObject(schools);
			ctx.setData("success", true);
			ctx.setData("MBASchool", jsonArray.toString());
			return;
		}
		ctx.setData("success", false);
	}

	// 构造调用BLAZE增强采集计算信息
	// 格式：JSON
	private String buildEnhanceJSON(JSONObject jsonObject) {
		if (jsonObject == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("构造BLAZE方法传参为空");
			}
		}

		// 判断是否存在数据存储器
		JSONArray storage = null;
		Map<String, BigDecimal> blaze = new HashMap<String, BigDecimal>();
		if (jsonObject.containsKey(STORAGE)) {
			String jsonStorage = jsonObject.getString(STORAGE);
			JSONArray jsonArray = JSONArray.fromObject(jsonStorage);
			storage = (JSONArray) ((JSONArray) jsonArray.get(1)).get(0);
			logger.debug(storage.toString());
		}

		return storage == null ? null : storage.toString();
	}

	public EnhanceCollectService getEnhanceCollectService() {
		return enhanceCollectService;
	}

	public void setEnhanceCollectService(EnhanceCollectService enhanceCollectService) {
		this.enhanceCollectService = enhanceCollectService;
	}

	public SalaryComputeService getSalaryComputeService() {
		return salaryComputeService;
	}

	public void setSalaryComputeService(SalaryComputeService salaryComputeService) {
		this.salaryComputeService = salaryComputeService;
	}

	public SalaryAdoptItemService getSalaryAdoptItemService() {
		return salaryAdoptItemService;
	}

	public void setSalaryAdoptItemService(SalaryAdoptItemService salaryAdoptItemService) {
		this.salaryAdoptItemService = salaryAdoptItemService;
	}

	public MBASchoolService getMBASchoolService() {
		return MBASchoolService;
	}

	public void setMBASchoolService(MBASchoolService mBASchoolService) {
		MBASchoolService = mBASchoolService;
	}

	public ApplyInfoService getApplyInfoService() {
		return applyInfoService;
	}

	public void setApplyInfoService(ApplyInfoService applyInfoService) {
		this.applyInfoService = applyInfoService;
	}

	public AddresMachLevService getAddressMachLevService() {
		return addressMachLevService;
	}

	public void setAddressMachLevService(AddresMachLevService addressMachLevService) {
		this.addressMachLevService = addressMachLevService;
	}

	public FicoService getFicoService() {
		return ficoService;
	}

	public void setFicoService(FicoService ficoService) {
		this.ficoService = ficoService;
	}

	public EducationService getEducationService() {
		return educationService;
	}

	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}

}
