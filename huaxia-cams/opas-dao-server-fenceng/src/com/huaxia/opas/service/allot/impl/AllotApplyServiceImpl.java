package com.huaxia.opas.service.allot.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotApplyAllotDao;
import com.huaxia.opas.dao.allot.AllotApplyAllotHisDao;
import com.huaxia.opas.dao.allot.AllotApplyDao;
import com.huaxia.opas.dao.allot.AllotBatchFailDao;
import com.huaxia.opas.dao.allot.AllotBzkResultDao;
import com.huaxia.opas.dao.allot.AllotCommonDao;
import com.huaxia.opas.dao.allot.AllotQueueDao;
import com.huaxia.opas.dao.allot.AutoApplyDao;
import com.huaxia.opas.dao.allot.ReviewDecisionDao;
import com.huaxia.opas.dao.apply.ApplyInfoDao;
import com.huaxia.opas.dao.apply.ApplyLifeCicleDao;
import com.huaxia.opas.dao.apply.BizInpApplC1Dao;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.collect.InfoCollectDao;
import com.huaxia.opas.dao.common.sqlmap.SqlExecutor;
import com.huaxia.opas.dao.compare.RevCompInfoDao;
import com.huaxia.opas.dao.decision.BizApprovalDao;
import com.huaxia.opas.dao.decision.BizApprovalHisDao;
import com.huaxia.opas.dao.decision.FicoSdBlazeDao;
import com.huaxia.opas.dao.decision.FicoYdjBlazeDao;
import com.huaxia.opas.dao.decision.KeyfiledMarchinfoDao;
import com.huaxia.opas.dao.decision.SysDecisionYdjDao;
import com.huaxia.opas.dao.decision.TelcheckAddnoteDao;
import com.huaxia.opas.dao.thirdparty.PBOCDao;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.audit.ApprovaInfoSupp;
import com.huaxia.opas.domain.compare.RevCompInfo;
import com.huaxia.opas.domain.decision.OpasReviewDecisionResult;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.FicoSdBlaze;
import com.huaxia.opas.domain.input.FicoYdjBlaze;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;
import com.huaxia.opas.domain.riskcheck.RiskCheck;
import com.huaxia.opas.domain.thirdparty.BizApproval;
import com.huaxia.opas.domain.thirdparty.BizApprovalHis;
import com.huaxia.opas.service.allot.AllotApplyService;
import com.huaxia.opas.service.workflow.TopbpmService;
import com.huaxia.opas.util.CommonProperties;
import com.huaxia.opas.util.StringUtil;
import com.huaxia.opas.util.UUIDGenerator;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

import net.sf.json.JSONArray;

/**
 * 分件条件查询分配
 *
 * @author wangdebin
 * @version v1.1(初始v1.0)
 * @history 修改历史记录 ------------------------------------------------ 2017-10-16
 *          修复审查批量提交审查库缺失及录入比对查看备注缺失问题 2017-10-17 修复池中挂起或释放操作员申请件的问题 2017-10-17
 *          添加团办号和推广机构号多选项 2017-10-20 添加特定征信人员申请件查询功能 2017-10-23 删除多余的方法,优化
 *          2017-10-25 更改比对信息取值情况 2017-10-31 修改列表组中文展示不全问题 2017-11-01
 *          增加叫停申请件不能分件功能 2017-11-20 增加blaze多个字段入表
 *          ------------------------------------------------
 */
public class AllotApplyServiceImpl extends AbstractDAO implements AllotApplyService, Serializable {

	private static final long serialVersionUID = -6302103168062163852L;
	private static final Logger log = LoggerFactory.getLogger(AllotApplyServiceImpl.class);
	@Resource(name = "allotApplyDao")
	private AllotApplyDao allotApplyDao;

	public AllotApplyDao getAllotApplyDao() {
		return allotApplyDao;
	}

	public void setAllotApplyDao(AllotApplyDao allotApplyDao) {
		this.allotApplyDao = allotApplyDao;
	}

	@Resource(name = "allotCommonDao")
	private AllotCommonDao allotCommonDao;

	public AllotCommonDao getAllotCommonDao() {
		return allotCommonDao;
	}

	public void setAllotCommonDao(AllotCommonDao allotCommonDao) {
		this.allotCommonDao = allotCommonDao;
	}

	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDao;

	@Resource(name = "allotApplyAllotDao")
	private AllotApplyAllotDao allotApplyAllotDao;

	@Resource(name = "applyLifeCicleDao")
	private ApplyLifeCicleDao applyLifeCicleDao;

	@Resource(name = "allotApplyAllotHisDao")
	private AllotApplyAllotHisDao allotApplyHisDao;

	@Resource(name = "allotQueueDao")
	private AllotQueueDao allotQueueDao;

	@Resource(name = "reviewDecisionDao")
	private ReviewDecisionDao reviewDecisionDao;

	@Resource(name = "telcheckAddnoteDao")
	private TelcheckAddnoteDao telcheckAddnoteDao;

	@Resource(name = "bizApprovalDao")
	private BizApprovalDao bizApprovalDao;

	@Resource(name = "bizApprovalHisDao")
	private BizApprovalHisDao bizApprovalHisDao;

	@Resource(name = "bizInpApplC1Dao")
	private BizInpApplC1Dao bizInpApplC1Dao;

	@Resource(name = "ficoSdBlazeDao")
	private FicoSdBlazeDao ficoSdBlazeDao;

	@Resource(name = "ficoYdjBlazeDao")
	private FicoYdjBlazeDao ficoYdjBlazeDao;

	@Resource(name = "revCompInfoDao")
	private RevCompInfoDao revCompInfoDao;

	@Resource(name = "applyInfoDao")
	private ApplyInfoDao applyInfoDao;

	@Resource(name = "keyfiledMarchinfoDao")
	private KeyfiledMarchinfoDao keyfiledMarchinfoDao;

	@Resource(name = "pbocDao")
	private PBOCDao pbocDao;

	@Resource(name = "allotBzkResultDao")
	private AllotBzkResultDao allotBzkResultDao;

	@Resource(name = "infoCollectDao")
	private InfoCollectDao infoCollectDao;

	@Resource(name = "allotBatchFailDao")
	private AllotBatchFailDao allotBatchFailDao;

	@Resource(name = "sysDecisionYdjDao")
	private SysDecisionYdjDao sysDecisionYdjDao;

	@Resource(name = "sqlExecutor")
	private SqlExecutor sqlExecutor;
	@Resource(name = "autoApplyDao")
	private AutoApplyDao autoApplyDao;
	
	@Resource(name = "topbpmService")
	private TopbpmService topbpmService;

	// 申请件批量标识打标、
	@Override
	public int updateFico(Map map) {
		String node = (String) map.get("node") != null ? (String) map.get("node") : "";
		String userCode = (String) map.get("userCode") != null ? (String) map.get("userCode") : "";
		String userName = (String) map.get("userName") != null ? (String) map.get("userName") : "";
		String ydjFlag = (String) map.get("ydjFlag") != null ? (String) map.get("ydjFlag") : "";
		String appId = "";
		List<String> ordrList = new ArrayList<String>();
		if ("01".equals(node)) {
			appId = (String) map.get("appId") != null ? (String) map.get("appId") : "";
		} else if ("02".equals(node) || "03".equals(node)) {
			if ("1".equals(ydjFlag)) {// 易达金
				List<String> batchList = (List<String>) map.get("list");
				// 易达金件和套卡取一件
				if (batchList.size() > 0) {
					for (int i = 0; i < batchList.size(); i++) {
						String subStr = batchList.get(i).trim().substring(0, 15);
						if (!ordrList.contains(subStr)) {// 申请件前15位包含加入新集合中
							ordrList.add(subStr);
						}
					}
				}
			} else if ("2".equals(ydjFlag)) {// 标准卡
				ordrList = (List<String>) map.get("list");
			}
		}
		map.put("flag", "7");
		map.put("currNode", node);
		int result = 0;
		String msg = "";
		Client client = null;
		if ("01".equals(node)) {// 审查批量
			try {
				OpasReviewDecisionResult ordr = new OpasReviewDecisionResult();
				ordr.setAppId(appId);
				ordr.setAutoId(UUIDGenerator.getUUID());
				ordr.setBatchCode("2");
				ordr.setCrtUser("system");
				ordr.setAuditor("system");
				ordr.setAuditorName("系统");
				log.info(appId + "该申请件审查批量处理现调工作流中..........");
				// 记录历史
				allotApplyHisDao.insertCopyOpasAllotToAllotHis(map);
				// 更新批量信息
				AllotApply allotApply = allotApplyDao.selectSingleByAppId(map);
				allotApply.setAppId(appId);
				allotApply.setBatchOperateFlag("1");
				allotApply.setDelStatus("1");
				allotApplyDao.updateAllotApply(allotApply);
				// 记录生命周期
				saveAppLifeCicle(map);
				msg = saveNewUrlClient(appId, node);
				if ("1".equals(msg)) {
					result = 1;
				}
				log.info(appId + "该申请件成功调用工作流");
				int count2 = 0;
				count2 = reviewDecisionDao.countBatchCode(appId);
				if (count2 > 0) {
					reviewDecisionDao.updateBatchCode(ordr);
				} else if (count2 == 0) {
					reviewDecisionDao.insertBatchCode(ordr);
				}
				log.info("成功修改审查批量标记为成功状态,申请件编号为" + appId);
				int numb = 0;
				// 将行职业代码入库
				numb = reviewDecisionDao.selectCount(appId);
				if (numb == 0) {// 插入
					reviewDecisionDao.insertApprovaInfoSupp(map);
				} else {// 更新
					reviewDecisionDao.updateByAppId(map);
				}
				// 需要往 征信电核注记信息表[OPAS_TELCHECK_ADDNOTE] 批量标识 落1条标识
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String str = sdf.format(nowDate);
				TelcheckAddnote zhukaNote = new TelcheckAddnote();
				zhukaNote.setAppId(appId.trim());
				zhukaNote.setAutoId(UUIDGenerator.getUUID());
				String zhukaMemo = "批量审查：操作员(原件管理岗)" + userName + ",时间：" + str;
				zhukaNote.setBigMemo(zhukaMemo.trim());
				zhukaNote.setCrtUser(userCode);
				zhukaNote.setCrtDate(nowDate);
				zhukaNote.setTypeFlag("3");
				telcheckAddnoteDao.insertPhone(zhukaNote);
				// 备注表插入备注 录入比对( 抄录文字完整有效、签名完整有效、国籍填写完整有效、身份证有效期完整有效)
				// 获取申请件编号的第7位
				Map<String, Object> remarkMap = new HashMap<>();
				String isWebBolt = appId.substring(6, 7);
				String remarkInfo = "";
				if ("B".equals(isWebBolt)) {
					remarkInfo = "录入比对全流程备注:必选项未勾选项【国籍填写完整有效】【身份证有效期完整有效】";
				} else {
					remarkInfo = "录入比对全流程备注:必选项未勾选项【抄录文字完整有效】【签名完整有效】【国籍填写完整有效】【身份证有效期完整有效】";
				}
				remarkMap.put("remarkInfo", remarkInfo);
				remarkMap.put("remarkId", UUIDGenerator.getUUID());
				remarkMap.put("remarkUser", "system");
				remarkMap.put("remarkTime", new Date());
				remarkMap.put("appId", appId);
				remarkMap.put("currNode", "01");
				revCompInfoDao.insertRemark(remarkMap);
				// 将审查库比对入表
				saveInfoCompare(appId);
				// 将blaze返回信息行职业代码及其它入表
				// saveInfoCollect(map);
			} catch (Exception e) {
				log.info(appId + "批量提交异常,异常原因--" + e.getMessage());
				int failCount = 0;
				try {
					failCount = allotBatchFailDao.selectCount(map);
					if (failCount > 0) {
						allotBatchFailDao.updateFailApply(map);
					} else {
						allotBatchFailDao.insertFailApply(map);
					}
				} catch (CoreException e1) {
					log.error("批量更新或新增异常：{}，失败条数：{}", new Object[] { e1.getMessage(), failCount }, e1);
				}
			}

		} else if ("02".equals(node)) {// 征信批量
			TelcheckResult telcheckResult = (TelcheckResult) map.get("tel");
			map.put("currStatus", "1");
			int count = 0;
			int count2 = 0;
			if (ordrList.size() > 0) {
				String blockCode = telcheckResult.getBlockCode() == null ? "" : telcheckResult.getBlockCode();
				String creditRefuseReason = telcheckResult.getCreditRefuseReason() == null ? ""
						: telcheckResult.getCreditRefuseReason();
				map.put("blockCode", blockCode);
				map.put("creditRefuseReason", creditRefuseReason);
				map.put("crtUser", userCode);
				map.put("crediter", userCode);
				log.info("征信批量>>>>>>>参数信息:过件码-blockCode=" + blockCode + ",备注=" + creditRefuseReason + "============");
				try {
					client = getClient(client);
				} catch (Exception e) {
					log.info("连接工作流失败" + e.getMessage());
				}
				if ("1".equals(ydjFlag)) {// 易达金考虑 套卡 或者捞件情况 只有一件
					for (int j = 0; j < ordrList.size(); j++) {
						try {
							String appNo = ordrList.get(j);
							Map<String, Object> lifeMap = new HashMap<String, Object>();
							lifeMap.put("appId", appNo);
							lifeMap.put("currNode", "02");
							lifeMap.put("ydjFlag", "1");
							lifeMap.put("currStatus", "1");
							// 查询当前环节当前状态申请件
							int appNum = allotApplyDao.selectHandCount(lifeMap);
							// 只有一件 判断是否捞件还是无套卡 或者 异常导致
							if (appNum == 1) {
								AllotApply app = allotApplyDao.selectHandByAppId(lifeMap);
								// 捞件情况
								String laoJianFlag = app.getLaoJianFlag();
								// 无套卡
								String matchingCardFlag = app.getMatchingCardFlag();

								appId = app.getAppId();
								// 申请件单独捞件 或单件退回或易达金未配发套卡
								if ("01".equals(laoJianFlag) || "02".equals(laoJianFlag) || "0".equals(matchingCardFlag)
										|| "3".equals(app.getDelStatus())) {
									map.put("appId", app.getAppId());
									log.info(appId + "该申请件 易达金 征信批量处理现调工作流中..............");
									// 记录历史
									allotApplyHisDao.insertCopyOpasAllotToAllotHis(map);
									// 更新为当前环节完成
									app.setBatchCreditFlag("1");
									app.setDelStatus("1");
									allotApplyDao.updateAllotApply(app);
									// (调工作流的申请件)记录生命周期
									saveAppLifeCicle(map);
									// 征信电核结果信息表[OPAS_TELCHECK_RESULT]
									count = allotApplyDao.selectCountBlockCode(appId);
									if (count > 0) {
										TelcheckResult tel = allotApplyDao.selectBlockCodeList(map);
										map.put("currOptGroup", "");
										map.put("autoId", tel.getAutoId());
										allotApplyDao.updateBlockCode(map);
									} else if (count == 0) {
										String autoId = UUIDGenerator.getUUID();
										map.put("autoId", autoId);
										allotApplyDao.insertBlockCode(map);
									}
									// 需要往 征信电核注记信息表[OPAS_TELCHECK_ADDNOTE] 批量标识
									// 拼写1条
									TelcheckAddnote zhukaNote = new TelcheckAddnote();
									Date nowDate = new Date();
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									String str = sdf.format(nowDate);
									zhukaNote.setAppId(app.getAppId());
									zhukaNote.setAutoId(UUIDGenerator.getUUID());
									String zhukaMemo = "批量征信：操作员(组长)" + userName + ",时间：" + str + "。批量征信过件码："
											+ blockCode + ",备注信息：" + creditRefuseReason;
									zhukaNote.setBigMemo(zhukaMemo.trim());
									zhukaNote.setCrtUser(userCode);
									zhukaNote.setCrtDate(nowDate);
									zhukaNote.setTypeFlag("3");
									telcheckAddnoteDao.insertPhone(zhukaNote);
									msg = saveNewUrlClient(map, client);
									if ("1".equals(msg)) {
										result++;
									}
									log.info(appId + "调用工作流,返回信息" + msg);
								}
							} else if (appNum == 2) {// 两件 最后一件调工作流
								List<AllotApply> appList = allotApplyDao.selectHand(lifeMap);
								appId = appNo + "2";
								String taokaId = appNo + "1";
								log.info(appId + "该申请件 易达金 征信批量处理 现调工作流中...........");
								// 记录历史 两件一起记录
								List<String> lifeList = new ArrayList<String>();
								lifeList.add(taokaId);
								lifeList.add(appId);
								allotApplyHisDao.insertHisBatch(lifeList);
								// 手动将分配表申请件 改状态
								for (AllotApply app : appList) {
									app.setBatchCreditFlag("1");
									app.setDelStatus("1");
									allotApplyDao.updateAllotApply(app);
								}
								// (调工作流的申请件)记录生命周期
								// 主卡
								map.put("appId", appId);
								saveAppLifeCicle(map);
								// 记录(套卡不调工作流)生命周期
								map.put("appId", taokaId);
								saveAppLifeCicle(map);
								// 征信电核结果信息表[OPAS_TELCHECK_RESULT]\
								count = allotApplyDao.selectCountBlockCode(taokaId);
								log.info("易达金征信批量,申请件编号(taokaId)为" + taokaId + ",征信电核记录为" + count + "+++++++++++");
								if (count > 0) {
									TelcheckResult tel = allotApplyDao.selectBlockCodeList(map);
									map.put("currOptGroup", "");
									map.put("autoId", tel.getAutoId());
									allotApplyDao.updateBlockCode(map);
								} else if (count == 0) {
									String autoId = UUIDGenerator.getUUID();
									map.put("autoId", autoId);
									allotApplyDao.insertBlockCode(map);
								}
								// 需要往 征信电核注记信息表[OPAS_TELCHECK_ADDNOTE] 批量标识
								// 拼写1条
								TelcheckAddnote taoKaNote = new TelcheckAddnote();
								Date nowDate = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String str = sdf.format(nowDate);
								taoKaNote.setAppId(taokaId);
								taoKaNote.setAutoId(UUIDGenerator.getUUID());
								String taokaMemo = "批量征信：操作员(组长)" + userName + ",时间：" + str + "。批量征信过件码：" + blockCode
										+ ",备注信息：" + creditRefuseReason;
								taoKaNote.setBigMemo(taokaMemo.trim());
								taoKaNote.setCrtUser(userCode);
								taoKaNote.setCrtDate(nowDate);
								taoKaNote.setTypeFlag("3");
								telcheckAddnoteDao.insertPhone(taoKaNote);
								// 征信电核结果信息表[OPAS_TELCHECK_RESULT]
								map.put("appId", appId);
								count2 = allotApplyDao.selectCountBlockCode(appId);
								log.info("易达金征信批量申请件编号(appId)=" + appId + ",征信电核结果记录为" + count2 + "+++++++++++");
								if (count2 > 0) {
									TelcheckResult tel = allotApplyDao.selectBlockCodeList(map);
									map.put("autoId", tel.getAutoId());
									allotApplyDao.updateBlockCode(map);
								} else if (count2 == 0) {
									String autoId = UUIDGenerator.getUUID();
									map.put("autoId", autoId);
									allotApplyDao.insertBlockCode(map);
								}
								// 需要往 征信电核注记信息表[OPAS_TELCHECK_ADDNOTE] 落1条标识
								TelcheckAddnote zhukaNote = new TelcheckAddnote();
								BeanUtils.copyProperties(zhukaNote, taoKaNote);
								zhukaNote.setAppId(appId);
								zhukaNote.setAutoId(UUIDGenerator.getUUID());
								telcheckAddnoteDao.insertPhone(zhukaNote);
								msg = saveNewUrlClient(map, client);
								if ("1".equals(msg)) {
									result++;
								}
								log.info(appId + "调用工作流,返回信息" + msg);

							}
						} catch (Exception e) {
							log.info(appId + "批量提交异常,异常原因--" + e.getMessage());
							int failCount = 0;
							try {
								failCount = allotBatchFailDao.selectCount(map);
								if (failCount > 0) {
									map.put("status", "0");
									allotBatchFailDao.updateFailApply(map);
								} else {
									allotBatchFailDao.insertFailApply(map);
								}
							} catch (CoreException e1) {
								log.error("批量更新或新增异常：{}，失败条数：{}", new Object[] { e1.getMessage(), failCount }, e1);
							}
						}
					}
				} else if ("2".equals(ydjFlag)) {// 标卡 单件调工作流()
					for (int i = 0; i < ordrList.size(); i++) {
						try {
							appId = ordrList.get(i);
							map.put("appId", appId);
							// 查询分件表 判断是否适合再调工作流
							AllotApply allotApply = allotApplyDao.selectSingleByAppId(map);
							String currNodeBatch = StringUtils.trimToEmpty(allotApply.getCurrNode());
							if (allotApply != null && "02".equals(currNodeBatch)) {
								log.info(appId + "该申请件现调标准卡工作流中........");
								// 记录历史
								allotApplyHisDao.insertCopyOpasAllotToAllotHis(map);
								// (调工作流的申请件)记录生命周期
								saveAppLifeCicle(map);
								count = allotApplyDao.selectCountBlockCode(appId);
								// 征信电核结果信息表[OPAS_TELCHECK_RESULT]
								if (count > 0) {
									TelcheckResult tel = allotApplyDao.selectBlockCodeList(map);
									map.put("currOptGroup", "");
									map.put("autoId", tel.getAutoId());
									allotApplyDao.updateBlockCode(map);
								} else if (count == 0) {
									String autoId = UUIDGenerator.getUUID();
									map.put("autoId", autoId);
									allotApplyDao.insertBlockCode(map);
								}
								// 需要往 征信电核注记信息表[OPAS_TELCHECK_ADDNOTE] 批量标识
								// 落1条标识
								TelcheckAddnote zhukaNote = new TelcheckAddnote();
								Date nowDate = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String str = sdf.format(nowDate);
								zhukaNote.setAppId(appId.trim());
								zhukaNote.setAutoId(UUIDGenerator.getUUID());
								String zhukaMemo = "批量征信：操作员(组长)" + userName + ",时间：" + str + "。批量征信过件码：" + blockCode
										+ ",备注信息：" + creditRefuseReason;
								zhukaNote.setBigMemo(zhukaMemo.trim());
								zhukaNote.setCrtUser(userCode);
								zhukaNote.setCrtDate(nowDate);
								zhukaNote.setTypeFlag("3");
								telcheckAddnoteDao.insertPhone(zhukaNote);
								msg = saveNewUrlClient(map, client);
								if ("1".equals(msg)) {
									result++;
								}
								log.info(appId + "调用工作流,返回信息" + msg);
							}
						} catch (Exception e) {
							log.info(appId + "批量提交异常,异常原因--" + e.getMessage());
							// 失败申请件记录异常表
							int failCount = 0;
							try {
								failCount = allotBatchFailDao.selectCount(map);
								if (failCount > 0) {
									map.put("status", "0");
									allotBatchFailDao.updateFailApply(map);
								} else {
									allotBatchFailDao.insertFailApply(map);
								}
							} catch (CoreException e1) {
								log.error("批量更新或新增异常：{}，失败条数：{}", new Object[] { e1.getMessage(), failCount }, e1);
							}
						}
					}
				}
			}
		} else if ("03".equals(node)) {// 审批批量
			BizApproval biz = (BizApproval) map.get("biz");
			map.put("currStatus", "1");
			int count = 0;
			int count2 = 0;
			String refuseCode1 = biz.getRefuseCode1() == null ? "" : biz.getRefuseCode1();
			String memo = biz.getMemo() == null ? "" : biz.getMemo();
			map.put("refuseCode1", refuseCode1);
			map.put("memo", memo);
			map.put("approveResult", "0");
			if (ordrList.size() > 0) {
				try {
					client = getClient(client);
				} catch (Exception e) {
					log.info("连接工作流失败" + e.getMessage());
				}
				if ("1".equals(ydjFlag)) {// 易达金
					for (int j = 0; j < ordrList.size(); j++) {
						try {
							String appNo = ordrList.get(j).trim();
							Map<String, Object> lifeMap = new HashMap<String, Object>();
							lifeMap.put("appId", appNo);
							lifeMap.put("currNode", "03");
							lifeMap.put("delStatus", "0");
							lifeMap.put("ydjFlag", "1");
							lifeMap.put("currStatus", "1");
							Date nowDate = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String str = sdf.format(nowDate);
							// 查询当前环节当前状态申请件
							int appNum = allotApplyDao.selectHandCount(lifeMap);
							String taokaId = "";
							if (appNum == 1) {// 只有一件 判断是否捞件 无套卡 还是异常导致
								AllotApply app = allotApplyDao.selectHandByAppId(lifeMap);
								// 捞件情况
								String laoJianFlag = app.getLaoJianFlag();
								// 无套卡
								String matchingCardFlag = app.getMatchingCardFlag();

								appId = app.getAppId();
								// 申请件单独捞件 或单件退回或易达金未配发套卡
								if ("01".equals(laoJianFlag) || "02".equals(laoJianFlag) || "0".equals(matchingCardFlag)
										|| "3".equals(app.getDelStatus())) {
									map.put("appId", appId);
									log.info(appId + "该申请件成功经过 易达金审批批量处理   现调工作流中");
									// 记录历史
									allotApplyHisDao.insertCopyOpasAllotToAllotHis(map);
									// 更新为当前环节完成(调工作流成功 分件表状态修改)
									app.setBatchApprovalFlag("1");
									app.setCurrNode("04");
									app.setDelStatus("1");
									allotApplyDao.updateAllotApply(app);
									// (调工作流的申请件)记录生命周期
									saveAppLifeCicle(map);
									// 审批表落数据
									map.put("userCode", userCode);
									map.put("userName", userName);
									// 同时往审批表 及 历史表落数据
									if ("0".equals(matchingCardFlag) || "2".equals(matchingCardFlag)) {// 易达金卡
										saveBizApproval(appId, "1", map);
									} else if ("1".equals(matchingCardFlag)) {// 易达件套卡
										saveBizApproval(appId, "2", map);
									}
									// 需要往 征信电核注记信息表[OPAS_TELCHECK_ADDNOTE]
									// 落1条标识
									TelcheckAddnote zhukaNote = new TelcheckAddnote();
									zhukaNote.setAppId(appId);
									zhukaNote.setAutoId(UUIDGenerator.getUUID());
									String zhukaMemo = "批量审批：操作员(组长)" + userName + ",时间：" + str + "批量审批-拒绝码："
											+ refuseCode1 + ",备注信息：" + memo;
									zhukaNote.setBigMemo(zhukaMemo.trim());
									zhukaNote.setCrtUser(userCode);
									zhukaNote.setCrtDate(nowDate);
									zhukaNote.setTypeFlag("3");
									telcheckAddnoteDao.insertPhone(zhukaNote);
									msg = saveNewUrlClient(map, client);
									if ("1".equals(msg)) {
										result++;
									}
									log.info(appId + "调用工作流,返回信息" + msg);
								}
							} else if (appNum == 2) {// 两件 最后一件调工作流
								List<AllotApply> appList = allotApplyDao.selectHand(lifeMap);
								taokaId = appNo + "1";
								appId = appNo + "2";
								log.info(appId + "该申请件成功经过 易达金审批批量处理   现调工作流中");
								// 记录历史
								List<String> lifeList = new ArrayList<String>();
								lifeList.add(taokaId);
								lifeList.add(appId);
								allotApplyHisDao.insertHisBatch(lifeList);
								// (调工作流的申请件)记录生命周期
								map.put("appId", appId);
								saveAppLifeCicle(map);
								// (不调工作流的申请件)记录生命周期
								map.put("appId", taokaId);
								saveAppLifeCicle(map);
								map.put("userCode", userCode);
								map.put("userName", userName);
								String matchingCardFlag = "";
								// 手动将分配表申请件归档及记录审批表 及历史表
								for (AllotApply app : appList) {
									app.setCurrNode("04");
									app.setBatchApprovalFlag("1");
									app.setDelStatus("1");
									matchingCardFlag = app.getMatchingCardFlag() != null ? app.getMatchingCardFlag()
											: "";
									String appId2 = app.getAppId() != null ? app.getAppId() : "";
									if ("1".equals(matchingCardFlag)) {// 易达金套卡
										saveBizApproval(appId2, "2", map);
									} else if ("2".equals(matchingCardFlag)) {// 易达金卡
										saveBizApproval(appId2, "1", map);
									}
									allotApplyDao.updateAllotApply(app);
								}
								// 需要往 征信电核注记信息表[OPAS_TELCHECK_ADDNOTE] 批量标识
								// 落1条标识
								TelcheckAddnote taoKaNote = new TelcheckAddnote();
								taoKaNote.setAppId(taokaId);
								taoKaNote.setAutoId(UUIDGenerator.getUUID());
								String taokaMemo = "批量审批：操作员(组长)" + userName + ",时间：" + str + "批量审批-拒绝码：" + refuseCode1
										+ ",备注信息：" + memo;
								taoKaNote.setBigMemo(taokaMemo.trim());
								taoKaNote.setCrtUser(userCode);
								taoKaNote.setCrtDate(nowDate);
								taoKaNote.setTypeFlag("3");
								telcheckAddnoteDao.insertPhone(taoKaNote);
								// 批量标识 拼写1条
								TelcheckAddnote zhukaNote = new TelcheckAddnote();
								BeanUtils.copyProperties(zhukaNote, taoKaNote);
								zhukaNote.setAppId(appId);
								zhukaNote.setAutoId(UUIDGenerator.getUUID());
								telcheckAddnoteDao.insertPhone(zhukaNote);
								msg = saveNewUrlClient(map, client);
								if ("1".equals(msg)) {
									result++;
								}
								log.info(appId + "调用工作流,返回信息" + msg);
							}
						} catch (Exception e) {
							log.info(appId + "批量提交异常,异常原因--" + e.getMessage());
							int failCount = 0;
							try {
								failCount = allotBatchFailDao.selectCount(map);
								if (failCount > 0) {
									map.put("status", "0");
									allotBatchFailDao.updateFailApply(map);
								} else {
									allotBatchFailDao.insertFailApply(map);
								}
							} catch (CoreException e1) {
								log.error("批量更新或新增异常：{}，失败条数：{}", new Object[] { e1.getMessage(), failCount }, e1);
							}
						}
					}
				} else if ("2".equals(ydjFlag)) {// 标准卡
					for (int i = 0; i < ordrList.size(); i++) {
						try {
							appId = ordrList.get(i);
							map.put("appId", appId);
							// 查询分件表 判断是否适合再调工作流
							AllotApply allotApply = allotApplyDao.selectSingleByAppId(map);
							log.info(appId + "该申请件 审批批量处理现调工作流中................");
							// 记录历史
							allotApplyHisDao.insertCopyOpasAllotToAllotHis(map);
							// 手动将分配表申请件归档
							allotApply.setAppId(appId);
							allotApply.setCurrNode("04");
							allotApply.setBatchApprovalFlag("1");
							allotApply.setDelStatus("1");
							allotApply.setSynFlag("0");
							allotApplyDao.updateAllotApply(allotApply);
							// (调工作流的申请件)记录生命周期
							saveAppLifeCicle(map);
							// 审批历史表 审批表
							map.put("userCode", userCode);
							map.put("userName", userName);
							map.put("matchingCardFlag", allotApply.getMatchingCardFlag());
							saveBizApproval(appId, "2", map);
							// 需要往 征信电核注记信息表[OPAS_TELCHECK_ADDNOTE] 落1条标识
							TelcheckAddnote zhukaNote = new TelcheckAddnote();
							Date nowDate = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String str = sdf.format(nowDate);
							zhukaNote.setAppId(appId.trim());
							zhukaNote.setAutoId(UUIDGenerator.getUUID());
							String zhukaMemo = "批量审批：操作员(组长)" + userName + ",时间：" + str + "批量审批-拒绝码：" + refuseCode1
									+ ",备注信息：" + memo;
							zhukaNote.setBigMemo(zhukaMemo.trim());
							zhukaNote.setCrtUser(userCode);
							zhukaNote.setCrtDate(nowDate);
							zhukaNote.setTypeFlag("3");
							telcheckAddnoteDao.insertPhone(zhukaNote);
							msg = saveNewUrlClient(map, client);
							if ("1".equals(msg)) {
								result++;
							}
							log.info(appId + "调用工作流,返回信息" + msg);
						} catch (Exception e) {
							log.info(appId + "批量提交异常,异常原因--" + e.getMessage());
							int failCount = 0;
							try {
								failCount = allotBatchFailDao.selectCount(map);
								if (failCount > 0) {
									map.put("status", "0");
									allotBatchFailDao.updateFailApply(map);
								} else {
									allotBatchFailDao.insertFailApply(map);
								}
							} catch (CoreException e1) {
								log.error("批量更新或新增异常：{}，失败条数：{}", new Object[] { e1.getMessage(), failCount }, e1);
							}
						}
					}
				}
			}
		}
		if (client != null) {
			client.close();
			log.info("工作流关闭成功！");
		}
		return result;
	}

