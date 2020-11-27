package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.rule.OpasBizPubsecGather;

public interface OpasBizPubsecGatherDao {

    int insert(OpasBizPubsecGather record);

    List<OpasBizPubsecGather> selectByExample(OpasBizPubsecGather example);
    
    List<OpasBizPubsecGather> selectByAppid(String appId);
    
    //2017/07/20 from wjf
  	List<Map<String,String>> selectByIdnbr(String identityNo);

	List<Map<String, String>> selectByAppidThd(String appIdThd);

	List<Map<String, String>> queryForeignCheckByAppId(String appIdThd);

	List<Map<String, String>> selectKexinMsgByAppidThd(String appIdThd);

}