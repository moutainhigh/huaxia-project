package com.huaxia.opas.service.archive;

import java.util.List;

import com.huaxia.opas.domain.archive.ArDetail;
import com.huaxia.opas.domain.archive.ArFailBack;


public interface ArEditService {

	public List<ArFailBack> queryFailBackList(ArFailBack arFailBack, int curNum, int pageNum);
	
	public int queryFailBackCount(ArFailBack arFailBack);
	
	public int updateArFailBack(ArFailBack arFailBack);
	
	public int updateArDetail(ArDetail arDetail);
	
}
