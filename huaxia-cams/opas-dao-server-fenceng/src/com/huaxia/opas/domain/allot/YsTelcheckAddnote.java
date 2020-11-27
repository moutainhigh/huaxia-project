package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;

public class YsTelcheckAddnote implements Serializable {
	
	private static final long serialVersionUID = 4696269939539611923L;

	private String autoId;

    private String appId;

    private String telSource;

    private String telType;

    private String telNo;

    private String noteObject;

    private String telcheckResult;

    private String memo;

    private Date crtDate;

    private String crtUser;

    private String teamTelcheckStatus;

    private String passCode;

    private String ydjFlag;

    private String typeFlag;

    private String talId;

    private String colorFlag;

    private String bigMemo;
    
    private String sortFlag;
    //资料审查表 客群结论
    private String passResult;
    
    private String approveResult;
    
    private String approveCode;
    
    private String industryCustomer;
    
    private String companyCustomer;
    
    private String educationCustomer;
    
    private String houseLoanCustomer;
  
    private String carLoanCustomer;
    
    private String cardCustomer;

    private String existCustomer;

    private String provSecCustomer;

    private String registryCustomer;

    private String fixedAssetsCustomer;

    private String currAssetsCustomer;

    private String incomeCustomer;

    private String otherCustomer;

    private String idCertify;

    private String workCertify;

    private String companyCertify;

    private String educationCertify;

    private String houseLoanCertify;

    private String carLoanCertify;

    private String cardCertify;

    private String existCertify;

    private String provCertify;

    private String secCertify;

    private String houseCertify;

    private String carCertify;

    private String currAssetsCertify;

    private String incomeCertify;

    private String otherCertify;

    private int ysModifyLog;
    
    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId == null ? null : autoId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getTelSource() {
        return telSource;
    }

    public void setTelSource(String telSource) {
        this.telSource = telSource == null ? null : telSource.trim();
    }

    public String getTelType() {
        return telType;
    }

    public void setTelType(String telType) {
        this.telType = telType == null ? null : telType.trim();
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo == null ? null : telNo.trim();
    }

    public String getNoteObject() {
        return noteObject;
    }

    public void setNoteObject(String noteObject) {
        this.noteObject = noteObject == null ? null : noteObject.trim();
    }

    public String getTelcheckResult() {
        return telcheckResult;
    }

    public void setTelcheckResult(String telcheckResult) {
        this.telcheckResult = telcheckResult == null ? null : telcheckResult.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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

    public String getTeamTelcheckStatus() {
        return teamTelcheckStatus;
    }

    public void setTeamTelcheckStatus(String teamTelcheckStatus) {
        this.teamTelcheckStatus = teamTelcheckStatus == null ? null : teamTelcheckStatus.trim();
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode == null ? null : passCode.trim();
    }

    public String getYdjFlag() {
        return ydjFlag;
    }

    public void setYdjFlag(String ydjFlag) {
        this.ydjFlag = ydjFlag == null ? null : ydjFlag.trim();
    }

    public String getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag == null ? null : typeFlag.trim();
    }

    public String getTalId() {
        return talId;
    }

    public void setTalId(String talId) {
        this.talId = talId == null ? null : talId.trim();
    }

    public String getColorFlag() {
        return colorFlag;
    }

    public void setColorFlag(String colorFlag) {
        this.colorFlag = colorFlag == null ? null : colorFlag.trim();
    }

    public String getBigMemo() {
        return bigMemo;
    }

    public void setBigMemo(String bigMemo) {
        this.bigMemo = bigMemo == null ? null : bigMemo.trim();
    }

	public String getSortFlag() {
		return sortFlag;
	}

	public void setSortFlag(String sortFlag) {
		this.sortFlag = sortFlag;
	}

	public String getPassResult() {
		return passResult;
	}

	public void setPassResult(String passResult) {
		this.passResult = passResult;
	}

