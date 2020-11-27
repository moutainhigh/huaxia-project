package com.huaxia.plaze.ui.unicom.service;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.unicom.domain.AddressData;
import com.huaxia.plaze.ui.unicom.domain.AddressSingle;

public interface AddressSingleService {
	/**
	 * 根据手机号查询数据解析表，判断24小时内是否查询过
	 * @param mobile 手机号
	 * @return 大于0 表示24小时内查询过
	 */
	int queryCountByMobile(Map args);
	/**
	 * 保存单条实时查询记录
	 * @param addressSingle
	 * @return
	 */
	int save(AddressSingle addressSingle);
	/**
	 * 更新单条查询表  查询状态
	 * @param trnId 交易编码
	 * @param string 单条查询状态
	 * @return
	 */
	int updateStatusById(String trnId, String string);
	/**
	 * 查询单条实时查询的结果
	 * @param trnId
	 * @return
	 */
	AddressData querySingleResultByTrnid(String trnId);
	/**
	 * 查询历史记录总条数
	 * @param mobile
	 * @return
	 */
	int queryHistoryListCountByPage(String mobile);
	/**
	 * 查询历史分页数据
	 * @param page
	 * @return
	 */
	List<AddressData> queryHistoryDataByPage(PageProperty page);
	/**
	 * 查询当前已经查询的数量条数
	 * @param args 当前年份的起止时间
	 * @return
	 */
	List<Map<String, Integer>> queryCountByTime(Map<String, Object> args);

}
