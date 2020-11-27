package com.huaxia.opas.interfaces.out;

import java.util.List;

import com.huaxia.opas.entity.QueryParam;

/**
 * 学历查询接口
 * 
 * @author zhiguo.li
 *
 */
public interface QueryEducation {

	String querySingle(QueryParam queryParam) throws Exception;

	String queryBatch(List<QueryParam> queryParamList) throws Exception;

}
