package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.UserCardDao;
import com.huaxia.opas.domain.sysparm.UserCard;
import com.huaxia.opas.service.sysparm.UserCardService;

public class UserCardServiceImpl extends AbstractDAO implements UserCardService  , Serializable{

	private static final long serialVersionUID = 5757582418545513970L;
	
	@Resource(name = "userCardDao")
	private UserCardDao userCardDao;

	public UserCardDao getUserCardDao() {
		return userCardDao;
	}

	public void setUserCardDao(UserCardDao userCardDao) {
		this.userCardDao = userCardDao;
	}

	@Override
	public int insert(UserCard usercard) throws CoreException {
		return getUserCardDao().insert(usercard);
	}

	@Override
	public int insertSelective(UserCard usercard) throws CoreException {
		return getUserCardDao().insertSelective(usercard);
	}

	@Override
	public int deleteByPrimaryKey(String cardId) throws CoreException {
		return getUserCardDao().deleteByPrimaryKey(cardId);
	}

	@Override
	public int updateByPrimaryKey(UserCard usercard) throws CoreException {
		return getUserCardDao().updateByPrimaryKey(usercard);
	}

	@Override
	public int updateByPrimaryKeySelective(UserCard usercard) throws CoreException {
		return getUserCardDao().updateByPrimaryKeySelective(usercard);
	}

	@Override
	public UserCard selectByPrimaryKey(String cardId) throws CoreException {
		return getUserCardDao().selectByPrimaryKey(cardId);
	}
	
	//根据用户ID查询   用于用户修改信心时  反显卡种类型  shihuan 2017-02-28
	@Override
	public List<UserCard> selectByUserid(String userId) throws CoreException{
		return getUserCardDao().selectByUserid(userId);
	}
	
	//根据用户ID删除   用于用户修改审批卡种是先删除后插入 shihuan 2017-03-03
	@Override
	public int deleteByUserId(String userId) throws CoreException {
		return getUserCardDao().deleteByUserId(userId);
	}
	
	//shihuan 2017-04-17 查询时用户审批卡种
	@Override
	public List<UserCard> queryUserCardList(){
		return getUserCardDao().queryUserCardList();
	}
	
	//shihuan 2017-04-17 查询时用户审批卡种
	@Override
	public int queryUserCardCount() {
		return getUserCardDao().queryUserCardCount();
	}

}
