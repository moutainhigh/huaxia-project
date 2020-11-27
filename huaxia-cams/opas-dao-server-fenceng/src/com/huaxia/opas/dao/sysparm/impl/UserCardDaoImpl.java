package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.UserCardDao;
import com.huaxia.opas.domain.riskcheck.TelRiskList;
import com.huaxia.opas.domain.sysparm.UserCard;

public class UserCardDaoImpl extends AbstractDAO implements UserCardDao {

	private static final long serialVersionUID = 5757582418545513970L;
	
	private static final String NAMESPACES = "UserCard.";

	@Override
	public int insert(UserCard usercard) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insert", usercard);
	}

	@Override
	public int insertSelective(UserCard usercard) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertSelective", usercard);
	}

	@Override
	public int deleteByPrimaryKey(String cardId) throws CoreException {
		UserCard usercard = new UserCard();
		usercard.setUserId(cardId);
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", cardId);
	}

	@Override
	public int updateByPrimaryKey(UserCard usercard) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", usercard);
	}

	@Override
	public int updateByPrimaryKeySelective(UserCard usercard) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", usercard);
	}

	@Override
	public UserCard selectByPrimaryKey(String userId) throws CoreException {
		UserCard usercard = new UserCard();
		usercard.setUserId(userId);
		return (UserCard)(getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", userId));
	}
	
	//根据用户ID查询   用于用户修改信心时  反显卡种类型  shihuan 2017-02-28
	@Override
	public List<UserCard> selectByUserid(String userId) throws CoreException{
		return getSqlMap().queryForList(NAMESPACES + "selectByUserid", userId);
	}
	
	//根据用户ID删除   用于用户修改审批卡种是先删除后插入 shihuan 2017-03-03
	@Override
	public int deleteByUserId(String userId) throws CoreException {
		UserCard usercard = new UserCard();
		usercard.setUserId(userId);
		return getSqlMap().delete(NAMESPACES + "deleteByUserId", userId);
	}
	
	//shihuan 2017-04-17 查询时用户审批卡种
	@Override
	public List<UserCard> queryUserCardList() {

		List<UserCard> list = new ArrayList<UserCard>();

		list = getSqlMap().queryForList(NAMESPACES + "queryUserCardList");

		return list;
	}

	//shihuan 2017-04-17 查询时用户审批卡种数目
	@Override
	public int queryUserCardCount() {
		return getSqlMap().queryForObject(NAMESPACES + "queryUserCardCount");
	}
}
