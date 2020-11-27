package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.TeamDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.TeamName;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.TeamService;

public class TeamServiceImpl extends AbstractService implements TeamService, Serializable {

	private static final long serialVersionUID = 562759436257232687L;
	
	@Resource(name="teamDao")
	private TeamDao teamDao;
	
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	
	public BatchFileInfoDao getBatchFileInfoDao() {
		return batchFileInfoDao;
	}

	public void setBatchFileInfoDao(BatchFileInfoDao batchFileInfoDao) {
		this.batchFileInfoDao = batchFileInfoDao;
	}

	//日期校验的正则
	private static final String pattDate = "^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\\2(?:29))$";
	//正则校验的方法
	public static boolean forRegex(String field,String regex){
		Pattern pattern = Pattern.compile(regex) ;
		Matcher matcher = pattern.matcher(field);
		boolean flag = matcher.matches();
		return flag;
	}
	public static void validate(TeamName teamName) throws Exception{
		if(teamName.getApproveTime() != null&& !("".equals(new SimpleDateFormat("yyyy-MM-dd").format(teamName.getApproveTime()).trim()))){
			if(!TeamServiceImpl.forRegex(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(teamName.getApproveTime())),pattDate)){
				 throw new Exception("批复时间格式不正确");
			}
		}
		if(teamName.getApproveVailudDate() != null&& !("".equals(new SimpleDateFormat("yyyy-MM-dd").format(teamName.getApproveVailudDate()).trim()))){
			if(!TeamServiceImpl.forRegex(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(teamName.getApproveVailudDate())),pattDate)){
				 throw new Exception("批复有效期格式不正确");
			}
		}
	}
	@Override
	public int queryTeamListCount(Map<String, Object> map) {
		return getTeamDao().queryTeamListCount(map);
	}

	@Override
	public List<TeamName> queryTeamList(Map<String, Object> map, int curNum, int pageNum) {
		return getTeamDao().queryTeamList(map, curNum, pageNum);
	}

	@Override
	public void deleteTeamNameByAutoId(String autoId) {
		getTeamDao().deleteTeamNameByAutoId(autoId);
	}
	
	@Override
	public int save(TeamName t) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = t.getCrtUser();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		t.setCrtUser(userName);
		return getTeamDao().insertSelective(t);
	}
	
	@Override
	public int update(TeamName t) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = t.getCrtUser();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		t.setCrtUser(userName);
		
		TeamName teamName = teamDao.selectById(t);
		teamName.setOperationId(UUID.randomUUID().toString().replace("-", ""));
		int result = teamDao.update(t); 
		teamDao.insertTeamHis(teamName);
		return result;
	}
	//名单库停用
	@Override
	public void NoTeamName(TeamName record) throws Exception {		
		getTeamDao().updateStatusById(record);

	}
	//名单库启用
	@Override
	public void OkTeamName(TeamName record) throws Exception {
		getTeamDao().updateStatusById(record);
		
	}
	
	public TeamDao getTeamDao() {
		return teamDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}
	
	
	@Override
	public int remove(TeamName t) throws CoreException {
		return 0;
	}



	@Override
	public TeamName get(TeamName t) {
		return null;
	}

	@Override
	public int showTeamNameHisCount(String autoId) {
		return teamDao.showTeamNameHisCount(autoId);
	}

	@Override
	public List<TeamName> showTeamNameHisList(String autoId, int curNum, int pageNum) {
		return teamDao.showTeamNameHisList(autoId,curNum,pageNum);
	}

	@Override
	public int insertTeamNameFromFile(List<TeamName> list, BatchfileInfo batchfileInfo) throws Exception {
		int inputCounts =  teamDao.insertTeamNameFromFile(list);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}
}

