package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.rule.OpasPbocCreditCue;

public interface OpasPbocCreditCueDao {

    int insert(OpasPbocCreditCue record);

    List<OpasPbocCreditCue> selectByExample(String appId);

    public List<String> queryPbocPhone(String appId);

	int selectCivjudgeCount(String appId);

	int selectForceexeCount(String appId);

	List<String> selectPbocMsg(String appId);
	
	List<String> selectEninsurdepMsg(String appId);
	
	Map<String,String> selectAccfundMsg(String appId);

	int selectEninsurdepMsgCount(String appId);
	
	int selectAccfundMsgCount(String appId);
	
}