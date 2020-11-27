package com.huaxia.opas.service.sysparm;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.MBASchool;
import com.huaxia.opas.service.BaseService;

public interface MBASchoolService extends BaseService<MBASchool>{

	public int queryMBASchoolListCount(Map<String, Object> map);

	public List<MBASchool> queryMBASchoolList(Map<String, Object> map, int curNum, int pageNum);

	public void removeMBASchoolByAutoId(String autoId);

	public void NoMBASchool(MBASchool record);

	public void OkMBASchool(MBASchool record);

	public Map<String, Object> queryMBASchoolHistoryList(MBASchool school, int curPage, int pageNum);
	
	/**
	 * 查询所有MBA院校名称名单
	 * 
	 * @return
	 */
	List<Map<String, String>> queryAllMBASchool();

	/**
	 * 批量上传
	 * @param file
	 * @param string
	 * @return
	 */
	public int saveBatchMBASchool(List<MBASchool> MBASchoollist, BatchfileInfo batchfileInfo);

	public int updateMBASchool(MBASchool mbaSchool) throws CoreException ;

	/**
	 * 更新MBA院校名单并记录历史操作信息
	 * @param mbaSchool
	 * @param mbaSchoolHistory
	 * @return
	 * @throws CoreException
	 */
	public int updateMBASchoolAndHistory(MBASchool mbaSchool, MBASchool mbaSchoolHistory) throws CoreException;

}
