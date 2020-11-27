package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLCertDetailDao;
import com.huaxia.opas.domain.AMLCertDetail;
import com.huaxia.opas.mapper.aml.AMLCertDetailMapper;

@Repository
public class AMLCertDetailDaoImpl implements AMLCertDetailDao {
	
	@Resource
	private AMLCertDetailMapper certDetailMapper;

	@Override
	public int insert(AMLCertDetail certDetail) {
		return getCertDetailMapper().insert(certDetail);
	}

	public AMLCertDetailMapper getCertDetailMapper() {
		return certDetailMapper;
	}

	public void setCertDetailMapper(AMLCertDetailMapper certDetailMapper) {
		this.certDetailMapper = certDetailMapper;
	}

}
