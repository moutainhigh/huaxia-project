package com.huaxia.opas.action.credit;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.credit.EducationManagemet;
import com.huaxia.opas.domain.credit.HumanQueryManagemet;
import com.huaxia.opas.service.credit.EducationHumanManagemetService;
import com.huaxia.opas.util.SequenceUtil;
import com.ibm.icu.text.SimpleDateFormat;

/*********************************************************************
 * @describe:人行征信查询匹配设置、人行查询管理、学历查询匹配设置、学历查询管理 Action
 * @author xiaoJianFeng
 * @date:2017-03-20
 **********************************************/
public class EducationHumanManagemetAction implements Action {
	
	private static Logger logger = LoggerFactory
			.getLogger(CreditCheckAction.class);

	@Resource(name = "educationHumanManagemetService")
	private EducationHumanManagemetService educationHumanManagemetService;

	/*************************
	 * @describe:学历查询管理 分页查询
	 * @param context
	 * @throws CoreException
	 * @author xiaoJianFeng
	 * @date:2017-03-20
	 *************************/
	public void educationPage(Context context) throws CoreException {
		try {
			logger.info("start educationPage>>>>>>>>>>>>>");
			Gson gson = new Gson();
			EducationManagemet education = gson
					.fromJson(gson.toJson(context.getDataMap()),
							EducationManagemet.class);
			int curNum = 0;
			int curPage = Integer.parseInt(String
					.valueOf(context.getDataMap().get("page") == null ? 0
							: context.getDataMap().get("page")));
			int pageNum = Integer.parseInt(String
					.valueOf(context.getDataMap().get("rows") == null ? 0
							: context.getDataMap().get("rows")));
			logger.info("start educationPage>>>>>>>>>>>>curPage=" + curPage
					+ ",pageNum=" + pageNum);
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			logger.info("start educationPage>>>>>>>>>>>>curNum=" + curNum);
			Map<String, Object> map = educationHumanManagemetService
					.selectEducationManagemetPage(education, curNum, pageNum);
			logger.info("start educationPage>>>>>>>>>>>>map=" + map == null ? null
					: map.toString());
			context.setDataMap(map);
		} catch (Exception e) {
			logger.info("educationPage>>>失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}

	/*************************
	 * @describe:添加 学历查询管理
	 * @param context
	 * @throws CoreException
	 * @author xiaoJianFeng
	 * @date:2017-03-20
	 *************************/
	public void addEducation(Context context) throws CoreException {
		try {
			logger.info("start addEducation>>>>>>>>>>>>>");
			Gson gson = new Gson();
			EducationManagemet education = gson
					.fromJson(gson.toJson(context.getDataMap()),
							EducationManagemet.class);
			education.setEdu_autoId(SequenceUtil.getUUID());
			education.setEdu_crtTime(new Date());
			education.setEdu_crtUser(context.getData("userId").toString());
			educationHumanManagemetService.addEducationManagemet(education);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>addEducation");
			context.setData("success", false);
			e.printStackTrace();
		}
	}

	/*************************
	 * @describe:修改 学历查询管理
	 * @param context
	 * @throws CoreException
	 * @author xiaoJianFeng
	 * @date:2017-03-20
	 *************************/
	public void updateEducation(Context context) throws CoreException {
		try {
			logger.info("start updateCreditMatch>>>>>>>>>>>>>");
			Gson gson = new Gson();
			EducationManagemet education = gson
					.fromJson(gson.toJson(context.getDataMap()),
							EducationManagemet.class);
			educationHumanManagemetService.updateEducationManagemet(education);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}

	/*************************
	 * @describe:删除 学历查询管理
	 * @param context
	 * @throws CoreException
	 * @author xiaoJianFeng
	 * @date:2017-03-20
	 *************************/
	public void deleteEducation(Context context) throws CoreException {
		try {
			logger.info("start deleteCreditMatch>>>>>>>>>>>>>");
			String edu_autoId = context.getDataMap().get("edu_autoId") == null ? null
					: String.valueOf(context.getDataMap().get("edu_autoId"));
			educationHumanManagemetService.deleteEducationManagemet(edu_autoId);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}

	/*************************************
	 * @describe:学历年限(自动、手动)类型查询 记录数
	 * @author：xiaoJianFeng
	 * @param: education
	 * @date:2017-03-21
	 *************************************/
	public void selectEducationLifeType(Context context) throws CoreException {
		try {
			logger.info("start selectEducationLifeType>>>>>>>>>>>>>");
			List<EducationManagemet> educations = educationHumanManagemetService
					.selectEducationLifeType();
			EducationManagemet educationManagemet = educationHumanManagemetService
					.selectOpasEdubackMaxcount();
			EducationManagemet educationM = new EducationManagemet();
			if (educations != null) {
				Integer z_sumber = 0;
				for (int i = 0; i < educations.size(); i++) {
					EducationManagemet education = educations.get(i);
					String lifeSumber = education.getLifeQuery();
					if ("ZD".equals(education.getLifeQueryFlag())) {
						z_sumber = z_sumber + Integer.valueOf(lifeSumber);
						educationM.setZd_lifeQuery(lifeSumber);
					} else if ("SD".equals(education.getLifeQueryFlag())) {
						z_sumber = z_sumber + Integer.valueOf(lifeSumber);
						educationM.setSd_lifeQuery(lifeSumber);
					}
				}
				educationM.setLifeQuery(String.valueOf(z_sumber));
			}
			context.setData("success", true);
			context.setData("educationM", educationM);
			context.setData("educationManagemet", educationManagemet);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>selectEducationLifeType");
			context.setData("success", false);
			e.printStackTrace();
		}
	}

	/*************************************
	 * @describe:保存 学历年限(自动、手动)
	 * @author：xiaoJianFeng
	 * @param: education
	 * @date:2017-03-22
	 *************************************/
	public void saveEducationLifeType(Context context) throws CoreException {
		try {
			logger.info("start selectEducationLifeType>>>>>>>>>>>>>");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Gson gson = new Gson();
			Map<String, Object> dataMap = context.getDataMap();
			String json = gson.toJson(dataMap);
			EducationManagemet education = gson.fromJson(json,
					EducationManagemet.class);
			education.setEdu_autoId(SequenceUtil.getUUID());
			education.setSet_crtDate(sdf.format(new Date()));
			education.setSet_autoStartTime(education.getSet_autoStartTime()
					.toString());
			education.setSet_autoEndTime(education.getSet_autoEndTime()
					.toString());
			education.setSet_bathTime(education.getSet_bathTime().toString());
			education.setSet_manualEndTime(education.getSet_manualEndTime()
					.toString());
			education.setSet_manualStartTime(education.getSet_manualStartTime()
					.toString());
			education.setSet_crtUser(context.getData("userId").toString());
			educationHumanManagemetService.saveEducationLifeType(education);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>selectEducationLifeType");
			context.setData("success", false);
			e.printStackTrace();
		}
	}

	/*************************************
	 * @describe:查询 人行本年查询上限数设置表
	 * @author：xiaoJianFeng
	 * @date:2017-03-23
	 *************************************/
	public void selectOpasApPeopleBank_maxCount(Context context)
			throws CoreException {
		try {
			List<HumanQueryManagemet> humanQuerys = educationHumanManagemetService
					.selectHumanQueryLifeType();
			HumanQueryManagemet humanQuery = educationHumanManagemetService
					.selectOpasApPeopleBank_maxCount();
			HumanQueryManagemet humanM = new HumanQueryManagemet();
			if (humanQuerys != null) {
				Integer z_sumber = 0;
				for (int i = 0; i < humanQuerys.size(); i++) {
					HumanQueryManagemet human = humanQuerys.get(i);
					String lifeSumber = human.getLifeQuery();
					if ("ZD".equals(human.getLifeQueryFlag())) {
						z_sumber = z_sumber + Integer.valueOf(lifeSumber);
						humanM.setZd_lifeQuery(lifeSumber);
					} else if ("SD".equals(human.getLifeQueryFlag())) {
						z_sumber = z_sumber + Integer.valueOf(lifeSumber);
						humanM.setSd_lifeQuery(lifeSumber);
					}
				}
				humanM.setLifeQuery(String.valueOf(z_sumber));
			}
			context.setData("humanQuery", humanQuery);
			context.setData("humanM", humanM);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>selectOpasApPeopleBank_maxCount");
			context.setData("success", false);
			e.printStackTrace();
		}
	}

	/*************************************
	 * @describe:添加 人行本年查询上限数设置表
	 * @author：xiaoJianFeng
	 * @param: humanQueryManagemet
	 * @date:2017-03-23
	 *************************************/
	public void saveOpasApPeopleBank_maxCount(Context context)
			throws CoreException {
		try {
			logger.info("start saveOpasApPeopleBank_maxCount>>>>>>>>>>>>>");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Gson gson = new Gson();
			Map<String, Object> dataMap = context.getDataMap();
			String json = gson.toJson(dataMap);
			HumanQueryManagemet humanQuery = gson.fromJson(json,
					HumanQueryManagemet.class);
			humanQuery.setHu_autoId(SequenceUtil.getUUID());
			humanQuery.setHu_crtDate(sdf.format(new Date()));
			humanQuery.setHu_autoStartTime(humanQuery.getHu_autoStartTime()
					.toString());
			humanQuery.setHu_autoEndTime(humanQuery.getHu_autoEndTime()
					.toString());
			humanQuery.setHu_manualEndTime(humanQuery.getHu_manualEndTime()
					.toString());
			humanQuery.setHu_manualStartTime(humanQuery.getHu_manualStartTime()
					.toString());
			humanQuery.setHu_crtUser(context.getData("userId").toString());
			educationHumanManagemetService
					.saveOpasApPeopleBank_maxCount(humanQuery);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>saveOpasApPeopleBank_maxCount");
			context.setData("success", false);
			e.printStackTrace();
		}
	}

	public EducationHumanManagemetService getEducationHumanManagemetService() {
		return educationHumanManagemetService;
	}

	public void setEducationHumanManagemetService(
			EducationHumanManagemetService educationHumanManagemetService) {
		this.educationHumanManagemetService = educationHumanManagemetService;
	}

}
