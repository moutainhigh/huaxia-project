package com.huaxia.opas.service.credit.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.credit.CreditWhiteListDao;
import com.huaxia.opas.dao.credit.ThdErrDataDao;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
import com.huaxia.opas.domain.sysparm.ThdErrMsg;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.credit.CreditWhiteListService;
import com.huaxia.opas.service.credit.ThdErrDataService;

/**
 * 三方异常查询信息业务实现类
 * 
 * @author weijinfeng 2018/09/20
 *
 */
public class ThdErrDataServiceImpl extends AbstractService implements ThdErrDataService,Serializable {
	private static final long serialVersionUID = 4217310994903470291L;

	@Resource(name = "thdErrDataDao")
	private ThdErrDataDao thdErrDataDao;

	public ThdErrDataDao getThdErrDataDao() {
		return thdErrDataDao;
	}

	public void setThdErrDataDao(ThdErrDataDao thdErrDataDao) {
		this.thdErrDataDao = thdErrDataDao;
	}

	@Override
	public int queryCount(ThdErrMsg thdErrMsg) throws CoreException {
		return thdErrDataDao.queryCount(thdErrMsg);
	}

	@Override
	public List<Map<Object, Object>> queryList(ThdErrMsg thdErrMsg, int curNum, int pageNum) throws CoreException {
		return thdErrDataDao.queryList(thdErrMsg, curNum, pageNum);
	}

	@Override
	public void updateRequeryData(List<Map<String, Object>> errMsgArr) throws CoreException {
		for (Map<String, Object> map : errMsgArr) {
			//System.err.println(map);
			thdErrDataDao.updateRequery(map);
		}
	}

	@Override
	public void updatePassListData(List<Map<String, Object>> errMsgArr) throws CoreException {
		for (Map<String, Object> map : errMsgArr) {
			//System.err.println(map);
			thdErrDataDao.updatePassData(map);
		}
	}

	@Override
	public void addHis(List<Map<String, Object>> errMsgArr) throws CoreException {
		for (Map<String, Object> map : errMsgArr) {
			thdErrDataDao.addHis(map);
		}
	}

	
	@Override
	public int querySailAndFicoCount(ThdErrMsg thdErrMsg) throws CoreException {
		return thdErrDataDao.querySailAndFicoCount(thdErrMsg);
	}

	@Override
	public List<Map<Object, Object>> querySailAndFicoList(ThdErrMsg thdErrMsg, int curNum, int pageNum)
			throws CoreException {
		return thdErrDataDao.querySailAndFicoList(thdErrMsg, curNum, pageNum);
	}

	@Override
	public void addSailAndFicoHis(List<Map<String, Object>> errMsgArr) throws CoreException {
		for (Map<String, Object> map : errMsgArr) {
			thdErrDataDao.addSailAndFicoHis(map);
		}
	}

	@Override
	public void updateSailAndFicoRequeryData(List<Map<String, Object>> errMsgArr) throws CoreException {
		for (Map<String, Object> map : errMsgArr) {
			//System.err.println(map);
			thdErrDataDao.updateSailAndFicoRequery(map);
		}
	}

	@Override
	public void updateSailAndFicoPassListData(List<Map<String, Object>> errMsgArr) throws CoreException {
		for (Map<String, Object> map : errMsgArr) {
			//System.err.println(map);
			thdErrDataDao.updateSailAndFicoPassData(map);
		}
	}

}
