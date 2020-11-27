package com.huaxia.opas.service.tripartite;

import java.util.Map;

import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStore;

/**
 * 企业信息库
 * 
 * @author gaoh
 *
 */
public interface QyhyStoreService {
/**
 *@Title:saveQyhyStoreUpdateDataAdapterAction
 *@Description:插入 删除 企业信息库 信息
 *@param qyhyStore
 *@param enterprise
 *@author: gaohui
 *@Date:2018年4月28日下午3:59:46
 */
void saveQyhyStoreUpdateDataAdapterAction(QyhyStore qyhyStore,String enterprise) throws Exception;	
/**
 *@Title:findRelateUuidByEnterPriseDays
 *@Description:根据单位全称 天数 获取关联的uuid
 *@param enterprise
 *@param qyhyStoreQueryDays
 *@return
 *@author: gaohui
 *@Date:2018年5月2日下午4:58:38
 */
String findRelateUuidByEnterPriseDays(String enterprise,Integer qyhyStoreQueryDays);
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