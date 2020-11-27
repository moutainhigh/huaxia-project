package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.MatchIdenity;
import com.huaxia.opas.domain.sysparm.PreCreditParam;
/**
 * 简项公安参数匹配
 * @author wangtao
 * @version v1.1
 *2017-10-12
 */
public interface MatchIdenityDao {

	public int queryMatchIdenityCount(MatchIdenity matchIdenity);

	public List<MatchIdenity> queryMatchIdenityList(MatchIdenity matchIdenity, int curNum, int pageNum);

	public int insertMatchIdenityStart(MatchIdenity matchIdenity);

	public int insertMatchIdenityEnd(MatchIdenity matchIdenity);

	public String queryMatchIdenityStatus(String id);

	public int updateMatchIdenityWithoutStatus(MatchIdenity matchIdenity);

	public int updateMatchIdenity(MatchIdenity matchIdenity);

	public int updateStartStatusMatchIdenity(MatchIdenity matchIdenity);

	public int updateStopStatusMatchIdenity(MatchIdenity matchIdenity);

	public int updateMatchIdenityWithViewFlag(MatchIdenity matchIdenity);

	public int insertRosterLibraryFromFile(List<MatchIdenity> rosterlist);

	public void deleteMatchIdenitys(MatchIdenity matchIdenity);

	public void deleteAll();


}
