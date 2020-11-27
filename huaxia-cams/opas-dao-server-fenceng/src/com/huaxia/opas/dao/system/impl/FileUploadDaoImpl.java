package com.huaxia.opas.dao.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.FileUploadDao;
import com.huaxia.opas.domain.system.FileUpload;

public class FileUploadDaoImpl extends AbstractDAO implements FileUploadDao {

	private static final long serialVersionUID = 2757582418545513970L;

	private static final String NAMESPACES = "FileUpload.";

	@Override
	public int deleteByPrimaryKey(String fileId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", fileId);
	}

	@Override
	public int insert(FileUpload record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(FileUpload record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public FileUpload selectByPrimaryKey(String fileId) {
		return (FileUpload) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", fileId));
	}
	@Override
	public FileUpload selectByFileName(String fileName) {
		return (FileUpload) (getSqlMap().queryForObject(NAMESPACES + "selectByFileName", fileName));
	}

	@Override
	public int updateByPrimaryKeySelective(FileUpload record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(FileUpload record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public int queryFileCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryFileCount", map);
	}

	@Override
	public List<FileUpload> queryFileList(Map<String, Object> map, int curNum, int pageNum) {
		List<FileUpload> list = new ArrayList<FileUpload>();
		list = getSqlMap().queryForList(NAMESPACES + "queryFileList", map, curNum, pageNum);
		return list;
	}
}
