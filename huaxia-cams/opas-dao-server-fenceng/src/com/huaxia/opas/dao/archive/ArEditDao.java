package com.huaxia.opas.dao.archive;

import java.util.List;

import com.huaxia.opas.domain.archive.ArDetail;
import com.huaxia.opas.domain.archive.ArFailBack;

public interface ArEditDao {

	public int queryFailBackCount(ArFailBack arFailBack);

	public List<ArFailBack> queryFailBackList(ArFailBack arFailBack, int curNum, int pageNum);

    public int updateArFailBack(ArFailBack arFailBack);
	
	public int updateArDetail(ArDetail arDetail);
}
