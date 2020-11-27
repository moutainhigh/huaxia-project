package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CardFace;
import com.huaxia.opas.domain.sysparm.MatchingCard;
import com.huaxia.opas.domain.sysparm.ProductCode;
/**
 * 配发卡信息服务层接口
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public interface MatchingCardService {
	//添加状态为Start
	public int saveMatchingCardStart(MatchingCard matchingCard) throws CoreException;
	//添加状态为End
	public int saveMatchingCardEnd(MatchingCard matchingCard) throws CoreException;
	//修改
	public int updateMatchingCard(MatchingCard matchingCard) throws CoreException;
	//删除
	public int deleteMatchingCard(String autoId) throws CoreException;
	//配发卡件数查询
	public int queryMatchingCardCount(MatchingCard matchingCard) throws CoreException;
	//卡版面件数查询
	public int queryCardFaceCount(CardFace cardFace) throws CoreException;
	//配发卡信息查询
	public List<MatchingCard> queryMatchingCard(MatchingCard matchingCard,int curNum,int pageNum) throws CoreException;
	//点击查询后，触发配发卡信息查询和排序功能
	public List<MatchingCard> queryMatchingCard(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//卡版面查询
	public List<CardFace> queryCardFace(CardFace cardFace,int curNum,int pageNum) throws CoreException;
	//查询卡产品信息和配发卡产品信息是否重复
	public MatchingCard queryMatchingCard(MatchingCard matchingCard) throws CoreException;
	//卡产品件数查询
	public int queryProductCodeCount(ProductCode productCode) throws CoreException;
	//卡产品信息详细查询
	public List<ProductCode> queryProductCode(ProductCode productCode,int curNum,int pageNum) throws CoreException;

}
