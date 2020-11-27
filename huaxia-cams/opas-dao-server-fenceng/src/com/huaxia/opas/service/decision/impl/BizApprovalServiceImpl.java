package com.huaxia.opas.service.decision.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;

import com.huaxia.opas.dao.rule.OpasBizApprovalDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.decision.BizApprovalService;

public class BizApprovalServiceImpl extends AbstractService implements BizApprovalService,Serializable {

	private static final long serialVersionUID = -8008508483239828116L;

	private static org.slf4j.Logger logger = LoggerFactory
			.getLogger(BizApprovalServiceImpl.class);

	@Resource(name = "opasBizApprovalDao")
	private OpasBizApprovalDao opasBizApprovalDaox;
	
	@Override
	public List<Map<String, String>> selectByHisAppIds(Map hisAppIdArrayParams) {
		return opasBizApprovalDaox.selectByHisAppIds(hisAppIdArrayParams);
	}

}
