package com.huaxia.opas.service.credit.impl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.credit.EducationHumanManagemetDao;
import com.huaxia.opas.domain.credit.EducationManagemet;
import com.huaxia.opas.domain.credit.HumanQueryManagemet;
import com.huaxia.opas.service.credit.EducationHumanManagemetService;
/******************************
 *@describe:人行征信查询匹配设置、人行查询管理、学历查询匹配设置、学历查询管理 Service实现类
 *@author xiaoJianFeng
 *@date:2015-03-20
 *****************************/
public class EducationHumanManagemetServiceImpl implements EducationHumanManagemetService, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -866155753374153222L;
	@Resource(name="educationHumanManagemetDao")
	private EducationHumanManagemetDao educationHumanManagemetDaoImpl;
	
	public EducationHumanManagemetDao getEducationHumanManagemetDaoImpl() {
		return educationHumanManagemetDaoImpl;
	}
	public void setEducationHumanManagemetDaoImpl(EducationHumanManagemetDao educationHumanManagemetDaoImpl) {
		this.educationHumanManagemetDaoImpl = educationHumanManagemetDaoImpl;
	}
	@Override
	public Map<String, Object> selectEducationManagemetPage(EducationManagemet education, int begNum, int pageNum) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String total = educationHumanManagemetDaoImpl.selectOpasEducationSetCount(education);
		List<EducationManagemet> rows = educationHumanManagemetDaoImpl.selectOpasEducationSetPage(education, begNum, pageNum);
		dataMap.put("total", total);
		dataMap.put("rows", rows);
		return dataMap;	
	}
	@Override
	public void addEducationManagemet(EducationManagemet education) {
		educationHumanManagemetDaoImpl.addOpasEducationSet(education);
	}
	@Override
	public void updateEducationManagemet(EducationManagemet education) {
		educationHumanManagemetDaoImpl.updateOpasEducationSet(education);
	}
	@Override
	public void deleteEducationManagemet(String edu_autoId) {
		educationHumanManagemetDaoImpl.deleteOpasEducationSet(edu_autoId);
	}
 
	@Override
	public List<EducationManagemet> selectEducationLifeType() {
		return educationHumanManagemetDaoImpl.selectEducationLifeType();
	}
	@Override
	public void saveEducationLifeType(EducationManagemet education) {
		educationHumanManagemetDaoImpl.deleteEducationLifeType();
		educationHumanManagemetDaoImpl.saveEducationLifeType(education);
	}
	@Override
	public EducationManagemet selectOpasEdubackMaxcount(){
		return educationHumanManagemetDaoImpl.selectOpasEdubackMaxcount();
	}
	@Override
	public HumanQueryManagemet selectOpasApPeopleBank_maxCount() {
		return educationHumanManagemetDaoImpl.selectOpasApPeopleBank_maxCount();
	}
	@Override
	public void saveOpasApPeopleBank_maxCount(HumanQueryManagemet humanQueryManagemet) {
		educationHumanManagemetDaoImpl.deleteOpasApPeopleBank_maxCount();
		educationHumanManagemetDaoImpl.saveOpasApPeopleBank_maxCount(humanQueryManagemet);
	}
	@Override
	public List<HumanQueryManagemet> selectHumanQueryLifeType() {
		return educationHumanManagemetDaoImpl.selectHumanQueryLifeType();
	}
	
	
	
	
}
