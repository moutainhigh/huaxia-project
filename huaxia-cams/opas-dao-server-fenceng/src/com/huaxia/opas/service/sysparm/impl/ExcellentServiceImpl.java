package com.huaxia.opas.service.sysparm.impl;

import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.ExcellentDao;
import com.huaxia.opas.domain.decision.ExcellentCompanyList;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.ExcellentService;

public class ExcellentServiceImpl extends AbstractService implements ExcellentService{

	private static final long serialVersionUID = 8538012046271392545L;

	@Resource
	private ExcellentDao ExcellentDao;
	/**
	 * 查询符合条件的数量
	 */
	@Override
	public int queryExcellentCount(ExcellentCompanyList excellent) {
		return ExcellentDao.queryExcellentCount(excellent);
	}
	/**
	 * 分页查询
	 */
	@Override
	public List<ExcellentCompanyList> queryExcellentList(ExcellentCompanyList excellent, int curNum, int pageNum) {
		return ExcellentDao.queryExcellentList(excellent,curNum,pageNum);
	}
	@Override
	public List<ExcellentCompanyList> checkIsExistExcellent(ExcellentCompanyList excellent) {
		return ExcellentDao.checkIsExistExcellent(excellent);
	}
	@Override
	public int insertExcellent(ExcellentCompanyList excellent) {
		return ExcellentDao.insertExcellent(excellent);
		
	}
	@Override
	public int updateExcellent(ExcellentCompanyList excellent) {
		return ExcellentDao.updateExcellent(excellent);
	}
	@Override
	public int deleteExcellent(ExcellentCompanyList excellent) {
		return ExcellentDao.deleteExcellent(excellent);
	}
	@Override
	public int excellentStatusOK(ExcellentCompanyList excellent) {
		return ExcellentDao.excellentStatusOK(excellent);
	}
	@Override
	public int excellentStatusStop(ExcellentCompanyList excellent) {
		return ExcellentDao.excellentStatusStop(excellent);
	}
	@Override
	public int insertExcellentUpload(List<ExcellentCompanyList> list) throws CoreException  {
		return ExcellentDao.insertExcellentUpload(list);
	}

}
