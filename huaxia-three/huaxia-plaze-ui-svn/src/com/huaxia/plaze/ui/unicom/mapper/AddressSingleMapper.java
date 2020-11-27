package com.huaxia.plaze.ui.unicom.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.unicom.domain.AddressData;
import com.huaxia.plaze.ui.unicom.domain.AddressSingle;

public interface AddressSingleMapper {

	int selectCountByMobile(Map args);

	void insert(AddressSingle addressSingle);

	int updateStatusById(Map<String, Object> params);

	AddressData selectSingleResultByTrnId(String trnId);

	int queryHistoryCountByMobile(String mobile);

	List<AddressData> queryHistoryDataByPage(Map<String, Object> args);

}
