package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.CardFace;
import com.huaxia.opas.domain.sysparm.MatchingCard;
import com.huaxia.opas.domain.sysparm.ProductCode;
import com.huaxia.opas.dao.sysparm.MatchingCardDao;

/**
 * 配发卡信息DAO层实现类
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class MatchingCardDaoImpl extends AbstractDAO implements MatchingCardDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6005161707835936614L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "MatchingCard.";
	//添加状态为Start
	@Override
	public int saveMatchingCardStart(MatchingCard matchingCard) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertMatchingCard1", matchingCard);
	}
	//添加状态为End
	@Override
	public int saveMatchingCardEnd(MatchingCard matchingCard) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertMatchingCard2", matchingCard);
	}
	//修改
	@Override
	public int updateMatchingCard(MatchingCard matchingCard) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateMatchingCard", matchingCard);
	}
	//删除
	@Override
	public int deleteMatchingCard(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteMatchingCardById", autoId);
	}
	//配发卡件数查询
	@Override
	public int queryMatchingCardCount(MatchingCard matchingCard) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryMatchingCardCount", matchingCard);
	}
	//卡版面件数查询
	@Override
	public int queryCardFaceCount(CardFace cardFace) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCardFaceCount", cardFace);
	}
	//配发卡信息查询
	@Override
	public List<MatchingCard> queryMatchingCard(MatchingCard matchingCard,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryMatchingCard1", matchingCard, curNum, pageNum);
	}
	//点击查询后，触发配发卡信息查询和排序功能
	@Override
	public List<MatchingCard> queryMatchingCard(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryMatchingCard2", params, curNum, pageNum);
	}
	//卡版面查询
	@Override
	public List<CardFace> queryCardFace(CardFace cardFace,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryCardFace", cardFace, curNum, pageNum);
	}
	//查询卡产品信息和配发卡产品信息是否重复
	@Override
	public MatchingCard queryMatchingCard(MatchingCard matchingCard) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryMatchingCard3", matchingCard);
	}
	//卡产品件数查询
	@Override
	public int queryProductCodeCount(ProductCode productCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryProductCodeCount", productCode);
	}
	//卡产品信息详细查询
	@Override
	public List<ProductCode> queryProductCode(ProductCode productCode, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryProductCode", productCode, curNum, pageNum);
	}

}
