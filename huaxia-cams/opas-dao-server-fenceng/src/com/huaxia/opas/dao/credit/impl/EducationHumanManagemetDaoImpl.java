package com.huaxia.opas.dao.credit.impl;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.credit.EducationHumanManagemetDao;
import com.huaxia.opas.domain.credit.EducationManagemet;
import com.huaxia.opas.domain.credit.HumanQueryManagemet;
/********************************
 *@describe:学历查询匹配设置表  接口实现类
 *@author：xiaoJianFeng
 *@date:2017-03-20
 *********************************/
public class EducationHumanManagemetDaoImpl extends AbstractDAO  implements EducationHumanManagemetDao{
	private static final long serialVersionUID = 1L;
	private static final String NAMESPACES = "EducationManage.";
	private static final String NAMESPACES1 = "humanQueryManagemet.";
	
	@Override
	public List<EducationManagemet> selectOpasEducationSetPage(EducationManagemet education, int begNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectOpasEducationSetPage", education, begNum,pageNum);
	}
	@Override
	public void addOpasEducationSet(EducationManagemet education) {
		getSqlMap().insert(NAMESPACES + "addOpasEducationSet", education);
	}
	@Override
	public void updateOpasEducationSet(EducationManagemet education) {
		getSqlMap().update(NAMESPACES + "updateOpasEducationSet", education);
	}
	@Override
	public void deleteOpasEducationSet(String edu_autoId) {
		getSqlMap().delete(NAMESPACES + "deleteOpasEducationSet", edu_autoId);
	}
	@Override
	public String selectOpasEducationSetCount(EducationManagemet education) {
		return getSqlMap().queryForObject(NAMESPACES + "selectOpasEducationSetCount",education);
	}
	@Override
	public List<EducationManagemet> selectEducationLifeType() {
		return getSqlMap().queryForList(NAMESPACES+"selectEducationLifeType");
	}
	@Override
	public void saveEducationLifeType(EducationManagemet education) {
		 getSqlMap().insert(NAMESPACES+"saveEducationLifeType", education);
		
	}
	@Override
	public EducationManagemet selectOpasEdubackMaxcount() {
		return getSqlMap().queryForObject(NAMESPACES+"selectOpasEdubackMaxcount");
	}
	@Override
	public void deleteEducationLifeType() {
		getSqlMap().delete(NAMESPACES+"deleteEducationLifeType");
		
	}
	@Override
	public HumanQueryManagemet selectOpasApPeopleBank_maxCount() {
		return getSqlMap().queryForObject(NAMESPACES1+"selectOpasApPeopleBank_maxCount");
	}
	@Override
	public void deleteOpasApPeopleBank_maxCount() {
		getSqlMap().delete(NAMESPACES1+"deleteOpasApPeopleBank_maxCount");
	}
	@Override
	public void saveOpasApPeopleBank_maxCount(HumanQueryManagemet humanQueryManagemet) {
		getSqlMap().insert(NAMESPACES1+"saveOpasApPeopleBank_maxCount", humanQueryManagemet);
		
	}
	@Override
	public List<HumanQueryManagemet> selectHumanQueryLifeType() {
		return getSqlMap().queryForList(NAMESPACES1+"selectHumanQueryLifeType");
	}

}
