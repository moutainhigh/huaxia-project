package com.huaxia.opas.service.decision.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.allot.AllotApplyAllotDao;
import com.huaxia.opas.dao.allot.AllotApplyAllotHisDao;
import com.huaxia.opas.dao.apply.BizInpApplC1Dao;
import com.huaxia.opas.dao.decision.BizApprovalDao;
import com.huaxia.opas.dao.decision.BizApprovalHisDao;
import com.huaxia.opas.dao.decision.FicoSdBlazeDao;
import com.huaxia.opas.dao.decision.FicoYdjBlazeDao;
import com.huaxia.opas.dao.decision.SysDecisionDao;
import com.huaxia.opas.dao.decision.SysDecisionYdjDao;
import com.huaxia.opas.dao.rule.OpasPbocCreditCueDao;
import com.huaxia.opas.dao.sysparm.ApproveZipcodeDao;
import com.huaxia.opas.dao.sysparm.CardProductDao;
import com.huaxia.opas.dao.system.ApRoleDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.input.BizInpApplC2;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.rule.OpasPbocCreditCue;
import com.huaxia.opas.domain.sysparm.ApproveZipcode;
import com.huaxia.opas.domain.sysparm.CardProduct;
import com.huaxia.opas.domain.system.ApRole;
import com.huaxia.opas.domain.thirdparty.BizApproval;
import com.huaxia.opas.domain.thirdparty.BizApprovalHis;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.decision.SysApprovalCommonService;

/**
 * @author xuzhiguo
 */
public class SysApprovalCommonServiceImpl extends AbstractService implements SysApprovalCommonService,Serializable  {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(SysApprovalCommonServiceImpl.class);

	private static final long serialVersionUID = -8008508483239828116L;

	@Resource(name = "sysDecisionYdjDao")
	private SysDecisionYdjDao sysDecisionYdjDao;
	@Resource(name = "sysDecisionDao")
	private SysDecisionDao sysDecisionDao;
	@Resource(name = "bizApprovalDao")
	private BizApprovalDao bizApprovalDao;
	@Resource(name = "bizApprovalHisDao")
	private BizApprovalHisDao bizApprovalHisDao;
	@Resource(name = "ficoSdBlazeDao")
	private FicoSdBlazeDao ficoSdBlazeDao;
	@Resource(name = "ficoYdjBlazeDao")
	private FicoYdjBlazeDao ficoYdjBlazeDao;
	@Resource(name = "allotApplyAllotDao")
	private AllotApplyAllotDao allotApplyAllotDao;
	@Resource(name = "cardProductDao")
	private CardProductDao cardProductDao;
	@Resource(name = "opasPbocCreditCueDao")
	private OpasPbocCreditCueDao opasPbocCreditCueDao;
	@Resource(name = "approveZipcodeDao")
	private ApproveZipcodeDao approveZipcodeDao;
	@Resource(name = "apRoleDao")
	private ApRoleDao apRoleDao;
	@Resource(name = "bizInpApplC1Dao")
	private BizInpApplC1Dao bizInpApplC1Dao;
	@Resource(name = "allotApplyAllotHisDao")
	private AllotApplyAllotHisDao allotApplyAllotHisDao;
	/**
	 * 查询标准卡或易达金具体信息
	 */
	public Object querySystemApprovalResult(String appId, String flag) throws Exception {
		if ("1".equals(flag)) {// 易达金
			return ficoYdjBlazeDao.selectByAppId(appId);
		} else if ("2".equals(flag)) {// 标准卡
			return ficoSdBlazeDao.selectByAppId(appId);
		} else {
			throw new Exception("flag传输值有误");
		}
	}
	@Override
	public int queryApprovalQzdcByAppId(String appId) throws Exception{
		return bizApprovalHisDao.queryApprovalQzdcByAppId(appId);
	}
	@Override
	public AllotApplyAllotHis selectLastOneByAppId(String appId) throws CoreException{
		return allotApplyAllotHisDao.selectLastOneByAppId(appId);
	}
	/**
	 * 将审批信息保存到表中
	 */
	@Override
	public int saveOrUpdateApprovalResult(BizApproval bizApproval, BizApprovalHis bizApprovalHis,boolean isSaveHis) throws Exception {
		logger.info("开始保存审批数据，申请件编号为appId="+bizApproval.getAppId());
		int result=0;
		BizApproval app = bizApprovalDao.selectByAppId(bizApproval.getAppId());
		try {
			if (app == null) {
				result=bizApprovalDao.insertSelective(bizApproval);
				logger.info("审批数据为空，插入审批数据，申请件编号为appId="+bizApproval.getAppId());
			} else {
				bizApproval.setAutoId(app.getAutoId());
				bizApproval.setLaojianNum((app==null||app.getLaojianNum()==null)?"0":app.getLaojianNum());
				result=bizApprovalDao.updateByPrimaryKey(bizApproval);
				logger.info("审批数据有值，更新审批数据，申请件编号为appId="+bizApproval.getAppId());
			}
			if(isSaveHis){
				bizApprovalHis.setLaojianNum((app==null||app.getLaojianNum()==null)?"0":app.getLaojianNum());
				bizApprovalHisDao.insertSelective(bizApprovalHis);
				logger.info("审批提交操作，插入审批历史表，申请件编号为appId="+bizApproval.getAppId());
			}
		} catch (Exception e) {
			logger.info("=========================================================【"+e.getMessage()+"】");
			if(StringUtils.isNotBlank(e.getMessage()) && e.getMessage().indexOf("IDX_BIZ_APPROVAL_APPID") != -1){
				throw new Exception("save sucess and unique error");
			}else{
				throw new RuntimeException(e);
			}
		}
		return result;
	}
	@Override
	public void updateApprovalResult(BizApproval bizApproval) throws Exception {
		bizApprovalDao.updateByPrimaryKeySelective(bizApproval);
	}
	