	// 插入 审批历史记录表
	public void saveBizApproval(String appId, String flag, Map streamMap) throws Exception {
		Date sysDeciTime = new Date();
		String decisionResult = "", decisionDesp = "", gjm = "", refuseCode1 = "";
		String RATE_NAME = "", REPAY_RATIO_VALUE = "", REPAY_FREE_VALUE = "";
		FicoSdBlaze fico_bzk = null;
		FicoYdjBlaze fico_ydj = null;
		// 根据appId获得征信电核结果信息，获得过件码
		TelcheckResult result = bizApprovalDao.selectTelcheckResultByAppId(appId);
		if (result != null) {
			gjm = result.getBlockCode();
		}
		String ydjFlag = (String) streamMap.get("ydjFlag") != null ? (String) streamMap.get("ydjFlag") : "";
		log.info(appId + "该申请件标识ydjFlag=" + ydjFlag);
		String matchingCardFlag = (String) streamMap.get("matchingCardFlag") != null
				? (String) streamMap.get("matchingCardFlag") : "";
		log.info(appId + "该申请件标识matchingCardFlag=" + matchingCardFlag);
		String memo = (String) streamMap.get("memo") != null ? (String) streamMap.get("memo") : "";
		// 易达金套卡统一落D700
		refuseCode1 = (String) streamMap.get("refuseCode1") != null ? (String) streamMap.get("refuseCode1") : "";
		BizInpApplC1 appl = bizInpApplC1Dao.selectBizInpApplC1ByAppId(appId);
		if ("2".equals(flag)) {// 标准卡及易达金套卡
			fico_bzk = (FicoSdBlaze) ficoSdBlazeDao.selectSystemDecisionByAppId(appId);
			if (fico_bzk != null) {
				sysDeciTime = fico_bzk.getCrtTime();
				decisionResult = fico_bzk.getDecisionResult();
				decisionDesp = fico_bzk.getDecisionDesp();
				RATE_NAME = fico_bzk.getRateName();
				REPAY_RATIO_VALUE = fico_bzk.getRepayRationValue();
				REPAY_FREE_VALUE = fico_bzk.getRepayFreeValue();
			}
		} else {// 易达金卡
			fico_ydj = (FicoYdjBlaze) ficoYdjBlazeDao.selectSystemDecisionByAppId(appId);
			if (fico_ydj != null) {
				sysDeciTime = fico_ydj.getCrtTime();
				decisionResult = fico_ydj.getDecisionResult();
				decisionDesp = fico_ydj.getDecisionDesp();
				RATE_NAME = fico_ydj.getRateName();
				REPAY_RATIO_VALUE = fico_ydj.getRepayRationValue();
				REPAY_FREE_VALUE = fico_ydj.getRepayFreeValue();
			}
		}
		BizApproval bizApproval = new BizApproval();
		bizApproval.setAutoId(StringUtil.randomUUIDPlainString());// 主键
		bizApproval.setAppId(appId);// 流水号
		bizApproval.setIdType(appl.getC1Idtype());// 证件类型
		bizApproval.setIdNo(appl.getC1Idnbr());// 证件号
		bizApproval.setApplyerName(appl.getC1Cname());// 客户姓名
		bizApproval.setApplyCard(appl.getAppProd());// 客户申请卡
		bizApproval.setSysDecisionTime(sysDeciTime);// 系统决策时间
		bizApproval.setMastercardDecresult(decisionResult);// 主卡决策结果
		bizApproval.setMastercardApprovereuslt(decisionDesp);// 主卡审批结果描述
		bizApproval.setSysCreditProduce("");// 系统授信产品
		bizApproval.setChildcardDecresult("");// 附属卡决策结果
		bizApproval.setChildcardApprovereuslt("");// 附属卡审批结果描述
		bizApproval.setCreditResult(gjm);// 征信结果[过件码]
		bizApproval.setCreditRefuseReason("3");// 征信拒绝原因 存放backFlag
		bizApproval.setApproveResult("0");// 审批结论
		bizApproval.setMemo(memo);// 审批批量提交备注
		// 易达金套卡拒绝码统一落D700
		if ("1".equals(ydjFlag) && "2".equals(flag)) {
			bizApproval.setRefuseCode1("D700");
		} else {
			bizApproval.setRefuseCode1(refuseCode1);
		}
		bizApproval.setApprover(streamMap.get("userCode") == null ? "" : streamMap.get("userCode").toString());// 审批员登录名
		bizApproval.setApproverName(streamMap.get("userName") == null ? "" : streamMap.get("userName").toString());// 审批员姓名
		bizApproval.setYdjFlag(flag);// 易达金标识
		bizApproval.setSubmitHighlevleTime(new Date());// 提交时间
		bizApproval.setApproveTime(new Date());// 审批时间
		bizApproval.setRateValue(RATE_NAME);// 利率差异化
		bizApproval.setRepayRationValue(REPAY_RATIO_VALUE);// 最低还款额
		bizApproval.setRepayFreeValue(REPAY_FREE_VALUE);// 免息还款期
		bizApproval.setInitSaveFlag("1");
		// 先查询审批表 存在该申请件 更新 不存在 插入
		BizApproval app = bizApprovalDao.selectByAppId(appId);
		if (app == null) {
			bizApprovalDao.insertSelective(bizApproval);
		} else {
			bizApproval.setAutoId(app.getAutoId());
			bizApprovalDao.updateByPrimaryKeySelective(bizApproval);
		}
		BizApprovalHis bizApprovalHis = copyApprovalRes(bizApproval);
		bizApprovalHisDao.insertSelective(bizApprovalHis);
		// 如果是标准卡套卡 入审批表值 wdb
		String appId_fk = "";
		if ("2".equals(ydjFlag) && "2".equals(matchingCardFlag)) {// 标准卡有套卡
																	// 落相同的值
			if (appId.substring(15, 16).endsWith("1")) {
				appId_fk = appId.substring(0, 15) + "2";
			} else if (appId.substring(15, 16).endsWith("2")) {
				appId_fk = appId.substring(0, 15) + "1";
			}
			BizApproval bizApproval_fk = bizApproval;
			bizApproval_fk.setAutoId(StringUtil.randomUUIDPlainString());
			bizApproval_fk.setAppId(appId_fk);
			BizApproval app_fk = bizApprovalDao.selectByAppId(appId_fk);
			if (app_fk == null) {
				bizApprovalDao.insertSelective(bizApproval_fk);
			} else {
				bizApproval_fk.setAutoId(app_fk.getAutoId());
				bizApprovalDao.updateByPrimaryKeySelective(bizApproval_fk);
			}
			BizApprovalHis bizApprovalHis_fk = copyApprovalRes(bizApproval_fk);
			bizApprovalHisDao.insertSelective(bizApprovalHis_fk);
			log.info(appId + "该标准卡存在套卡 ,批量提交时需要为套卡入审批表值。。。。");
		}
	}

