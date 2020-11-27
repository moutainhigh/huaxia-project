package com.huaxia.cams.modules.baoxin.service;

import com.huaxia.cams.modules.baoxin.domain.BaoXinBase;

public interface BaoXinService {

	int selectCountByAppId(String appId);

	int saveBase(BaoXinBase base);

}
