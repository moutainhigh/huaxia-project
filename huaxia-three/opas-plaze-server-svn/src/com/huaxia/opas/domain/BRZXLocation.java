package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 百融征信 & 反欺诈 & 地址信息核对
 * 
 * @author zhiguo.li
 *
 */
public class BRZXLocation extends BRZX implements Serializable {

	private static final long serialVersionUID = -4513182037298360175L;

	// 请求参数 & 身份证号
	private String identityCard;

	// 请求参数 & 手机号
	private String cell;

	// 请求参数 & 姓名
	private String name;

	// 请求参数 & 公司地址
	private String bizAddr;

	// 请求参数 & 家庭地址
	private String homeAddr;

	// 请求参数 & 户籍地址
	private String perAddr;

	// 请求参数 & 申请地址
	private String applyAddr;

	// 请求参数 & 其他地址
	private String othAddr;

	// 响应参数 & 地址信息核对产品输出标识
	private String flagLocation;

	// 响应参数 & 距离公司地址的最小距离1
	private String locationBizAddr1;

	// 响应参数 & 距离公司地址的最小距离2
	private String locationBizAddr2;

	// 响应参数 & 距离公司地址的最小距离3
	private String locationBizAddr3;

	// 响应参数 & 距离公司地址的最小距离4
	private String locationBizAddr4;

	// 响应参数 & 距离公司地址的最小距离5
	private String locationBizAddr5;

	// 响应参数 & 距离家庭地址的最小距离1
	private String locationHomeAddr1;

	// 响应参数 & 距离家庭地址的最小距离2
	private String locationHomeAddr2;

	// 响应参数 & 距离家庭地址的最小距离3
	private String locationHomeAddr3;

	// 响应参数 & 距离家庭地址的最小距离4
	private String locationHomeAddr4;

	// 响应参数 & 距离家庭地址的最小距离5
	private String locationHomeAddr5;

	// 响应参数 & 距离户籍地址的最小距离1
	private String locationPerAddr1;

	// 响应参数 & 距离户籍地址的最小距离2
	private String locationPerAddr2;

	// 响应参数 & 距离户籍地址的最小距离3
	private String locationPerAddr3;

	// 响应参数 & 距离户籍地址的最小距离4
	private String locationPerAddr4;

	// 响应参数 & 距离户籍地址的最小距离5
	private String locationPerAddr5;

	// 响应参数 & 距离申请地址的最小距离1
	private String locationApplyAddr1;

	// 响应参数 & 距离申请地址的最小距离2
	private String locationApplyAddr2;

	// 响应参数 & 距离申请地址的最小距离3
	private String locationApplyAddr3;

	// 响应参数 & 距离申请地址的最小距离4
	private String locationApplyAddr4;

	// 响应参数 & 距离申请地址的最小距离5
	private String locationApplyAddr5;

	// 响应参数 & 距离其他地址的最小距离1
	private String locationOthAddr1;

	// 响应参数 & 距离其他地址的最小距离2
	private String locationOthAddr2;

	// 响应参数 & 距离其他地址的最小距离3
	private String locationOthAddr3;

	// 响应参数 & 距离其他地址的最小距离4
	private String locationOthAddr4;

	// 响应参数 & 距离其他地址的最小距离5
	private String locationOthAddr5;

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBizAddr() {
		return bizAddr;
	}

	public void setBizAddr(String bizAddr) {
		this.bizAddr = bizAddr;
	}

	public String getHomeAddr() {
		return homeAddr;
	}

	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	public String getPerAddr() {
		return perAddr;
	}

	public void setPerAddr(String perAddr) {
		this.perAddr = perAddr;
	}

	public String getApplyAddr() {
		return applyAddr;
	}

	public void setApplyAddr(String applyAddr) {
		this.applyAddr = applyAddr;
	}

	public String getOthAddr() {
		return othAddr;
	}

	public void setOthAddr(String othAddr) {
		this.othAddr = othAddr;
	}

	public String getFlagLocation() {
		return flagLocation;
	}

	public void setFlagLocation(String flagLocation) {
		this.flagLocation = flagLocation;
	}

	public String getLocationBizAddr1() {
		return locationBizAddr1;
	}

	public void setLocationBizAddr1(String locationBizAddr1) {
		this.locationBizAddr1 = locationBizAddr1;
	}

	public String getLocationBizAddr2() {
		return locationBizAddr2;
	}

	public void setLocationBizAddr2(String locationBizAddr2) {
		this.locationBizAddr2 = locationBizAddr2;
	}

	public String getLocationBizAddr3() {
		return locationBizAddr3;
	}

	public void setLocationBizAddr3(String locationBizAddr3) {
		this.locationBizAddr3 = locationBizAddr3;
	}

