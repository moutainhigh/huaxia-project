package com.huaxia.cams.modules.pengyuan.service;

import com.huaxia.cams.modules.pengyuan.domain.PyPwslMsgResponse;
import com.huaxia.cams.modules.pengyuan.domain.PyPwslResponse;

public interface PyPwslResponseService {

	/**
	 * 将解析后报文持久化
	 * @param pyPwslResponse
	 * @return
	 */
	int savePyPwslResponse(PyPwslResponse pyPwslResponse);
	
	/**
	 * 保存数据源返回的原始报文
	 * @param pyPwslMsgResponse
	 * @return
	 */
	int save(PyPwslMsgResponse pyPwslMsgResponse);
	
	/**
	 * 通过请求条件查询已经保存的结果
	 * @param queryType
	 * @param name
	 * @param certNo
	 * @param subreportIds
	 * @param queryReaseonId
	 * @return
	 */
	String findMsgByRequest(String name,String certNo);
}
