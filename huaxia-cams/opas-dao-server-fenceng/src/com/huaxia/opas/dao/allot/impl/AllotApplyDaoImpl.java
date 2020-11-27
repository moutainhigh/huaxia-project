package com.huaxia.opas.dao.allot.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotApplyDao;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.TempAllot;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.report.KeyMessageModify;
import com.huaxia.opas.domain.thirdparty.BizApproval;

public class AllotApplyDaoImpl extends AbstractDAO implements AllotApplyDao {

	private static final long serialVersionUID = 8036407851361305852L;
	private static final String NAMESPACES = "AllotApply.";
	private static final String NAMESPACES1 = "ApplyLifeCicle.";
	
	//定时批量导入申请件到分配
	@Override
	public List<AllotApply> selectYdjAppFromC1() throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectYdjAppFromC1");
	}
	
	@Override
	public List<AllotApply> selectBzkAppFromC1() throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectBzkAppFromC1");
	}
	
	@Override
	public int insertAppBatch(List<AllotApply> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertAppBatch", list);
	}
	
	@Override
	public int updateC1Batch(List<AllotApply> list) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateC1Batch", list);
	}
	
	//end
	//多条件查询
	@Override
	public List<String> selectReviewApplication(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectReviewApplication",map);
	}
	
	@Override
	public List<String> selectYdjTelAppProd(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectYdjTelAppProd",map);
	}
	
	@Override
	public List<String> selectReviewAppByIVS(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectReviewAppByIVS",map);
	}
	
	@Override
	public List<String> selectReviewAppByScor(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectReviewAppByScor",map);
	}
	
	@Override
	public List<String> selectReviewAppByCue(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectReviewAppByCue",map);
	}
	
	@Override
	public List<String> selectBzkAppByBlaze(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectBzkAppByBlaze",map);
	}
	
	@Override
	public List<String> selectYdjAppByBlaze(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectYdjAppByBlaze",map);
	}
	
	@Override
	public List<String> selectCreditAppByBzkDecision(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectCreditAppByBzkDecision",map);
	}
	
	@Override
	public List<String> selectCreditAppByYdjDecision(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectCreditAppByYdjDecision",map);
	}
	
	@Override
	public List<String> selectAppByReviewDecision(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectAppByReviewDecision",map);
	}
	
	@Override
	public List<AllotApply> selectAppNoByOrder(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectAppNoByOrder",map);
	}
	
	@Override
	public List<AllotApply> selectAppListByOrder(Map map,int currentPage, int pageSize) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectAppListByOrder",map,currentPage , pageSize);
	}
	
	@Override
	public List<AllotApply> selectAppListByOrder(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectAppListByOrder",map);
	}
	
	@Override
	public int selectCountByOrder(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectCountByOrder",map);
	}
	//end
	
	@Override
	public List<KeyMessageModify> selectKeyMessageApp() throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectKeyMessageApp");
	}
	//批量分配
	@Override
	public int updateBatchApply(List<AllotApply> list) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateBatchApply", list);
	}
	
	@Override
	public int updateBatchList(AllotApply  allotApply) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateBatchList", allotApply);
	}
	
	@Override
	public int insertAllotApply(AllotApply allotApply) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertAllotApplySelective", allotApply);
	}
	
	//节点之间更新
	@Override
	public int update(AllotApply allotApply) throws CoreException {
		return getSqlMap().update(NAMESPACES + "update", allotApply);
	}
	//转移 回收
	@Override
	public int updateAllotApply(AllotApply allotApply) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateAllotApply", allotApply);
	}
	
	@Override
	public int updateByPrimaryKey(AllotApply allotApply) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", allotApply);
	}
	
	@Override
	public int updateBatchByIds(List<AllotApply> list) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateBatchByIds", list);
	}
	//多表综合
	// 审查   征信  审批 环节 池待分配件数量及快速
	@Override
	public int selectCount(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectCount",map);
	}
	
