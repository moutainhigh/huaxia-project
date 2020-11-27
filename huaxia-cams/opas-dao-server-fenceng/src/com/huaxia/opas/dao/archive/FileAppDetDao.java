package com.huaxia.opas.dao.archive;

import com.huaxia.opas.domain.archive.FileAppDet;

public interface FileAppDetDao {
	
	int deleteByPrimaryKey(String appId);

    int insert(FileAppDet record);

    FileAppDet selectByPrimaryKey(String appId);

    int updateByPrimaryKey(FileAppDet record);
}
