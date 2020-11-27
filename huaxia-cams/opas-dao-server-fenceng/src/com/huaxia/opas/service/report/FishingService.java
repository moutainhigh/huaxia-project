/**
 * 
 */
package com.huaxia.opas.service.report;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.report.Fishing;


/**
 * @author gaohui (捞件表)
 *
 */
public interface FishingService {
 /**
 *@Title:findListFishingByCrtDateOrApplyChannle
 *@Description:根据起始时间或渠道分页获取捞件表数据
 *@param paramsMap Map参数
 *@return
 *@author: gaohui
 *@Date:2017年3月12日下午5:43:07
  */
 public Map<String, Object> findListFishingByCrtDateOrApplyChannle(Map<String, Object> paramsMap,int pageNum, int pageRows);
 /**
  * 捞件表插数据
  * @param addFish
  * @throws CoreException
  */
 public void insertFishing(Fishing addFish) throws CoreException;
 /**
  *@Title:queryListOutsourceByCrtDate
  *@Description:根据起始时间分页获取外包报表数据
  *@param paramsMap Map参数
  *@return
  *@author: andong
  *@Date:2017年10月18日下午2:45:40
   */
 public Map<String, Object> queryListOutsourceByCrtDate(Map<String, Object> paraMap, int pageNum, int pageRows);
 /**
  *@Title:queryReturnNumInfo
  *@Description:根据起始时间和userCode获取当前人的退件量
  *@param paramsMap Map参数
  *@return
  *@author: andong
  *@Date:2017年10月23日下午4:13:10
   */
 public Map<String, Object> queryReturnNumInfo(Map<String, Object> map);
}
