package com.huaxia.opas.service.sysparm.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.PromoterRiskDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.IdentityRisk;
import com.huaxia.opas.domain.sysparm.Promoter;
import com.huaxia.opas.domain.sysparm.PromoterHis;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.PromoterRiskService;
import com.huaxia.opas.util.TransDateUtil;

public class PromoterRiskServiceImpl extends AbstractService implements PromoterRiskService, Serializable {

	private static final long serialVersionUID = -5286276550553333492L;
	
	@Resource(name = "promoterRiskDao")
	private PromoterRiskDao promoterRiskDao;

	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	public PromoterRiskDao getPromoterRiskDao() {
		return promoterRiskDao;
	}

	public void setPromoterRiskDao(PromoterRiskDao promoterRiskDao) {
		this.promoterRiskDao = promoterRiskDao;
	}

	@Override
	public int queryPromoterCount(Promoter prom) throws CoreException {
		return promoterRiskDao.queryPromoterCount(prom);
	}

	@Override
	public List<Promoter> queryPromoterList(Promoter prom, int curNum, int pageNum) throws CoreException {
		return promoterRiskDao.queryPromoterList(prom, curNum, pageNum);
	}

	@Override
	public int queryPromoterHisCount(PromoterHis promHis) throws CoreException {
		return promoterRiskDao.queryPromoterHisCount(promHis);
	}

	@Override
	public List<PromoterHis> queryPromoterHisList(PromoterHis promHis, int curNum, int pageNum) throws CoreException {
		return promoterRiskDao.queryPromoterHisList(promHis, curNum, pageNum);
	}

	@Override
	public int insertPromoter(Promoter prom, PromoterHis promHis) throws CoreException, ParseException {
		int count = promoterRiskDao.insertPromoter(prom);
		promoterRiskDao.insertPromoterHis(promHis);
		return count;
	}

	@Override
	public void updatePromoter(Promoter prom, PromoterHis promHis) throws CoreException, ParseException {
		promoterRiskDao.updatePromoter(prom);
		promoterRiskDao.insertPromoterHis(promHis);
	}

	@Override
	public void deletePromoters(List<String> autoIds) throws CoreException, ParseException {
		for (String autoId : autoIds) {
			promoterRiskDao.deletePromoter(autoId);
		}
	}

	@Override
	public void closePromoters(List<String> autoIds ,String user) throws CoreException, ParseException {
		for (String autoId : autoIds) {
			Promoter prom = new Promoter();
			prom.setAutoId(autoId);
			prom.setOperator(user);
			prom.setOperatTime(new Date());
			promoterRiskDao.closePromoter(prom);
		}
	}

	@Override
	public void openPromoters(List<String> autoIds ,String user) throws CoreException, ParseException {
		for (String autoId : autoIds) {
			Promoter prom = new Promoter();
			prom.setAutoId(autoId);
			prom.setOperator(user);
			prom.setOperatTime(new Date());
			promoterRiskDao.openPromoter(prom);
		}
	}

	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	@Override
	public int insertPromoterRiskList(List<Promoter> list, BatchfileInfo batchfileInfo) throws Exception {
		int inputCounts =  promoterRiskDao.insertPromoterImportFile(list);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insertRist(batchfileInfo);
		return inputCounts;
	}

}
