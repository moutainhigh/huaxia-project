package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.CardProductDao;
import com.huaxia.opas.domain.decision.OpasCustBaseInfo;
import com.huaxia.opas.domain.sysparm.CardFace;
import com.huaxia.opas.domain.sysparm.CardProduct;
import com.huaxia.opas.domain.sysparm.UserCard;
import com.huaxia.opas.domain.sysparm.YearFeeDerate;

/**
 * 卡产品维护DAO层实现类
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class CardProductDaoImpl extends AbstractDAO implements CardProductDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8660166296413181569L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "CardProduct.";
	//添加状态为启动
	@Override
	public int saveCardProductStart(CardProduct cardProduct) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCardProduct1", cardProduct);
	}
	//添加状态为禁止
	@Override
	public int saveCardProductEnd(CardProduct cardProduct) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCardProduct2", cardProduct);
	}
	//更新
	@Override
	public int updateCardProduct(CardProduct cardProduct) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateCardProduct", cardProduct);
	}
	//删除
	@Override
	public int deleteCardProduct(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteCardProductById", autoId);
	}
	//卡产品件数查询
	@Override
	public int queryCardProductCount(CardProduct cardProduct) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCardProductCount", cardProduct);
	}
	//卡版面件数查询
	@Override
	public int queryCardFaceCount(CardFace cardFace) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCardFaceCount", cardFace);
	}
	//年费方式件数查询
	@Override
	public int queryYearFeeDerateCount(YearFeeDerate yearFeeDerate) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryYearFeeDerateCount", yearFeeDerate);
	}
	//卡产品初始化信息查询
	@Override
	public List<CardProduct> queryCardProduct(CardProduct cardProduct,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryCardProduct1", cardProduct, curNum, pageNum);
	}
	//卡产品页面点击查询后实现查询和排序
	@Override
	public List<CardProduct> queryCardProduct(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryCardProduct2", params, curNum, pageNum);
	}
	//卡版面信息查询
	@Override
	public List<CardFace> queryCardFace(CardFace cardFace,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryCardFace", cardFace, curNum, pageNum);
	}
	//年费方式信息查询
	@Override
	public List<YearFeeDerate> queryYearFeeDerate(YearFeeDerate yearFeeDerate,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryYearFeeDerate", yearFeeDerate, curNum, pageNum);
	}
	//卡产品信息是否重复查询
	@Override
	public CardProduct queryCardProduct(CardProduct cardProduct) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCardProduct3", cardProduct);
	}
	//用户卡种表查询
	@Override
	public List<UserCard> queryUserCard() throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryUserCard");
	}
	//卡产品新增时插入用户卡种表
	@Override
	public int saveUserCard(CardProduct cardProduct) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "saveUserCard", cardProduct);
	}
	//卡产品更新时更新用户卡种表
	/*@Override
	public int updateByPrimaryKeyUserCard(String productCode) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeyUserCard", productCode);
	}*/
	//卡产品删除时删除用户卡种表
	@Override
	public int deleteByPrimaryKeyUserCard(String productCode) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKeyUserCard", productCode);
	}
	@Override
	public CardProduct queryCardByCardCode(String cardCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCardByCardCode", cardCode);
	}
	@Override
	public CardProduct queryProductCode(String productCode) {
//		(CardProduct) getSqlMap().queryForObject(CUSTBASEINFO_NAMESPACES + "selectByAppId", appId);
		return (CardProduct)getSqlMap().queryForObject(NAMESPACES + "queryCardProduct4", productCode);
	}
	@Override
	public CardProduct queryCardProductByCardCode(String productCode) {
		return (CardProduct)getSqlMap().queryForObject(NAMESPACES + "queryCardProductByCardCode", productCode);
	}
	@Override
	public List<CardProduct> queryCardAgreeByCardCode(String cardCode) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryCardAgreeByCardCode",cardCode);
	}
		@Override
	public String queryCardProductStatus(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCardProductStatus", autoId);
	}
	@Override
	public int updateCardProductWithoutStatus(CardProduct cardProduct) {
		return getSqlMap().update(NAMESPACES + "updateCardProductWithoutStatus", cardProduct);
	}
	
	@Override
	public List<Map<String,String>> queryCodeAndName() {
		return getSqlMap().queryForList(NAMESPACES + "queryCodeAndName");
	}
}
