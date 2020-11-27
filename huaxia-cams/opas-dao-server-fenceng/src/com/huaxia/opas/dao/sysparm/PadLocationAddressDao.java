package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.PadLocationAddress;
/**
 * PAD定位地址
 * @author wenyh
 *
 */
public interface PadLocationAddressDao {
	/**
	 * PAD定位地址信息的条数
	 * @param padLocationAddress
	 * @return
	 */
	public int queryPadLocationAddressCount(PadLocationAddress padLocationAddress);
	
	/**
	 * 分页显示PAD定位地址信息
	 * @param padLocationAddress
	 * @param curNum
	 * @param pageNum
	 * @return
	 */
	public List<PadLocationAddress> queryPadLocationAddressList(PadLocationAddress padLocationAddress, int curNum, int pageNum);

	/**
	 * PAD定位地址信息修改
	 * @param padLocationAddress
	 * @return
	 */
	public int updatePadLocationAddress(PadLocationAddress padLocationAddress) ;
	
	/**
	 * 修改PAD定位地址信息（不修改启停状态）
	 */
	public int updatePadLocationAddressWithoutStatus(PadLocationAddress padLocationAddress);

	/**
	 * 新增时，设置状态为启用
	 * @param padLocationAddress
	 * @return
	 */
	public int insertPadLocationAddressStart(PadLocationAddress padLocationAddress);
	
	/**
	 * 新增时，设置状态为禁用
	 * @param padLocationAddress
	 * @return
	 */
	public int insertPadLocationAddressEnd(PadLocationAddress padLocationAddress);
	
	/**
	 * 批量启用PAD定位地址信息
	 * @param padLocationAddressMaps
	 * @return
	 */
	public int batchStartPadLocationAddress(List<Map<Object,Object>> padLocationAddressMaps);
	
	/**
	 * 批量禁用PAD定位地址信息
	 * @param padLocationAddressMaps
	 * @return
	 */
	public int batchStopPadLocationAddress(List<Map<Object,Object>> padLocationAddressMaps);
	
	/**
	 * 批量删除PAD定位地址信息
	 * @param autoIds
	 * @return
	 */
	public int batchDeletePadLocationAddress(List<String> autoIds);
	
	/**
	 * 批量导入
	 * @param padLocationAddressList
	 * @return
	 */
	public int insertPadLocationAddressImportFile(List<PadLocationAddress> padLocationAddressList);
	
	/**
	 * 导入成功时插入PAD定位地址excel文件历史信息记录表
	 */
	public int insertPLA(BatchfileInfo batchfileinfo);

	/**
	 * 查询PAD定位地址的修改前的状态
	 */
	public String queryPadLocationAddressStatus(String autoID);
	
}
