package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasBizPubsecGatherDao;
import com.huaxia.opas.domain.rule.OpasBizPubsecGather;

public class OpasBizPubsecGatherDaoImpl extends AbstractDAO implements OpasBizPubsecGatherDao ,Serializable{
	@Override
	public int insert(OpasBizPubsecGather record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OpasBizPubsecGather> selectByExample(OpasBizPubsecGather example) {
		return getSqlMap().queryForList("OpasBizPubsecGather.selectByExample", example);
	}

	@Override
	public List<OpasBizPubsecGather> selectByAppid(String appId) {
		return getSqlMap().queryForList("OpasBizPubsecGather.selectByAppid", appId);
	}
	
	@Override
	public List<Map<String,String>> selectByIdnbr(String identityNo) {
		return getSqlMap().queryForList("OpasBizPubsecGather.selectByIdnbr", identityNo);
	}
	
	@Override
	public List<Map<String,String>> selectByAppidThd(String appIdThd) {
		return getSqlMap().queryForList("OpasBizPubsecGather.selectByAppidThd", appIdThd);
	}
	
	@Override
	public List<Map<String,String>> queryForeignCheckByAppId(String appIdThd) {
		return getSqlMap().queryForList("OpasBizPubsecGather.queryForeignCheckByAppId", appIdThd);
	}

	@Override
	public List<Map<String, String>> selectKexinMsgByAppidThd(String appIdThd) {
		return getSqlMap().queryForList("OpasBizPubsecGather.selectKexinMsgByAppidThd", appIdThd);
	}

}
