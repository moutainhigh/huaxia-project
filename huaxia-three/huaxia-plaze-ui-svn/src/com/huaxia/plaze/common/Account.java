package com.huaxia.plaze.common;

/**
 * 账户状态
 * 
 * @author zhiguo.li
 *
 */
public enum Account {

	ACTIVE("1", "开通"), CLOSE("2", "关闭"), STOP("3", "停用"), LOCK("4", "锁定"), OFF("5", "禁用");

	private String status;

	private String description;

	private Account(String status, String description) {
		this.status = status;
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}
	
	public static String getStatusDesc(String status) {
		Account[] array = Account.values();
		for (int i = 0; i < array.length; i++) {
			if (array[i].getStatus().equals(status)) {
				return array[i].getDescription();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		Account.getStatusDesc(Account.LOCK.getStatus());
	}

}
