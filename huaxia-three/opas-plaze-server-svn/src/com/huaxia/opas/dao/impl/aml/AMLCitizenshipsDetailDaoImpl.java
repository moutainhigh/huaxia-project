package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLCitizenshipsDetailDao;
import com.huaxia.opas.domain.AMLCitizenshipsDetail;
import com.huaxia.opas.mapper.aml.AMLCitizenshipsDetailMapper;

@Repository
public class AMLCitizenshipsDetailDaoImpl implements AMLCitizenshipsDetailDao {
	
	@Resource
	private AMLCitizenshipsDetailMapper citizenshipsDetailMapper;

	@Override
	public int insert(AMLCitizenshipsDetail citizenshipsDetail) {
		return getCitizenshipsDetailMapper().insert(citizenshipsDetail);
	}

	public AMLCitizenshipsDetailMapper getCitizenshipsDetailMapper() {
		return citizenshipsDetailMapper;
	}

	public void setCitizenshipsDetailMapper(AMLCitizenshipsDetailMapper citizenshipsDetailMapper) {
		this.citizenshipsDetailMapper = citizenshipsDetailMapper;
	}

}
