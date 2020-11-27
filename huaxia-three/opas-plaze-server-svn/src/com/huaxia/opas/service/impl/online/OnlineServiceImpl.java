package com.huaxia.opas.service.impl.online;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.online.OnlineDao;
import com.huaxia.opas.domain.online.Online;
import com.huaxia.opas.service.online.OnlineService;



@Service("onlineService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class OnlineServiceImpl implements OnlineService {
	
	private final static Logger logger = LoggerFactory.getLogger(OnlineServiceImpl.class);
	
	@Autowired
	private OnlineDao onlineDao;
	
	public OnlineDao getOnlineDao() {
		return onlineDao;
	}

	public void setOnlineDao(OnlineDao onlineDao) {
		this.onlineDao = onlineDao;
	}

	@Override
	public int getCountByAppId(String appId) {
		return onlineDao.getCountByAppId(appId);
	}

	@Override
	public int insert(Online online) {
		return onlineDao.insert(online);
	}

}
