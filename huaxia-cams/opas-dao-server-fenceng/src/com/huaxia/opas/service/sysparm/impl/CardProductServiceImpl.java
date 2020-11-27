package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.CardFace;
import com.huaxia.opas.domain.sysparm.CardProduct;
import com.huaxia.opas.domain.sysparm.UserCard;
import com.huaxia.opas.domain.sysparm.YearFeeDerate;
import com.huaxia.opas.dao.sysparm.CardProductDao;
import com.huaxia.opas.service.sysparm.CardProductService;
/**
 * 卡产品维护服务层实现类
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class CardProductServiceImpl extends AbstractDAO implements CardProductService,Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -46826519668381289L;

	@Resource(name = "cardProductDao")
	private CardProductDao cardProductDao;



	public CardProductDao getCardProductDao() {
		return cardProductDao;
	}

	public void setCardProductDao(CardProductDao cardProductDao) {
		this.cardProductDao = cardProductDao;
	}

	//添加状态为Start
	public int saveCardProductStart(CardProduct cardProduct) throws CoreException {
		return getCardProductDao().saveCardProductStart(cardProduct);
	}
	
	//添加状态为End
	public int saveCardProductEnd(CardProduct cardProduct) throws CoreException {
		return getCardProductDao().saveCardProductEnd(cardProduct);
	}
	
	//修改
	public int updateCardProduct(CardProduct cardProduct) throws CoreException {
		return getCardProductDao().updateCardProduct(cardProduct);
	}
	
	//删除
	public int deleteCardProduct(String autoId) throws CoreException {
		return getCardProductDao().deleteCardProduct(autoId);
	}
	
	//卡产品件数查询
	public int queryCardProductCount(CardProduct cardProduct) throws CoreException {
		return getCardProductDao().queryCardProductCount(cardProduct);
	}
	
	//卡版面件数查询
	public int queryCardFaceCount(CardFace cardFace) throws CoreException {
		return getCardProductDao().queryCardFaceCount(cardFace);
	}
	
	//年费方式件数查询
	public int queryYearFeeDerateCount(YearFeeDerate yearFeeDerate) throws CoreException {
		return getCardProductDao().queryYearFeeDerateCount(yearFeeDerate);
	}
	
	//卡产品信息查询
	public List<CardProduct> queryCardProduct(CardProduct cardProduct,int curNum,int pageNum) throws CoreException {
		return getCardProductDao().queryCardProduct(cardProduct, curNum, pageNum);
	}
	
	//点击查询后，触发卡产品信息查询和排序功能
	public List<CardProduct> queryCardProduct(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getCardProductDao().queryCardProduct(params, curNum, pageNum);
	}
	
	//卡版面查询
	public List<CardFace> queryCardFace(CardFace cardFace,int curNum,int pageNum) throws CoreException {
		return getCardProductDao().queryCardFace(cardFace, curNum, pageNum);
	}
	
	//年费方式查询
	public List<YearFeeDerate> queryYearFeeDerate(YearFeeDerate yearFeeDerate,int curNum,int pageNum) throws CoreException {
		return getCardProductDao().queryYearFeeDerate(yearFeeDerate, curNum, pageNum);
	}
	
	//查询卡产品是否重复
	public CardProduct queryCardProduct(CardProduct cardProduct) throws CoreException {
		return getCardProductDao().queryCardProduct(cardProduct);
	}
	
	//用户卡种表查询
	public List<UserCard> queryUserCard() throws CoreException {
		return getCardProductDao().queryUserCard();
	}
	
	//卡产品新增时插入用户卡种表
	public int saveUserCard(CardProduct cardProduct) throws CoreException {
		return getCardProductDao().saveUserCard(cardProduct);
	}

	//卡产品更新时更新用户卡种表
	/*public int updateByPrimaryKeyUserCard(String productCode) throws CoreException {
		return getCardProductDao().updateByPrimaryKeyUserCard(productCode);
	}*/
	
	//卡产品删除时删除用户卡种表
	public int deleteByPrimaryKeyUserCard(String productCode) throws CoreException {
		return getCardProductDao().deleteByPrimaryKeyUserCard(productCode);
	}

	/**
	 * 查询卡名称
	 */
	public CardProduct queryCardProduct(String productCode)
			throws CoreException {
		
		return getCardProductDao().queryProductCode(productCode);
	}
	
	/**
	 * 查询卡名称、卡版面、年费
	 */
	@Override
	public CardProduct queryCardProductByCardCode(String productCode) throws CoreException {
		return getCardProductDao().queryCardProductByCardCode(productCode);
	}
	@Override
	public List<CardProduct> queryCardAgreeByCardCode(String cardCode) throws CoreException {
		return getCardProductDao().queryCardAgreeByCardCode(cardCode);
	}
		@Override
	public String queryCardProductStatus(String autoId) {
		return getCardProductDao().queryCardProductStatus(autoId);
	}

	@Override
	public int updateCardProductWithoutStatus(CardProduct cardProduct) {
		return getCardProductDao().updateCardProductWithoutStatus(cardProduct);
	}

	@Override
	public List<Map<String,String>> queryCodeAndName() {
		return getCardProductDao().queryCodeAndName();
	}
}
