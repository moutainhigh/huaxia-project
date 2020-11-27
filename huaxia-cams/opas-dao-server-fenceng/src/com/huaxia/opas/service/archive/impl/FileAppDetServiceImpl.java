package com.huaxia.opas.service.archive.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import com.huaxia.opas.dao.archive.FileAppDetDao;
import com.huaxia.opas.dao.decision.BizApprovalDao;
import com.huaxia.opas.dao.rule.OpasBizApprovalDao;
import com.huaxia.opas.domain.archive.FileAppDet;
import com.huaxia.opas.domain.rule.OpasBizApproval;
import com.huaxia.opas.domain.thirdparty.BizApproval;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.archive.FileAppDetService;
import com.huaxia.opas.util.StringUtil;

public class FileAppDetServiceImpl extends AbstractService implements FileAppDetService , Serializable{

	private static final long serialVersionUID = 4222708380138694159L;

	@Resource(name = "fileAppDetDao")
	private FileAppDetDao fileAppDetDao;
	
	@Resource(name="opasBizApprovalDao")
	private OpasBizApprovalDao opasBizApprovalDao;
	
	@Resource(name="bizApprovalDao")
	private BizApprovalDao bizApprovalDao;
	

	@Override
	public int deleteByPrimaryKey(String appId) {
		return fileAppDetDao.deleteByPrimaryKey(appId);
	}

	@Override
	public int insert(FileAppDet record) {
		return fileAppDetDao.insert(record);
	}

	@Override
	public FileAppDet selectByPrimaryKey(String appId) {
		return fileAppDetDao.selectByPrimaryKey(appId);
	}

	@Override
	public int updateByPrimaryKey(FileAppDet record) {
		return fileAppDetDao.updateByPrimaryKey(record);
	}
	@Override
	public OpasBizApproval selectOpasBizApprovalByPrimaryKey(String appId) {
		return opasBizApprovalDao.selectByPrimaryKey(appId);
	}

	@Override
	public void insertFileAppDet(FileAppDet fileAppDet, BizApproval record) {
		String appId = fileAppDet.getAppId();
		FileAppDet appDet = fileAppDetDao.selectByPrimaryKey(appId);
		if(null==appDet){
			fileAppDetDao.insert(fileAppDet);
		}else{
			fileAppDetDao.updateByPrimaryKey(fileAppDet);
		}
		
		BizApproval bizApproval = bizApprovalDao.selectByAppId(appId);
		if(null==bizApproval){
			record.setAutoId(StringUtil.randomUUIDPlainString());
			bizApprovalDao.insertSelective(record);
		}else{
			bizApprovalDao.updateByPrimaryKeySelective(record);
		}
	} 
}
