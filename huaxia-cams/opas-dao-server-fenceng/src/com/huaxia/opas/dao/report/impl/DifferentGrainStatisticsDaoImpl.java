package com.huaxia.opas.dao.report.impl;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.DifferentGrainStatisticsDao;


/**
 * @author gaohui(不同粒度进件情况统计表)
 *
 */
public class DifferentGrainStatisticsDaoImpl extends AbstractDAO implements DifferentGrainStatisticsDao{
	private static final long serialVersionUID = 4078028872141786897L;
	private static final String NAMESPACES = "DifferentGrainStatistics.";
	@Override
	public List<Map<String, Object>> selectDifferentGrainStatisticsDataByDateDimension(Map<String, Object> paramsMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectDifferentGrainStatisticsDataByDateDimension", paramsMap);
	}
	@Override
	public List<Map<String, Object>> selectApplicationCardProducts() {
		return getSqlMap().queryForList(NAMESPACES + "selectApplicationCardProducts");
	}


}
