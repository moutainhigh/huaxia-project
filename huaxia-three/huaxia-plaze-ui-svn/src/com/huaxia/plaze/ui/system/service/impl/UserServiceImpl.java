package com.huaxia.plaze.ui.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.common.Account;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.PasswordDetail;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.mapper.PasswordDetailMapper;
import com.huaxia.plaze.ui.system.mapper.UserMapper;
import com.huaxia.plaze.ui.system.mapper.UserRoleMapper;
import com.huaxia.plaze.ui.system.service.UserService;
import com.huaxia.util.GuidUtil;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper<User> userMapper;
	
	@Resource
	private UserRoleMapper userRoleMapper;
	
	@Resource
	private PasswordDetailMapper passwordDetailMapper;
	
	@Override
	public int queryListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return userMapper.selectListCountByPage(args);
	}

	@Override
	public List<User> queryListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return userMapper.selectListByPage(args);
	}
	
	@Override
	public User queryByAccount(String account) {
		return userMapper.selectByUserAcct(account);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int synchronization(User user) {
		int result = 0;
		user.addPasswordExpireTime(30);

		// 更新初始口令
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("account", user.getAccount());
		args.put("password", user.getPassword());
		args.put("firstLogin", (user.isFirstTimeLogin() ? "1" : "0"));
		args.put("status", Account.ACTIVE.getStatus());
		args.put("passwordExpireTime", user.getPasswordExpireTime());
		result += userMapper.updateWithParamsByAcct(args);

		// 记录新口令
		PasswordDetail passwordDetail = new PasswordDetail();
		passwordDetail.setUuid(GuidUtil.getGuid());
		passwordDetail.setAccount(user.getAccount());
		passwordDetail.setPassword(user.getOldPassword());
		result += passwordDetailMapper.insertObject(passwordDetail);

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateById(Map<String, Object> args) {
		return userMapper.updateWithParamsById(args);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateByAcct(Map<String, Object> args) {
		return userMapper.updateWithParamsByAcct(args);
	}

	@Override
	public User queryById(String id) {
		return userMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int save(User user) {
		return userMapper.insert(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int removeObjectAndMappingById(String id) {
		int result = userRoleMapper.deleteListMappingById(id);
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", id);
		args.put("status", Account.CLOSE.getStatus());
		result += userMapper.updateWithParamsById(args);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int removeById(String id) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", id);
		args.put("status", Account.CLOSE.getStatus());
		return userMapper.updateWithParamsById(args);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateByUser(User user) {
		return userMapper.updateByUser(user);
	}

	@Override
	public String getUserName(String account) {
		User user = userMapper.selectByUserAcct(account);
		if (user != null) {
			return user.getStaffName();
		}
		return null;
	}

	@Override
	public boolean isKeyExist(Map<String, Object> args) {
		int result = userMapper.selectCountExistByKeyParam(args);
		return result > 0 ? true : false;
	}

	@Override
	public boolean isKeySame(Map<String, Object> args) {
		int result = userMapper.selectCountSameByKeyParam(args);
		return result > 0 ? true : false;
	}
	@Override
	public int updateForResetById(Map<String, Object> args) {
		return userMapper.updateForResetById(args);
	}
	
}
