package com.huaxia.opas.domain.compare;

import java.io.Serializable;
import java.util.Date;

/**
 * PAD进件追加信息
 * 
 * @author Mr.Chen  2019-11-13
 *
 */
public class CcardAppPadAddition implements Serializable {

	private static final long serialVersionUID = 698480541603090064L;
	
	private String appId;//流水号
	
	private String isAlive;//是否活体
	
	private String comfirmNum;//确认函编号
	
	private String isSignatureIntegrityValid;//申请人签名及抄录文字是否完整有效
	
	private String isIdDateIntegrityValid;//申请人身份证明文件完成有效
	
	private String isInfoIntegrityValid;//申请表信息及必附资料完整有效
	
	private String baseStation;//基站定位信息
	
	private Date crtTime;//创建时间
	
	private Date lastUpdTime;//最后一次修改时间
	
	private Date lastUpdDate;//最后一次修改日期
	
	private Date crtDate;//创建日期

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(String isAlive) {
		this.isAlive = isAlive;
	}

	public String getComfirmNum() {
		return comfirmNum;
	}

	public void setComfirmNum(String comfirmNum) {
		this.comfirmNum = comfirmNum;
	}

	public String getIsSignatureIntegrityValid() {
		return isSignatureIntegrityValid;
	}

	public void setIsSignatureIntegrityValid(String isSignatureIntegrityValid) {
		this.isSignatureIntegrityValid = isSignatureIntegrityValid;
	}

	public String getIsIdDateIntegrityValid() {
		return isIdDateIntegrityValid;
	}

	public void setIsIdDateIntegrityValid(String isIdDateIntegrityValid) {
		this.isIdDateIntegrityValid = isIdDateIntegrityValid;
	}

	public String getIsInfoIntegrityValid() {
		return isInfoIntegrityValid;
	}

	public void setIsInfoIntegrityValid(String isInfoIntegrityValid) {
		this.isInfoIntegrityValid = isInfoIntegrityValid;
	}

	public String getBaseStation() {
		return baseStation;
	}

	public void setBaseStation(String baseStation) {
		this.baseStation = baseStation;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public Date getLastUpdTime() {
		return lastUpdTime;
	}

	public void setLastUpdTime(Date lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}

	public Date getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	
	

}
