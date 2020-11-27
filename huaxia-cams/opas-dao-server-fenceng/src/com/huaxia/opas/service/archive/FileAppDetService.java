package com.huaxia.opas.service.archive;

import com.huaxia.opas.domain.archive.FileAppDet;
import com.huaxia.opas.domain.rule.OpasBizApproval;
import com.huaxia.opas.domain.thirdparty.BizApproval;

public interface FileAppDetService {

	int deleteByPrimaryKey(String appId);

    int insert(FileAppDet record);

    FileAppDet selectByPrimaryKey(String appId);

    int updateByPrimaryKey(FileAppDet record);
    
    OpasBizApproval selectOpasBizApprovalByPrimaryKey(String appId);

	void insertFileAppDet(FileAppDet fileAppDet, BizApproval bizApproval); 
}
