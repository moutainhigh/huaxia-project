package com.huaxia.opas.dao.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.FileUploadLogDao;
import com.huaxia.opas.domain.system.FileUpload;
import com.huaxia.opas.domain.system.FileUploadLog;

public class FileUploadLogDaoImpl extends AbstractDAO implements FileUploadLogDao {

	private static final long serialVersionUID = 5757582418545513971L;

	private static final String NAMESPACES = "FileUploadLog.";

	@Override
	public int deleteByPrimaryKey(String fileId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", fileId);
	}

	@Override
	public int insert(FileUploadLog record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(FileUploadLog record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public FileUploadLog selectByPrimaryKey(String fileId) {
		return (FileUploadLog) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", fileId));
	}

	@Override
	public List<FileUpload> queryFileList(Map<String, Object> map) {
		List<FileUpload> list = new ArrayList<FileUpload>();
		list = getSqlMap().queryForList(NAMESPACES + "queryFileList", map);
		return list;
	}

	@Override
	public int updateByPrimaryKeySelective(FileUploadLog record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(FileUploadLog record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public int queryFileCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryFileCount", map);
	}

}
