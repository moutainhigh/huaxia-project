package com.huaxia.opas.domain.allot;

import java.io.Serializable;
/**
 * 分配开关
 * @author wangdebin
 */
public class AllotSwitch implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8020636894025666307L;
	//开关id
	private String switchId;
	//开关名称
	private String switchName;
	//开关代码switchPosition
	private String switchCode;
	//开关状态switchWay
	private String switchStatus;
	//是否为节假日
	private int num;
	public String getSwitchId() {
		return switchId;
	}
	public void setSwitchId(String switchId) {
		this.switchId = (switchId == null ? null : switchId.trim());
	}
	public String getSwitchName() {
		return switchName;
	}
	public void setSwitchName(String switchName) {
		this.switchName = switchName;
	}
	public String getSwitchCode() {
		return switchCode;
	}
	public void setSwitchCode(String switchCode) {
		this.switchCode = switchCode;
	}
	public String getSwitchStatus() {
		return switchStatus;
	}
	public void setSwitchStatus(String switchStatus) {
		this.switchStatus = switchStatus;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
