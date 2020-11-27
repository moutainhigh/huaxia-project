package com.huaxia.opas.dao.compare.impl;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.compare.TestObjDao;
import com.huaxia.opas.domain.excel.TestObj;

public class TestObjDaoImpl extends AbstractDAO implements TestObjDao {

	private static final long serialVersionUID = -2816674839886233681L;
	private static final String NAMESPACES = "TestObj.";

	@Override
	public void insertTestObjList(List<TestObj> obj) throws CoreException {
		getSqlMap().insert(NAMESPACES + "insertList", obj);

	}

}
