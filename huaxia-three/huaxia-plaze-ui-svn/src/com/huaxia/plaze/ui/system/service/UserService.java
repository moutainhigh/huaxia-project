package com.huaxia.plaze.ui.system.service;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.User;

/**
 * 用户服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface UserService {

	/** 分页总数量 */
	int queryListCountByPage(PageProperty property);

	/** 分页总记录 */
	List<User> queryListByPage(PageProperty property);
	
	/** 删除指定编号对象和与之相关映射记录 */
	int removeObjectAndMappingById(String id);

	/** 删除指定编号对象 */
	int removeById(String id);
	
	/** 获取指定账户对象 */
	User queryByAccount(String account);
	
	/** 查询指定编号对象 */
	User queryById(String id);
	
	/** 保存对象 */
	int save(User user);
	
	/** 更新对象 */
	int updateByUser(User user);
	
	/** 根据记录编号更新对象 */
	int updateById(Map<String, Object> args);
	
	/** 根据账户编号更新对象 */
	int updateByAcct(Map<String, Object> args);
	int updateForResetById(Map<String, Object> args);

	/** 同步用户信息 */
	int synchronization(User user);
	
	/** 获取指定账户所对应的员工名称 */
	String getUserName(String account);
	
	/** 判断是否存在指定关键元素的用户 */
	boolean isKeyExist(Map<String, Object> args);
	
	/** 判断是否存在相同关键元素的用户 */
	boolean isKeySame(Map<String, Object> args);
	
}
