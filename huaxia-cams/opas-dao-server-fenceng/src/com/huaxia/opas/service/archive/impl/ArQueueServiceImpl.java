package com.huaxia.opas.service.archive.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.ApplyLifeCicleDao;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.archive.ArQueueDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.archive.ArBatch;
import com.huaxia.opas.domain.archive.ArDetail;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.archive.ArQueueService;
import com.huaxia.opas.util.StringUtil;

public class ArQueueServiceImpl extends AbstractService implements ArQueueService, Serializable {

	private static final long serialVersionUID = 5092661330809633413L;

	@Resource(name = "arQueueDao")
	private ArQueueDao arQueueDao;
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDao;
	@Resource(name = "applyLifeCicleDao")
	private ApplyLifeCicleDao applyLifeCicleDao;

	@Override
	public List<ArDetail> queryArQueueList(ArDetail arDetail, int curNum, int pageNum) throws Exception {
		List<ArDetail> list = getArQueueDao().queryArQueueList(arDetail, curNum, pageNum);
		for(ArDetail arDetail1 : list){
			String operator = arDetail1.getOperator();
			ApUser user = apUserDao.queryApUserByUserCode(operator);
			String name = user.getUserName();
			String userName = name + "-" + operator;
			arDetail1.setOperator(userName);
		}
		return list;
	}

	@Override
	public int queryArQueueCount(ArDetail arDetail) {
		return getArQueueDao().queryArQueueCount(arDetail);
	}

	public ArQueueDao getArQueueDao() {
		return arQueueDao;
	}

	public void setArQueueDao(ArQueueDao arQueueDao) {
		this.arQueueDao = arQueueDao;
	}

	@Override
	public int updateArQueueToDel(ArDetail arDetail) {
		return getArQueueDao().updateArQueueToDel(arDetail);
	}

	@Override
	public int updateFileNoForAr(ArDetail arDetail) throws Exception {
		// 更新归档批次号
		int result = getArQueueDao().updateFileNoForAr(arDetail);

		// 在生命流程表中记录归档的操作
		String userCode = arDetail.getOperator();
		String appId = arDetail.getAppId();
		ApplyLifeCicle a = new ApplyLifeCicle();
		a.setUuid(StringUtil.randomUUIDPlainString());
		a.setAppId(appId);
		a.setOperateDesc("手动申请归档");
		a.setCrtUser(userCode);
		a.setCrtDate(new Date());
		a.setOperateResult("完成");
		ApUser user = apUserDao.queryApUserByUserCode(userCode);
		String userName = user.getUserName();
		a.setOperater(userName + "-" + userCode);
		applyLifeCicleDao.addApplyLifeCicle(a);

		return result;
	}

	@Override
	public int insertArBatch(ArBatch arBatch) {
		return getArQueueDao().insertArBatch(arBatch);
	}

	@Override
	public int updAndInsAll(ArDetail arDetail) {
		return getArQueueDao().updAndInsAll(arDetail);
	}

	@Override
	public int updateAppFlag(ArDetail arDetail) {
		return getArQueueDao().updateAppFlag(arDetail);
	}

	// 查询批量归档的件
	@Override
	public List<ArDetail> queryArDetailList(ArDetail arDetail) {
		return getArQueueDao().queryArDetailList(arDetail);
	}

	// 查询套卡标志
	@Override
	public Map<String, Object> queryMatchingFlag(Map appIdMap) {
		return getArQueueDao().queryMatchingFlag(appIdMap);
	}

	// 套卡查询同步标志
	@Override
	public Map<String, Object> querysynFlag(Map appIdMap) {
		return getArQueueDao().querysynFlag(appIdMap);
	}

	// 修改同步标志
	@Override
	public void updatesynFlag(Map appIdMap) throws Exception {
		// 在生命流程表中记录归档的操作
		String userCode = (String) appIdMap.get("userId");
		String appId = (String) appIdMap.get("appId");
		ApplyLifeCicle a = new ApplyLifeCicle();
		a.setUuid(StringUtil.randomUUIDPlainString());
		a.setAppId(appId);
		a.setOperateDesc("手动申请归档");
		a.setCrtUser(userCode);
		a.setCrtDate(new Date());
		a.setOperateResult("完成");
		ApUser user = apUserDao.queryApUserByUserCode(userCode);
		String userName = user.getUserName();
		a.setOperater(userName + "-" + userCode);
		applyLifeCicleDao.addApplyLifeCicle(a);
				
		getArQueueDao().updatesynFlag(appIdMap);
	}

	@Override
	public int updateArchiveBatchNo(Map<String, String> params) throws Exception {
		// 在生命流程表中记录归档的操作
		String userCode =params.get("userId");
		String appId =params.get("appId");
		ApplyLifeCicle a = new ApplyLifeCicle();
		a.setUuid(StringUtil.randomUUIDPlainString());
		a.setAppId(appId);
		a.setOperateDesc("手动申请归档");
		a.setCrtUser(userCode);
		a.setCrtDate(new Date());
		a.setOperateResult("完成");
		ApUser user = apUserDao.queryApUserByUserCode(userCode);
		String userName = user.getUserName();
		a.setOperater(userName + "-" + userCode);
		applyLifeCicleDao.addApplyLifeCicle(a);
				
		return getArQueueDao().updateArchiveBatchNo(params);
	}

}
