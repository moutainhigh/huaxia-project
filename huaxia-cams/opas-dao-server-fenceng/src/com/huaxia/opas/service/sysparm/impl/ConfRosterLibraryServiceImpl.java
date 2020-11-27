package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.ConfQuestionDao;
import com.huaxia.opas.dao.sysparm.ConfRosterLibraryDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.ConfQuestion;
import com.huaxia.opas.domain.sysparm.ConfRosterLibrary;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.sysparm.ConfRosterLibraryService;

public class ConfRosterLibraryServiceImpl implements ConfRosterLibraryService,Serializable {

	private static final long serialVersionUID = 1662418127884228985L;
	
	@Resource(name="confRosterLibraryDao")
	private ConfRosterLibraryDao confRosterLibraryDao;
	//插入历史记录
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	
	
	public ConfRosterLibraryDao getConfRosterLibraryDao() {
		return confRosterLibraryDao;
	}

	public void setConfRosterLibraryDao(ConfRosterLibraryDao confRosterLibraryDao) {
		this.confRosterLibraryDao = confRosterLibraryDao;
	}

	@Override
	public int queryConfRosterLibraryCount(ConfRosterLibrary confRosterLibrary) {
		return getConfRosterLibraryDao().queryConfRosterLibraryCount(confRosterLibrary);
	}

	@Override
	public List<ConfRosterLibrary> queryConfRosterLibraryList(ConfRosterLibrary confRosterLibrary, int curNum, int pageNum) {
		return getConfRosterLibraryDao().queryConfRosterLibraryList(confRosterLibrary, curNum, pageNum);
	}

	@Override
	public int insertConfRosterLibrary(ConfRosterLibrary confRosterLibrary) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = confRosterLibrary.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		confRosterLibrary.setCrtUser(userName);
		confRosterLibrary.setLstUpdUser(userName);
		return getConfRosterLibraryDao().insertConfRosterLibrary(confRosterLibrary);
	}

	@Override
	public int updateConfRosterLibrary(ConfRosterLibrary confRosterLibrary) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = confRosterLibrary.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		confRosterLibrary.setLstUpdUser(userName);
		return getConfRosterLibraryDao().updateConfRosterLibrary(confRosterLibrary);
	}

	@Override
	public int deleteConfRosterLibrary(ConfRosterLibrary confRosterLibrary) {
		return getConfRosterLibraryDao().deleteConfRosterLibrary(confRosterLibrary);
	}

	@Override
	public int insertRosterLibraryFromFile(List<ConfRosterLibrary> list, BatchfileInfo batchfileInfo) {
		int inputCounts = confRosterLibraryDao.insertConfRosterLibrary(list);
		//添加导入记录
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}

	//唯一校验
	@Override
	public ConfRosterLibrary queryOnly(ConfRosterLibrary confRosterLibrary) {
		ConfRosterLibrary only = confRosterLibraryDao.queryOnly(confRosterLibrary);
		return only;
	}

	@Override
	public int deleteAll() throws CoreException {
		return confRosterLibraryDao.deleteAll();
	}

}
