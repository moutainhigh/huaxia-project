package com.huaxia.opas.dao.tripartite;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreBasic;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreData;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreMetaData;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreOrgBasic;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreOrgDetail;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStorePerson;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStorePunishBreak;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreShareHolder;

public interface QyhyStoreDao {
/**
 *@Title:selectRelateUuidByEnterprise
 *@Description:根据单位名称获取关联的uuid
 *@param enterprise
 *@return
 *@author: gaohui
 *@Date:2018年4月28日下午4:18:23
 */
String selectRelateUuidByEnterprise(String enterprise);
/**
 *@Title:deleteQyhyStoreDataByRelateUuid
 *@Description:根据relateUuid  删除1.企业信息库 企业行业信息 成功失败 参数存储表
 *@param relateUuid 关联的uuid
 *@author: gaohui
 *@Date:2018年5月2日上午8:51:53
 */
void deleteQyhyStoreDataByRelateUuid(String relateUuid);
/**
 *@Title:deleteQyhyStoreShareHolderByRelateUuid
 *@Description:根据relateUuid 删除2.企业信息库 SHAREHOLDER 股东及出资信息
 *@param relateUuid
 *@author: gaohui
 *@Date:2018年5月2日上午8:58:59
 */
void deleteQyhyStoreShareHolderByRelateUuid(String relateUuid);
/**
 *@Title:deleteQyhyStoreBasicByRelateUuid
 *@Description:根据relateUuid 删除3.企业信息库 BASIC 照面信息	
 *@param relateUuid
 *@author: gaohui
 *@Date:2018年5月2日上午9:03:19
 */
void deleteQyhyStoreBasicByRelateUuid(String relateUuid);
/**
 *@Title:deleteQyhyStorePersonByRelateUuid
 *@Description:根据relateUuid 删除4.企业信息库 PERSON 主要管理人员
 *@param relateUuid
 *@author: gaohui
 *@Date:2018年5月2日上午9:08:51
 */
void deleteQyhyStorePersonByRelateUuid(String relateUuid);
/**
 *@Title:deleteQyhyStorePunishBreakByRelateUuid
 *@Description:根据relateUuid 删除5.企业信息库 PUNISHBREAK 失信被执行人信息	
 *@param relateUuid
 *@author: gaohui
 *@Date:2018年5月2日上午9:15:53
 */
void deleteQyhyStorePunishBreakByRelateUuid(String relateUuid);
/**
 *@Title:deleteQyhyStoreOrgBasicByRelateUuid
 *@Description:根据relateUuid 删除6.企业信息库 ORGBASIC 组织机构列表
 *@param relateUuid
 *@author: gaohui
 *@Date:2018年5月2日上午9:19:00
 */
void deleteQyhyStoreOrgBasicByRelateUuid(String relateUuid);
/**
 *@Title:deleteQyhyStoreOrgDetailByRelateUuid
 *@Description:根据relateUuid 删除7.企业信息库 ORGDETAIL 组织机构详情
 *@param relateUuid
 *@author: gaohui
 *@Date:2018年5月2日上午9:27:00
 */
void deleteQyhyStoreOrgDetailByRelateUuid(String relateUuid);
/**
 *@Title:deleteQyhyStoreMetaDataByRelateUuid
 *@Description:根据relateUuid 删除8.企业信息库 METADATA 元数据来源	
 *@param relateUuid
 *@author: gaohui
 *@Date:2018年5月2日上午9:29:57
 */
void deleteQyhyStoreMetaDataByRelateUuid(String relateUuid);


/**
 *@Title:insertQyhyStoreData
 *@Description:插入 1.企业信息库 企业行业信息 成功失败 参数存储表
 *@param qyhyStoreData
 *@author: gaohui
 *@Date:2018年5月2日上午10:24:28
 */
void insertQyhyStoreData(QyhyStoreData qyhyStoreData);
/**
 *@Title:insertQyhyStoreShareHolderList
 *@Description:插入 2.企业信息库 SHAREHOLDER 股东及出资信息 
 *@param qyhyStoreShareHolderList
 *@author: gaohui
 *@Date:2018年5月2日上午10:38:04
 */
void insertQyhyStoreShareHolderList(List<QyhyStoreShareHolder> qyhyStoreShareHolderList);
/**
 *@Title:insertQyhyStoreBasic
 *@Description:插入 3.企业信息库 BASIC 照面信息
 *@param qyhyStoreBasic
 *@author: gaohui
 *@Date:2018年5月2日上午10:41:47
 */
void insertQyhyStoreBasic(QyhyStoreBasic qyhyStoreBasic);
/**
 *@Title:insertQyhyStorePersonList
 *@Description:插入4.企业信息库 PERSON 主要管理人员
 *@param qyhyStorePersonList
 *@author: gaohui
 *@Date:2018年5月2日上午10:47:05
 */
void insertQyhyStorePersonList(List<QyhyStorePerson> qyhyStorePersonList);
/**
 *@Title:insertQyhyStorePunishBreakList
 *@Description:插入5.企业信息库 PUNISHBREAK 失信被执行人信息
 *@param qyhyStorePunishBreakList
 *@author: gaohui
 *@Date:2018年5月2日上午10:52:04
 */
void insertQyhyStorePunishBreakList(List<QyhyStorePunishBreak> qyhyStorePunishBreakList) throws Exception;
/**
 *@Title:insertQyhyStoreOrgBasicList
 *@Description:插入6.企业信息库 ORGBASIC 组织机构列表
 *@param qyhyStoreOrgBasicList
 *@author: gaohui
 *@Date:2018年5月2日上午10:59:10
 */
void insertQyhyStoreOrgBasicList(List<QyhyStoreOrgBasic> qyhyStoreOrgBasicList);
/**
 *@Title:insertQyhyStoreOrgDetail
 *@Description:插入7.企业信息库 ORGDETAIL 组织机构详情
 *@param qyhyStoreOrgDetail
 *@author: gaohui
 *@Date:2018年5月2日上午11:02:13
 */
void insertQyhyStoreOrgDetail(QyhyStoreOrgDetail qyhyStoreOrgDetail);
/**
 *@Title:insertQyhyStoreMetaData
 *@Description:插入8.企业信息库 METADATA 元数据来源	
 *@param qyhyStoreMetaData
 *@author: gaohui
 *@Date:2018年5月2日上午11:07:07
 */
void insertQyhyStoreMetaData(QyhyStoreMetaData qyhyStoreMetaData);
/**
 *@Title:selectRelateUuidByEnterPriseDays
 *@Description:根据单位全称 天数 获取关联的uuid
 *@param params
 *@return
 *@author: gaohui
 *@Date:2018年5月3日上午9:14:51
 */
String selectRelateUuidByEnterPriseDays(Map<String, Object> params);
/**
 *@Title:saveCloneQyhyInfoData
 *@Description:从企业信息库 克隆数据到 企业行业信息表里
 *@param params
 *@return
 *@author: gaohui
 *@Date:2018年5月4日下午3:33:03
 */
Map<String,Object> saveCloneQyhyInfoData(Map<String,Object> params);

}