package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.CardDegrade;
import com.huaxia.opas.domain.sysparm.ProductCode;
import com.huaxia.opas.dao.sysparm.CardDegradeDao;

/**
 * 卡产品降级信息DAO层实现类
 * @author liudi
 * @since 2017-03-14
 * @version 1.0
 */
public class CardDegradeDaoImpl extends AbstractDAO implements CardDegradeDao{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2861234477015808491L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "CardDegrade.";
	//添加状态为Start
	@Override
	public int saveCardDegradeStart(CardDegrade cardDegrade) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCardDegrade1", cardDegrade);
	}
	//添加状态为End
	@Override
	public int saveCardDegradeEnd(CardDegrade cardDegrade) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCardDegrade2", cardDegrade);
	}
	//修改
	@Override
	public int updateCardDegrade(CardDegrade cardDegrade) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateCardDegrade", cardDegrade);
	}
	//删除
	@Override
	public int deleteCardDegrade(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteCardDegradeById", autoId);
	}
	//卡产品降级件数查询
	@Override
	public int queryCardDegradeCount(CardDegrade cardDegrade) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCardDegradeCount", cardDegrade);
	}
	//卡产品件数查询
	@Override
	public int queryProductCodeCount(ProductCode productCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryProductCodeCount", productCode);
	}
	//卡产品降级信息查询
	@Override
	public List<CardDegrade> queryCardDegrade(CardDegrade cardDegrade,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryCardDegrade1", cardDegrade, curNum, pageNum);
	}
	//点击查询后，触发卡产品降级信息查询和排序功能
	@Override
	public List<CardDegrade> queryCardDegrade(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryCardDegrade2", params, curNum, pageNum);
	}
	//卡产品信息详细查询
	@Override
	public List<ProductCode> queryProductCode(ProductCode productCode, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryProductCode", productCode, curNum, pageNum);
	}
	//查询卡产品是否重复
	@Override
	public CardDegrade queryCardDegrade(CardDegrade cardDegrade) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCardDegrade3", cardDegrade);
	}
	//查询卡产品信息以及该卡的降级产品
	@Override
	public List<CardDegrade> queryCardDegradeAndDegreeCard(String cardCode) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryCardDegradeAndDegreeCard", cardCode);
	}
}
