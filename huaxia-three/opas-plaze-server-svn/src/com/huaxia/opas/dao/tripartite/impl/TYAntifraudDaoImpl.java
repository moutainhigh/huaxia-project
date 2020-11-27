package com.huaxia.opas.dao.tripartite.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.huaxia.opas.dao.tripartite.TYAntifraudDao;
import com.huaxia.opas.domain.tripartite.TYAntifraudData;
import com.huaxia.opas.domain.tripartite.TYAntifraudRiskInfo;
import com.huaxia.opas.mapper.tripartite.TYAntifraudMapper;
@Repository
public class TYAntifraudDaoImpl implements TYAntifraudDao {

	@Resource
	private TYAntifraudMapper tyAntifraudMapper;
	
	public TYAntifraudMapper getTyAntifraudMapper() {
		return tyAntifraudMapper;
	}

	public void setTyAntifraudMapper(TYAntifraudMapper tyAntifraudMapper) {
		this.tyAntifraudMapper = tyAntifraudMapper;
	}


	@Override
	public int selectCountByAppId(String appId) {
		return tyAntifraudMapper.selectCountByAppId(appId);
	}

	@Override
	public void insertTyData(TYAntifraudData tyAntifraudData) {
		tyAntifraudMapper.insertTyData(tyAntifraudData);
	}

	@Override
	public void insertBatchTyRisk(List<TYAntifraudRiskInfo> listTYAntifraudRiskInfo) {
		tyAntifraudMapper.insertBatchTyRisk(listTYAntifraudRiskInfo);
	}

}