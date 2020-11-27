package com.huaxia.opas.dao.sysparm.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.MatchIdenityDao;
import com.huaxia.opas.dao.sysparm.PreCreditParamDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.MatchIdenity;
import com.huaxia.opas.domain.sysparm.PreCreditParam;
/**
 * 简项公安参数匹配
 * @author wangtao
 *@version v1.0
 *2017-10-12
 */
public class MatchIdenityDaoImpl extends AbstractDAO  implements MatchIdenityDao {

	private static final long serialVersionUID = 6497434023765964100L;
	private static final String NAMESPACES = "MatchIdenity.";

	@Override
	public int queryMatchIdenityCount(MatchIdenity matchIdenity) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMatchIdenityCount", matchIdenity);
	}
	@Override
	public List<MatchIdenity> queryMatchIdenityList(MatchIdenity matchIdenity, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryMatchIdenityList",matchIdenity,curNum,pageNum);
	}
	@Override
	public int insertMatchIdenityStart(MatchIdenity matchIdenity) {
		return getSqlMap().insert(NAMESPACES + "insertMatchIdenityStart", matchIdenity);
	}
	@Override
	public int insertMatchIdenityEnd(MatchIdenity matchIdenity) {
		return getSqlMap().insert(NAMESPACES + "insertMatchIdenityEnd", matchIdenity);
	}
	@Override
	public String queryMatchIdenityStatus(String id) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMatchIdenityStatus", id);
	}
	@Override
	public int updateMatchIdenityWithoutStatus(MatchIdenity matchIdenity) {
		return getSqlMap().update(NAMESPACES + "updateMatchIdenityWithoutStatus", matchIdenity);
	}
	@Override
	public int updateMatchIdenity(MatchIdenity matchIdenity) {
		return getSqlMap().update(NAMESPACES + "updateMatchIdenity", matchIdenity);
	}
	@Override
	public int updateStartStatusMatchIdenity(MatchIdenity matchIdenity) {
		return getSqlMap().update(NAMESPACES + "updateStartStatusMatchIdenity", matchIdenity);
	}
	@Override
	public int updateStopStatusMatchIdenity(MatchIdenity matchIdenity) {
		return getSqlMap().update(NAMESPACES + "updateStopStatusMatchIdenity", matchIdenity);
	}
	@Override
	public int updateMatchIdenityWithViewFlag(MatchIdenity matchIdenity) {
		return  getSqlMap().update(NAMESPACES + "updateMatchIdenityWithViewFlag", matchIdenity);
	}
	@Override
	public int insertRosterLibraryFromFile(List<MatchIdenity> rosterlist) {
		return getSqlMap().insert(NAMESPACES + "inputMatchIdenityFile",rosterlist);
	}
	@Override
	public void deleteMatchIdenitys(MatchIdenity matchIdenity) {
		List<String> ids = matchIdenity.getIds();
		getSqlMap().update(NAMESPACES + "deleteMatchIdenitys",ids);
	}
	@Override
	public void deleteAll() {
		getSqlMap().update(NAMESPACES + "deleteAll");		
	}

}
