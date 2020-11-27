package com.huaxia.cams.modules.unicom.mapper;

import java.util.Map;

import com.huaxia.cams.modules.unicom.domain.UnicomAddress;

public interface UnicomAddressMapper {

	int selectCountByAppId(Map<String,String> map);

	void saveUnicomAddress(UnicomAddress unicomAddress);

}
