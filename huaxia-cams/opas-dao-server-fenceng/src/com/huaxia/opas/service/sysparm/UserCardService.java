package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.UserCard;

public interface UserCardService {
    int deleteByPrimaryKey(String cardId) throws CoreException;

    int insert(UserCard usercard) throws CoreException;

    int insertSelective(UserCard usercard) throws CoreException;

    UserCard selectByPrimaryKey(String cardId) throws CoreException;

    int updateByPrimaryKeySelective(UserCard usercard) throws CoreException;

    int updateByPrimaryKey(UserCard usercard) throws CoreException;
    
    //根据用户ID查询   用于用户修改信心时  反显卡种类型  shihuan 2017-02-28
    List<UserCard> selectByUserid(String userId) throws CoreException;
    
    //根据用户ID删除   用于用户修改审批卡种是先删除后插入 shihuan 2017-03-03
    int deleteByUserId(String userId) throws CoreException;
    
    //shihuan 2017-04-17 查询时用户审批卡种
  	List<UserCard> queryUserCardList();

  	//shihuan 2017-04-17 查询时用户审批卡种数目
  	int queryUserCardCount();
}