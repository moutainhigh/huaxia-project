package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 百融征信 & 信用评估 & 商品消费评估
 * 
 * @author zhiguo.li
 *
 */
public class BRZXConsumption extends BRZX implements Serializable {

	private static final long serialVersionUID = -3857091464016394670L;

	// 请求参数 & 身份证号
	private String identityCard;

	// 请求参数 & 手机号
	private String cell;

	// 请求参数 & 姓名
	private String name;

	// 请求参数 & 电子邮件
	private String mail;

	// 响应参数 & 商品消费评估产品输出标识
	private String flagConsumption;

	// 响应参数 & 过去3/6/12个月内持续消费情况
	private String consCont;

	// 响应参数 & 最近一次消费距离现在的时间
	private String consTimeRecent;

	// 响应参数 & 近3个月消费次数总和
	private String consTotM3Num;

	// 响应参数 & 近3个月消费金额总和
	private String consTotM3Pay;

	// 响应参数 & 近3个月消费金额>0类目类别数
	private String consTotM3PCatenum;

	// 响应参数 & 近3个月浏览类目类别数
	private String consTotM3VCatenum;

	// 响应参数 & 近3个月浏览次数总和
	private String consTotM3Visits;

	// 响应参数 & 近12个月消费次数总和
	private String consTotM12Num;

	// 响应参数 & 近12个月消费金额总和
	private String consTotM12Pay;

	// 响应参数 & 近12个月消费金额>0类目类别数
	private String consTotM12PCatenum;

	// 响应参数 & 近12个月浏览类目类别数
	private String consTotM12VCatenum;

	// 响应参数 & 近12个月浏览次数总和
	private String consTotM12Visits;

	// 响应参数 & 近3个月最大单类目消费次数
	private String consMaxM3Num;

	// 响应参数 & 近3个月最大单类目消费金额
	private String consMaxM3Pay;

	// 响应参数 & 近3个月最大单类目消费金额的类目
	private String consMaxM3Paycate;

	// 响应参数 & 近12个月最大单类目消费次数
	private String consMaxM12Num;

	// 响应参数 & 近12个月最大单类目消费金额
	private String consMaxM12Pay;

	// 响应参数 & 近12个月最大单类目消费金额的类目
	private String consMaxM12Paycate;

	// 响应参数 & 近3个月日用百货类商品总消费次数
	private String consM3RYBHNum;

	// 响应参数 & 近3个月日用百货类商品总消费金额
	private String consM3RYBHPay;

	// 响应参数 & 近3个月家用电器类商品总消费次数
	private String consM3JYDQNum;

	// 响应参数 & 近3个月家用电器类商品总消费金额
	private String consM3JYDQPay;

	// 响应参数 & 近3个月母婴用品类商品总消费次数
	private String consM3MYYPNum;

	// 响应参数 & 近3个月母婴用品类商品总消费金额
	private String consM3MYYPPay;

	// 响应参数 & 近12个月日用百货类商品总消费次数
	private String consM12RYBHNum;

	// 响应参数 & 近12个月日用百货类商品总消费金额
	private String consM12RYBHPay;

	// 响应参数 & 近12个月家用电器类商品总消费次数
	private String consM12JYDQNum;

	// 响应参数 & 近12个月家用电器类商品总消费金额
	private String consM12JYDQPay;

	// 响应参数 & 近12个月母婴用品类商品总消费次数
	private String consM12MYYPNum;

	// 响应参数 & 近12个月母婴用品类商品总消费金额
	private String consM12MYYPPay;

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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFlagConsumption() {
		return flagConsumption;
	}

	public void setFlagConsumption(String flagConsumption) {
		this.flagConsumption = flagConsumption;
	}

	public String getConsCont() {
		return consCont;
	}

	public void setConsCont(String consCont) {
		this.consCont = consCont;
	}

	public String getConsTimeRecent() {
		return consTimeRecent;
	}

	public void setConsTimeRecent(String consTimeRecent) {
		this.consTimeRecent = consTimeRecent;
	}

	public String getConsTotM3Num() {
		return consTotM3Num;
	}

	public void setConsTotM3Num(String consTotM3Num) {
		this.consTotM3Num = consTotM3Num;
	}

	public String getConsTotM3Pay() {
		return consTotM3Pay;
	}

	public void setConsTotM3Pay(String consTotM3Pay) {
		this.consTotM3Pay = consTotM3Pay;
	}

	public String getConsTotM3PCatenum() {
		return consTotM3PCatenum;
	}

	public void setConsTotM3PCatenum(String consTotM3PCatenum) {
		this.consTotM3PCatenum = consTotM3PCatenum;
	}

	public String getConsTotM3VCatenum() {
		return consTotM3VCatenum;
	}

	public void setConsTotM3VCatenum(String consTotM3VCatenum) {
		this.consTotM3VCatenum = consTotM3VCatenum;
	}

	public String getConsTotM3Visits() {
		return consTotM3Visits;
	}

	public void setConsTotM3Visits(String consTotM3Visits) {
		this.consTotM3Visits = consTotM3Visits;
	}

