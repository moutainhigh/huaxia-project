package com.huaxia.opas.service.sysparm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.StockcustTelSale;
/**
 * 存量客户电销名单
 * @author 汪涛
 *
 */
public interface StockcustTelSaleService {

	int queryStockcustTelSaleCount(StockcustTelSale stockcustTelSale);
	/**
	 * 存量客户电销名单的查询列表显示
	 */
	List<StockcustTelSale> queryStockcustTelSaleList(StockcustTelSale stockcustTelSale, int curNum, int pageNum);
	/**
	 * 存量客户电销名单的新增
	 * @throws Exception 
	 */
	void insertStockcustTelSale(Context ctx) throws Exception;
	/**
	 * 存量客户电销名单的修改
	 * @throws Exception 
	 */
	void updateStockcustTelSale(Context ctx) throws Exception;
	/**
	 * 存量客户电销名单的删除
	 */
	void deleteStockcustTelSale(Context ctx);
	/**
	 * 存量客户电销的批量启用
	 */
	void batchStartStockcustTelSale(Context ctx);
	/**
	 * 存量客户电销的批量禁用
	 */
	void batchStopStockcustTelSale(Context ctx);
	/**
	 * 存量客户电销的批量导入
	 * @throws Exception 
	 */
	int insertStockImportFile(List<StockcustTelSale> stockcustTelSaleList,BatchfileInfo batchfileInfo) throws FileNotFoundException, Exception;
	/**
	 * 下面两个方法是存量客户电销的历史纪录列表显示
	 */
	int stockcustTelSaleHistoryCount(String autoId);

	List<StockcustTelSale> stockcustTelSaleHistory(String autoId, int curNum, int pageNum);

}
