package com.huaxia.opas.outerfaces.thirdparty;

import java.util.Map;

import com.huateng.neofp.core.CoreException;

/**
 * @Description:  
 * Copyright: Copyright (c) 2016
 * Company: HUATENG
 * @Author  wang.jf
 * @Version 1.0
 * Created at 2016-4-5 
 */
public interface InvokeFraudPolicyService {

	public Map<String,Object> invokeFraudPolicy(Map<String,Object> input) throws CoreException; 
}
