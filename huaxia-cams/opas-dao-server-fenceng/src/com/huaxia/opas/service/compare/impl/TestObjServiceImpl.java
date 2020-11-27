package com.huaxia.opas.service.compare.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.compare.TestObjDao;
import com.huaxia.opas.domain.excel.TestObj;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.compare.TestObjService;

public class TestObjServiceImpl extends AbstractService implements TestObjService,Serializable{

	private static final long serialVersionUID = 5894208145964188992L;
	
	@Resource(name = "testObjDao")
	private TestObjDao testObjDao;

	@Override
	public void insertTestObjList(List<TestObj> obj) throws CoreException {
		getTestObjDao().insertTestObjList(obj);
	}

	public TestObjDao getTestObjDao() {
		return testObjDao;
	}

	public void setTestObjDao(TestObjDao testObjDao) {
		this.testObjDao = testObjDao;
	}

}
