package com.huaxia.opas.service.system;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.system.FileUpload;
import com.huaxia.opas.domain.system.FileUploadLog;

public interface FileUploadLogService {
	
	int deleteByPrimaryKey(String fileId);

	int insert(FileUploadLog record);

	int insertSelective(FileUploadLog record);

	FileUploadLog selectByPrimaryKey(String fileId);

	int updateByPrimaryKeySelective(FileUploadLog record);

	int updateByPrimaryKey(FileUploadLog record);

	List<FileUpload> queryFileList(Map<String, Object> map);

	int queryFileCount(Map<String, Object> map);
}