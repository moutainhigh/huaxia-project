package com.huaxia.opas.dao.address;

import java.util.List;

import com.huaxia.opas.domain.address.OpasArea;
import com.huaxia.opas.domain.address.OpasCity;
import com.huaxia.opas.domain.address.OpasProvince;
import com.huaxia.opas.domain.address.OpasTown;

public interface QueryAddressListDao {
	public List<OpasProvince> queryProvinceDetail();
	public List<OpasCity> queryCityDetail();
	public List<OpasArea> queryAreaDetail();
	public List<OpasTown> queryTownDetail();
}