	public BizApprovalHis copyApprovalRes(BizApproval bizApproval) {
		BizApprovalHis bizApprovalHis = new BizApprovalHis();

		bizApprovalHis.setAutoId(StringUtil.randomUUIDPlainString());
		bizApprovalHis.setAppId(bizApproval.getAppId());
		bizApprovalHis.setRefuseChildcard(bizApproval.getRefuseChildcard());// 拒绝附属卡
		bizApprovalHis.setPreApprovelimit(bizApproval.getPreApprovelimit());// 预审批额度
		bizApprovalHis.setApplyerName(bizApproval.getApplyerName());
		bizApprovalHis.setApplyCard(bizApproval.getApplyCard());
		bizApprovalHis.setIsAgreeDegrade(bizApproval.getIsAgreeDegrade());
		bizApprovalHis.setSysDecisionTime(bizApproval.getSysDecisionTime());// 系统决策时间
		bizApprovalHis.setMastercardDecresult(bizApproval.getMastercardDecresult());
		bizApprovalHis.setMastercardApprovereuslt(bizApproval.getMastercardApprovereuslt());
		bizApprovalHis.setSysCreditProduce(bizApproval.getSysCreditProduce());
		bizApprovalHis.setChildcardDecresult(bizApproval.getChildcardDecresult());// 附属卡决策结果
		bizApprovalHis.setChildcardApprovereuslt(bizApproval.getChildcardApprovereuslt());// 附属卡审批结果描述
		bizApprovalHis.setCreditResult(bizApproval.getCreditResult());// 征信结果[过件码]
		bizApprovalHis.setCreditRefuseReason(bizApproval.getCreditRefuseReason());// 存放backFlag
		bizApprovalHis.setWarninglistResult(bizApproval.getWarninglistResult());// WARNINGLIST结果
		bizApprovalHis.setApproveResult(bizApproval.getApproveResult());// 审批结论
		bizApprovalHis.setApproveCreditLimit(bizApproval.getApproveCreditLimit());
		bizApprovalHis.setApproveCreditProduct(bizApproval.getApproveCreditProduct());
		bizApprovalHis.setManualLimit(bizApproval.getManualLimit());// 调整额度
		bizApprovalHis.setPolicyCode1(bizApproval.getPolicyCode1());
		bizApprovalHis.setPolicyCode2(bizApproval.getPolicyCode2());
		bizApprovalHis.setPolicyCode3(bizApproval.getPolicyCode3());
		bizApprovalHis.setViolateCode1(bizApproval.getViolateCode1());
		bizApprovalHis.setViolateCode2(bizApproval.getViolateCode2());
		bizApprovalHis.setViolateCode3(bizApproval.getViolateCode3());
		bizApprovalHis.setMemo(bizApproval.getMemo());
		bizApprovalHis.setRefuseCode1(bizApproval.getRefuseCode1());
		bizApprovalHis.setRefuseCode2(bizApproval.getRefuseCode2());
		bizApprovalHis.setRefuseCode3(bizApproval.getRefuseCode3());
		bizApprovalHis.setApprover(bizApproval.getApprover());
		bizApprovalHis.setApproverName(bizApproval.getApproverName());
		bizApprovalHis.setSubmitHighlevleTime(bizApproval.getSubmitHighlevleTime());
		bizApprovalHis.setApproveTime(bizApproval.getApproveTime());
		bizApprovalHis.setCurrOptGroup(bizApproval.getCurrOptGroup());// 当前操作组
		bizApprovalHis.setIdType(bizApproval.getIdType());
		bizApprovalHis.setIdNo(bizApproval.getIdNo());
		bizApprovalHis.setYdjFlag(bizApproval.getYdjFlag());/* 1为易达金 2标准卡 */
		bizApprovalHis.setYearFeeDerateType(bizApproval.getYearFeeDerateType());
		bizApprovalHis.setCardFaceCode(bizApproval.getCardFaceCode());
		return bizApprovalHis;
	}

	// end
	// 多条件 start
	@Override
	public Map<String, Object> saveReviewPoolByCondition(Map<String, Object> map) throws CoreException {
		log.info("AllotApplyServiceImpl.saveReviewPoolByCondition 查询符合筛选条件的申请件   enter ");
		// 初始时间
		Map<String, Object> allMap = new HashMap<String, Object>();
		String ydjFlag = (String) map.get("ydjFlag") == null ? "" : (String) map.get("ydjFlag");
		if ("2".equals(ydjFlag)) {
			allMap = saveApplyByConditions(map);
		} else {
			String currNode = (String) map.get("currNode") == null ? "" : (String) map.get("currNode");
			String secondNode = (String) map.get("secondNode") == null ? "" : (String) map.get("secondNode");
			String userCode = (String) map.get("userCode") == null ? "" : (String) map.get("userCode");
			String strType = (String) map.get("strType") == null ? "" : (String) map.get("strType");
			String shareWay = (String) map.get("shareWay") == null ? "" : (String) map.get("shareWay");
			String currStatusFlag = (String) map.get("currStatusFlag") == null ? ""
					: (String) map.get("currStatusFlag");
			String submitStr = "";
			if (!"05".equals(currNode)) {// 提报状态
				submitStr = "submit";
			}

			String rcdSrc = (String) map.get("rcdSrc") == null ? "" : (String) map.get("rcdSrc");
			if (!"".equals(rcdSrc)) {
				List<String> rcdSrcList = new ArrayList<String>();
				String[] arr = rcdSrc.split(",");
				rcdSrcList = Arrays.asList(arr);
				map.put("rcdSrcList", rcdSrcList);
			}
			String platType = (String) map.get("platType") == null ? "" : (String) map.get("platType");
			if (!"".equals(platType)) {
				List<String> platTypeList = new ArrayList<String>();
				String[] arr = platType.split(",");
				platTypeList = Arrays.asList(arr);
				map.put("platTypeList", platTypeList);
			}
			String coprType = (String) map.get("coprType") == null ? "" : (String) map.get("coprType");
			if (!"".equals(coprType)) {
				List<String> coprTypeList = new ArrayList<String>();
				String[] arr = coprType.split(",");
				coprTypeList = Arrays.asList(arr);
				map.put("coprTypeList", coprTypeList);
			}
			String platRiskLvl = (String) map.get("platRiskLvl") == null ? "" : (String) map.get("platRiskLvl");
			if (!"".equals(platRiskLvl)) {
				List<String> platRiskLvlList = new ArrayList<String>();
				String[] arr = platRiskLvl.split(",");
				platRiskLvlList = Arrays.asList(arr);
				map.put("platRiskLvlList", platRiskLvlList);
			}
			if (!"".equals(rcdSrc) || !"".equals(platType) || !"".equals(coprType) || !"".equals(platRiskLvl)) {
				map.put("ditchStr", "ditchStr");
			}
			map.put("submitStr", submitStr);
			String serialNum = (String) map.get("serialNum") == null ? "" : (String) map.get("serialNum");
			String appRegion = (String) map.get("appRegion") == null ? "" : (String) map.get("appRegion");
			String appChannel = (String) map.get("appChannel") == null ? "" : (String) map.get("appChannel");
			String appInput = (String) map.get("appInput") == null ? "" : (String) map.get("appInput");
			String appProd = (String) map.get("appProd") == null ? "" : (String) map.get("appProd");
			String shareTime = (String) map.get("shareTime") == null ? "" : (String) map.get("shareTime");
			String crtTime = (String) map.get("crtTime") == null ? "" : (String) map.get("crtTime");
			String appTime = (String) map.get("appTime") == null ? "" : (String) map.get("appTime");
			String appStatus = (String) map.get("appStatus") == null ? "" : (String) map.get("appStatus");
			String appStatus2 = (String) map.get("appStatus2") == null ? "" : (String) map.get("appStatus2");
			String quickCardFlag = (String) map.get("quickCardFlag") == null ? "" : (String) map.get("quickCardFlag");
			String nosafePromoter = (String) map.get("nosafePromoter") == null ? ""
					: (String) map.get("nosafePromoter");
			String creditDecisionLayer = (String) map.get("creditDecisionLayer") == null ? ""
					: (String) map.get("creditDecisionLayer");
			String creditDecisionResult = (String) map.get("creditDecisionResult") == null ? ""
					: (String) map.get("creditDecisionResult");
			String minNumberRead = (String) map.get("minNumberRead") == null ? "" : (String) map.get("minNumberRead");
			String maxNumberRead = (String) map.get("maxNumberRead") == null ? "" : (String) map.get("maxNumberRead");
			String isHaveCardCust = (String) map.get("isHaveCardCust") == null ? ""
					: (String) map.get("isHaveCardCust");
			String pubPolice = (String) map.get("pubPolice") == null ? "" : (String) map.get("pubPolice");
			String minProposedLmt = (String) map.get("minProposedLmt") == null ? ""
					: (String) map.get("minProposedLmt");
			String maxProposedLmt = (String) map.get("maxProposedLmt") == null ? ""
					: (String) map.get("maxProposedLmt");
			String allotType = ((String) map.get("allotType")) == null ? "" : (String) map.get("allotType");
			String applyId = ((String) map.get("appId")) == null ? "" : ((String) map.get("appId")).trim();
			String pbocRstFinDesp = ((String) map.get("pbocRstFinDesp")) == null ? ""
					: (String) map.get("pbocRstFinDesp");
			String c4ApBatchStr = (String) map.get("c4ApBatch") == null ? "" : (String) map.get("c4ApBatch");
			String c5AbCodeStr = (String) map.get("c5AbCode") == null ? "" : (String) map.get("c5AbCode");
			String c4AbUser = (String) map.get("c4AbUser") == null ? "" : (String) map.get("c4AbUser");
			// 批量标识 审查时无法查询 征信查询 审查批量过来的件 审批查询 征信批量过来的件
			String batchLabel = (String) map.get("batchLabel") == null ? "" : (String) map.get("batchLabel");
			// 质检叫停
			String stopStatus = (String) map.get("stopStatus") == null ? "" : (String) map.get("stopStatus");
			// 天御分
			String minRiskScore = (String) map.get("minRiskScore") == null ? "" : (String) map.get("minRiskScore");
			String maxRiskScore = (String) map.get("maxRiskScore") == null ? "" : (String) map.get("maxRiskScore");
			// 反欺诈决策结果
			String fqzValue = (String) map.get("fqzValue") == null ? "" : (String) map.get("fqzValue");
			// 天御风险码及等级
			String riskCode = (String) map.get("riskCode") == null ? "" : (String) map.get("riskCode");
			String riskLevel = (String) map.get("riskLevel") == null ? "" : (String) map.get("riskLevel");
			// 过件码1
			String blockCode1 = (String) map.get("blockCode1") == null ? "" : (String) map.get("blockCode1");
			// 过件码2
			String blockCode2 = (String) map.get("blockCode2") == null ? "" : (String) map.get("blockCode2");
			// 征信评分
			String triggerType1 = (String) map.get("triggerType1") == null ? "" : (String) map.get("triggerType1");
			String triggerType2 = (String) map.get("triggerType2") == null ? "" : (String) map.get("triggerType2");
			// 流水号字母
			if (!"".equals(serialNum)) {
				List<String> serialNumList = new ArrayList<String>();
				String[] arr = serialNum.split(",");
				serialNumList = Arrays.asList(arr);
				map.put("serialNumList", serialNumList);
			}
			// 展业地区
			if (!"".equals(appRegion)) {
				List<String> appRegionList = new ArrayList<String>();
				String[] arr = appRegion.split(",");
				appRegionList = Arrays.asList(arr);
				map.put("appRegionList", appRegionList);
			}
			// 渠道
			if (!"".equals(appChannel)) {
				List<String> appChannelList = new ArrayList<String>();
				String[] arr = appChannel.split(",");
				appChannelList = Arrays.asList(arr);
				map.put("appChannelList", appChannelList);
			}
			// 录入商
			String inputStr = "";
			if (appInput.indexOf("A") != -1 && appInput.indexOf("B") == -1 && appInput.indexOf("1") == -1) {
				inputStr = "AND substr(allot.APP_ID,7,1) = 'A' ";
			} else if (appInput.indexOf("A") == -1 && appInput.indexOf("B") != -1 && appInput.indexOf("1") == -1) {
				inputStr = "AND substr(allot.APP_ID,7,1) = 'B' ";
			} else if (appInput.indexOf("A") == -1 && appInput.indexOf("B") == -1 && appInput.indexOf("1") != -1) {
				inputStr = "AND substr(allot.APP_ID,7,1) not in ('A','B') ";
			} else if (appInput.indexOf("A") != -1 && appInput.indexOf("B") != -1 && appInput.indexOf("1") == -1) {
				inputStr = "AND substr(allot.APP_ID,7,1)  in ('A','B') ";
			} else if (appInput.indexOf("A") != -1 && appInput.indexOf("B") == -1 && appInput.indexOf("1") != -1) {
				inputStr = "AND (substr(allot.APP_ID,7,1) = 'A' or substr(allot.APP_ID,7,1) not in ('A','B')) ";
			} else if (appInput.indexOf("A") == -1 && appInput.indexOf("B") != -1 && appInput.indexOf("1") != -1) {
				inputStr = "AND (substr(allot.APP_ID,7,1) = 'B' or substr(allot.APP_ID,7,1) not in ('A','B')) ";
			}
			map.put("inputStr", inputStr);
			// 卡产品类型
			if (!"".equals(appProd)) {
				List<String> appProdList = new ArrayList<String>();
				String[] arr = appProd.split(",");
				appProdList = Arrays.asList(arr);
				map.put("appProdList", appProdList);
			}
			// 批量提交
			String batchStr = "";
			if ("1".equals(batchLabel)) {
				if ("02".equals(currNode) || "05".equals(currNode)) {
					batchStr = "AND allot.BATCH_OPERATE_FLAG = '1' ";
				} else if ("03".equals(currNode)) {
					batchStr = "AND allot.BATCH_CREDIT_FLAG ='1' ";
				}
			}
			map.put("batchStr", batchStr);
			// 标识
			String identify = (String) map.get("identify") == null ? "" : (String) map.get("identify");
			String quickStr = "";
			if (!"1".equals(identify) && !"2".equals(identify) && !"3".equals(identify)) {// 未取值
				// 已持卡客户
				isHaveCardCust = "";
				// 快速申请
				quickCardFlag = "";
				// 公安链接
				pubPolice = "";
			} else if ("1".equals(identify)) {
				// 快速申请
				quickCardFlag = "";
				// 公安链接
				pubPolice = "";
			} else if ("2".equals(identify)) {
				// 已持卡客户
				isHaveCardCust = "";
				// 公安链接
				pubPolice = "";
				// 快速发卡标志 0正常 1快速
				if ("0".equals(quickCardFlag)) {
					quickStr = "AND (ac.QUICK_CARD_FLAG = '0' or ac.QUICK_CARD_FLAG is null)";
				} else if ("1".equals(quickCardFlag)) {
					quickStr = "AND ac.QUICK_CARD_FLAG in ('1','2') ";
				}
			} else if ("3".equals(identify)) {
				// 已持卡客户
				isHaveCardCust = "";
				// 快速申请
				quickCardFlag = "";
			}
			map.put("quickStr", quickStr);
			// 申请件编号
			String appId = "";
			if (!"".equals(applyId) && null != applyId) {
				appId = applyId.toUpperCase();
				map.put("appId", appId);
			}
			// 易达金非捞件的申请件 只看易达金条件(如果applyId不为空时 失效)
			String ydjStr = "";
			if (("1".equals(ydjFlag) || "2".equals(ydjFlag) || "".equals(ydjFlag))
					&& ("".equals(applyId) || null == applyId)) {
				ydjStr = "AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02' or allot.DEL_STATUS='3')";
			}
			map.put("ydjStr", ydjStr);
			// 时间
			if ("1".equals(shareTime)) {// 条形码日期,将进件日期致空
				crtTime = "";
				map.put("crtTime", crtTime);
			} else if ("2".equals(shareTime)) {// 进件日期,将条形码日期致空
				appTime = "";
				map.put("appTime", appTime);
			} else if ("".equals(shareTime) || shareTime == null) {
				map.put("crtTime", "");
				appTime = "";
				map.put("appTime", appTime);
			}
			if (!"".equals(appTime) && appTime != null && !"-1".equals(appTime)) {
				String appDate = (appTime.replace("-", "")).substring(2, appTime.replace("-", "").length());
				map.put("appTime", appDate);
			}
			// 排序
			String checkStr = "";
			if (!"".equals(allotType) && allotType != null) {
				if ("1".equals(allotType)) {
					checkStr = "ac.CRT_TIME asc,allot.APP_ID asc,substr(allot.APP_ID,16,1) desc";
				} else if ("2".equals(allotType)) {
					checkStr = "ac.CRT_TIME desc,allot.APP_ID asc,substr(allot.APP_ID,16,1) desc";
				} else if ("3".equals(allotType)) {
					checkStr = "NLSSORT(translate(lower(ac.C1_CONAME),'abcdefghijklmnopqrstuywxyz','啊八嚓大额发噶哈几卡拉吗呐哦扒七然仨他哇西呀杂'),'NLS_SORT=SCHINESE_PINYIN_M') asc,allot.APP_ID asc,substr(allot.APP_ID,16,1) desc";
				} else if ("4".equals(allotType)) {
					checkStr = "NLSSORT(translate(lower(ac.C1_CONAME),'abcdefghijklmnopqrstuywxyz','啊八嚓大额发噶哈几卡拉吗呐哦扒七然仨他哇西呀杂'),'NLS_SORT=SCHINESE_PINYIN_M') desc,allot.APP_ID asc,substr(allot.APP_ID,16,1) desc";
				} else if ("5".equals(allotType)) {
					checkStr = "allot.APP_ID asc,substr(allot.APP_ID,16,1) desc";
				} else if ("6".equals(allotType)) {
					checkStr = "allot.APP_ID desc,substr(allot.APP_ID,16,1) desc";
				}
			}
			map.put("checkStr", checkStr);
			// 分配状态 (排除转移 回收 和当日申请件查询currStatusList赋值)
			if ("".equals(currStatusFlag)) {
				List<String> currStatusList = new ArrayList<String>();
				if ("1".equals(secondNode)) {
					if ((appStatus != null && !"".equals(appStatus) && !"-1".equals(appStatus))
							|| ("24".equals(appStatus2))) {
						if ("1".equals(appStatus) && "24".equals(appStatus2)) {// 池中待分配已挂起
							currStatusList.add("5");
						} else if ("1".equals(appStatus) && !"24".equals(appStatus2)) {// 池中待分配未挂起
							currStatusList.add("0");
						} else if ("2".equals(appStatus) && !"24".equals(appStatus2)) {// 池中已分配显示
																						// 到队列
																						// 到组或人的
																						// 已分配未挂起
							currStatusList.add("1");
							currStatusList.add("3");
							currStatusList.add("6");
						} else if ("2".equals(appStatus) && "24".equals(appStatus2)) {// 到
																						// 队列
																						// 、组或人
																						// 已分配
																						// 已挂起
							currStatusList.add("2");
							currStatusList.add("4");
							currStatusList.add("7");
						} else if ((appStatus == null || "".equals(appStatus) || "-1".equals(appStatus))
								&& ("24".equals(appStatus2))) {// 挂起标签(显示池中 或队列中
																// 组中 挂起件)
							currStatusList.add("5");
							currStatusList.add("2");
							currStatusList.add("7");
						} else if ("3".equals(appStatus)) {
							currStatusList.add("5");
						}
					} else {// 默认待分配 (更改 未挂起和挂起都可以分配)
						currStatusList.add("0");
					}
				} else if ("2".equals(secondNode)) {
					if ((appStatus != null && !"".equals(appStatus) && !"-1".equals(appStatus))
							|| ("24".equals(appStatus2))) {
						if ("1".equals(appStatus) && "24".equals(appStatus2)) {// 队列中
																				// 待分配已挂起
							currStatusList.add("7");
						} else if ("1".equals(appStatus) && !"24".equals(appStatus2)) {// 队列中待分配未挂起
							currStatusList.add("6");
						} else if ("2".equals(appStatus) && !"24".equals(appStatus2)) {// 队列中已分配到组
																						// 或人的
																						// 已分配未挂起
							currStatusList.add("1");
							currStatusList.add("3");
						} else if ("2".equals(appStatus) && "24".equals(appStatus2)) {// 到组或人
																						// 已分配
																						// 已挂起
							currStatusList.add("2");
							currStatusList.add("4");
						} else if ((appStatus == null || "".equals(appStatus) || "-1".equals(appStatus))
								&& ("24".equals(appStatus2))) {// 挂起标签(显示队列中 组中
																// 挂起件)
							currStatusList.add("7");
							currStatusList.add("2");
						} else if ("3".equals(appStatus)) {
							currStatusList.add("7");
						}
					} else {// 待分配（更改 未挂起和挂起都可以分配）
						currStatusList.add("6");
					}
				} else if ("3".equals(secondNode)) {
					if ((appStatus != null && !"".equals(appStatus) && !"-1".equals(appStatus))
							|| ("24".equals(appStatus2))) {
						if ("1".equals(appStatus) && "24".equals(appStatus2)) {// 组中
																				// 待分配已挂起
							currStatusList.add("2");
						} else if ("1".equals(appStatus) && !"24".equals(appStatus2)) {// 组中待分配未挂起
							currStatusList.add("1");
						} else if ("2".equals(appStatus) && !"24".equals(appStatus2)) {// 已分配未挂起
							currStatusList.add("3");
						} else if ("2".equals(appStatus) && "24".equals(appStatus2)) {// 已分配
																						// 已挂起
							currStatusList.add("4");
						} else if ((appStatus == null || "".equals(appStatus) || "-1".equals(appStatus))
								&& ("24".equals(appStatus2))) {// 挂起标签(显示 组中 个人
																// 挂起件)
							currStatusList.add("2");
							currStatusList.add("4");
						} else if ("3".equals(appStatus)) {
							currStatusList.add("2");
							currStatusList.add("4");
						}
					} else {// 待分配（更改 未挂起和挂起都可以分配）
						currStatusList.add("1");
					}
					if (!"system".equals(userCode) && !"".equals(userCode)) {
						AllotCommon allotCommon = allotCommonDao.selectGroupByUserCode(userCode);
						String currGroup = allotCommon.getOrgNo();
						map.put("currGroup", currGroup);
					}
				}
				map.put("currStatusList", currStatusList);
			}
			// 是否持卡客户
			String cardStr = "";
			if (!"".equals(isHaveCardCust) && isHaveCardCust != null && !"-1".equals(isHaveCardCust)) {
				if ("0".equals(isHaveCardCust)) {// 不持卡
					cardStr = "AND (ac.EXIST_CARD_FLAG ='0' or ac.EXIST_CARD_FLAG is null )";
				} else if ("1".equals(isHaveCardCust)) {// 已持卡
					cardStr = "AND ac.EXIST_CARD_FLAG ='1' ";
				} else if ("2".equals(isHaveCardCust)) {// 排查
					cardStr = "AND ac.EXIST_CARD_FLAG ='2' ";
				}
			}
			map.put("cardStr", cardStr);
			// 异常进件渠道
			String safeStr = "";
			if ("0".equals(nosafePromoter)) {// 否 不是 不安全推广人员件
				safeStr = "and (promoter.promoter_no is null or promoter.CURR_STATUS='0' or promoter.CURR_STATUS is null ) ";
			} else if ("1".equals(nosafePromoter)) {// 是 不安全推广人员件
				safeStr = "and  promoter.CURR_STATUS='1' ";
			}
			map.put("safeStr", safeStr);
			// 公安链接状态-wenyh
			String pubStr = "";
			if ("0".equals(pubPolice)) {
				pubStr = "AND (compare.RESULT_GMSFHM = '不一致' or compare.RESULT_GMSFHM is null or compare.RESULT_XM= '不一致' or compare.RESULT_XM is null)";
			} else if ("1".equals(pubPolice)) {
				pubStr = "AND (compare.RESULT_GMSFHM = '一致' and compare.RESULT_XM='一致')";
			}
			map.put("pubStr", pubStr);
			// 团办号
			if (!"".equals(c4ApBatchStr)) {
				List<String> c4ApBatchList = new ArrayList<String>();
				String[] arr = c4ApBatchStr.split(",");
				c4ApBatchList = Arrays.asList(arr);
				map.put("c4ApBatchList", c4ApBatchList);
			}
			// 推广机构号
			if (!"".equals(c5AbCodeStr)) {
				List<String> c5AbCodeList = new ArrayList<String>();
				String[] arr = c5AbCodeStr.split(",");
				c5AbCodeList = Arrays.asList(arr);
				map.put("c5AbCodeList", c5AbCodeList);
			}
			// 推广人
			if (!"".equals(c4AbUser)) {
				List<String> c4AbUserList = new ArrayList<String>();
				String[] arr = c4AbUser.split(",");
				c4AbUserList = Arrays.asList(arr);
				map.put("c4AbUserList", c4AbUserList);
			}
			// 质检叫停 2 不能分件
			String stopStr = "";
			if ("1".equals(stopStatus)) {
				stopStr = "AND info.STOP_FLAG ='2' ";
			} else {
				stopStr = "AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
			}
			map.put("stopStr", stopStr);
			// 天御分 '0~100：欺诈分值。值越高欺诈可能性越大' 有-1可能
			String riskScore = "";
			if ((!"".equals(minRiskScore)) && (!"".equals(maxRiskScore))) {// 都不为空
				int min = Integer.valueOf(minRiskScore);
				int max = Integer.valueOf(maxRiskScore);
				// 两种情况 1、最小值大于最大值 查询为空的情况
				if (min > max) {
					riskScore = "1";
				} else {// 2、最大值大于最小值
					riskScore = "2";
				}
			} else if (("".equals(minRiskScore)) && (!"".equals(maxRiskScore))) {// 小于最大值
				riskScore = "3";
			} else if ((!"".equals(minRiskScore)) && ("".equals(maxRiskScore))) {// 大于最小值
				riskScore = "4";
			}
			map.put("riskScore", riskScore);
			// 反欺诈决策结果
			if (!"".equals(fqzValue)) {
				List<String> fqzValueList = new ArrayList<String>();
				String[] arr = fqzValue.split(",");
				fqzValueList = Arrays.asList(arr);
				map.put("fqzValueList", fqzValueList);
			}
			// 天御风险码及等级 有一个不为零时查天御风险表
			if (!"".equals(riskCode) || !"".equals(riskLevel)) {
				if (!"".equals(riskCode)) {
					List<String> riskcodeList = new ArrayList<String>();
					String[] arr = riskCode.split(",");
					riskcodeList = Arrays.asList(arr);
					map.put("riskcodeList", riskcodeList);
				}
				if (!"".equals(riskLevel)) {
					List<String> riskValueList = new ArrayList<String>();
					String[] arr = riskLevel.split(",");
					riskValueList = Arrays.asList(arr);
					map.put("riskValueList", riskValueList);
				}
				map.put("tianyu", "tianyu");
			}
			// 过件码1
			if (!"".equals(blockCode1)) {
				List<String> creditList = new ArrayList<String>();
				String[] arr = blockCode1.split(",");
				creditList = Arrays.asList(arr);
				map.put("creditList", creditList);
			}
			// 过件码2
			if (!"".equals(blockCode2)) {
				List<String> blockList = new ArrayList<String>();
				String[] arr = blockCode2.split(",");
				blockList = Arrays.asList(arr);
				map.put("blockList", blockList);
			}
			// 征信评分
			String triggerType = "";
			if ((!"".equals(triggerType1)) && (!"".equals(triggerType2))) {// 都不为空
				int min = Integer.valueOf(triggerType1);
				int max = Integer.valueOf(triggerType2);
				// 两种情况 1、最小值大于最大值 查询为空的情况
				if (min > max) {
					triggerType = "1";
				} else {// 2、最大值大于最小值
					triggerType = "2";
				}
			} else if (("".equals(triggerType1)) && (!"".equals(triggerType2))) {// 小于最大值
				triggerType = "3";
			} else if ((!"".equals(triggerType1)) && ("".equals(triggerType2))) {// 大于最小值
				triggerType = "4";
			}
			map.put("triggerType", triggerType);
			// 多选且包含未发起查询的申请件 特殊处理
			String onlineTime = (String) map.get("onlineTime") == null ? "" : (String) map.get("onlineTime");// 在网时长
			if (!"".equals(onlineTime)) {// 在网时长
				map.put("onlineTimeList", Arrays.asList(onlineTime.split(",")));
				if (onlineTime.contains("W")) {// 包含未发起查询
					map.put("onlineTimeStr", "1");
				} else {// 不包含
					map.put("onlineTimeStr", "2");
				}
			}
			String phoneCertification = (String) map.get("phoneCertification") == null ? ""
					: (String) map.get("phoneCertification");// 手机实名制
			if (!"".equals(phoneCertification)) {
				map.put("phoneList", Arrays.asList(phoneCertification.split(",")));
			}
			// shareWay 1 易达金84、85卡自动 2 易达金非84、85自动 3 其它情况
			List<String> list = new ArrayList<String>();
			if ("1".equals(shareWay)) {
				//80 81 82 83 84 85 主卡
				List<String> ydjTelList = allotApplyDao.selectYdjTelAppProd(map);
				if (ydjTelList.size() > 0) {
					for (int i = 0; i < ydjTelList.size(); i++) {
						String zhuKaId = ydjTelList.get(i);
						String taoKaId = null;
						if (zhuKaId.endsWith("1")) {
							taoKaId = zhuKaId.substring(0, 15) + "2";
						} else if (zhuKaId.endsWith("2")) {
							taoKaId = zhuKaId.substring(0, 15) + "1";
						} else {
							taoKaId = zhuKaId;
						}
						list.add(zhuKaId);
						list.add(taoKaId);
					}
				}
			} else if ("2".equals(shareWay)) {
				list = getAllotApplyDao().selectReviewApplication(map);
				List<String> telList = getAllotApplyDao().selectYdjTelAppProd(map);
				List<String> telAllList = new ArrayList<String>();
				if (telList.isEmpty() != true) {
					String taoKaId = null;
					for (String apId : telList) {
						if (apId.endsWith("1")) {
							taoKaId = apId.substring(0, 15) + "2";
						} else if (apId.endsWith("2")) {
							taoKaId = apId.substring(0, 15) + "1";
						} else {
							taoKaId = apId;
						}
						telAllList.add(taoKaId);
						telAllList.add(apId);
					}
				}
				list.removeAll(telAllList);
			} else {
				list = getAllotApplyDao().selectReviewApplication(map);
			}
			// 查询中征信1000评分
			if (list.size() > 0) {
				if ((!"".equals(minNumberRead) && minNumberRead != null)
						|| (!"".equals(maxNumberRead) && maxNumberRead != null)) {
					if ((!"".equals(minNumberRead) && minNumberRead != null)
							&& (!"".equals(maxNumberRead) && maxNumberRead != null)) {
						int min = Integer.valueOf(minNumberRead);
						int max = Integer.valueOf(maxNumberRead);
						int a = min - max;
						if (a > 0) {
							map.put("minNumberRead", "-2");
							map.put("maxNumberRead", "-2");
						}
					}
					list.retainAll(getAllotApplyDao().selectReviewAppByCue(map));
				}
			}
			// 解析决策额度
			if (list.size() > 0) {
				if ((!"".equals(minProposedLmt) && minProposedLmt != null && !"0.0".equals(minProposedLmt))
						|| (!"".equals(maxProposedLmt) && maxProposedLmt != null && !"0.0".equals(maxProposedLmt))
						|| (!"".equals(pbocRstFinDesp) && pbocRstFinDesp != null)) {
					// 两者都不为空时
					if ((!"".equals(minProposedLmt) && minProposedLmt != null && !"0.0".equals(minProposedLmt))
							&& (!"".equals(maxProposedLmt) && maxProposedLmt != null
									&& !"0.0".equals(maxProposedLmt))) {
						Double min = Double.valueOf(minProposedLmt);
						Double max = Double.valueOf(maxProposedLmt);
						if (min > max) {
							map.put("minProposedLmt", "-2");
							map.put("maxProposedLmt", "-2");
						}
					}
					if ("1".equals(ydjFlag) || "".equals(ydjFlag)) {
						List<String> blazeList = getAllotApplyDao().selectYdjAppByBlaze(map);
						List<String> telAllList = new ArrayList<String>();
						if (blazeList.size() > 0) {
							String taoKaId = "";
							for (String apId : blazeList) {
								taoKaId = apId.substring(0, 15) + "1";
								telAllList.add(taoKaId);
								telAllList.add(apId);
							}
						}
						list.retainAll(telAllList);
					} else if ("2".equals(ydjFlag)) {
						list.retainAll(getAllotApplyDao().selectBzkAppByBlaze(map));
					}
				}
			}
			// 征信策略结果和描述 易达金区分 主卡和套卡
			if (list.size() > 0 && !"01".equals(currNode)) {
				if ((!"".equals(creditDecisionLayer) && creditDecisionLayer != null
						&& !"-1".equals(creditDecisionLayer))
						|| (!"".equals(creditDecisionResult) && creditDecisionResult != null
								&& !"-1".equals(creditDecisionResult))) {
					if ((!"".equals(creditDecisionResult) && creditDecisionResult != null
							&& !"-1".equals(creditDecisionResult))) {
						List<String> creditResultList = new ArrayList<String>();
						String[] arr = creditDecisionResult.split(",");
						creditResultList = Arrays.asList(arr);
						map.put("creditResultList", creditResultList);
					}
					if ((!"".equals(creditDecisionLayer) && creditDecisionLayer != null
							&& !"-1".equals(creditDecisionLayer))) {
						List<String> creditLayerList = new ArrayList<String>();
						String[] arr = creditDecisionLayer.split(",");
						creditLayerList = Arrays.asList(arr);
						map.put("creditLayerList", creditLayerList);
					}
					if ("1".equals(ydjFlag) || "".equals(ydjFlag)) {
						List<String> blazeList = getAllotApplyDao().selectCreditAppByYdjDecision(map);
						List<String> telAllList = new ArrayList<String>();
						if (blazeList.size() > 0) {
							String taoKaId = "";
							for (String apId : blazeList) {
								taoKaId = apId.substring(0, 15) + "1";
								telAllList.add(taoKaId);
								telAllList.add(apId);
							}
						}
						list.retainAll(telAllList);
					} else if ("2".equals(ydjFlag)) {
						list.retainAll(getAllotApplyDao().selectCreditAppByBzkDecision(map));
					}
				}
			}
			// 批量查询
			List<AllotApply> list2 = new ArrayList<AllotApply>();
			if (list.size() > 0) {
				if ("1".equals(strType)) {// 易达金/反欺诈分件查询 前15位截取
					List<String> temList = new ArrayList<String>();
					String appNo = "";
					for (int i = 0; i < list.size(); i++) {
						AllotApply app = new AllotApply();
						appNo = list.get(i).substring(0, 15);
						if (!temList.contains(appNo)) {// 没有加入\有跳过
							temList.add(appNo);
							app.setAppNo(appNo);
							app.setYdjFlag(ydjFlag);
							list2.add(app);
						}
					}
				}
			}
			log.info("分件条件易达金和反欺诈查询--1完成!");
			allMap.put("list2", list2);
		}
		return allMap;
	}

