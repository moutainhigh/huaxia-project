package com.huaxia.opas.outerfaces.thirdparty;

import com.huateng.neofp.core.CoreException;

public interface PBOCService {

	/**
	 * 导入人行征信信息
	 * 
	 * @param content
	 *            输入报文
	 * @return 返回报文
	 * @throws CoreException
	 */
	public String importPBOC(String content) throws CoreException;
}
