package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 网申合作商户实体类
 *
 * @author lipengfei
 *
 */
public class Business implements Serializable {
	private static final long serialVersionUID = -3349052057037193825L;

	// ID
	private String businessID;
	// 渠道标志
	private String businessLogo;
	// 公司编码
	private String businessNumber;
	// 公司名称
	private String businessName;
	// 模块编码
	private String moduleNumber;
	// 模块名称
	private String moduleName;
	// 用户状态
	private String status;
	// 创建日期
	private Date crtDate;
	// 创建人
	private String crtUser;
	// 最后修改日期
	private Date lstUpdDate;
	// 最后修改人
	private String lstUpdUser;
	// 当前登录用户
	private String operator;
	// id集合用于批量操作
	private List<String> ids;
	//推荐来源
	private String rcdSrc;
	//平台类型
	private String platType;
	//合作类型
	private String coprType;
	//平台风险层级
	private String platRiskLvl;
	//接口模式
	private String interfaceMode;
	//场景备案域名链接
	private String sceneFileDNLink;
	//阻断阈值
	private String blockingThreshold;
	//阻断开关
	private String blockingSwitch;
	
	public String getInterfaceMode() {
		return interfaceMode;
	}
	public void setInterfaceMode(String interfaceMode) {
		this.interfaceMode = interfaceMode;
	}
	public String getSceneFileDNLink() {
		return sceneFileDNLink;
	}
	public void setSceneFileDNLink(String sceneFileDNLink) {
		this.sceneFileDNLink = sceneFileDNLink;
	}
	public String getBlockingThreshold() {
		return blockingThreshold;
	}
	public void setBlockingThreshold(String blockingThreshold) {
		this.blockingThreshold = blockingThreshold;
	}
	public String getBlockingSwitch() {
		return blockingSwitch;
	}
	public void setBlockingSwitch(String blockingSwitch) {
		this.blockingSwitch = blockingSwitch;
	}
	public String getBusinessID() {
		return businessID;
	}
	public void setBusinessID(String businessID) {
		this.businessID = businessID;
	}
	public String getBusinessLogo() {
		return businessLogo;
	}
	public void setBusinessLogo(String businessLogo) {
		this.businessLogo = businessLogo;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getModuleNumber() {
		return moduleNumber;
	}
	public void setModuleNumber(String moduleNumber) {
		this.moduleNumber = moduleNumber;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRcdSrc() {
		return rcdSrc;
	}
	public void setRcdSrc(String rcdSrc) {
		this.rcdSrc = rcdSrc;
	}
	public String getPlatType() {
		return platType;
	}
	public void setPlatType(String platType) {
		this.platType = platType;
	}
	public String getCoprType() {
		return coprType;
	}
	public void setCoprType(String coprType) {
		this.coprType = coprType;
	}
	public String getPlatRiskLvl() {
		return platRiskLvl;
	}
	public void setPlatRiskLvl(String platRiskLvl) {
		this.platRiskLvl = platRiskLvl;
	}
	public Date getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	public Date getLstUpdDate() {
		return lstUpdDate;
	}
	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}
	public String getLstUpdUser() {
		return lstUpdUser;
	}
	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public Business(String businessID, String businessLogo, String businessNumber, String businessName,
			String moduleNumber, String moduleName, String status, Date crtDate, String crtUser, Date lstUpdDate,
			String lstUpdUser, String operator, List<String> ids, String rcdSrc, String platType, String coprType,
			String platRiskLvl, String interfaceMode, String sceneFileDNLink, String blockingThreshold, String blockingSwitch) {
		super();
		this.businessID = businessID;
		this.businessLogo = businessLogo;
		this.businessNumber = businessNumber;
		this.businessName = businessName;
		this.moduleNumber = moduleNumber;
		this.moduleName = moduleName;
		this.status = status;
		this.crtDate = crtDate;
		this.crtUser = crtUser;
		this.lstUpdDate = lstUpdDate;
		this.lstUpdUser = lstUpdUser;
		this.operator = operator;
		this.ids = ids;
		this.rcdSrc = rcdSrc;
		this.platType = platType;
		this.coprType = coprType;
		this.platRiskLvl = platRiskLvl;
		this.interfaceMode = interfaceMode;
		this.sceneFileDNLink = sceneFileDNLink;
		this.blockingThreshold = blockingThreshold;
		this.blockingSwitch = blockingSwitch;
	}
	public Business() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private String crtDate1;
	public String getCrtDate1() {
		return crtDate1;
	}



	public void setCrtDate1(String crtDate1) {
		this.crtDate1 = crtDate1;
	}
}
