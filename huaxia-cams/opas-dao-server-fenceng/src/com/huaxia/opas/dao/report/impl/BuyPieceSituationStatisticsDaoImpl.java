package com.huaxia.opas.dao.report.impl;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.BuyPieceSituationStatisticsDao;
import com.huaxia.opas.domain.report.EntryStatistics;


/**
 * @author gaohui(进件情况统计表)
 *
 */
public class BuyPieceSituationStatisticsDaoImpl extends AbstractDAO implements BuyPieceSituationStatisticsDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4977572751674129646L;
	private static final String NAMESPACES = "BuyPieceSituationStatistics.";
	@Override
	public List<EntryStatistics> selectBuyPieceSituationStatisticsDataByDate(Map<String, Object> paramsMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectBuyPieceSituationStatisticsDataByDate", paramsMap);
	}
}
