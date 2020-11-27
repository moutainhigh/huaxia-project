package com.huaxia.opas.service.allot.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotApplyDao;
import com.huaxia.opas.dao.allot.AllotQueueDao;
import com.huaxia.opas.domain.allot.AllotQueue;
import com.huaxia.opas.service.allot.AllotQueueService;

public class AllotQueueServiceImpl extends AbstractDAO implements AllotQueueService , Serializable{

	private static final long serialVersionUID = -5085772796149010429L;
	@Resource(name = "allotQueueDao")
	private AllotQueueDao allotQueueDao;
	
	public AllotQueueDao getAllotQueueDao() {
		return allotQueueDao;
	}
	public void setAllotQueueDao(AllotQueueDao allotQueueDao) {
		this.allotQueueDao = allotQueueDao;
	}
	
	@Resource(name = "allotApplyDao")
	private AllotApplyDao allotApplyDao;
	
	public AllotApplyDao getAllotApplyDao() {
		return allotApplyDao;
	}
	public void setAllotApplyDao(AllotApplyDao allotApplyDao) {
		this.allotApplyDao = allotApplyDao;
	}
	
	//增加队列
	@Override
	public int saveAllotQueue(AllotQueue allotQueue) throws CoreException {
		return getAllotQueueDao().insertAllotQueue(allotQueue);
	}
	//更改队列信息
	@Override
	public int updateAllotQueue(AllotQueue allotQueue) throws CoreException {
		return getAllotQueueDao().updateAllotQueue(allotQueue);
	}
	//删除队列
	@Override
	public int removeAllotQueueById(String queueId) throws CoreException {
		return getAllotQueueDao().deleteAllotQueueById(queueId);
	}
	//根据队列id  查询队列
	@Override
	public AllotQueue queryAllotQueueByCode(String queueCode) throws CoreException {
		return getAllotQueueDao().selectAllotQueueByCode(queueCode);
	}
	
