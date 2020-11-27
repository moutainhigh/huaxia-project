package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLImageUrlDao;
import com.huaxia.opas.domain.AMLImageUrl;
import com.huaxia.opas.mapper.aml.AMLImageUrlMapper;

@Repository
public class AMLImageUrlDaoImpl implements AMLImageUrlDao {
	
	@Resource
	private AMLImageUrlMapper imageUrlMapper;

	@Override
	public int insert(AMLImageUrl imageUrl) {
		return getImageUrlMapper().insert(imageUrl);
	}

	public AMLImageUrlMapper getImageUrlMapper() {
		return imageUrlMapper;
	}

	public void setImageUrlMapper(AMLImageUrlMapper imageUrlMapper) {
		this.imageUrlMapper = imageUrlMapper;
	}

}