	public String getConsTotM12Num() {
		return consTotM12Num;
	}

	public void setConsTotM12Num(String consTotM12Num) {
		this.consTotM12Num = consTotM12Num;
	}

	public String getConsTotM12Pay() {
		return consTotM12Pay;
	}

	public void setConsTotM12Pay(String consTotM12Pay) {
		this.consTotM12Pay = consTotM12Pay;
	}

	public String getConsTotM12PCatenum() {
		return consTotM12PCatenum;
	}

	public void setConsTotM12PCatenum(String consTotM12PCatenum) {
		this.consTotM12PCatenum = consTotM12PCatenum;
	}

	public String getConsTotM12VCatenum() {
		return consTotM12VCatenum;
	}

	public void setConsTotM12VCatenum(String consTotM12VCatenum) {
		this.consTotM12VCatenum = consTotM12VCatenum;
	}

	public String getConsTotM12Visits() {
		return consTotM12Visits;
	}

	public void setConsTotM12Visits(String consTotM12Visits) {
		this.consTotM12Visits = consTotM12Visits;
	}

	public String getConsMaxM3Num() {
		return consMaxM3Num;
	}

	public void setConsMaxM3Num(String consMaxM3Num) {
		this.consMaxM3Num = consMaxM3Num;
	}

	public String getConsMaxM3Pay() {
		return consMaxM3Pay;
	}

	public void setConsMaxM3Pay(String consMaxM3Pay) {
		this.consMaxM3Pay = consMaxM3Pay;
	}

	public String getConsMaxM3Paycate() {
		return consMaxM3Paycate;
	}

	public void setConsMaxM3Paycate(String consMaxM3Paycate) {
		this.consMaxM3Paycate = consMaxM3Paycate;
	}

	public String getConsMaxM12Num() {
		return consMaxM12Num;
	}

	public void setConsMaxM12Num(String consMaxM12Num) {
		this.consMaxM12Num = consMaxM12Num;
	}

	public String getConsMaxM12Pay() {
		return consMaxM12Pay;
	}

	public void setConsMaxM12Pay(String consMaxM12Pay) {
		this.consMaxM12Pay = consMaxM12Pay;
	}

	public String getConsMaxM12Paycate() {
		return consMaxM12Paycate;
	}

	public void setConsMaxM12Paycate(String consMaxM12Paycate) {
		this.consMaxM12Paycate = consMaxM12Paycate;
	}

	public String getConsM3RYBHNum() {
		return consM3RYBHNum;
	}

	public void setConsM3RYBHNum(String consM3RYBHNum) {
		this.consM3RYBHNum = consM3RYBHNum;
	}

	public String getConsM3RYBHPay() {
		return consM3RYBHPay;
	}

	public void setConsM3RYBHPay(String consM3RYBHPay) {
		this.consM3RYBHPay = consM3RYBHPay;
	}

	public String getConsM3JYDQNum() {
		return consM3JYDQNum;
	}

	public void setConsM3JYDQNum(String consM3JYDQNum) {
		this.consM3JYDQNum = consM3JYDQNum;
	}

	public String getConsM3JYDQPay() {
		return consM3JYDQPay;
	}

	public void setConsM3JYDQPay(String consM3JYDQPay) {
		this.consM3JYDQPay = consM3JYDQPay;
	}

	public String getConsM3MYYPNum() {
		return consM3MYYPNum;
	}

	public void setConsM3MYYPNum(String consM3MYYPNum) {
		this.consM3MYYPNum = consM3MYYPNum;
	}

	public String getConsM3MYYPPay() {
		return consM3MYYPPay;
	}

	public void setConsM3MYYPPay(String consM3MYYPPay) {
		this.consM3MYYPPay = consM3MYYPPay;
	}

	public String getConsM12RYBHNum() {
		return consM12RYBHNum;
	}

	public void setConsM12RYBHNum(String consM12RYBHNum) {
		this.consM12RYBHNum = consM12RYBHNum;
	}

	public String getConsM12RYBHPay() {
		return consM12RYBHPay;
	}

	public void setConsM12RYBHPay(String consM12RYBHPay) {
		this.consM12RYBHPay = consM12RYBHPay;
	}

	public String getConsM12JYDQNum() {
		return consM12JYDQNum;
	}

	public void setConsM12JYDQNum(String consM12JYDQNum) {
		this.consM12JYDQNum = consM12JYDQNum;
	}

	public String getConsM12JYDQPay() {
		return consM12JYDQPay;
	}

	public void setConsM12JYDQPay(String consM12JYDQPay) {
		this.consM12JYDQPay = consM12JYDQPay;
	}

	public String getConsM12MYYPNum() {
		return consM12MYYPNum;
	}

	public void setConsM12MYYPNum(String consM12MYYPNum) {
		this.consM12MYYPNum = consM12MYYPNum;
	}

	public String getConsM12MYYPPay() {
		return consM12MYYPPay;
	}

	public void setConsM12MYYPPay(String consM12MYYPPay) {
		this.consM12MYYPPay = consM12MYYPPay;
	}

}
