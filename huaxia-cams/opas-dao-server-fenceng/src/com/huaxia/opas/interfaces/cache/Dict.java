package com.huaxia.opas.interfaces.cache;

import java.io.Serializable;

/**
 * 业务字典实体类
 * 
 * @author uatyp990295 panyf
 *
 */
public class Dict implements Serializable {

	private static final long serialVersionUID = 903428213513297136L;

	/**
	 * 业务字典小类Id
	 */
	private String dictId;

	/**
	 * 业务字典小类id对应名称
	 */
	private String dictName;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

}
