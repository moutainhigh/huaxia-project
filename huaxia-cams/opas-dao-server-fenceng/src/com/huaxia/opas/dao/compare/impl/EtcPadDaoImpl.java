package com.huaxia.opas.dao.compare.impl;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.compare.EtcPadDao;

/**
 * PAD进件追加信息
 * @author Mr.Chen
 *
 */
public class EtcPadDaoImpl extends AbstractDAO implements EtcPadDao{

	private static final long serialVersionUID = -5792053590982062438L;
	
	private static final String NAMESPACES = "CcardAppPadAddition.";

	@Override
	public Map<String, String> selectSignatureAndIdByAppId(String appId) throws CoreException{
		
		return getSqlMap().queryForObject(NAMESPACES + "selectSignatureAndIdByAppId", appId);
		
	}

}