	public AllotApplyAllot queryAllotApplyAllot(String appId) throws Exception {
		return allotApplyAllotDao.selectByPrimaryKey(appId);
	}

	public TelcheckResult selectTelcheckResultByAppId(String appId) throws Exception {
		return bizApprovalDao.selectTelcheckResultByAppId(appId);
	}
	@Override
	public int selectApprovalCard(Map<String,String> map){
		return bizApprovalDao.selectApprovalCard(map);
	}
	public CardProduct queryCardByCardCode(String cardCode) throws CoreException {
		return cardProductDao.queryCardByCardCode(cardCode);
	}

	public List<OpasPbocCreditCue> selectOpasPbocCreditCueByAppId(String appId) throws Exception {
		return opasPbocCreditCueDao.selectByExample(appId);
	}

	public List<ApproveZipcode> queryApproveZipcodeByTelno(Map map) throws CoreException {
		return approveZipcodeDao.queryApproveZipcodeByTelno(map);
	}
	/**
	 * 根据userId查询用户对应的等级
	 */
	@Override
	public ApRole queryRoleCodeByUserId(String userId) throws CoreException{
		return apRoleDao.queryRoleCodeByUserId(userId);
	}

	/**
	 * 根据appId查询OPAS_BIZ_APPROVAL用来反显
	 */
	@Override
	public BizApproval selectByAppId(String appId) {
		BizApproval app = bizApprovalDao.selectByAppId(appId);
		return app;
	}
	@Override
	public BizInpApplC2 findBiz2info(String appno) throws CoreException {
		 return bizInpApplC1Dao.findBiz2info(appno);
	}
	@Override
	public String findYxqPd(String appId) throws CoreException {
		 return bizInpApplC1Dao.findYxqPd(appId);
	}
	@Override
	public int findHaveCard(Map<String, Object> map) throws CoreException{
		return bizInpApplC1Dao.findHaveCard(map);
	}
	@Override
	public int getPersonAge(String appId) throws CoreException {
		 return bizInpApplC1Dao.getPersonAge(appId);
	}
	@Override
	public String selectAppByAppId(String appId) {
		return bizApprovalDao.selectAppByAppId(appId);
	}
	@Override
	public String selectApplyByAppId(String appId) {
		return bizApprovalDao.selectApplyByAppId(appId);
	}
	@Override
	public String selectYdjFlagByAppId(String appId) {
		return bizApprovalDao.selectYdjFlagByAppId(appId);
	}
	@Override
	public List<String> selectApplyCardByAppId(String appId) {
		return bizApprovalDao.selectApplyCardByAppId(appId);
	}
	@Override
	public List<String> selectCity() {
		return ficoSdBlazeDao.selectGxkjCity();
	}
	@Override
	public int selectCountHaveCard(Map<String, Object> map) throws CoreException{
		return bizApprovalDao.selectCountHaveCard(map);
	}
	@Override
	public int selectCardLimit(Map<String, Object> map) throws Exception {
		return bizApprovalDao.selectCardLimit(map);
	}
	@Override
	public int selectFkCardLimit(Map<String, Object> map) throws Exception {
		return bizApprovalDao.selectFkCardLimit(map);
	}
	@Override
	public String selectApproveResult(String appId) throws Exception {
		return bizApprovalDao.selectApproveResult(appId);
	}
}
