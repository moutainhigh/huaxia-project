package com.huaxia.opas.service.address;

import java.util.List;

import com.huaxia.opas.domain.address.OpasArea;
import com.huaxia.opas.domain.address.OpasCity;
import com.huaxia.opas.domain.address.OpasProvince;
import com.huaxia.opas.domain.address.OpasTown;
import com.huaxia.opas.service.BaseService;

public interface QueryAddressListService extends BaseService{
	public List<OpasProvince> queryProvinceDetail();
	public List<OpasCity> queryCityDetail();
	public List<OpasArea> queryAreaDetail();
	public List<OpasTown> queryTownDetail();
}
