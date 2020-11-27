package com.huaxia.opas.domain.riskcheck;

import java.io.Serializable;
import java.util.Map;

public class RiskCheck implements Serializable{
	private static final long serialVersionUID = -6192803450648717197L;
	/**
	 *  比较项key：如：单位身份证 1、单位名称 2、院校 3
		比较项值：
		对比的基准数据来源大类:0:内部数据 1：外部数据/三方数据
		对比的基准数据来源table名称：如：OPAS_IDENTITY_RISKLIST、OPAS_COMPANY_RISKLIST、OPAS_MAJORSCHOOL_LIST等等
		比较项基准值：
		比较项基准值对应的主键值：
		比较结果：1:true 0：false
		风险类别：
		other：map类型
	 */
	/**
	 * 比较项key
	 */
	private String FieldKey;
	/**
	 * 比较项值
	 */
	private String FieldValue;
	/**
	 * 对比的基准数据来源大类:0:内部数据 1：外部数据/三方数据
	 */
	private String BaseDataType;
	/**
	 * 对比的基准数据来源table名称：如：OPAS_IDENTITY_RISKLIST、OPAS_COMPANY_RISKLIST、OPAS_MAJORSCHOOL_LIST等等
	 */
	private String TableName;
	/**
	 * 比较项基准值：
	 */
	private String BaseDataValue;
	
	/**
	 * 比较项基准值对应的主键值：
	 */
	private String PriKeyValue;
	
	/**
	 * 比较结果：1:true 0：false
	 */
	private String RiskResult;
	/**
	 * 风险类别：一级
	 */
	private String RiskType;
	/**
	 * 风险类别：二级
	 */
	private String RiskType_Two;
	/**
	 * 自定义ID
	 */
	private String DefinedId;
	/**
	 * 自定义数据域
	 */
	private Map<String, String> UserDefined;
	
	public RiskCheck() {
		super();
	}

	public String getFieldKey() {
		return FieldKey;
	}

	public void setFieldKey(String fieldKey) {
		FieldKey = fieldKey;
	}

	public String getFieldValue() {
		return FieldValue;
	}

	public void setFieldValue(String fieldValue) {
		FieldValue = fieldValue;
	}

	public String getBaseDataType() {
		return BaseDataType;
	}

	public void setBaseDataType(String baseDataType) {
		BaseDataType = baseDataType;
	}

	public String getTableName() {
		return TableName;
	}

	public void setTableName(String tableName) {
		TableName = tableName;
	}

	public String getBaseDataValue() {
		return BaseDataValue;
	}

	public void setBaseDataValue(String baseDataValue) {
		BaseDataValue = baseDataValue;
	}

	public String getPriKeyValue() {
		return PriKeyValue;
	}

	public void setPriKeyValue(String priKeyValue) {
		PriKeyValue = priKeyValue;
	}

	public String getRiskType() {
		return RiskType;
	}

	public void setRiskType(String riskType) {
		RiskType = riskType;
	}

	public Map<String, String> getUserDefined() {
		return UserDefined;
	}

	public void setUserDefined(Map<String, String> userDefined) {
		UserDefined = userDefined;
	}

	public String getDefinedId() {
		return DefinedId;
	}

	public void setDefinedId(String definedId) {
		DefinedId = definedId;
	}

	public String getRiskResult() {
		return RiskResult;
	}

	public void setRiskResult(String riskResult) {
		RiskResult = riskResult;
	}

	public String getRiskType_Two() {
		return RiskType_Two;
	}

	public void setRiskType_Two(String riskType_Two) {
		RiskType_Two = riskType_Two;
	}
}
