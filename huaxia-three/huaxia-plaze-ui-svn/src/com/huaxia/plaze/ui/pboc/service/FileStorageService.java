package com.huaxia.plaze.ui.pboc.service;

import java.util.List;

import com.huaxia.plaze.ui.pboc.domain.FileStorageInfo;

// 文件上传记录业务处理层
public interface FileStorageService {

	String getFilePathById(String id);
	
	List<FileStorageInfo> queryById(String sourceId,String fileServerAddr);

}