	@Override
	public List<AllotApply> queryAppNoByOrder(Map map) throws CoreException {
		return getAllotApplyDao().selectAppNoByOrder(map);
	}

	// end
	// 批量分配
	@Override
	public int updateBatchApply(List<AllotApply> list) throws CoreException {
		return getAllotApplyDao().updateBatchApply(list);
	}

	@Override
	public int updateBatchList(AllotApply allotApply) throws CoreException {
		int m = 0;
		m = getAllotApplyDao().updateBatchList(allotApply);
		return m;
	}

	@Override
	public int updateBatchByTemp(List<AllotApply> list, List<String> lifeList) throws CoreException {
		int abc = 0;
		int m = 0;
		// 先同步到临时分配主表
		abc = getAllotApplyDao().saveBatchByTemp(list);
		if (abc != 0) {
			// 根据临时分配主表 同步更新到分配表
			m = getAllotApplyDao().updateBatchByTemp(lifeList);
			log.info("分件--根据临时分配主表 同步更新到分配表完成!");
		} else {
			throw new RuntimeException("同步临时分配主表失败");// 为了制造异常 使事物回滚;
		}

		return m;
	}

	//
	@Override
	public int saveAllotApply(AllotApply allotApply) throws CoreException {
		return getAllotApplyDao().insertAllotApply(allotApply);
	}

	// 转移 回收
	@Override
	public int updateAllotApply(AllotApply allotApply) throws CoreException {
		return getAllotApplyDao().updateAllotApply(allotApply);
	}

	@Override
	public int updateByPrimaryKey(AllotApply allotApply) throws CoreException {
		return getAllotApplyDao().updateByPrimaryKey(allotApply);
	}

	// 多表综合
	// 审查 征信 审批 环节 件数量及快速件
	@Override
	public int queryCount(Map map) throws CoreException {
		return getAllotApplyDao().selectCount(map);
	}

	@Override
	public List<AllotApply> queryAllotApplyByGroup(Map map, int currentPage, int pageSize) throws CoreException {
		return getAllotApplyDao().selectAllotApplyByGroup(map, currentPage, pageSize);
	}

	@Override
	public List<AllotApply> queryAllotApplyByGroup(Map map) throws CoreException {
		// 组中未叫停申请件 质检叫停 2 不能分件
		String stopStr = "AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
		map.put("stopStr", stopStr);
		return getAllotApplyDao().selectAllotApplyByGroup(map);
	}

	// 组员
	@Override
	public List<AllotApply> queryAllotApplyByUser(Map<String, Object> map) throws CoreException {
		String currNode = (String) map.get("currNode") == null ? "" : (String) map.get("currNode");
		String ydjFlag = (String) map.get("ydjFlag") == null ? "" : (String) map.get("ydjFlag");
		String specialMen = (String) map.get("specialMen") == null ? "" : (String) map.get("specialMen");
		String submitStr = "";
		if (!"05".equals(currNode)) {// 提报状态
			submitStr = "submit";
		}
		map.put("submitStr", submitStr);
		// 易达金非捞件的申请件 只看易达金条件(当appId不为空时 不需要该条件)
		String ydjStr = "";
		if ("1".equals(ydjFlag) || "2".equals(ydjFlag) || "".equals(ydjFlag)) {
			ydjStr = "AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02' or allot.DEL_STATUS='3' )";
		}
		map.put("ydjStr", ydjStr);
		// 组中未叫停申请件 质检叫停 2 不能分件
		String stopStr = "AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
		map.put("stopStr", stopStr);
		List<AllotApply> applyList = new ArrayList<AllotApply>();
		if ("yes".equals(specialMen)) {
			applyList = getAllotApplyDao().selectApplyBySpecialMen(map);
		} else {
			applyList = getAllotApplyDao().selectAllotApplyByUser(map);
		}
		return applyList;
	}

	// 标准卡 团办号去重
	@Override
	public List<AllotApply> queryC4ApBatch(Map<String, Object> map, int currentPage, int pageSize)
			throws CoreException {
		return getAllotApplyDao().selectC4ApBatch(map, currentPage, pageSize);
	}

	@Override
	public List<AllotApply> queryC5JCType(Map<String, Object> map, int currentPage, int pageSize) throws CoreException {
		return getAllotApplyDao().selectC5JCType(map, currentPage, pageSize);
	}

	@Override
	public int countC4ApBatch(Map<String, Object> map) throws CoreException {
		return getAllotApplyDao().countC4ApBatch(map);
	}

	@Override
	public int countC5JCType(Map<String, Object> map) throws CoreException {
		return getAllotApplyDao().countC5JCType(map);
	}

	// 推广机构
	@Override
	public List<AllotApply> queryC5AbCode(Map<String, Object> map, int currentPage, int pageSize) throws CoreException {
		return getAllotApplyDao().selectC5AbCode(map, currentPage, pageSize);
	}

	@Override
	public int countC5AbCode(Map<String, Object> map) throws CoreException {
		return getAllotApplyDao().countC5AbCode(map);
	}

	// 推广人员
	@Override
	public List<AllotApply> queryC4AbUser(Map<String, Object> map, int currentPage, int pageSize) throws CoreException {
		return getAllotApplyDao().selectC4AbUser(map, currentPage, pageSize);
	}

	@Override
	public int countC4AbUser(Map<String, Object> map) throws CoreException {
		return getAllotApplyDao().countC4AbUser(map);
	}

	// 按申请件模糊查询

	@Override
	public List<AllotApply> queryApplyListByAppId(String appId) throws CoreException {
		List<AllotApply> list = new ArrayList<AllotApply>();
		list = getAllotApplyDao().selectApplyListByAppId(appId);
		return list;
	}

	@Override
	public List<AllotApply> queryHand(Map map) throws CoreException {
		List<AllotApply> list = new ArrayList<AllotApply>();
		list = getAllotApplyDao().selectHand(map);
		return list;
	}

	@Override
	public AllotApply queryHandByAppId(Map map) throws CoreException {
		return getAllotApplyDao().selectHandByAppId(map);
	}

	@Override
	public int queryHandCount(Map map) throws CoreException {
		return getAllotApplyDao().selectHandCount(map);
	}

	@Override
	public int queryHandCount(String appId) throws CoreException {
		return getAllotApplyDao().selectHandCount(appId);
	}

	// 征信批量过件码
	@Override
	public int queryCountBlockCode(String appId) throws CoreException {
		return getAllotApplyDao().selectCountBlockCode(appId);
	}

	@Override
	public TelcheckResult queryBlockCodeList(Map map) throws CoreException {
		return getAllotApplyDao().selectBlockCodeList(map);
	}

	@Override
	public int updateBlockCode(Map map) throws CoreException {
		return getAllotApplyDao().updateBlockCode(map);
	}

	@Override
	public int saveBlockCode(Map map) throws CoreException {
		return getAllotApplyDao().insertBlockCode(map);
	}

	// 审批拒绝码
	@Override
	public int queryCountRefuseCode(String appId) throws CoreException {
		return getAllotApplyDao().selectCountRefuseCode(appId);
	}

	@Override
	public BizApproval queryRefuseCodeList(Map map) throws CoreException {
		return getAllotApplyDao().selectRefuseCodeList(map);
	}

	@Override
	public int updateRefuseCode(Map map) throws CoreException {
		return getAllotApplyDao().updateRefuseCode(map);
	}

	@Override
	public int saveRefuseCode(Map map) throws CoreException {
		return getAllotApplyDao().insertRefuseCode(map);
	}

	@Override
	public int saveRefuseCodeBatch(List<BizApproval> list) throws CoreException {
		return getAllotApplyDao().insertRefuseCodeBatch(list);
	}

	// 新申请件当天
	@Override
	public int queryTodayApp(Map map) throws CoreException {
		return getAllotApplyDao().selectTodayApp(map);
	}

