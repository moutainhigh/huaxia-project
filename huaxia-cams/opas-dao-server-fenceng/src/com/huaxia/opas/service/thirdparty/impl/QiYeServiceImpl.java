package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.huaxia.opas.dao.thirdparty.QiYeDao;
import com.huaxia.opas.domain.thirdparty.QiYeBasic;
import com.huaxia.opas.domain.thirdparty.QiYeOrgbasic;
import com.huaxia.opas.domain.thirdparty.QiYeOrgdetail;
import com.huaxia.opas.domain.thirdparty.QiYePerson;
import com.huaxia.opas.domain.thirdparty.QiYePunishbreak;
import com.huaxia.opas.domain.thirdparty.QiYeShareholder;
import com.huaxia.opas.service.thirdparty.QiYeService;

/**
 * 查询企业及行业信息的实现类
 * @author user
 *
 */
public class QiYeServiceImpl implements QiYeService,Serializable {
	
	private static final long serialVersionUID = 6353560302073554808L;

	@Resource(name = "qiYeDao")
	private QiYeDao qiYeDao;

	public QiYeDao getQiYeDao() {
		return qiYeDao;
	}
	public void setQiYeDao(QiYeDao qiYeDao) {
		this.qiYeDao = qiYeDao;
	}
	/**
	 * 查询结果展示
	 */
	@Override
	public Map<String, String> queryQiYecode(String appId) {
		return qiYeDao.queryQiYecode(appId);
	}
	
	/**
	 * 查询概要部分
	 */
	@Override
	public Map<String, String> queryQiYeInfoByAppId(String appId) {
		return qiYeDao.queryQiYeInfoByAppId(appId);	
	}
	
	/**
	 * 	查询详细部分
	 */
	@Override
	public Map<String, String> queryQiYeDatailsByAppId(String appId) {
		return qiYeDao.queryQiYeDatailsByAppId(appId);
	}
	/**
	 * 查询条数详细部分
	 */
	@Override
	public Map<String, String> queryQiYeNumber(String yearNumber) {
		return qiYeDao.queryQiYeNumber(yearNumber);
	}
	/**
	 * 每月1号自动更新上个月查询成功条数
	 */
	public void updateQiYeNumber(){
		//获取uuid
		String uuid = getUUID();
		//查询数据map
		Map<String,String> queryMap = new HashMap<String,String>();
		//修改数据map
		Map<String, String> updateMap = new HashMap<String,String>();
		String year = "";
		String month = "";
		//获取当前的数据库时间
		Map<String, String> yearMap = qiYeDao.queryTimeYear();
		String year1 = yearMap.get("year");
		Map<String, String> monyhMap = qiYeDao.queryTimeMonth();
		String month1 = monyhMap.get("month");		
		if(month1.equals("01")){
			month = "12";
			int year2 = Integer.parseInt(year1);
			year =  (year2 - 1) + "";			
		}else if(month1.equals("02")){
			month = "01";
			year = year1;			
		}else{
			int month2 = Integer.parseInt(month1);
			month = (month2 - 1) + "";
			year = year1;	
		}
		
		queryMap.put("year", year);
		queryMap.put("month", month);
		String selectTime = year + "-" + month;
		queryMap.put("selectTime", selectTime);
		//查询是否有本年度的数据条目
		Map<String,String> map = qiYeDao.queryQiYeNumberByyear(year1);
		//查询本月查询成功的条数		
		Map<String, String> queryQiYeNumberBymonth = qiYeDao.queryQiYeNumberBymonth(queryMap);
		String number = queryQiYeNumberBymonth.get("number");
		//组合添加月份数据map
		updateMap.put("uuid", uuid);
		updateMap.put("year", year);
		updateMap.put("month", month);
		updateMap.put("number", number);
		//判断map,执行数据插入的操作
		if(map != null){
			//执行更改操作,插入对应月份的数据			
			qiYeDao.updateQiYeNumber(updateMap);
		}else{
			//插入新的一年,和当年1月份的数据
			qiYeDao.insertYear(updateMap);
		} 
	}
	
	
	@Override
	public List<QiYeBasic> queryQiYeBasic(String appId) {
		return qiYeDao.queryQiYeBasic(appId);
	}
	@Override
	public List<QiYePerson> queryQiYePerson(String appId) {
		return qiYeDao.queryQiYePerson(appId);
	}
	@Override
	public List<QiYePunishbreak> queryQiYePunishbreak(String appId) {
		return qiYeDao.queryQiYePunishbreak(appId);
	}
	@Override
	public List<QiYeShareholder> queryQiYeShareholder(String appId) {
		return qiYeDao.queryQiYeShareholder(appId);
	}
	@Override
	public List<QiYeOrgbasic> queryQiYeOrgbasic(String appId) {
		return qiYeDao.queryQiYeOrgbasic(appId);
	}
	@Override
	public List<QiYeOrgdetail> queryQiYeQrgdetail(String appId) {
		return qiYeDao.queryQiYeQrgdetail(appId);
	}
	
	//生成uuid的方法
	public static String getUUID(){
		//生成UUID
		String uuid = UUID.randomUUID().toString();
		//去掉uuid的"-"
		return uuid.substring(0, 8) + uuid.substring(9, 13) + 
			   uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}
	
    //经营状态，企业网法人查询
	public Map<String, String> queryEetFrnameByAppId(String appId) {
		return qiYeDao.queryEetFrnameByAppId(appId);
	}
}
