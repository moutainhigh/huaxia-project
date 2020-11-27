package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.StockcustTelSaleDao;
import com.huaxia.opas.domain.sysparm.StockcustTelSale;
/**
 * 存量客户电销名单
 * @author 汪涛
 *
 */
public class StockcustTelSaleDaoImpl extends AbstractDAO implements StockcustTelSaleDao {
	
	private static final long serialVersionUID = 9022780379091571072L;
	private static final String NAMESPACES = "StockcustTelSale.";
	
	@Override
	public int queryStockcustTelSaleCount(StockcustTelSale stockcustTelSale) {
		return getSqlMap().queryForObject(NAMESPACES + "queryStockcustTelSaleCount", stockcustTelSale);
	}
	/**
	 * 存量客户电销名单列表显示
	 */
	@Override
	public List<StockcustTelSale> queryStockcustTelSaleList(StockcustTelSale stockcustTelSale, int curNum,
			int pageNum) {
		List<StockcustTelSale> list=new ArrayList<StockcustTelSale>();
		list= getSqlMap().queryForList(NAMESPACES + "queryStockcustTelSaleList", stockcustTelSale, curNum, pageNum);
		return list;
	}
	/**
	 * 存量客户电销名单新增
	 */
	@Override
	public int insertStockcustTelSale(StockcustTelSale stockcustTelSale) {
		return getSqlMap().insert(NAMESPACES + "insertStockcustTelSale", stockcustTelSale);
	}
	/**
	 * 存量客户电销名单修改
	 */
	@Override
	public int updateStockcustTelSale(StockcustTelSale stockcustTelSale) {
		return getSqlMap().update(NAMESPACES + "updateStockcustTelSale", stockcustTelSale);
	}
	/**
	 * 存量客户电销名单删除
	 */
	@Override
	public int deleteStockcustTelSale(List<String> autoIds) {
		return getSqlMap().delete(NAMESPACES + "deleteStockcustTelSale", autoIds);
	}
	/**
	 * 存量客户电销名单批量启用
	 */
	@Override
	public int batchStartStockcustTelSale(List<Map<Object,Object>> stockcustTelSaleMaps) {
		return getSqlMap().update(NAMESPACES + "batchStartStockcustTelSale", stockcustTelSaleMaps);
	}
	/**
	 * 存量客户电销名单批量禁用
	 */
	@Override
	public int batchStopStockcustTelSale(List<Map<Object,Object>> stockcustTelSaleMaps) {
		return getSqlMap().update(NAMESPACES + "batchStopStockcustTelSale", stockcustTelSaleMaps);
	}
	/**
	 * 存量客户电销名单批量导入
	 */
	@Override
	public int insertStockImportFile(List<StockcustTelSale> stockcustTelSaleList) {
		return getSqlMap().insert(NAMESPACES + "insertStockImportFile", stockcustTelSaleList);
	}
	@Override
	public StockcustTelSale selectByPrimaryKey(String autoId) {
		return (StockcustTelSale) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	/**
	 * 下面两个方法是存量客户电销的历史纪录列表显示 
	 */
	@Override
	public int stockcustTelSaleHistoryCount(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "stockcustTelSaleHistoryCount", autoId);
	}

	@Override
	public List<StockcustTelSale> stockcustTelSaleHistory(String autoId, int curNum, int pageNum) {
		List<StockcustTelSale> list=new ArrayList<StockcustTelSale>();
		list= getSqlMap().queryForList(NAMESPACES + "stockcustTelSaleHistory", autoId, curNum, pageNum);
		return list;
	}
	/**
	 * 新增和修改时插入一条历史纪录
	 */
	@Override
	public int insertStockcustTelSaleHis(StockcustTelSale stockcustTelSale) {
		return getSqlMap().insert(NAMESPACES + "insertStockcustTelSaleHis", stockcustTelSale);
	}
	/**
	 * 导入时插入历史纪录
	 */
	@Override
	public int insertStockcustTelSaleHisOfImport(List<StockcustTelSale> stockcustTelSaleList) {
		return getSqlMap().insert(NAMESPACES + "insertStockcustTelSaleHisOfImport", stockcustTelSaleList);
	}
	/**
	 * 批量启用和禁用时添加历史记录
	 */
	@Override
	public int insertStockcustTelSaleHisOfBatch(List<Map<Object, Object>> stockcustTelSaleMaps) {
		return getSqlMap().insert(NAMESPACES + "insertStockcustTelSaleHisOfBatch", stockcustTelSaleMaps);
	}
}
