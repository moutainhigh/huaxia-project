package com.huaxia.plaze.ui.pboc.mapper;

import java.util.List;

import com.huaxia.plaze.ui.pboc.domain.FileStorageInfo;

public interface FileStorageMapper<T> {

	int insert(T t);
	
	List<FileStorageInfo> selectBySourceId(String sourceId);
	
	String selectFilePathById(String id);

}
