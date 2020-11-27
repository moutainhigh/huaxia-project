package com.huaxia.opas.action.dict;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.cache.service.IDictService;
import com.huaxia.opas.domain.dict.ApDict;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.dict.ApDictDetailSmall;
import com.huaxia.opas.service.dict.ApDictDetailService;
import com.huaxia.opas.service.dict.ApDictService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 业务字典大类
 * 
 * @author shihuan
 *
 */
public class DictAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(DictAction.class);

	@Resource(name = "apDictService")
	private ApDictService apDictService;

	@Resource(name = "apDictDetailService")
	private ApDictDetailService apDictDetailService;

	@Resource(name = "dictService")
	private IDictService dictService;

	/**
	 * 业务字典大类分页查询
	 * 
	 * @param context
	 */
	public void queryDictList(Context context) {

		Gson json = new Gson();
		ApDict apDict = json.fromJson(json.toJson(context.getDataMap()),
				ApDict.class);
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get(
				"page") == null ? 0 : context.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get(
				"rows") == null ? 0 : context.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> dataMap = apDictService.queryDicts(apDict, curNum,
				pageNum);
		context.setDataMap(dataMap);
	}

	/**
	 * 业务字典大类删除
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void removeDict(Context context) throws Exception {
		try {
			String dictId = String.valueOf(context.getDataMap().get("dictId"));
			String dictCode = String.valueOf(context.getDataMap().get(
					"dictCode"));

			ApDict apDict = new ApDict();
			apDict.setDictId(dictId);

			int num = apDictService.remove(apDict);

			ApDictDetail apDictDetail = new ApDictDetail();
			apDictDetail.setDictId(dictId);

			int num1 = apDictDetailService.remove(apDictDetail);
			dictService.deleteDict(dictCode);
			context.setState(String.valueOf(num));
			context.setData("isSuccess", true);
		} catch (Exception e) {
			logger.error("业务字典大类删除异常.." + e.getMessage());
			throw new Exception();
		}

	}

	/**
	 * 业务字典大类修改
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void updateDict(Context context) throws Exception {
		try {
			Gson json = new Gson();
			ApDict apDict = json.fromJson(json.toJson(context.getDataMap()),ApDict.class);
			//判断业务字典大类修改时  是否重复
			List<ApDict> apDicts = apDictService.queryUpApDictByDictCode(apDict.getDictCode());
			if(apDicts!=null){
				if(apDicts.size()>0){
					context.setData("isCode", true);
					return;
				}
			}
			//判断业务字典大类修改时  是否重复
			List<ApDict> apDictss = apDictService.queryUpApDictByDictName(apDict.getDictName());
			if(apDictss!=null){
				if(apDictss.size()>0){
					context.setData("isName", true);
					return;
				}
			}
			int num = apDictService.update(apDict);
			context.setState(String.valueOf(num));
			refshDict(apDict.getDictCode());
			context.setData("isSuccess", true);
		} catch (Exception e) {
			logger.error("业务字典大类修改异常.." + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * 业务字典大类添加
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void addDict(Context context) throws Exception {
		Gson json = new Gson();
		String userId = (String) context.getDataMap().get("userId");
		ApDict apDict = json.fromJson(json.toJson(context.getDataMap()),ApDict.class);
		//判断业务字典大类添加时  是否重复
		ApDict apDicts = apDictService.queryApDictByDictCode(apDict.getDictCode());
		if(apDicts!=null&&!"".equals(apDicts)){
			context.setData("isCode", true);
			return;
		}
		//判断业务字典大类添加时  是否重复
		ApDict apDictss = apDictService.queryApDictByDictName(apDict.getDictName());
		if(apDictss!=null&&!"".equals(apDictss)){
			context.setData("isName", true);
			return;
		}
		apDict.setDictId(SequenceUtil.getUUID());
		apDict.setCrtUser(userId);
		int num = apDictService.save(apDict);
		context.setState(String.valueOf(num));
		context.setData("isSuccess", true);
	}

	/**
	 * 业务字典大类刷新
	 * 
	 * @param context
	 */
	public void dictRefsh(Context context) {
		try {
			String dictCode = String.valueOf(context.getDataMap().get(
					"dictCode"));
			refshDict(dictCode);
		} catch (Exception e) {
			logger.error("业务字典大类字典刷新异常.." + e.getMessage());
		}

	}

	/**
	 * 业务字典全量刷新
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void dictRefshAll(Context context) throws Exception {
		List<String> dictCodes = apDictService.queryDictCodes();
		dictService.deleteAllDict(dictCodes);
	}

	// 大类刷新
	public void refshDict(String dictCode) throws Exception {
		dictService.deleteDict(dictCode);
		List<ApDictDetailSmall> dicts = apDictDetailService
				.queryDictDetailByCode(dictCode);
		dictService.addDictContent(dictCode, dicts);
	}

	public ApDictService getApDictService() {
		return apDictService;
	}

	public void setApDictService(ApDictService apDictService) {
		this.apDictService = apDictService;
	}

	public ApDictDetailService getApDictDetailService() {
		return apDictDetailService;
	}

	public void setApDictDetailService(ApDictDetailService apDictDetailService) {
		this.apDictDetailService = apDictDetailService;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}
}
