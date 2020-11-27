package com.huaxia.opas.service.sysparm.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.Constant;
import com.huaxia.opas.dao.dict.ApDictDetailDao;
import com.huaxia.opas.dao.sysparm.YdjIncomeDao;
import com.huaxia.opas.domain.sysparm.Income;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.YdjIncomeService;

public class YdjIncomeServiceImpl extends AbstractService implements YdjIncomeService,Serializable {

	private static final long serialVersionUID = -5065580577681811254L;

	@Resource(name = "ydjIncomeDao")
	private  YdjIncomeDao ydjIncomeDao;
	
	//完成数据字典的验证
	@Resource(name = "apDictDetailDao")
	private  ApDictDetailDao apDictDetailDao;



	/**
	 * @return the ydjIncomeDao
	 */
	public YdjIncomeDao getYdjIncomeDao() {
		return ydjIncomeDao;
	}

	/**
	 * @param ydjIncomeDao the ydjIncomeDao to set
	 */
	public void setYdjIncomeDao(YdjIncomeDao ydjIncomeDao) {
		this.ydjIncomeDao = ydjIncomeDao;
	}

	@Override
	public int queryIncomeCount(Income income) {
		return getYdjIncomeDao().qureyIncomeCount(income);
	}

	@Override
	public List<Income> queryIncomeList(Income income, int curNum, int pageNum) {
		return getYdjIncomeDao().queryIncome(income, curNum, pageNum);
	}

	@Override
	public int insertIncome(Income income) throws CoreException {
		return getYdjIncomeDao().insertIncome(income);
	}

	@Override
	public int updateIncome(Income income) throws CoreException {
		return getYdjIncomeDao().updateIncome(income);
	}

	@Override
	public int deleteIncome(Income income) throws CoreException {
		return getYdjIncomeDao().deleteIncome(income);
	}

	
	@Override
	public void insertIncomeFromFile(File file, String operator) {
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			List<Income> list = readExcel(in,operator);
			//将导入的集合保存
			ydjIncomeDao.insertIncomeList(list);
		} catch (FileNotFoundException e) {
		}
	}

	public  List<Income> readExcel(InputStream in,String operator) {
		//验证城市代码Map
//		Map<String,String> cityMap=apDictDetailDao.queryCityMap();
		//验证单位性质Map
//		Map<String,String> unionMap=apDictDetailDao.queryUnionMap();
		//验证教育程度Map
//		Map<String,String> educationMap=apDictDetailDao.queryEducationMap();		
		//验证行业代码Map
//		Map<String,String> industryMap=apDictDetailDao.queryIndustryMap();		
//		//验证职业代码Map
//		Map<String,String> jobMap=apDictDetailDao.queryJobMap();		
		
		List<Income> incomeList = new ArrayList<Income>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Workbook workbook = WorkbookFactory.create(in);  
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
			Date date = new Date();
			// 遍历每一行 第一行为标题行
			for (int r = 1; r < rowCount; r++) {
				Income income = new Income();
				Row row = sheet.getRow(r);
				int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
				// 遍历每一列
				for (int c = 0; c < cellCount; c++) {
					Cell cell = row.getCell(c);//获得列值   
					if(cell != null){
						switch(c){
							case Constant.CITYNAME_INDEX:  //城市代码
//								String cityCode=cityMap.get(cell.toString()); //将获取的列值与业务字典的存储值比较
								String cityCode = cell.toString();
								income.setCityCode(cityCode);
								break;
							case Constant.UNIONTYPE_INDEX:  //单位性质
//								String unionType=unionMap.get(cell.toString());
								String unionType = cell.toString();
								income.setUnionType(unionType);
								break;
							case Constant.EDUCATION_INDEX:  //教育程度
//								String education=educationMap.get(cell.toString());
								String education = cell.toString();
								income.setEducation(education);
								break;
							case Constant.INDUSTRYCODE_INDEX:  //行业代码
//								String industryCode=industryMap.get(cell.toString());
								String industryCode = cell.toString();
								income.setIndustryCode(industryCode);
								break;
							case Constant.JOBCODE_INDEX:	//职业代码
								String jobCode=cell.toString();
								income.setJobCode(jobCode);
								break;
							case Constant.MATCHINCOME_INDEX:  //行职业匹配收入
								income.setMatchIncome(cell.toString());
								break;
						}
					}
					
					//生成对应的ID
					UUID uuid = UUID.randomUUID();
					income.setUuId(uuid.toString().replace("-", ""));
					income.setCrtDate(new Date());
					income.setOperator(operator);
				}
				incomeList.add(income);
			}
		} catch (Exception e) {
		}
		return incomeList;
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	@Override
	public int setIncomeStatusOK(Income income) throws CoreException {
		return getYdjIncomeDao().setIncomeStatusOK(income);
	}

	@Override
	public int setIncomeStatusNO(Income income) throws CoreException {
		return getYdjIncomeDao().setIncomeStatusNO(income);
	}
}
