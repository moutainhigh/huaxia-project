package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLLinkPersonDao;
import com.huaxia.opas.domain.AMLLinkPerson;
import com.huaxia.opas.mapper.aml.AMLLinkPersonMapper;

@Repository
public class AMLLinkPersonDaoImpl implements AMLLinkPersonDao {
	
	@Resource
	private AMLLinkPersonMapper linkPersonMapper;

	@Override
	public int insert(AMLLinkPerson linkPerson) {
		return getLinkPersonMapper().insert(linkPerson);
	}

	public AMLLinkPersonMapper getLinkPersonMapper() {
		return linkPersonMapper;
	}

	public void setLinkPersonMapper(AMLLinkPersonMapper linkPersonMapper) {
		this.linkPersonMapper = linkPersonMapper;
	}

}
