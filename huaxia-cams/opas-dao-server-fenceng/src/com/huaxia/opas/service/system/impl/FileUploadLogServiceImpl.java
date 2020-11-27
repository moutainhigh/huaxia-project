package com.huaxia.opas.service.system.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.system.FileUploadLogDao;
import com.huaxia.opas.domain.system.FileUpload;
import com.huaxia.opas.domain.system.FileUploadLog;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.system.FileUploadLogService;

public class FileUploadLogServiceImpl extends AbstractService implements FileUploadLogService, Serializable  {

	private static final long serialVersionUID = 5757582418545513971L;
	
	@Resource(name = "fileUploadLogDao")
	private FileUploadLogDao fileUploadLogDao;
	
	public FileUploadLogDao getFileUploadLogDao() {
		return fileUploadLogDao;
	}

	public void setFileUploadLogDao(FileUploadLogDao fileUploadLogDao) {
		this.fileUploadLogDao = fileUploadLogDao;
	}

	public int deleteByPrimaryKey(String fileId) {
		return getFileUploadLogDao().deleteByPrimaryKey(fileId);
	}

	public int insert(FileUploadLog record) {
		return getFileUploadLogDao().insert(record);
	}

	@Override
	public int insertSelective(FileUploadLog record) {
		return getFileUploadLogDao().insertSelective(record);
	}

	@Override
	public FileUploadLog selectByPrimaryKey(String fileId) {
		return getFileUploadLogDao().selectByPrimaryKey(fileId);
	}
	@Override
	public List<FileUpload> queryFileList(Map<String, Object> map) {
		return getFileUploadLogDao().queryFileList(map);
	}
	@Override
	public int updateByPrimaryKeySelective(FileUploadLog record) {
		return getFileUploadLogDao().updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(FileUploadLog record) {
		return getFileUploadLogDao().updateByPrimaryKey(record);
	}

	@Override
	public int queryFileCount(Map<String, Object> map) {
		return getFileUploadLogDao().queryFileCount(map);
	}

}
