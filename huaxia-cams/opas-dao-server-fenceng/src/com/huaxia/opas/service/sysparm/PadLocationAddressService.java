package com.huaxia.opas.service.sysparm;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.PadLocationAddress;

/**
 * PAD定位地址
 * @author wenyh
 *
 */
public interface PadLocationAddressService {
	/**
	 * PAD定位地址信息的条数
	 * @param padLocationAddress
	 * @return
	 */
	int queryPadLocationAddressCount(PadLocationAddress padLocationAddress);

	/**
	 * 分页显示PAD定位地址信息
	 * @param padLocationAddress
	 * @param curNum
	 * @param pageNum
	 * @return
	 */
	List<PadLocationAddress> queryPadLocationAddressList(PadLocationAddress padLocationAddress, int curNum, int pageNum);

	/**
	 * 当新增时，用户设置状态为启用
	 * @param padLocationAddress
	 * @return
	 * @throws Exception
	 */
	int insertPadLocationAddressStart(PadLocationAddress padLocationAddress) throws Exception;

	/**
	 * 当新增时，用户设置状态为停用
	 * @param padLocationAddress
	 * @return
	 * @throws Exception
	 */
	int insertPadLocationAddressEnd(PadLocationAddress padLocationAddress) throws Exception;

	/**
	 * PAD定位地址的修改
	 * @param padLocationAddress
	 * @return
	 * @throws Exception
	 */
	int updatePadLocationAddress(PadLocationAddress padLocationAddress) throws Exception;

	/**
	 * PAD定位地址的批量启用
	 * @param padLocationAddressMaps
	 * @return
	 * @throws ParseException
	 * @throws java.text.ParseException 
	 */
	int batchStartPadLocationAddress(List<Map<Object, Object>> padLocationAddressMaps) throws ParseException, java.text.ParseException;

	/**
	 * PAD定位地址的批量禁用
	 * @param padLocationAddressMaps
	 * @return
	 * @throws ParseException
	 */
	int batchStopPadLocationAddress(List<Map<Object, Object>> padLocationAddressMaps) throws ParseException;

	/**
	 * PAD定位地址的批量删除
	 * @param autoIds
	 * @return
	 */
	int batchDeletePadLocationAddress(List<String> autoIds);

	/**
	 * PAD定位地址的批量导入
	 * @param PadLocationAddressList
	 * @param batchfileInfo
	 * @return
	 * @throws Exception
	 */
	int insertPadLocationAddressImportFile(List<PadLocationAddress> PadLocationAddressList, BatchfileInfo batchfileInfo)
			throws Exception;

	/**
	 * 导入成功时插入PAD定位地址excel文件历史信息记录表
	 * @param batchfileInfo
	 * @return
	 */
	int insertPLA(BatchfileInfo batchfileInfo);

	/**
	 * 查询修改前PAD定位地址的状态
	 * @param autoID
	 * @return
	 */
	String queryPadLocationAddressStatus(String autoID);

	/**
	 * 修改PAD定位地址信息（启用状态没变）
	 * @param padLocationAddress
	 * @return
	 * @throws Exception
	 */
	int updatePadLocationAddressWithoutStatus(PadLocationAddress padLocationAddress) throws Exception;
}
