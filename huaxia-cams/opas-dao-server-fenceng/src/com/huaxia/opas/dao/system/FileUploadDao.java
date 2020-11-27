package com.huaxia.opas.dao.system;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.system.FileUpload;

public interface FileUploadDao {
	
	int deleteByPrimaryKey(String fileId);

	int insert(FileUpload record);

	int insertSelective(FileUpload record);

	FileUpload selectByPrimaryKey(String fileId);

	int updateByPrimaryKeySelective(FileUpload record);

	int updateByPrimaryKey(FileUpload record);

	int queryFileCount(Map<String, Object> map);

	List<FileUpload> queryFileList(Map<String, Object> map, int curNum, int pageNum);

	FileUpload selectByFileName(String fileName);
}