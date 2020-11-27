package com.huaxia.opas.domain.archive;

import java.io.Serializable;
import java.util.Date;

public class ArBatch implements Serializable {

	private static final long serialVersionUID = -2736578524787290225L;

	/**
	 * 归档编号
	 */
	private String fileNo;

	/**
	 * 归档件数
	 */
	private String fileApp;

	/**
	 * 归档员
	 */
	private String fileOperator;

	/**
	 * 归档时间
	 */
	private Date fileTime;

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getFileApp() {
		return fileApp;
	}

	public void setFileApp(String fileApp) {
		this.fileApp = fileApp;
	}

	public String getFileOperator() {
		return fileOperator;
	}

	public void setFileOperator(String fileOperator) {
		this.fileOperator = fileOperator;
	}

	public Date getFileTime() {
		return fileTime;
	}

	public void setFileTime(Date fileTime) {
		this.fileTime = fileTime;
	}

	

}
