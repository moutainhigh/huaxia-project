package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.TeamdealDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.decision.TeamdealList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.TeamdealService;
/**
 * 名单库管理_易达金团办名单service层实现类的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-19
 * 
 * @version 1.0
 */
public class TeamdealServiceImpl extends AbstractService implements TeamdealService,Serializable {
	
	private static final long serialVersionUID = -2985742108350852710L;
	
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	
	@Resource(name="teamdealDao")
	private TeamdealDao teamdealDao;
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	@Override
	public int  updateTeamdealActive(TeamdealList teamdealList) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = teamdealList.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		teamdealList.setOperator(userName);	
		
		List<String> ids = teamdealList.getIds();
		String currStatus = teamdealList.getCurrStatus();
		//分页最大页数是50
		List<TeamdealList> resutIdentitylist = teamdealDao.queryTeamdealList(teamdealList, 0, 50);
		ListIterator<TeamdealList> iterator = resutIdentitylist.listIterator();
		TeamdealList tmp =new TeamdealList();
		while (iterator.hasNext()) {
			tmp = iterator.next();
			if (currStatus.equals(tmp.getCurrStatus())) {
				ids.remove(tmp.getAutoId());
				iterator.remove();
			}else{
				tmp.setOpretionId(UUID.randomUUID().toString().replace("-", ""));
			}
		}
		if (null == resutIdentitylist || resutIdentitylist.size() == 0) {
			String flag = currStatus.equals("0")?"停用":"启用";
			throw new CoreException("所有选中数据已经"+flag+",无需修改!");
		}
			teamdealDao.insertTeamdealHistoryList(resutIdentitylist);
			return teamdealDao.updateTeamdealActive(teamdealList);	
	}

	@Override
	public Map<String, Object> queryTeamdealList(TeamdealList teamdealList, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<TeamdealList> list = new ArrayList<TeamdealList>();
		int count = teamdealDao.queryTeamdealCount(teamdealList);
		if(count > 0){
			list = teamdealDao.queryTeamdealList(teamdealList, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}

	@Override
	public Map<String, Object> queryTeamdealHistoryList(TeamdealList teamdealList, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<TeamdealList> list = new ArrayList<TeamdealList>();
		int count = teamdealDao.queryTeamdealHistoryCount(teamdealList);
		if(count > 0){
			list = teamdealDao.queryTeamdealHistoryList(teamdealList, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}

	@Override
	public int  insertTeamdealList(List<TeamdealList> list,BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts =  teamdealDao.insertTeamdealList(list);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}

	
	/**
	 * 新增易达金团办名单实例
	 * 
	 * @author luzhen.ou
	 * */
	@Override
	public int save(TeamdealList teamdealList) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = teamdealList.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		teamdealList.setOperator(userName);		
		return teamdealDao.insert1(teamdealList);
	}
	
	
	/**
	 * 删除易达金团办名单实例,通过主键集合ids
	 *  
	 * @author luzhen.ou
	 */	
	@Override
	public int remove(TeamdealList teamdealList) throws CoreException {
		return teamdealDao.delete(teamdealList);
	}

	
	/**
	 * 修改易达金团办名单实例
	 *  
	 * @author luzhen.ou
	 */
	@Override
	public int update(TeamdealList teamdealList) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = teamdealList.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		teamdealList.setOperator(userName);	
		
		TeamdealList returnTeamdealList = teamdealDao.selectById(teamdealList);
		System.out.println(returnTeamdealList);
		System.out.println(teamdealList);
		if (teamdealList.toString().equals(returnTeamdealList.toString())) {
			throw new CoreException("修改数据没有变化！");
		}
		returnTeamdealList.setOpretionId(teamdealList.getOpretionId());
		teamdealDao.insertTeamdealHistory(returnTeamdealList);
		return teamdealDao.update(teamdealList);
	}

	
	/**
	 * 查询易达金团办名单实例
	 * 
	 * @author luzhen.ou
	 * */
	@Override
	public TeamdealList get(TeamdealList teamdealList) {
		return teamdealDao.selectById(teamdealList);
	}
	
}
