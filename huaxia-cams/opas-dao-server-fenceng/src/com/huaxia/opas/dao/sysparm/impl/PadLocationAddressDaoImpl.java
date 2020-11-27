package com.huaxia.opas.dao.sysparm.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.PadLocationAddressDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.PadLocationAddress;
/**
 * PAD定位地址
 * @author wenyh
 *
 */
public class PadLocationAddressDaoImpl extends AbstractDAO  implements PadLocationAddressDao {
	private static final long serialVersionUID = 6497434023765964100L;
	private static final String NAMESPACES = "PadLocationAddress.";
	
	/**
	 * PAD定位地址信息的条数
	 * @param padLocationAddress
	 * @return
	 */
	@Override
	public int queryPadLocationAddressCount(PadLocationAddress padLocationAddress) {
		return  getSqlMap().queryForObject(NAMESPACES + "queryPadLocationAddressCount", padLocationAddress);	
	}
	
	/**
	 * 分页显示PAD定位地址信息
	 * @param padLocationAddress
	 * @param curNum
	 * @param pageNum
	 * @return
	 */
	@Override
	public List<PadLocationAddress> queryPadLocationAddressList(PadLocationAddress padLocationAddress, int curNum, int pageNum) {
		List<PadLocationAddress> list=new ArrayList<PadLocationAddress>();
		list= getSqlMap().queryForList(NAMESPACES + "queryPadLocationAddressList", padLocationAddress, curNum, pageNum);
		return list;
	}

	/**
	 * PAD定位地址信息的修改
	 */
	@Override
	public int updatePadLocationAddress(PadLocationAddress PadLocationAddress)  {
		return getSqlMap().update(NAMESPACES + "updatePadLocationAddress", PadLocationAddress);
	}
	
	/**
	 * 修改PAD定位地址信息（不修改启停状态）
	 */
	@Override
	public int updatePadLocationAddressWithoutStatus(PadLocationAddress padLocationAddress) {
		return getSqlMap().update(NAMESPACES+"updatePadLocationAddressWithoutStatus",padLocationAddress);
	}
	
	/**
	 * 新增时，设置状态为启用
	 */
	@Override
	public int insertPadLocationAddressStart(PadLocationAddress padLocationAddress) {
		return getSqlMap().insert(NAMESPACES + "insertPadLocationAddressStart", padLocationAddress);
	}
	
	/**
	 * 新增时设置状态为禁用
	 */
	@Override
	public int insertPadLocationAddressEnd(PadLocationAddress padLocationAddress) {
		return getSqlMap().insert(NAMESPACES + "insertPadLocationAddressEnd", padLocationAddress);
	}
	
	/**
	 * 批量启用
	 */
	@Override
	public int batchStartPadLocationAddress(List<Map<Object,Object>> padLocationAddressMaps) {
		return getSqlMap().update(NAMESPACES + "batchStartPadLocationAddress", padLocationAddressMaps);
	}
	
	/**
	 * 批量禁用
	 */
	@Override
	public int batchStopPadLocationAddress(List<Map<Object,Object>> padLocationAddressMaps) {
		return getSqlMap().update(NAMESPACES + "batchStopPadLocationAddress", padLocationAddressMaps);
	}
	
	/**
	 * 批量删除
	 */
	@Override
	public int batchDeletePadLocationAddress(List<String> autoIds) {
		return getSqlMap().delete(NAMESPACES + "batchDeletePadLocationAddress", autoIds);
	}
	
	/**
	 * 批量导入
	 */
	@Override
	public int insertPadLocationAddressImportFile(List<PadLocationAddress> padLocationAddressList) {
		return getSqlMap().insert(NAMESPACES + "insertPadLocationAddressImportFile", padLocationAddressList);
	}
	
	/**
	 * 导入成功时插入PAD定位地址excel文件历史信息记录表
	 */
	@Override
	public int insertPLA(BatchfileInfo batchfileinfo) {
		return  getSqlMap().insert(NAMESPACES + "insertPLA", batchfileinfo);
	}

	/**
	 * 根据autoID查询启停状态
	 */
	@Override
	public String queryPadLocationAddressStatus(String autoID) {
		return getSqlMap().queryForObject(NAMESPACES+"queryPadLocationAddressStatus",autoID);
	}

}
