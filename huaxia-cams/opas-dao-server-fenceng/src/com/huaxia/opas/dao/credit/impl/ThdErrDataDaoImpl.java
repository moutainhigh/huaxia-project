package com.huaxia.opas.dao.credit.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.credit.ThdErrDataDao;
import com.huaxia.opas.domain.sysparm.ThdErrMsg;
/**
 * 征信白名单dao层实现类
 * @author susha 2017/03/20
 *
 */
public class ThdErrDataDaoImpl extends AbstractDAO implements ThdErrDataDao {

	private static final long serialVersionUID = 5839271984710046311L;

	private static final String NAMESPACES = "ThdErrData.";

	@Override
	public int queryCount(ThdErrMsg thdErrMsg) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCount", thdErrMsg);
	}

	@Override
	public List<Map<Object, Object>> queryList(ThdErrMsg thdErrMsg, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "querythdErrMsgList", thdErrMsg, curNum, pageNum);
	}

	@Override
	public void updateRequery(Map<String, Object> map) throws CoreException {
		getSqlMap().update(NAMESPACES + "requery", map);
	}

	@Override
	public void updatePassData(Map<String, Object> map) throws CoreException {
		getSqlMap().update(NAMESPACES + "passData", map);
	}

	@Override
	public void addHis(Map<String, Object> map) throws CoreException {
		getSqlMap().insert(NAMESPACES + "insertHis", map);
	}

	@Override
	public int querySailAndFicoCount(ThdErrMsg thdErrMsg) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySailAndFicoCount", thdErrMsg);
	}

	@Override
	public List<Map<Object, Object>> querySailAndFicoList(ThdErrMsg thdErrMsg, int curNum, int pageNum)
			throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "querySailAndFicoList", thdErrMsg, curNum, pageNum);
	}

	@Override
	public void addSailAndFicoHis(Map<String, Object> map) throws CoreException {
		getSqlMap().insert(NAMESPACES + "insertSailAndFicoHis", map);
	}

	@Override
	public void updateSailAndFicoRequery(Map<String, Object> map) throws CoreException {
		getSqlMap().update(NAMESPACES + "sailAndFicoRequery", map);
	}

	@Override
	public void updateSailAndFicoPassData(Map<String, Object> map) throws CoreException {
		getSqlMap().update(NAMESPACES + "sailAndFicoPassData", map);
	}

}
