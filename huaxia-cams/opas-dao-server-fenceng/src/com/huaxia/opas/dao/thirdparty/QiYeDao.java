package com.huaxia.opas.dao.thirdparty;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.thirdparty.QiYeBasic;
import com.huaxia.opas.domain.thirdparty.QiYeOrgbasic;
import com.huaxia.opas.domain.thirdparty.QiYeOrgdetail;
import com.huaxia.opas.domain.thirdparty.QiYePerson;
import com.huaxia.opas.domain.thirdparty.QiYePunishbreak;
import com.huaxia.opas.domain.thirdparty.QiYeShareholder;
/**
 * 企业及行业信息查询用接口
 * @author user
 *
 */

public interface QiYeDao {
	//查询结果展示
	Map<String, String> queryQiYecode(String appId);
	//查询概要信息
	Map<String, String> queryQiYeInfoByAppId(String appId);
	//查询详细信息
	Map<String, String> queryQiYeDatailsByAppId(String appId);
	//查询条数详细信息
	Map<String, String> queryQiYeNumber(String yearNumber);
	//查询当前数据库系统时间年
	Map<String, String> queryTimeYear();
	//查询当前数据库系统时间月
	Map<String, String> queryTimeMonth();
	//查询有无当前年份数据
	Map<String, String> queryQiYeNumberByyear(String year);
	//查询当前月份的查询成功条数
	Map<String,String> queryQiYeNumberBymonth(Map<String,String> queryMap);
	//插入新的一年和当年1月份的数据
	int insertYear(Map<String,String> updateMap);
	//更新对应月份的数据
	int updateQiYeNumber(Map<String,String> updateMap);
	
	
	List<QiYeBasic> queryQiYeBasic(String appId);

	List<QiYePerson> queryQiYePerson(String appId);

	List<QiYePunishbreak> queryQiYePunishbreak(String appId);

	List<QiYeShareholder> queryQiYeShareholder(String appId);

	List<QiYeOrgbasic> queryQiYeOrgbasic(String appId);

	List<QiYeOrgdetail> queryQiYeQrgdetail(String appId);
	
	/**
	 * 经营状态，企业网法人查询
	 * @param appId
	 * @return
	 */
	Map<String,String>  queryEetFrnameByAppId(String appId);
}
