package com.huaxia.opas.dao.credit.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.credit.CreditWhiteListDao;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
/**
 * 征信白名单dao层实现类
 * @author susha 2017/03/20
 *
 */
public class CreditWhiteListDaoImpl extends AbstractDAO implements CreditWhiteListDao {

	private static final long serialVersionUID = 5839271984710046311L;

	private static final String NAMESPACES = "CreditWhiteList.";
	//数量查询
	public int queryCount(CreditWhiteList creditWhiteList) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCount", creditWhiteList);
	}
	//列表查询(含模糊查询)
//	public List<CreditWhiteList> queryList(CreditWhiteList creditWhiteList, int curNum, int pageNum)
//			throws CoreException {
//		return getSqlMap().queryForList(NAMESPACES + "queryCreditWhiteList", creditWhiteList, curNum, pageNum);
//	}
	public List<Map<Object, Object>> queryList(CreditWhiteList creditWhiteList, int curNum, int pageNum)
			throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryCreditWhiteList", creditWhiteList, curNum, pageNum);
	}
	//批量删除(含单条)
	public int deleteList(List list) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteList", list);
	}
	//批量审核确认
	public int insertList(List list) throws CoreException {

		return getSqlMap().insert(NAMESPACES + "insertList", list);
	}
	//单条审核确认
	public int save(CreditWhiteList t) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertOne", t);
	}
	//查询已完成的数量
	public int queryFinishedCount(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryFinishedCount", map);
	}
	//查询未完成的数量
	public int queryUnfinishedCount(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryUnfinishedCount", map);
	}
	//查询提交到其他环节的数量
	public int querySub2othersCount(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySub2othersCount", map);
	}
	//更新中间表状态
	public int update(CreditWhiteList t) throws CoreException {
		return getSqlMap().update(NAMESPACES+"updateOne", t);
	}
	//批量更新中间表状态
	public int updateList(List list) throws CoreException {
		return getSqlMap().update(NAMESPACES+"updateList", list);
	}
	//拒绝添加
	public int refuse(CreditWhiteList creditWhiteList) throws CoreException {
		return getSqlMap().update(NAMESPACES+"refuse", creditWhiteList);
	}
	@Override
	public void insertCreditWhiteCenter(CreditWhiteList creditWhiteList) {
		 getSqlMap().insert(NAMESPACES+"insertCreditWhiteCenter", creditWhiteList);
	}
	@Override
	public int queryCreditWhiteCountInfo(CreditWhiteList creditWhiteList) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCreditWhiteCountInfo", creditWhiteList);
	}
	@Override
	public List<CreditWhiteList> queryCreditWhiteListInfo(CreditWhiteList creditWhiteList, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryCreditWhiteListInfo", creditWhiteList, curNum, pageNum);
	}

}
