package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.MarketerDao;
import com.huaxia.opas.domain.sysparm.Marketer;

public class MarketerDaoImpl  extends AbstractDAO implements MarketerDao{

	private static final long serialVersionUID = -1264109150276625900L;

	private static final String NAMESPACES = "Marketer.";
	/**
	 * 数量统计
	 */
	@Override
	public int queryMarketersCount(Marketer marketer) {
		return sqlMap.queryForObject(NAMESPACES+"queryMarketersCount", marketer);
	}
	/**
	 * 分页查询
	 */	@Override
	public List<Marketer> queryMarketersList(Marketer marketer, int curNum, int pageNum) {
		return sqlMap.queryForList(NAMESPACES+"queryMarketersList", marketer, curNum,pageNum);
	}
	@Override
	public List<Marketer> checkIsExistMarketer(Marketer marketer) {
		return sqlMap.queryForList(NAMESPACES+"checkIsExistMarketer", marketer);
	}
	@Override
	public int insertMarketer(Marketer marketer) {
		return sqlMap.insert(NAMESPACES+"insertMarketer", marketer);
	}
	@Override
	public int updateMarketer(Marketer marketer) {
		return sqlMap.update(NAMESPACES+"updateMarketer", marketer);
	}
	@Override
	public int deleteMarketer(Marketer marketer) {
		return sqlMap.delete(NAMESPACES+"deleteMarketer", marketer);
	}
	@Override
	public int marketerStatusOK(Marketer marketer) {
		return sqlMap.update(NAMESPACES+"marketerStatusOK", marketer);
	}
	@Override
	public int marketerStatusStop(Marketer marketer) {
		return sqlMap.update(NAMESPACES+"marketerStatusStop", marketer);
	}
	@Override
	public int insertMarketerUpload(List<Marketer> list) {
		return sqlMap.insert(NAMESPACES+"insertMarketerUpload", list);
	}

}