	public String getLocationBizAddr4() {
		return locationBizAddr4;
	}

	public void setLocationBizAddr4(String locationBizAddr4) {
		this.locationBizAddr4 = locationBizAddr4;
	}

	public String getLocationBizAddr5() {
		return locationBizAddr5;
	}

	public void setLocationBizAddr5(String locationBizAddr5) {
		this.locationBizAddr5 = locationBizAddr5;
	}

	public String getLocationHomeAddr1() {
		return locationHomeAddr1;
	}

	public void setLocationHomeAddr1(String locationHomeAddr1) {
		this.locationHomeAddr1 = locationHomeAddr1;
	}

	public String getLocationHomeAddr2() {
		return locationHomeAddr2;
	}

	public void setLocationHomeAddr2(String locationHomeAddr2) {
		this.locationHomeAddr2 = locationHomeAddr2;
	}

	public String getLocationHomeAddr3() {
		return locationHomeAddr3;
	}

	public void setLocationHomeAddr3(String locationHomeAddr3) {
		this.locationHomeAddr3 = locationHomeAddr3;
	}

	public String getLocationHomeAddr4() {
		return locationHomeAddr4;
	}

	public void setLocationHomeAddr4(String locationHomeAddr4) {
		this.locationHomeAddr4 = locationHomeAddr4;
	}

	public String getLocationHomeAddr5() {
		return locationHomeAddr5;
	}

	public void setLocationHomeAddr5(String locationHomeAddr5) {
		this.locationHomeAddr5 = locationHomeAddr5;
	}

	public String getLocationPerAddr1() {
		return locationPerAddr1;
	}

	public void setLocationPerAddr1(String locationPerAddr1) {
		this.locationPerAddr1 = locationPerAddr1;
	}

	public String getLocationPerAddr2() {
		return locationPerAddr2;
	}

	public void setLocationPerAddr2(String locationPerAddr2) {
		this.locationPerAddr2 = locationPerAddr2;
	}

	public String getLocationPerAddr3() {
		return locationPerAddr3;
	}

	public void setLocationPerAddr3(String locationPerAddr3) {
		this.locationPerAddr3 = locationPerAddr3;
	}

	public String getLocationPerAddr4() {
		return locationPerAddr4;
	}

	public void setLocationPerAddr4(String locationPerAddr4) {
		this.locationPerAddr4 = locationPerAddr4;
	}

	public String getLocationPerAddr5() {
		return locationPerAddr5;
	}

	public void setLocationPerAddr5(String locationPerAddr5) {
		this.locationPerAddr5 = locationPerAddr5;
	}

	public String getLocationApplyAddr1() {
		return locationApplyAddr1;
	}

	public void setLocationApplyAddr1(String locationApplyAddr1) {
		this.locationApplyAddr1 = locationApplyAddr1;
	}

	public String getLocationApplyAddr2() {
		return locationApplyAddr2;
	}

	public void setLocationApplyAddr2(String locationApplyAddr2) {
		this.locationApplyAddr2 = locationApplyAddr2;
	}

	public String getLocationApplyAddr3() {
		return locationApplyAddr3;
	}

	public void setLocationApplyAddr3(String locationApplyAddr3) {
		this.locationApplyAddr3 = locationApplyAddr3;
	}

	public String getLocationApplyAddr4() {
		return locationApplyAddr4;
	}

	public void setLocationApplyAddr4(String locationApplyAddr4) {
		this.locationApplyAddr4 = locationApplyAddr4;
	}

	public String getLocationApplyAddr5() {
		return locationApplyAddr5;
	}

	public void setLocationApplyAddr5(String locationApplyAddr5) {
		this.locationApplyAddr5 = locationApplyAddr5;
	}

	public String getLocationOthAddr1() {
		return locationOthAddr1;
	}

	public void setLocationOthAddr1(String locationOthAddr1) {
		this.locationOthAddr1 = locationOthAddr1;
	}

	public String getLocationOthAddr2() {
		return locationOthAddr2;
	}

	public void setLocationOthAddr2(String locationOthAddr2) {
		this.locationOthAddr2 = locationOthAddr2;
	}

	public String getLocationOthAddr3() {
		return locationOthAddr3;
	}

	public void setLocationOthAddr3(String locationOthAddr3) {
		this.locationOthAddr3 = locationOthAddr3;
	}

	public String getLocationOthAddr4() {
		return locationOthAddr4;
	}

	public void setLocationOthAddr4(String locationOthAddr4) {
		this.locationOthAddr4 = locationOthAddr4;
	}

	public String getLocationOthAddr5() {
		return locationOthAddr5;
	}

	public void setLocationOthAddr5(String locationOthAddr5) {
		this.locationOthAddr5 = locationOthAddr5;
	}

}
