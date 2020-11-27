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
import com.huaxia.opas.dao.sysparm.IdentityRiskDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.IdentityRisk;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.IdentityRiskService;

/**
 * 系统参数--身份类风险名单service层实现类的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-10
 * 
 * @version 1.0
 */
public class IdentityRiskServiceImpl extends AbstractService implements IdentityRiskService,Serializable{
	
	private static final long serialVersionUID = -5046096597233559561L;
	
	@Resource(name="identityRiskDao")
	private IdentityRiskDao identityRiskDao;
	
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;

	@Override
	public int updateIdentityRiskActive(IdentityRisk identityRisk) throws CoreException {
		List<String> ids = identityRisk.getIds();
		String currStatus = identityRisk.getCurrStatus();
		//分页最大页数是50
		List<IdentityRisk> resutIdentitylist = identityRiskDao.queryIdentityRiskList(identityRisk, 0, 50);
		ListIterator<IdentityRisk> iterator = resutIdentitylist.listIterator();
		IdentityRisk tmp =new IdentityRisk();
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
		identityRiskDao.insertIdentityRiskHistoryList(resutIdentitylist);
		return identityRiskDao.updateIdentityRiskActive(identityRisk);
	}

	@Override
	public Map<String, Object> queryIdentityRiskList(IdentityRisk identityRisk, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<IdentityRisk> list = new ArrayList<IdentityRisk>();
		int count = identityRiskDao.queryIdentityRiskCount(identityRisk);
		if(count > 0){
			list = identityRiskDao.queryIdentityRiskList(identityRisk, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}

	@Override
	public Map<String, Object> queryIdentityRiskHistoryList(IdentityRisk identityRisk, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<IdentityRisk> list = new ArrayList<IdentityRisk>();
		int count = identityRiskDao.queryIdentityRiskHistoryCount(identityRisk);
		if(count > 0){
			list = identityRiskDao.queryIdentityRiskHistoryList(identityRisk, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}

	@Override
	public int insertIdentityRiskList(List<IdentityRisk> list ,BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts =  identityRiskDao.insertIdentityRiskList(list);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}
	
	/**
	 * 新增身份类风险名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param IdentityRisk
	 * 
	 * @return Int
	 * */
	@Override
	public int save(IdentityRisk identityRisk) throws CoreException {
		return identityRiskDao.insertIdentityRisk(identityRisk);
	}
	
	/**
	 * 删除身份类风险名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param identityRisk
	 * 
	 * @return int
	 * */
	@Override
	public int remove(IdentityRisk identityRisk) throws CoreException {
		return identityRiskDao.deleteIdentityRisk(identityRisk);
	}
	
	/**
	 * 修改身份类风险名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param IdentityRisk
	 * 
	 * @return Int
	 * */
	@Override
	public int update(IdentityRisk identityRisk) throws CoreException {
		IdentityRisk returnIdentityRisk = identityRiskDao.queryIdentityRisk(identityRisk.getAutoId());
		//判断修改操作，有没数据修改，利旧toString的方法，createtime和operatTime不用比较
		if (returnIdentityRisk.toString().equals(identityRisk.toString())) {
			throw new CoreException("修改数据没有变化！");
		}
		returnIdentityRisk.setOpretionId(identityRisk.getOpretionId());
		identityRiskDao.insertIdentityRiskHistory(returnIdentityRisk);
		return identityRiskDao.updateIdentityRisk(identityRisk);
	}

	/**
	 * 查询身份类风险名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param String autoId
	 * 
	 * @return IdentityRisk
	 * @throws CoreException 
	 * */
	public IdentityRisk get(IdentityRisk identityRisk){
		return identityRiskDao.queryIdentityRisk(identityRisk.getAutoId());
	}
	

	
}
