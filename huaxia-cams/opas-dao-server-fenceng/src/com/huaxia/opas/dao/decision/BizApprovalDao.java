package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.thirdparty.BizApproval;

public interface BizApprovalDao {
	int deleteByPrimaryKey(String autoId);

    int insert(BizApproval record);

    int insertSelective(BizApproval record);

    BizApproval selectByPrimaryKey(String autoId);

    int updateByPrimaryKeySelective(BizApproval record);

    int updateByPrimaryKey(BizApproval record);

	BizApproval selectByAppId(String appId);
	
	String selectAppByAppId(String appId);
	String selectApplyByAppId(String appId);
	String selectYdjFlagByAppId(String appId);

	TelcheckResult selectTelcheckResultByAppId(String appId);
	//zhanglibo
	public int selectMemberJobCompletCountApprove(Map<String, Object> map);

	int selectApprovalCard(Map<String, String> map);

	int selectMemberJobCompletCountApproveCheckup(Map<String, Object> mapPar);
	
	 int updateInitSaveFlag(Map map);
	 //是否批准相同卡产品修改
	 List<String> selectApplyCardByAppId(String appId);
	//0115、0116两种卡判定审批系统中该卡产品编号下已批准的卡片数量
	int selectCountHaveCard(Map<String, Object> map);
	//客户级总卡数限制
	int selectCardLimit(Map<String, Object> map);
	int selectFkCardLimit(Map<String, Object> map);
	//迅卡审批结论查询
	String selectApproveResult(String appId);
}