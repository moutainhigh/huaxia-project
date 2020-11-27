package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.VvipList;
import com.huaxia.opas.domain.sysparm.VvipListHistory;
/**
 * VVIP名单信息参数DAO层接口
 * @author liudi
 * @since 2017-03-27
 * @version 1.0
 */
public interface VvipListDao {
	//添加状态为Start
	public int saveVvipListStart(VvipList vvipList) throws CoreException;
	//添加状态为End
	public int saveVvipListEnd(VvipList vvipList) throws CoreException;
	//根据主表主键查询所有项
	public VvipList queryByPrimaryKey(String autoId) throws CoreException;
	//当前数据修改前添加至历史表
	public int saveVvipListHistory(VvipListHistory vvipListHistory) throws CoreException;
	//修改
	public int updateVvipList(VvipList vvipList) throws CoreException;
	//删除
	public int deleteVvipList(VvipList vvipList) throws CoreException;
	//VVIP件数查询
	public int queryVvipListCount(VvipList vvipList) throws CoreException;
	//查询
	public List<VvipList> queryVvipList(VvipList vvipList,int curNum,int pageNum) throws CoreException;
	//点击查询后，触发查询和排序功能
	public List<VvipList> queryVvipList(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//查询证件号是否重复
	public VvipList queryVvipList(VvipList vvipList) throws CoreException;
	//VVIP历史修改记录件数查询
	public int queryVvipListHistoryCount(String autoId) throws CoreException;
	//查询历史表
	public List<VvipListHistory> queryHistoryUpdate(String autoId,int curNum,int pageNum) throws CoreException;
	//批量导入VVIP名单实例Excel
	public int saveVvipInfoList(List<VvipList> list) throws CoreException;
}
