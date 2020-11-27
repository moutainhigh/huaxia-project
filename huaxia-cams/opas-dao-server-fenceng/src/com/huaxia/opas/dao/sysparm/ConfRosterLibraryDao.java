package com.huaxia.opas.dao.sysparm;

import java.util.List;

import com.huaxia.opas.domain.sysparm.ConfRosterLibrary;

public interface ConfRosterLibraryDao {

	//添加
	public int insertConfRosterLibrary(ConfRosterLibrary confRosterLibrary);
	//查询
	public int queryConfRosterLibraryCount(ConfRosterLibrary confRosterLibrary);
	public List<ConfRosterLibrary> queryConfRosterLibraryList(ConfRosterLibrary confRosterLibrary, int curNum, int pageNum);
	//修改
	public int updateConfRosterLibrary(ConfRosterLibrary confRosterLibrary);
	//删除
	public int deleteConfRosterLibrary(ConfRosterLibrary confRosterLibrary);
	public int deleteAll();
	//上传
	public int insertConfRosterLibrary(List<ConfRosterLibrary> rosterlist);
	//唯一校验
	public ConfRosterLibrary queryOnly(ConfRosterLibrary confRosterLibrary);
	
	

}
