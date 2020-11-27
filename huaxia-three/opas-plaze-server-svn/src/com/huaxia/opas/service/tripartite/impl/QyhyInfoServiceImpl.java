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

import com.huaxia.opas.common.AppConst;
import com.huaxia.opas.dao.tripartite.QyhyInfoDao;
import com.huaxia.opas.domain.tripartite.QyhyInfo;
import com.huaxia.opas.domain.tripartite.QyhyInfoBasic;
import com.huaxia.opas.domain.tripartite.QyhyInfoData;
import com.huaxia.opas.domain.tripartite.QyhyInfoMetaData;
import com.huaxia.opas.domain.tripartite.QyhyInfoOrgBasic;
import com.huaxia.opas.domain.tripartite.QyhyInfoOrgDetail;
import com.huaxia.opas.domain.tripartite.QyhyInfoPerson;
import com.huaxia.opas.domain.tripartite.QyhyInfoPunishBreak;
import com.huaxia.opas.domain.tripartite.QyhyInfoShareHolder;
import com.huaxia.opas.service.tripartite.QyhyInfoService;
import com.huaxia.opas.util.TaskStatusUtil;


@Service("qyhyInfoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class QyhyInfoServiceImpl  implements QyhyInfoService {
	private final static Logger logger = LoggerFactory.getLogger(QyhyInfoServiceImpl.class);
	@Autowired
	private QyhyInfoDao qyhyInfoDao;
	
//	@Autowired
//	private TaskCallStatusDao taskCallStatusDao;
	
	public QyhyInfoDao getQyhyInfoDao() {
		return qyhyInfoDao;
	}

	public void setQyhyInfoDao(QyhyInfoDao qyhyInfoDao) {
		this.qyhyInfoDao = qyhyInfoDao;
	}

	@Override
	public int getCountByAppId(String appId) {
		return qyhyInfoDao.selectCountByAppId(appId);
	}

	@Override
	public Map<String, String> saveQyhyInfoUpdateDataAdapterAction(QyhyInfo qyhyInfo, String appId, String qyhyTaskType) throws Exception {
		
		 String status=TaskStatusUtil.SUCCESS;//修改状态
		 String failureAddNum=null;//失败新增次数
		 String errorCode=null;
		//===========1.企业行业信息 成功失败 参数存储表
		QyhyInfoData qyhyInfoData=qyhyInfo.getQyhyInfoData();
		Integer code= qyhyInfoData.getCode();
		if(code==200||code==404||code==444||code==445||code==901){
		qyhyInfoDao.insertQyhyInfoData(qyhyInfoData);
		//===========2.SHAREHOLDER 股东及出资信息 	
		List<QyhyInfoShareHolder> qyhyInfoShareHolderList=qyhyInfo.getQyhyInfoShareHolderList();
		if(qyhyInfoShareHolderList!=null&&qyhyInfoShareHolderList.size()>0){
			qyhyInfoDao.insertQyhyInfoShareHolderList(qyhyInfoShareHolderList);
		}
		//===========3.BASIC 照面信息
		QyhyInfoBasic qyhyInfoBasic=qyhyInfo.getQyhyInfoBasic();
		if(qyhyInfoBasic!=null){
			qyhyInfoDao.insertQyhyInfoBasic(qyhyInfoBasic);
		}
		//===========4.PERSON 主要管理人员
		List<QyhyInfoPerson> qyhyInfoPersonList=qyhyInfo.getQyhyInfoPersonList();
		if(qyhyInfoPersonList!=null&&qyhyInfoPersonList.size()>0){
			qyhyInfoDao.insertQyhyInfoPersonList(qyhyInfoPersonList);
		}
		//===========5.PUNISHBREAK 失信被执行人信息	
		List<QyhyInfoPunishBreak> qyhyInfoPunishBreakList=qyhyInfo.getQyhyInfoPunishBreakList();
		if(qyhyInfoPunishBreakList!=null&&qyhyInfoPunishBreakList.size()>0){
			qyhyInfoDao.insertQyhyInfoPunishBreakList(qyhyInfoPunishBreakList);
		}
		//===========6.ORGBASIC 组织机构列表	
		List<QyhyInfoOrgBasic> qyhyInfoOrgBasicList=qyhyInfo.getQyhyInfoOrgBasicList();
		if(qyhyInfoOrgBasicList!=null&&qyhyInfoOrgBasicList.size()>0){
			qyhyInfoDao.insertQyhyInfoOrgBasicList(qyhyInfoOrgBasicList);
		}
		//===========7.ORGDETAIL 组织机构详情
		 QyhyInfoOrgDetail qyhyInfoOrgDetail=qyhyInfo.getQyhyInfoOrgDetail();
		 if(qyhyInfoOrgDetail!=null){
			 qyhyInfoDao.insertQyhyInfoOrgDetail(qyhyInfoOrgDetail);
		 }
		//===========8.METADATA 元数据来源	
		 QyhyInfoMetaData qyhyInfoMetaData =qyhyInfo.getQyhyInfoMetaData();
		 if(qyhyInfoMetaData!=null){
			 qyhyInfoDao.insertQyhyInfoMetaData(qyhyInfoMetaData);
		 }
	     
		 }else{
			status=TaskStatusUtil.RESPOSE_CODE_ERROR;//报文响应码异常
			errorCode=code.toString();
			failureAddNum="1";
		 }
		// 更新申请件任务状态
		Map<String, String> paramsUpdate = new HashMap<String, String>();
	    paramsUpdate.put("appId", appId);
	    paramsUpdate.put("status", status);
	    paramsUpdate.put("taskType", qyhyTaskType);
	    paramsUpdate.put("errorCode", errorCode);
	    paramsUpdate.put("lstOptUser", TaskStatusUtil.CURR_USER);
	    paramsUpdate.put("requestAddNum", null);
	    paramsUpdate.put("failureAddNum", failureAddNum);
	    return paramsUpdate ;
//		getTaskCallStatusDao().updateSingleTask(paramsUpdate);
	}

	
}