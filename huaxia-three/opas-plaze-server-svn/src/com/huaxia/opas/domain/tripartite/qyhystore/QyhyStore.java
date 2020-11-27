package com.huaxia.opas.domain.tripartite.qyhystore;

import java.io.Serializable;
import java.util.List;
/**
 * 企业信息库 企业行业信息总对象
 * @author gaoh
 */
public class QyhyStore implements Serializable{
	private static final long serialVersionUID = 1845558674770468094L;

	private QyhyStoreData qyhyStoreData;//企业行业信息 成功失败 参数存储表
	
	private QyhyStoreBasic qyhyStoreBasic;//BASIC 照面信息		
	
	private List<QyhyStoreOrgBasic> qyhyStoreOrgBasicList;//ORGBASIC 组织机构列表		
	
	private QyhyStoreOrgDetail qyhyStoreOrgDetail;//ORGDETAIL 组织机构详情		
	
	private List<QyhyStorePerson> qyhyStorePersonList;//PERSON 主要管理人员		
	
	private List<QyhyStorePunishBreak> qyhyStorePunishBreakList;//PUNISHBREAK 失信被执行人信息		
	
	private List<QyhyStoreShareHolder> qyhyStoreShareHolderList;//SHAREHOLDER 股东及出资信息 	
	
    private QyhyStoreMetaData  qyhyStoreMetaData;//METADATA 元数据来源		

	public QyhyStoreData getQyhyStoreData() {
		return qyhyStoreData;
	}

	public void setQyhyStoreData(QyhyStoreData qyhyStoreData) {
		this.qyhyStoreData = qyhyStoreData;
	}

	public QyhyStoreBasic getQyhyStoreBasic() {
		return qyhyStoreBasic;
	}

	public void setQyhyStoreBasic(QyhyStoreBasic qyhyStoreBasic) {
		this.qyhyStoreBasic = qyhyStoreBasic;
	}

	public List<QyhyStoreOrgBasic> getQyhyStoreOrgBasicList() {
		return qyhyStoreOrgBasicList;
	}

	public void setQyhyStoreOrgBasicList(List<QyhyStoreOrgBasic> qyhyStoreOrgBasicList) {
		this.qyhyStoreOrgBasicList = qyhyStoreOrgBasicList;
	}

	public QyhyStoreOrgDetail getQyhyStoreOrgDetail() {
		return qyhyStoreOrgDetail;
	}

	public void setQyhyStoreOrgDetail(QyhyStoreOrgDetail qyhyStoreOrgDetail) {
		this.qyhyStoreOrgDetail = qyhyStoreOrgDetail;
	}

	public List<QyhyStorePerson> getQyhyStorePersonList() {
		return qyhyStorePersonList;
	}

	public void setQyhyStorePersonList(List<QyhyStorePerson> qyhyStorePersonList) {
		this.qyhyStorePersonList = qyhyStorePersonList;
	}

	public List<QyhyStorePunishBreak> getQyhyStorePunishBreakList() {
		return qyhyStorePunishBreakList;
	}

	public void setQyhyStorePunishBreakList(List<QyhyStorePunishBreak> qyhyStorePunishBreakList) {
		this.qyhyStorePunishBreakList = qyhyStorePunishBreakList;
	}

	public List<QyhyStoreShareHolder> getQyhyStoreShareHolderList() {
		return qyhyStoreShareHolderList;
	}

	public void setQyhyStoreShareHolderList(List<QyhyStoreShareHolder> qyhyStoreShareHolderList) {
		this.qyhyStoreShareHolderList = qyhyStoreShareHolderList;
	}

	public QyhyStoreMetaData getQyhyStoreMetaData() {
		return qyhyStoreMetaData;
	}

	public void setQyhyStoreMetaData(QyhyStoreMetaData qyhyStoreMetaData) {
		this.qyhyStoreMetaData = qyhyStoreMetaData;
	}
    
}