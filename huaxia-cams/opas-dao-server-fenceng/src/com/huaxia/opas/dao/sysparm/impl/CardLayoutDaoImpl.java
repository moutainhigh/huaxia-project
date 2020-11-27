package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.CardDegrade;
import com.huaxia.opas.domain.sysparm.CardDegradeFace;
import com.huaxia.opas.domain.sysparm.ProductCode;
import com.huaxia.opas.dao.sysparm.CardDegradeDao;
import com.huaxia.opas.dao.sysparm.CardLayoutDao;

/**
 * 卡版面降级信息DAO层实现类
 * @author wdb
 * @since 2018-1-16
 * @version 1.1
 */
public class CardLayoutDaoImpl extends AbstractDAO implements CardLayoutDao{
	private static final long serialVersionUID = -2861234477015808491L;
	private static final String NAMESPACES = "CardLayout.";
	//查询卡版面信息
	@Override
	public String queryCardFace(String productCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCardFace", productCode);
	}
	@Override
	public CardDegradeFace queryCardLayout(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCardLayout", map);
	}
	
	@Override
	public int queryCardLayoutCount(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCardLayoutCount", map);
	}
	@Override
	public List<CardDegradeFace> queryCardLayoutList(Map map, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryCardLayoutList", map, curNum, pageNum);
	}
	//添加
	@Override
	public int saveCardLayout(Map map) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "saveCardLayout", map);
	}
	//删除
	@Override
	public int deleteCardLayoutById(String id) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteCardLayoutById", id);
	}
	//修改
	@Override
	public int updateCardLayout(CardDegradeFace cardDegradeFace) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateCardLayout", cardDegradeFace);
	}
}
