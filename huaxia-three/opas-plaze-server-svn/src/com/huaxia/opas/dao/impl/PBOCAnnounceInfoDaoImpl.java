package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCAnnounceInfoDao;
import com.huaxia.opas.mapper.PBOCAnnounceInfoMapper;

@Repository
public class PBOCAnnounceInfoDaoImpl implements PBOCAnnounceInfoDao {

	@Resource
	private PBOCAnnounceInfoMapper announceInfoMapper;

	public PBOCAnnounceInfoMapper getAnnounceInfoMapper() {
		return announceInfoMapper;
	}

	public void setAnnounceInfoMapper(PBOCAnnounceInfoMapper announceInfoMapper) {
		this.announceInfoMapper = announceInfoMapper;
	}

	@Override
	public int insert(Map<String, Object> entity) {
		return getAnnounceInfoMapper().insert(entity);
	}
	
}
