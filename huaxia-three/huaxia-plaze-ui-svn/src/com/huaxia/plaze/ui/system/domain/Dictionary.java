package com.huaxia.plaze.ui.system.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 数据字典
 * 
 * @author zhiguo.li
 *
 */
@Alias("Dictionary")
public class Dictionary implements Serializable {
	
	private static final long serialVersionUID = 6380071695081043828L;

	// 分组代码
	private String groupCode;
	
	// 分组名称
	private String groupName;
	
	// 字典编号 
	private String dictCode;
	
	// 字典名称
	private String dictName;
	
	// 字典索引
	private int dictIndex;

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public int getDictIndex() {
		return dictIndex;
	}

	public void setDictIndex(int dictIndex) {
		this.dictIndex = dictIndex;
	}

}
