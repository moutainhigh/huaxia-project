package com.huaxia.opas.service.thirdparty;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.thirdparty.QiYeBasic;
import com.huaxia.opas.domain.thirdparty.QiYeOrgbasic;
import com.huaxia.opas.domain.thirdparty.QiYeOrgdetail;
import com.huaxia.opas.domain.thirdparty.QiYePerson;
import com.huaxia.opas.domain.thirdparty.QiYePunishbreak;
import com.huaxia.opas.domain.thirdparty.QiYeShareholder;

/**
 * 第三方企业及行业信息接口
 * 
 * @author lipengfei
 *
 */

public interface QiYeService {

	/**
	 * 查询企业及行业信息的查询结果
	 * @param appId
	 * @return
	 */
	Map<String, String> queryQiYecode(String appId);
	
	/**
	 * 根据appId查询企业及行业信息概要部分
	 * @param appId
	 * @return
	 */
	Map<String, String> queryQiYeInfoByAppId(String appId);

	/**
	 * 企业及行业信息详情部分
	 * @param appId
	 * @return
	 */
	Map<String, String> queryQiYeDatailsByAppId(String appId);
	/**
	 * 企业及行业信息,查询条数详情
	 * @param yearNumber
	 * @return
	 */
	Map<String, String> queryQiYeNumber(String yearNumber);
	
	/**
	 * 备用的查询方法,需求更改以后没有删除
	 * @param appId
	 * @return
	 */
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
