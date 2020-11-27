package com.huaxia.opas.service.sysparm;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.ConfQuestion;
import com.huaxia.opas.domain.sysparm.IdentityRisk;

public interface ConfQuestionService {

	//増
	public int insertConfQuestion(ConfQuestion confQuestion) throws CoreException;
	//删
	public int deleteConfQuestion(ConfQuestion confQuestion) throws CoreException;
	//改
	public int updateConfQuestion(ConfQuestion confQuestion) throws CoreException;
	//查
	public List<ConfQuestion> queryConfQuestionList(ConfQuestion confQuestion, int curNum, int pageNum);

	public int queryConfQuestionCount(ConfQuestion confQuestion);

	//批量启用
	public int confQuestionsetStatusOK(ConfQuestion confQuestion) throws CoreException;
	//批量禁用
	public int confQuestionsetStatusNO(ConfQuestion confQuestion) throws CoreException;
	//上传的方法
	public int insertConfQuestionFromFile(List<ConfQuestion> list,BatchfileInfo batchfileInfo);
	//查询历史记录的方法
	public Map<String, Object> queryCreditInfoHistoryList(ConfQuestion confQuestion, int curPage, int pageNum) throws CoreException;

}
