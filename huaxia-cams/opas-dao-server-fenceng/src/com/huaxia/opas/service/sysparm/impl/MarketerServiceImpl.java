package com.huaxia.opas.service.sysparm.impl;

import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.MarketerDao;
import com.huaxia.opas.domain.sysparm.Marketer;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.MarketerService;

public class MarketerServiceImpl extends AbstractService implements MarketerService{

	private static final long serialVersionUID = 8538012046271392545L;

	@Resource
	private MarketerDao marketerDao;
	/**
	 * 查询符合条件的数量
	 */
	@Override
	public int queryMarketersCount(Marketer marketer) {
		return marketerDao.queryMarketersCount(marketer);
	}
	/**
	 * 分页查询
	 */
	@Override
	public List<Marketer> queryMarketersList(Marketer marketer, int curNum, int pageNum) {
		return marketerDao.queryMarketersList(marketer,curNum,pageNum);
	}
	@Override
	public List<Marketer> checkIsExistMarketer(Marketer marketer) {
		return marketerDao.checkIsExistMarketer(marketer);
	}
	@Override
	public int insertMarketer(Marketer marketer) {
		return marketerDao.insertMarketer(marketer);
		
	}
	@Override
	public int updateMarketer(Marketer marketer) {
		return marketerDao.updateMarketer(marketer);
	}
	@Override
	public int deleteMarketer(Marketer marketer) {
		return marketerDao.deleteMarketer(marketer);
	}
	@Override
	public int marketerStatusOK(Marketer marketer) {
		return marketerDao.marketerStatusOK(marketer);
	}
	@Override
	public int marketerStatusStop(Marketer marketer) {
		return marketerDao.marketerStatusStop(marketer);
	}
	@Override
	public int insertMarketerUpload(List<Marketer> list) throws CoreException  {
		return marketerDao.insertMarketerUpload(list);
	}

}
