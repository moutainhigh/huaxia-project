package com.huaxia.opas.service.tripartite.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.huaxia.opas.dao.tripartite.QyhyStoreDao;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStore;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreBasic;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreData;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreMetaData;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreOrgBasic;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreOrgDetail;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStorePerson;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStorePunishBreak;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreShareHolder;
import com.huaxia.opas.service.tripartite.QyhyStoreService;

@Service("qyhyStoreService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class QyhyStoreServiceImpl implements QyhyStoreService {
	private final static Logger logger = LoggerFactory.getLogger(QyhyStoreServiceImpl.class);
	@Autowired
	private QyhyStoreDao qyhyStoreDao;

	public QyhyStoreDao getQyhyStoreDao() {
		return qyhyStoreDao;
	}

	public void setQyhyStoreDao(QyhyStoreDao qyhyStoreDao) {
		this.qyhyStoreDao = qyhyStoreDao;
	}

	@Override
	public void saveQyhyStoreUpdateDataAdapterAction(QyhyStore qyhyStore, String enterprise)  throws Exception{

		// 根据 单位名称 、关联的uuid(relateUuid)删除 企业信息库数据

		String relateUuid = qyhyStoreDao.selectRelateUuidByEnterprise(enterprise);

		if (relateUuid != null && !"".equals(relateUuid)) {
			// ===========1.企业信息库 企业行业信息 成功失败 参数存储表
			qyhyStoreDao.deleteQyhyStoreDataByRelateUuid(relateUuid);
			// ===========2.企业信息库 SHAREHOLDER 股东及出资信息
			qyhyStoreDao.deleteQyhyStoreShareHolderByRelateUuid(relateUuid);
			// ===========3.企业信息库 BASIC 照面信息
			qyhyStoreDao.deleteQyhyStoreBasicByRelateUuid(relateUuid);
			// ===========4.企业信息库 PERSON 主要管理人员
			qyhyStoreDao.deleteQyhyStorePersonByRelateUuid(relateUuid);
			// ===========5.企业信息库 PUNISHBREAK 失信被执行人信息
			qyhyStoreDao.deleteQyhyStorePunishBreakByRelateUuid(relateUuid);
			// ===========6.企业信息库 ORGBASIC 组织机构列表
			qyhyStoreDao.deleteQyhyStoreOrgBasicByRelateUuid(relateUuid);
			// ===========7.企业信息库 ORGDETAIL 组织机构详情
			qyhyStoreDao.deleteQyhyStoreOrgDetailByRelateUuid(relateUuid);
			// ===========8.企业信息库 METADATA 元数据来源
			qyhyStoreDao.deleteQyhyStoreMetaDataByRelateUuid(relateUuid);
		}
		// ===========1.企业信息库 企业行业信息 成功失败 参数存储表
		QyhyStoreData qyhyStoreData = qyhyStore.getQyhyStoreData();
		qyhyStoreDao.insertQyhyStoreData(qyhyStoreData);
		// ===========2.企业信息库 SHAREHOLDER 股东及出资信息
		List<QyhyStoreShareHolder> qyhyStoreShareHolderList = qyhyStore.getQyhyStoreShareHolderList();
		if (qyhyStoreShareHolderList != null && qyhyStoreShareHolderList.size() > 0) {
			qyhyStoreDao.insertQyhyStoreShareHolderList(qyhyStoreShareHolderList);
		}
		// ===========3.企业信息库 BASIC 照面信息
		QyhyStoreBasic qyhyStoreBasic = qyhyStore.getQyhyStoreBasic();
		if (qyhyStoreBasic != null) {
			qyhyStoreDao.insertQyhyStoreBasic(qyhyStoreBasic);
		}
		// ===========4.企业信息库 PERSON 主要管理人员
		List<QyhyStorePerson> qyhyStorePersonList = qyhyStore.getQyhyStorePersonList();
		if (qyhyStorePersonList != null && qyhyStorePersonList.size() > 0) {
			qyhyStoreDao.insertQyhyStorePersonList(qyhyStorePersonList);
		}
		// ===========5.企业信息库 PUNISHBREAK 失信被执行人信息
		List<QyhyStorePunishBreak> qyhyStorePunishBreakList = qyhyStore.getQyhyStorePunishBreakList();
		if (qyhyStorePunishBreakList != null && qyhyStorePunishBreakList.size() > 0) {
			qyhyStoreDao.insertQyhyStorePunishBreakList(qyhyStorePunishBreakList);
		}
		// ===========6.企业信息库 ORGBASIC 组织机构列表
		List<QyhyStoreOrgBasic> qyhyStoreOrgBasicList = qyhyStore.getQyhyStoreOrgBasicList();
		if (qyhyStoreOrgBasicList != null && qyhyStoreOrgBasicList.size() > 0) {
			qyhyStoreDao.insertQyhyStoreOrgBasicList(qyhyStoreOrgBasicList);
		}
		// ===========7.企业信息库 ORGDETAIL 组织机构详情
		QyhyStoreOrgDetail qyhyStoreOrgDetail = qyhyStore.getQyhyStoreOrgDetail();
		if (qyhyStoreOrgDetail != null) {
			qyhyStoreDao.insertQyhyStoreOrgDetail(qyhyStoreOrgDetail);
		}
		// ===========8.企业信息库 METADATA 元数据来源
		QyhyStoreMetaData qyhyStoreMetaData = qyhyStore.getQyhyStoreMetaData();
		if (qyhyStoreMetaData != null) {
			qyhyStoreDao.insertQyhyStoreMetaData(qyhyStoreMetaData);
		}
	}

	@Override
	public String findRelateUuidByEnterPriseDays(String enterprise, Integer qyhyStoreQueryDays) {
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("enterprise", enterprise);
		params.put("qyhyStoreQueryDays", qyhyStoreQueryDays);
		return qyhyStoreDao.selectRelateUuidByEnterPriseDays(params);
	}

	@Override
	public Map<String, Object> saveCloneQyhyInfoData(Map<String,Object> params) {
		return qyhyStoreDao.saveCloneQyhyInfoData(params);
	}

}