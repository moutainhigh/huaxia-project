package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CardDegrade;
import com.huaxia.opas.domain.sysparm.ProductCode;
/**
 * 卡产品降级信息服务层接口
 * @author liudi
 * @since 2017-03-14
 * @version 1.0
 */
public interface CardDegradeService {
	//添加状态为Start
	public int saveCardDegradeStart(CardDegrade cardDegrade) throws CoreException;
	//添加状态为End
	public int saveCardDegradeEnd(CardDegrade cardDegrade) throws CoreException;
	//修改
	public int updateCardDegrade(CardDegrade cardDegrade) throws CoreException;
	//删除
	public int deleteCardDegrade(String autoId) throws CoreException;
	//卡产品降级件数查询
	public int queryCardDegradeCount(CardDegrade cardDegrade) throws CoreException;
	//卡产品件数查询
	public int queryProductCodeCount(ProductCode productCode) throws CoreException;
	//卡产品降级信息查询
	public List<CardDegrade> queryCardDegrade(CardDegrade cardDegrade,int curNum,int pageNum) throws CoreException;
	//点击查询后，触发卡产品降级信息查询和排序功能
	public List<CardDegrade> queryCardDegrade(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//卡产品信息详细查询
	public List<ProductCode> queryProductCode(ProductCode productCode,int curNum,int pageNum) throws CoreException;
	//查询卡产品是否重复
	public CardDegrade queryCardDegrade(CardDegrade cardDegrade) throws CoreException;
	List<CardDegrade> queryCardDegradeAndDegreeCard(String cardCode) throws CoreException;

}