//	@Override
//	public int selectCountByUserCode(Map map) throws CoreException {
//		return  getSqlMap().queryForObject(NAMESPACES + "selectCountByUserCode",map);
//	}
	
	//组查询 
	@Override
	public List<AllotApply> selectAllotApplyByGroup(Map map,int currentPage, int pageSize) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectAllotApplyByGroup",map,(currentPage -1) * pageSize, pageSize);
	}
	
	@Override
	public List<AllotApply> selectAllotApplyByGroup(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectAllotApplyByGroup",map);
	}
	
	//组员
	
	@Override
	public List<AllotApply> selectAllotApplyByUser(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectAllotApplyByUser",map);
	}

	// 团办号去重
	@Override
	public List<AllotApply> selectC4ApBatch(Map map,int page, int rows) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES+"selectC4ApBatch",map,page, rows);
	}
	@Override
	public int countC5JCType(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "countC5JCType",map);
	}
	
	@Override
	public List<AllotApply> selectC5JCType(Map map,int page, int rows) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES+"selectC5JCType",map,page, rows);
	}
	@Override
	public int countC4ApBatch(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "countC4ApBatch",map);
	}
	
	//推广机构
	@Override
	public List<AllotApply> selectC5AbCode(Map map,int page, int rows) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES+"selectC5AbCode",map,page, rows);
	}
	@Override
	public int countC5AbCode(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "countC5AbCode",map);
	}
	
	//推广人员
	@Override
	public List<AllotApply> selectC4AbUser(Map map,int page, int rows) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES+"selectC4AbUser",map,page, rows);
	}
	
	@Override
	public int countC4AbUser(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "countC4AbUser",map);
	}
	
	//按申请件模糊查询
	@Override
	public List<AllotApply> selectHand(Map map) throws CoreException{
		List<AllotApply> list = new ArrayList<AllotApply>();
		list = getSqlMap().queryForList(NAMESPACES+"selectHand", map);
		return list;
	}
	
	@Override
	public int selectHandCount(String appId) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES+"selectHandCount", appId);
	}
	
	@Override
	public List<AllotApply> selectApplyListByAppId(String appId) throws CoreException{
		List<AllotApply> list = new ArrayList<AllotApply>();
		list = getSqlMap().queryForList(NAMESPACES+"selectApplyListByAppId", appId);
		return list;
	}
	
	@Override
	public AllotApply selectHandByAppId(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectHandByAppId",map);
	}
	
	@Override
	public AllotApply selectSingleByAppId(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectSingleByAppId",map);
	}
	
	@Override
	public int selectHandCount(Map map) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES+"selectHandNum", map);
	}
	//自动  
	//易达金快速  
	@Override
	public List<String> selectYdjTelApplication(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES+"selectYdjTelApplication",map);
	}

	//征信批量过件码
	@Override
	public int selectCountBlockCode(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountBlockCode",appId);
	}
	
	@Override
	public TelcheckResult selectBlockCodeList(Map map) throws CoreException {
		return (TelcheckResult) (getSqlMap().queryForObject(NAMESPACES + "selectBlockCodeList", map));
	}
	
	@Override
	public int updateBlockCode(Map map) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateBlockCode",map);
	} 
	
	@Override
	public int insertBlockCode(Map map) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertBlockCode", map);
	}
	//审批批量
	@Override
	public int selectCountRefuseCode(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountRefuseCode",appId);
	}
	
	@Override
	public BizApproval selectRefuseCodeList(Map map) throws CoreException {
		return (BizApproval) (getSqlMap().queryForObject(NAMESPACES + "selectRefuseCodeList", map));
	}
	
	@Override
	public int updateRefuseCode(Map map) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateRefuseCode",map);
	} 
	
	@Override
	public int insertRefuseCode(Map map) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertRefuseCode", map);
	}
	
	@Override
	public int insertRefuseCodeBatch(List<BizApproval> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertRefuseCodeBatch", list);
	}
	//当日新接收申请件
	@Override
	public int selectTodayApp(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectTodayApp",map);
	}
	
	//临时表
	@Override
	public int insertTemp(Map map) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTemp", map);
	}
	
	@Override
	public List<AllotApply> executeOpasPoTempAllot(Map map) {
		List<Map<String,Object>> paramList=new ArrayList<Map<String,Object>>();
		getSqlMap().queryForObject(NAMESPACES+"executeOpasPoTempAllot", map);
		List<AllotApply>  list=new ArrayList<AllotApply>();
		paramList=(List<Map<String, Object>>) ((List<Map<String, Object>>) map.get("v_cursor")==null?new ArrayList<AllotApply>():(List<Map<String, Object>>) map.get("v_cursor"));
		for(Map<String,Object> paramMap:paramList){
			AllotApply allotApply = JSON.parseObject(JSON.toJSONString(paramMap), AllotApply.class);
			list.add(allotApply);
		}
		return list;
	}
	
	@Override
	public int insertTempBatch(List<TempAllot> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTempBatch", list);
	}
	
	@Override
	public int insertTempTab(List<TempAllot> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTempTab", list);
	}
	
	@Override
	public List<AllotApply> selectAppNoByTemp(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectAppNoByTemp",map);
	}
	
	//
	@Override
	public int saveBatchByTemp(List<AllotApply> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "inserBatchByTemp", list);
	}
	
	@Override
	public int updateBatchByTemp(List<String> list) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateBatchByTemp", list);
	}
	
	@Override
	public List<AllotApply> selectApplyBySpecialMen(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectApplyBySpecialMen",map);
	}
	
	@Override
	public int selectBzkCountByConditions(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectBzkCountByConditions",map);
	}
	
	@Override
	public List<AllotApply> selectBzkByConditions(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectBzkByConditions",map);
	}
	
	@Override
	public List<AllotApply> selectBzkByConditions(Map map,int currentPage, int pageSize) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectBzkByConditions",map,currentPage,pageSize);
	}
	
	@Override
	public int selectYdjCountByConditions(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectYdjCountByConditions",map);
	}
	
	@Override
	public int selectYdjNumByConditions(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectYdjNumByConditions",map);
	}
	
	@Override
	public List<AllotApply> selectYdjByConditions(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectYdjByConditions",map);
	}
	
	@Override
	public List<AllotApply> selectYdjByConditions(Map map,int currentPage, int pageSize) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectYdjByConditions",map,currentPage,pageSize);
	}
	
	@Override
	public List<AllotApply> selectYdjAppNoByConditions(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectYdjAppNoByConditions",map);
	}
	
	@Override
	public List<AllotApply> selectYdjAppNoByConditions(Map map,int currentPage, int pageSize) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectYdjAppNoByConditions",map,currentPage,pageSize);
	}
	
	@Override
	public int selectFraudCountByConditions(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectFraudCountByConditions",map);
	}
	
	@Override
	public int selectFraudNumByConditions(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectFraudNumByConditions",map);
	}
	
	@Override
	public List<AllotApply> selectFraudByConditions(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectFraudByConditions",map);
	}
	
	@Override
	public List<AllotApply> selectFraudByConditions(Map map,int currentPage, int pageSize) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectFraudByConditions",map,currentPage,pageSize);
	}
	
	@Override
	public List<AllotApply> selectFraudAppNoByConditions(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectFraudAppNoByConditions",map);
	}
	
	@Override
	public List<AllotApply> selectFraudAppNoByConditions(Map map,int currentPage, int pageSize) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectFraudAppNoByConditions",map,currentPage,pageSize);
	}
	
	@Override
	public Map selectApplC2(String appId) throws CoreException{
		return  getSqlMap().queryForObject(NAMESPACES + "selectApplC2",appId); 
	}
	
	@Override
	public int updateLstOptTime(Map map) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateLstOptTime", map);
	}
	
	@Override
	public int queryWaitingEnterCount(Map<String, Object> params) {
		return getSqlMap().queryForObject(NAMESPACES + "queryWaitingEnterCount", params);
	}

	@Override
	public List<Map<String, Object>> queryWaitingEnterList(Map<String, Object> params, int curNum,
			int pageNum) {
		return  getSqlMap().queryForList(NAMESPACES + "queryWaitingEnterList", params, curNum, pageNum);
	}
	
	//批量分配
	@Override
	public int updateBatchSubmitFraud(List<AllotApply> list) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateBatchSubmitFraud", list);
	}
}
