package com.huaxia.cams.modules.unicom.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.cams.modules.unicom.domain.UnicomPhone;
import com.huaxia.cams.modules.unicom.mapper.UnicomPhoneMapper;
import com.huaxia.cams.modules.unicom.service.UnicomPhoneService;

@Service("unicomPhoneService")
public class UnicomPhoneServiceImpl implements UnicomPhoneService {
	
	@Resource
	private UnicomPhoneMapper unicomPhoneMapper;

	@Override
	public int countByAppId(String appId) {
		return unicomPhoneMapper.selectCountByAppId(appId);
	}

	@Override
	public void saveUnicomPhone(UnicomPhone unicomPhone) {
		unicomPhoneMapper.insertUnicomPhone(unicomPhone);
	}

}
