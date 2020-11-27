package com.huaxia.opas.dao.sysparm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.ConfQuestion;

public interface ConfQuestionDao {

	//増
	public int insertConfQuestion(ConfQuestion confQuestion);
	//删
	public int deleteConfQuestion(ConfQuestion confQuestion);
	//改
	public int updateConfQuestion(ConfQuestion confQuestion);
	//查
	public List<ConfQuestion> queryConfQuestionList(ConfQuestion confQuestion, int curNum, int pageNum);
	
	public int queryConfQuestionCount(ConfQuestion confQuestion);

	//批量启用
	public int confQuestionsetStatusOK(ConfQuestion confQuestion);
	//批量禁用
	public int confQuestionsetStatusNO(ConfQuestion confQuestion);
	
	//上传的方法
	public int insertConfQuestion(List<ConfQuestion> list);

	
	//查询历史表
	public int queryConfQuestionHistoryCount(ConfQuestion confQuestion) throws CoreException;
	public List<ConfQuestion> queryConfQuestionHistoryList(ConfQuestion confQuestion, int curNum, int pageNum);
	//修改历史操作表
	public int insertupdateHistory(List<ConfQuestion> list) throws CoreException;
	//查询修改的部分
	public List<ConfQuestion> queryUpdateConfQuestion(ConfQuestion confQuestion, int i, int j);
	
	
}
