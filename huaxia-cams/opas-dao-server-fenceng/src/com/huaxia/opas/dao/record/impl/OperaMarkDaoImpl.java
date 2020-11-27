package com.huaxia.opas.dao.record.impl;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.record.OperaMarkDao;
import com.huaxia.opas.domain.record.OperaRecord;

public class OperaMarkDaoImpl extends AbstractDAO implements OperaMarkDao {
	
	private static final long serialVersionUID = -1081064262165918725L;
	
	private static final String NAMESPACES = "OperaRecord.";

	@Override
	public int insertRecord(OperaRecord operaRecord) {
		return getSqlMap().insert(NAMESPACES + "insertRecord", operaRecord);
	}

}
