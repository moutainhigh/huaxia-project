package com.huaxia.opas.service.compare;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.excel.TestObj;

public interface TestObjService {

	public void insertTestObjList(List<TestObj> obj) throws CoreException;

}
