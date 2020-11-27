package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.CardFace;
import com.huaxia.opas.domain.sysparm.MatchingCard;
import com.huaxia.opas.domain.sysparm.ProductCode;
import com.huaxia.opas.dao.sysparm.MatchingCardDao;
import com.huaxia.opas.service.sysparm.MatchingCardService;
/**
 * 配发卡信息服务层实现类
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class MatchingCardServiceImpl extends AbstractDAO implements MatchingCardService,Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2664082823661531037L;
	
	@Resource(name = "matchingCardDao")
	private MatchingCardDao matchingCardDao;



	public MatchingCardDao getMatchingCardDao() {
		return matchingCardDao;
	}

	public void setMatchingCardDao(MatchingCardDao matchingCardDao) {
		this.matchingCardDao = matchingCardDao;
	}

	//添加状态为Start
	public int saveMatchingCardStart(MatchingCard matchingCard) throws CoreException {
		return getMatchingCardDao().saveMatchingCardStart(matchingCard);
	}
	
	//添加状态为End
	public int saveMatchingCardEnd(MatchingCard matchingCard) throws CoreException {
		return getMatchingCardDao().saveMatchingCardEnd(matchingCard);
	}
	
	//修改
	public int updateMatchingCard(MatchingCard matchingCard) throws CoreException {
		return getMatchingCardDao().updateMatchingCard(matchingCard);
	}
	
	//删除
	public int deleteMatchingCard(String autoId) throws CoreException {
		return getMatchingCardDao().deleteMatchingCard(autoId);
	}
	
	//配发卡件数查询
	public int queryMatchingCardCount(MatchingCard matchingCard) throws CoreException {
		return getMatchingCardDao().queryMatchingCardCount(matchingCard);
	}
	
	//卡版面件数查询
	public int queryCardFaceCount(CardFace cardFace) throws CoreException {
		return getMatchingCardDao().queryCardFaceCount(cardFace);
	}
	
	//配发卡信息查询
	public List<MatchingCard> queryMatchingCard(MatchingCard matchingCard,int curNum,int pageNum) throws CoreException {
		return getMatchingCardDao().queryMatchingCard(matchingCard, curNum, pageNum);
	}
	
	//点击查询后，触发配发卡信息查询和排序功能
	public List<MatchingCard> queryMatchingCard(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getMatchingCardDao().queryMatchingCard(params, curNum, pageNum);
	}
	
	//卡版面查询
	public List<CardFace> queryCardFace(CardFace cardFace,int curNum,int pageNum) throws CoreException {
		return getMatchingCardDao().queryCardFace(cardFace, curNum, pageNum);
	}
	
	//查询卡产品信息和配发卡产品信息是否重复
	public MatchingCard queryMatchingCard(MatchingCard matchingCard) throws CoreException {
		return getMatchingCardDao().queryMatchingCard(matchingCard);
	}
	
	//卡产品件数查询
	public int queryProductCodeCount(ProductCode productCode) throws CoreException {
		return getMatchingCardDao().queryProductCodeCount(productCode);
	}
	
	//卡产品信息详细查询
	public List<ProductCode> queryProductCode(ProductCode productCode,int curNum,int pageNum) throws CoreException {
		return getMatchingCardDao().queryProductCode(productCode, curNum, pageNum);
	}
}
