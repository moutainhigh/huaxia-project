package com.huaxia.opas.util;

/**
 * JDBC ת�� ΪJava Bean����Ҫ�� ӳ����Ϣ
 * @author pccc
 */
public class Mapping {
	
	private String columnName;			// �ֶ�����
	private int columnType;				// �ֶ�JDBC����
	private String propertyName;		// ��������
	private String setMethodName;		// set��������
	private String javaType;
	
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public int getColumnType() {
		return columnType;
	}
	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getSetMethodName() {
		return setMethodName;
	}
	public void setSetMethodName(String setMethodName) {
		this.setMethodName = setMethodName;
	}

}
