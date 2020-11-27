package com.huaxia.opas.domain.thirdparty;

import java.io.Serializable;
import java.util.Date;

public class OpasConfQuestion implements Serializable{
    private String autoId;

    private String questionNo;

    private String questionLevel;

    private String questionDesc;

    private String fromTableNo;

    private String fromFieldNo;

    private Date crtDate;

    private String crtUser;

    private Date lstUpdTime;

    private String lstUpdUser;

    public String getAutoId() {
        return autoId;
    }
    //FROM_FIELD_NO表的字段， 其他同学可忽略此字段
    private String fromFiledDesc;
    //问题电核结果
    private String dianHeResult;

    public void setAutoId(String autoId) {
        this.autoId = autoId == null ? null : autoId.trim();
    }

    public String getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo == null ? null : questionNo.trim();
    }

    public String getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(String questionLevel) {
        this.questionLevel = questionLevel == null ? null : questionLevel.trim();
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc == null ? null : questionDesc.trim();
    }

    public String getFromTableNo() {
        return fromTableNo;
    }

    public void setFromTableNo(String fromTableNo) {
        this.fromTableNo = fromTableNo == null ? null : fromTableNo.trim();
    }

    public String getFromFieldNo() {
        return fromFieldNo;
    }

    public void setFromFieldNo(String fromFieldNo) {
        this.fromFieldNo = fromFieldNo == null ? null : fromFieldNo.trim();
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

    public String getLstUpdUser() {
        return lstUpdUser;
    }

    public void setLstUpdUser(String lstUpdUser) {
        this.lstUpdUser = lstUpdUser == null ? null : lstUpdUser.trim();
    }

	public String getFromFiledDesc() {
		return fromFiledDesc;
	}

	public void setFromFiledDesc(String fromFiledDesc) {
		this.fromFiledDesc = fromFiledDesc;
	}

	public String getDianHeResult() {
		return dianHeResult;
	}

	public void setDianHeResult(String dianHeResult) {
		this.dianHeResult = dianHeResult;
	}
}