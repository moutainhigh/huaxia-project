package com.huaxia.plaze.ui.pboc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.ui.pboc.domain.FileStorageInfo;
import com.huaxia.plaze.ui.pboc.mapper.FileStorageMapper;
import com.huaxia.plaze.ui.pboc.service.FileStorageService;

//文件上传记录业务处理层
@Service("fileStorageService")
public class FileStorageServiceImpl implements FileStorageService {

	@Resource
	private FileStorageMapper<FileStorageInfo> fileStorageMapper;

	// 根据sourceId查询附件信息列表
	@Override
	public List<FileStorageInfo> queryById(String sourceId,String fileServerAddr) {
		return fileStorageMapper.selectBySourceId(sourceId);
	}

	@Override
	public String getFilePathById(String id) {
		return fileStorageMapper.selectFilePathById(id);
	}

}
