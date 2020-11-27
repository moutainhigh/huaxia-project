package com.huaxia.opas.dao.impl;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.huaxia.opas.dao.PBOCAssetDispositionInfoDao;
import com.huaxia.opas.mapper.PBOCAssetDispositionInfoMapper;

@Repository
public class PBOCAssetDispositionInfoDaoImpl implements PBOCAssetDispositionInfoDao {

	@Resource
	private PBOCAssetDispositionInfoMapper assetDispositionInfoMapper;
	
	public PBOCAssetDispositionInfoMapper getAssetDispositionInfoMapper() {
		return assetDispositionInfoMapper;
	}
	public void setAssetDispositionInfoMapper(PBOCAssetDispositionInfoMapper assetDispositionInfoMapper) {
		this.assetDispositionInfoMapper = assetDispositionInfoMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return  getAssetDispositionInfoMapper().insertBatch(parameters);
	}
	
}