	/**
	 * @Title:lineNewUrlClientZx
	 * @Description:连接工作流 标准卡 跳过审查 池 --组到人分件
	 * @param ctx
	 * @author: 王德彬
	 * @throws Exception
	 * @Date:2017年5月26日下午8:28:56
	 */
	public String saveNewUrlClient(String appId, String currNode) throws Exception {
		log.info("AllotApplyServiceImpl.lineNewUrlClient--审查批量--" + appId + "开始调用工作流");
		Map paramMap = new HashMap();
		paramMap.put("appId", appId);
		String msg = "";
		try {
			Map<String, Object> needMap = bizInpApplDao.selectProcessIdByAppId(paramMap);
			log.info("AllotApplyServiceImpl.lineNewUrlClient--审查批量--needMap=" + needMap);
			Map<String, String> streamMap = CommonProperties.getParoperMap();
			log.info("AllotApplyServiceImpl.lineNewUrlClient--审查批量--streamMap=" + streamMap);
			if (null != needMap) {
				String processId = needMap.get("PROCESSID") != null ? needMap.get("PROCESSID").toString() : "";
				log.info("AllotApplyServiceImpl.lineNewUrlClient--审查批量工作流节点--processId=" + processId);
				String processIp = streamMap.get("process_IP").toString();// 服务ip地址
				log.info("AllotApplyServiceImpl.lineNewUrlClient--审查批量服务ip地址--process_IP=" + processIp);
				String nodeId = streamMap.get("nodeId_lr_bzk").toString();// 流节点
				log.info("AllotApplyServiceImpl.lineNewUrlClient--审查批量流节点--nodeId=" + nodeId);
				if (processId != null && !"".equals(processId)) {
					/*Client client = new Client(
							new URL("http://" + processIp + "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));*/
					String intputXml = "";
					intputXml = "{'processId':'" + processId + "','nodeId':'" + nodeId + "'}";
					/*Object[] obj = new Object[] { intputXml.toString() };
					Object[] result = client.invoke("signal", obj);*/
					String result = topbpmService.signal(intputXml).toString();
					log.info("AllotApplyServiceImpl.lineNewUrlClient--审查批量single======" + result.toString());
					if (result != null && "1".equals(result)) {// 工作流返回机制
																// 1
																// 成功
																// 2或者其它
																// 失败
						msg = "1";
					} else {
						msg = "2";
						throw new RuntimeException("调工作流失败");// 为了制造异常 使事物回滚;
					}
				}
			} else {
				log.info("申请表数据缺失processId，请检测appId=" + paramMap.get("appId"));
				msg = "2";
				throw new CoreException("申请表数据缺失processId，请检测appId=" + paramMap.get("appId"));
			}
		} catch (Exception e) {
			msg = "2";
			log.warn("工作流回调失败：{}", new Object[] { msg });
		}
		log.info("AllotApplyServiceImpl.lineNewUrlClient--审查批量" + appId + "工作流调用结束");
		return msg;
	}

	/**
	 * @Title:lineNewUrlClientZx
	 * @Description:连接工作流 标准卡 易达金 跳过征信 审批 组到人分件 直接到下一环节
	 * @param ctx
	 * @author: 王德彬
	 * @throws Exception
	 * @Date:2017年6月26日下午2:53:56
	 */
	public String saveNewUrlClient(Map<String, Object> appMap, Client client) throws Exception {
		String currNode = (String) appMap.get("currNode") == null ? "system" : (String) appMap.get("currNode");
		if ("02".equals(currNode)) {
			log.info("AllotApplyServiceImpl.lineNewUrlClient--征信批量提交--" + appMap.get("appId").toString() + "开始调用工作流");
		} else if ("03".equals(currNode)) {
			log.info("AllotApplyServiceImpl.lineNewUrlClient--审批批量提交--" + appMap.get("appId").toString() + "开始调用工作流");
		}
		String msg = "";
		String userCode = (String) appMap.get("userCode") == null ? "system" : (String) appMap.get("userCode");
		String ydjFlag = (String) appMap.get("ydjFlag") == null ? "system" : (String) appMap.get("ydjFlag");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appId", appMap.get("appId").toString());
		Map<String, Object> needMap = bizInpApplDao.selectProcessIdByAppId(paramMap);
		//log.info("AllotApplyServiceImpl.lineNewUrlClient--批量提交--needMap="+needMap);
		Map<String, String> streamMap = CommonProperties.getParoperMap();
		// log.info("AllotApplyServiceImpl.lineNewUrlClient--批量提交--streamMap="+streamMap);
		if (needMap != null) {
			String processId = needMap.get("PROCESSID") != null ? needMap.get("PROCESSID").toString() : "";
			String processIp = streamMap.get("process_IP").toString();// 服务ip地址
			String nodeId = "";
			if ("02".equals(currNode)) {
				if ("1".equals(ydjFlag)) {
					nodeId = streamMap.get("nodeId_sc_ydj").toString();// 流节点
				} else if ("2".equals(ydjFlag)) {
					nodeId = streamMap.get("nodeId_sc_bzk").toString();// 流节点
				}

			} else if ("03".equals(currNode)) {
				if ("1".equals(ydjFlag)) {
					nodeId = streamMap.get("nodeId_sp_ydj").toString();// 流节点
				} else if ("2".equals(ydjFlag)) {
					nodeId = streamMap.get("nodeId_sp_bzk").toString();// 流节点
				}
			}
			// log.info("调用流程>>>>>>>参数信息:" + processId + ",nodeId=" +
			// nodeId+",process_IP="+processIp);
			if (processId != null && !"".equals(processId)) {
				// Client client = new Client(new URL("http://" + processIp+
				// "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
				// 查询申请件 根据backFlag判断 批量提交件
				AllotApply allot_backFlag = allotApplyDao.selectHandByAppId(appMap);
				String backFlag = "";
				String specialExamineUser = "1-" + userCode + "-systemBatch";
				if (allot_backFlag != null && "02".equals(currNode)) {
					backFlag = StringUtils.trimToEmpty(allot_backFlag.getBackFlag());
					if ("4".equals(backFlag)) {
						specialExamineUser = "5-" + userCode + "-systemBatch";
					}
				}
				String isBack = "";
				String intputXml = "";
				if ("02".equals(currNode)) {
					isBack = "2";
					intputXml = "{'processId':'" + processId + "','nodeId':'" + nodeId + "','specialExamineUser':'"
							+ specialExamineUser + "','isBack':'" + isBack + "'}";
				} else if ("03".equals(currNode)) {
					isBack = "0";
					intputXml = "{'processId':'" + processId + "','nodeId':'" + nodeId + "','isBack':'" + isBack + "'}";

				}
				log.info("流水号" + appMap.get("appId").toString() + ">>>>>>>参数信息:" + intputXml);
				/*Object[] obj = new Object[] { intputXml.toString() };
				Object[] result = null;
				result = client.invoke("signal", obj);*/
				String result = topbpmService.signal(intputXml).toString();
				if (result != null && "1".equals(result)) {// 工作流返回机制
															// 1
															// 成功
															// 2或者其它
															// 失败
					msg = "1";
					log.info("流水号" + appMap.get("appId").toString() + "===========调用流程接口【成功】============");
				} else {
					msg = "2";
					throw new RuntimeException("流水号" + appMap.get("appId").toString() + "调工作流失败");// 为了制造异常
																									// 使事物回滚;
				}
				if(result != null && "1".equals(result)){//工作流返回机制  1 成功  2或者其它 失败 
					msg="1";
					log.info("流水号"+appMap.get("appId").toString()+"===========调用流程接口【成功】============");
				}else{
					msg="2";
					throw  new RuntimeException("流水号"+appMap.get("appId").toString()+"调工作流失败");//为了制造异常 使事物回滚;
				}
			}else{
				msg="2";
				log.info("流水号"+appMap.get("appId").toString()+"===========调用流程接口【失败】============");
				throw  new RuntimeException("流水号"+appMap.get("appId").toString()+"数据异常(processId不存在).提交失败。");//为了制造异常 使事物回滚;
			}
		} else {
			msg = "2";
			throw new RuntimeException("数据异常(processId不存在).提交失败。");// 为了制造异常
																	// 使事物回滚;
		}
		// log.info("AllotApplyServiceImpl.lineNewUrlClient--批量提交"+paramMap.get("appId").toString()+"-工作流调用结束");
		return msg;
	}

