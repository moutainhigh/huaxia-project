package com.huaxia.cams.modules.baoxin.mapper;

import java.util.Map;

import com.huaxia.cams.modules.baoxin.domain.BaoXinBase;


public interface BaoXinMapper {


	int saveBase(BaoXinBase base);

	int selectCountByAppId(String appId);


}
