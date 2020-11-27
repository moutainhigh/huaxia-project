package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.ConfQuestion;
import com.huaxia.opas.domain.sysparm.ConfRosterLibrary;

public interface ConfRosterLibraryService {

	//查询
	public int queryConfRosterLibraryCount(ConfRosterLibrary confRosterLibrary) throws CoreException;
	public List<ConfRosterLibrary> queryConfRosterLibraryList(ConfRosterLibrary confRosterLibrary, int curNum, int pageNum) throws CoreException;
	//添加
	public int insertConfRosterLibrary(ConfRosterLibrary confRosterLibrary) throws CoreException;
	//修改
	public int updateConfRosterLibrary(ConfRosterLibrary confRosterLibrary) throws CoreException;
	//删除
	public int deleteConfRosterLibrary(ConfRosterLibrary confRosterLibrary) throws CoreException;
	public int deleteAll() throws CoreException;
	//文件上传
	public int insertRosterLibraryFromFile(List<ConfRosterLibrary> list, BatchfileInfo batchfileInfo);
	//唯一校验
	public ConfRosterLibrary queryOnly(ConfRosterLibrary confRosterLibrary);
	
	

}
