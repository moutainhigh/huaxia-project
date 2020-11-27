package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.SpecialProjectDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.SpecialprojectList;
import com.huaxia.opas.domain.sysparm.SpecialprojectListHis;
import com.huaxia.opas.service.sysparm.SpecialProjectService;

public class SpecialProjectServiceImpl extends AbstractDAO implements SpecialProjectService, Serializable{

	private static final long serialVersionUID = -8031110907080968923L;
	
	@Resource(name = "specialProjectDao")
	private SpecialProjectDao specialProjectDao;
	
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	public SpecialProjectDao getSpecialProjectDao() {
		return specialProjectDao;
	}


	public void setSpecialProjectDao(SpecialProjectDao specialProjectDao) {
		this.specialProjectDao = specialProjectDao;
	}


	@Override
	public int insert(SpecialprojectList specialprojectlist) throws CoreException {
		return getSpecialProjectDao().insert(specialprojectlist);
	}


	@Override
	public int insertSelective(SpecialprojectList specialprojectlist) throws CoreException {
		return getSpecialProjectDao().insertSelective(specialprojectlist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) throws CoreException {
		return getSpecialProjectDao().deleteByPrimaryKey(autoId);
	}

	@Override
	public int updateByPrimaryKey(SpecialprojectList specialprojectlist) throws CoreException {
		return getSpecialProjectDao().updateByPrimaryKey(specialprojectlist);
	}

	@Override
	public int updateByPrimaryKeySelective(SpecialprojectList specialprojectlist) throws CoreException {
		return getSpecialProjectDao().updateByPrimaryKeySelective(specialprojectlist);
	}

	@Override
	public SpecialprojectList selectByPrimaryKey(String autoId) throws CoreException {
		return getSpecialProjectDao().selectByPrimaryKey(autoId);
	}
	
	//shihuan 2017-03-16 特殊项目列表查询 
	@Override
	public List<SpecialprojectList> querySpecialprojectList(SpecialprojectList specialprojectlist, int curNum, int pageNum) throws CoreException {
		return getSpecialProjectDao().querySpecialprojectList(specialprojectlist, curNum, pageNum);
	}

	//shihuan 2017-03-16 特殊项目列表查询 条数
	@Override
	public int querySpecialprojectCount(SpecialprojectList specialprojectlist) throws CoreException {
		return getSpecialProjectDao().querySpecialprojectCount(specialprojectlist);
	}
	
	//shihuan 2017-03-16  特殊项目批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getSpecialProjectDao().updateStartStatus(autoId);
	}
	
	//shihuan 2017-03-16  特殊项目批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getSpecialProjectDao().updateStopStatus(autoId);
	}
	
	//shihuan 2017-03-16  添加历史修改记录表
	@Override
	public int insertHisSelective(SpecialprojectListHis specialprojectlisthis) throws CoreException {
		return getSpecialProjectDao().insertHisSelective(specialprojectlisthis);
	}
	
	//shihuan 2017-03-16 历史特殊项目列表查询 
	@Override
	public List<SpecialprojectList> querySpecialprojectHistory(String autoId, int curNum, int pageNum) throws CoreException {
		return getSpecialProjectDao().querySpecialprojectHistory(autoId, curNum, pageNum);
	}

	//shihuan 2017-03-16 历史特殊项目列表查询 条数
	@Override
	public int queryHistoryCount(String autoId) throws CoreException {
		return getSpecialProjectDao().queryHistoryCount(autoId);
	}
	
	//shihuan 2017-03-17 上传特殊项目文件
	@Override
	public int insertSpecProUpload(List<SpecialprojectList> obj, BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts = specialProjectDao.insertSpecProUpload(obj);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}
	
	//shihuan 2017-04-24  校验项目代码不能重复
	@Override
	public int queryProjectCode(String projectCode) throws CoreException {
		return getSpecialProjectDao().queryProjectCode(projectCode);
	}
}