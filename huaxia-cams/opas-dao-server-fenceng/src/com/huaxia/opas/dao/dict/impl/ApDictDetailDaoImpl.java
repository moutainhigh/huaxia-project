package com.huaxia.opas.dao.dict.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.dict.ApDictDetailDao;
import com.huaxia.opas.domain.dict.ApDict;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.dict.ApDictDetailSmall;

public class ApDictDetailDaoImpl extends AbstractDAO implements ApDictDetailDao {

	private static final long serialVersionUID = 2578632308244166456L;

	private static final String NAMESPACES = "ApDictDetail.";

	@Override
	public int insertApDictDetail(ApDictDetail apDictDetail) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApDictDetail", apDictDetail);
	}

	@Override
	public int insertApDictDetailSelective(ApDictDetail apDictDetail) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApDictDetailSelective", apDictDetail);
	}

	@Override
	public int deleteApDictDetailByDictDetailId(String dictDetailId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteApDictDetailByDictDetailId", dictDetailId);
	}

	@Override
	public int updateApDictDetail(ApDictDetail apDictDetail) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApDictDetail", apDictDetail);
	}

	@Override
	public int updateApDictDetailSelective(ApDictDetail apDictDetail) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApDictDetailSelective", apDictDetail);
	}

	@Override
	public ApDictDetail queryApDictDetailByDictDetailId(String dictDetailId) throws CoreException {
		return (ApDictDetail) (getSqlMap().queryForObject(NAMESPACES + "queryApDictDetailByDictDetailId",
				dictDetailId));
	}

	@Override
	public List<ApDictDetailSmall> queryDictDetailByCode(String dictCode) {
		return getSqlMap().queryForList(NAMESPACES + "queryDictDetailByCode", dictCode);
	}

	@Override
	public Map<String, Object> queryDictDetails(ApDictDetail dictDetail, int curNum, int pageNum) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<ApDict> dictDetails = getSqlMap().queryForList(NAMESPACES + "queryApDictDetailList", dictDetail, curNum,
				pageNum);
		int total = queryDictDetailTotal(dictDetail);
		dataMap.put("rows", dictDetails);
		dataMap.put("total", total);
		return dataMap;
	}

	@Override
	public int queryDictDetailTotal(ApDictDetail dictDetail) {
		return Integer.parseInt(
				String.valueOf(getSqlMap().queryForObject(NAMESPACES + "queryApDictDetailCount", dictDetail)));
	}

	@Override
	public int deleteDictDetailByDictId(String dictDetailId) {
		return getSqlMap().delete(NAMESPACES + "deleteDictDetailByDictId", dictDetailId);
	}

	@Override
	public List<ApDictDetail> queryDictDetailListByDictId(String dictId) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryDictDetailListByDictId", dictId);
	}

	//城市代码
	@Override
	public Map<String,String> queryCityMap() {
		Map<String,String> cityMap = new HashMap<String,String>();
		List<ApDictDetail> list = getSqlMap().queryForList(NAMESPACES+"queryCityMap");
		for (ApDictDetail apDictDetail : list) {
			cityMap.put(apDictDetail.getDictDetailName(),apDictDetail.getDictDetailCode());			
		}
		return cityMap;
	}

	//参数管理:行职业收入,业务字典
	//单位性质
	@Override
	public Map<String, String> queryUnionMap() {
		Map<String,String> unionMap = new HashMap<String,String>();
		List<ApDictDetail> list = getSqlMap().queryForList(NAMESPACES+"queryUnionMap");
		for (ApDictDetail apDictDetail : list) {
			unionMap.put(apDictDetail.getDictDetailName(),apDictDetail.getDictDetailCode());			
		}
		return unionMap;
	}

	//教育程度
	@Override
	public Map<String, String> queryEducationMap() {
		Map<String,String> educationMap = new HashMap<String,String>();
		List<ApDictDetail> list = getSqlMap().queryForList(NAMESPACES+"queryEducationMap");
		for (ApDictDetail apDictDetail : list) {
			educationMap.put(apDictDetail.getDictDetailName(),apDictDetail.getDictDetailCode());			
		}
		return educationMap;
	}

	//行业代码
	@Override
	public Map<String, String> queryIndustryMap() {
		Map<String,String> industryMap = new HashMap<String,String>();
		List<ApDictDetail> list = getSqlMap().queryForList(NAMESPACES+"queryIndustryMap");
		for (ApDictDetail apDictDetail : list) {
			industryMap.put(apDictDetail.getDictDetailName(),apDictDetail.getDictDetailCode());			
		}
		return industryMap;
	}

	//职业代码
	@Override
	public Map<String, String> queryJobMap() {
		Map<String,String> industryMapMap = new HashMap<String,String>();
		List<ApDictDetail> list = getSqlMap().queryForList(NAMESPACES+"queryJobMap");
		for (ApDictDetail apDictDetail : list) {
			industryMapMap.put(apDictDetail.getDictDetailName(),apDictDetail.getDictDetailCode());			
		}
		return industryMapMap;
	}
	//推荐来源
	@Override
	public Map<String, String> queryRcdSrcMap() {
		Map<String, String> rcdSrcMap = new HashMap<String, String>();
		List<ApDictDetail> list = getSqlMap().queryForList(NAMESPACES+"queryRcdSrc");
		for (ApDictDetail apDictDetail : list) {
			rcdSrcMap.put(apDictDetail.getDictDetailName(),apDictDetail.getDictDetailCode());
		}
		return rcdSrcMap;
	}
	
	//平台类型
	@Override
	public Map<String, String> queryPlatTypeMap() {
		Map<String, String> platTypeMap = new HashMap<String, String>();
		List<ApDictDetail> list = getSqlMap().queryForList(NAMESPACES+"queryPlatType");
		for (ApDictDetail apDictDetail : list) {
			platTypeMap.put(apDictDetail.getDictDetailName(),apDictDetail.getDictDetailCode());
		}
		return platTypeMap;
	}

	//合作类型
	@Override
	public Map<String, String> queryCoprTypeMap() {
		Map<String, String> coprTypeMap = new HashMap<String, String>();
		List<ApDictDetail> list = getSqlMap().queryForList(NAMESPACES+"queryCoprType");
		for (ApDictDetail apDictDetail : list) {
			coprTypeMap.put(apDictDetail.getDictDetailName(),apDictDetail.getDictDetailCode());
		}
		return coprTypeMap;
	}

	//平台风险层级
	@Override
	public Map<String, String> queryPlatRiskLvlMap() {
		Map<String, String> platRiskLvlMap = new HashMap<String, String>();
		List<ApDictDetail> list = getSqlMap().queryForList(NAMESPACES+"queryPlatRiskLvl");
		for (ApDictDetail apDictDetail : list) {
			platRiskLvlMap.put(apDictDetail.getDictDetailName(),apDictDetail.getDictDetailCode());
		}
		return platRiskLvlMap;
	}
	
	/**
	 * 接口模式-wenyh
	 * @return
	 */
	@Override
	public Map<String, String> queryInterfaceModeMap() {
		Map<String, String> interfaceModeMap = new HashMap<String, String>();
		List<ApDictDetail> list = getSqlMap().queryForList(NAMESPACES+"queryInterfaceMode");
		for (ApDictDetail apDictDetail : list) {
			interfaceModeMap.put(apDictDetail.getDictDetailName(),apDictDetail.getDictDetailCode());
		}
		return interfaceModeMap;
	}
	
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	@Override
	public int queryApDetailByDictCode(ApDictDetail dictDetail) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryApDetailByDictCode",dictDetail);
	}
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	@Override
	public int queryApDetailByDictName(ApDictDetail dictDetail) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryApDetailByDictName",dictDetail);
	}
}