	public String getApproveResult() {
		return approveResult;
	}

	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}

	public String getApproveCode() {
		return approveCode;
	}

	public void setApproveCode(String approveCode) {
		this.approveCode = approveCode;
	}

	public String getIndustryCustomer() {
		return industryCustomer;
	}

	public void setIndustryCustomer(String industryCustomer) {
		this.industryCustomer = industryCustomer;
	}

	public String getCompanyCustomer() {
		return companyCustomer;
	}

	public void setCompanyCustomer(String companyCustomer) {
		this.companyCustomer = companyCustomer;
	}

	public String getEducationCustomer() {
		return educationCustomer;
	}

	public void setEducationCustomer(String educationCustomer) {
		this.educationCustomer = educationCustomer;
	}

	public String getHouseLoanCustomer() {
		return houseLoanCustomer;
	}

	public void setHouseLoanCustomer(String houseLoanCustomer) {
		this.houseLoanCustomer = houseLoanCustomer;
	}

	public String getCarLoanCustomer() {
		return carLoanCustomer;
	}

	public void setCarLoanCustomer(String carLoanCustomer) {
		this.carLoanCustomer = carLoanCustomer;
	}

	public String getCardCustomer() {
		return cardCustomer;
	}

	public void setCardCustomer(String cardCustomer) {
		this.cardCustomer = cardCustomer;
	}

	public String getExistCustomer() {
		return existCustomer;
	}

	public void setExistCustomer(String existCustomer) {
		this.existCustomer = existCustomer;
	}

	public String getProvSecCustomer() {
		return provSecCustomer;
	}

	public void setProvSecCustomer(String provSecCustomer) {
		this.provSecCustomer = provSecCustomer;
	}

	public String getRegistryCustomer() {
		return registryCustomer;
	}

	public void setRegistryCustomer(String registryCustomer) {
		this.registryCustomer = registryCustomer;
	}

	public String getFixedAssetsCustomer() {
		return fixedAssetsCustomer;
	}

	public void setFixedAssetsCustomer(String fixedAssetsCustomer) {
		this.fixedAssetsCustomer = fixedAssetsCustomer;
	}

	public String getCurrAssetsCustomer() {
		return currAssetsCustomer;
	}

	public void setCurrAssetsCustomer(String currAssetsCustomer) {
		this.currAssetsCustomer = currAssetsCustomer;
	}

	public String getIncomeCustomer() {
		return incomeCustomer;
	}

	public void setIncomeCustomer(String incomeCustomer) {
		this.incomeCustomer = incomeCustomer;
	}

	public String getOtherCustomer() {
		return otherCustomer;
	}

	public void setOtherCustomer(String otherCustomer) {
		this.otherCustomer = otherCustomer;
	}

	public String getIdCertify() {
		return idCertify;
	}

	public void setIdCertify(String idCertify) {
		this.idCertify = idCertify;
	}

	public String getWorkCertify() {
		return workCertify;
	}

	public void setWorkCertify(String workCertify) {
		this.workCertify = workCertify;
	}

	public String getCompanyCertify() {
		return companyCertify;
	}

	public void setCompanyCertify(String companyCertify) {
		this.companyCertify = companyCertify;
	}

	public String getEducationCertify() {
		return educationCertify;
	}

	public void setEducationCertify(String educationCertify) {
		this.educationCertify = educationCertify;
	}

	public String getHouseLoanCertify() {
		return houseLoanCertify;
	}

	public void setHouseLoanCertify(String houseLoanCertify) {
		this.houseLoanCertify = houseLoanCertify;
	}

	public String getCarLoanCertify() {
		return carLoanCertify;
	}

	public void setCarLoanCertify(String carLoanCertify) {
		this.carLoanCertify = carLoanCertify;
	}

	public String getCardCertify() {
		return cardCertify;
	}

	public void setCardCertify(String cardCertify) {
		this.cardCertify = cardCertify;
	}

	public String getExistCertify() {
		return existCertify;
	}

	public void setExistCertify(String existCertify) {
		this.existCertify = existCertify;
	}

	public String getProvCertify() {
		return provCertify;
	}

	public void setProvCertify(String provCertify) {
		this.provCertify = provCertify;
	}

	public String getSecCertify() {
		return secCertify;
	}

	public void setSecCertify(String secCertify) {
		this.secCertify = secCertify;
	}

	public String getHouseCertify() {
		return houseCertify;
	}

	public void setHouseCertify(String houseCertify) {
		this.houseCertify = houseCertify;
	}

	public String getCarCertify() {
		return carCertify;
	}

	public void setCarCertify(String carCertify) {
		this.carCertify = carCertify;
	}

	public String getCurrAssetsCertify() {
		return currAssetsCertify;
	}

	public void setCurrAssetsCertify(String currAssetsCertify) {
		this.currAssetsCertify = currAssetsCertify;
	}

	public String getIncomeCertify() {
		return incomeCertify;
	}

	public void setIncomeCertify(String incomeCertify) {
		this.incomeCertify = incomeCertify;
	}

	public String getOtherCertify() {
		return otherCertify;
	}

	public void setOtherCertify(String otherCertify) {
		this.otherCertify = otherCertify;
	}

	public int getYsModifyLog() {
		return ysModifyLog;
	}

	public void setYsModifyLog(int ysModifyLog) {
		this.ysModifyLog = ysModifyLog;
	}
    
}