package com.huaxia.opas.mapper.tripartite;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface TaskCallInfoMapper {

	/**
	 *@Title:selectAllTaskForWorkflow
	 *@Description:查询可以调工作流的申请件
	 *@param param
	 *@return
	 *@author: gaohui
	 *@Date:2017年12月25日下午3:52:57
	 */
	List<Map<String,String>> selectAllTaskForWorkflow(Map<String, Object> param);
	/**
	 *@Title:insertSuccessInfoByAppId
	 *@Description:根据appId插入 成功表数据
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2017年12月25日下午5:01:57
	 */
	int insertSuccessInfoByAppId(String appId);
	/**
	 *@Title:deleteTaskCallInfo
	 *@Description:删除任务信息表数据
	 *@param param
	 *@return
	 *@author: gaohui
	 *@Date:2017年12月26日上午8:58:01
	 */
	int deleteTaskCallInfo(Map<String,Object> param);
	/**
	 *@Title:updateTaskInfoStatus
	 *@Description:修改 任务信息
	 *@param param
	 *@author: gaohui
	 *@Date:2017年12月27日下午3:17:40
	 */
	void updateTaskInfoStatus(Map<String,Object> param);
	/**
	 *@Title:selectFormularyNumTasksByType
	 *@Description:根据类型查询规定数量的任务
	 *@param params
	 *@return
	 *@author: gaohui
	 *@Date:2018年1月5日下午2:33:52
	 */
	List<Map<String,String>> selectFormularyNumTasksByType(Map<String,Object> params);
	/**
	 *@Title:updateBatchStatus
	 *@Description:根据appId的list集合批量修改 任务状态
	 *@param params
	 *@author: gaohui
	 *@Date:2018年1月5日下午4:22:00
	 */
	void  updateBatchStatus(Map<String,Object> params);
	/**
	 *@Title:updateSingleTaskStatus
	 *@Description:修改任务信息表的任务状态
	 *@param params
	 *@author: gaohui
	 *@Date:2018年1月11日下午1:38:18
	 */
	void updateSingleTaskStatus(Map<String,Object> params);
	/**
	 *@Title:insertBatch
	 *@Description:批量插入任务信息表信息
	 *@param taskCallInfoList
	 *@author: gaohui
	 *@Date:2018年1月15日上午10:40:39
	 */
	void insertBatch(Map<String,Object> params);

	/**
	 *@Title:updateSingleTask
	 *@Description:修改任务信息表的任务
	 *@param params
	 *@return
	 *@author: gaohui
	 *@Date:2018年9月15日下午1:47:01
	 */
	int updateSingleTask(Map<String, Object> params);
	/**
	 *@Title:insertTaskCallInfoHis
	 *@Description:插入历史表
	 *@param params
	 *@author: gaohui
	 *@Date:2018年9月15日上午11:15:08
	 */
	void insertTaskCallInfoHis(Map<String, Object> params);
	/**
	 *@Title:insertTaskCallInfoHisBatch
	 *@Description:批量插入任务历史表根据任务状态和最后修改时间的时间间隔数
	 *@param params
	 *@author: gaohui
	 *@Date:2018年10月18日上午11:11:57
	 */
	void insertTaskCallInfoHisBatch(Map<String, Object> params);
	/**
	 *@Title:updateTaskCallInfoBatch
	 *@Description:批量修改任务表根据任务状态和最后修改时间的时间间隔数和请求次数
	 *@param params
	 *@author: gaohui
	 *@Date:2018年10月18日上午11:11:57
	 */
	void updateTaskCallInfoBatch(Map<String, Object> params);
	/**
	 *@Title:updateTaskCallInfoInteract
	 *@Description:与外界交互时进行 任务表的 更改 
	 *@param sourceId 任务表的 id
	 *@param status   任务状态
	 *@param taskType 任务类型
	 *@param errorCode 错误码信息
	 *@param lstOptUser 最后操作人
	 *@param requestAddNum 请求次数的增加数字
	 *@param failureAddNum 失败次数的增加数字
	 *@return
	 *@author: gaohui
	 *@Date:2019年3月1日下午2:29:05
	 */
	int updateTaskCallInfoInteract(@Param("sourceId")String sourceId,@Param("status")String status,
			@Param("taskType")String taskType,@Param("errorCode")String errorCode,
			@Param("lstOptUser")String lstOptUser,@Param("requestAddNum")String requestAddNum,
			@Param("failureAddNum")String failureAddNum);
	/**
	 *@Title:insertTaskCallInfoInteractHis
	 *@Description:插入任务历史表 根据任务表id
	 *@param sourceId   根据任务表id(交互id)
	 *@param taskType   任务类型
	 *@return
	 *@author: gaohui
	 *@Date:2019年3月2日上午10:46:29
	 */
	int insertTaskCallInfoInteractHis(@Param("sourceId")String sourceId,@Param("taskType")String taskType);
	/**
	 *@Title:selectAppIdTaskInfoInteract
	 *@Description:根据任务表id(交互id) 获取appId
	 *@param sourceId 任务表id(交互id)
	 *@param taskType 任务类型
	 *@param taskStatus 任务状态
	 *@return
	 *@author: gaohui
	 *@Date:2018年12月17日上午11:24:12
	 */
	 String selectAppIdTaskInfoInteract(@Param("sourceId")String sourceId,@Param("taskType")String taskType,
			 @Param("taskStatus")String taskStatus);
	 /**
	  *@Title:updateTaskStatusNotTypeHisBatch
	  *@Description:批量修改任务表 不含某些任务类型 ， 根据任务状态和最后修改时间的时间间隔数和请求次数
	  *@param taskStatus 任务状态
	  *@param requestAddNum  请求次数的增加数字
	  *@param hourNum 最后修改时间的时间间隔数
	  *@param requestNum 请求次数
	  *@param updateStatus 修改成的状态
	  *@param lstOptUser 最后操作人
	  *@param taskTypeMap 任务类型集合Map对象
	  *@author: gaohui
	  *@Date:2019年3月7日下午4:25:55
	  */
	 int updateTaskStatusNotTypeHisBatch(@Param("taskStatus")String taskStatus,@Param("requestAddNum")Integer requestAddNum,
			 @Param("hourNum")Integer hourNum, @Param("requestNum")Integer requestNum,
			 @Param("updateStatus")String updateStatus, @Param("lstOptUser")String lstOptUser,
			 @Param("taskTypeMap")Map<String, String> taskTypeMap); 
}