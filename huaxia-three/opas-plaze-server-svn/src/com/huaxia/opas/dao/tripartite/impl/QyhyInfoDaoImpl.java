package com.huaxia.opas.dao.tripartite.impl;


import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.tripartite.QyhyInfoDao;
import com.huaxia.opas.domain.tripartite.QyhyInfoBasic;
import com.huaxia.opas.domain.tripartite.QyhyInfoData;
import com.huaxia.opas.domain.tripartite.QyhyInfoMetaData;
import com.huaxia.opas.domain.tripartite.QyhyInfoOrgBasic;
import com.huaxia.opas.domain.tripartite.QyhyInfoOrgDetail;
import com.huaxia.opas.domain.tripartite.QyhyInfoPerson;
import com.huaxia.opas.domain.tripartite.QyhyInfoPunishBreak;
import com.huaxia.opas.domain.tripartite.QyhyInfoShareHolder;
import com.huaxia.opas.mapper.tripartite.QyhyInfoMapper;



@Repository
public class QyhyInfoDaoImpl implements QyhyInfoDao {
	private final static Logger logger = LoggerFactory.getLogger(QyhyInfoDaoImpl.class);
	@Resource(name = "dataSource")
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() throws SQLException {
		return DataSourceUtils.doGetConnection(dataSource);
	}
	@Resource
	private QyhyInfoMapper qyhyInfoMapper;
	
	public QyhyInfoMapper getQyhyInfoMapper() {
		return qyhyInfoMapper;
	}

	public void setQyhyInfoMapper(QyhyInfoMapper qyhyInfoMapper) {
		this.qyhyInfoMapper = qyhyInfoMapper;
	}

	@Override
	public int selectCountByAppId(String appId) {
		return qyhyInfoMapper.selectCountByAppId(appId);
	}

	@Override
	public void insertQyhyInfoData(QyhyInfoData qyhyInfoData) {
		 qyhyInfoMapper.insertQyhyInfoData(qyhyInfoData);
	}

	@Override
	public void insertQyhyInfoShareHolderList(List<QyhyInfoShareHolder> qyhyInfoShareHolderList) {
		qyhyInfoMapper.insertQyhyInfoShareHolderList(qyhyInfoShareHolderList);
	}

	@Override
	public void insertQyhyInfoBasic(QyhyInfoBasic qyhyInfoBasic) {
		qyhyInfoMapper.insertQyhyInfoBasic(qyhyInfoBasic);
	}

	@Override
	public void insertQyhyInfoPersonList(List<QyhyInfoPerson> qyhyInfoPersonList) {
		qyhyInfoMapper.insertQyhyInfoPersonList(qyhyInfoPersonList);
	}

	@Override
	public void insertQyhyInfoPunishBreakList(List<QyhyInfoPunishBreak> qyhyInfoPunishBreakList) throws SQLException {
		
		Connection conn = getConnection();
		PreparedStatement prest = conn.prepareStatement("INSERT INTO TRD_QYHY_INFO_PUNISHBREAK (UUID,APP_ID,CASESTATE,COURTNAME,DISRUPTTYPENAME,PERFORMANCE,PUBLISHDATECLEAN,CRT_TIME,DUTY)VALUES(SYS_GUID(),?,?,?,?,?,?,SYSDATE,?)");
		try {
			
		//	String appId =qyhyInfoPunishBreakList.get(0).getAppId();
			for (QyhyInfoPunishBreak qyhyInfoPunishBreak : qyhyInfoPunishBreakList) {
				prest.setString(1, qyhyInfoPunishBreak.getAppId());
				prest.setString(2, qyhyInfoPunishBreak.getCasestate());
				prest.setString(3, qyhyInfoPunishBreak.getCourtname());
				prest.setString(4, qyhyInfoPunishBreak.getDisrupttypename());
				prest.setString(5, qyhyInfoPunishBreak.getPerformance());
				prest.setString(6, qyhyInfoPunishBreak.getPublishdateclean());
				String duty=qyhyInfoPunishBreak.getDuty();
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
				logger.info(" INSERT INTO TRD_QYHY_INFO_PUNISHBREAK (UUID,APP_ID,CASESTATE,COURTNAME,DISRUPTTYPENAME,PERFORMANCE,PUBLISHDATECLEAN,CRT_TIME,DUTY)VALUES(SYS_GUID(),?,?,?,?,?,?,SYSDATE,?),申请件编号:{},qyhyInfoPunishBreakList:{}", appId,qyhyInfoPunishBreakList);
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			throw new RuntimeException();
		} finally {
			prest.close();
		}
		
	//	qyhyInfoMapper.insertQyhyInfoPunishBreakList(qyhyInfoPunishBreakList);
	}

	@Override
	public void insertQyhyInfoOrgBasicList(List<QyhyInfoOrgBasic> qyhyInfoOrgBasicList) {
		qyhyInfoMapper.insertQyhyInfoOrgBasicList(qyhyInfoOrgBasicList);
	}

	@Override
	public void insertQyhyInfoOrgDetail(QyhyInfoOrgDetail qyhyInfoOrgDetail) {
		qyhyInfoMapper.insertQyhyInfoOrgDetail(qyhyInfoOrgDetail);
	}

	@Override
	public void insertQyhyInfoMetaData(QyhyInfoMetaData qyhyInfoMetaData) {
		qyhyInfoMapper.insertQyhyInfoMetaData(qyhyInfoMetaData);
	}


}