	@Override
	public int queryCountAllotQueue(AllotQueue allotQueue) throws CoreException {
		 return getAllotQueueDao().selectCountAllotQueue(allotQueue);
	}
	//分页查询队列信息
	@Override
	public List<AllotQueue> queryAllAllotQueue(AllotQueue allotQueue, int currentPage, int pageSize) throws CoreException {
		List<AllotQueue> list = new ArrayList<AllotQueue>();
		list=getAllotQueueDao().selectAllAllotQueue(allotQueue, currentPage, pageSize);
		return list;
	}
	@Override
	public int countAllotQueue(AllotQueue allotQueue) throws CoreException {
		 return getAllotQueueDao().countAllotQueue(allotQueue);
	}
	//查询全部队列
	@Override
	public List<AllotQueue> queryAllotQueue2(Map<String,Object> map) throws CoreException {
		List<AllotQueue> list=new ArrayList<AllotQueue>();
		//新版本  不具体维护队列表  固定队列查询  
		List<String> queueCodeList=queryQueueCode();
		List<String> currStatusList=new ArrayList<String>();
		currStatusList.add("6");
		map.put("currStatusList", currStatusList);
		map.put("currNode", "02");
		map.put("ydjFlag", "2");
		map.put("submitStr", "submit");
		//未叫停申请件  质检叫停 2 不能分件 
    	String stopStr="AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
    	map.put("stopStr", stopStr);
		for(int i=0;i<queueCodeList.size();i++){
			AllotQueue allotQueue=new AllotQueue();
			String currQueue=queueCodeList.get(i);
			allotQueue.setQueueCode(currQueue);
			if("L".equals(currQueue)){
				allotQueue.setQueueName("低风险人工征信L");
			}else if("L1".equals(currQueue)){
				allotQueue.setQueueName("低风险征信L1");
			}else if("L2".equals(currQueue)){
				allotQueue.setQueueName("低风险征信L2");
			}else if("L3-1".equals(currQueue)){
				allotQueue.setQueueName("W低风险征信L3.1");
			}else if("L3-2".equals(currQueue)){
				allotQueue.setQueueName("W低风险免电核(人工排查)L3.2");
			}else if("LV".equals(currQueue)){
				allotQueue.setQueueName("低风险人工征信LV");
			}else if("M".equals(currQueue)){
				allotQueue.setQueueName("中风险人工征信M");
			}else if("H".equals(currQueue)){
				allotQueue.setQueueName("高风险人工征信H");
			}else if("H1".equals(currQueue)){
				allotQueue.setQueueName("高风险征信H1");
			}else if("S100".equals(currQueue)){
				allotQueue.setQueueName("系统征信自动通过");
			}else if("L1-1".equals(currQueue)){
				allotQueue.setQueueName("低风险征信L1.1");
			}else if("WL1-1".equals(currQueue)){
				allotQueue.setQueueName("W低风险征信L1.1");
			}else if("LR1-1".equals(currQueue)){
				allotQueue.setQueueName("低风险征信LR1.1");
			}else if("WLR1-1".equals(currQueue)){
				allotQueue.setQueueName("W低风险征信LR1.1 ");
			}else if("LR1-2".equals(currQueue)){
				allotQueue.setQueueName("低风险征信LR1.2");
			}else if("WLR1-2".equals(currQueue)){
				allotQueue.setQueueName("W低风险征信LR1.2");
			}else if("LR1-3".equals(currQueue)){
				allotQueue.setQueueName("低风险人工侧核LR1.3");
			}else if("WLR1-3".equals(currQueue)){
				allotQueue.setQueueName("W低风险人工侧核LR1.3");
			}else if("LR1-4".equals(currQueue)){
				allotQueue.setQueueName("低风险人工审核LR1.4");
			}else if("WLR1-4".equals(currQueue)){
				allotQueue.setQueueName("W低风险人工审核LR1.4");
			}else if("LR3".equals(currQueue)){
				allotQueue.setQueueName("低风险征信LR3");
			}else if("WLR3".equals(currQueue)){
				allotQueue.setQueueName("W低风险征信LR3");
			}else if("LR2-1".equals(currQueue)){
				allotQueue.setQueueName("低风险征信LR2.1");
			}else if("WLR2-1".equals(currQueue)){
				allotQueue.setQueueName("W低风险征信LR2.1");
			}else if("LR2-2".equals(currQueue)){
				allotQueue.setQueueName("低风险征信LR2.2");
			}else if("WLR2-2".equals(currQueue)){
				allotQueue.setQueueName("W低风险征信LR2.2");
			}else if("LR2-3".equals(currQueue)){
				allotQueue.setQueueName("低风险人工侧核LR2.3");
			}else if("WLR2-3".equals(currQueue)){
				allotQueue.setQueueName("W低风险人工侧核LR2.3 ");
			}else if("MR2-1".equals(currQueue)){
				allotQueue.setQueueName("中风险人工征信-关注正核MR2.1");
			}else if("WMR2-1".equals(currQueue)){
				allotQueue.setQueueName("W中风险人工征信-关注正核MR2.1");
			}else if("HR".equals(currQueue)){
				allotQueue.setQueueName("高风险人工征信HR");
			}else if("HR2".equals(currQueue)){
				allotQueue.setQueueName("高风险人工征信HR-关注正核 ");
			}else if("RGZX".equals(currQueue)){
				allotQueue.setQueueName("人工征信");
			}
			map.put("currQueue", currQueue);
	    	int num=allotApplyDao.selectCount(map);
			allotQueue.setQueueNum(num);
			list.add(allotQueue);
		}
		return list;
	}
	@Override
	public List<AllotQueue> queryAllotQueue(Map<String,Object> map) throws CoreException {
		String isHangUp=(String) map.get("isHangUp");
		List<String> currStatusList=new ArrayList<String>();
		currStatusList.add("6");
		if("1".equals(isHangUp)){//挂起件允许转移  不能分配和回收
			currStatusList.add("7");
		}
		map.put("currStatusList", currStatusList);
		map.put("currNode", "02");
		map.put("ydjFlag", "2");
		map.put("submitStr", "submit");
		//未叫停申请件  质检叫停 2 不能分件 
    	String stopStr="AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
    	map.put("stopStr", stopStr);
		List<AllotQueue> list=allotQueueDao.selectCreditDict();
		if(list==null){
			list=new ArrayList<AllotQueue>();
		}
		if(list.size()>0){
			for(int j=0;j<list.size();j++){
				AllotQueue allotQueue=list.get(j);
				allotQueue.setQueueCode(allotQueue.getQueueCode().replace(".", "-"));
				map.put("currQueue", allotQueue.getQueueCode());
				int num=allotApplyDao.selectCount(map);
				allotQueue.setQueueNum(num);
			}
		}
		return list;
	}
	
	//查询队列等级
	@Override
	public int queryQueueLevel(String queueCode) throws CoreException {
		return getAllotQueueDao().queryQueueLevel(queueCode);
	}
	
	//标准卡征信队列维护
	public List<String > queryQueueCode() throws CoreException {
	/*	List<String> list=new ArrayList<String>();
		list.add("L");
		list.add("L1");
		list.add("L2");
		list.add("L3-1");
		list.add("L3-2");
		list.add("LV");
		list.add("M");
		list.add("H");
		list.add("H1");
		list.add("S100");*/
		List<String> list=new ArrayList<String>(Arrays.asList("L","L1","L2","L3-1","L3-2","LV","M",
				"H","H1","S100","L1-1","WL1-1","LR1-1","WLR1-1","LR1-2","WLR1-2","LR1-3","WLR1-3","LR1-4","WLR1-4","LR3",
				"WLR3","LR2-1","WLR2-1","LR2-2","WLR2-2","LR2-3","WLR2-3","MR2-1","WMR2-1",
				"HR","HR2","RGZX"));
				
		return list;
	}
}
