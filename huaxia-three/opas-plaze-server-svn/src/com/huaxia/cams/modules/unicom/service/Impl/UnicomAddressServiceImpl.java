package com.huaxia.cams.modules.unicom.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.cams.modules.unicom.domain.UnicomAddress;
import com.huaxia.cams.modules.unicom.mapper.UnicomAddressMapper;
import com.huaxia.cams.modules.unicom.service.UnicomAddressService;

import net.sf.json.JSONObject;
@Service("unicomAddressService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UnicomAddressServiceImpl implements UnicomAddressService {
	private final static Logger logger = LoggerFactory.getLogger(UnicomAddressServiceImpl.class);
	
	@Autowired
	private UnicomAddressMapper unicomAddressMapper;

	public UnicomAddressMapper getUnicomAddressMapper() {
		return unicomAddressMapper;
	}

	public void setUnicomAddressMapper(UnicomAddressMapper unicomAddressMapper) {
		this.unicomAddressMapper = unicomAddressMapper;
	}
	
	// 确认数据是否存在
	@Override
	public int countByAppId(String appId,String apikey) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("appId",appId);
		map.put("apikey", apikey);
		return unicomAddressMapper.selectCountByAppId(map);
	}

	// 地址类信息入库
	@Override
	public void saveUnicomAddress(UnicomAddress unicomAddress) {		
		unicomAddressMapper.saveUnicomAddress(unicomAddress);
	}

}
