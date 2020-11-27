package com.huaxia.opas.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import com.huateng.neofp.service.sqlmap.SqlMap;

/**
 * 报表库
 * 
 * @Version 1.0
 */
public abstract class AbstractSimpleDAO implements Serializable {

	private static final long serialVersionUID = 541426608572960628L;

	@Resource(name = "bigTableSqlMap")
	private SqlMap bigTableSqlMap;

	public SqlMap getBigTableSqlMap() {
		return bigTableSqlMap;
	}

	public void setBigTableSqlMap(SqlMap bigTableSqlMap) {
		this.bigTableSqlMap = bigTableSqlMap;
	}

}
