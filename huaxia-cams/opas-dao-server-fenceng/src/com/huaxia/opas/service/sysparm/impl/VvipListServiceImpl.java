package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.VvipList;
import com.huaxia.opas.domain.sysparm.VvipListHistory;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.InnerRiskInfoDao;
import com.huaxia.opas.dao.sysparm.VvipListDao;
import com.huaxia.opas.service.sysparm.VvipListService;
/**
 * VVIP名单信息参数服务层实现类
 * @author liudi
 * @since 2017-03-27
 * @version 1.0
 */
public class VvipListServiceImpl extends AbstractDAO implements VvipListService,Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7336687209772680655L;

	@Resource(name = "vvipListDao")
	private VvipListDao vvipListDao;
	
	@Resource(name="innerRiskInfoDao")
	private InnerRiskInfoDao innerRiskInfoDao;

	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;

	public VvipListDao getVvipListDao() {
		return vvipListDao;
	}

	public void setVvipListDao(VvipListDao vvipListDao) {
		this.vvipListDao = vvipListDao;
	}

	//添加状态为Start
	public int saveVvipListStart(VvipList vvipList) throws CoreException {
		return getVvipListDao().saveVvipListStart(vvipList);
	}
	
	//添加状态为End
	public int saveVvipListEnd(VvipList vvipList) throws CoreException {
		return getVvipListDao().saveVvipListEnd(vvipList);
	}
	
	//根据主表主键查询所有项
	public VvipList queryByPrimaryKey(String autoId) throws CoreException {
		return getVvipListDao().queryByPrimaryKey(autoId);
	}
	
	//当前数据修改前添加至历史表
	public int saveVvipListHistory(VvipListHistory vvipListHistory) throws CoreException {
		return getVvipListDao().saveVvipListHistory(vvipListHistory);
	}
	
	//修改
	public int updateVvipList(VvipList vvipList) throws CoreException {
		return getVvipListDao().updateVvipList(vvipList);
	}
	
	//删除
	public int deleteVvipList(VvipList vvipList) throws CoreException {
		return getVvipListDao().deleteVvipList(vvipList);
	}
	
	//VVIP件数查询
	public int queryVvipListCount(VvipList vvipList) throws CoreException {
		return getVvipListDao().queryVvipListCount(vvipList);
	}
	
	//查询
	public List<VvipList> queryVvipList(VvipList vvipList,int curNum,int pageNum) throws CoreException {
		return getVvipListDao().queryVvipList(vvipList, curNum, pageNum);
	}
	
	//点击查询后，触发查询和排序功能
	public List<VvipList> queryVvipList(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getVvipListDao().queryVvipList(params, curNum, pageNum);
	}
	
	//查询证件号是否重复
	public VvipList queryVvipList(VvipList vvipList) throws CoreException {
		return getVvipListDao().queryVvipList(vvipList);
	}
	
	//VVIP历史修改记录件数查询
	public int queryVvipListHistoryCount(String autoId) throws CoreException {
		return getVvipListDao().queryVvipListHistoryCount(autoId);
	}
	
	//查询历史表
	public List<VvipListHistory> queryHistoryUpdate(String autoId,int curNum,int pageNum) throws CoreException {
		return getVvipListDao().queryHistoryUpdate(autoId, curNum, pageNum);
	}
	
	//批量导入VVIP名单实例Excel
	public int  saveVvipInfoList(List<VvipList> list, BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts =  vvipListDao.saveVvipInfoList(list);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}

}
