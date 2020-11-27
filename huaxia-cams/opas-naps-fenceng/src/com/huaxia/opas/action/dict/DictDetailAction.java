package com.huaxia.opas.action.dict;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.service.IDictService;
import com.huaxia.opas.domain.dict.ApDict;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.dict.ApDictDetailSmall;
import com.huaxia.opas.service.dict.ApDictDetailService;
import com.huaxia.opas.service.dict.ApDictService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 业务字典小类
 * 
 * @author shihuan
 *
 */
public class DictDetailAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(DictDetailAction.class);

	@Resource(name = "dictService")
	private IDictService dictService;

	@Resource(name = "apDictService")
	private ApDictService apDictService;

	@Resource(name = "apDictDetailService")
	private ApDictDetailService apDictDetailService;

	/**
	 * 下拉框业务字典查询服务
	 * 
	 * @param context
	 */
	public void loadDictServer(Context context) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		// 大类编码
		String dictCode = String.valueOf(context.getDataMap().get("dictCode"));
		List<ApDictDetailSmall> dictDetails = apDictDetailService.queryDictDetailByCode(dictCode);
		dataMap.put("dicts", dictDetails);
		context.setDataMap(dataMap);
	}

	/**
	 * 业务字典小类分页查询
	 * 
	 * @param context
	 */
	public void queryDictDetailList(Context context) {
		Gson json = new Gson();
		ApDictDetail apDictDetail = json.fromJson(json.toJson(context.getDataMap()), ApDictDetail.class);
		int curNum = 0;
		int curPage = Integer.parseInt(
				String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		int pageNum = Integer.parseInt(
				String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> dataMap = apDictDetailService.queryDictDetails(apDictDetail, curNum, pageNum);
		context.setDataMap(dataMap);
	}

	/**
	 * 业务字典小类删除
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void removeDictDetail(Context context) throws CoreException {
		String dictDetailId = String.valueOf(context.getDataMap().get("dictDetailId"));
		String dictId = String.valueOf(context.getDataMap().get("dictId"));
		ApDictDetail apDictDetail = new ApDictDetail();
		apDictDetail.setDictDetailId(dictDetailId);
		int num = apDictDetailService.remove(apDictDetail);
		updateDictDetailCache(dictId);
		context.setState(String.valueOf(num));
		context.setData("isSuccess", true);
	}

	/**
	 * 业务字典小类添加
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void saveDictDetail(Context context) throws CoreException {
		Gson json = new Gson();
		String userId = (String) context.getDataMap().get("userId");
		ApDictDetail apDictDetail = json.fromJson(json.toJson(context.getDataMap()), ApDictDetail.class);
		String dictDetailCode = apDictDetail.getDictDetailCode();
		String dictDetailName = apDictDetail.getDictDetailName();
		String dictId = apDictDetail.getDictId();
		ApDictDetail apDictDetail1 = new ApDictDetail();
		apDictDetail1.setDictId(dictId);
		apDictDetail1.setDictDetailCode(dictDetailCode);
		apDictDetail1.setDictDetailName(dictDetailName);
		int n = apDictDetailService.queryApDetailByDictCode(apDictDetail1);
		if (n > 0) {
			context.setData("isCode", true);
			return;
		}
		int count = apDictDetailService.queryApDetailByDictName(apDictDetail1);
		if (count > 0) {
			context.setData("isName", true);
			return;
		}
		apDictDetail.setDictDetailId(SequenceUtil.getUUID());
		apDictDetail.setCrtUser(userId);
		int num = apDictDetailService.save(apDictDetail);
		updateDictDetailCache(apDictDetail.getDictId());
		context.setState(String.valueOf(num));
		context.setData("isSuccess", true);
	}

	/**
	 * 业务字典小类修改
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void updateDictDetail(Context context) throws CoreException {
		Gson json = new Gson();
		ApDictDetail apDictDetail = json.fromJson(json.toJson(context.getDataMap()), ApDictDetail.class);
		String dictId = apDictDetail.getDictId();
		String dictDetailId = apDictDetail.getDictDetailId();
		String dictDetailCode = apDictDetail.getDictDetailCode();
		String dictDetailName = apDictDetail.getDictDetailName();
		ApDictDetail apDictDetail2 = new ApDictDetail();
		apDictDetail2.setDictId(dictId);
		apDictDetail2.setDictDetailCode(dictDetailCode);
		apDictDetail2.setDictDetailName(dictDetailName);
		ApDictDetail apDictDetail1 = apDictDetailService.queryApDictDetailByDictDetailId(dictDetailId);
		if (!apDictDetail1.getDictDetailCode().equals(apDictDetail.getDictDetailCode())) {
			int n = apDictDetailService.queryApDetailByDictCode(apDictDetail2);
			if (n > 0) {
				context.setData("isCode", true);
				return;
			}
		}
		if (!apDictDetail1.getDictDetailName().equals(apDictDetail.getDictDetailName())) {
			int count = apDictDetailService.queryApDetailByDictName(apDictDetail2);
			if (count > 0) {
				context.setData("isName", true);
				return;
			}
		}
		int num = apDictDetailService.update(apDictDetail);
		updateDictDetailCache(apDictDetail.getDictId());
		context.setState(String.valueOf(num));
		context.setData("isSuccess", true);
	}

	/**
	 * 页面，业务字典下拉选项加载
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void loadDict(Context context) throws Exception {
		String dictData = String.valueOf(context.getDataMap().get("dictData"));

		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(dictData)) {

			dictData = dictData.substring(0, dictData.length() - 1);

			Gson gson = new Gson();

			Map<String, String> dictMap = dictService.getDictMap(dictData);
			if (dictMap != null) {
				Iterator<Entry<String, String>> it = dictMap.entrySet().iterator();

				Entry<String, String> entry = null;
				while (it.hasNext()) {
					entry = it.next();

					// 转换成对象
					returnMap.put(entry.getKey(),
							gson.fromJson(entry.getValue(), new TypeToken<List<ApDictDetailSmall>>() {
							}.getType()));
				}
			}
		}
		context.setDataMap(returnMap);
	}

	// 业务字典小类缓存更改
	public void updateDictDetailCache(String dictId) {
		try {
			ApDict dict = apDictService.queryApDictByDictId(dictId);
			dictService.deleteDict(dict.getDictCode());
			List<ApDictDetailSmall> dicts = apDictDetailService.queryDictDetailByCode(dict.getDictCode());
			dictService.addDictContent(dict.getDictCode(), dicts);
		} catch (Exception e) {
			logger.error("业务字典小类缓存更新异常..", e);
		}

	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
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

}
