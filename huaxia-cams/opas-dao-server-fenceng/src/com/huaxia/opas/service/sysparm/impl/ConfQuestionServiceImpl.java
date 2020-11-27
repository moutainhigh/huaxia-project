package com.huaxia.opas.service.sysparm.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.huaxia.opas.common.Constant;
import com.huaxia.opas.common.InconfQuestion;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.ConfQuestionDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.ConfQuestion;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
import com.huaxia.opas.domain.sysparm.Income;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.ConfQuestionService;

public class ConfQuestionServiceImpl extends AbstractService implements ConfQuestionService,Serializable{

	private static final long serialVersionUID = 1977026634751939247L;

	//调用Dao
	@Resource(name="confQuestionDao")
	private ConfQuestionDao confQuestionDao;
	//插入的历史记录
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	
	
	public ConfQuestionDao getConfQuestionDao() {
		return confQuestionDao;
	}

	public void setConfQuestionDao(ConfQuestionDao confQuestionDao) {
		this.confQuestionDao = confQuestionDao;
	}

	//添加
	@Override
	public int insertConfQuestion(ConfQuestion confQuestion) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = confQuestion.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		confQuestion.setCrtUser(userName);
		confQuestion.setLstUpdUser(userName);
		return getConfQuestionDao().insertConfQuestion(confQuestion);
	}

	//修改
	@Override
	public int updateConfQuestion(ConfQuestion confQuestion) throws CoreException {
		//查询修改的数据
		List<ConfQuestion> list = confQuestionDao.queryUpdateConfQuestion(confQuestion, 0, 50);
		//将查询出的数据,添加历史记录的ID
		for (ConfQuestion c1 : list) {
			//给历史数据添加UUID	
			c1.setHistoryId(UUID.randomUUID().toString().replace("-", ""));
		}
		//将修改前的数据保存到历史表中
		confQuestionDao.insertupdateHistory(list);
		//将操作用户设置成操作员的姓名
		String operator = confQuestion.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		confQuestion.setLstUpdUser(userName);
		return getConfQuestionDao().updateConfQuestion(confQuestion);
	}

	//查询
	@Override
	public List<ConfQuestion> queryConfQuestionList(ConfQuestion confQuestion, int curNum, int pageNum) {
		return getConfQuestionDao().queryConfQuestionList(confQuestion, curNum, pageNum);
	}

	@Override
	public int queryConfQuestionCount(ConfQuestion confQuestion) {
		return getConfQuestionDao().queryConfQuestionCount(confQuestion);
	}

	//删除
	@Override
	public int deleteConfQuestion(ConfQuestion confQuestion) throws CoreException {
		return getConfQuestionDao().deleteConfQuestion(confQuestion);
	}

	@Override
	public int confQuestionsetStatusOK(ConfQuestion confQuestion) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = confQuestion.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		confQuestion.setLstUpdUser(userName);
		
		//查询记录
		List<ConfQuestion> list = confQuestionDao.queryUpdateConfQuestion(confQuestion, 0, 50);
		//添加历史记录
		for (ConfQuestion c1 : list) {
			c1.setHistoryId(UUID.randomUUID().toString().replace("-", ""));
		}
		confQuestionDao.insertupdateHistory(list);
		return getConfQuestionDao().confQuestionsetStatusOK(confQuestion);
	}

	@Override
	public int confQuestionsetStatusNO(ConfQuestion confQuestion) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = confQuestion.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		confQuestion.setLstUpdUser(userName);
		
		//查询记录
		List<ConfQuestion> list = confQuestionDao.queryUpdateConfQuestion(confQuestion, 0, 50);
		//添加历史记录
		for (ConfQuestion c1 : list) {
			c1.setHistoryId(UUID.randomUUID().toString().replace("-", ""));
		}
		confQuestionDao.insertupdateHistory(list);
		return getConfQuestionDao().confQuestionsetStatusNO(confQuestion);
	}

	@Override
	public int insertConfQuestionFromFile(List<ConfQuestion> list, BatchfileInfo batchfileInfo) {
		int inputCounts = confQuestionDao.insertConfQuestion(list);
		//添加导入记录
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}

	//查询历史操作的方法
	@Override
	public Map<String, Object> queryCreditInfoHistoryList(ConfQuestion confQuestion, int curPage, int pageNum) throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<ConfQuestion> list = new ArrayList<ConfQuestion>();
		int count = confQuestionDao.queryConfQuestionHistoryCount(confQuestion);
		if(count>0){
			list = confQuestionDao.queryConfQuestionHistoryList(confQuestion, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}
}
