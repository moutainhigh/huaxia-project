package com.huaxia.opas.service.credit.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.slf4j.Logger;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.credit.CreditWhiteListDao;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.credit.CreditWhiteListService;

/**
 * 征信白名单复核业务实现类
 * 
 * @author susha 2017/03/20
 *
 */
public class CreditWhiteListServiceImpl extends AbstractService implements CreditWhiteListService,Serializable {

	@Resource(name = "creditWhiteListDao")
	private CreditWhiteListDao creditWhiteListDao;
	public CreditWhiteListDao getCreditWhiteListDao() {
		return creditWhiteListDao;
	}

	public void setCreditWhiteListDao(CreditWhiteListDao creditWhiteListDao) {
		this.creditWhiteListDao = creditWhiteListDao;
	}
	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDao;
	
	public BizInpApplDao getBizInpApplDao() {
		return bizInpApplDao;
	}

	public void setBizInpApplDao(BizInpApplDao bizInpApplDao) {
		this.bizInpApplDao = bizInpApplDao;
	}

	private static final long serialVersionUID = 4217310994903470291L;

	// 单条保存
	public int save(CreditWhiteList t) throws CoreException {
		// 复核通过保存至表内
		creditWhiteListDao.save(t);
		// 更新中间表状态
		creditWhiteListDao.update(t);
		return 1;
	}

	public int remove(CreditWhiteList t) throws CoreException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(CreditWhiteList t) throws CoreException {
		// TODO Auto-generated method stub
		return 0;
	}

	public CreditWhiteList get(CreditWhiteList t) {
		// TODO Auto-generated method stub
		return null;
	}

	// 列表条数查询
	public int queryCount(CreditWhiteList creditWhiteList) throws CoreException {
		return creditWhiteListDao.queryCount(creditWhiteList);
	}

	// 列表查询
//	public List<CreditWhiteList> queryList(CreditWhiteList creditWhiteList, int curNum, int pageNum)
//			throws CoreException {
//
//		return creditWhiteListDao.queryList(creditWhiteList, curNum, pageNum);
//	}
	public List<Map<Object, Object>> queryList(CreditWhiteList creditWhiteList, int curNum, int pageNum)
			throws CoreException {

		return creditWhiteListDao.queryList(creditWhiteList, curNum, pageNum);
	}

	// 单条或多条删除
	public void deleteList(List list) throws CoreException {
		creditWhiteListDao.deleteList(list);
	}

	// 批量保存
	public void insertList(List list) throws CoreException {
		creditWhiteListDao.insertList(list);
		creditWhiteListDao.updateList(list);
	}

	// 征信工作量查询
	public Map<String, Object> queryWorkload(String startTime, String endTime,String crediter) throws CoreException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date start = null;
		Date end = null;

		try {
			start = sdf.parse(startTime);
			end = sdf.parse(endTime);
		} catch (ParseException e) {
			throw new CoreException("日期格式转换异常", e);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("crediter", crediter);
		map.put("start", start);
		map.put("end", end);
		// 调用dao方法
		int count1 = creditWhiteListDao.queryFinishedCount(map);
		int count2 = creditWhiteListDao.queryUnfinishedCount(map);
		int count3 = creditWhiteListDao.querySub2othersCount(map);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total1", count1 + "");
		dataMap.put("total2", count2 + "");
		dataMap.put("total3", count3 + "");
		return dataMap;
	}

	// 拒绝添加
	public int refuse(CreditWhiteList creditWhiteList) throws CoreException {
		return creditWhiteListDao.refuse(creditWhiteList);
	}

	@Override
	public void saveOrUpdateCreditWhiteCenter(CreditWhiteList creditWhiteList) {
		creditWhiteListDao.insertCreditWhiteCenter(creditWhiteList);
	}

	@Override
	public int queryCreditWhiteCountInfo(CreditWhiteList creditWhiteList) {
		return creditWhiteListDao.queryCreditWhiteCountInfo(creditWhiteList);
	}

	@Override
	public List<CreditWhiteList> queryCreditWhiteListInfo(CreditWhiteList creditWhiteList, int curNum, int pageNum) {
		List<CreditWhiteList>	list= creditWhiteListDao.queryCreditWhiteListInfo(creditWhiteList, curNum, pageNum);
		Map<String,Object> userMap=new HashMap<String,Object>();
		int listSize=list.size();
		// 操作员系统登录号  变为中文       operator   
		for (int i = 0; i <listSize; i++) {
			CreditWhiteList creditWhite=list.get(i);
			if(creditWhite!=null){
				String operator=creditWhite.getOperator();
				if(operator!=null&&!"".equals(operator)){
					userMap.put("userCode",operator);
					Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
					if(apUserMap!=null){
					String userName=apUserMap.get("userName").toString();
					list.get(i).setOperator(userName);
					}
				}
			}	
		}
		return list;
	}

}
