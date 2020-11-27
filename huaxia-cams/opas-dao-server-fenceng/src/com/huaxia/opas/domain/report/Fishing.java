package com.huaxia.opas.domain.report;

import java.io.Serializable;
import java.util.Date;
//捞件信息表
public class Fishing implements Serializable{
	
	private static final long serialVersionUID = 2989422720465115236L;
	
    private String autoId;//序列值
    
    private String appId;//申请件编号

    private String applyChannle;//渠道

    private String applyerName;//申请人姓名

    private String operName;//操作人姓名

    private String c1Idtype;// 证件类别

    private String c1Idnbr;//证件号码

    private String returnLink;//捞返环节

    private Date crtDate;//创建日期
    private String crtTime;//用于前台反显

    private String refuseReason;//拒绝原因

    private String approveReuslt;//审批结果
    
    private String ydjFlag;//易达金标识     1为易达金，2为标准卡
    
    private String approveWay;// 审批方式  0自动  1手动
    
	private Long preApprovelimit;//预审批额度
	
	private String policyCode1;//政策码1
	
	private String policyCode2;//政策码2
	
	private String policyCode3;//政策码3
	
	private String violateCode1;//违例码1
	
	private String violateCode2;//违例码2
	
	private String violateCode3;//违例码3
	
	private String memo;//备注[拒绝、审批]
	
	private String refuseCode1;
	
	private String refuseCode2;
	
	private String refuseCode3;
	
	
    public String getRefuseCode1() {
		return refuseCode1;
	}

	public void setRefuseCode1(String refuseCode1) {
		this.refuseCode1 = refuseCode1;
	}

	public String getRefuseCode2() {
		return refuseCode2;
	}

	public void setRefuseCode2(String refuseCode2) {
		this.refuseCode2 = refuseCode2;
	}

	public String getRefuseCode3() {
		return refuseCode3;
	}

	public void setRefuseCode3(String refuseCode3) {
		this.refuseCode3 = refuseCode3;
	}

	public Long getPreApprovelimit() {
		return preApprovelimit;
	}

	public void setPreApprovelimit(Long preApprovelimit) {
		this.preApprovelimit = preApprovelimit;
	}

	public String getPolicyCode1() {
		return policyCode1;
	}

	public void setPolicyCode1(String policyCode1) {
		this.policyCode1 = policyCode1;
	}

	public String getPolicyCode2() {
		return policyCode2;
	}

	public void setPolicyCode2(String policyCode2) {
		this.policyCode2 = policyCode2;
	}

	public String getPolicyCode3() {
		return policyCode3;
	}

	public void setPolicyCode3(String policyCode3) {
		this.policyCode3 = policyCode3;
	}

	public String getViolateCode1() {
		return violateCode1;
	}

	public void setViolateCode1(String violateCode1) {
		this.violateCode1 = violateCode1;
	}

	public String getViolateCode2() {
		return violateCode2;
	}

	public void setViolateCode2(String violateCode2) {
		this.violateCode2 = violateCode2;
	}

	public String getViolateCode3() {
		return violateCode3;
	}

	public void setViolateCode3(String violateCode3) {
		this.violateCode3 = violateCode3;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

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

    public String getApplyChannle() {
        return applyChannle;
    }

    public void setApplyChannle(String applyChannle) {
        this.applyChannle = applyChannle == null ? null : applyChannle.trim();
    }

    public String getApplyerName() {
        return applyerName;
    }

    public void setApplyerName(String applyerName) {
        this.applyerName = applyerName == null ? null : applyerName.trim();
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName == null ? null : operName.trim();
    }

    public String getC1Idtype() {
        return c1Idtype;
    }

    public void setC1Idtype(String c1Idtype) {
        this.c1Idtype = c1Idtype == null ? null : c1Idtype.trim();
    }

    public String getC1Idnbr() {
        return c1Idnbr;
    }

    public void setC1Idnbr(String c1Idnbr) {
        this.c1Idnbr = c1Idnbr == null ? null : c1Idnbr.trim();
    }

    public String getReturnLink() {
        return returnLink;
    }

    public void setReturnLink(String returnLink) {
        this.returnLink = returnLink == null ? null : returnLink.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason == null ? null : refuseReason.trim();
    }

    public String getApproveReuslt() {
        return approveReuslt;
    }

    public void setApproveReuslt(String approveReuslt) {
        this.approveReuslt = approveReuslt == null ? null : approveReuslt.trim();
    }
    
    public String getYdjFlag() {
        return ydjFlag;
    }

    public void setYdjFlag(String ydjFlag) {
        this.ydjFlag = ydjFlag == null ? null : ydjFlag.trim();
    }

	public String getApproveWay() {
		return approveWay;
	}

	public void setApproveWay(String approveWay) {
		this.approveWay = approveWay;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}
	
}