package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.sysparm.StockcustTelSale;
/**
 * 存量客户电销名单
 * @author 汪涛
 *
 */
public interface StockcustTelSaleDao {
	StockcustTelSale selectByPrimaryKey(String autoId);
	
	int queryStockcustTelSaleCount(StockcustTelSale stockcustTelSale);
	/**
	 * 存量客户电销名单的列表显示
	 */
	List<StockcustTelSale> queryStockcustTelSaleList(StockcustTelSale stockcustTelSale, int curNum, int pageNum);
	/**
	 * 存量客户电销名单的新增
	 */
	int insertStockcustTelSale(StockcustTelSale stockcustTelSale);
	/**
	 * 存量客户电销名单的修改
	 */
	int updateStockcustTelSale(StockcustTelSale stockcustTelSale);
	/**
	 * 存量客户电销名单的删除
	 */
	int deleteStockcustTelSale(List<String> autoIds);
	/**
	 * 存量客户电销名单的批量启用
	 */
	int batchStartStockcustTelSale(List<Map<Object,Object>> stockcustTelSaleMaps);
	/**
	 * 存量客户电销名单的批量禁用
	 */
	int batchStopStockcustTelSale(List<Map<Object,Object>> stockcustTelSaleMaps);
	/**
	 * 存量客户电销名单的批量导入
	 */
	int insertStockImportFile(List<StockcustTelSale> stockcustTelSaleList);
	/**
	 * 下面两个方法是存量客户电销名单的历史纪录列表显示
	 */
	int stockcustTelSaleHistoryCount(String autoId);

	List<StockcustTelSale> stockcustTelSaleHistory(String autoId, int curNum, int pageNum);
	//存量客户电销历史纪录的添加（对应新增和修改）
	int insertStockcustTelSaleHis(StockcustTelSale stockcustTelSale);
	//存量客户电销历史纪录的添加（对应导入）
	int insertStockcustTelSaleHisOfImport(List<StockcustTelSale> stockcustTelSaleList);
	//对应批量启用和禁用时添加的历史记录
	int insertStockcustTelSaleHisOfBatch(List<Map<Object, Object>> stockcustTelSaleMaps);

}
