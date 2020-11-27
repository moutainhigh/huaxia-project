package com.huaxia.opas.service.system.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.system.FileRoleRelationDao;
import com.huaxia.opas.dao.system.FileUploadDao;
import com.huaxia.opas.dao.system.FileUploadLogDao;
import com.huaxia.opas.domain.system.FileRoleRelation;
import com.huaxia.opas.domain.system.FileUpload;
import com.huaxia.opas.domain.system.FileUploadLog;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.system.FileUploadService;

public class FileUploadServiceImpl extends AbstractService implements FileUploadService, Serializable {

	private static final long serialVersionUID = 2757582418545513970L;
	
	@Resource(name = "fileUploadDao")
	private FileUploadDao fileUploadDao;
	@Resource(name = "fileUploadLogDao")
	private FileUploadLogDao fileUploadLogDao;
	@Resource(name = "fileRoleRelationDao")
	private FileRoleRelationDao fileRoleRelationDao;
	
	public FileUploadDao getFileUploadDao() {
		return fileUploadDao;
	}

	public void setFileUploadDao(FileUploadDao fileUploadDao) {
		this.fileUploadDao = fileUploadDao;
	}

	@Override
	public int deleteByPrimaryKey(String fileId) {
		return getFileUploadDao().deleteByPrimaryKey(fileId);
	}

	@Override
	public int deleteFileUpload(FileUploadLog filelog,String fileId) {
		fileUploadLogDao.insert(filelog);
		return getFileUploadDao().deleteByPrimaryKey(fileId);
	}
	@Override
	public int insert(FileUpload record) {
		return getFileUploadDao().insert(record);
	}
	@Override
	public int addFileUpload(FileUpload record,FileUploadLog filelog) {
		fileUploadLogDao.insert(filelog);
		return getFileUploadDao().insert(record);
	}

	@Override
	public int insertSelective(FileUpload record) {
		return getFileUploadDao().insertSelective(record);
	}

	@Override
	public FileUpload selectByPrimaryKey(String fileId) {
		return getFileUploadDao().selectByPrimaryKey(fileId);
	}
	@Override
	public FileUpload selectByFileName(String fileName) {
		return getFileUploadDao().selectByFileName(fileName);
	}
	@Override
	public int updateByPrimaryKeySelective(FileUpload record) {
		return getFileUploadDao().updateByPrimaryKeySelective(record);
	}
	@Override
	public int queryFileRoleCount(Map<String, Object> map) {
		return fileRoleRelationDao.queryFileRoleCount(map);
	}
	@Override
	public List<FileRoleRelation> queryFileRoleList(Map<String, Object> map, int curNum, int pageNum) {
		return fileRoleRelationDao.queryFileRoleList(map, curNum, pageNum);
	}
	@Override
	public int updateByPrimaryKey(FileUpload record) {
		return getFileUploadDao().updateByPrimaryKey(record);
	}
	
	@Override
	public int queryFileCount(Map<String, Object> map) {
		return getFileUploadDao().queryFileCount(map);
	}
	@Override
	public List<FileUpload> queryFileList(Map<String, Object> map, int curNum, int pageNum) {
		return getFileUploadDao().queryFileList(map, curNum, pageNum);
	}
	@Override
	public List<String> selectSmallCategoryByUserCode(String userCode) {
		return fileRoleRelationDao.selectSmallCategoryByUserCode(userCode);
	}
	@Override
	public void insertSelective(FileRoleRelation record) {
		fileRoleRelationDao.insertSelective(record);
	}
	@Override
	public void deleteByCondition(FileRoleRelation record){
		fileRoleRelationDao.deleteByCondition(record);
	}
	
}
