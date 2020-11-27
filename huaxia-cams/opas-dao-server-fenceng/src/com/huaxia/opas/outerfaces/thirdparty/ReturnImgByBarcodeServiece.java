package com.huaxia.opas.outerfaces.thirdparty;

import com.huateng.neofp.core.CoreException;

public interface ReturnImgByBarcodeServiece {

	/**
	 * 通过申请书编号获取影像编号（如果存在多个影像编号，则是","分隔）
	 * 
	 * @param appno
	 *            申请书编号
	 * @return
	 * @throws CoreException
	 */
	public String returnImgByBarcode(String appno) throws CoreException;
}
