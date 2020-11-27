package com.huaxia.opas.service.avaya.impl;

import java.io.Serializable;
import javax.annotation.Resource;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.avaya.AvayaLogDao;
import com.huaxia.opas.domain.avaya.AvayaLog;
import com.huaxia.opas.service.avaya.AvayaLogService;

public class AvayaLogServiceImpl extends AbstractDAO implements AvayaLogService, Serializable {

	private static final long serialVersionUID = 7125563714587817959L;

	@Resource(name = "avayaLogDao")
	private AvayaLogDao avayaLogDao;


	public AvayaLogDao getAvayaLogDao() {
		return avayaLogDao;
	}

	@Override
	public int addAvayaLog(AvayaLog avayaLog) throws CoreException {
		// TODO Auto-generated method stub
		return getAvayaLogDao().add(avayaLog);
	}


}
