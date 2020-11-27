package com.huaxia.opas.dao.credit.impl;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.credit.CreditMatchingDao;
import com.huaxia.opas.domain.credit.CreditMatching;
/*******************************
 *@describe:人行征信查询匹配设置 接口实现类
 *@author：xiaoJianFeng
 *@date:2017-03-02
 ********************************/
public class CreditMatchingDaoImpl extends AbstractDAO implements CreditMatchingDao{
	private static final long serialVersionUID = 1L;
	private static final String NAMESPACES = "credit.";
	
	@Override
	public List<CreditMatching> selectCreditMatchPage(CreditMatching creditMatching, int begNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectCreditMatchPage", creditMatching, begNum,pageNum);
	}

	@Override
	public void addCreditMatch(CreditMatching creditMatching) {
		getSqlMap().insert(NAMESPACES+"insertCreditMatch", creditMatching);
	}

	@Override
	public void updateCreditMatch(CreditMatching creditMatching) {
		getSqlMap().update(NAMESPACES+"updateCreditMatch", creditMatching);
	}

	@Override
	public void deleteCreditMatch(String autoId) {
		getSqlMap().delete(NAMESPACES+"deleteCreditMatch", autoId);
	}

	@Override
	public String selectCreditMatchCount(CreditMatching creditMatching) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCreditMatchPageCount",creditMatching);
	}

}
