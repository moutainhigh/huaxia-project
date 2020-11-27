package com.huaxia.cams.modules.unicom.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.cams.modules.unicom.domain.UnicomOnline;
import com.huaxia.cams.modules.unicom.mapper.UnicomOnlineMapper;
import com.huaxia.cams.modules.unicom.service.UnicomOnlineService;

@Service("unicomOnlineService")
public class UnicomOnlineServiceImpl implements UnicomOnlineService {
	
	@Resource
	private UnicomOnlineMapper unicomOnlineMapper;

	@Override
	public int countByAppId(String appId) {
		return unicomOnlineMapper.selectCountByAppId(appId);
	}

	@Override
	public void saveUnicomOnline(UnicomOnline unicomOnline) {
		unicomOnlineMapper.insertUnicomOnline(unicomOnline);
	}

}
