package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CardDegrade;
import com.huaxia.opas.domain.sysparm.CardDegradeFace;
import com.huaxia.opas.domain.sysparm.ProductCode;
/**
 * 卡版面降级信息服务层接口
 * @author wdb
 * @since 2018-01-16
 * @version 1.1
 */
public interface CardLayoutService {
	//查询卡版面
	List<String> queryCardFace(String cardCode) throws CoreException;
	//查询卡版面实体
	CardDegradeFace queryCardLayout( Map map) throws CoreException;
	int queryCardLayoutCount(Map map) throws CoreException;
	List<CardDegradeFace> queryCardLayoutList(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//保存卡版面降级信息
	int saveCardLayout(Map  map) throws CoreException;
	//删除
	int deleteCardLayoutById(String id) throws CoreException;
	//修改
	int updateCardLayout(CardDegradeFace cardDegradeFace) throws CoreException;
	
}
