package com.huaxia.plaze.ui.pboc.support;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.ui.pboc.domain.SingleQueryReview;
import com.huaxia.plaze.ui.pboc.domain.SysLogQuery;
import com.huaxia.plaze.ui.pboc.service.LogQueryService;
import com.huaxia.plaze.ui.pboc.service.SingleQueryService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.service.DictionaryService;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

@Component
public class QueryLogSupport {

	private final static Logger logger = LoggerFactory.getLogger(QueryLogSupport.class);

	@Autowired
	private DictionaryService dictionaryService;

	@Autowired
	private SingleQueryService singleQueryService;

	@Autowired
	private LogQueryService sysLogQueryService;

	/**
	 * 插入查询日志
	 * 
	 * @param id
	 *            根据id查询被查询人的身份信息
	 * @param type
	 *            查询来源 0-单条实时 1-单条查找（历史数据查询）
	 * @param queryAction
	 *            查询动作
	 * @param queryExclude
	 *            0-正常查询 1-例外
	 * @return
	 */
	public void queryAfter(String id, String type, String queryAction, String queryExclude) {
		try {
			String[] ids = id.split(",");
			Map<String, String> map = new HashMap<>();
			map.put("groupCode", "QUERY_TYPE");
			// 获取request
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			User loginUser = JacksonUtil.jsonToObject(body, User.class);
			for (int i = 0; i < ids.length; i++) {
				SingleQueryReview review = singleQueryService.queryObjectById(ids[i]);
				//映射字典表中的人行查询原因:贷前审批03 映射为1，贷后01为2，特约商户查询19映射为3
				if(StringUtils.isNotBlank(review.getQueryType())&& "03".equals(review.getQueryType()))
				{
					map.put("dictCode", "1");
				} else
				if(StringUtils.isNotBlank(review.getQueryType())&& "01".equals(review.getQueryType()))
				{
					map.put("dictCode", "2");
				} else
				if(StringUtils.isNotBlank(review.getQueryType())&& "19".equals(review.getQueryType()))
				{
					map.put("dictCode", "3");
				} else{
					map.put("dictCode", review.getQueryType());
				}
				SysLogQuery query = new SysLogQuery();
				query.setCertNo(review.getCertNo());
				query.setName(review.getName());
				query.setCrtUser(loginUser.getAccount());
				query.setQueryReason(dictionaryService.queryName(map));
				query.setTrnId(review.getTrnId());
				query.setStaffCertNo(loginUser.getCertNo());
				query.setStaffName(loginUser.getStaffName());
				query.setIp(loginUser.getIp());
				query.setQueryType(type);
				query.setQueryAction(queryAction);
				query.setQueryExclude(queryExclude);
				sysLogQueryService.saveObject(query);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 插入查询日志 (单条查找)
	 * 
	 * @param id
	 *            根据id查询被查询人的身份信息
	 * @param type
	 *            查询来源 0-单条实时 1-单条查找（历史数据查询）
	 * @param queryAction
	 *            查询动作
	 * @param queryExclude
	 *            0-正常查询 1-例外
	 * @return
	 */
	public void queryAfterSearch(String certNo, String type, String queryAction, String queryExclude) {
		try {
			// 获取request
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			User loginUser = JacksonUtil.jsonToObject(body, User.class);
			SysLogQuery query = new SysLogQuery();
			query.setCertNo(certNo);
			query.setCrtUser(loginUser.getAccount());
			query.setStaffCertNo(loginUser.getCertNo());
			query.setStaffName(loginUser.getStaffName());
			query.setIp(loginUser.getIp());
			query.setQueryType(type);
			query.setQueryAction(queryAction);
			query.setQueryExclude(queryExclude);
			sysLogQueryService.saveObject(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 插入阻断查询日志
	 * 
	 * @param review
	 *            查询被查询人的身份信息
	 * @param type
	 *            查询来源 0-单条实时 1-单条查找（历史数据查询）
	 * @param queryAction
	 *            查询动作
	 * @param queryExclude
	 *            0-正常查询 1-例外
	 * @return
	 */
	public void queryAfterForbid(SingleQueryReview review, String type, String queryAction, String queryExclude,
			Integer preventCount, String preventReason) {
		try {
			Map<String, String> map = new HashMap<>();
			map.put("groupCode", "QUERY_TYPE");
			
			// 获取request
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			User loginUser = JacksonUtil.jsonToObject(body, User.class);
			map.put("dictCode", review.getQueryType());
			SysLogQuery query = new SysLogQuery();
			query.setCertNo(review.getCertNo());
			query.setName(review.getName());
			query.setCrtUser(loginUser.getAccount());
			query.setQueryReason(dictionaryService.queryName(map));
			query.setStaffCertNo(loginUser.getCertNo());
			query.setStaffName(loginUser.getStaffName());
			query.setIp(loginUser.getIp());
			query.setQueryType(type);
			query.setQueryAction(queryAction);
			query.setQueryExclude(queryExclude);
			query.setPreventQueryCount(preventCount);
			query.setPreventQueryReason(preventReason);
			sysLogQueryService.saveObject(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 插入阻断查询日志
	 * 
	 * @param review
	 *            查询被查询人的身份信息
	 * @param type
	 *            查询来源 0-单条实时 1-单条查找（历史数据查询）
	 * @param queryAction
	 *            查询动作
	 * @param queryExclude
	 *            0-正常查询 1-例外
	 * @return
	 */
	public void queryAfterForbidSearch(String certNo, String type, String queryAction, String queryExclude,
			int preventCount, String preventReason) {
		try {
			// 获取request
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			User loginUser = JacksonUtil.jsonToObject(body, User.class);
			SysLogQuery query = new SysLogQuery();
			query.setCertNo(certNo);
			query.setCrtUser(loginUser.getAccount());
			query.setStaffCertNo(loginUser.getCertNo());
			query.setStaffName(loginUser.getStaffName());
			query.setIp(loginUser.getIp());
			query.setQueryType(type);
			query.setQueryAction(queryAction);
			query.setQueryExclude(queryExclude);
			query.setPreventQueryCount(preventCount);
			query.setPreventQueryReason(preventReason);
			sysLogQueryService.saveObject(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
