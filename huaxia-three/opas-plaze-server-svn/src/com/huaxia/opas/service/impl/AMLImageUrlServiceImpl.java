package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLImageUrlDao;
import com.huaxia.opas.domain.AMLImageUrl;
import com.huaxia.opas.service.AMLImageUrlService;

@Service("imageUrlService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLImageUrlServiceImpl implements AMLImageUrlService {

	@Autowired
	private AMLImageUrlDao imageUrlDao;

	@Override
	public int save(AMLImageUrl imageUrl) {
		return getImageUrlDao().insert(imageUrl);
	}

	public AMLImageUrlDao getImageUrlDao() {
		return imageUrlDao;
	}

	public void setImageUrlDao(AMLImageUrlDao imageUrlDao) {
		this.imageUrlDao = imageUrlDao;
	}

}
