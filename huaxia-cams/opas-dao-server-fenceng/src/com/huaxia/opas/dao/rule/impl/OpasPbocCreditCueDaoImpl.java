package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasPbocCreditCueDao;
import com.huaxia.opas.domain.rule.OpasPbocCreditCue;

public class OpasPbocCreditCueDaoImpl extends AbstractDAO implements OpasPbocCreditCueDao ,Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4593888276480192526L;


	@Override
	public int insert(OpasPbocCreditCue record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<OpasPbocCreditCue> selectByExample(String  appId) {
		
		return getSqlMap().queryForList("OpasPbocCreditCue.selectByExample",appId);
	}


	@Override
	public List<String> queryPbocPhone(String appId) {
		return getSqlMap().queryForList("OpasPbocCreditCue.queryPbocPhone", appId);
	}
	
	// 人行有民事判决记录
	@Override
	public int selectCivjudgeCount(String appId) {
		return getSqlMap().queryForObject("OpasPbocCreditCue.selectCivjudgeCount", appId);
	}
	
	// 人行有强制执行记录
	@Override
	public int selectForceexeCount(String appId) {
		return getSqlMap().queryForObject("OpasPbocCreditCue.selectForceexeCount", appId);
	}
	
	// 人行有无记录(有无个人信息)
	@Override
	public List<String> selectPbocMsg(String appId) {
		return getSqlMap().queryForList("OpasPbocCreditCue.selectPbocMsg", appId);
	}
	
	// 人行账户社保信息有无异常
	@Override
	public List<String> selectEninsurdepMsg(String appId) {
		return getSqlMap().queryForList("OpasPbocCreditCue.selectEninsurdepMsg", appId);
	}
	
	// 人行账户公积金信息有无异常
	@Override
	public Map<String, String> selectAccfundMsg(String appId) {
		return getSqlMap().queryForObject("OpasPbocCreditCue.selectAccfundMsg", appId);
	}
	
	// 人行有无社保信息记录
	@Override
	public int selectEninsurdepMsgCount(String appId) {
		return getSqlMap().queryForObject("OpasPbocCreditCue.selectEninsurdepMsgCount", appId);
	}
	
	// 人行有无公积金信息记录
	@Override
	public int selectAccfundMsgCount(String appId) {
		return getSqlMap().queryForObject("OpasPbocCreditCue.selectAccfundMsgCount", appId);
	}
		
}
