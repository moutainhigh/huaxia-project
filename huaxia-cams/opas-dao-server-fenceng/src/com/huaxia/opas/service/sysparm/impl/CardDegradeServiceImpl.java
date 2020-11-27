package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.CardDegrade;
import com.huaxia.opas.domain.sysparm.ProductCode;
import com.huaxia.opas.dao.sysparm.CardDegradeDao;
import com.huaxia.opas.service.sysparm.CardDegradeService;
/**
 * 卡产品降级信息服务层实现类
 * @author liudi
 * @since 2017-03-14
 * @version 1.0
 */
public class CardDegradeServiceImpl extends AbstractDAO implements CardDegradeService,Serializable  {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5050539148322122916L;

	@Resource(name = "cardDegradeDao")
	private CardDegradeDao cardDegradeDao;

	public CardDegradeDao getCardDegradeDao() {
		return cardDegradeDao;
	}

	public void setCardDegradeDao(CardDegradeDao cardDegradeDao) {
		this.cardDegradeDao = cardDegradeDao;
	}

	//添加状态为Start
	public int saveCardDegradeStart(CardDegrade cardDegrade) throws CoreException {
		return getCardDegradeDao().saveCardDegradeStart(cardDegrade);
	}
	
	//添加状态为End
	public int saveCardDegradeEnd(CardDegrade cardDegrade) throws CoreException {
		return getCardDegradeDao().saveCardDegradeEnd(cardDegrade);
	}
	
	//修改
	public int updateCardDegrade(CardDegrade cardDegrade) throws CoreException {
		return getCardDegradeDao().updateCardDegrade(cardDegrade);
	}
	
	//删除
	public int deleteCardDegrade(String autoId) throws CoreException {
		return getCardDegradeDao().deleteCardDegrade(autoId);
	}
	
	//卡产品降级件数查询
	public int queryCardDegradeCount(CardDegrade cardDegrade) throws CoreException {
		return getCardDegradeDao().queryCardDegradeCount(cardDegrade);
	}
	
	//卡产品件数查询
	public int queryProductCodeCount(ProductCode productCode) throws CoreException {
		return getCardDegradeDao().queryProductCodeCount(productCode);
	}
	
	//卡产品降级信息查询
	public  List<CardDegrade> queryCardDegrade(CardDegrade cardDegrade,int curNum,int pageNum) throws CoreException {
		return getCardDegradeDao().queryCardDegrade(cardDegrade, curNum, pageNum);
	}
	
	//点击查询后，触发卡产品降级信息查询和排序功能
	public List<CardDegrade> queryCardDegrade(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getCardDegradeDao().queryCardDegrade(params, curNum, pageNum);
	}
	
	//卡产品信息详细查询
	public List<ProductCode> queryProductCode(ProductCode productCode,int curNum,int pageNum) throws CoreException {
		return getCardDegradeDao().queryProductCode(productCode, curNum, pageNum);
	}
	
	//查询卡产品是否重复
	public CardDegrade queryCardDegrade(CardDegrade cardDegrade) throws CoreException {
		return getCardDegradeDao().queryCardDegrade(cardDegrade);
	}
	
	//查询卡产品信息以及该卡的降级产品
	@Override
	public List<CardDegrade> queryCardDegradeAndDegreeCard(String cardCode) throws CoreException {
		return getCardDegradeDao().queryCardDegradeAndDegreeCard(cardCode);
	}
}
