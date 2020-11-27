package com.huaxia.opas.service.archive.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.archive.ArBatchDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.archive.ArBatch;
import com.huaxia.opas.domain.archive.ArDetail;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.archive.ArBatchService;

import ch.qos.logback.classic.Logger;

public class ArBatchServiceImpl extends AbstractService implements ArBatchService, Serializable {
	private static final long serialVersionUID = 4222708380138694159L;

	@Resource(name = "arBatchDao")
	private ArBatchDao arBatchDao;
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;

	@Override
	public List<ArBatch> queryArchiveList(ArBatch arBatch, int curNum, int pageNum) throws Exception {
		List<ArBatch> list = getArBatchDao().queryArchiveList(arBatch, curNum, pageNum);
		for (ArBatch arBatch1 : list) {
			String fileOperator = arBatch1.getFileOperator();
			ApUser user = apUserDao.queryApUserByUserCode(fileOperator);
			if (user != null && user.getUserName() != null && !("").equals(user.getUserName())) {
				String name = user.getUserName();
				String userName = name + "-" + fileOperator;
				arBatch1.setFileOperator(userName);
			}
		}
		return list;
	}

	@Override
	public int queryArchiveCount(ArBatch arBatch) {
		return getArBatchDao().queryArchiveCount(arBatch);
	}

	@Override
	public List<ArDetail> queryArDetailsList(ArDetail arDetail, int curNum, int pageNum) {
		return getArBatchDao().queryArDetailsList(arDetail, curNum, pageNum);
	}

	@Override
	public int queryArDetailsCount(ArDetail arDetail) {
		return getArBatchDao().queryArDetailsCount(arDetail);
	}

	public ArBatchDao getArBatchDao() {
		return arBatchDao;
	}

	public void setArBatchDao(ArBatchDao arBatchDao) {
		this.arBatchDao = arBatchDao;
	}
}
