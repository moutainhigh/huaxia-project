package com.huaxia.opas.service.credit;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.credit.EducationManagemet;
import com.huaxia.opas.domain.credit.HumanQueryManagemet;
/******************************
 *@describe:人行征信查询匹配设置、人行查询管理、学历查询匹配设置、学历查询管理 Service
 *@author xiaoJianFeng
 *@date:2015-03-20
 *****************************/
public interface EducationHumanManagemetService {
	/********************************
	 *@describe:学历查询管理 分页查询
	 *@param EducationManagemet
	 *@param begNum
	 *@param pageNum
	 *@author：xiaoJianFeng
	 *@date:2017-03-20
	 *@return
	 *********************************/
	Map<String, Object> selectEducationManagemetPage(EducationManagemet education, int begNum, int pageNum);
	/*************************************
	 *@describe:添加 学历查询管理
	 *@param EducationManagemet
	 *@author：xiaoJianFeng
	 *@date:2017-03-20
	 *************************************/
	void addEducationManagemet(EducationManagemet education);
	/*************************************
	 *@describe:修改 学历查询管理
	 *@param EducationManagemet
	 *@author：xiaoJianFeng
	 *@date:2017-03-20
	 *************************************/	
	void updateEducationManagemet(EducationManagemet education);
	/*************************************
	 *@describe:删除 学历查询管理
	 *@param edu_autoId
	 *@author：xiaoJianFeng
	 *@date:2017-03-20
	 *************************************/	
	void deleteEducationManagemet(String edu_autoId);
	/*************************************
	 *@describe:学历年限(自动、手动)类型查询  记录数
	 *@author：xiaoJianFeng
	 *@param: education
	 *@date:2017-03-21
	 *************************************/	
	List<EducationManagemet> selectEducationLifeType();
	/*************************************
	 *@describe:保存 学历(自动、手动)年限 时间
	 *@author：xiaoJianFeng
	 *@param: education
	 *@date:2017-03-21
	 *************************************/
	void saveEducationLifeType(EducationManagemet education);
	/*************************************
	 *@describe:查询 学历本年查询上限数设置表
	 *@author：xiaoJianFeng
	 *@param: education
	 *@date:2017-03-21
	 *************************************/
	EducationManagemet selectOpasEdubackMaxcount();
	/*************************************
	 *@describe:查询 人行本年查询上限数设置表
	 *@author：xiaoJianFeng
	 *@date:2017-03-23
	 *************************************/
	HumanQueryManagemet selectOpasApPeopleBank_maxCount();
	/*************************************
	 *@describe:添加 人行本年查询上限数设置表
	 *@author：xiaoJianFeng
	 *@param: humanQueryManagemet
	 *@date:2017-03-23
	 *************************************/
	void saveOpasApPeopleBank_maxCount(HumanQueryManagemet humanQueryManagemet);
	/*************************************
	 *@describe:人行年限类型查询 
	 *@author：xiaoJianFeng
	 *@param: HumanQueryManagemet
	 *@date:2017-03-23
	 *************************************/
	List<HumanQueryManagemet> selectHumanQueryLifeType();
}
