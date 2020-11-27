package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.CardLayoutDao;
import com.huaxia.opas.domain.sysparm.CardDegrade;
import com.huaxia.opas.domain.sysparm.CardDegradeFace;
import com.huaxia.opas.domain.sysparm.ProductCode;
import com.huaxia.opas.service.sysparm.CardLayoutService;
/**
 * 卡版面降级信息服务层实现类
 * @author wdb
 * @since 2018-01-16
 * @version 1.1
 */
public class CardLayoutServiceImpl extends AbstractDAO implements CardLayoutService,Serializable  {

	private static final long serialVersionUID = 4923578140279316212L;
	@Resource(name = "cardLayoutDao")
	private CardLayoutDao cardLayoutDao;
	
	public CardLayoutDao getCardLayoutDao() {
		return cardLayoutDao;
	}

	public void setCardLayoutDao(CardLayoutDao cardLayoutDao) {
		this.cardLayoutDao = cardLayoutDao;
	}
	
	//查询卡版面信息
	@Override
	public List<String> queryCardFace(String cardCode) throws CoreException {
		String cardFace=StringUtils.trimToEmpty(getCardLayoutDao().queryCardFace(cardCode));
		List<String> cardFaceList=new ArrayList<String>();
		if(!"".equals(cardFace)){
			cardFaceList=Arrays.asList((cardFace.split(",")));
		}
		return cardFaceList;
	}
	
	public CardDegradeFace queryCardLayout(Map map) throws CoreException {
		return getCardLayoutDao().queryCardLayout(map);
	}
	public int queryCardLayoutCount(Map map) throws CoreException {
		return getCardLayoutDao().queryCardLayoutCount(map);
	}
	public List<CardDegradeFace> queryCardLayoutList(Map map,int curNum,int pageNum) throws CoreException {
		return getCardLayoutDao().queryCardLayoutList(map, curNum, pageNum);
	}
	//保存卡版面信息
	public int saveCardLayout(Map map) throws CoreException {
		return getCardLayoutDao().saveCardLayout(map);
	}
	public int deleteCardLayoutById(String id) throws CoreException {
		return getCardLayoutDao().deleteCardLayoutById(id);
	}
	
	//修改
	public int updateCardLayout(CardDegradeFace cardDegradeFace) throws CoreException {
		return getCardLayoutDao().updateCardLayout(cardDegradeFace);
	}
}
