package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLAliasNameDetailDao;
import com.huaxia.opas.domain.AMLAliasNameDetail;
import com.huaxia.opas.mapper.aml.AMLAliasNameDetailMapper;

@Repository
public class AMLAliasNameDetailDaoImpl implements AMLAliasNameDetailDao {
	
	@Resource
	private AMLAliasNameDetailMapper aliasNameDetailMapper;

	@Override
	public int insert(AMLAliasNameDetail aliasNameDetail) {
		return getAliasNameDetailMapper().insert(aliasNameDetail);
	}

	public AMLAliasNameDetailMapper getAliasNameDetailMapper() {
		return aliasNameDetailMapper;
	}

	public void setAliasNameDetailMapper(AMLAliasNameDetailMapper aliasNameDetailMapper) {
		this.aliasNameDetailMapper = aliasNameDetailMapper;
	}

}
