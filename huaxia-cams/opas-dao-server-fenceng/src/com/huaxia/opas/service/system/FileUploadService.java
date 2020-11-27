package com.huaxia.opas.service.system;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.system.FileRoleRelation;
import com.huaxia.opas.domain.system.FileUpload;
import com.huaxia.opas.domain.system.FileUploadLog;

public interface FileUploadService {
	
	int deleteByPrimaryKey(String fileId);

	int insert(FileUpload record);

	int insertSelective(FileUpload record);

	FileUpload selectByPrimaryKey(String fileId);

	int updateByPrimaryKeySelective(FileUpload record);

	int updateByPrimaryKey(FileUpload record);

	int queryFileCount(Map<String, Object> map);

	List<FileUpload> queryFileList(Map<String, Object> map, int curNum,
			int pageNum);

	FileUpload selectByFileName(String fileName);

	int deleteFileUpload(FileUploadLog filelog, String fileId);

	int addFileUpload(FileUpload record, FileUploadLog filelog);

	List<String> selectSmallCategoryByUserCode(String userCode);

	int queryFileRoleCount(Map<String, Object> map);

	List<FileRoleRelation> queryFileRoleList(Map<String, Object> map, int curNum, int pageNum);

	void insertSelective(FileRoleRelation record);

	void deleteByCondition(FileRoleRelation record);
}