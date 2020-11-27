/**
 * 
 */
package com.huaxia.opas.dao.report.impl;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.FishingDao;
import com.huaxia.opas.domain.report.Fishing;
import com.huaxia.opas.domain.report.OutsourceDetail;


/**
 * @author gaohui(捞件表)
 *
 */
public class FishingDaoImpl extends AbstractDAO implements FishingDao{

	private static final long serialVersionUID = -526339105200676851L;
	private static final String NAMESPACES = "Fishing.";
	@Override
	public String getFishingCountByCrtDateOrApplyChannle(Map<String, Object> paraMap) {
		return  this.getSqlMap().queryForObject(NAMESPACES + "getFishingCountByCrtDateOrApplyChannle", paraMap);
	}
	
	@Override
	public List<Fishing> findFishingListByCrtDateOrApplyChannle(Map<String, Object> paraMap, int pageNum, int pageRows) {
		return  getSqlMap().queryForList(NAMESPACES + "findFishingListByCrtDateOrApplyChannle", paraMap, pageNum,pageRows);
	}

	@Override
	public void insertFishing(Fishing addFish) {
		getSqlMap().insert(NAMESPACES+"insertFishing", addFish);
	}

	@Override
	public List<OutsourceDetail> selectListOutsource(Map<String, Object> paraMap, int pageNum, int pageRows) {
		return getSqlMap().queryForList(NAMESPACES + "selectListOutsource", paraMap, pageNum,pageRows);
	}

	@Override
	public int selectTodaySubmitNum(Map<String, Object> paraMaps) {
		return getSqlMap().queryForObject(NAMESPACES + "selectTodaySubmitNum", paraMaps);
	}

	@Override
	public int selectTodayDetailNum(Map<String, Object> paraMaps) {
		return getSqlMap().queryForObject(NAMESPACES + "selectTodayDetailNum", paraMaps);
	}

	@Override
	public int selectTodayInventoryNum(Map<String, Object> paraMaps) {
		return getSqlMap().queryForObject(NAMESPACES + "selectTodayInventoryNum", paraMaps);
	}

	@Override
	public int selectTodayBackNum(Map<String, Object> paraMaps) {
		return getSqlMap().queryForObject(NAMESPACES + "selectTodayBackNum", paraMaps);
	}
	
	@Override
	public int selectHistoryBackNum(Map<String, Object> paraMaps) {
		return getSqlMap().queryForObject(NAMESPACES + "selectHistoryBackNum", paraMaps);
	}
	
	@Override
	public OutsourceDetail selectEnterQueueDayNum(Map<String, Object> paraMaps) {
		return getSqlMap().queryForObject(NAMESPACES + "selectEnterQueueDayNum", paraMaps);
	}

	@Override
	public List<OutsourceDetail> selectReturnNumInfo(Map<String, Object> map) {
		return getSqlMap().queryForList(NAMESPACES + "selectReturnNumInfo", map);
	}

	@Override
	public List<OutsourceDetail> selectNowdayInfo(Map<String, Object> map) {
		return getSqlMap().queryForList(NAMESPACES + "selectNowdayInfo", map);
	}

	
}
