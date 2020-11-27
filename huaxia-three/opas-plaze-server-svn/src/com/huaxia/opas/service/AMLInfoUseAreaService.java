package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLInfoUseArea;

/**
 * 信息使用地区服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLInfoUseAreaService {

	/**
	 * 保存信息使用地区息
	 * 
	 * @param infoUseArea
	 *            信息使用地区对象
	 * @return
	 */
	int save(AMLInfoUseArea infoUseArea);

}
