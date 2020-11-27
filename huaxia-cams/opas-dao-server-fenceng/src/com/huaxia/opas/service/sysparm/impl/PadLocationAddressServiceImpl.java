package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.dao.sysparm.PadLocationAddressDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.PadLocationAddress;
import com.huaxia.opas.service.sysparm.PadLocationAddressService;
/**
 * PAD定位地址
 * @author wenyh
 *
 */
public class PadLocationAddressServiceImpl implements PadLocationAddressService, Serializable {
	protected static Logger logger = LoggerFactory.getLogger(PadLocationAddressServiceImpl.class);
	@Resource(name = "padLocationAddressDao")
	private PadLocationAddressDao padLocationAddressDao;	
	
	/**
	 * PAD定位地址信息的后台校验
	 * @param padLocationAddress
	 * @throws Exception
	 */
	public static void validate(PadLocationAddress padLocationAddress) throws Exception{
		 if(padLocationAddress.getCityID() == null||"".equals(padLocationAddress.getCityID().trim())){
			 logger.error("padLocationAddressServiceImpl PAD定位地址的service实现类的城市ID cityID不能为空");
			 throw new Exception("城市ID不能为空");
		 }
		 if(padLocationAddress.getCityID().length() > 32){
			 logger.error("padLocationAddressServiceImpl PAD定位地址的service实现类的城市ID cityID长度不能超过32位");	 
			 throw new Exception("城市ID长度不能超过32位");
		 }
		 if(padLocationAddress.getPadLocationAddress() == null || "".equals(padLocationAddress.getPadLocationAddress().trim())){
			 logger.error("padLocationAddressServiceImpl PAD定位地址的service实现类的现有PAD端传输的定位地址 padLocationAddress不能为空");
			 throw new Exception("现有PAD端传输的定位地址不能为空");
		 }
		 if(padLocationAddress.getPadLocationAddress().length() > 32){
			 logger.error("padLocationAddressServiceImpl PAD定位地址的service实现类的现有PAD端传输的定位地址 padLocationAddress长度不能超过32位");
			 throw new Exception("现有PAD端传输的定位地址不能超过32位");
		 }
		 if(padLocationAddress.getStatus() == null || "".equals(padLocationAddress.getStatus().trim())){
			 logger.error("padLocationAddressServiceImpl PAD定位地址的service实现类的启用状态 status不能为空");
			 throw new Exception("启用状态不能为空");
		 }
	}
	
	/**
	 * PAD定位地址信息的条数
	 * @param padLocationAddress
	 * @return
	 */
	@Override
	public int queryPadLocationAddressCount(PadLocationAddress padLocationAddress) {
		return padLocationAddressDao.queryPadLocationAddressCount(padLocationAddress);
	}
	
	/**
	 * 分页显示PAD定位地址信息
	 * @param padLocationAddress
	 * @param curNum
	 * @param pageNum
	 * @return
	 */
	@Override
	public List<PadLocationAddress> queryPadLocationAddressList(
			PadLocationAddress padLocationAddress, int curNum, int pageNum) {
		return padLocationAddressDao.queryPadLocationAddressList(padLocationAddress,
				curNum, pageNum);
	}
	
	/**
	 * 当新增时，设置状态为启用
	 */
	@Override
	public int insertPadLocationAddressStart(PadLocationAddress padLocationAddress) throws Exception {
		//对新增的PAD定位地址信息进行校验
		PadLocationAddressServiceImpl.validate(padLocationAddress);
		return padLocationAddressDao.insertPadLocationAddressStart(padLocationAddress);
	}
	
	/**
	 * 新增时，设置状态为禁用
	 */
	@Override
	public int insertPadLocationAddressEnd(PadLocationAddress padLocationAddress) throws Exception {
		//对新增的PAD定位地址信息进行校验
		PadLocationAddressServiceImpl.validate(padLocationAddress);
		return padLocationAddressDao.insertPadLocationAddressEnd(padLocationAddress);
	}
	
