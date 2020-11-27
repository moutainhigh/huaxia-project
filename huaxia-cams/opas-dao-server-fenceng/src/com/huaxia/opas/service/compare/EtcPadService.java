package com.huaxia.opas.service.compare;

import java.util.Map;

import com.huateng.neofp.core.CoreException;

/**
 * PAD进件追加信息
 * 
 * @author Mr.Chen 2019-11-13
 *
 */
public interface EtcPadService {

	/**
	 * 根据 appId 查询 PAD进件追加信息表中的   申请人签名及抄录文字是否完整有效、申请人身份证明文件完成有效
	 * @param appId
	 * @return
	 * @throws CoreException 
	 */
	Map<String, String> querySignatureAndIdByAppId(String appId) throws CoreException;

}
