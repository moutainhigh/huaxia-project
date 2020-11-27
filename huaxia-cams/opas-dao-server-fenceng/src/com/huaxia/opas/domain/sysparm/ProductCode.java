package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;

/**
 * 卡产品降级-产品码信息
 * @author liudi
 * @since 2017-03-14
 * @version 1.0
 */
public class ProductCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7757188719919361271L;

	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 产品编号
	 */
	private String productCode;

	/**
	 * 产品名称
	 */
	private String productName;


	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = (autoId == null ? null : autoId.trim());
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = (productCode == null ? null : productCode.trim());
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = (productName == null ? null : productName.trim());
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