	/**
	 * PAD定位地址信息的修改
	 * @param padLocationAddress
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updatePadLocationAddress(PadLocationAddress padLocationAddress) throws Exception {
		//对修改后的PAD定位地址信息进行校验
		PadLocationAddressServiceImpl.validate(padLocationAddress);
		return padLocationAddressDao.updatePadLocationAddress(padLocationAddress);
	}
	
	/**
	 * 修改PAD定位地址信息（不修改启停状态）
	 */
	@Override
	public int updatePadLocationAddressWithoutStatus(PadLocationAddress padLocationAddress) throws Exception {
		//对修改后的PAD定位地址信息进行校验
		PadLocationAddressServiceImpl.validate(padLocationAddress);
		return padLocationAddressDao.updatePadLocationAddressWithoutStatus(padLocationAddress);
	}
	
	/**
	 * PAD定位地址信息的批量启用
	 */
	@Override
	public int batchStartPadLocationAddress(List<Map<Object,Object>> padLocationAddressMaps) throws ParseException {
		for (Map<Object, Object> padLocationAddressMap : padLocationAddressMaps) {
			if(padLocationAddressMap.get("importDate")!=null&&!"".equals(padLocationAddressMap.get("importDate"))){
				padLocationAddressMap.put("importDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) padLocationAddressMap.get("importDate")));
			}
			if(padLocationAddressMap.get("crtDate")!=null&&!"".equals(padLocationAddressMap.get("crtDate"))){
				padLocationAddressMap.put("crtDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) padLocationAddressMap.get("crtDate")));
			}
			if(padLocationAddressMap.get("lstUpdDate")!=null&&!"".equals(padLocationAddressMap.get("lstUpdDate"))){
				padLocationAddressMap.put("lstUpdDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) padLocationAddressMap.get("lstUpdDate")));
			}
		}
		return padLocationAddressDao.batchStartPadLocationAddress(padLocationAddressMaps);
	}
	
	/**
	 * PAD定位地址信息的批量禁用
	 */
	@Override
	public int batchStopPadLocationAddress(List<Map<Object,Object>> padLocationAddressMaps) throws ParseException {
		for (Map<Object, Object> padLocationAddressMap : padLocationAddressMaps) {
			if(padLocationAddressMap.get("importDate")!=null&&!"".equals(padLocationAddressMap.get("importDate"))){
				padLocationAddressMap.put("importDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) padLocationAddressMap.get("importDate")));
			}
			if(padLocationAddressMap.get("crtDate")!=null&&!"".equals(padLocationAddressMap.get("crtDate"))){
				padLocationAddressMap.put("crtDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) padLocationAddressMap.get("crtDate")));
			}
			if(padLocationAddressMap.get("lstUpdDate")!=null&&!"".equals(padLocationAddressMap.get("lstUpdDate"))){
				padLocationAddressMap.put("lstUpdDate", new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.ENGLISH).parse((String) padLocationAddressMap.get("lstUpdDate")));
			}
		}
		return padLocationAddressDao.batchStopPadLocationAddress(padLocationAddressMaps);
	}
	
	/**
	 * PAD定位地址信息的删除及批量删除
	 */
	@Override
	public int batchDeletePadLocationAddress(List<String> autoIds) {
		return padLocationAddressDao.batchDeletePadLocationAddress(autoIds);
	}
	
	/**
	 * 批量导入
	 */
	@Override
	public int insertPadLocationAddressImportFile(List<PadLocationAddress> padLocationAddressList, BatchfileInfo batchfileInfo) throws Exception {
		int inputCounts =  padLocationAddressDao.insertPadLocationAddressImportFile(padLocationAddressList);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		padLocationAddressDao.insertPLA(batchfileInfo);
		return inputCounts;
	}
	
	/**
	 * 导入成功时插入PAD定位地址excel文件历史信息记录表
	 */
	@Override
	public int insertPLA(BatchfileInfo batchfileInfo) {
		return padLocationAddressDao.insertPLA(batchfileInfo);
	}
	
	/**
	 * 根据autoID查询启停状态
	 */
	@Override
	public String queryPadLocationAddressStatus(String autoID) {
		return padLocationAddressDao.queryPadLocationAddressStatus(autoID);
	}

	

}
