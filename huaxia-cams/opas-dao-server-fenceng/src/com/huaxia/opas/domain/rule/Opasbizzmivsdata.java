package com.huaxia.opas.domain.rule;

import java.io.Serializable;
import java.util.Date;

public class Opasbizzmivsdata implements Serializable{
    private String insideAppNo;

    private String ivsScore;

    private String bizId;

    private String code;

    private String description;

    private Date crtTime;

    private Date crtDate;

    private String crtUser;

    private Date lstUpdTime;

    private Date lstUpdDate;

    private String lstUpdUser;

    private Date batchDate;

    private String recStatus;

    private String appId;

    private String certNo;

    private String certType;

    private String name;

    private String mobile;

    private String email;

    private String bankCard;

    private String address;

    private String ip;

    private String mac;

    private String wifimac;

    private String imei;

    private String imsi;
    public String getCodeAddrEnglish() {
		return codeAddrEnglish;
	}

	public void setCodeAddrEnglish(String codeAddrEnglish) {
		this.codeAddrEnglish = codeAddrEnglish;
	}

	public String getCodePhoenEnglish() {
		return codePhoenEnglish;
	}

	public void setCodePhoenEnglish(String codePhoenEnglish) {
		this.codePhoenEnglish = codePhoenEnglish;
	}

	public String getCodeNameEnglish() {
		return codeNameEnglish;
	}

	public void setCodeNameEnglish(String codeNameEnglish) {
		this.codeNameEnglish = codeNameEnglish;
	}

	public String getCodeEmailEnglish() {
		return codeEmailEnglish;
	}

	public void setCodeEmailEnglish(String codeEmailEnglish) {
		this.codeEmailEnglish = codeEmailEnglish;
	}

	public String getCodeCertnoEnglish() {
		return codeCertnoEnglish;
	}

	public void setCodeCertnoEnglish(String codeCertnoEnglish) {
		this.codeCertnoEnglish = codeCertnoEnglish;
	}

	private String codeAddrEnglish;
    private String codePhoenEnglish;
    private String codeNameEnglish;
    private String codeEmailEnglish;
    private String codeCertnoEnglish;

    public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getIvsScore() {
        return ivsScore;
    }

    public void setIvsScore(String ivsScore) {
        this.ivsScore = ivsScore == null ? null : ivsScore.trim();
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId == null ? null : bizId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
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
        this.crtUser = crtUser == null ? null : crtUser.trim();
    }

    public Date getLstUpdTime() {
        return lstUpdTime;
    }

    public void setLstUpdTime(Date lstUpdTime) {
        this.lstUpdTime = lstUpdTime;
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
        this.lstUpdUser = lstUpdUser == null ? null : lstUpdUser.trim();
    }

    public Date getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus == null ? null : recStatus.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getWifimac() {
        return wifimac;
    }

    public void setWifimac(String wifimac) {
        this.wifimac = wifimac == null ? null : wifimac.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi == null ? null : imsi.trim();
    }
}