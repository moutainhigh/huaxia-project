package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;

public class AllotApplyAllot  implements Serializable {
	
	private static final long serialVersionUID = 2122961074391839287L;

	private String appId;

    private String currOptQueue;

    private String lastOptQueue;

    private String currOptGroup;

    private String lastOptGroup;

    private String currOptUser;

    private String lastOptUser;

    private String currStatus;

    private String delStatus;

    private String currNode;

    private Date crtTeamDate;

    private Date queueDate;

    private Date groupDate;

    private String submitStatus;

    private String submitType;

    private Date lstTeamDate;

    private String submitMemo;

    private String ydjFlag;

    private String reviewStatus;

    private String matchingCardFlag;

    private String processId;

    private Date userDate;

    private String synFlag;

    private String backFlag;

    private String laojianflag;

    private int subDate;//进队列天数
    
    private String lstDate;
    
    private String appProd;
    
    private String check_meuoFlag;
    private String batchCreditFlag;
    private String batchApprovalFlag;
    private String batchOperateFlag;
    //组日期(全环节 主要  欺诈  审批用 )
  	private Date groupTeamDate;
  	//欺诈池日期
  	private Date fraudDate;
  	private String zshyInnerCheck;
  	private String secDecisionFlag;
  	
	//乐观锁  避免行级锁情况
	private int allotVersion;
	
	private String approveReturn;//智能语音正核过  审批退回到征信
		
    public String getSecDecisionFlag() {
		return secDecisionFlag;
	}

	public void setSecDecisionFlag(String secDecisionFlag) {
		this.secDecisionFlag = secDecisionFlag;
	}

	public String getZshyInnerCheck() {
		return zshyInnerCheck;
	}

	public void setZshyInnerCheck(String zshyInnerCheck) {
		this.zshyInnerCheck = zshyInnerCheck;
	}

	public Date getGroupTeamDate() {
		return groupTeamDate;
	}

	public void setGroupTeamDate(Date groupTeamDate) {
		this.groupTeamDate = groupTeamDate;
	}

	public Date getFraudDate() {
		return fraudDate;
	}

	public void setFraudDate(Date fraudDate) {
		this.fraudDate = fraudDate;
	}

	public String getBatchOperateFlag() {
		return batchOperateFlag;
	}

	public void setBatchOperateFlag(String batchOperateFlag) {
		this.batchOperateFlag = batchOperateFlag;
	}

	public String getBatchApprovalFlag() {
		return batchApprovalFlag;
	}

	public void setBatchApprovalFlag(String batchApprovalFlag) {
		this.batchApprovalFlag = batchApprovalFlag;
	}

	public String getBatchCreditFlag() {
		return batchCreditFlag;
	}

	public void setBatchCreditFlag(String batchCreditFlag) {
		this.batchCreditFlag = batchCreditFlag;
	}

	public String getCheck_meuoFlag() {
		return check_meuoFlag;
	}

	public void setCheck_meuoFlag(String check_meuoFlag) {
		this.check_meuoFlag = check_meuoFlag;
	}

	public String getAppProd() {
		return appProd;
	}

	public void setAppProd(String appProd) {
		this.appProd = appProd;
	}

	public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getCurrOptQueue() {
        return currOptQueue;
    }

    public void setCurrOptQueue(String currOptQueue) {
        this.currOptQueue = currOptQueue == null ? null : currOptQueue.trim();
    }

    public String getLastOptQueue() {
        return lastOptQueue;
    }

    public void setLastOptQueue(String lastOptQueue) {
        this.lastOptQueue = lastOptQueue == null ? null : lastOptQueue.trim();
    }

    public String getCurrOptGroup() {
        return currOptGroup;
    }

    public void setCurrOptGroup(String currOptGroup) {
        this.currOptGroup = currOptGroup == null ? null : currOptGroup.trim();
    }

    public String getLastOptGroup() {
        return lastOptGroup;
    }

    public void setLastOptGroup(String lastOptGroup) {
        this.lastOptGroup = lastOptGroup == null ? null : lastOptGroup.trim();
    }

    public String getCurrOptUser() {
        return currOptUser;
    }

    public void setCurrOptUser(String currOptUser) {
        this.currOptUser = currOptUser == null ? null : currOptUser.trim();
    }

    public String getLastOptUser() {
        return lastOptUser;
    }

    public void setLastOptUser(String lastOptUser) {
        this.lastOptUser = lastOptUser == null ? null : lastOptUser.trim();
    }

    public String getCurrStatus() {
        return currStatus;
    }

    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus == null ? null : currStatus.trim();
    }

    public String getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(String delStatus) {
        this.delStatus = delStatus == null ? null : delStatus.trim();
    }

    public String getCurrNode() {
        return currNode;
    }

    public void setCurrNode(String currNode) {
        this.currNode = currNode == null ? null : currNode.trim();
    }

    public Date getCrtTeamDate() {
        return crtTeamDate;
    }

    public void setCrtTeamDate(Date crtTeamDate) {
        this.crtTeamDate = crtTeamDate;
    }

    public Date getQueueDate() {
        return queueDate;
    }

    public void setQueueDate(Date queueDate) {
        this.queueDate = queueDate;
    }

    public Date getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(Date groupDate) {
        this.groupDate = groupDate;
    }

    public String getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus == null ? null : submitStatus.trim();
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType == null ? null : submitType.trim();
    }

    public Date getLstTeamDate() {
        return lstTeamDate;
    }

    public void setLstTeamDate(Date lstTeamDate) {
        this.lstTeamDate = lstTeamDate;
    }

    public String getSubmitMemo() {
        return submitMemo;
    }

    public void setSubmitMemo(String submitMemo) {
        this.submitMemo = submitMemo == null ? null : submitMemo.trim();
    }

    public String getYdjFlag() {
        return ydjFlag;
    }

    public void setYdjFlag(String ydjFlag) {
        this.ydjFlag = ydjFlag == null ? null : ydjFlag.trim();
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus == null ? null : reviewStatus.trim();
    }

    public String getMatchingCardFlag() {
        return matchingCardFlag;
    }

    public void setMatchingCardFlag(String matchingCardFlag) {
        this.matchingCardFlag = matchingCardFlag == null ? null : matchingCardFlag.trim();
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId == null ? null : processId.trim();
    }

    public Date getUserDate() {
        return userDate;
    }

    public void setUserDate(Date userDate) {
        this.userDate = userDate;
    }

    public String getSynFlag() {
        return synFlag;
    }

    public void setSynFlag(String synFlag) {
        this.synFlag = synFlag == null ? null : synFlag.trim();
    }

    public String getBackFlag() {
        return backFlag;
    }

    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag == null ? null : backFlag.trim();
    }

    

	public String getLaojianflag() {
		return laojianflag;
	}

	public void setLaojianflag(String laojianflag) {
		this.laojianflag = laojianflag;
	}

	public int getSubDate() {
		return subDate;
	}

	public void setSubDate(int subDate) {
		this.subDate = subDate;
	}

	public String getLstDate() {
		return lstDate;
	}

	public void setLstDate(String lstDate) {
		this.lstDate = lstDate;
	}

	public int getAllotVersion() {
		return allotVersion;
	}

	public void setAllotVersion(int allotVersion) {
		this.allotVersion = allotVersion;
	}

	public String getApproveReturn() {
		return approveReturn;
	}

	public void setApproveReturn(String approveReturn) {
		this.approveReturn = approveReturn;
	}

}