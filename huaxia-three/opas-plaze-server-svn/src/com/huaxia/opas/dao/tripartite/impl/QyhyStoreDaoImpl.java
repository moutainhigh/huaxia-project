package com.huaxia.opas.dao.tripartite.impl;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import com.huaxia.opas.dao.tripartite.QyhyStoreDao;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreBasic;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreData;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreMetaData;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreOrgBasic;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreOrgDetail;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStorePerson;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStorePunishBreak;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreShareHolder;
import com.huaxia.opas.mapper.tripartite.QyhyStoreMapper;

@Repository
public class QyhyStoreDaoImpl implements QyhyStoreDao {
	private final static Logger logger = LoggerFactory.getLogger(QyhyStoreDaoImpl.class);
	@Resource(name = "dataSource")
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() throws SQLException {
		return DataSourceUtils.doGetConnection(dataSource);
	}
	@Resource
	private QyhyStoreMapper qyhyStoreMapper;

	public QyhyStoreMapper getQyhyStoreMapper() {
		return qyhyStoreMapper;
	}

	public void setQyhyStoreMapper(QyhyStoreMapper qyhyStoreMapper) {
		this.qyhyStoreMapper = qyhyStoreMapper;
	}

	@Override
	public String selectRelateUuidByEnterprise(String enterprise) {
		return qyhyStoreMapper.selectRelateUuidByEnterprise(enterprise);		
	}

	@Override
	public void deleteQyhyStoreDataByRelateUuid(String relateUuid) {
		qyhyStoreMapper.deleteQyhyStoreDataByRelateUuid(relateUuid);
	}

	@Override
	public void deleteQyhyStoreShareHolderByRelateUuid(String relateUuid) {
		qyhyStoreMapper.deleteQyhyStoreShareHolderByRelateUuid(relateUuid);
	}

	@Override
	public void deleteQyhyStoreBasicByRelateUuid(String relateUuid) {
		qyhyStoreMapper.deleteQyhyStoreBasicByRelateUuid(relateUuid);
	}

	@Override
	public void deleteQyhyStorePersonByRelateUuid(String relateUuid) {
		qyhyStoreMapper.deleteQyhyStorePersonByRelateUuid(relateUuid);
	}

	@Override
	public void deleteQyhyStorePunishBreakByRelateUuid(String relateUuid) {
		qyhyStoreMapper.deleteQyhyStorePunishBreakByRelateUuid(relateUuid);
	}

	@Override
	public void deleteQyhyStoreOrgBasicByRelateUuid(String relateUuid) {
		qyhyStoreMapper.deleteQyhyStoreOrgBasicByRelateUuid(relateUuid);
	}

	@Override
	public void deleteQyhyStoreOrgDetailByRelateUuid(String relateUuid) {
		qyhyStoreMapper.deleteQyhyStoreOrgDetailByRelateUuid(relateUuid);
	}

	@Override
	public void deleteQyhyStoreMetaDataByRelateUuid(String relateUuid) {
		qyhyStoreMapper.deleteQyhyStoreMetaDataByRelateUuid(relateUuid);
	}

	@Override
	public void insertQyhyStoreData(QyhyStoreData qyhyStoreData) {
		qyhyStoreMapper.insertQyhyStoreData(qyhyStoreData);
	}

	@Override
	public void insertQyhyStoreShareHolderList(List<QyhyStoreShareHolder> qyhyStoreShareHolderList) {
		qyhyStoreMapper.insertQyhyStoreShareHolderList(qyhyStoreShareHolderList);
	}

	@Override
	public void insertQyhyStoreBasic(QyhyStoreBasic qyhyStoreBasic) {
		qyhyStoreMapper.insertQyhyStoreBasic(qyhyStoreBasic);
	}

	@Override
	public void insertQyhyStorePersonList(List<QyhyStorePerson> qyhyStorePersonList) {
		qyhyStoreMapper.insertQyhyStorePersonList(qyhyStorePersonList);
	}

	@Override
	public void insertQyhyStorePunishBreakList(List<QyhyStorePunishBreak> qyhyStorePunishBreakList) throws Exception {
		Connection conn = getConnection();
		PreparedStatement prest = conn.prepareStatement("INSERT INTO TRD_QYHY_STORE_PUNISHBREAK (UUID, RELATE_UUID, CASESTATE,COURTNAME,DISRUPTTYPENAME, PERFORMANCE,PUBLISHDATECLEAN, CRT_TIME, DUTY)VALUES(SYS_GUID(),?,?,?,?,?,?,SYSDATE,?)");
		try {
			for (QyhyStorePunishBreak qyhyStorePunishBreak : qyhyStorePunishBreakList) {
				prest.setString(1, qyhyStorePunishBreak.getRelateUuid());
				prest.setString(2, qyhyStorePunishBreak.getCasestate());
				prest.setString(3, qyhyStorePunishBreak.getCourtname());
				prest.setString(4, qyhyStorePunishBreak.getDisrupttypename());
				prest.setString(5, qyhyStorePunishBreak.getPerformance());
				prest.setString(6, qyhyStorePunishBreak.getPublishdateclean());
				String duty=qyhyStorePunishBreak.getDuty();
				if(duty==null||"".equals(duty)){
					prest.setString(7, "");
				}else{
					StringReader reader = new StringReader(duty);
					prest.setCharacterStream(7, reader, duty.length());
				}
				prest.addBatch();
			}
			prest.executeBatch();
			prest.clearBatch();
			conn.commit();
		/*	if(logger.isInfoEnabled()){
				logger.info("INSERT INTO TRD_QYHY_STORE_PUNISHBREAK (UUID, RELATE_UUID, CASESTATE,COURTNAME,DISRUPTTYPENAME, PERFORMANCE,PUBLISHDATECLEAN, CRT_TIME, DUTY)VALUES(SYS_GUID(),?,?,?,?,?,?,SYSDATE,?),qyhyStorePunishBreakList:{}",qyhyStorePunishBreakList);
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			throw new RuntimeException();
		} finally {
			prest.close();
		}
		
	//	qyhyStoreMapper.insertQyhyStorePunishBreakList(qyhyStorePunishBreakList);
	}

	@Override
	public void insertQyhyStoreOrgBasicList(List<QyhyStoreOrgBasic> qyhyStoreOrgBasicList) {
		qyhyStoreMapper.insertQyhyStoreOrgBasicList(qyhyStoreOrgBasicList);
	}

	@Override
	public void insertQyhyStoreOrgDetail(QyhyStoreOrgDetail qyhyStoreOrgDetail) {
		qyhyStoreMapper.insertQyhyStoreOrgDetail(qyhyStoreOrgDetail);
	}

	@Override
	public void insertQyhyStoreMetaData(QyhyStoreMetaData qyhyStoreMetaData) {
		qyhyStoreMapper.insertQyhyStoreMetaData(qyhyStoreMetaData);
	}

	public String selectRelateUuidByEnterPriseDays(Map<String, Object> params) {
		return qyhyStoreMapper.selectRelateUuidByEnterPriseDays(params);
	}

	@Override
	public Map<String, Object> saveCloneQyhyInfoData(Map<String,Object> params) {
		return qyhyStoreMapper.saveCloneQyhyInfoData(params);
	}

}