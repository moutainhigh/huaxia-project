package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.MatchIdenityDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.MatchIdenity;
import com.huaxia.opas.service.sysparm.MatchIdenityService;

/**
 * 简项公安参数匹配
 * 
 * @author wangtao
 * @version v1.0 2017-10-12
 */
@SuppressWarnings("serial")
public class MatchIdenityServiceImpl implements MatchIdenityService, Serializable {

	@Resource(name = "matchIdenityDao")
	private MatchIdenityDao matchIdenityDao;

	// 插入历史记录
	@Resource(name = "batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;

	@Override
	public int queryMatchIdenityCount(MatchIdenity matchIdenity) {
		return matchIdenityDao.queryMatchIdenityCount(matchIdenity);
	}

	@Override
	public List<MatchIdenity> queryMatchIdenityList(MatchIdenity matchIdenity, int curNum, int pageNum) {
		return matchIdenityDao.queryMatchIdenityList(matchIdenity, curNum, pageNum);
	}

	@Override
	public int insertMatchIdenityStart(MatchIdenity matchIdenity) {
		return matchIdenityDao.insertMatchIdenityStart(matchIdenity);
	}

	@Override
	public int insertMatchIdenityEnd(MatchIdenity matchIdenity) {
		return matchIdenityDao.insertMatchIdenityEnd(matchIdenity);
	}

	@Override
	public String queryMatchIdenityStatus(String id) {
		return matchIdenityDao.queryMatchIdenityStatus(id);
	}

	@Override
	public int updateMatchIdenityWithoutStatus(MatchIdenity matchIdenity) {
		return matchIdenityDao.updateMatchIdenityWithoutStatus(matchIdenity);
	}

	@Override
	public int updateMatchIdenity(MatchIdenity matchIdenity) {
		return matchIdenityDao.updateMatchIdenity(matchIdenity);
	}

	@Override
	public int updateStartStatusMatchIdenity(MatchIdenity matchIdenity) {
		return matchIdenityDao.updateStartStatusMatchIdenity(matchIdenity);
	}

	@Override
	public int updateStopStatusMatchIdenity(MatchIdenity matchIdenity) {
		return matchIdenityDao.updateStopStatusMatchIdenity(matchIdenity);
	}

	@Override
	public int updateMatchIdenityWithViewFlag(MatchIdenity matchIdenity) {
		return matchIdenityDao.updateMatchIdenityWithViewFlag(matchIdenity);
	}

	/**
	 * 导入方法
	 */
	@Override
	public int insertRosterLibraryFromFile(List<MatchIdenity> rosterlist, BatchfileInfo batchfileInfo) {
		int inputCounds = matchIdenityDao.insertRosterLibraryFromFile(rosterlist);
		// 添加导入记录
		batchfileInfo.setInputCounts(new BigDecimal(inputCounds));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounds;
	}

	@Override
	public void deleteMatchIdenitys(MatchIdenity matchIdenity) {
		matchIdenityDao.deleteMatchIdenitys(matchIdenity);
	}

	@Override
	public void deleteAll() {
		matchIdenityDao.deleteAll();
	}

}
