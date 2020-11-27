package com.huaxia.opas.outerfaces.thirdparty;

import java.util.Map;

import com.huateng.neofp.core.CoreException;

/**
 * @Description:  调用征信服务-数据平台
 * Copyright: Copyright (c) 2016
 * Company: HUATENG
 * @Author wang.jf
 * @Version 1.0  
 * Created at 2016-4-5 
 */
public interface InvokeCreditService {
	
	public Map<String,Object> invokeCreditInfo(Map<String,Object> input) throws CoreException;

}
