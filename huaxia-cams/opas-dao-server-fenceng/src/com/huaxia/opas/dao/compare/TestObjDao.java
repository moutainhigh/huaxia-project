package com.huaxia.opas.dao.compare;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.excel.TestObj;

public interface TestObjDao {

	public void insertTestObjList(List<TestObj> obj) throws CoreException;

}
