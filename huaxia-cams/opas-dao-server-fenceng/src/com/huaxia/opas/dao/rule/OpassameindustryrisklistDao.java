package com.huaxia.opas.dao.rule;

import java.util.List;

import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.rule.Opassameindustryrisklist;

public interface OpassameindustryrisklistDao {

    int insert(Opassameindustryrisklist record);


    List<Opassameindustryrisklist> selectByExample(Opasbizinpapplc1 example);


	List<String> selectPhoneNameMatch(String appId);


	List<String> selectJdPhoneNameMatch(String appId);

}