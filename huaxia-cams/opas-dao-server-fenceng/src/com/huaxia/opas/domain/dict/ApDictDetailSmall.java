package com.huaxia.opas.domain.dict;

import java.io.Serializable;

public class ApDictDetailSmall implements Serializable {

	private static final long serialVersionUID = -1435678137168840512L;

	/**
	 * 业务字典小类编码
	 */
	private String dictDetailCode;

	/**
	 * 业务字典小类名称
	 */
	private String dictDetailName;

	public String getDictDetailCode() {
		return dictDetailCode;
	}

	public void setDictDetailCode(String dictDetailCode) {
		this.dictDetailCode = (dictDetailCode == null ? null : dictDetailCode.trim());
	}

	public String getDictDetailName() {
		return dictDetailName;
	}

	public void setDictDetailName(String dictDetailName) {
		this.dictDetailName = (dictDetailName == null ? null : dictDetailName.trim());
	}

}
