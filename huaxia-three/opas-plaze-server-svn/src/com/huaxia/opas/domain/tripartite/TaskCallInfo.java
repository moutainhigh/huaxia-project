package com.huaxia.opas.domain.tripartite;

import java.io.Serializable;
import java.util.Date;

/**
 * 新三方（海航）状态任务
 * 
 * @author gaohui
 *
 */
public class TaskCallInfo implements Serializable, Cloneable{
	
	private static final long serialVersionUID = 6466406237390603329L;

	private String allTaskType;//全部任务类型

    private String taskType;//当前任务类型

    private String taskStatus;//任务状态

    private String id;//uuid

    private Date timeStamp;//创建时间

    private String identityType;//身份证类型

    private String identityNo;//身份证号

    private String appId;//申请件编号

    private String processId;

    private String nodeId;

    private String name;//名字

    private String mobile;//手机号

    private String email;//邮箱

    private String bankcard;//银行卡号

    private String address;//地址
  
  	private String quickCardFlag;//快速发卡标识
  	
	//报文返回的错误码信息
    private String errorCodeMsg;
    
    //最后操作人
    private String lstOptUser;
    
    //最后操作时间
    private Date lstOptTime;
    
    //请求次数
    private Integer requestNum;
    
    //失败次数
    private Integer failureNum;
    
    public String getAllTaskType() {
        return allTaskType;
    }

    public void setAllTaskType(String allTaskType) {
        this.allTaskType = allTaskType == null ? null : allTaskType.trim();
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType == null ? null : identityType.trim();
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo == null ? null : identityNo.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId == null ? null : processId.trim();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
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

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard == null ? null : bankcard.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
    
    public String getQuickCardFlag() {
		return quickCardFlag;
	}

	public void setQuickCardFlag(String quickCardFlag) {
		this.quickCardFlag = quickCardFlag;
	}
	
    public String getErrorCodeMsg() {
		return errorCodeMsg;
	}

	public void setErrorCodeMsg(String errorCodeMsg) {
		this.errorCodeMsg = errorCodeMsg;
	}

	public String getLstOptUser() {
		return lstOptUser;
	}

	public void setLstOptUser(String lstOptUser) {
		this.lstOptUser = lstOptUser;
	}

	public Date getLstOptTime() {
		return lstOptTime;
	}

	public void setLstOptTime(Date lstOptTime) {
		this.lstOptTime = lstOptTime;
	}

	public Integer getRequestNum() {
		return requestNum;
	}

	public void setRequestNum(Integer requestNum) {
		this.requestNum = requestNum;
	}

	public Integer getFailureNum() {
		return failureNum;
	}

	public void setFailureNum(Integer failureNum) {
		this.failureNum = failureNum;
	}

	@Override
	public TaskCallInfo clone() throws CloneNotSupportedException {
		return (TaskCallInfo) super.clone();
	}

    
}