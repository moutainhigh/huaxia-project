package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.DAO;
import com.huaxia.opas.domain.sysparm.MBASchool;

public interface MBASchoolDao extends DAO<MBASchool> {
	
	int insert(MBASchool record);

	int insertSelective(MBASchool record);

	int queryMBASchoolListCount(Map<String, Object> map);

	List<MBASchool> queryMBASchoolList(Map<String, Object> map, int curNum, int pageNum);

	void deleteMBASchoolByAutoId(String autoId);

	void updateStatusById(MBASchool record);

	void insertMBASchoolHistory(MBASchool mBASchoolResult);

	/**
	 * 查询所有MBA院校名称名单
	 * 
	 * @return
	 */
	List<Map<String, String>> selectAllMBASchool();
	
	int queryMBASchoolHistoryCount(MBASchool school);

	List<MBASchool> queryMBASchoolHistoryList(MBASchool school, int curNum, int pageNum);

	void insertinsertMBASchoolHistory(MBASchool mschool);

	MBASchool queryMBASchoolByAutoId(String autoId);
	
	//批量导入名单
	int saveBatchMBASchool(List<MBASchool> mBASchoollist);

}