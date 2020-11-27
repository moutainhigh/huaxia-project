package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MerchTeamdealList  implements Serializable{
	
	private static final long serialVersionUID = -2844349717487745255L;

	private String autoId;

    private String teamdealNo;

    private String merchName;

    private String produceName;

    private BigDecimal producePrice;

    private String operator;

    private Date operatTime;

    private String currStatus;
    //批量导入排序
    private String orderByNo;

    public String getOrderByNo() {
		return orderByNo;
	}

	public void setOrderByNo(String orderByNo) {
		this.orderByNo = orderByNo;
	}

	public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId == null ? null : autoId.trim();
    }

    public String getTeamdealNo() {
        return teamdealNo;
    }

    public void setTeamdealNo(String teamdealNo) {
        this.teamdealNo = teamdealNo == null ? null : teamdealNo.trim();
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName == null ? null : merchName.trim();
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName == null ? null : produceName.trim();
    }

    public BigDecimal getProducePrice() {
        return producePrice;
    }

    public void setProducePrice(BigDecimal producePrice) {
        this.producePrice = producePrice;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperatTime() {
        return operatTime;
    }

    public void setOperatTime(Date operatTime) {
        this.operatTime = operatTime;
    }

    public String getCurrStatus() {
        return currStatus;
    }

    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus == null ? null : currStatus.trim();
    }
	
	/**
	 * 商户团办名单表实例的历史操作ID NULL
	 */
	private String opretionId;
	

	/**
	 * 商户团办名单实例的添加时间
	 */
	private Date createTime;
	

	/**
	 * 商户团办名单表实例的ID的集合
	 */
	private List<String> ids ;
	

	/**
	 * 获取商户团办名单实例历史操作表的ID
	 */
	public String getOpretionId() {
		return opretionId;
	}

	/**
	 * 设置商户团办名单实例历史操作表的ID
	 */
	public void setOpretionId(String opretionId) {
		this.opretionId = (opretionId == null ? null :opretionId.trim());
	}	
	

	/**
	 * 获取商户团办名单实例的ID的集合
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * 设置商户团办名单实例的ID的集合
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	

	/**
	 * 获取商户团办名单实例的添加时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置商户团办名单实例的添加时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "MerchTeamdealList [autoId=" + autoId + ", teamdealNo=" + teamdealNo + ", merchName=" + merchName
				+ ", produceName=" + produceName + ", producePrice=" + producePrice + ", operator=" + operator
				+ ", currStatus=" + currStatus + "]";
	}

}