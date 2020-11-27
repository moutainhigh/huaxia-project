package com.huaxia.opas.domain;

import java.io.Serializable;

import com.huaxia.opas.domain.brzx.SpecialListForGid;

/**
 * 第三方 & 百融
 * 
 * @author zhiguo.li
 *
 */
public class BRZX implements Serializable {

	private static final long serialVersionUID = 2460870788892083779L;

	// 申请件编号
	private String appId;

	// 创建时间
	private String crtTime;

	// 创建人
	private String crtUser;

	// 最后操作时间
	private String lstUpdTime;

	// 最后更新人
	private String lstUpdUser;

	// 响应参数 & 流水号（设备请求唯一标识）
	private String swiftNumber;

	// 响应参数 & 响应代码
	private String code;

	// 特殊名单核查 & 通过身份证查询
	private BRZXSpecialListForId specialListForId;

	// 特殊名单核查 & 通过手机号查询
	private BRZXSpecialListForCell specialListForCell;

	// 特殊名单核查 & 通过联系人手机号查询
	private BRZXSpecialListForLmCell specialListForLmCell;

	// 特殊名单核查 & 通过GID查询
	private SpecialListForGid specialListForGid;

	// 地址信息核对
	private BRZXLocation location;

	// 商品消费评估
	private BRZXConsumption consumption;

	// 百融通用评分
	private BRZXCreditPoint creditPoint;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSwiftNumber() {
		return swiftNumber;
	}

	public void setSwiftNumber(String swiftNumber) {
		this.swiftNumber = swiftNumber;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BRZXSpecialListForId getSpecialListForId() {
		return specialListForId;
	}

	public void setSpecialListForId(BRZXSpecialListForId specialListForId) {
		this.specialListForId = specialListForId;
	}

	public BRZXSpecialListForCell getSpecialListForCell() {
		return specialListForCell;
	}

	public void setSpecialListForCell(BRZXSpecialListForCell specialListForCell) {
		this.specialListForCell = specialListForCell;
	}

	public BRZXSpecialListForLmCell getSpecialListForLmCell() {
		return specialListForLmCell;
	}

	public void setSpecialListForLmCell(BRZXSpecialListForLmCell specialListForLmCell) {
		this.specialListForLmCell = specialListForLmCell;
	}

	public BRZXLocation getLocation() {
		return location;
	}

	public void setLocation(BRZXLocation location) {
		this.location = location;
	}

	public BRZXConsumption getConsumption() {
		return consumption;
	}

	public void setConsumption(BRZXConsumption consumption) {
		this.consumption = consumption;
	}

	public BRZXCreditPoint getCreditPoint() {
		return creditPoint;
	}

	public void setCreditPoint(BRZXCreditPoint creditPoint) {
		this.creditPoint = creditPoint;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(String lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public SpecialListForGid getSpecialListForGid() {
		return specialListForGid;
	}

	public void setSpecialListForGid(SpecialListForGid specialListForGid) {
		this.specialListForGid = specialListForGid;
	}

}
