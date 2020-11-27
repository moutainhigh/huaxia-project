package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & CRM & 签约汇总表（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsSignSmryTotal extends CRM implements Serializable {

	private static final long serialVersionUID = 3980652015419196468L;
	
	// 客户号
	private String custId;
	
	// 定期签约
	private String fixedDepSign;
	
	// 活期签约
	private String currSign;
	
	// 理财签约
	private String finSign;
	
	// 基金签约
	private String fundSign;
	
	// 电子国债签约
	private String eBondSign;
	
	// 凭证国债签约
	private String bondSign;
	
	// 保险签约
	private String insSign;
	
	// 第三方存管签约
	private String tpccSign;
	
	// 信托签约
	private String trustSign;
	
	// 贵金属
	private String goldSign;
	
	// 外汇买卖
	private String whTransSign;
	
	// 稳盈灵通账户
	private String wyltSign;
	
	// 国际结算
	private String intAccontSign;
	
	// 个人贷款
	private String lnsSign;
	
	// 信用卡
	private String crCardSign;
	
	// 个人网银签约版
	private String netBankQySign;
	
	// 个人网银证书版
	private String netBankZsSign;
	
	// 营销活动终端
	private String posSign;
	
	// T营销活动终端
	private String tPosSign;
	
	// 电话银行
	private String phoneBankSign;
	
	// ATM跨行转账
	private String atmIbtSign;
	
	// 手机银行
	private String mPhoneSing;
	
	// 短信通知
	private String messageSign;
	
	// 代发工资
	private String payRollSign;
	
	// 约定转存
	private String conDepositSign;
	
	// 支付宝卡通
	private String treaCardSign;
	
	// E商宝
	private String eBusiSign;
	
	// 信用卡自扣还款
	private String crCardAutoSign;
	
	// 无卡支付
	private String noCardPaySign;
	
	// 银联网上支付
	private String upNetPaySign;
	
	// 电子支付
	private String elePaySign;
	
	// 银联无卡支付
	private String upNoCardPaySign;
	
	// 代缴费
	private String billSign;
	
	// ETC
	private String etcSign;
	
	// 存抵贷业务
	private String dTolSign;
	
	// 数据日期
	private String dDate;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getFixedDepSign() {
		return fixedDepSign;
	}

	public void setFixedDepSign(String fixedDepSign) {
		this.fixedDepSign = fixedDepSign;
	}

	public String getCurrSign() {
		return currSign;
	}

	public void setCurrSign(String currSign) {
		this.currSign = currSign;
	}

	public String getFinSign() {
		return finSign;
	}

	public void setFinSign(String finSign) {
		this.finSign = finSign;
	}

	public String getFundSign() {
		return fundSign;
	}

	public void setFundSign(String fundSign) {
		this.fundSign = fundSign;
	}

	public String geteBondSign() {
		return eBondSign;
	}

	public void seteBondSign(String eBondSign) {
		this.eBondSign = eBondSign;
	}

	public String getBondSign() {
		return bondSign;
	}

	public void setBondSign(String bondSign) {
		this.bondSign = bondSign;
	}

	public String getInsSign() {
		return insSign;
	}

	public void setInsSign(String insSign) {
		this.insSign = insSign;
	}

	public String getTpccSign() {
		return tpccSign;
	}

	public void setTpccSign(String tpccSign) {
		this.tpccSign = tpccSign;
	}

	public String getTrustSign() {
		return trustSign;
	}

	public void setTrustSign(String trustSign) {
		this.trustSign = trustSign;
	}

	public String getGoldSign() {
		return goldSign;
	}

	public void setGoldSign(String goldSign) {
		this.goldSign = goldSign;
	}

	public String getWhTransSign() {
		return whTransSign;
	}

	public void setWhTransSign(String whTransSign) {
		this.whTransSign = whTransSign;
	}

	public String getWyltSign() {
		return wyltSign;
	}

	public void setWyltSign(String wyltSign) {
		this.wyltSign = wyltSign;
	}

	public String getIntAccontSign() {
		return intAccontSign;
	}

	public void setIntAccontSign(String intAccontSign) {
		this.intAccontSign = intAccontSign;
	}

	public String getLnsSign() {
		return lnsSign;
	}

	public void setLnsSign(String lnsSign) {
		this.lnsSign = lnsSign;
	}

	public String getCrCardSign() {
		return crCardSign;
	}

	public void setCrCardSign(String crCardSign) {
		this.crCardSign = crCardSign;
	}

	public String getNetBankQySign() {
		return netBankQySign;
	}

	public void setNetBankQySign(String netBankQySign) {
		this.netBankQySign = netBankQySign;
	}

	public String getNetBankZsSign() {
		return netBankZsSign;
	}

	public void setNetBankZsSign(String netBankZsSign) {
		this.netBankZsSign = netBankZsSign;
	}

	public String getPosSign() {
		return posSign;
	}

	public void setPosSign(String posSign) {
		this.posSign = posSign;
	}

	public String gettPosSign() {
		return tPosSign;
	}

	public void settPosSign(String tPosSign) {
		this.tPosSign = tPosSign;
	}

	public String getPhoneBankSign() {
		return phoneBankSign;
	}

	public void setPhoneBankSign(String phoneBankSign) {
		this.phoneBankSign = phoneBankSign;
	}

	public String getAtmIbtSign() {
		return atmIbtSign;
	}

	public void setAtmIbtSign(String atmIbtSign) {
		this.atmIbtSign = atmIbtSign;
	}

	public String getmPhoneSing() {
		return mPhoneSing;
	}

	public void setmPhoneSing(String mPhoneSing) {
		this.mPhoneSing = mPhoneSing;
	}

	public String getMessageSign() {
		return messageSign;
	}

	public void setMessageSign(String messageSign) {
		this.messageSign = messageSign;
	}

	public String getPayRollSign() {
		return payRollSign;
	}

	public void setPayRollSign(String payRollSign) {
		this.payRollSign = payRollSign;
	}

	public String getConDepositSign() {
		return conDepositSign;
	}

	public void setConDepositSign(String conDepositSign) {
		this.conDepositSign = conDepositSign;
	}

	public String getTreaCardSign() {
		return treaCardSign;
	}

	public void setTreaCardSign(String treaCardSign) {
		this.treaCardSign = treaCardSign;
	}

	public String geteBusiSign() {
		return eBusiSign;
	}

	public void seteBusiSign(String eBusiSign) {
		this.eBusiSign = eBusiSign;
	}

	public String getCrCardAutoSign() {
		return crCardAutoSign;
	}

	public void setCrCardAutoSign(String crCardAutoSign) {
		this.crCardAutoSign = crCardAutoSign;
	}

	public String getNoCardPaySign() {
		return noCardPaySign;
	}

	public void setNoCardPaySign(String noCardPaySign) {
		this.noCardPaySign = noCardPaySign;
	}

	public String getUpNetPaySign() {
		return upNetPaySign;
	}

	public void setUpNetPaySign(String upNetPaySign) {
		this.upNetPaySign = upNetPaySign;
	}

	public String getElePaySign() {
		return elePaySign;
	}

	public void setElePaySign(String elePaySign) {
		this.elePaySign = elePaySign;
	}

	public String getUpNoCardPaySign() {
		return upNoCardPaySign;
	}

	public void setUpNoCardPaySign(String upNoCardPaySign) {
		this.upNoCardPaySign = upNoCardPaySign;
	}

	public String getBillSign() {
		return billSign;
	}

	public void setBillSign(String billSign) {
		this.billSign = billSign;
	}

	public String getEtcSign() {
		return etcSign;
	}

	public void setEtcSign(String etcSign) {
		this.etcSign = etcSign;
	}

	public String getdTolSign() {
		return dTolSign;
	}

	public void setdTolSign(String dTolSign) {
		this.dTolSign = dTolSign;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

}
