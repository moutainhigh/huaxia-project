package com.huaxia.opas.action.archive;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.archive.ArBatch;
import com.huaxia.opas.domain.archive.ArDetail;
import com.huaxia.opas.service.archive.ArBatchService;
import com.huaxia.opas.util.TransDateUtil;

/**
 * 归档批次
 * 
 * @author qbn
 *
 */
public class ArchiveBatchAction implements Action{
	
	private static Logger logger = LoggerFactory.getLogger(ArchiveBatchAction.class);

	@Resource(name = "arBatchService")
	private ArBatchService arBatchService;
	
	/**
	 * 查询归档列表信息
	 * @throws Exception 
	 */
	public void listArchiveBatch(Context ctx) throws Exception {
		Gson gson = new Gson();
		Map map = ctx.getDataMap();
		
		if(map.get("fileTime")==""||map.get("fileTime")==null){
			map.remove("fileTime");
		}else{
			map.put("fileTime", TransDateUtil.String2Date((String) ctx.getDataMap().get("fileTime"), "yyyy-MM-dd"));
		}
		
		ArBatch arBatch = gson.fromJson(gson.toJson(map), ArBatch.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<ArBatch> list = new ArrayList<ArBatch>();

		int count = arBatchService.queryArchiveCount(arBatch);
		if (count > 0) {
			list = arBatchService.queryArchiveList(arBatch, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	
	public void listArchiveDetails(Context ctx) throws ParseException {
		Gson gson = new Gson();
		Map map = ctx.getDataMap();
		ArDetail arDetail = gson.fromJson(gson.toJson(map), ArDetail.class);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<ArDetail> list = new ArrayList<ArDetail>();

		int count = arBatchService.queryArDetailsCount(arDetail);
		if (count > 0) {
			list = arBatchService.queryArDetailsList(arDetail, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
}
