package com.huaxia.opas.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import com.huateng.neofp.service.sqlmap.SqlMap;
import com.huaxia.opas.dao.common.sqlmap.SqlExecutor;

@SuppressWarnings("serial")
public class AbstractDAO implements Serializable {

	@Resource(name = "sqlMap")
	protected SqlMap sqlMap;

	@Resource(name = "sqlExecutor")
	protected SqlExecutor sqlExecutor;

	public SqlMap getSqlMap() {
		return sqlMap;
	}

	public SqlExecutor getSqlExecutor() {
		return sqlExecutor;
	}

	// jpc0522
	@Resource(name = "sqlMapWorkflow")
	protected SqlMap sqlMapWorkflow;

	public SqlMap getSqlMapWorkflow() {
		return sqlMapWorkflow;
	}

}
