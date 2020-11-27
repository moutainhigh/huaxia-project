package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.VvipList;
import com.huaxia.opas.domain.sysparm.VvipListHistory;
import com.huaxia.opas.dao.sysparm.VvipListDao;

/**
 * VVIP名单信息参数DAO层实现类
 * @author liudi
 * @since 2017-03-27
 * @version 1.0
 */
public class VvipListDaoImpl extends AbstractDAO implements VvipListDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -572383631485047618L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "VvipList.";
	//添加状态为Start
	@Override
	public int saveVvipListStart(VvipList vvipList) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertVvipList1", vvipList);
	}
	//添加状态为End
	@Override
	public int saveVvipListEnd(VvipList vvipList) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertVvipList2", vvipList);
	}
	//根据主表主键查询所有项
	@Override
	public VvipList queryByPrimaryKey(String autoId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryByPrimaryKey", autoId);
	}
	//当前数据修改前添加至历史表
	@Override
	public int saveVvipListHistory(VvipListHistory vvipListHistory) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertVvipListHistory", vvipListHistory);
	}
	//修改
	@Override
	public int updateVvipList(VvipList vvipList) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateVvipList", vvipList);
	}
	//删除
	@Override
	public int deleteVvipList(VvipList vvipList) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteVvipListById", vvipList);
	}
	//VVIP件数查询
	@Override
	public int queryVvipListCount(VvipList vvipList) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryVvipListCount", vvipList);
	}
	//查询
	@Override
	public List<VvipList> queryVvipList(VvipList vvipList,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryVvipList1", vvipList, curNum, pageNum);
	}
	//点击查询后，触发查询和排序功能
	@Override
	public List<VvipList> queryVvipList(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryVvipList2", params, curNum, pageNum);
	}
	//查询证件号是否重复
	@Override
	public VvipList queryVvipList(VvipList vvipList) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryVvipList3", vvipList);
	}
	//VVIP历史修改记录件数查询
	@Override
	public int queryVvipListHistoryCount(String autoId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryVvipListHistoryCount", autoId);
	}
	//查询历史表
	@Override
	public List<VvipListHistory> queryHistoryUpdate(String autoId,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryVvipListHistory", autoId);
	}
	//批量导入VVIP名单实例Excel
	@Override
	public int saveVvipInfoList(List<VvipList> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "queryVvipInfoList", list);
	}
	
}