	// 获取Client连接
	public Client getClient(Client client) {
		try {
			if (client == null) {
				Map<String, String> streamMap = CommonProperties.getParoperMap();
				log.info("AllotApplyServiceImpl.getClient--批量提交--streamMap=" + streamMap);
				String processIp = streamMap.get("process_IP").toString();// 服务ip地址
				client = new Client(
						new URL("http://" + processIp + "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
			}
		} catch (Exception e) {
			log.info("连接工作流失败，失败原因为" + e.getMessage());
			throw new RuntimeException("连接工作流失败" + e.getMessage());
		}
		return client;
	}

	/**
	 * @Description:生命周期记录入库
	 * @author 王德彬
	 * @version V1.0
	 * @param context
	 * @throws Exception
	 */
	public int saveAppLifeCicle(Map<String, Object> lifeMap) {
		log.info("AllotHandAction.saveAppLifeCicle 开始记录生命周期");
		int result = 0;
		try {
			String flag = StringUtils.trimToEmpty((String) lifeMap.get("flag"));
			String currNode = StringUtils.trimToEmpty((String) lifeMap.get("currNode"));
			String userCode = StringUtils.trimToEmpty((String) lifeMap.get("userCode"));
			String appBacthId = StringUtils.trimToEmpty((String) lifeMap.get("appId"));
			if ("7".equals(flag)) {// 单件记录
				ApplyLifeCicle a = new ApplyLifeCicle();
				a.setAppId(appBacthId);
				Map userMap = new HashMap();
				userMap.put("userCode", userCode);
				AllotCommon acmon = allotCommonDao.selectUser(userMap);
				// 操作者(原件管理岗 或组长)
				String userName2 = "";
				if (acmon != null) {
					userName2 = acmon.getUserName();
				} else {
					userName2 = "系统";
				}
				if ("01".equals(currNode)) {
					a.setOperateDesc(userName2 + "将该申请件审查批量提交");
				} else if ("02".equals(currNode)) {
					a.setOperateDesc(userName2 + "将该申请件征信批量提交");
				} else if ("03".equals(currNode)) {
					a.setOperateDesc(userName2 + "将该申请件审批批量提交");
				}
				a.setOperater(userName2 + "-" + userCode);
				a.setCrtUser(userCode);
				a.setOperateResult("完成");
				a.setCrtDate(new Date());
				a.setUuid(StringUtil.randomUUIDPlainString());
				result = applyLifeCicleDao.addApplyLifeCicle(a);
				if (result == 0) {
					log.info(appBacthId + "AllotHandAction.saveAppLifeCicle 生命周期记录失败");
				} else {
					log.info(appBacthId + "AllotHandAction.saveAppLifeCicle 生命周期记录成功");
				}
			}
		} catch (Exception e) {
			log.error("AllotHandAction.saveAppLifeCicle 生命周期记录失败", e);
		}
		return result;
	}

	/**
	 * @Description:审查库结果入库
	 * @author 王德彬
	 * @version V1.0
	 * @param appId
	 * @throws Exception
	 */
	public void saveInfoCompare(String appId) throws CoreException {
		RevCompInfo revCompInfo = null;
		String flag = "1";
		try {
			revCompInfo = revCompInfoDao.selectByPrimaryKey(appId);
		} catch (CoreException e) {
			throw e;
		}
		if (revCompInfo == null) {
			revCompInfo = new RevCompInfo();
			flag = "0";
		}
		revCompInfo.setAppId(appId);
		// 根据前台传过来的appId,获取到申请件信息的寄往地址
		BizInpApplC1 t = new BizInpApplC1();
		t.setAppId(appId);
		BizInpApplC1 bizInpApplC1 = applyInfoDao.queryBizInpApplC1ByAppId(appId);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("appId", appId);
		map2.put("FQZ_REQUESTTYPE", "2");// 三方比对请求批次是 “2”
		// 人行单名单址等
		KeyfiledMarchinfo kfmi = keyfiledMarchinfoDao.selectByAppId(map2);
		if ("0".equals(flag)) {
			/*// 人行首张贷记卡发卡日期大于六个月
			String mon = revCompInfoDao.selectMonByAppId(appId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
			int monret = 0;
			if (mon != null && !"".equals(mon)) {
				Date d;
				try {
					d = sdf.parse(mon);
					monret = getMonth(d, new Date());
				} catch (ParseException e) {
				}
			}
			if (monret > 6) {
				revCompInfo.setPeobankFirstcard6months("1");
				revCompInfo.setApplyWorkinfoTure("1");
			} else {
				revCompInfo.setPeobankFirstcard6months("");
			}*/
			
			// 人行首张贷记卡发卡日期大于六个月
			// 人行信用提示信息表--一代人行用的表
			//String mon = revCompInfoDao.selectMonByAppId(appId);
			//二代人行（目前使用的表）：信贷交易提示信息--PBOC_CREDIT_TRANSACTION_DEL，人行个人基本信息表--opas_pboc_personal_info;
			Map<String,Object> months = revCompInfoDao.selectMonthsByAppId(appId);//add by wenyh
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
			int monret = 0;
			if (months != null && !"".equals(months)) {
				//首笔业务发放月份  --专门查人行首张贷记卡发卡日期
				String firstYwGrantMonth = (String) months.get("FIRST_YW_GRANT_MONTH");
				//报告时间
				String reportTime = (String) months.get("REPORT_TIME");
				Date d1;
				Date d2;
				try {
					if(StringUtils.isNotBlank(firstYwGrantMonth) && StringUtils.isNotBlank(reportTime)){
						d1 = sdf.parse(firstYwGrantMonth);
						d2 = sdf.parse(reportTime);
						monret = getMonth(d1, d2);
					}
				} catch (ParseException e) {
					log.error("日期格式化异常：{}", new Object[] { e.getMessage() }, e);
				}
			}

			if (monret > 6) {
				revCompInfo.setPeobankFirstcard6months("1");//人行首张贷记卡发卡日期大于6个月
				revCompInfo.setApplyWorkinfoTure("1");//申请人工作信息真实
			} else {
				revCompInfo.setPeobankFirstcard6months("");
			}

			// 查询第三方信息手机和第三方信息单址(ivs的)
			Map<String, String> ivsMap = revCompInfoDao.selectIvsMap(appId);
			if (ivsMap != null) {
				String codeAddrEnglish = ivsMap.get("CODEADDRENGLISH");
				String codePhoenEnglish = ivsMap.get("CODEPHOENENGLISH");
				String[] addrArray = { "ADDR_Match_Recency_Bad", "ADDR_Match_Trust_Self_Recency_Bad",
						"ADDR_Match_Recency_Good", "ADDR_Match_Trust_Self_Recency_Good", "ADDR_Match_Trust_Other" };
				String[] phoneArray = { "PHONE_Match_Sharing_Good", "PHONE_Match_Trust_Self_Sharing_Good",
						"PHONE_Match_Recency_Bad", "PHONE_Match_Trust_Self_Recency_Bad", "PHONE_Match_Recency_Good",
						"PHONE_Match_Trust_Self_Recency_Good", "PHONE_Match_Reliability_Good",
						"PHONE_Match_Trust_Self_Reliability_Good", "PHONE_Match_Trust_Other" };
				for (int i = 0; i < addrArray.length; i++) {
					if (addrArray[i].equals(codeAddrEnglish)) {
						revCompInfo.setThirddataAddr("1");// 第三方信息单址
						revCompInfo.setApplyWorkTure("1");
					}
				}
				for (int j = 0; j < phoneArray.length; j++) {
					if (phoneArray[j].equals(codePhoenEnglish)) {
						revCompInfo.setThirdPhone("1");// 第三方信息手机
						revCompInfo.setApplyerTrue("1");
						;// 申请人真实
					}
				}
			}
		}
		// 查appId的人行信息 单位名称 单位地址 单位电话 住宅地址
		List<Map<String, String>> allPbocApppid = pbocDao.queryPbocCompanyAndAddressByAppId(appId);
		List<Map<String, String>> compPhonePbocApppid = pbocDao.queryCompPhoneByAppId(appId);
		// List<Map<String,String>> hmaddPhonePbocApppid =
		// pbocDao.queryResidentAddrByAppId(appId);
		StringBuffer company = new StringBuffer();
		StringBuffer compAddr = new StringBuffer();
		StringBuffer ccompPhone = new StringBuffer();
		StringBuffer cellPhone = new StringBuffer();
		if (allPbocApppid != null && allPbocApppid.size() > 0) {
			for (int i = 0; i < allPbocApppid.size(); i++) {
				company.append((i == allPbocApppid.size() - 1) ? allPbocApppid.get(i).get("company")
						: allPbocApppid.get(i).get("company") + "<br />");
				compAddr.append((i == allPbocApppid.size() - 1) ? allPbocApppid.get(i).get("compAddr")
						: allPbocApppid.get(i).get("compAddr") + "<br />");
			}
		}
		if (compPhonePbocApppid != null && compPhonePbocApppid.size() > 0) {
			for (int i = 0; i < compPhonePbocApppid.size(); i++) {
				ccompPhone.append((i == compPhonePbocApppid.size() - 1) ? compPhonePbocApppid.get(i).get("ccompPhone")
						: compPhonePbocApppid.get(i).get("ccompPhone") + "<br />");
				cellPhone.append((i == compPhonePbocApppid.size() - 1) ? compPhonePbocApppid.get(i).get("cellPhone")
						: compPhonePbocApppid.get(i).get("cellPhone") + "<br />");
			}
		}
		// 公司地址C1_COADD1
		StringBuffer sbaddrCom = new StringBuffer();
		sbaddrCom.append(StringUtil.trimToEmpty(bizInpApplC1.getC1Coadd1()));
		sbaddrCom.append(StringUtil.trimToEmpty(bizInpApplC1.getC1Coadd2()));
		sbaddrCom.append(StringUtil.trimToEmpty(bizInpApplC1.getC1Coadd3()));
		sbaddrCom.append(StringUtil.trimToEmpty(bizInpApplC1.getC1Coadd4()));
		// 公司名称
		/*
		 * String cocname = StringUtil.trimToEmpty(bizInpApplC1.getC1Coname());
		 * //公司电话 String C1_COTEL =
		 * StringUtil.trimToEmpty(bizInpApplC1.getC1Cotel()); //申请人手机 String
		 * cell_phone = StringUtil.trimToEmpty(bizInpApplC1.getC1Mobile());
		 */
		// 匹配结果
		String company1 = "";// 人行单位名称
		String compAddr1 = "";// 人行单位地址
		String ccompPhone1 = "";// 人行单位电话
		String cellPhoneCheck = "";// 人行手机
		String billAddressCheck = "";// 人行账单地址
		/*
		 * if("0".equals(flag)){ Map<String,String> fqzMap =
		 * revCompInfoDao.selectFqzRiskDesc(map2);//根据流水号和请求批次查询反欺诈返回的人行匹配结果
		 * if(fqzMap!=null){ String fqzRiskDesc = fqzMap.get("FQZ_RISKDESC");
		 * if(fqzRiskDesc!=null&&!"".equals(fqzRiskDesc)){ String[] split =
		 * fqzRiskDesc.split(","); if(split!=null){ for (String arr : split) {
		 * if("rule_crd_coname_21".equals(arr)){//申请表单位信息与人行单位信息匹配 company1 =
		 * "1"; } if("rule_crd_coadd_34".equals(arr)){//申请表单位地址与人行单位地址匹配
		 * compAddr1 = "1"; }
		 * if("B".equals(bizInpApplC1.getC4Cycadd1())&&"rule_crd_coadd_34".
		 * equals(arr)){ //如果主卡寄往的地址是单位地址 billAddressCheck = "1"; }
		 * if("H".equals(bizInpApplC1.getC4Cycadd1())&&"rule_crd_hmadd_28".
		 * equals(arr)){//如果主卡寄往的地址是家庭地址 billAddressCheck = "1"; } } } } } }
		 */
		if ("0".equals(flag)) {
			if (kfmi != null) {
				// String marchResult = kfmi.getMarchResult();
				String matchResult = kfmi.getMarchResult();
				if (matchResult != null && !"".equals(matchResult)) {
					JSONArray ja = JSONArray.fromObject(matchResult);
					List<RiskCheck> listrisk = (List) JSONArray.toCollection(ja, RiskCheck.class);
					for (RiskCheck r : listrisk) {
						if ((("COMPANY").equals(r.getFieldKey()))
								&& ("OPAS_PBOC_PROFESSION_INFO".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							company1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ((("COMP_ADDR").equals(r.getFieldKey())
								&& ("OPAS_PBOC_PROFESSION_INFO").equals(r.getTableName())
								&& "3".equals(r.getRiskType()))) {
							compAddr1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ((("C_COMP_PHONE").equals(r.getFieldKey())
								&& ("OPAS_PBOC_PERSONAL_INFO").equals(r.getTableName())
								&& "3".equals(r.getRiskType()))) {
							ccompPhone1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ((("CELL_PHONE").equals(r.getFieldKey())
								&& ("OPAS_PBOC_PERSONAL_INFO").equals(r.getTableName())
								&& "3".equals(r.getRiskType()))) {
							cellPhoneCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ("B".equals(bizInpApplC1.getC4Cycadd1())) {
							// 如果主卡寄往的地址是单位地址,查询人行个人职业信息表获得人行单位地址proessionInfoList
							if ((("COMP_ADDR").equals(r.getFieldKey())
									&& ("OPAS_PBOC_PROFESSION_INFO").equals(r.getTableName())
									&& "3".equals(r.getRiskType()))) {
								billAddressCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
							}
						} else if ("H".equals(bizInpApplC1.getC4Cycadd1())) {
							// 如果主卡寄往的地址是家庭地址
							if ((("RESIDENT_ADDR").equals(r.getFieldKey())
									&& ("OPAS_PBOC_RESIDENCE_INFO").equals(r.getTableName())
									&& "3".equals(r.getRiskType()))) {
								billAddressCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
							}
						} else {
							billAddressCheck = "";
						}
					}
				}
			}
		}
		/*
		 * StringBuffer billAddress =new StringBuffer(); String billAddressC1 =
		 * ""; if("B".equals(bizInpApplC1.getC4Cycadd1())){
		 * //如果主卡寄往的地址是单位地址,查询人行个人职业信息表获得人行单位地址proessionInfoList
		 * if(allPbocApppid != null && allPbocApppid.size()>0){ for (int i = 0;
		 * i < allPbocApppid.size(); i++) {
		 * billAddress.append((i==allPbocApppid.size()-1)?allPbocApppid.get(i).
		 * get("compAddr"):allPbocApppid.get(i).get("compAddr")+"<br />"); } }
		 * billAddressC1 = sbaddrCom.toString(); }else
		 * if("H".equals(bizInpApplC1.getC4Cycadd1())){ //如果主卡寄往的地址是家庭地址
		 * hmaddPhonePbocApppid if(hmaddPhonePbocApppid != null &&
		 * hmaddPhonePbocApppid.size()>0){ for (int i = 0; i <
		 * hmaddPhonePbocApppid.size(); i++) {
		 * billAddress.append((i==hmaddPhonePbocApppid.size()-1)?
		 * hmaddPhonePbocApppid.get(i).get("residentAddr"):hmaddPhonePbocApppid.
		 * get(i).get("residentAddr")+"<br />"); } } billAddressC1 =
		 * StringUtil.trimToEmpty(bizInpApplC1.getC1Hmadd1())+StringUtil.
		 * trimToEmpty(bizInpApplC1.getC1Hmadd2())
		 * +StringUtil.trimToEmpty(bizInpApplC1.getC1Hmadd3())+StringUtil.
		 * trimToEmpty(bizInpApplC1.getC1Hmadd4()); }else{ billAddressC1 = "" ;
		 * }
		 */
		if ("1".equals(company1) && "0".equals(flag)) {
			revCompInfo.setCompany(company1);
			if ("1".equals(compAddr1) || "1".equals(ccompPhone1)) {
				revCompInfo.setApplyWorkTure("1");
			}
		}
		if ("1".equals(compAddr1) && "0".equals(flag)) {
			revCompInfo.setPeobankCompanyNameAddr(compAddr1);
		}
		if ("1".equals(ccompPhone1) && "0".equals(flag)) {
			revCompInfo.setPeobankCompanyNameTel(ccompPhone1);
		}
		if ("1".equals(cellPhoneCheck) && "0".equals(flag)) {
			revCompInfo.setPeobankPhone(cellPhoneCheck);
			revCompInfo.setApplyerTrue("1");// 申请人真实
		}
		if ("1".equals(billAddressCheck) && "0".equals(flag)) {
			revCompInfo.setOrderAddress(billAddressCheck);// 账单地址真实
		}
		RevCompInfo revCompInfo2 = revCompInfoDao.selectByPrimaryKey(appId);
		if (revCompInfo2 == null) {
			revCompInfoDao.insert(revCompInfo);
		} else {
			revCompInfoDao.updateByPrimaryKey(revCompInfo);
		}
	}

	/**
	 * @Description:录入结果（blaze）入库
	 * @author 王德彬
	 * @version V1.1
	 * @param appId
	 * @throws Exception
	 */
	public void saveInfoCollect(Map<String, Object> dataMap) {
		String appId = (String) dataMap.get("appId");
		try {
			// 查询blaze表数据
			Map<String, Object> map = infoCollectDao.querySdBlazeBatch(dataMap);
			if (map != null) {
				ApprovaInfoSupp approvaInfoSupp = new ApprovaInfoSupp();
				approvaInfoSupp.setAppId(appId);
				//
				String industryCode = dataMap.get("industryCode") != null ? dataMap.get("industryCode").toString() : "";
				approvaInfoSupp.setIndustryCode(industryCode);
				//
				String jobCode = dataMap.get("jobCode") != null ? dataMap.get("jobCode").toString() : "";
				approvaInfoSupp.setJobCode(jobCode);
				//
				BigDecimal houseCost = (BigDecimal) map.get("houseCost");
				if (houseCost != null) {
					approvaInfoSupp.setHouseCost(houseCost);
				}
				//
				BigDecimal houseArea = (BigDecimal) map.get("houseArea");
				if (houseArea != null) {
					approvaInfoSupp.setHouseArea(houseArea);
				}
				//
				String carCost1 = (String) map.get("carCost");
				if (!"".equals(carCost1) && carCost1 != null) {
					approvaInfoSupp.setCarCost(carCost1);
				}
				//
				BigDecimal perPayWage = (BigDecimal) map.get("perPayWage");
				if (perPayWage != null) {
					approvaInfoSupp.setPerPayWage(perPayWage);
				}
				//
				BigDecimal perAssetBalance = (BigDecimal) map.get("perAssetBalance");
				if (perAssetBalance != null) {
					approvaInfoSupp.setPerAssetBalance(perAssetBalance);
				}
				//
				BigDecimal perFinAssetSum = (BigDecimal) map.get("perFinAssetSum");
				if (perFinAssetSum != null) {
					approvaInfoSupp.setPerFinAssetSum(perFinAssetSum);
				}
				//
				BigDecimal perLoanpri = (BigDecimal) map.get("perLoanpri");
				if (perLoanpri != null) {
					approvaInfoSupp.setPerLoanpri(perLoanpri);
				}
				//
				BigDecimal sbMonthPayment = (BigDecimal) map.get("sbMonthPayment");
				if (sbMonthPayment != null) {
					approvaInfoSupp.setSbMonthPayment(sbMonthPayment);
				}
				//
				BigDecimal sbMonthPaybase = (BigDecimal) map.get("sbMonthPaybase");
				if (sbMonthPaybase != null) {
					approvaInfoSupp.setSbMonthPaybase(sbMonthPaybase);
				}
				//
				BigDecimal gjjMonthPayment = (BigDecimal) map.get("gjjMonthPayment");
				if (gjjMonthPayment != null) {
					approvaInfoSupp.setGjjMonthPayment(gjjMonthPayment);
				}
				//
				Date gjjPayDate = (Date) map.get("gjjPayDate");
				//
				Date gjjEndMonth = (Date) map.get("gjjEndMonth");
				//
				Date registerDate = (Date) map.get("registerDate");
				//
				Date issueDate = (Date) map.get("issueDate");
				//
				Date perOpendate = (Date) map.get("perOpendate");
				//
				Date sbPayDate = (Date) map.get("sbPayDate");
				if (gjjPayDate != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						approvaInfoSupp.setGjjPayDate(sdf.parse(sdf.format(gjjPayDate)));
					} catch (ParseException e1) {
						log.error("开始日期类型转换错误", e1);
						return;
					}
				}
				if (gjjEndMonth != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
					try {
						approvaInfoSupp.setGjjEndMonth(sdf.parse(sdf.format(gjjEndMonth)));
					} catch (ParseException e1) {
						log.error("日期类型转换错误", e1);
						return;
					}
				}
				if (registerDate != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						approvaInfoSupp.setRegisterDate(sdf.parse(sdf.format(registerDate)));
					} catch (ParseException e1) {
						log.error("开始日期类型转换错误", e1);
						return;
					}
				}
				if (issueDate != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						issueDate = sdf.parse(sdf.format(issueDate));
						approvaInfoSupp.setIssueDate(issueDate);
					} catch (ParseException e1) {
						log.error("开始日期类型转换错误", e1);
						return;
					}
				}
				if (perOpendate != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						perOpendate = sdf.parse(sdf.format(perOpendate));
						approvaInfoSupp.setPerOpendate(perOpendate);
					} catch (ParseException e1) {
						log.error("开始日期类型转换错误", e1);
						return;
					}
				}
				if (sbPayDate != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						sbPayDate = sdf.parse(sdf.format(sbPayDate));
						approvaInfoSupp.setSbPayDate(sbPayDate);
					} catch (ParseException e1) {
						log.error("开始日期类型转换错误", e1);
						return;
					}
				}
				//
				String carOld1 = (String) map.get("carOld");
				if (!"".equals(carOld1) && carOld1 != null) {
					approvaInfoSupp.setCarOld(carOld1);
				}
				//
				BigDecimal sbCurrCompanyMonth = (BigDecimal) map.get("sbCurrCompanyMonth");
				if (sbCurrCompanyMonth != null && !"".equals(sbCurrCompanyMonth)) {
					approvaInfoSupp.setSbCurrCompanyMonth(sbCurrCompanyMonth.longValue());
				}
				//
				BigDecimal gjjPerPayrate = (BigDecimal) map.get("gjjPerPayrate");
				if (gjjPerPayrate != null && !"".equals(gjjPerPayrate)) {
					approvaInfoSupp.setGjjPerPayrate(gjjPerPayrate.shortValue());
				}
				// s
				String houseInfoFrom = map.get("houseInfoFrom") != null ? map.get("houseInfoFrom").toString() : "";
				approvaInfoSupp.setHouseInfoFrom(houseInfoFrom);
				// s
				String carHand = map.get("carHand") != null ? map.get("carHand").toString() : "";
				approvaInfoSupp.setCarHand(carHand);
				// s
				String perLoanstatus = map.get("perLoanstatus") != null ? map.get("perLoanstatus").toString() : "";
				approvaInfoSupp.setPerLoanstatus(perLoanstatus);
				// s
				String sbCurrPayStatus = map.get("sbCurrPayStatus") != null ? map.get("sbCurrPayStatus").toString()
						: "";
				approvaInfoSupp.setSbCurrPayStatus(sbCurrPayStatus);
				// s
				String sbCurrPaycompany = map.get("sbCurrPaycompany") != null ? map.get("sbCurrPaycompany").toString()
						: "";
				approvaInfoSupp.setSbCurrPaycompany(sbCurrPaycompany);
				// s
				String gjjPayStatus = map.get("gjjPayStatus") != null ? map.get("gjjPayStatus").toString() : "";
				approvaInfoSupp.setGjjPayStatus(gjjPayStatus);
				// s
				String eduModel = map.get("eduModel") != null ? map.get("eduModel").toString() : "";
				approvaInfoSupp.setEduModel(eduModel);
				// s
				String eduType = map.get("eduType") != null ? map.get("eduType").toString() : "";
				approvaInfoSupp.setEduType(eduType);
				ApprovaInfoSupp approvaInfoSupp2 = infoCollectDao.queryApprovaInfoSupp(dataMap);
				if (approvaInfoSupp2 == null) {
					infoCollectDao.insertApprovaInfoSupp(approvaInfoSupp);
				} else {// 更新行职业代码
					reviewDecisionDao.updateByAppId(dataMap);
				}
			} else {
				int numb = reviewDecisionDao.selectCount(appId);
				if (numb == 0) {// 插入
					reviewDecisionDao.insertApprovaInfoSupp(dataMap);
				} else {// 更新
					reviewDecisionDao.updateByAppId(dataMap);
				}
			}

		} catch (CoreException e) {
			log.error("录入结果入库异常：{}", new Object[] { e.getMessage() }, e);
		}
	}

	// 计算两个日期相差的月份
	private static int getMonth(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int m1 = c1.get(Calendar.MONTH);
		int m2 = c2.get(Calendar.MONTH);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int ret = (y2 - y1) * 12 + m2 - m1;
		return ret;
	}

	// 综合分件查询
	@Override
	public Map<String, Object> saveApplyByConditions(Map<String, Object> map) throws CoreException {
		log.info("AllotApplyServiceImpl.saveApplyByConditions 查询符合筛选条件的申请件   enter ");
		// 初始时间
		String currNode = (String) map.get("currNode") == null ? "" : (String) map.get("currNode");
		String secondNode = (String) map.get("secondNode") == null ? "" : (String) map.get("secondNode");
		String userCode = (String) map.get("userCode") == null ? "" : (String) map.get("userCode");
		String strType = (String) map.get("strType") == null ? "" : (String) map.get("strType");
		String ydjFlag = (String) map.get("ydjFlag") == null ? "" : (String) map.get("ydjFlag");
		String currStatusFlag = (String) map.get("currStatusFlag") == null ? "" : (String) map.get("currStatusFlag");
		String submitStr = "";
		if (!"05".equals(currNode)) {// 提报状态
			submitStr = "submit";
		}
		map.put("submitStr", submitStr);
		String serialNum = (String) map.get("serialNum") == null ? "" : (String) map.get("serialNum");
		String appRegion = (String) map.get("appRegion") == null ? "" : (String) map.get("appRegion");
		String appChannel = (String) map.get("appChannel") == null ? "" : (String) map.get("appChannel");
		String appInput = (String) map.get("appInput") == null ? "" : (String) map.get("appInput");
		String appProd = (String) map.get("appProd") == null ? "" : (String) map.get("appProd");
		String shareTime = (String) map.get("shareTime") == null ? "" : (String) map.get("shareTime");
		String crtTimeStart = (String) map.get("crtTimeStart") == null ? "" : (String) map.get("crtTimeStart");
		String crtTimeEnd = (String) map.get("crtTimeEnd") == null ? "" : (String) map.get("crtTimeEnd");
		String appTimeStart = (String) map.get("appTimeStart") == null ? "" : (String) map.get("appTimeStart");
		String appTimeEnd = (String) map.get("appTimeEnd") == null ? "" : (String) map.get("appTimeEnd");
		String appStatus = (String) map.get("appStatus") == null ? "" : (String) map.get("appStatus");
		String appStatus2 = (String) map.get("appStatus2") == null ? "" : (String) map.get("appStatus2");
		String quickCardFlag = (String) map.get("quickCardFlag") == null ? "" : (String) map.get("quickCardFlag");
		String nosafePromoter = (String) map.get("nosafePromoter") == null ? "" : (String) map.get("nosafePromoter");
		String creditDecisionLayer = (String) map.get("creditDecisionLayer") == null ? ""
				: (String) map.get("creditDecisionLayer");
		String creditDecisionResult = (String) map.get("creditDecisionResult") == null ? ""
				: (String) map.get("creditDecisionResult");
		String minNumberRead = (String) map.get("minNumberRead") == null ? "" : (String) map.get("minNumberRead");
		String maxNumberRead = (String) map.get("maxNumberRead") == null ? "" : (String) map.get("maxNumberRead");
		String isHaveCardCust = (String) map.get("isHaveCardCust") == null ? "" : (String) map.get("isHaveCardCust");
		String pubPolice = (String) map.get("pubPolice") == null ? "" : (String) map.get("pubPolice");
		String minProposedLmt = (String) map.get("minProposedLmt") == null ? "" : (String) map.get("minProposedLmt");
		String maxProposedLmt = (String) map.get("maxProposedLmt") == null ? "" : (String) map.get("maxProposedLmt");
		String allotType = ((String) map.get("allotType")) == null ? "" : (String) map.get("allotType");
		String applyId = ((String) map.get("appId")) == null ? "" : ((String) map.get("appId")).trim();
		String pbocRstFinDesp = ((String) map.get("pbocRstFinDesp")) == null ? "" : (String) map.get("pbocRstFinDesp");
		String c4ApBatchStr = (String) map.get("c4ApBatch") == null ? "" : (String) map.get("c4ApBatch");
		String c5JcTypeStr = (String) map.get("c5JcType") == null ? "" : (String) map.get("c5JcType");
		String c5AbCodeStr = (String) map.get("c5AbCode") == null ? "" : (String) map.get("c5AbCode");
		String c4AbUser = (String) map.get("c4AbUser") == null ? "" : (String) map.get("c4AbUser");
		// 批量标识 审查时无法查询 征信查询 审查批量过来的件 审批查询 征信批量过来的件
		String batchLabel = (String) map.get("batchLabel") == null ? "" : (String) map.get("batchLabel");
		// 质检叫停
		String stopStatus = (String) map.get("stopStatus") == null ? "" : (String) map.get("stopStatus");
		// 天御分
		String minRiskScore = (String) map.get("minRiskScore") == null ? "" : (String) map.get("minRiskScore");
		String maxRiskScore = (String) map.get("maxRiskScore") == null ? "" : (String) map.get("maxRiskScore");
		// 反欺诈决策结果
		String fqzValue = (String) map.get("fqzValue") == null ? "" : (String) map.get("fqzValue");
		// 天御风险码及等级
		String riskCode = (String) map.get("riskCode") == null ? "" : (String) map.get("riskCode");
		String riskLevel = (String) map.get("riskLevel") == null ? "" : (String) map.get("riskLevel");
		// 过件码1
		String blockCode1 = (String) map.get("blockCode1") == null ? "" : (String) map.get("blockCode1");
		// 过件码2
		String blockCode2 = (String) map.get("blockCode2") == null ? "" : (String) map.get("blockCode2");
		// 征信评分
		String triggerType1 = (String) map.get("triggerType1") == null ? "" : (String) map.get("triggerType1");
		String triggerType2 = (String) map.get("triggerType2") == null ? "" : (String) map.get("triggerType2");
		String wsCode = (String) map.get("wsCode") == null ? "" : (String) map.get("wsCode");// 网申渠道代码
		String decisionResult = (String) map.get("decisionResult") == null ? "" : (String) map.get("decisionResult");// 授信决策结果
		String custFlag = (String) map.get("custFlag") == null ? "" : (String) map.get("custFlag");// 客群标签
		// delStatusStr 区分未完成、退回、补件情况
		String delStatusStr = (String) map.get("delStatusStr") == null ? "" : (String) map.get("delStatusStr");		// 分件开始、结束时间
		//次策略捞回 -筛选出全量FICO捞回的申请件【决策结果描述含“评分标准（FICO），人工审查”字样】-wenyh-20200807
		String secondDecisionLH = (String) map.get("secondDecisionLH") == null ? "" : (String) map.get("secondDecisionLH");
		String secondDecisionLHStr = "";
		if(secondDecisionLH != null && secondDecisionLH != ""){
			if("1".equals(secondDecisionLH)){
				secondDecisionLHStr = "AND ydjBlaze.DECISION_DESP like '%评分标准（FICO），人工审查%' ";
			}else if("2".equals(secondDecisionLH)){
				secondDecisionLHStr = "AND sdBlaze.DECISION_DESP like '%评分标准（FICO），人工审查%' ";
			}
		}
		map.put("secondDecisionLHStr", secondDecisionLHStr);
		// 分件开始、结束时间
		String fenTime1 = (String) map.get("fenTime1") == null ? "" : (String) map.get("fenTime1");
		String fenTime2 = (String) map.get("fenTime2") == null ? "" : (String) map.get("fenTime2");
		// 流水号字母
		if (!"".equals(serialNum)) {
			List<String> serialNumList = new ArrayList<String>();
			String[] arr = serialNum.split(",");
			serialNumList = Arrays.asList(arr);
			map.put("serialNumList", serialNumList);
		}
		// 展业地区
		if (!"".equals(appRegion)) {
			List<String> appRegionList = new ArrayList<String>();
			String[] arr = appRegion.split(",");
			appRegionList = Arrays.asList(arr);
			map.put("appRegionList", appRegionList);
		}
		// 渠道
		if (!"".equals(appChannel)) {
			List<String> appChannelList = new ArrayList<String>();
			String[] arr = appChannel.split(",");
			appChannelList = Arrays.asList(arr);
			map.put("appChannelList", appChannelList);
		}
		// 网申策略优化：推荐来源、平台类型、合作类型、平台风险层级
		String rcdSrc = (String) map.get("rcdSrc") == null ? "" : (String) map.get("rcdSrc");
		if (!"".equals(rcdSrc)) {
			List<String> rcdSrcList = new ArrayList<String>();
			String[] arr = rcdSrc.split(",");
			rcdSrcList = Arrays.asList(arr);
			map.put("rcdSrcList", rcdSrcList);
		}
		String platType = (String) map.get("platType") == null ? "" : (String) map.get("platType");
		if (!"".equals(platType)) {
			List<String> platTypeList = new ArrayList<String>();
			String[] arr = platType.split(",");
			platTypeList = Arrays.asList(arr);
			map.put("platTypeList", platTypeList);
		}
		String coprType = (String) map.get("coprType") == null ? "" : (String) map.get("coprType");
		if (!"".equals(coprType)) {
			List<String> coprTypeList = new ArrayList<String>();
			String[] arr = coprType.split(",");
			coprTypeList = Arrays.asList(arr);
			map.put("coprTypeList", coprTypeList);
		}
		String platRiskLvl = (String) map.get("platRiskLvl") == null ? "" : (String) map.get("platRiskLvl");
		if (!"".equals(platRiskLvl)) {
			List<String> platRiskLvlList = new ArrayList<String>();
			String[] arr = platRiskLvl.split(",");
			platRiskLvlList = Arrays.asList(arr);
			map.put("platRiskLvlList", platRiskLvlList);
		}
		if (!"".equals(rcdSrc) || !"".equals(platType) || !"".equals(coprType) || !"".equals(platRiskLvl)) {
			map.put("ditchStr", "ditchStr");
		}
		// 录入商
		String inputStr = "";
		if (appInput.indexOf("A") != -1 && appInput.indexOf("B") == -1 && appInput.indexOf("1") == -1) {
			inputStr = "AND substr(allot.APP_ID,7,1) = 'A' ";
		} else if (appInput.indexOf("A") == -1 && appInput.indexOf("B") != -1 && appInput.indexOf("1") == -1) {
			inputStr = "AND substr(allot.APP_ID,7,1) = 'B' ";
		} else if (appInput.indexOf("A") == -1 && appInput.indexOf("B") == -1 && appInput.indexOf("1") != -1) {
			inputStr = "AND substr(allot.APP_ID,7,1) not in ('A','B') ";
		} else if (appInput.indexOf("A") != -1 && appInput.indexOf("B") != -1 && appInput.indexOf("1") == -1) {
			inputStr = "AND substr(allot.APP_ID,7,1)  in ('A','B') ";
		} else if (appInput.indexOf("A") != -1 && appInput.indexOf("B") == -1 && appInput.indexOf("1") != -1) {
			inputStr = "AND (substr(allot.APP_ID,7,1) = 'A' or substr(allot.APP_ID,7,1) not in ('A','B')) ";
		} else if (appInput.indexOf("A") == -1 && appInput.indexOf("B") != -1 && appInput.indexOf("1") != -1) {
			inputStr = "AND (substr(allot.APP_ID,7,1) = 'B' or substr(allot.APP_ID,7,1) not in ('A','B')) ";
		}
		map.put("inputStr", inputStr);
		// 卡产品类型
		if (!"".equals(appProd)) {
			List<String> appProdList = new ArrayList<String>();
			String[] arr = appProd.split(",");
			appProdList = Arrays.asList(arr);
			map.put("appProdList", appProdList);
		}
		// 批量提交
		String batchStr = "";
		if ("1".equals(batchLabel)) {
			if ("02".equals(currNode) || "05".equals(currNode)) {
				batchStr = "AND allot.BATCH_OPERATE_FLAG = '1' ";
			} else if ("03".equals(currNode)) {
				batchStr = "AND allot.BATCH_CREDIT_FLAG ='1' ";
			}
		}
		map.put("batchStr", batchStr);
		// 标识
		String identify = (String) map.get("identify") == null ? "" : (String) map.get("identify");
		String quickStr = "";
		if (!"1".equals(identify) && !"2".equals(identify) && !"3".equals(identify)) {// 未取值
			// 已持卡客户
			isHaveCardCust = "";
			// 快速申请
			quickCardFlag = "";
			// 公安链接
			pubPolice = "";
		} else if ("1".equals(identify)) {
			// 快速申请
			quickCardFlag = "";
			// 公安链接
			pubPolice = "";
		} else if ("2".equals(identify)) {
			// 已持卡客户
			isHaveCardCust = "";
			// 公安链接
			pubPolice = "";
			// 快速发卡标志 0正常 1快速
			if ("0".equals(quickCardFlag)) {
				quickStr = "AND (ac.QUICK_CARD_FLAG = '0' or ac.QUICK_CARD_FLAG is null)";
			} else if ("1".equals(quickCardFlag)) {
				quickStr = "AND ac.QUICK_CARD_FLAG in ('1','2') ";
			}
		} else if ("3".equals(identify)) {
			// 已持卡客户
			isHaveCardCust = "";
			// 快速申请
			quickCardFlag = "";
		}
		map.put("quickStr", quickStr);

		// 申请件编号
		String appId = "";
		if (!"".equals(applyId) && null != applyId) {
			appId = applyId.toUpperCase();
			map.put("appId", appId);
		}
		// 易达金非捞件的申请件 只看易达金条件(如果applyId不为空时 失效)
		String ydjStr = "";
		if (("1".equals(ydjFlag) || "2".equals(ydjFlag) || "".equals(ydjFlag))
				&& ("".equals(applyId) || null == applyId)) {
			ydjStr = "AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02' or allot.DEL_STATUS='3')";
		}
		map.put("ydjStr", ydjStr);
		// 时间
		if ("1".equals(shareTime)) {// 条形码日期,将进件日期致空
			crtTimeStart = "";
			crtTimeEnd = "";
			map.put("crtTimeStart", crtTimeStart);
			map.put("crtTimeEnd", crtTimeEnd);
		} else if ("2".equals(shareTime)) {// 进件日期,将条形码日期致空
			map.put("appTimeStart", "");
			map.put("appTimeEnd", "");
		} else if ("".equals(shareTime) || shareTime == null) {
			map.put("crtTimeStart", "");
			map.put("crtTimeEnd", "");
			map.put("appTimeStart", "");
			map.put("appTimeEnd", "");
		}
		// 条形码日期格式化
		if (!"".equals(appTimeStart) && appTimeStart != null && !"-1".equals(appTimeStart)) {
			String appDateStart = (appTimeStart.replace("-", "")).substring(2, appTimeStart.replace("-", "").length());
			map.put("appTimeStart", appDateStart);
		}
		if (!"".equals(appTimeEnd) && appTimeEnd != null && !"-1".equals(appTimeEnd)) {
			String appDateEnd = (appTimeEnd.replace("-", "")).substring(2, appTimeEnd.replace("-", "").length());
			map.put("appTimeEnd", appDateEnd);
		}
		// 排序
		String checkStr = "";
		if (!"".equals(allotType) && allotType != null) {
			if ("1".equals(allotType)) {
				checkStr = "ac.CRT_TIME asc,allot.APP_ID asc,substr(allot.APP_ID,16,1) desc";
			} else if ("2".equals(allotType)) {
				checkStr = "ac.CRT_TIME desc,allot.APP_ID asc,substr(allot.APP_ID,16,1) desc";
			} else if ("3".equals(allotType)) {
				checkStr = "NLSSORT(translate(lower(ac.C1_CONAME),'abcdefghijklmnopqrstuywxyz','啊八嚓大额发噶哈几卡拉吗呐哦扒七然仨他哇西呀杂'),'NLS_SORT=SCHINESE_PINYIN_M') asc,allot.APP_ID asc,substr(allot.APP_ID,16,1) desc";
			} else if ("4".equals(allotType)) {
				checkStr = "NLSSORT(translate(lower(ac.C1_CONAME),'abcdefghijklmnopqrstuywxyz','啊八嚓大额发噶哈几卡拉吗呐哦扒七然仨他哇西呀杂'),'NLS_SORT=SCHINESE_PINYIN_M') desc,allot.APP_ID asc,substr(allot.APP_ID,16,1) desc";
			} else if ("5".equals(allotType)) {
				checkStr = "allot.APP_ID asc,substr(allot.APP_ID,16,1) desc";
			} else if ("6".equals(allotType)) {
				checkStr = "allot.APP_ID desc,substr(allot.APP_ID,16,1) desc";
			}
		}
		map.put("checkStr", checkStr);
		// 分配状态 (排除转移 回收 和当日申请件查询currStatusList赋值)
		if ("".equals(currStatusFlag)) {
			List<String> currStatusList = new ArrayList<String>();
			if ("1".equals(secondNode)) {
				if ((appStatus != null && !"".equals(appStatus) && !"-1".equals(appStatus))
						|| ("24".equals(appStatus2))) {
					if ("1".equals(appStatus) && "24".equals(appStatus2)) {// 池中待分配已挂起
						currStatusList.add("5");
					} else if ("1".equals(appStatus) && !"24".equals(appStatus2)) {// 池中待分配未挂起
						currStatusList.add("0");
					} else if ("2".equals(appStatus) && !"24".equals(appStatus2)) {// 池中已分配显示
																					// 到队列
																					// 到组或人的
																					// 已分配未挂起
						currStatusList.add("1");
						currStatusList.add("3");
						currStatusList.add("6");
					} else if ("2".equals(appStatus) && "24".equals(appStatus2)) {// 到
																					// 队列
																					// 、组或人
																					// 已分配
																					// 已挂起
						currStatusList.add("2");
						currStatusList.add("4");
						currStatusList.add("7");
					} else if ((appStatus == null || "".equals(appStatus) || "-1".equals(appStatus))
							&& ("24".equals(appStatus2))) {// 挂起标签(显示池中 或队列中 组中
															// 挂起件)
						currStatusList.add("5");
						currStatusList.add("2");
						currStatusList.add("7");
					} else if ("3".equals(appStatus)) {
						currStatusList.add("5");
					}
				} else {// 默认待分配 (更改 未挂起和挂起都可以分配)
					currStatusList.add("0");
				}
			} else if ("2".equals(secondNode)) {
				if ((appStatus != null && !"".equals(appStatus) && !"-1".equals(appStatus))
						|| ("24".equals(appStatus2))) {
					if ("1".equals(appStatus) && "24".equals(appStatus2)) {// 队列中
																			// 待分配已挂起
						currStatusList.add("7");
					} else if ("1".equals(appStatus) && !"24".equals(appStatus2)) {// 队列中待分配未挂起
						currStatusList.add("6");
					} else if ("2".equals(appStatus) && !"24".equals(appStatus2)) {// 队列中已分配到组
																					// 或人的
																					// 已分配未挂起
						currStatusList.add("1");
						currStatusList.add("3");
					} else if ("2".equals(appStatus) && "24".equals(appStatus2)) {// 到组或人
																					// 已分配
																					// 已挂起
						currStatusList.add("2");
						currStatusList.add("4");
					} else if ((appStatus == null || "".equals(appStatus) || "-1".equals(appStatus))
							&& ("24".equals(appStatus2))) {// 挂起标签(显示队列中 组中 挂起件)
						currStatusList.add("7");
						currStatusList.add("2");
					} else if ("3".equals(appStatus)) {
						currStatusList.add("7");
					}
				} else {// 待分配（更改 未挂起和挂起都可以分配）
					currStatusList.add("6");
				}
			} else if ("3".equals(secondNode)) {
				if ((appStatus != null && !"".equals(appStatus) && !"-1".equals(appStatus))
						|| ("24".equals(appStatus2))) {
					if ("1".equals(appStatus) && "24".equals(appStatus2)) {// 组中
																			// 待分配已挂起
						currStatusList.add("2");
					} else if ("1".equals(appStatus) && !"24".equals(appStatus2)) {// 组中待分配未挂起
						currStatusList.add("1");
					} else if ("2".equals(appStatus) && !"24".equals(appStatus2)) {// 已分配未挂起
						currStatusList.add("3");
					} else if ("2".equals(appStatus) && "24".equals(appStatus2)) {// 已分配
																					// 已挂起
						currStatusList.add("4");
					} else if ((appStatus == null || "".equals(appStatus) || "-1".equals(appStatus))
							&& ("24".equals(appStatus2))) {// 挂起标签(显示 组中 个人 挂起件)
						currStatusList.add("2");
						currStatusList.add("4");
					} else if ("3".equals(appStatus)) {
						currStatusList.add("2");
						currStatusList.add("4");
					}
				} else {// 待分配（更改 未挂起和挂起都可以分配）
					currStatusList.add("1");
				}
				if (!"system".equals(userCode) && !"".equals(userCode)) {
					AllotCommon allotCommon = allotCommonDao.selectGroupByUserCode(userCode);
					String currGroup = allotCommon.getOrgNo();
					map.put("currGroup", currGroup);
				}
			}
			map.put("currStatusList", currStatusList);
		}
		// 是否持卡客户
		String cardStr = "";
		if (!"".equals(isHaveCardCust) && isHaveCardCust != null && !"-1".equals(isHaveCardCust)) {
			if ("0".equals(isHaveCardCust)) {
				cardStr = "AND (ac.EXIST_CARD_FLAG ='0' or ac.EXIST_CARD_FLAG is null )";
			} else if ("1".equals(isHaveCardCust)) {
				cardStr = "AND ac.EXIST_CARD_FLAG ='1' ";
			} else if ("2".equals(isHaveCardCust)) {// 排查
				cardStr = "AND ac.EXIST_CARD_FLAG ='2' ";
			}
		}
		map.put("cardStr", cardStr);
		// 异常进件渠道
		String safeStr = "";
		if ("0".equals(nosafePromoter)) {// 否 不是 不安全推广人员件
			safeStr = "and (promoter.promoter_no is null or promoter.CURR_STATUS='0' or promoter.CURR_STATUS is null ) ";
		} else if ("1".equals(nosafePromoter)) {// 是 不安全推广人员件
			safeStr = "and  promoter.CURR_STATUS='1' ";
		}
		map.put("safeStr", safeStr);
		// 公安链接状态-wenyh
		String pubStr = "";
		if ("1".equals(ydjFlag)){
			if ("0".equals(pubPolice)) {
				pubStr = "AND (compare.RESULT_GMSFHM = '不一致' or compare.RESULT_GMSFHM is null or compare.RESULT_XM= '不一致' or compare.RESULT_XM is null)";
			} else if ("1".equals(pubPolice)) {
				pubStr = "AND (compare.RESULT_GMSFHM = '一致' and compare.RESULT_XM='一致')";
			}
		}else{
			if ("0".equals(pubPolice)) {
				pubStr = "AND (((compare.ERROR_MESSAGE is not null and INSTR(compare.ERROR_MESSAGE, '库中无此号') > 0) OR " + 
							 "(compare.RESULT_GMSFHM = '一致' AND compare.RESULT_XM = '不一致') OR " + 
							 "(compare.RESULT_GMSFHM = '不一致' AND compare.RESULT_XM = '一致') OR " + 
							 "(compare.RESULT_GMSFHM = '不一致' AND compare.RESULT_XM = '不一致')) OR " + 
							 "((compare.RESULT_GMSFHM = '一致' AND compare.RESULT_XM = '一致') AND substr(sfrz.AUTH_RESULT, 1, 1) = '5') OR " + 
							 "(compare.APP_ID IS NULL AND substr(sfrz.AUTH_RESULT, 1, 1) = '5'))";
			} else if ("1".equals(pubPolice)) {
				pubStr = "AND (((compare.RESULT_GMSFHM = '一致' AND compare.RESULT_XM = '一致') AND " + 
							 "(substr(sfrz.AUTH_RESULT, 1, 1) = '0' OR sfrz.app_id IS NULL OR (substr(sfrz.AUTH_RESULT, 1, 1) != '0' AND substr(sfrz.AUTH_RESULT, 1, 1) != '5'))) OR " + 
							 "(compare.app_id is null AND substr(sfrz.AUTH_RESULT, 1, 1) = '0')) ";
			} else if ("-1".equals(pubPolice)) {
				pubStr = "AND (compare.APP_ID IS NULL AND (substr(sfrz.AUTH_RESULT, 1, 1) != '0' AND substr(sfrz.AUTH_RESULT, 1, 1) != '5'))";
			}
		}
		map.put("pubStr", pubStr);
		// 团办号
		if (!"".equals(c4ApBatchStr)) {
			List<String> c4ApBatchList = new ArrayList<String>();
			String[] arr = c4ApBatchStr.split(",");
			c4ApBatchList = Arrays.asList(arr);
			map.put("c4ApBatchList", c4ApBatchList);
		}
		// 团办号
		if (!"".equals(c5JcTypeStr)) {
			List<String> c5JcTypeList = new ArrayList<String>();
			String[] arr = c5JcTypeStr.split(",");
			c5JcTypeList = Arrays.asList(arr);
			map.put("c5JcTypeList", c5JcTypeList);
		}
		// 推广机构号
		if (!"".equals(c5AbCodeStr)) {
			List<String> c5AbCodeList = new ArrayList<String>();
			String[] arr = c5AbCodeStr.split(",");
			c5AbCodeList = Arrays.asList(arr);
			map.put("c5AbCodeList", c5AbCodeList);
		}
		// 推广人
		if (!"".equals(c4AbUser)) {
			List<String> c4AbUserList = new ArrayList<String>();
			String[] arr = c4AbUser.split(",");
			c4AbUserList = Arrays.asList(arr);
			map.put("c4AbUserList", c4AbUserList);
		}
		// 质检叫停 2 不能分件
		String stopStr = "";
		if ("1".equals(stopStatus)) {
			stopStr = "AND info.STOP_FLAG ='2' ";
		} else {
			stopStr = "AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
		}
		map.put("stopStr", stopStr);
		// 天御分 '0~100：欺诈分值。值越高欺诈可能性越大' 有-1可能
		String riskScore = "";
		if ((!"".equals(minRiskScore)) && (!"".equals(maxRiskScore))) {// 都不为空
			int min = Integer.valueOf(minRiskScore);
			int max = Integer.valueOf(maxRiskScore);
			// 两种情况 1、最小值大于最大值 查询为空的情况
			if (min > max) {
				riskScore = "1";
			} else {// 2、最大值大于最小值
				riskScore = "2";
			}
		} else if (("".equals(minRiskScore)) && (!"".equals(maxRiskScore))) {// 小于最大值
			riskScore = "3";
		} else if ((!"".equals(minRiskScore)) && ("".equals(maxRiskScore))) {// 大于最小值
			riskScore = "4";
		}
		map.put("riskScore", riskScore);
		// 中征信1000评分
		if ((!"".equals(minNumberRead) && minNumberRead != null)
				|| (!"".equals(maxNumberRead) && maxNumberRead != null)) {
			if ((!"".equals(minNumberRead) && minNumberRead != null)
					&& (!"".equals(maxNumberRead) && maxNumberRead != null)) {
				int min3 = Integer.valueOf(minNumberRead);
				int max3 = Integer.valueOf(maxNumberRead);
				int a = min3 - max3;
				if (a > 0) {
					map.put("minNumberRead", "-2");
					map.put("maxNumberRead", "-2");
				}
			}
		}
		// 决策额度
		StringBuffer proposedLmtStr = new StringBuffer();
		if ((!"".equals(minProposedLmt) && minProposedLmt != null && !"0.0".equals(minProposedLmt))
				|| (!"".equals(maxProposedLmt) && maxProposedLmt != null && !"0.0".equals(maxProposedLmt))
				|| (!"".equals(pbocRstFinDesp) && pbocRstFinDesp != null)) {
			// 两者都不为空时
			if ((!"".equals(minProposedLmt) && minProposedLmt != null && !"0.0".equals(minProposedLmt))
					&& (!"".equals(maxProposedLmt) && maxProposedLmt != null && !"0.0".equals(maxProposedLmt))) {
				Double min4 = Double.valueOf(minProposedLmt);
				Double max4 = Double.valueOf(maxProposedLmt);
				if (min4 > max4) {// 为空时
					if ("1".equals(ydjFlag)) {// 易达金
						proposedLmtStr.append("AND ydjBlaze.PROPOSED_LMT is null");
					} else if ("2".equals(ydjFlag)) {// 标准卡
						proposedLmtStr.append("AND sdBlaze.PROPOSED_LMT is null");
					} else if ("".equals(ydjFlag)) {// 反欺诈
						proposedLmtStr.append("AND (sdBlaze.PROPOSED_LMT is null or ydjBlaze.PROPOSED_LMT is null)");
					}
				} else {// 不为空时
					if ("1".equals(ydjFlag)) {// 易达金
						proposedLmtStr.append("AND (ydjBlaze.PROPOSED_LMT >=" + minProposedLmt
								+ " and ydjBlaze.PROPOSED_LMT <=" + maxProposedLmt + " )");
					} else if ("2".equals(ydjFlag)) {// 标准卡
						proposedLmtStr.append("AND (sdBlaze.PROPOSED_LMT  >=" + minProposedLmt
								+ " and sdBlaze.PROPOSED_LMT <=" + maxProposedLmt + " )");
					} else if ("".equals(ydjFlag)) {// 反欺诈
						proposedLmtStr.append("AND ((ydjBlaze.PROPOSED_LMT  >=" + minProposedLmt
								+ " and ydjBlaze.PROPOSED_LMT <=" + maxProposedLmt + " ) or ");
						proposedLmtStr.append(" (sdBlaze.PROPOSED_LMT  >=" + minProposedLmt
								+ " and sdBlaze.PROPOSED_LMT <=" + maxProposedLmt + " ))");
					}
				}
			} else {
				if ((!"".equals(minProposedLmt) && minProposedLmt != null && !"0.0".equals(minProposedLmt))
						&& ("".equals(maxProposedLmt) || maxProposedLmt != null)) {
					if ("1".equals(ydjFlag)) {// 易达金
						proposedLmtStr.append("AND (ydjBlaze.PROPOSED_LMT  >=" + minProposedLmt + " )");
					} else if ("2".equals(ydjFlag)) {// 标准卡
						proposedLmtStr.append("AND (sdBlaze.PROPOSED_LMT  >=" + minProposedLmt + "  )");
					} else if ("".equals(ydjFlag)) {// 反欺诈
						proposedLmtStr.append("AND ((ydjBlaze.PROPOSED_LMT  >=" + minProposedLmt + " ) or ");
						proposedLmtStr.append("(sdBlaze.PROPOSED_LMT  >=" + minProposedLmt + " ))");
					}
				} else if (("".equals(minProposedLmt) || minProposedLmt != null)
						&& (!"".equals(maxProposedLmt) && maxProposedLmt != null && !"0.0".equals(maxProposedLmt))) {
					if ("1".equals(ydjFlag)) {// 易达金
						proposedLmtStr.append("AND ( ydjBlaze.PROPOSED_LMT <=" + maxProposedLmt + " )");
					} else if ("2".equals(ydjFlag)) {// 标准卡
						proposedLmtStr.append("AND ( sdBlaze.PROPOSED_LMT <=" + maxProposedLmt + " )");
					} else if ("".equals(ydjFlag)) {// 反欺诈
						proposedLmtStr.append("AND (( ydjBlaze.PROPOSED_LMT <=" + maxProposedLmt + " ) or ");
						proposedLmtStr.append(" ( sdBlaze.PROPOSED_LMT <=" + maxProposedLmt + " ))");
					}
				}
			}
			map.put("proposedLmtStr", proposedLmtStr.toString());
		}
		// 征信策略结果和描述 易达金区分 主卡和套卡
		if (!"01".equals(currNode)) {
			if ((!"".equals(creditDecisionResult) && creditDecisionResult != null
					&& !"-1".equals(creditDecisionResult))) {
				List<String> creditResultList = new ArrayList<String>();
				String[] arr = creditDecisionResult.split(",");
				creditResultList = Arrays.asList(arr);
				map.put("creditResultList", creditResultList);
			}
			if ((!"".equals(creditDecisionLayer) && creditDecisionLayer != null && !"-1".equals(creditDecisionLayer))) {
				List<String> creditLayerList = new ArrayList<String>();
				String[] arr = creditDecisionLayer.split(",");
				creditLayerList = Arrays.asList(arr);
				map.put("creditLayerList", creditLayerList);
			}
		}
		// 反欺诈决策结果
		if (!"".equals(fqzValue)) {
			List<String> fqzValueList = new ArrayList<String>();
			String[] arr = fqzValue.split(",");
			fqzValueList = Arrays.asList(arr);
			map.put("fqzValueList", fqzValueList);
		}
		// 天御风险码及等级 有一个不为零时查天御风险表
		if (!"".equals(riskCode) || !"".equals(riskLevel)) {
			if (!"".equals(riskCode)) {
				List<String> riskcodeList = new ArrayList<String>();
				String[] arr = riskCode.split(",");
				riskcodeList = Arrays.asList(arr);
				map.put("riskcodeList", riskcodeList);
			}
			if (!"".equals(riskLevel)) {
				List<String> riskValueList = new ArrayList<String>();
				String[] arr = riskLevel.split(",");
				riskValueList = Arrays.asList(arr);
				map.put("riskValueList", riskValueList);
			}
			map.put("tianyu", "tianyu");
		}
		// 过件码1
		if (!"".equals(blockCode1)) {
			List<String> creditList = new ArrayList<String>();
			String[] arr = blockCode1.split(",");
			creditList = Arrays.asList(arr);
			map.put("creditList", creditList);
		}
		// 过件码2
		if (!"".equals(blockCode2)) {
			List<String> blockList = new ArrayList<String>();
			String[] arr = blockCode2.split(",");
			blockList = Arrays.asList(arr);
			map.put("blockList", blockList);
		}
		// 征信评分
		String triggerType = "";
		if ((!"".equals(triggerType1)) && (!"".equals(triggerType2))) {// 都不为空
			int min = Integer.valueOf(triggerType1);
			int max = Integer.valueOf(triggerType2);
			// 两种情况 1、最小值大于最大值 查询为空的情况
			if (min > max) {
				triggerType = "1";
			} else {// 2、最大值大于最小值
				triggerType = "2";
			}
		} else if (("".equals(triggerType1)) && (!"".equals(triggerType2))) {// 小于最大值
			triggerType = "3";
		} else if ((!"".equals(triggerType1)) && ("".equals(triggerType2))) {// 大于最小值
			triggerType = "4";
		}
		map.put("triggerType", triggerType);
		if (!"".equals(wsCode)) {// 网申渠道代码 新客群二期
			map.put("wsCodeList", Arrays.asList(wsCode.split(",")));
		}
		if (!"".equals(decisionResult)) {// 授信决策结果
			map.put("decisionResultList", Arrays.asList(decisionResult.split(",")));
		}
		if (!"".equals(custFlag)) {// 客群标签
			map.put("custFlagInfo", custFlag.replace(",", "|"));
		}
		String fenTime = "";
		if ("2".equals(appStatus) && ("1".equals(ydjFlag) || "".equals(ydjFlag))
				&& (!"".equals(fenTime1) || !"".equals(fenTime2))) {// 易达金或反欺诈分件查询
																	// 时间字符
			if ("1".equals(secondNode)) {// 池
				fenTime = "2";
			} else if ("3".equals(secondNode)) {
				fenTime = "3";
			}
		} else if ("2".equals(appStatus) && "2".equals(ydjFlag) && (!"".equals(fenTime1) || !"".equals(fenTime2))) {// 标准卡分件查询
			if ("1".equals(secondNode) && "02".equals(currNode)) {
				fenTime = "1";
			} else if ("1".equals(secondNode) && !"02".equals(currNode)) {
				fenTime = "2";
			} else if ("2".equals(secondNode)) {
				fenTime = "2";
			} else if ("3".equals(secondNode)) {
				fenTime = "3";
			}
		}
		map.put("fenTime", fenTime);
		// 预审结论及预审码值
		String approveResult = (String) map.get("approveResult") == null ? "" : (String) map.get("approveResult");// 预审结论
		String resultInfo = (String) map.get("resultInfo") == null ? "" : (String) map.get("resultInfo");// 预审结论码值
		if (!"".equals(approveResult) || !"".equals(resultInfo)) {
			if (!"".equals(resultInfo)) {// 预审结论拒绝时
				map.put("resultInfoList", Arrays.asList(resultInfo.split(",")));
			}
			map.put("YsjlStr", "YsjlStr");
		}
		// 多选且包含未发起查询的申请件 特殊处理
		String onlineTime = (String) map.get("onlineTime") == null ? "" : (String) map.get("onlineTime");// 在网时长
		if (!"".equals(onlineTime)) {// 在网时长
			map.put("onlineTimeList", Arrays.asList(onlineTime.split(",")));
			if (onlineTime.contains("W")) {// 包含未发起查询
				map.put("onlineTimeStr", "1");
			} else {// 不包含
				map.put("onlineTimeStr", "2");
			}
		}
		String phoneCertification = (String) map.get("phoneCertification") == null ? ""
				: (String) map.get("phoneCertification");// 手机实名制
		if (!"".equals(phoneCertification)) {
			map.put("phoneList", Arrays.asList(phoneCertification.split(",")));
		}
		int allResult=0;
		List<AllotApply> list=new ArrayList<AllotApply>();
		//分页查询标识  rownumFlag false 不分页  true  分页
		String rownumFlag=(String) map.get("rownumFlag")==null?"":(String) map.get("rownumFlag");
		if("1".equals(strType)){//易达金或反欺诈分件、转移、回收查询(申请件数量大时候使用)
			if("true".equals(rownumFlag)){//分页查询
				int curNum=(Integer) map.get("curNum");
				int pageNum=(Integer) map.get("pageNum");
				if("1".equals(ydjFlag)){
					list=getAllotApplyDao().selectYdjAppNoByConditions(map,curNum, pageNum);
				}else if("".equals(ydjFlag)){
					list=getAllotApplyDao().selectFraudAppNoByConditions(map,curNum, pageNum);//wenyh-20200701
				}
			} else {// 易达金或反欺诈一次性全部查询
				if ("1".equals(ydjFlag)) {
					list = getAllotApplyDao().selectYdjAppNoByConditions(map);
				} else if ("".equals(ydjFlag)) {
					list = getAllotApplyDao().selectFraudAppNoByConditions(map);//wenyh-20200701
				}
			}
		} else if ("2".equals(strType)) {// 分页页面展示查询
			if ("1".equals(ydjFlag)) {// 易达金
				allResult = getAllotApplyDao().selectYdjCountByConditions(map);
			} else if ("2".equals(ydjFlag)) {// 标准卡
				allResult = getAllotApplyDao().selectBzkCountByConditions(map);
			} else {// 反欺诈
				allResult = getAllotApplyDao().selectFraudCountByConditions(map);//wenyh-20200701
			}
			if (allResult > 0) {
				int curNum = (Integer) map.get("curNum");
				int pageNum = (Integer) map.get("pageNum");
				if ("1".equals(ydjFlag)) {
					list = getAllotApplyDao().selectYdjByConditions(map, curNum, pageNum);
				} else if ("2".equals(ydjFlag)) {
					list = getAllotApplyDao().selectBzkByConditions(map, curNum, pageNum);
				} else {
					list = getAllotApplyDao().selectFraudByConditions(map, curNum, pageNum);//wenyh-20200701
				}
				AllotApply aly = new AllotApply();
				// 查询组员中文姓名
				for (int k = 0; k < list.size(); k++) {
					aly = list.get(k);
					String currUserName = "";
					String currUser = "";
					String lastGroup = "";
					String currGroup = "";
					if (aly != null) {
						// 当前用户
						currUser = aly.getCurrUser();
						// 当前组
						currGroup = aly.getCurrGroup();
						String appId2 = aly.getAppId();
						if (currUser != null) {
							AllotCommon allotCommon = allotCommonDao.selectGroupByUserCode(currUser);
							if (allotCommon != null) {
								currUserName = allotCommon.getUserName();
								if (currUserName != null) {
									aly.setCurrUserName(currUserName);
								}
								lastGroup = allotCommon.getOrgName();
								if (lastGroup != null) {
									aly.setLastGroup(lastGroup);
								}
							}
						} else if (currGroup != null) {
							AllotCommon allotCommon = allotCommonDao.selectGroupByOrgNo(currGroup);
							if (allotCommon != null) {
								aly.setLastGroup(allotCommon.getOrgName());
							}
						}
						// c1Cname c1CoName 判断姓名和单位是否为空 为空查C2表
						String c1Cname = aly.getC1Cname() != null ? aly.getC1Cname() : "";
						String c1CoName = aly.getC1CoName() != null ? aly.getC1CoName() : "";
						if ("".equals(c1Cname) || "".equals(c1CoName)) {
							Map applC2Map = getAllotApplyDao().selectApplC2(appId2);
							if (applC2Map != null && applC2Map.size() > 0) {
								aly.setC1Cname(StringUtils.trimToEmpty((String) applC2Map.get("c2_cname")));
								aly.setC1CoName(StringUtils.trimToEmpty((String) applC2Map.get("c2_coname")));
							}
						}
					}
				}
			}
		} else if ("3".equals(strType)) {// 符合条件申请件数量查询
			if ("1".equals(ydjFlag)) {// 易达金 与套卡算一件
				allResult = getAllotApplyDao().selectYdjNumByConditions(map);
			} else if ("2".equals(ydjFlag)) {// 标准卡
				allResult = getAllotApplyDao().selectBzkCountByConditions(map);
			} else {// 反欺诈
				allResult = getAllotApplyDao().selectFraudNumByConditions(map);//wenyh-20200701
			}

		} else if ("4".equals(strType)) {// 标准卡分件、转移、回收查询
			if ("true".equals(rownumFlag)) {// 分页查询
				int curNum = (Integer) map.get("curNum");
				int pageNum = (Integer) map.get("pageNum");
				// 新修改
				/*
				 * if("1".equals(secondNode)&&"01".equals(currNode)){
				 * map.put("jiuCuoFlag", "1"); }
				 */
				list = getAllotApplyDao().selectBzkByConditions(map, curNum, pageNum);
				// 池分件避免并发冲突 新修改
				/*
				 * if("1".equals(secondNode)){ //更新标识 REVIEW_STATUS 改为1 待分件状态
				 * try { map.put("jiuCuoFlag", "1");
				 * list=getAllotApplyDao().selectBzkByConditions(map, curNum,
				 * pageNum); updateReviewStatus(list); map.put("jiuCuoFlag",
				 * "2"); list=getAllotApplyDao().selectBzkByConditions(map,
				 * curNum, pageNum); } catch (Exception e) { throw new
				 * RuntimeException("本次分件发生冲突,事务回滚"); } }else{
				 * list=getAllotApplyDao().selectBzkByConditions(map, curNum,
				 * pageNum); }
				 */
			} else {// 一次性申请件全部查询
				list = getAllotApplyDao().selectBzkByConditions(map);
			}
		} else if ("5".equals(strType)) {// 标准卡/易达金自动分件数量统计
			if ("1".equals(ydjFlag)) {
				allResult = autoApplyDao.selectYdjAuto(map);
			} else if ("2".equals(ydjFlag)) {
				allResult = autoApplyDao.selectBzkAuto(map);
			}
		} else if ("6".equals(strType)) {// 自动分件组到人（只分池或队列自动分到组的申请件集合）
			int curNum = (Integer) map.get("curNum");
			int pageNum = (Integer) map.get("pageNum");
			if ("1".equals(ydjFlag)) {
				list = autoApplyDao.selectYdjAutoList(map, curNum, pageNum);
			} else if ("2".equals(ydjFlag)) {
				list = autoApplyDao.selectBzkAutoList(map, curNum, pageNum);
			}
		}
		if ("2".equals(strType)) {
			log.info("分件条件页面展示查询------完成!");
		} else if ("3".equals(strType)) {
			log.info("分件条件查询数量------完成!allResult=" + allResult);
		} else if ("4".equals(strType)) {
			log.info("分件条件更新查询------完成!");
		}
		Map<String, Object> allMap = new HashMap<String, Object>();
		allMap.put("list2", list);
		allMap.put("allResult", allResult);
		return allMap;
	}

	// 标准卡征信策略结果查询
	public String queryResultByAppId(String appId) throws CoreException {
		return allotBzkResultDao.selectResultByAppId(appId);
	}

	@Override
	public int updateReviewStatus(List<AllotApply> allotApplyList) throws Exception {
		int result = 0;
		// 数据源获取数据库连接
		Connection conn = sqlExecutor.getConnection();
		if (conn == null) {
			return result;
		}
		// 设置为不自动提交
		conn.setAutoCommit(false);
		StringBuffer sbf = new StringBuffer();
		sbf.append(
				"update OPAS_ALLOT_APPLY_ALLOT set REVIEW_STATUS=?, ALLOT_VERSION=? where APP_ID =? AND ALLOT_VERSION=?");
		String sql = sbf.toString();
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			int evenyCount = allotApplyList.size();
			// 循环提交的次数
			for (int i = 0; i < allotApplyList.size(); i++) {
				AllotApply allotApply = allotApplyList.get(i);
				String appId = allotApply.getAppId();
				ps.setString(1, "1");
				ps.setInt(2, (allotApply.getAllotVersion() + 1));
				ps.setString(3, appId);
				ps.setInt(4, allotApply.getAllotVersion());
				ps.addBatch();
			}
			ps.executeBatch();
			int updateCount = ps.getUpdateCount();
			if (evenyCount == updateCount) {
				conn.commit();
				ps.clearBatch();
				result++;
			} else {
				if (conn != null && (!conn.isClosed())) {
					conn.rollback();
				}
				throw new RuntimeException("本次分件发生冲突,事务回滚");
			}
		} catch (SQLException e) {
			if (conn != null && (!conn.isClosed())) {
				conn.rollback();
			}
			throw new RuntimeException(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}


	/**
	 * 查询待进入人工列表总记录数
	 * @param params
	 * @return
	 */
	@Override
	public int queryWaitingEnterCount(Map<String, Object> params) {
		log.info("AllotApplyServiceImpl.queryWaitingEnterCount 查询符合筛选条件的申请件   start ");
		String shareTime = (String) params.get("shareTime") == null ? "" : (String) params.get("shareTime");
		String crtTimeStart = (String) params.get("crtTimeStart") == null ? "" : (String) params.get("crtTimeStart");
		String crtTimeEnd = (String) params.get("crtTimeEnd") == null ? "" : (String) params.get("crtTimeEnd");
		String appTimeStart = (String) params.get("appTimeStart") == null ? "" : (String) params.get("appTimeStart");
		String appTimeEnd = (String) params.get("appTimeEnd") == null ? "" : (String) params.get("appTimeEnd");
		// 时间
		if ("1".equals(shareTime)) {// 条形码日期,将进件日期致空
			crtTimeStart = "";
			crtTimeEnd = "";
			params.put("crtTimeStart", crtTimeStart);
			params.put("crtTimeEnd", crtTimeEnd);
		} else if ("2".equals(shareTime)) {// 进件日期,将条形码日期致空
			params.put("appTimeStart", "");
			params.put("appTimeEnd", "");
		} else if ("".equals(shareTime) || shareTime == null) {
			params.put("crtTimeStart", "");
			params.put("crtTimeEnd", "");
			params.put("appTimeStart", "");
			params.put("appTimeEnd", "");
		}
		// 条形码日期格式化
		if (!"".equals(appTimeStart) && appTimeStart != null && !"-1".equals(appTimeStart)) {
			String appDateStart = (appTimeStart.replace("-", "")).substring(2, appTimeStart.replace("-", "").length());
			params.put("appTimeStart", appDateStart);
		}
		if (!"".equals(appTimeEnd) && appTimeEnd != null && !"-1".equals(appTimeEnd)) {
			String appDateEnd = (appTimeEnd.replace("-", "")).substring(2, appTimeEnd.replace("-", "").length());
			params.put("appTimeEnd", appDateEnd);
		}
		int waitingEnterCount = allotApplyDao.queryWaitingEnterCount(params);
		log.info("AllotApplyServiceImpl.queryWaitingEnterCount 查询符合筛选条件的申请件   end,waitingEnterCount: ", waitingEnterCount);
		return waitingEnterCount;
	}
	
	/**
	 * 查询待进入人工列表数据
	 * @param params
	 * @param curNum
	 * @param pageNum
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryWaitingEnterList(Map<String, Object> params, int curNum,
			int pageNum) {
		log.info("AllotApplyServiceImpl.queryWaitingEnterList 查询符合筛选条件的申请件   start ");
		String shareTime = (String) params.get("shareTime") == null ? "" : (String) params.get("shareTime");
		String crtTimeStart = (String) params.get("crtTimeStart") == null ? "" : (String) params.get("crtTimeStart");
		String crtTimeEnd = (String) params.get("crtTimeEnd") == null ? "" : (String) params.get("crtTimeEnd");
		String appTimeStart = (String) params.get("appTimeStart") == null ? "" : (String) params.get("appTimeStart");
		String appTimeEnd = (String) params.get("appTimeEnd") == null ? "" : (String) params.get("appTimeEnd");
		// 时间
		if ("1".equals(shareTime)) {// 条形码日期,将进件日期致空
			crtTimeStart = "";
			crtTimeEnd = "";
			params.put("crtTimeStart", crtTimeStart);
			params.put("crtTimeEnd", crtTimeEnd);
		} else if ("2".equals(shareTime)) {// 进件日期,将条形码日期致空
			params.put("appTimeStart", "");
			params.put("appTimeEnd", "");
		} else if ("".equals(shareTime) || shareTime == null) {
			params.put("crtTimeStart", "");
			params.put("crtTimeEnd", "");
			params.put("appTimeStart", "");
			params.put("appTimeEnd", "");
		}
		// 条形码日期格式化
		if (!"".equals(appTimeStart) && appTimeStart != null && !"-1".equals(appTimeStart)) {
			String appDateStart = (appTimeStart.replace("-", "")).substring(2, appTimeStart.replace("-", "").length());
			params.put("appTimeStart", appDateStart);
		}
		if (!"".equals(appTimeEnd) && appTimeEnd != null && !"-1".equals(appTimeEnd)) {
			String appDateEnd = (appTimeEnd.replace("-", "")).substring(2, appTimeEnd.replace("-", "").length());
			params.put("appTimeEnd", appDateEnd);
		}
		List<Map<String, Object>> waitingEnterList = allotApplyDao.queryWaitingEnterList(params, curNum, pageNum);
		log.info("AllotApplyServiceImpl.queryWaitingEnterList 查询符合筛选条件的申请件   end ");
		return waitingEnterList;
	}
}
