package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.MatchIdenity;
/**
 * 简项公安参数匹配
 * @author wangtao 
 * @version v1.0
 * 2017-10-12
 *
 */
public interface MatchIdenityService {
	int queryMatchIdenityCount(MatchIdenity matchIdenity);

	List<MatchIdenity> queryMatchIdenityList(MatchIdenity matchIdenity, int curNum, int pageNum);

	int insertMatchIdenityStart(MatchIdenity matchIdenity);

	int insertMatchIdenityEnd(MatchIdenity matchIdenity);

	String queryMatchIdenityStatus(String id);

	int updateMatchIdenityWithoutStatus(MatchIdenity matchIdenity);

	int updateMatchIdenity(MatchIdenity matchIdenity);

	int updateStartStatusMatchIdenity(MatchIdenity matchIdenity);

	int updateStopStatusMatchIdenity(MatchIdenity matchIdenity);

	int updateMatchIdenityWithViewFlag(MatchIdenity matchIdenity);

	int insertRosterLibraryFromFile(List<MatchIdenity> rosterlist, BatchfileInfo batchfileInfo);

	void deleteMatchIdenitys(MatchIdenity matchIdenity);

	void deleteAll();
}
