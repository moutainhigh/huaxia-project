package com.huaxia.opas.dao.thirdparty.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.QiYeDao;
import com.huaxia.opas.domain.thirdparty.QiYeBasic;
import com.huaxia.opas.domain.thirdparty.QiYeOrgbasic;
import com.huaxia.opas.domain.thirdparty.QiYeOrgdetail;
import com.huaxia.opas.domain.thirdparty.QiYePerson;
import com.huaxia.opas.domain.thirdparty.QiYePunishbreak;
import com.huaxia.opas.domain.thirdparty.QiYeShareholder;

public class QiYeDaoImpl extends AbstractDAO implements QiYeDao {
	private static final long serialVersionUID = 4716164023274520911L;
	
	private static final String NAMESPACES = "QiYe.";

	private Object getSqlMap;
	/**
	 * 企业与行业信息   结果展示
	 */
	@Override
	public Map<String, String> queryQiYecode(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryQiYeCode",appId);
	}

	/**
	 * 企业与行业信息  概要部分
	 */
	@Override
	public Map<String, String> queryQiYeInfoByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryQiYeDatails",appId);
	}

	/**
	 * 企业与行业信息   详情部分
	 */
	@Override
	public Map<String, String> queryQiYeDatailsByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryQiYeDatails",appId);
	}
	/**
	 * 企业与行业信息 查询条数详细部分
	 */
	@Override
	public Map<String, String> queryQiYeNumber(String yearNumber) {
		return getSqlMap().queryForObject(NAMESPACES + "queryQiYeNumber",yearNumber);
	}
	/**
	 * 查询年
	 */
	@Override
	public Map<String,String> queryTimeYear() {
		return getSqlMap().queryForObject(NAMESPACES + "queryYear");
	}
	/**
	 * 查询月
	 */
	@Override
	public Map<String,String> queryTimeMonth() {
		return getSqlMap().queryForObject(NAMESPACES + "queryMonth");
	}
	/**
	 * 查询有无当年数据条目
	 */
	@Override
	public Map<String, String> queryQiYeNumberByyear(String year) {
		return getSqlMap().queryForObject(NAMESPACES + "queryYearNumber",year);
	}
	/**
	 * 查询对应月份的成功条数
	 */
	@Override
	public Map<String,String> queryQiYeNumberBymonth(Map<String, String> queryMap) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMonthNumber",queryMap);
	}
	/**
	 * 插入新一年的数据
	 */
	@Override
	public int insertYear(Map<String, String> updateMap) {
		return getSqlMap().insert(NAMESPACES + "insterYear",updateMap);
	}
	/**
	 * 插入对应月份的成功条数
	 */
	@Override
	public int updateQiYeNumber(Map<String, String> updateMap) {
		return getSqlMap().update(NAMESPACES + "updateMonthNumber",updateMap);
	}

	@Override
	public List<QiYeBasic> queryQiYeBasic(String appId) {	
		return getSqlMap().queryForList(NAMESPACES + "queryQiYeBasic",appId);
	}

	@Override
	public List<QiYePerson> queryQiYePerson(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryQiYePerson",appId);
	}

	@Override
	public List<QiYePunishbreak> queryQiYePunishbreak(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryQiYePunishbreak",appId);
	}

	@Override
	public List<QiYeShareholder> queryQiYeShareholder(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryQiYeShareholder",appId);
	}

	@Override
	public List<QiYeOrgbasic> queryQiYeOrgbasic(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryQiYeOrgbasic",appId);
	}

	@Override
	public List<QiYeOrgdetail> queryQiYeQrgdetail(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryQiYeOrgdetail",appId);
	}

	@Override
	public Map<String, String> queryEetFrnameByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryEetFrnameByAppId",appId);
	}

}
