package com.huaxia.opas.dao.impl.online;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.online.OnlineDao;
import com.huaxia.opas.domain.online.Online;
import com.huaxia.opas.mapper.online.OnlineMapper;


@Repository
public class OnlineDaoImpl implements OnlineDao {
	
	@Resource
	private OnlineMapper onlineMapper;

	@Override
	public int getCountByAppId(String appId) {
		return onlineMapper.getCountByAppId(appId);
	}

	@Override
	public int insert(Online online) {
		return onlineMapper.insert(online);
	}

}
