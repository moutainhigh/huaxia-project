package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;


import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.MBASchoolDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.MBASchool;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.MBASchoolService;

public class MBASchoolServiceImpl extends AbstractService implements MBASchoolService, Serializable {

	private static final long serialVersionUID = 562759436257232687L;

	@Resource(name = "MBASchoolDao")
	private MBASchoolDao MBASchoolDao;

	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	public BatchFileInfoDao getBatchFileInfoDao() {
		return batchFileInfoDao;
	}

	public void setBatchFileInfoDao(BatchFileInfoDao batchFileInfoDao) {
		this.batchFileInfoDao = batchFileInfoDao;
	}
	@Override
	public int queryMBASchoolListCount(Map<String, Object> map) {
		return getMBASchoolDao().queryMBASchoolListCount(map);
	}

	@Override
	public List<MBASchool> queryMBASchoolList(Map<String, Object> map, int curNum, int pageNum) {
		return getMBASchoolDao().queryMBASchoolList(map, curNum, pageNum);
	}

	@Override
	public int save(MBASchool t) throws CoreException {
		return getMBASchoolDao().insertSelective(t);
	}

	// 名单库停用
	@Override
	public void NoMBASchool(MBASchool record) {
		String status = "0";
		getMBASchoolDao().updateStatusById(record);

	}

	// 名单库启用
	@Override
	public void OkMBASchool(MBASchool record) {
		String status = "1";
		getMBASchoolDao().updateStatusById(record);

	}

	@Override
	public int remove(MBASchool mbaSchool) throws CoreException {
		return getMBASchoolDao().delete(mbaSchool);
	}

	@Override
	public MBASchool get(MBASchool mbaSchool) {
		return getMBASchoolDao().selectById(mbaSchool);
	}

	@Override
	public int update(MBASchool mbaSchool) throws CoreException {
		return getMBASchoolDao().update(mbaSchool);
	}

	@Override
	public List<Map<String, String>> queryAllMBASchool() {
		return getMBASchoolDao().selectAllMBASchool();
	}

	public MBASchoolDao getMBASchoolDao() {
		return MBASchoolDao;
	}

	public void setMBASchoolDao(MBASchoolDao mBASchoolDao) {
		MBASchoolDao = mBASchoolDao;
	}

	@Override
	public void removeMBASchoolByAutoId(String autoId) {
		getMBASchoolDao().deleteMBASchoolByAutoId(autoId);
	}

	@Override
	public Map<String, Object> queryMBASchoolHistoryList(MBASchool school, int curPage, int pageNum) {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<MBASchool> list = new ArrayList<MBASchool>();
		int count = MBASchoolDao.queryMBASchoolHistoryCount(school);
		if(count > 0){
			list = MBASchoolDao.queryMBASchoolHistoryList(school, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}



	@Override
	public int updateMBASchool(MBASchool mbaSchool) throws CoreException {
		 MBASchool mschool = MBASchoolDao.queryMBASchoolByAutoId(mbaSchool.getAutoId());
		 mschool.setOperationId(UUID.randomUUID().toString().replace("-",""));
		 int result = getMBASchoolDao().update(mbaSchool);
		 MBASchoolDao.insertinsertMBASchoolHistory(mschool);
		 return result;
	}
	
	@Override
	public int updateMBASchoolAndHistory(MBASchool mbaSchool, MBASchool mbaSchoolHistory) throws CoreException {
		// 将数据库中的数据存到历史表
		getMBASchoolDao().insertMBASchoolHistory(mbaSchoolHistory);
		// 将页面数据存到数据库
		getMBASchoolDao().update(mbaSchool);
		return 0;
	}
	//批量导入名单
	@Override
	public int saveBatchMBASchool(List<MBASchool> MBASchoollist, BatchfileInfo batchfileInfo) {
		int inputCounts =  MBASchoolDao.saveBatchMBASchool(MBASchoollist);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}

}
