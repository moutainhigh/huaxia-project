package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CardFace;
import com.huaxia.opas.domain.sysparm.CardProduct;
import com.huaxia.opas.domain.sysparm.UserCard;
import com.huaxia.opas.domain.sysparm.YearFeeDerate;
/**
 * 卡产品维护DAO层接口
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public interface CardProductDao {
	//添加状态为启动
	public int saveCardProductStart(CardProduct cardProduct) throws CoreException;
	//添加状态为禁止
	public int saveCardProductEnd(CardProduct cardProduct) throws CoreException;
	//更新
	public int updateCardProduct(CardProduct cardProduct) throws CoreException;
	//删除
	public int deleteCardProduct(String autoId) throws CoreException;
	//卡产品件数查询
	public int queryCardProductCount(CardProduct cardProduct) throws CoreException;
	//卡版面件数查询
	public int queryCardFaceCount(CardFace cardFace) throws CoreException;
	//年费方式件数查询
	public int queryYearFeeDerateCount(YearFeeDerate yearFeeDerate) throws CoreException;
	//卡产品初始化信息查询
	public List<CardProduct> queryCardProduct(CardProduct cardProduct,int curNum,int pageNum) throws CoreException;
	//卡产品页面点击查询后实现查询和排序
	public List<CardProduct> queryCardProduct(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//卡版面信息查询
	public List<CardFace> queryCardFace(CardFace cardFace,int curNum,int pageNum) throws CoreException;
	//年费方式信息查询
	public List<YearFeeDerate> queryYearFeeDerate(YearFeeDerate yearFeeDerate,int curNum,int pageNum) throws CoreException;
	//卡产品信息是否重复查询
	public CardProduct queryCardProduct(CardProduct cardProduct) throws CoreException;
	//用户卡种表查询
	public List<UserCard> queryUserCard() throws CoreException;;
	//卡产品新增时插入用户卡种表
	public int saveUserCard(CardProduct cardProduct) throws CoreException;
	//卡产品更新时更新用户卡种表
	/*public int updateByPrimaryKeyUserCard(String productCode) throws CoreException;*/
	//卡产品删除时删除用户卡种表
	public int deleteByPrimaryKeyUserCard(String productCode) throws CoreException;
	//
	public CardProduct queryCardByCardCode(String cardCode) throws CoreException;
	public CardProduct queryProductCode(String productCode);
	List<CardProduct> queryCardAgreeByCardCode(String cardCode) throws CoreException;
	//查询修改对象前的状态
	public String queryCardProductStatus(String autoId);
	//修改对象信息不包括状态
	public int updateCardProductWithoutStatus(CardProduct cardProduct);

	public List<Map<String,String>> queryCodeAndName();
	CardProduct queryCardProductByCardCode(String productCode);
	
}
