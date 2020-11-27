package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.MajorschoolListDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.MajorschoolList;
import com.huaxia.opas.domain.sysparm.MajorschoolListHistory;
import com.huaxia.opas.service.sysparm.MajorschoolListService;

public class MajorschoolListServiceImpl extends AbstractDAO implements MajorschoolListService, Serializable{

	private static final long serialVersionUID = -8031110907080948923L;
	
	@Resource(name = "majorschoolListDao")
	private MajorschoolListDao majorschoolListDao;
	
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	public MajorschoolListDao getMajorschoolListDao() {
		return majorschoolListDao;
	}
	
	public void setMajorschoolListDao(MajorschoolListDao majorschoolListDao) {
		this.majorschoolListDao = majorschoolListDao;
	}
	
	@Override
	public int insert(MajorschoolList majorschoollist) throws CoreException {
		return getMajorschoolListDao().insert(majorschoollist);
	}


	@Override
	public int insertSelective(MajorschoolList majorschoollist) throws CoreException {
		return getMajorschoolListDao().insertSelective(majorschoollist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) throws CoreException {
		return getMajorschoolListDao().deleteByPrimaryKey(autoId);
	}

	@Override
	public int updateByPrimaryKey(MajorschoolList majorschoollist) throws CoreException {
		return getMajorschoolListDao().updateByPrimaryKey(majorschoollist);
	}

	@Override
	public int updateByPrimaryKeySelective(MajorschoolList majorschoollist) throws CoreException {
		return getMajorschoolListDao().updateByPrimaryKeySelective(majorschoollist);
	}

	@Override
	public MajorschoolList selectByPrimaryKey(String autoId) throws CoreException {
		return getMajorschoolListDao().selectByPrimaryKey(autoId);
	}
	
	//shihuan 2017-03-14 重点院校列表查询 
	@Override
	public List<MajorschoolList> queryMajorSchooList(MajorschoolList majorschoollist, int curNum, int pageNum) throws CoreException {
		return getMajorschoolListDao().queryMajorSchooList(majorschoollist, curNum, pageNum);
	}

	//shihuan 2017-03-14 重点院校列表查询 条数
	@Override
	public int queryMajorSchooCount(MajorschoolList majorschoollist) throws CoreException {
		return getMajorschoolListDao().queryMajorSchooCount(majorschoollist);
	}
	
	//shihuan 2017-03-14  重点院校批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getMajorschoolListDao().updateStartStatus(autoId);
	}
	
	//shihuan 2017-03-14  重点院校批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getMajorschoolListDao().updateStopStatus(autoId);
	}
	
	//shihuan 2017-03-14  添加历史修改记录表
	@Override
	public int insertHisSelective(MajorschoolListHistory majorschoollisthistory) throws CoreException {
		return getMajorschoolListDao().insertHisSelective(majorschoollisthistory);
	}
	
	//shihuan 2017-03-14 历史重点院校列表查询 
	@Override
	public List<MajorschoolList> queryMajorSchoolHistory(String autoId, int curNum, int pageNum) throws CoreException {
		return getMajorschoolListDao().queryMajorSchoolHistory(autoId, curNum, pageNum);
	}

	//shihuan 2017-03-14 历史重点院校列表查询 条数
	@Override
	public int queryMajorSchoolHistoryCount(String autoId) throws CoreException {
		return getMajorschoolListDao().queryMajorSchoolHistoryCount(autoId);
	}
	//shihuan 2017-03-18 重点院校名单批导入文件
	@Override
	public int insertMajorSchUpload(List<MajorschoolList> obj, BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts = majorschoolListDao.insertMajorSchUpload(obj);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}
	
	//shihuan 2017-03-14 历史重点院校列表查询 条数
	@Override
	public int queryByMajorschoolName(String majorschoolName) throws CoreException {
		return getMajorschoolListDao().queryByMajorschoolName(majorschoolName);
	}
}