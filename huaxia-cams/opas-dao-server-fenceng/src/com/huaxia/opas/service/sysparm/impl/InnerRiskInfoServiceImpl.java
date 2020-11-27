package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.InnerRiskInfoDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.InnerRiskInfo;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.InnerRiskInfoService;
/**
 * 名单库管理--内部风险信息名单service层实现类的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-10
 * 
 * @version 1.0
 */
public class InnerRiskInfoServiceImpl extends AbstractService implements InnerRiskInfoService,Serializable{

	private static final long serialVersionUID = 1858281900877294404L;
	
	@Resource(name="innerRiskInfoDao")
	private InnerRiskInfoDao innerRiskInfoDao;

	
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	
	@Override
	public int  updateInnerRiskInfoActive(InnerRiskInfo innerRiskInfo) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = innerRiskInfo.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		innerRiskInfo.setOperator(userName);
		
		List<String> ids = innerRiskInfo.getIds();
		String currStatus = innerRiskInfo.getCurrStatus();
		//分页最大页数是50
		List<InnerRiskInfo> resutIdentitylist = innerRiskInfoDao.queryInnerRiskInfoList(innerRiskInfo, 0, 50);
		ListIterator<InnerRiskInfo> iterator = resutIdentitylist.listIterator();
		InnerRiskInfo tmp =new InnerRiskInfo();
		while (iterator.hasNext()) {
			tmp = iterator.next();
			if (currStatus.equals(tmp.getCurrStatus())) {
				ids.remove(tmp.getAutoId());
				iterator.remove();
			}else{
				tmp.setOpretionId(UUID.randomUUID().toString().replace("-", ""));
			}
		}
		if (null == resutIdentitylist || resutIdentitylist.size() == 0) {
			String flag = currStatus.equals("0")?"禁用":"启用";
			throw new CoreException("所有选中数据已经"+flag+",无需修改!");
		}
		innerRiskInfoDao.insertInnerRiskInfoHistoryList(resutIdentitylist);
		return innerRiskInfoDao.updateInnerRiskInfoActive(innerRiskInfo);
	}

	@Override
	public int  deleteInnerRiskInfo(InnerRiskInfo innerRiskInfo) throws CoreException {
		return innerRiskInfoDao.deleteInnerRiskInfo(innerRiskInfo);
	}

	@Override
	public int  updateInnerRiskInfo(InnerRiskInfo innerRiskInfo) throws CoreException {
		InnerRiskInfo returnInnerRiskInfo = innerRiskInfoDao.queryInnerRiskInfo(innerRiskInfo.getAutoId());
		if (returnInnerRiskInfo.toString().equals(innerRiskInfo.toString())) {
			throw new CoreException("修改数据没有变化！");
		}
		//将操作用户设置成操作员的姓名
		String operator = innerRiskInfo.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		innerRiskInfo.setOperator(userName);
		returnInnerRiskInfo.setOpretionId(innerRiskInfo.getOpretionId());
		innerRiskInfoDao.insertInnerRiskInfoHistory(returnInnerRiskInfo);
		return innerRiskInfoDao.updateInnerRiskInfo(innerRiskInfo);
	}

	@Override
	public int  insertInnerRiskInfo(InnerRiskInfo innerRiskInfo) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = innerRiskInfo.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		innerRiskInfo.setOperator(userName);
		
		return innerRiskInfoDao.insertInnerRiskInfo(innerRiskInfo);
	}

	@Override
	public Map<String, Object> queryInnerRiskInfoList(InnerRiskInfo innerRiskInfo, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<InnerRiskInfo> list = new ArrayList<InnerRiskInfo>();
		int count = innerRiskInfoDao.queryInnerRiskInfoCount(innerRiskInfo);
		if(count > 0){
			list = innerRiskInfoDao.queryInnerRiskInfoList(innerRiskInfo, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}

	@Override
	public Map<String, Object> queryInnerRiskInfoHistoryList(InnerRiskInfo innerRiskInfo, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<InnerRiskInfo> list = new ArrayList<InnerRiskInfo>();
		int count = innerRiskInfoDao.queryInnerRiskInfoHistoryCount(innerRiskInfo);
		if(count > 0){
			list = innerRiskInfoDao.queryInnerRiskInfoHistoryList(innerRiskInfo, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}

	@Override
	public int  insertInnerRiskInfoList(List<InnerRiskInfo> list, BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts =  innerRiskInfoDao.insertInnerRiskInfoList(list);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}
	
}
