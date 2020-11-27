package com.huaxia.opas.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.domain.PBOC;
import com.huaxia.opas.domain.PBOCAnnounce;
import com.huaxia.opas.domain.PBOCCreditCue;
import com.huaxia.opas.domain.PBOCCreditCue.CreditScore;
import com.huaxia.opas.domain.PBOCCreditCue.CreditSummaryCue;
import com.huaxia.opas.domain.PBOCCreditDetail;
import com.huaxia.opas.domain.PBOCCreditDetail.AssetDisposition;
import com.huaxia.opas.domain.PBOCCreditDetail.AssurerRepay;
import com.huaxia.opas.domain.PBOCCreditDetail.GuaranteeInfo;
import com.huaxia.opas.domain.PBOCCreditDetail.GuaranteeInfo.Guarantee;
import com.huaxia.opas.domain.PBOCCreditDetail.Loan;
import com.huaxia.opas.domain.PBOCCreditDetail.Loancard;
import com.huaxia.opas.domain.PBOCCreditDetail.StandardLoancard;
import com.huaxia.opas.domain.PBOCHeader;
import com.huaxia.opas.domain.PBOCHeader.MessageHeader;
import com.huaxia.opas.domain.PBOCHeader.QueryReq;
import com.huaxia.opas.domain.PBOCInfoSummary;
import com.huaxia.opas.domain.PBOCInfoSummary.OverdueAndFellback;
import com.huaxia.opas.domain.PBOCInfoSummary.OverdueAndFellback.FellbackSummary;
import com.huaxia.opas.domain.PBOCInfoSummary.OverdueAndFellback.FellbackSummary.AssetDispositionSum;
import com.huaxia.opas.domain.PBOCInfoSummary.OverdueAndFellback.FellbackSummary.AssureerRepaySum;
import com.huaxia.opas.domain.PBOCInfoSummary.OverdueAndFellback.FellbackSummary.FellbackDebtSum;
import com.huaxia.opas.domain.PBOCInfoSummary.OverdueAndFellback.OverdueSummary;
import com.huaxia.opas.domain.PBOCInfoSummary.ShareAndDebt;
import com.huaxia.opas.domain.PBOCPersonalInfo;
import com.huaxia.opas.domain.PBOCPersonalInfo.Identity;
import com.huaxia.opas.domain.PBOCPersonalInfo.Professional;
import com.huaxia.opas.domain.PBOCPersonalInfo.Residence;
import com.huaxia.opas.domain.PBOCPersonalInfo.Spouse;
import com.huaxia.opas.domain.PBOCPublicInfo;
import com.huaxia.opas.domain.PBOCPublicInfo.AccFund;
import com.huaxia.opas.domain.PBOCPublicInfo.AdminAward;
import com.huaxia.opas.domain.PBOCPublicInfo.AdminPunishment;
import com.huaxia.opas.domain.PBOCPublicInfo.CivilJudgement;
import com.huaxia.opas.domain.PBOCPublicInfo.Competence;
import com.huaxia.opas.domain.PBOCPublicInfo.EndowmentInsuranceDeliver;
import com.huaxia.opas.domain.PBOCPublicInfo.EndowmentInsuranceDeposit;
import com.huaxia.opas.domain.PBOCPublicInfo.ForceExecution;
import com.huaxia.opas.domain.PBOCPublicInfo.Salvation;
import com.huaxia.opas.domain.PBOCPublicInfo.TaxArrear;
import com.huaxia.opas.domain.PBOCPublicInfo.TelPayment;
import com.huaxia.opas.domain.PBOCPublicInfo.Vehicle;
import com.huaxia.opas.domain.PBOCQueryRecord;
import com.huaxia.opas.domain.PBOCQueryRecord.RecordInfo;
import com.huaxia.opas.domain.PBOCQueryRecord.RecordSummary;
import com.huaxia.opas.interfaces.in.MessageParser;

/**
 * 人行报文解析器
 * 
 * @author zhiguo.li
 * @version 1.1
 * @history 修改历史记录
 *          --------------------------------------------------------------------
 *          ------- 2017-09-21 zhiguo.li v1.0 初始版本 2017-10-10 zhiguo.li v1.1
 *          "特殊交易信息"标签域下标取值异常修复
 *          --------------------------------------------------------------------
 *          -------
 */
public class PBOCMessageParser implements MessageParser<PBOC> {

	private final static Logger logger = LoggerFactory.getLogger(PBOCMessageParser.class);

	@SuppressWarnings("unchecked")
	@Override
	public PBOC parse(String message) throws Exception {
		if (message == null) {
			throw new IllegalArgumentException("人行报文为空");
		}

		Document document = null;
		try {
			document = DocumentHelper.parseText(message);
		} catch (DocumentException e) {
			if (logger.isErrorEnabled()) {
				logger.error("[人行数据解析] 构建XML解析异常：{}", e.getMessage());
			}
			e.printStackTrace();
			throw e;
		}

		PBOC pboc = new PBOC();

		// 报文临时节点
		Node xNode = null;

		// 报文临时元素
		Element xElement = null;

		// ===============================================
		// == L1 - 报告头（Header）
		// ===============================================
		Node headerNode = document.selectSingleNode("/ReportMessage/Header");
		if (headerNode != null) {
			PBOCHeader header = new PBOCHeader();

			// [L2] 报告头描述（MessageHeader）
			Node messageHeaderNode = headerNode.selectSingleNode("MessageHeader");
			if (messageHeaderNode != null) {
				MessageHeader messageHeader = header.new MessageHeader();

				// [L3] 报告编号
				xNode = messageHeaderNode.selectSingleNode("ReportSN");
				if (xNode != null) {
					messageHeader.setReportSN(xNode.getText());
				}

				// [L3] 查询请求时间
				xNode = messageHeaderNode.selectSingleNode("QueryTime");
				if (xNode != null) {
					messageHeader.setQueryTime(xNode.getText());
				}

				// [L3] 报告时间
				xNode = messageHeaderNode.selectSingleNode("ReportCreateTime");
				if (xNode != null) {
					messageHeader.setReportCreateTime(xNode.getText());
				}

				header.setMessageHeader(messageHeader);
				xNode = null;
			}

			// [L2] 查询信息（QueryReq）
			Node queryReqNode = headerNode.selectSingleNode("QueryReq");
			if (queryReqNode != null) {
				QueryReq queryReq = header.new QueryReq();

				// [L3] 产品种类
				xNode = queryReqNode.selectSingleNode("ProductType");
				if (xNode != null) {
					queryReq.setProductType(xNode.getText());
				}

				// [L3] 产品版式
				xNode = queryReqNode.selectSingleNode("Format");
				if (xNode != null) {
					queryReq.setFormat(xNode.getText());
				}

				// [L3] 产品版本
				xNode = queryReqNode.selectSingleNode("FormatVersion");
				if (xNode != null) {
					queryReq.setFormatVersion(xNode.getText());
				}

				// [L3] 被查询者姓名
				xNode = queryReqNode.selectSingleNode("Name");
				if (xNode != null) {
					queryReq.setName(xNode.getText());
				}

				// [L3] 被查询者证件类型
				xNode = queryReqNode.selectSingleNode("Certtype");
				if (xNode != null) {
					queryReq.setCerttype(xNode.getText());
				}

				// [L3] 被查询者证件号码
				xNode = queryReqNode.selectSingleNode("Certno");
				if (xNode != null) {
					queryReq.setCertno(xNode.getText());
				}

				// [L3] 查询原因
				xNode = queryReqNode.selectSingleNode("QueryReason");
				if (xNode != null) {
					queryReq.setQueryReason(xNode.getText());
				}

				// [L3] 查询机构
				xNode = queryReqNode.selectSingleNode("QueryOrg");
				if (xNode != null) {
					queryReq.setQueryOrg(xNode.getText());
				}

				// [L3] 查询操作员
				xNode = queryReqNode.selectSingleNode("UserCode");
				if (xNode != null) {
					queryReq.setUserCode(xNode.getText());
				}

				// [L3] 查询结果提示
				xNode = queryReqNode.selectSingleNode("QueryResultCue");
				if (xNode != null) {
					queryReq.setQueryResultCue(xNode.getText());
				}

				header.setQueryReq(queryReq);
				xNode = null;
			}
			pboc.setHeader(header);
		}

		// ===============================================
		// == L1 - 个人基本信息（PersonalInfo）
		// ===============================================
		Node personalInfoNode = document.selectSingleNode("/ReportMessage/PersonalInfo");
		if (personalInfoNode != null) {
			PBOCPersonalInfo personalInfo = new PBOCPersonalInfo();
			pboc.setPersonalInfo(personalInfo);

			// [L2] 身份信息（Identity）
			Node identityNode = personalInfoNode.selectSingleNode("Identity");
			if (identityNode != null) {
				Identity identity = personalInfo.new Identity();

				// [L3] 性别
				xNode = identityNode.selectSingleNode("Gender");
				if (xNode != null) {
					identity.setGender(xNode.getText());
				}

				// [L3] 出生日期
				xNode = identityNode.selectSingleNode("Birthday");
				if (xNode != null) {
					identity.setBirthday(xNode.getText());
				}

				// [L3] 婚姻状况
				xNode = identityNode.selectSingleNode("MaritalState");
				if (xNode != null) {
					identity.setMaritalState(xNode.getText());
				}

				// [L3] 手机号码
				xNode = identityNode.selectSingleNode("Mobile");
				if (xNode != null) {
					identity.setMobile(xNode.getText());
				}

				// [L3] 单位电话
				xNode = identityNode.selectSingleNode("OfficeTelephoneNo");
				if (xNode != null) {
					identity.setOfficeTelphoneNo(xNode.getText());
				}

				// [L3] 住宅电话
				xNode = identityNode.selectSingleNode("HomeTelephoneNo");
				if (xNode != null) {
					identity.setHomeTelphoneNo(xNode.getText());
				}

				// [L3] 学历
				xNode = identityNode.selectSingleNode("EduLevel");
				if (xNode != null) {
					identity.setEduLevel(xNode.getText());
				}

				// [L3] 学位
				xNode = identityNode.selectSingleNode("EduDegree");
				if (xNode != null) {
					identity.setEduDegree(xNode.getText());
				}

				// [L3] 通讯地址
				xNode = identityNode.selectSingleNode("PostAddress");
				if (xNode != null) {
					identity.setPostAddress(xNode.getText());
				}

				// [L3] 户籍地址
				xNode = identityNode.selectSingleNode("RegisteredAddress");
				if (xNode != null) {
					identity.setRegisteredAddress(xNode.getText());
				}

				personalInfo.setIdentity(identity);
				xNode = null;
			}

			// [L2] 配偶信息（Spouse）
			Node spouseNode = personalInfoNode.selectSingleNode("Spouse");
			if (spouseNode != null) {
				Spouse spouse = personalInfo.new Spouse();

				// [L3] 姓名
				xNode = spouseNode.selectSingleNode("Name");
				if (xNode != null) {
					spouse.setName(xNode.getText());
				}

				// [L3] 证件类型
				xNode = spouseNode.selectSingleNode("Certtype");
				if (xNode != null) {
					spouse.setCerttype(xNode.getText());
				}

				// [L3] 证件号码
				xNode = spouseNode.selectSingleNode("Certno");
				if (xNode != null) {
					spouse.setCertno(xNode.getText());
				}

				// [L3] 工作单位
				xNode = spouseNode.selectSingleNode("Employer");
				if (xNode != null) {
					spouse.setEmployer(xNode.getText());
				}

				// [L3] 联系电话
				xNode = spouseNode.selectSingleNode("TelephoneNo");
				if (xNode != null) {
					spouse.setTelephoneNo(xNode.getText());
				}

				personalInfo.setSpouse(spouse);
				xNode = null;
			}

			// [L2] 居住信息（Residence）
			List<Element> residenceElementList = personalInfoNode.selectNodes("Residence");
			if (residenceElementList != null && residenceElementList.size() > 0) {
				List<Residence> residenceList = new ArrayList<Residence>();
				for (int i = 0; i < residenceElementList.size(); i++) {
					Element residenceElement = residenceElementList.get(i);
					if(residenceElement==null){
						continue;
					}
					Residence residence = personalInfo.new Residence();

					// [L3] 居住地址
					xNode = residenceElement.selectSingleNode("Address");
					if (xNode != null) {
						residence.setAddress(xNode.getText());
					}

					// [L3] 居住状况
					xNode = residenceElement.selectSingleNode("ResidenceType");
					if (xNode != null) {
						residence.setResidenceType(xNode.getText());
					}

					// [L3] 信息更新日期
					xNode = residenceElement.selectSingleNode("GetTime");
					if (xNode != null) {
						residence.setGetTime(xNode.getText());
					}

					residenceList.add(residence);
					xNode = null;
				}
				personalInfo.setResidenceList(residenceList);
			}

			// [L2] 职业信息（Professional）
			List<Element> professionalElementList = personalInfoNode.selectNodes("Professional");
			if (professionalElementList != null && professionalElementList.size() > 0) {
				List<Professional> professionalList = new ArrayList<Professional>();
				for (int i = 0; i < professionalElementList.size(); i++) {
					Element professionalElement = professionalElementList.get(i);
					if(professionalElement==null){
						continue;
					}
					Professional professional = personalInfo.new Professional();

					// [L3] 工作单位
					xNode = professionalElement.selectSingleNode("Employer");
					if (xNode != null) {
						professional.setEmployer(xNode.getText());
					}

					// [L3] 单位地址
					xNode = professionalElement.selectSingleNode("EmployerAddress");
					if (xNode != null) {
						professional.setEmployerAddress(xNode.getText());
					}

					// [L3] 职业
					xNode = professionalElement.selectSingleNode("Occupation");
					if (xNode != null) {
						professional.setOccupation(xNode.getText());
					}

					// [L3] 行业
					xNode = professionalElement.selectSingleNode("Industry");
					if (xNode != null) {
						professional.setIndustry(xNode.getText());
					}

					// [L3] 职务
					xNode = professionalElement.selectSingleNode("Duty");
					if (xNode != null) {
						professional.setDuty(xNode.getText());
					}

					// [L3] 职称
					xNode = professionalElement.selectSingleNode("Title");
					if (xNode != null) {
						professional.setTitle(xNode.getText());
					}

					// [L3] 进入本单位年份
					xNode = professionalElement.selectSingleNode("StartYear");
					if (xNode != null) {
						professional.setStartYear(xNode.getText());
					}

					// [L3] 信息更新日期
					xNode = professionalElement.selectSingleNode("GetTime");
					if (xNode != null) {
						professional.setGetTime(xNode.getText());
					}

					professionalList.add(professional);
					xNode = null;
				}
				personalInfo.setProfessionalList(professionalList);
			}
		}

		// ===============================================
		// == L1 - 信息概要（InfoSummary）
		// ===============================================
		Node infoSummaryNode = document.selectSingleNode("/ReportMessage/InfoSummary");
		if (infoSummaryNode != null) {
			PBOCInfoSummary infoSummary = new PBOCInfoSummary();

			// [L2] 信用提示（CreditCue）
			Node creditCueNode = infoSummaryNode.selectSingleNode("CreditCue");
			if (creditCueNode != null) {
				PBOCCreditCue creditCue = new PBOCCreditCue();

				// [L3] 信用汇总提示（CreditSummaryCue）
				Node creditSummaryCueNode = creditCueNode.selectSingleNode("CreditSummaryCue");
				if (creditSummaryCueNode != null) {
					CreditSummaryCue creditSummaryCue = creditCue.new CreditSummaryCue();

					// [L4] 个人住房贷款笔数
					xNode = creditSummaryCueNode.selectSingleNode("PerHouseLoanCount");
					if (xNode != null) {
						creditSummaryCue.setPerHouseLoanCount(xNode.getText());
					}

					// [L4] 个人商用房（包括商住两用）贷款笔数
					xNode = creditSummaryCueNode.selectSingleNode("PerBusinessHouseLoanCount");
					if (xNode != null) {
						creditSummaryCue.setPerBusinessHouseLoanCount(xNode.getText());
					}

					// [L4] 其他贷款笔数
					xNode = creditSummaryCueNode.selectSingleNode("OtherLoanCount");
					if (xNode != null) {
						creditSummaryCue.setOtherLoanCount(xNode.getText());
					}

					// [L4] 首笔贷款发放月份
					xNode = creditSummaryCueNode.selectSingleNode("FirstLoanOpenMonth");
					if (xNode != null) {
						creditSummaryCue.setFirstLoanOpenMonth(xNode.getText());
					}

					// [L4] 贷记卡账户数
					xNode = creditSummaryCueNode.selectSingleNode("LoancardCount");
					if (xNode != null) {
						creditSummaryCue.setLoancardCount(xNode.getText());
					}

					// [L4] 首张贷记卡发卡月份
					xNode = creditSummaryCueNode.selectSingleNode("FirstLoancardOpenMonth");
					if (xNode != null) {
						creditSummaryCue.setFirstLoancardOpenMonth(xNode.getText());
					}

					// [L4] 准贷记卡账户数
					xNode = creditSummaryCueNode.selectSingleNode("StandardLoancardCount");
					if (xNode != null) {
						creditSummaryCue.setStandardLoancardCount(xNode.getText());
					}

					// [L4] 首张准贷记卡发卡月份
					xNode = creditSummaryCueNode.selectSingleNode("FirstStandardLoancardOpenMonth");
					if (xNode != null) {
						creditSummaryCue.setFirstStandardLoancardOpenMonth(xNode.getText());
					}

					// [L4] 本人声明数目
					xNode = creditSummaryCueNode.selectSingleNode("AnnounceCount");
					if (xNode != null) {
						creditSummaryCue.setAnnounceCount(xNode.getText());
					}

					// [L4] 异议标注数目
					xNode = creditSummaryCueNode.selectSingleNode("DissentCount");
					if (xNode != null) {
						creditSummaryCue.setDissentCount(xNode.getText());
					}

					creditCue.setCreditSummaryCue(creditSummaryCue);
					xNode = null;
				}

				// [L3] 个人信用报告"数字解读"（CreditScore）
				Node creditScoreNode = creditCueNode.selectSingleNode("CreditScore");
				if (creditScoreNode != null) {
					CreditScore creditScore = creditCue.new CreditScore();

					// [L4] 数字解读
					xNode = creditScoreNode.selectSingleNode("Score");
					if (xNode != null) {
						if (!"--".equals(xNode.getText())) {
							creditScore.setScore(xNode.getText());
						}
					}

					// [L4] 相对位置
					xNode = creditScoreNode.selectSingleNode("ScoreLevel");
					if (xNode != null) {
						creditScore.setScoreLevel(xNode.getText());
					}
					// [L4] 说明
					/*
					 * xNode =
					 * creditScoreNode.selectSingleNode("ScoreElements/ScoreEle"
					 * ); if (xNode != null) {
					 * creditScore.setScoreEle(xNode.getText()); }
					 */
					List<Element> scoreElementsList = creditScoreNode.selectNodes("ScoreElements");
					if (scoreElementsList != null && scoreElementsList.size() > 0) {
						List<HashMap<String, Object>> scoreElesMapList = new ArrayList<HashMap<String, Object>>();
						for (int i = 0; i < scoreElementsList.size(); i++) {
							Element scoreElement = scoreElementsList.get(i);
							if (scoreElement != null && !"".equals(scoreElement)) {
								HashMap<String, Object> scoreEleMap = new HashMap<String, Object>();
								scoreEleMap.put("ScoreEle", scoreElement.selectSingleNode("ScoreEle").getText());
								scoreElesMapList.add(scoreEleMap);
							}
						}
						if (scoreElesMapList != null && scoreElesMapList.size() > 0) {
							creditScore.setScoreElesMapList(scoreElesMapList);
						}
					}
					creditCue.setCreditScore(creditScore);
					xNode = null;
				}

				infoSummary.setCreditCue(creditCue);
			}

			// [L2] 逾期及违约信息概要（OverdueAndFellback）
			Node overdueAndFellbackNode = infoSummaryNode.selectSingleNode("OverdueAndFellback");
			if (overdueAndFellbackNode != null) {
				OverdueAndFellback overdueAndFellback = infoSummary.new OverdueAndFellback();

				// [L3] 违约信息汇总（FellbackSummary）
				Node fellbackSummaryNode = overdueAndFellbackNode.selectSingleNode("FellbackSummary");
				if (fellbackSummaryNode != null) {
					FellbackSummary fellbackSummary = overdueAndFellback.new FellbackSummary();
					// [L4] 呆帐信息汇总（FellbackDebtSum）
					Node fellbackDebtSumNode = fellbackSummaryNode.selectSingleNode("FellbackDebtSum");
					if (fellbackDebtSumNode != null) {

						FellbackDebtSum fellbackDebtSum = fellbackSummary.new FellbackDebtSum();
						// [L5] 笔数
						xNode = fellbackDebtSumNode.selectSingleNode("Count");
						if (xNode != null) {
							fellbackDebtSum.setCount(xNode.getText());
						}

						// [L5] 余额
						xNode = fellbackDebtSumNode.selectSingleNode("Balance");
						if (xNode != null) {
							fellbackDebtSum.setBalance(xNode.getText());
						}
						fellbackSummary.setFellbackDebtSum(fellbackDebtSum);
						xNode = null;
					}

					// [L4] 资产处置信息汇总（AssetDispositionSum）
					Node assetDispositionSumNode = fellbackSummaryNode.selectSingleNode("AssetDispositionSum");
					if (assetDispositionSumNode != null) {
						AssetDispositionSum assetDispositionSum = fellbackSummary.new AssetDispositionSum();
						// [L5] 笔数
						xNode = assetDispositionSumNode.selectSingleNode("Count");
						if (xNode != null) {
							assetDispositionSum.setCount(xNode.getText());
						}

						// [L5] 余额
						xNode = assetDispositionSumNode.selectSingleNode("Balance");
						if (xNode != null) {
							assetDispositionSum.setBalance(xNode.getText());
						}
						fellbackSummary.setAssetDispositionSum(assetDispositionSum);
						xNode = null;
					}

					// [L4] 保证人代偿汇总（AssureerRepaySum）
					Node assureerRepaySumNode = fellbackSummaryNode.selectSingleNode("AssureerRepaySum");
					if (assureerRepaySumNode != null) {
						AssureerRepaySum assureerRepaySum = fellbackSummary.new AssureerRepaySum();
						// [L5] 笔数
						xNode = assureerRepaySumNode.selectSingleNode("Count");
						if (xNode != null) {
							assureerRepaySum.setCount(xNode.getText());
						}

						// [L5] 余额
						xNode = assureerRepaySumNode.selectSingleNode("Balance");
						if (xNode != null) {
							assureerRepaySum.setBalance(xNode.getText());
						}

						fellbackSummary.setAssureerRepaySum(assureerRepaySum);
					}
					overdueAndFellback.setFellbackSummary(fellbackSummary);
				}

				// [L3] 逾期(透支)信息汇总（OverdueSummary）
				Node overdueSummaryNode = overdueAndFellbackNode.selectSingleNode("OverdueSummary");
				if (overdueSummaryNode != null) {
					OverdueAndFellback.OverdueSummary overdueSummary = overdueAndFellback.new OverdueSummary();

					// [L4] 贷款逾期（LoanSum）
					Node loanSumNode = overdueSummaryNode.selectSingleNode("LoanSum");
					if (loanSumNode != null) {
						OverdueSummary.LoanSum loanSum = overdueSummary.new LoanSum();

						// [L5] 笔数/账户数
						xNode = loanSumNode.selectSingleNode("Count");
						if (xNode != null) {
							loanSum.setCount(xNode.getText());
						}

						// [L5] 月份数
						xNode = loanSumNode.selectSingleNode("Months");
						if (xNode != null) {
							loanSum.setMonths(xNode.getText());
						}

						// [L5] 单月最高逾期总额/单月最高透支总额
						xNode = loanSumNode.selectSingleNode("HighestOverdueAmountPerMon");
						if (xNode != null) {
							loanSum.setHighestOverdueAmountPerMon(xNode.getText());
						}

						// [L5] 最长逾期月数/最长透支月数
						xNode = loanSumNode.selectSingleNode("MaxDuration");
						if (xNode != null) {
							loanSum.setMaxDuration(xNode.getText());
						}

						overdueSummary.setLoanSum(loanSum);
						xNode = null;
					}

					// [L4] 贷记卡逾期（LoancardSum）
					Node loancardSumNode = overdueSummaryNode.selectSingleNode("LoancardSum");
					if (loancardSumNode != null) {
						OverdueSummary.LoancardSum loancardSum = overdueSummary.new LoancardSum();

						// [L5] 笔数/账户数
						xNode = loancardSumNode.selectSingleNode("Count");
						if (xNode != null) {
							loancardSum.setCount(xNode.getText());
						}

						// [L5] 月份数
						xNode = loancardSumNode.selectSingleNode("Months");
						if (xNode != null) {
							loancardSum.setMonths(xNode.getText());
						}

						// [L5] 单月最高逾期总额/单月最高透支总额
						xNode = loancardSumNode.selectSingleNode("HighestOverdueAmountPerMon");
						if (xNode != null) {
							loancardSum.setHighestOverdueAmountPerMon(xNode.getText());
						}

						// [L5] 最长逾期月数/最长透支月数
						xNode = loancardSumNode.selectSingleNode("MaxDuration");
						if (xNode != null) {
							loancardSum.setMaxDuration(xNode.getText());
						}

						overdueSummary.setLoancardSum(loancardSum);
						xNode = null;
					}

					// [L4] 准贷记卡60天以上透支（StandardLoancardSum）
					Node standardLoancardSumNode = overdueSummaryNode.selectSingleNode("StandardLoancardSum");
					if (standardLoancardSumNode != null) {
						OverdueSummary.StandardLoancardSum standardLoancardSum = overdueSummary.new StandardLoancardSum();

						// [L5] 笔数/账户数
						xNode = standardLoancardSumNode.selectSingleNode("Count");
						if (xNode != null) {
							standardLoancardSum.setCount(xNode.getText());
						}

						// [L5] 月份数
						xNode = standardLoancardSumNode.selectSingleNode("Months");
						if (xNode != null) {
							standardLoancardSum.setMonths(xNode.getText());
						}

						// [L5] 单月最高逾期总额/单月最高透支总额
						xNode = standardLoancardSumNode.selectSingleNode("HighestOverdueAmountPerMon");
						if (xNode != null) {
							standardLoancardSum.setHighestOverdueAmountPerMon(xNode.getText());
						}

						// [L5] 最长逾期月数/最长透支月数
						xNode = standardLoancardSumNode.selectSingleNode("MaxDuration");
						if (xNode != null) {
							standardLoancardSum.setMaxDuration(xNode.getText());
						}

						overdueSummary.setStandardLoancardSum(standardLoancardSum);
						xNode = null;
					}

					overdueAndFellback.setOverdueSummary(overdueSummary);
				}

				infoSummary.setOverdueAndFellback(overdueAndFellback);
			}

			// [L2] 授信及负债信息概要（ShareAndDebt）
			Node shareAndDebtNode = infoSummaryNode.selectSingleNode("ShareAndDebt");
			if (shareAndDebtNode != null) {
				ShareAndDebt shareAndDebt = infoSummary.new ShareAndDebt();

				// [L3] 未结清贷款信息汇总（UnpaidLoan）
				Node unpaidLoanNode = shareAndDebtNode.selectSingleNode("UnpaidLoan");
				if (unpaidLoanNode != null) {
					ShareAndDebt.UnpaidLoan unpaidLoan = shareAndDebt.new UnpaidLoan();

					// [L4] 贷款法人机构数/发卡法人机构数
					xNode = unpaidLoanNode.selectSingleNode("FinanceCorpCount");
					if (xNode != null) {
						unpaidLoan.setFinanceCorpCount(xNode.getText());
					}

					// [L4] 贷款机构数/发卡机构数
					xNode = unpaidLoanNode.selectSingleNode("FinanceOrgCount");
					if (xNode != null) {
						unpaidLoan.setFinanceOrgCount(xNode.getText());
					}

					// [L4] 笔数/账户数
					xNode = unpaidLoanNode.selectSingleNode("AccountCount");
					if (xNode != null) {
						unpaidLoan.setAccountCount(xNode.getText());
					}

					// [L4] 合同金额/授信总额
					xNode = unpaidLoanNode.selectSingleNode("CreditLimit");
					if (xNode != null) {
						unpaidLoan.setCreditLimit(xNode.getText());
					}

					// [L4] 单家行最高授信额度
					xNode = unpaidLoanNode.selectSingleNode("MaxCreditLimitPerOrg");
					if (xNode != null) {
						unpaidLoan.setMaxCreditLimitPerOrg(xNode.getText());
					}

					// [L4] 单家行最低授信额度
					xNode = unpaidLoanNode.selectSingleNode("MinCreditLimitPerOrg");
					if (xNode != null) {
						unpaidLoan.setMinCreditLimitPerOrg(xNode.getText());
					}

					// [L4] 余额
					xNode = unpaidLoanNode.selectSingleNode("Balance");
					if (xNode != null) {
						unpaidLoan.setBalance(xNode.getText());
					}

					// [L4] 已用额度/透支余额
					xNode = unpaidLoanNode.selectSingleNode("UsedCreditLimit");
					if (xNode != null) {
						unpaidLoan.setUsedCreditLimit(xNode.getText());
					}

					// [L4] 最近6个月平均应还款/最近6个月平均使用额度/最近6个月平均透支余额
					xNode = unpaidLoanNode.selectSingleNode("Latest6MonthUsedAvgAmount");
					if (xNode != null) {
						unpaidLoan.setLatest6MonthUsedAvgAmount(xNode.getText());
					}

					shareAndDebt.setUnpaidLoan(unpaidLoan);
					xNode = null;
				}

				// [L3] 未销户贷记卡信息汇总（UndestoryLoancard）
				Node undestoryLoancardNode = shareAndDebtNode.selectSingleNode("UndestoryLoancard");
				if (undestoryLoancardNode != null) {
					ShareAndDebt.UndestoryLoancard undestoryLoancard = shareAndDebt.new UndestoryLoancard();

					// [L4] 贷款法人机构数/发卡法人机构数
					xNode = undestoryLoancardNode.selectSingleNode("FinanceCorpCount");
					if (xNode != null) {
						undestoryLoancard.setFinanceCorpCount(xNode.getText());
					}

					// [L4] 贷款机构数/发卡机构数
					xNode = undestoryLoancardNode.selectSingleNode("FinanceOrgCount");
					if (xNode != null) {
						undestoryLoancard.setFinanceOrgCount(xNode.getText());
					}

					// [L4] 笔数/账户数
					xNode = undestoryLoancardNode.selectSingleNode("AccountCount");
					if (xNode != null) {
						undestoryLoancard.setAccountCount(xNode.getText());
					}

					// [L4] 合同金额/授信总额
					xNode = undestoryLoancardNode.selectSingleNode("CreditLimit");
					if (xNode != null) {
						undestoryLoancard.setCreditLimit(xNode.getText());
					}

					// [L4] 单家行最高授信额度
					xNode = undestoryLoancardNode.selectSingleNode("MaxCreditLimitPerOrg");
					if (xNode != null) {
						undestoryLoancard.setMaxCreditLimitPerOrg(xNode.getText());
					}

					// [L4] 单家行最低授信额度
					xNode = undestoryLoancardNode.selectSingleNode("MinCreditLimitPerOrg");
					if (xNode != null) {
						undestoryLoancard.setMinCreditLimitPerOrg(xNode.getText());
					}

					// [L4] 余额
					xNode = undestoryLoancardNode.selectSingleNode("Balance");
					if (xNode != null) {
						undestoryLoancard.setBalance(xNode.getText());
					}

					// [L4] 已用额度/透支余额
					xNode = undestoryLoancardNode.selectSingleNode("UsedCreditLimit");
					if (xNode != null) {
						undestoryLoancard.setUsedCreditLimit(xNode.getText());
					}

					// [L4] 最近6个月平均应还款/最近6个月平均使用额度/最近6个月平均透支余额
					xNode = undestoryLoancardNode.selectSingleNode("Latest6MonthUsedAvgAmount");
					if (xNode != null) {
						undestoryLoancard.setLatest6MonthUsedAvgAmount(xNode.getText());
					}

					shareAndDebt.setUndestoryLoancard(undestoryLoancard);
					xNode = null;
				}

				// [L3] 未销户准贷记卡信息汇总（UndestoryLoancard）
				Node undestoryStandardLoancardNode = shareAndDebtNode.selectSingleNode("UndestoryStandardLoancard");
				if (undestoryStandardLoancardNode != null) {
					ShareAndDebt.UndestoryStandardLoancard undestoryStandardLoancard = shareAndDebt.new UndestoryStandardLoancard();

					// [L4] 贷款法人机构数/发卡法人机构数
					xNode = undestoryStandardLoancardNode.selectSingleNode("FinanceCorpCount");
					if (xNode != null) {
						undestoryStandardLoancard.setFinanceCorpCount(xNode.getText());
					}

					// [L4] 贷款机构数/发卡机构数
					xNode = undestoryStandardLoancardNode.selectSingleNode("FinanceOrgCount");
					if (xNode != null) {
						undestoryStandardLoancard.setFinanceOrgCount(xNode.getText());
					}

					// [L4] 笔数/账户数
					xNode = undestoryStandardLoancardNode.selectSingleNode("AccountCount");
					if (xNode != null) {
						undestoryStandardLoancard.setAccountCount(xNode.getText());
					}

					// [L4] 合同金额/授信总额
					xNode = undestoryStandardLoancardNode.selectSingleNode("CreditLimit");
					if (xNode != null) {
						undestoryStandardLoancard.setCreditLimit(xNode.getText());
					}

					// [L4] 单家行最高授信额度
					xNode = undestoryStandardLoancardNode.selectSingleNode("MaxCreditLimitPerOrg");
					if (xNode != null) {
						undestoryStandardLoancard.setMaxCreditLimitPerOrg(xNode.getText());
					}

					// [L4] 单家行最低授信额度
					xNode = undestoryStandardLoancardNode.selectSingleNode("MinCreditLimitPerOrg");
					if (xNode != null) {
						undestoryStandardLoancard.setMinCreditLimitPerOrg(xNode.getText());
					}

					// [L4] 余额
					xNode = undestoryStandardLoancardNode.selectSingleNode("Balance");
					if (xNode != null) {
						undestoryStandardLoancard.setBalance(xNode.getText());
					}

					// [L4] 已用额度/透支余额
					xNode = undestoryStandardLoancardNode.selectSingleNode("UsedCreditLimit");
					if (xNode != null) {
						undestoryStandardLoancard.setUsedCreditLimit(xNode.getText());
					}

					// [L4] 最近6个月平均应还款/最近6个月平均使用额度/最近6个月平均透支余额
					xNode = undestoryStandardLoancardNode.selectSingleNode("Latest6MonthUsedAvgAmount");
					if (xNode != null) {
						undestoryStandardLoancard.setLatest6MonthUsedAvgAmount(xNode.getText());
					}

					shareAndDebt.setUndestoryStandardLoancard(undestoryStandardLoancard);
					xNode = null;
				}

				// [L3] 对外担保信息汇总（GuaranteeSummary）
				Node guaranteeSummaryNode = shareAndDebtNode.selectSingleNode("GuaranteeSummary");
				if (guaranteeSummaryNode != null) {
					ShareAndDebt.GuaranteeSummary guaranteeSummary = shareAndDebt.new GuaranteeSummary();

					// [L4] 担保笔数
					xNode = guaranteeSummaryNode.selectSingleNode("Count");
					if (xNode != null) {
						guaranteeSummary.setCount(xNode.getText());
					}

					// [L4] 担保金额
					xNode = guaranteeSummaryNode.selectSingleNode("Amount");
					if (xNode != null) {
						guaranteeSummary.setAmount(xNode.getText());
					}

					// [L4] 担保本金余额
					xNode = guaranteeSummaryNode.selectSingleNode("Balance");
					if (xNode != null) {
						guaranteeSummary.setBalance(xNode.getText());
					}

					shareAndDebt.setGuaranteeSummary(guaranteeSummary);
					xNode = null;
				}

				infoSummary.setShareAndDebt(shareAndDebt);
			}

			pboc.setInfoSummary(infoSummary);
		}

		// ===============================================
		// == L1 - 信贷交易信息明细
		// ===============================================
		Node creditDetailNode = document.selectSingleNode("/ReportMessage/CreditDetail");
		if (creditDetailNode != null) {
			PBOCCreditDetail creditDetail = new PBOCCreditDetail();

			// [L2] 资产处置信息（AssetDisposition）
			List<Element> assetDispositionElementList = creditDetailNode.selectNodes("AssetDisposition");
			if (assetDispositionElementList != null && assetDispositionElementList.size() > 0) {
				List<AssetDisposition> assetDispositionList = new ArrayList<AssetDisposition>();
				for (int i = 0; i < assetDispositionElementList.size(); i++) {
					Element assetDispositionElement = assetDispositionElementList.get(i);
					if(assetDispositionElement==null){
						continue;
					}
					AssetDisposition assetDisposition = creditDetail.new AssetDisposition();
					// [L2] 资产管理公司
					xNode = assetDispositionElement.selectSingleNode("Organname");
					if (xNode != null) {
						assetDisposition.setOrganname(xNode.getText());
					}
					// [L2] 债务接收日期
					xNode = assetDispositionElement.selectSingleNode("ReceiveTime");
					if (xNode != null) {
						assetDisposition.setReceiveTime(xNode.getText());
					}
					// [L2]接收的债务金额
					xNode = assetDispositionElement.selectSingleNode("Money");
					if (xNode != null) {
						assetDisposition.setMoney(xNode.getText());
					}
					// [L2] 最近一次还款日期
					xNode = assetDispositionElement.selectSingleNode("LatestRepayDate");
					if (xNode != null) {
						assetDisposition.setLatestRepayDate(xNode.getText());
					}
					// [L2] 余额
					xNode = assetDispositionElement.selectSingleNode("Balance");
					if (xNode != null) {
						assetDisposition.setBalance(xNode.getText());
					}
					assetDispositionList.add(assetDisposition);
					xNode = null;
				}
				creditDetail.setAssetDispositionList(assetDispositionList);
			}

			// [L2] 保证人代偿信息（AssurerRepay）
			List<Element> assurerRepayElementList = creditDetailNode.selectNodes("AssurerRepay");
			if (assurerRepayElementList != null && assurerRepayElementList.size() > 0) {
				List<AssurerRepay> assurerRepayList = new ArrayList<AssurerRepay>();
				for (int i = 0; i < assurerRepayElementList.size(); i++) {
					Element assurerRepayElement = assurerRepayElementList.get(i);
					if(assurerRepayElement==null){
						continue;
					}
					AssurerRepay assurerRepay = creditDetail.new AssurerRepay();
					// [L2] 代偿机构
					xNode = assurerRepayElement.selectSingleNode("Organname");
					if (xNode != null) {
						assurerRepay.setOrganname(xNode.getText());
					}
					// [L2] 最近一次代偿日期
					xNode = assurerRepayElement.selectSingleNode("LatestAssurerRepayDate");
					if (xNode != null) {
						assurerRepay.setLatestAssurerRepayDate(xNode.getText());
					}
					// [L2]累计代偿金额
					xNode = assurerRepayElement.selectSingleNode("Money");
					if (xNode != null) {
						assurerRepay.setMoney(xNode.getText());
					}
					// [L2] 最近一次还款日期
					xNode = assurerRepayElement.selectSingleNode("LatestRepayDate");
					if (xNode != null) {
						assurerRepay.setLatestRepayDate(xNode.getText());
					}
					// [L2] 余额
					xNode = assurerRepayElement.selectSingleNode("Balance");
					if (xNode != null) {
						assurerRepay.setBalance(xNode.getText());
					}
					assurerRepayList.add(assurerRepay);
					xNode = null;
				}
				creditDetail.setAssurerRepayList(assurerRepayList);
			}

			// [L2] 贷款（Loan）
			List<Element> loanElementList = creditDetailNode.selectNodes("Loan");
			if (loanElementList != null && loanElementList.size() > 0) {
				List<Loan> loanList = new ArrayList<Loan>();
				for (int i = 0; i < loanElementList.size(); i++) {
					Element loanElement = loanElementList.get(i);
					if(loanElement==null){
						continue;
					}
					Loan loan = creditDetail.new Loan();
					// [L3] 基本信息提示
					Node cueNode = loanElement.selectSingleNode("Cue");
					if(cueNode!=null){
						loan.setCue(cueNode.getText());
					}

					// [L3] 贷款的合同信息（ContractInfo）
					Node contractInfoNode = loanElement.selectSingleNode("ContractInfo");
					if (contractInfoNode != null) {
						PBOCCreditDetail.Loan.ContractInfo contractInfo = loan.new ContractInfo();

						// [L4] 贷款机构
						xNode = contractInfoNode.selectSingleNode("FinanceOrg");
						if (xNode != null) {
							contractInfo.setFinanceOrg(xNode.getText());
						}

						// [L4] 机构类型
						xNode = contractInfoNode.selectSingleNode("FinanceType");
						if (xNode != null) {
							contractInfo.setFinanceType(xNode.getText());
						}

						// [L4] 业务号
						xNode = contractInfoNode.selectSingleNode("Account");
						if (xNode != null) {
							contractInfo.setAccount(xNode.getText());
						}

						// [L4] 贷款种类细分
						xNode = contractInfoNode.selectSingleNode("Type");
						if (xNode != null) {
							contractInfo.setType(xNode.getText());
						}

						// [L4] 币种
						xNode = contractInfoNode.selectSingleNode("Currency");
						if (xNode != null) {
							contractInfo.setCurrency(xNode.getText());
						}

						// [L4] 发放日期
						xNode = contractInfoNode.selectSingleNode("OpenDate");
						if (xNode != null) {
							contractInfo.setOpenDate(xNode.getText());
						}

						// [L4] 到期日期
						xNode = contractInfoNode.selectSingleNode("EndDate");
						if (xNode != null) {
							contractInfo.setEndDate(xNode.getText());
						}

						// [L4] 合同金额
						xNode = contractInfoNode.selectSingleNode("CreditLimitAmount");
						if (xNode != null) {
							contractInfo.setCreditLimitAmount(xNode.getText());
						}

						// [L4] 担保方式
						xNode = contractInfoNode.selectSingleNode("GuaranteeType");
						if (xNode != null) {
							contractInfo.setGuaranteeType(xNode.getText());
						}

						// [L4] 还款频率
						xNode = contractInfoNode.selectSingleNode("PaymentRating");
						if (xNode != null) {
							contractInfo.setPaymentRating(xNode.getText());
						}

						// [L4] 还款期数
						xNode = contractInfoNode.selectSingleNode("PaymentCyc");
						if (xNode != null) {
							contractInfo.setPaymentCyc(xNode.getText());
						}

						loan.setContractInfo(contractInfo);
						xNode = null;
					}

					// [L3] 账户状态
					Node stateNode=loanElement.selectSingleNode("State");
					if(stateNode!=null){
						loan.setState(stateNode.getText());
					}

					// [L3] 当前账户信息（CurrAccountInfo）
					Node currAccountInfoNode = loanElement.selectSingleNode("CurrAccountInfo");
					if (currAccountInfoNode != null) {
						PBOCCreditDetail.Loan.CurrAccountInfo currAccountInfo = loan.new CurrAccountInfo();

						// [L4] 状态截止日
						xNode = currAccountInfoNode.selectSingleNode("StateEndDate");
						if (xNode != null) {
							currAccountInfo.setStateEndDate(xNode.getText());
						}

						// [L4] 状态截止月/转出月
						xNode = currAccountInfoNode.selectSingleNode("StateEndMonth");
						if (xNode != null) {
							currAccountInfo.setStateEndMonth(xNode.getText());
						}

						// [L4] 五级分类
						xNode = currAccountInfoNode.selectSingleNode("Class5State");
						if (xNode != null) {
							currAccountInfo.setClass5State(xNode.getText());
						}

						// [L4] 本金余额
						xNode = currAccountInfoNode.selectSingleNode("Balance");
						if (xNode != null) {
							currAccountInfo.setBalance(xNode.getText());
						}

						// [L4] 剩余还款期数
						xNode = currAccountInfoNode.selectSingleNode("RemainPaymentCyc");
						if (xNode != null) {
							currAccountInfo.setRemainPaymentCyc(xNode.getText());
						}

						// [L4] 本月应还款
						xNode = currAccountInfoNode.selectSingleNode("ScheduledPaymentAmount");
						if (xNode != null) {
							currAccountInfo.setScheduledPaymentAmount(xNode.getText());
						}

						// [L4] 应还款日
						xNode = currAccountInfoNode.selectSingleNode("ScheduledPaymentDate");
						if (xNode != null) {
							currAccountInfo.setScheduledPaymentDate(xNode.getText());
						}

						// [L4] 本月实还款
						xNode = currAccountInfoNode.selectSingleNode("ActualPaymentAmount");
						if (xNode != null) {
							currAccountInfo.setActualPaymentAmount(xNode.getText());
						}

						// [L4] 最近一次还款日期
						xNode = currAccountInfoNode.selectSingleNode("RecentPayDate");
						if (xNode != null) {
							currAccountInfo.setRecentPayDate(xNode.getText());
						}

						loan.setCurrAccountInfo(currAccountInfo);
						xNode = null;
					}

					// [L3] 当前逾期信息（CurrOverdue）
					Node currOverdueNode = loanElement.selectSingleNode("CurrOverdue");
					if (currOverdueNode != null) {
						PBOCCreditDetail.Loan.CurrOverdue currOverdue = loan.new CurrOverdue();

						// [L4] 当前逾期期数
						xNode = currOverdueNode.selectSingleNode("CurrOverdueCyc");
						if (xNode != null) {
							currOverdue.setCurrOverdueCyc(xNode.getText());
						}

						// [L4] 当前逾期金额
						xNode = currOverdueNode.selectSingleNode("CurrOverdueAmount");
						if (xNode != null) {
							currOverdue.setCurrOverdueAmount(xNode.getText());
						}

						// [L4] 逾期31-60天未归还贷款本金
						xNode = currOverdueNode.selectSingleNode("Overdue31To60Amount");
						if (xNode != null) {
							currOverdue.setOverdue31To60Amount(xNode.getText());
						}

						// [L4] 逾期61-90天未归还贷款本金
						xNode = currOverdueNode.selectSingleNode("Overdue61To90Amount");
						if (xNode != null) {
							currOverdue.setOverdue61To90Amount(xNode.getText());
						}

						// [L4] 逾期91-180天未归还贷款本金
						xNode = currOverdueNode.selectSingleNode("Overdue91To180Amount");
						if (xNode != null) {
							currOverdue.setOverdue91To180Amount(xNode.getText());
						}

						// [L4] 逾期180天以上未归还贷款本金
						xNode = currOverdueNode.selectSingleNode("OverdueOver180Amount");
						if (xNode != null) {
							currOverdue.setOverdueOver180Amount(xNode.getText());
						}

						loan.setCurrOverdue(currOverdue);
						xNode = null;
					}

					// [L3] 最近24个月还款状态（Latest24MonthPaymentState）
					Node latest24MonthPaymentStateNode = loanElement.selectSingleNode("Latest24MonthPaymentState");
					if (latest24MonthPaymentStateNode != null) {
						PBOCCreditDetail.Loan.Latest24MonthPaymentState latest24MonthPaymentState = loan.new Latest24MonthPaymentState();

						// [L4] 起始月
						xNode = latest24MonthPaymentStateNode.selectSingleNode("BeginMonth");
						if (xNode != null) {
							latest24MonthPaymentState.setBeginMonth(xNode.getText());
						}

						// [L4] 截止月
						xNode = latest24MonthPaymentStateNode.selectSingleNode("EndMonth");
						if (xNode != null) {
							latest24MonthPaymentState.setEndMonth(xNode.getText());
						}

						// [L4] 24个月还款状态
						xNode = latest24MonthPaymentStateNode.selectSingleNode("Latest24State");
						if (xNode != null) {
							latest24MonthPaymentState.setLatest24State(xNode.getText());
						}

						// [L4] 还款记录明细（State）
						List<Element> stateElementList = latest24MonthPaymentStateNode.selectNodes("State");
						if (stateElementList != null) {

						}

						loan.setLatest24MonthPaymentState(latest24MonthPaymentState);
						xNode = null;
					}

					// [L3] 最近5年内的逾期记录（Latest5YearOverdueRecord）
					Node latest5YearOverdueRecordNode = loanElement.selectSingleNode("Latest5YearOverdueRecord");
					if (latest5YearOverdueRecordNode != null) {
						PBOCCreditDetail.Loan.Latest5YearOverdueRecord latest5YearOverdueRecord = loan.new Latest5YearOverdueRecord();

						// [L4] 起始月
						xNode = latest5YearOverdueRecordNode.selectSingleNode("BeginMonth");
						if (xNode != null) {
							latest5YearOverdueRecord.setBeginMonth(xNode.getText());
						}

						// [L4] 截止月
						xNode = latest5YearOverdueRecordNode.selectSingleNode("EndMonth");
						if (xNode != null) {
							latest5YearOverdueRecord.setEndMonth(xNode.getText());
						}

						// [L4] 逾期记录明细（OverdueRecord）
						List<Element> overdueRecordElementList = latest5YearOverdueRecordNode
								.selectNodes("OverdueRecord");
						if (overdueRecordElementList != null && overdueRecordElementList.size() > 0) {
							List<PBOCCreditDetail.Loan.Latest5YearOverdueRecord.OverdueRecord> overdueRecordList = new ArrayList<PBOCCreditDetail.Loan.Latest5YearOverdueRecord.OverdueRecord>();

							for (int j = 0; j < overdueRecordElementList.size(); j++) {
								Element overdueRecordElement = overdueRecordElementList.get(j);
								if(overdueRecordElement==null){
									continue;
								}
								PBOCCreditDetail.Loan.Latest5YearOverdueRecord.OverdueRecord overdueRecord = latest5YearOverdueRecord.new OverdueRecord();

								// 月份
								xNode = overdueRecordElement.selectSingleNode("Month");
								if (xNode != null) {
									overdueRecord.setMonth(xNode.getText());
								}

								// 持续月数
								xNode = overdueRecordElement.selectSingleNode("LastMonths");
								if (xNode != null) {
									overdueRecord.setLastMonths(xNode.getText());
								}

								// 金额
								xNode = overdueRecordElement.selectSingleNode("Amount");
								if (xNode != null) {
									overdueRecord.setAmount(xNode.getText());
								}
								overdueRecordList.add(overdueRecord);
								xNode = null;
							}
							latest5YearOverdueRecord.setOverdueRecordList(overdueRecordList);
						}
						loan.setLatest5YearOverdueRecord(latest5YearOverdueRecord);
						xNode = null;
					}

					// [L3] 特殊交易信息（SpecialTrade）
					List<Element> specialTradeElementList = loanElement.selectNodes("SpecialTrade");
					if (specialTradeElementList != null && specialTradeElementList.size() > 0) {
						List<PBOCCreditDetail.Loan.SpecialTrade> specialTradeList = new ArrayList<PBOCCreditDetail.Loan.SpecialTrade>();

						for (int j = 0; j < specialTradeElementList.size(); j++) {
							Element specialTradeElement = specialTradeElementList.get(j);
							if(specialTradeElement==null){
								continue;
							}
							PBOCCreditDetail.Loan.SpecialTrade specialTrade = loan.new SpecialTrade();

							// [L4] 类型
							xNode = specialTradeElement.selectSingleNode("Type");
							if (xNode != null) {
								specialTrade.setType(xNode.getText());
							}

							// [L4] 发生日期
							xNode = specialTradeElement.selectSingleNode("GetTime");
							if (xNode != null) {
								specialTrade.setGetTime(xNode.getText());
							}

							// [L4] 变更月数
							xNode = specialTradeElement.selectSingleNode("ChangingMonths");
							if (xNode != null) {
								specialTrade.setChangingMonths(xNode.getText());
							}

							// [L4] 发生金额
							xNode = specialTradeElement.selectSingleNode("ChangingAmount");
							if (xNode != null) {
								specialTrade.setChangingAmount(xNode.getText());
							}

							// [L4] 明细记录
							xNode = specialTradeElement.selectSingleNode("Content");
							if (xNode != null) {
								specialTrade.setContent(xNode.getText());
							}

							specialTradeList.add(specialTrade);
							xNode = null;
						}

						loan.setSpecialTradeList(specialTradeList);
					}

					// [L3] 与该笔业务相关的贷款机构说明（BankIlluminate）
					List<Element> bankIlluminateElementList = loanElement.selectNodes("BankIlluminate");
					if (bankIlluminateElementList != null && bankIlluminateElementList.size() > 0) {
						List<PBOCCreditDetail.Loan.BankIlluminate> bankIlluminateList = new ArrayList<PBOCCreditDetail.Loan.BankIlluminate>();

						for (int j = 0; j < bankIlluminateElementList.size(); j++) {
							Element bankIlluminateElement = bankIlluminateElementList.get(j);
							if(bankIlluminateElement==null){
								continue;
							}
							PBOCCreditDetail.Loan.BankIlluminate bankIlluminate = loan.new BankIlluminate();

							// [L4] 机构说明
							xNode = bankIlluminateElement.selectSingleNode("Content");
							if (xNode != null) {
								bankIlluminate.setContent(xNode.getText());
							}

							// [L4] 机构说明
							/*
							 * xNode =
							 * bankIlluminateElement.selectSingleNode("Content")
							 * ; if (xNode != null) {
							 * bankIlluminate.setContent(xNode.getText()); }
							 */

							bankIlluminateList.add(bankIlluminate);
							xNode = null;
						}

						loan.setBankIlluminateList(bankIlluminateList);
					}

					// [L3] 异议标注（DissentInfo）
					List<Element> dissentInfoElementList = loanElement.selectNodes("DissentInfo");
					if (dissentInfoElementList != null && dissentInfoElementList.size() > 0) {
						List<PBOCCreditDetail.Loan.DissentInfo> dissentInfoList = new ArrayList<PBOCCreditDetail.Loan.DissentInfo>();

						for (int j = 0; j < dissentInfoElementList.size(); j++) {
							Element dissentInfoElement = dissentInfoElementList.get(j);
							if(dissentInfoElement==null){
								continue;
							}
							PBOCCreditDetail.Loan.DissentInfo dissentInfo = loan.new DissentInfo();

							// [L4] 机构说明
							xNode = dissentInfoElement.selectSingleNode("Content");
							if (xNode != null) {
								dissentInfo.setContent(xNode.getText());
							}

							// [L4] 添加日期
							xNode = dissentInfoElement.selectSingleNode("GetTime");
							if (xNode != null) {
								dissentInfo.setGetTime(xNode.getText());
							}

							dissentInfoList.add(dissentInfo);
							xNode = null;
						}

						loan.setDissentInfoList(dissentInfoList);
					}

					// [L3] 本人声明（AnnounceInfo）
					List<Element> announceInfoElementList = loanElement.selectNodes("AnnounceInfo");
					if (announceInfoElementList != null && announceInfoElementList.size() > 0) {
						List<PBOCCreditDetail.Loan.AnnounceInfo> announceInfoList = new ArrayList<PBOCCreditDetail.Loan.AnnounceInfo>();

						for (int j = 0; j < announceInfoElementList.size(); j++) {
							Element announceInfoElement = announceInfoElementList.get(j);
							if(announceInfoElement==null){
								continue;
							}
							PBOCCreditDetail.Loan.AnnounceInfo announceInfo = loan.new AnnounceInfo();

							// [L4] 机构说明
							xNode = announceInfoElement.selectSingleNode("Content");
							if (xNode != null) {
								announceInfo.setContent(xNode.getText());
							}

							// [L4] 添加日期
							xNode = announceInfoElement.selectSingleNode("GetTime");
							if (xNode != null) {
								announceInfo.setGetTime(xNode.getText());
							}

							announceInfoList.add(announceInfo);
							xNode = null;
						}

						loan.setAnnounceInfoList(announceInfoList);
					}

					loanList.add(loan);
				}

				creditDetail.setLoanList(loanList);
			}

			// [L2] 贷记卡（Loancard）
			List<Element> loancardElementList = creditDetailNode.selectNodes("Loancard");
			if (loancardElementList != null && loancardElementList.size() > 0) {
				List<Loancard> loancardList = new ArrayList<Loancard>();
				for (int i = 0; i < loancardElementList.size(); i++) {
					Element loancardElement = loancardElementList.get(i);
					if(loancardElement==null){
						continue;
					}
					Loancard loancard = creditDetail.new Loancard();

					// [L3] 基本信息提示
					Node cueNode=loancardElement.selectSingleNode("Cue");
					if(cueNode!=null){
						loancard.setCue(cueNode.getText());
					}
					// [L3] 贷记卡授信情况（ AwardCreditInfo）
					Node awardCreditInfoNode = loancardElement.selectSingleNode("AwardCreditInfo");
					if (awardCreditInfoNode != null) {
						PBOCCreditDetail.Loancard.AwardCreditInfo awardCreditInfo = loancard.new AwardCreditInfo();

						// [L4] 发卡机构
						xNode = awardCreditInfoNode.selectSingleNode("FinanceOrg");
						if (xNode != null) {
							awardCreditInfo.setFinanceOrg(xNode.getText());
						}

						// [L4] 机构类型
						xNode = awardCreditInfoNode.selectSingleNode("FinanceType");
						if (xNode != null) {
							awardCreditInfo.setFinanceType(xNode.getText());
						}

						// [L4] 业务号
						xNode = awardCreditInfoNode.selectSingleNode("Account");
						if (xNode != null) {
							awardCreditInfo.setAccount(xNode.getText());
						}

						// [L4] 币种
						xNode = awardCreditInfoNode.selectSingleNode("Currency");
						if (xNode != null) {
							awardCreditInfo.setCurrency(xNode.getText());
						}

						// [L4] 发卡日期
						xNode = awardCreditInfoNode.selectSingleNode("OpenDate");
						if (xNode != null) {
							awardCreditInfo.setOpenDate(xNode.getText());
						}

						// [L4] 授信额度
						xNode = awardCreditInfoNode.selectSingleNode("CreditLimitAmount");
						if (xNode != null) {
							awardCreditInfo.setCreditLimitAmount(xNode.getText());
						}

						// [L4] 担保方式
						xNode = awardCreditInfoNode.selectSingleNode("GuaranteeType");
						if (xNode != null) {
							awardCreditInfo.setGuaranteeType(xNode.getText());
						}

						loancard.setAwardCreditInfo(awardCreditInfo);
						xNode = null;
					}

					// [L3] 账户状态
					Node stateNode=loancardElement.selectSingleNode("State");
					if(stateNode!=null){
						loancard.setState(stateNode.getText());
					}

					// [L3] 使用/还款情况（RepayInfo）
					Node repayInfoNode = loancardElement.selectSingleNode("RepayInfo");
					if (repayInfoNode != null) {
						PBOCCreditDetail.Loancard.RepayInfo repayInfo = loancard.new RepayInfo();

						// [L4] 状态截止日
						xNode = repayInfoNode.selectSingleNode("StateEndDate");
						if (xNode != null) {
							repayInfo.setStateEndDate(xNode.getText());
						}

						// [L4] 状态截止月
						xNode = repayInfoNode.selectSingleNode("StateEndMonth");
						if (xNode != null) {
							repayInfo.setStateEndMonth(xNode.getText());
						}

						// [L4] 共享额度
						xNode = repayInfoNode.selectSingleNode("ShareCreditLimitAmount");
						if (xNode != null) {
							repayInfo.setShareCreditLimitAmount(xNode.getText());
						}

						// [L4] 已用额度/透支余额
						xNode = repayInfoNode.selectSingleNode("UsedCreditLimitAmount");
						if (xNode != null) {
							repayInfo.setUsedCreditLimitAmount(xNode.getText());
						}

						// [L4] 最近6个月平均使用额度/最近6个月平均透支余额
						xNode = repayInfoNode.selectSingleNode("Latest6MonthUsedAvgAmount");
						if (xNode != null) {
							repayInfo.setLatest6MonthUsedAvgAmount(xNode.getText());
						}

						// [L4] 最大使用额度/最大透支余额
						xNode = repayInfoNode.selectSingleNode("UsedHighestAmount");
						if (xNode != null) {
							repayInfo.setUsedHighestAmount(xNode.getText());
						}

						// [L4] 账单日
						xNode = repayInfoNode.selectSingleNode("ScheduledPaymentDate");
						if (xNode != null) {
							repayInfo.setScheduledPaymentDate(xNode.getText());
						}

						// [L4] 本月应还款
						xNode = repayInfoNode.selectSingleNode("ScheduledPaymentAmount");
						if (xNode != null) {
							repayInfo.setScheduledPaymentAmount(xNode.getText());
						}

						// [L4] 本月实还款
						xNode = repayInfoNode.selectSingleNode("ActualPaymentAmount");
						if (xNode != null) {
							repayInfo.setActualPaymentAmount(xNode.getText());
						}

						// [L4] 最近一次还款日期
						xNode = repayInfoNode.selectSingleNode("RecentPayDate");
						if (xNode != null) {
							repayInfo.setRecentPayDate(xNode.getText());
						}

						loancard.setRepayInfo(repayInfo);
						xNode = null;
					}

					// [L3] 当前逾期信息（CurrOverdue）
					Node currOverdueNode = loancardElement.selectSingleNode("CurrOverdue");
					if (currOverdueNode != null) {
						PBOCCreditDetail.Loancard.CurrOverdue currOverdue = loancard.new CurrOverdue();

						// [L4] 当前逾期期数
						xNode = currOverdueNode.selectSingleNode("CurrOverdueCyc");
						if (xNode != null) {
							currOverdue.setCurrOverdueCyc(xNode.getText());
						}

						// [L4] 当前逾期金额
						xNode = currOverdueNode.selectSingleNode("CurrOverdueAmount");
						if (xNode != null) {
							currOverdue.setCurrOverdueAmount(xNode.getText());
						}

						// [L4] 逾期31-60天未归还贷款本金
						xNode = currOverdueNode.selectSingleNode("Overdue31To60Amount");
						if (xNode != null) {
							currOverdue.setOverdue31To60Amount(xNode.getText());
						}

						// [L4] 逾期61-90天未归还贷款本金
						xNode = currOverdueNode.selectSingleNode("Overdue61To90Amount");
						if (xNode != null) {
							currOverdue.setOverdue61To90Amount(xNode.getText());
						}

						// [L4] 逾期91-180天未归还贷款本金
						xNode = currOverdueNode.selectSingleNode("Overdue91To180Amount");
						if (xNode != null) {
							currOverdue.setOverdue91To180Amount(xNode.getText());
						}

						// [L4] 逾期180天以上未归还贷款本金
						xNode = currOverdueNode.selectSingleNode("OverdueOver180Amount");
						if (xNode != null) {
							currOverdue.setOverdueOver180Amount(xNode.getText());
						}

						loancard.setCurrOverdue(currOverdue);
						xNode = null;
					}

					// [L3] 最近24个月还款状态（Latest24MonthPaymentState）
					Node latest24MonthPaymentStateNode = loancardElement.selectSingleNode("Latest24MonthPaymentState");
					if (latest24MonthPaymentStateNode != null) {
						PBOCCreditDetail.Loancard.Latest24MonthPaymentState latest24MonthPaymentState = loancard.new Latest24MonthPaymentState();

						// [L4] 起始月
						xNode = latest24MonthPaymentStateNode.selectSingleNode("BeginMonth");
						if (xNode != null) {
							latest24MonthPaymentState.setBeginMonth(xNode.getText());
						}

						// [L4] 截止月
						xNode = latest24MonthPaymentStateNode.selectSingleNode("EndMonth");
						if (xNode != null) {
							latest24MonthPaymentState.setEndMonth(xNode.getText());
						}

						// [L4] 24个月还款状态
						xNode = latest24MonthPaymentStateNode.selectSingleNode("Latest24State");
						if (xNode != null) {
							latest24MonthPaymentState.setLatest24State(xNode.getText());
						}

						// [L4] 还款记录明细（State）
						List<Element> stateElementList = latest24MonthPaymentStateNode.selectNodes("State");
						if (stateElementList != null) {

						}

						loancard.setLatest24MonthPaymentState(latest24MonthPaymentState);
						xNode = null;
					}

					// [L3] 最近5年内的逾期记录（Latest5YearOverdueRecord）
					Node latest5YearOverdueRecordNode = loancardElement.selectSingleNode("Latest5YearOverdueRecord");
					if (latest5YearOverdueRecordNode != null) {
						PBOCCreditDetail.Loancard.Latest5YearOverdueRecord latest5YearOverdueRecord = loancard.new Latest5YearOverdueRecord();

						// [L4] 起始月
						xNode = latest5YearOverdueRecordNode.selectSingleNode("BeginMonth");
						if (xNode != null) {
							latest5YearOverdueRecord.setBeginMonth(xNode.getText());
						}

						// [L4] 截止月
						xNode = latest5YearOverdueRecordNode.selectSingleNode("EndMonth");
						if (xNode != null) {
							latest5YearOverdueRecord.setEndMonth(xNode.getText());
						}

						// [L4] 逾期记录明细（OverdueRecord）
						List<Element> overdueRecordElementList = latest5YearOverdueRecordNode
								.selectNodes("OverdueRecord");
						if (overdueRecordElementList != null && overdueRecordElementList.size() > 0) {
							List<PBOCCreditDetail.Loancard.Latest5YearOverdueRecord.OverdueRecord> listOverdueRecord = new ArrayList<PBOCCreditDetail.Loancard.Latest5YearOverdueRecord.OverdueRecord>();
							for (int j = 0; j < overdueRecordElementList.size(); j++) {
								Element overdueRecordElement = overdueRecordElementList.get(j);
								
								if(overdueRecordElement==null){
									continue;
								}
								PBOCCreditDetail.Loancard.Latest5YearOverdueRecord.OverdueRecord overdueRecord = latest5YearOverdueRecord.new OverdueRecord();
								// 月份
								xNode = overdueRecordElement.selectSingleNode("Month");
								if (xNode != null) {
									overdueRecord.setMonth(xNode.getText());
								}
								// 持续月数
								xNode = overdueRecordElement.selectSingleNode("LastMonths");
								if (xNode != null) {
									overdueRecord.setLastMonths(xNode.getText());
								}
								// 金额
								xNode = overdueRecordElement.selectSingleNode("Amount");
								if (xNode != null) {
									overdueRecord.setAmount(xNode.getText());
								}
								listOverdueRecord.add(overdueRecord);
								xNode = null;
							}
							latest5YearOverdueRecord.setOverdueRecordList(listOverdueRecord);
						}
						loancard.setLatest5YearOverdueRecord(latest5YearOverdueRecord);
						xNode = null;
					}

					// [L3] 特殊交易信息（SpecialTrade）
					List<Element> specialTradeElementList = loancardElement.selectNodes("SpecialTrade");
					if (specialTradeElementList != null && specialTradeElementList.size() > 0) {
						List<PBOCCreditDetail.Loancard.SpecialTrade> specialTradeList = new ArrayList<PBOCCreditDetail.Loancard.SpecialTrade>();

						for (int j = 0; j < specialTradeElementList.size(); j++) {
							Element specialTradeElement = specialTradeElementList.get(j);
							if(specialTradeElement==null){
								continue;
							}
							PBOCCreditDetail.Loancard.SpecialTrade specialTrade = loancard.new SpecialTrade();

							// [L4] 类型
							xNode = specialTradeElement.selectSingleNode("Type");
							if (xNode != null) {
								specialTrade.setType(xNode.getText());
							}

							// [L4] 发生日期
							xNode = specialTradeElement.selectSingleNode("GetTime");
							if (xNode != null) {
								specialTrade.setGetTime(xNode.getText());
							}

							// [L4] 变更月数
							xNode = specialTradeElement.selectSingleNode("ChangingMonths");
							if (xNode != null) {
								specialTrade.setChangingMonths(xNode.getText());
							}

							// [L4] 发生金额
							xNode = specialTradeElement.selectSingleNode("ChangingAmount");
							if (xNode != null) {
								specialTrade.setChangingAmount(xNode.getText());
							}

							// [L4] 明细记录
							xNode = specialTradeElement.selectSingleNode("Content");
							if (xNode != null) {
								specialTrade.setContent(xNode.getText());
							}

							specialTradeList.add(specialTrade);
							xNode = null;
						}

						loancard.setSpecialTradeList(specialTradeList);
					}

					// [L3] 与该笔业务相关的贷款机构说明（BankIlluminate）
					List<Element> bankIlluminateElementList = loancardElement.selectNodes("BankIlluminate");
					if (bankIlluminateElementList != null && bankIlluminateElementList.size() > 0) {
						List<PBOCCreditDetail.Loancard.BankIlluminate> bankIlluminateList = new ArrayList<PBOCCreditDetail.Loancard.BankIlluminate>();

						for (int j = 0; j < bankIlluminateElementList.size(); j++) {
							Element bankIlluminateElement = bankIlluminateElementList.get(j);
							if(bankIlluminateElement==null){
								continue;
							}
							PBOCCreditDetail.Loancard.BankIlluminate bankIlluminate = loancard.new BankIlluminate();

							// [L4] 机构说明
							xNode = bankIlluminateElement.selectSingleNode("Content");
							if (xNode != null) {
								bankIlluminate.setContent(xNode.getText());
							}

							// [L4] 机构说明
							xNode = bankIlluminateElement.selectSingleNode("Content");
							if (xNode != null) {
								bankIlluminate.setContent(xNode.getText());
							}

							bankIlluminateList.add(bankIlluminate);
							xNode = null;
						}

						loancard.setBankIlluminateList(bankIlluminateList);
					}

					// [L3] 异议标注（DissentInfo）
					List<Element> dissentInfoElementList = loancardElement.selectNodes("DissentInfo");
					if (dissentInfoElementList != null && dissentInfoElementList.size() > 0) {
						List<PBOCCreditDetail.Loancard.DissentInfo> dissentInfoList = new ArrayList<PBOCCreditDetail.Loancard.DissentInfo>();

						for (int j = 0; j < dissentInfoElementList.size(); j++) {
							Element dissentInfoElement = dissentInfoElementList.get(j);
							if(dissentInfoElement==null){
								continue;
							}
							PBOCCreditDetail.Loancard.DissentInfo dissentInfo = loancard.new DissentInfo();

							// [L4] 机构说明
							xNode = dissentInfoElement.selectSingleNode("Content");
							if (xNode != null) {
								dissentInfo.setContent(xNode.getText());
							}

							// [L4] 添加日期
							xNode = dissentInfoElement.selectSingleNode("GetTime");
							if (xNode != null) {
								dissentInfo.setGetTime(xNode.getText());
							}

							dissentInfoList.add(dissentInfo);
							xNode = null;
						}

						loancard.setDissentInfoList(dissentInfoList);
					}

					// [L3] 本人声明（AnnounceInfo）
					List<Element> announceInfoElementList = loancardElement.selectNodes("AnnounceInfo");
					if (announceInfoElementList != null && announceInfoElementList.size() > 0) {
						List<PBOCCreditDetail.Loancard.AnnounceInfo> announceInfoList = new ArrayList<PBOCCreditDetail.Loancard.AnnounceInfo>();

						for (int j = 0; j < announceInfoElementList.size(); j++) {
							Element announceInfoElement = announceInfoElementList.get(j);
							if(announceInfoElement==null){
								continue;
							}
							PBOCCreditDetail.Loancard.AnnounceInfo announceInfo = loancard.new AnnounceInfo();

							// [L4] 机构说明
							xNode = announceInfoElement.selectSingleNode("Content");
							if (xNode != null) {
								announceInfo.setContent(xNode.getText());
							}

							// [L4] 添加日期
							xNode = announceInfoElement.selectSingleNode("GetTime");
							if (xNode != null) {
								announceInfo.setGetTime(xNode.getText());
							}

							announceInfoList.add(announceInfo);
							xNode = null;
						}

						loancard.setAnnounceInfoList(announceInfoList);
					}

					loancardList.add(loancard);
				}

				creditDetail.setLoancardList(loancardList);
			}

			// [L2] 准贷记卡（StandardLoancard）
			List<Element> standardLoancardElementList = creditDetailNode.selectNodes("StandardLoancard");
			if (standardLoancardElementList != null && standardLoancardElementList.size() > 0) {
				List<StandardLoancard> standardLoancardList = new ArrayList<StandardLoancard>();
				for (int i = 0; i < standardLoancardElementList.size(); i++) {
					Element standardLoancardElement = standardLoancardElementList.get(i);
					if(standardLoancardElement==null){
						continue;
					}
					StandardLoancard standardLoancard = creditDetail.new StandardLoancard();

					// [L3] 基本信息提示
					Node cueNode=standardLoancardElement.selectSingleNode("Cue");
					if(cueNode!=null){
						standardLoancard.setCue(cueNode.getText());
					}

					// [L3] 贷记卡授信情况（AwardCreditInfo）
					Node awardCreditInfoNode = standardLoancardElement.selectSingleNode("AwardCreditInfo");
					if (awardCreditInfoNode != null) {
						PBOCCreditDetail.StandardLoancard.AwardCreditInfo awardCreditInfo = standardLoancard.new AwardCreditInfo();
						// 发卡机构
						xNode = awardCreditInfoNode.selectSingleNode("FinanceOrg");
						if (xNode != null) {
							awardCreditInfo.setFinanceOrg(xNode.getText());
						}

						xNode = awardCreditInfoNode.selectSingleNode("FinanceType");
						if (xNode != null) {
							awardCreditInfo.setFinanceType(xNode.getText());
						}

						xNode = awardCreditInfoNode.selectSingleNode("Account");
						if (xNode != null) {
							awardCreditInfo.setAccount(xNode.getText());
						}

						xNode = awardCreditInfoNode.selectSingleNode("Currency");
						if (xNode != null) {
							awardCreditInfo.setCurrency(xNode.getText());
						}

						xNode = awardCreditInfoNode.selectSingleNode("OpenDate");
						if (xNode != null) {
							awardCreditInfo.setOpenDate(xNode.getText());
						}

						xNode = awardCreditInfoNode.selectSingleNode("CreditLimitAmount");
						if (xNode != null) {
							awardCreditInfo.setCreditLimitAmount(xNode.getText());
						}

						xNode = awardCreditInfoNode.selectSingleNode("GuaranteeType");
						if (xNode != null) {
							awardCreditInfo.setGuaranteeType(xNode.getText());
						}

						standardLoancard.setAwardCreditInfo(awardCreditInfo);
					}

					// [L3] 账户状态
					Node stateNode=standardLoancardElement.selectSingleNode("State");
					if(stateNode!=null){
						standardLoancard.setState(stateNode.getText());
					}
				
					// [L3] 使用/还款情况（RepayInfo）
					Node repayInfoNode = standardLoancardElement.selectSingleNode("RepayInfo");
					if (repayInfoNode != null) {
						PBOCCreditDetail.StandardLoancard.RepayInfo repayInfo = standardLoancard.new RepayInfo();

						// [L4] 状态截止日
						xNode = repayInfoNode.selectSingleNode("StateEndDate");
						if (xNode != null) {
							repayInfo.setStateEndDate(xNode.getText());
						}

						// [L4] 状态截止月
						xNode = repayInfoNode.selectSingleNode("StateEndMonth");
						if (xNode != null) {
							repayInfo.setStateEndMonth(xNode.getText());
						}

						// [L4] 共享额度
						xNode = repayInfoNode.selectSingleNode("ShareCreditLimitAmount");
						if (xNode != null) {
							repayInfo.setShareCreditLimitAmount(xNode.getText());
						}

						// [L4] 已用额度/透支余额
						xNode = repayInfoNode.selectSingleNode("UsedCreditLimitAmount");
						if (xNode != null) {
							repayInfo.setUsedCreditLimitAmount(xNode.getText());
						}

						// [L4] 最近6个月平均使用额度/最近6个月平均透支余额
						xNode = repayInfoNode.selectSingleNode("Latest6MonthUsedAvgAmount");
						if (xNode != null) {
							repayInfo.setLatest6MonthUsedAvgAmount(xNode.getText());
						}

						// [L4] 最大使用额度/最大透支余额
						xNode = repayInfoNode.selectSingleNode("UsedHighestAmount");
						if (xNode != null) {
							repayInfo.setUsedHighestAmount(xNode.getText());
						}

						// [L4] 账单日
						xNode = repayInfoNode.selectSingleNode("ScheduledPaymentDate");
						if (xNode != null) {
							repayInfo.setScheduledPaymentDate(xNode.getText());
						}

						// [L4] 本月应还款
						xNode = repayInfoNode.selectSingleNode("ScheduledPaymentAmount");
						if (xNode != null) {
							repayInfo.setScheduledPaymentAmount(xNode.getText());
						}

						// [L4] 本月实还款
						xNode = repayInfoNode.selectSingleNode("ActualPaymentAmount");
						if (xNode != null) {
							repayInfo.setActualPaymentAmount(xNode.getText());
						}

						// [L4] 最近一次还款日期
						xNode = repayInfoNode.selectSingleNode("RecentPayDate");
						if (xNode != null) {
							repayInfo.setRecentPayDate(xNode.getText());
						}

						standardLoancard.setRepayInfo(repayInfo);
						xNode = null;
					}

					// [L3] 当前逾期信息（CurrOverdue）
					Node currOverdueNode = standardLoancardElement.selectSingleNode("CurrOverdue");
					if (currOverdueNode != null) {
						PBOCCreditDetail.StandardLoancard.CurrOverdue currOverdue = standardLoancard.new CurrOverdue();

						// [L4] 当前逾期期数
						xNode = currOverdueNode.selectSingleNode("CurrOverdueCyc");
						if (xNode != null) {
							currOverdue.setCurrOverdueCyc(xNode.getText());
						}

						// [L4] 当前逾期金额
						xNode = currOverdueNode.selectSingleNode("CurrOverdueAmount");
						if (xNode != null) {
							currOverdue.setCurrOverdueAmount(xNode.getText());
						}

						// [L4] 逾期31-60天未归还贷款本金
						xNode = currOverdueNode.selectSingleNode("Overdue31To60Amount");
						if (xNode != null) {
							currOverdue.setOverdue31To60Amount(xNode.getText());
						}

						// [L4] 逾期61-90天未归还贷款本金
						xNode = currOverdueNode.selectSingleNode("Overdue61To90Amount");
						if (xNode != null) {
							currOverdue.setOverdue61To90Amount(xNode.getText());
						}

						// [L4] 逾期91-180天未归还贷款本金
						xNode = currOverdueNode.selectSingleNode("Overdue91To180Amount");
						if (xNode != null) {
							currOverdue.setOverdue91To180Amount(xNode.getText());
						}

						// [L4] 逾期180天以上未归还贷款本金
						xNode = currOverdueNode.selectSingleNode("OverdueOver180Amount");
						if (xNode != null) {
							currOverdue.setOverdueOver180Amount(xNode.getText());
						}

						standardLoancard.setCurrOverdue(currOverdue);
						xNode = null;
					}

					// [L3] 最近24个月还款状态（Latest24MonthPaymentState）
					Node latest24MonthPaymentStateNode = standardLoancardElement
							.selectSingleNode("Latest24MonthPaymentState");
					if (latest24MonthPaymentStateNode != null) {
						PBOCCreditDetail.StandardLoancard.Latest24MonthPaymentState latest24MonthPaymentState = standardLoancard.new Latest24MonthPaymentState();

						// [L4] 起始月
						xNode = latest24MonthPaymentStateNode.selectSingleNode("BeginMonth");
						if (xNode != null) {
							latest24MonthPaymentState.setBeginMonth(xNode.getText());
						}

						// [L4] 截止月
						xNode = latest24MonthPaymentStateNode.selectSingleNode("EndMonth");
						if (xNode != null) {
							latest24MonthPaymentState.setEndMonth(xNode.getText());
						}

						// [L4] 24个月还款状态
						xNode = latest24MonthPaymentStateNode.selectSingleNode("Latest24State");
						if (xNode != null) {
							latest24MonthPaymentState.setLatest24State(xNode.getText());
						}

						// [L4] 还款记录明细（State）
						List<Element> stateElementList = latest24MonthPaymentStateNode.selectNodes("State");
						if (stateElementList != null) {

						}

						standardLoancard.setLatest24MonthPaymentState(latest24MonthPaymentState);
						xNode = null;
					}

					// [L3] 最近5年内的逾期记录（Latest5YearOverdueRecord）
					Node latest5YearOverdueRecordNode = standardLoancardElement
							.selectSingleNode("Latest5YearOverdueRecord");
					if (latest5YearOverdueRecordNode != null) {
						PBOCCreditDetail.StandardLoancard.Latest5YearOverdueRecord latest5YearOverdueRecord = standardLoancard.new Latest5YearOverdueRecord();

						// [L4] 起始月
						xNode = latest5YearOverdueRecordNode.selectSingleNode("BeginMonth");
						if (xNode != null) {
							latest5YearOverdueRecord.setBeginMonth(xNode.getText());
						}

						// [L4] 截止月
						xNode = latest5YearOverdueRecordNode.selectSingleNode("EndMonth");
						if (xNode != null) {
							latest5YearOverdueRecord.setEndMonth(xNode.getText());
						}

						// [L4] 逾期记录明细（OverdueRecord）
						List<Element> overdueRecordElementList = latest5YearOverdueRecordNode
								.selectNodes("OverdueRecord");
						if (overdueRecordElementList != null) {

						}

						standardLoancard.setLatest5YearOverdueRecord(latest5YearOverdueRecord);
						xNode = null;
					}

					// [L3] 特殊交易信息（SpecialTrade）
					List<Element> specialTradeElementList = standardLoancardElement.selectNodes("SpecialTrade");
					if (specialTradeElementList != null && specialTradeElementList.size() > 0) {
						List<PBOCCreditDetail.StandardLoancard.SpecialTrade> specialTradeList = new ArrayList<PBOCCreditDetail.StandardLoancard.SpecialTrade>();

						for (int j = 0; j < specialTradeElementList.size(); j++) {
							Element specialTradeElement = specialTradeElementList.get(j);
							if(specialTradeElement==null){
								continue;
							}
							PBOCCreditDetail.StandardLoancard.SpecialTrade specialTrade = standardLoancard.new SpecialTrade();

							// [L4] 类型
							xNode = specialTradeElement.selectSingleNode("Type");
							if (xNode != null) {
								specialTrade.setType(xNode.getText());
							}

							// [L4] 发生日期
							xNode = specialTradeElement.selectSingleNode("GetTime");
							if (xNode != null) {
								specialTrade.setGetTime(xNode.getText());
							}

							// [L4] 变更月数
							xNode = specialTradeElement.selectSingleNode("ChangingMonths");
							if (xNode != null) {
								specialTrade.setChangingMonths(xNode.getText());
							}

							// [L4] 发生金额
							xNode = specialTradeElement.selectSingleNode("ChangingAmount");
							if (xNode != null) {
								specialTrade.setChangingAmount(xNode.getText());
							}

							// [L4] 明细记录
							xNode = specialTradeElement.selectSingleNode("Content");
							if (xNode != null) {
								specialTrade.setContent(xNode.getText());
							}

							specialTradeList.add(specialTrade);
							xNode = null;
						}

						standardLoancard.setSpecialTradeList(specialTradeList);
					}

					// [L3] 与该笔业务相关的贷款机构说明（BankIlluminate）
					List<Element> bankIlluminateElementList = standardLoancardElement.selectNodes("BankIlluminate");
					if (bankIlluminateElementList != null && bankIlluminateElementList.size() > 0) {
						List<PBOCCreditDetail.StandardLoancard.BankIlluminate> bankIlluminateList = new ArrayList<PBOCCreditDetail.StandardLoancard.BankIlluminate>();

						for (int j = 0; j < bankIlluminateElementList.size(); j++) {
							Element bankIlluminateElement = bankIlluminateElementList.get(j);
							if(bankIlluminateElement==null){
								continue;
							}
							PBOCCreditDetail.StandardLoancard.BankIlluminate bankIlluminate = standardLoancard.new BankIlluminate();

							// [L4] 机构说明
							xNode = bankIlluminateElement.selectSingleNode("Content");
							if (xNode != null) {
								bankIlluminate.setContent(xNode.getText());
							}

							// [L4] 机构说明
							/*
							 * xNode =
							 * bankIlluminateElement.selectSingleNode("Content")
							 * ; if (xNode != null) {
							 * bankIlluminate.setContent(xNode.getText()); }
							 */

							bankIlluminateList.add(bankIlluminate);
							xNode = null;
						}

						standardLoancard.setBankIlluminateList(bankIlluminateList);
					}

					// [L3] 异议标注（DissentInfo）
					List<Element> dissentInfoElementList = standardLoancardElement.selectNodes("DissentInfo");
					if (dissentInfoElementList != null && dissentInfoElementList.size() > 0) {
						List<PBOCCreditDetail.StandardLoancard.DissentInfo> dissentInfoList = new ArrayList<PBOCCreditDetail.StandardLoancard.DissentInfo>();

						for (int j = 0; j < dissentInfoElementList.size(); j++) {
							Element dissentInfoElement = dissentInfoElementList.get(j);
							if(dissentInfoElement==null){
								continue;
							}
							PBOCCreditDetail.StandardLoancard.DissentInfo dissentInfo = standardLoancard.new DissentInfo();

							// [L4] 机构说明
							xNode = dissentInfoElement.selectSingleNode("Content");
							if (xNode != null) {
								dissentInfo.setContent(xNode.getText());
							}

							// [L4] 添加日期
							xNode = dissentInfoElement.selectSingleNode("GetTime");
							if (xNode != null) {
								dissentInfo.setGetTime(xNode.getText());
							}

							dissentInfoList.add(dissentInfo);
							xNode = null;
						}

						standardLoancard.setDissentInfoList(dissentInfoList);
					}

					// [L3] 本人声明（AnnounceInfo）
					List<Element> announceInfoElementList = standardLoancardElement.selectNodes("AnnounceInfo");
					if (announceInfoElementList != null && announceInfoElementList.size() > 0) {
						List<PBOCCreditDetail.StandardLoancard.AnnounceInfo> announceInfoList = new ArrayList<PBOCCreditDetail.StandardLoancard.AnnounceInfo>();

						for (int j = 0; j < announceInfoElementList.size(); j++) {
							Element announceInfoElement = announceInfoElementList.get(j);
							if(announceInfoElement==null){
								continue;
							}
							PBOCCreditDetail.StandardLoancard.AnnounceInfo announceInfo = standardLoancard.new AnnounceInfo();

							// [L4] 机构说明
							xNode = announceInfoElement.selectSingleNode("Content");
							if (xNode != null) {
								announceInfo.setContent(xNode.getText());
							}

							// [L4] 添加日期
							xNode = announceInfoElement.selectSingleNode("GetTime");
							if (xNode != null) {
								announceInfo.setGetTime(xNode.getText());
							}

							announceInfoList.add(announceInfo);
							xNode = null;
						}

						standardLoancard.setAnnounceInfoList(announceInfoList);
					}

					standardLoancardList.add(standardLoancard);
					xNode = null;
				}
				creditDetail.setStandardLoancardList(standardLoancardList);
			}

			// [L2] 担保信息（GuaranteeInfo）
			List<Element> guaranteeInfoList = creditDetailNode.selectNodes("GuaranteeInfo");

			if (guaranteeInfoList != null && guaranteeInfoList.size() > 0) {
				List<GuaranteeInfo> guaranteeInfoListData = new ArrayList<GuaranteeInfo>();

				for (int i = 0; i < guaranteeInfoList.size(); i++) {
					List<Guarantee> guaranteeList = new ArrayList<Guarantee>();
					Node guaranteeInfoNode = guaranteeInfoList.get(i);
					if (guaranteeInfoNode != null) {
						GuaranteeInfo guaranteeInfo = creditDetail.new GuaranteeInfo();

						// [L3] 担保类型
						xNode = guaranteeInfoNode.selectSingleNode("GuaranteeFormat");
						if (xNode != null) {
							guaranteeInfo.setGuaranteeFormat(xNode.getText());
						}

						// [L3] 担保信息（Guarantee）
						List<Element> guaranteeElementList = guaranteeInfoNode.selectNodes("Guarantee");
						if (guaranteeElementList != null && guaranteeElementList.size() > 0) {

							for (int j = 0; j < guaranteeElementList.size(); j++) {
								Element guaranteeElement = guaranteeElementList.get(j);
								if(guaranteeElement==null){
									continue;
								}
								Guarantee guarantee = guaranteeInfo.new Guarantee();

								// [L4] 担保贷款/信用卡发放机构
								xNode = guaranteeElement.selectSingleNode("Organname");
								if (xNode != null) {
									guarantee.setOrganname(xNode.getText());
								}

								// [L4] 担保贷款合同金额/担保信用卡授信额度
								xNode = guaranteeElement.selectSingleNode("ContractMoney");
								if (xNode != null) {
									guarantee.setContractMoney(xNode.getText());
								}

								// [L4] 担保贷款发放日期/担保信用卡发卡日期
								xNode = guaranteeElement.selectSingleNode("BeginDate");
								if (xNode != null) {
									guarantee.setBeginDate(xNode.getText());
								}

								// [L4] 担保贷款到期日期
								xNode = guaranteeElement.selectSingleNode("EndDate");
								if (xNode != null) {
									guarantee.setEndDate(xNode.getText());
								}

								// [L4] 担保金额
								xNode = guaranteeElement.selectSingleNode("GuananteeMoney");
								if (xNode != null) {
									guarantee.setGuaranteeMoney(xNode.getText());
								}

								// [L4] 担保贷款本金余额/担保信用卡已用额度
								xNode = guaranteeElement.selectSingleNode("GuaranteeBalance");
								if (xNode != null) {
									guarantee.setGuaranteeBalance(xNode.getText());
								}

								// [L4] 担保贷款五级分类
								xNode = guaranteeElement.selectSingleNode("Class5State");
								if (xNode != null) {
									guarantee.setClass5State(xNode.getText());
								}

								// [L4] 应还款日/账单日
								xNode = guaranteeElement.selectSingleNode("BillingDate");
								if (xNode != null) {
									guarantee.setBillingDate(xNode.getText());
								}

								guaranteeList.add(guarantee);
							}
							guaranteeInfo.setGuaranteeList(guaranteeList);
							guaranteeInfoListData.add(guaranteeInfo);
						}
					}
				}
				creditDetail.setGuaranteeInfoList(guaranteeInfoListData);
			}

			pboc.setCreditDetail(creditDetail);
		}

		// ===============================================
		// == L1 - 公共信息明细（PublicInfo）
		// ===============================================
		Node publicInfoNode = document.selectSingleNode("/ReportMessage/PublicInfo");
		if (publicInfoNode != null) {
			PBOCPublicInfo publicInfo = new PBOCPublicInfo();

			// [L2] 欠税记录（TaxArrear）
			List<Element> taxArrearElementList = publicInfoNode.selectNodes("TaxArrear");
			if (taxArrearElementList != null && taxArrearElementList.size() > 0) {
				List<TaxArrear> taxArrearList = new ArrayList<TaxArrear>();
				for (int i = 0; i < taxArrearElementList.size(); i++) {
					Element taxArrearElement = taxArrearElementList.get(i);
					TaxArrear taxArrear = publicInfo.new TaxArrear();

					// [L3] 主管税务机关
					xNode = taxArrearElement.selectSingleNode("Organname");
					if (xNode != null) {
						taxArrear.setOrganname(xNode.getText());
					}

					// [L3] 欠税总额
					xNode = taxArrearElement.selectSingleNode("TaxArreaAmount");
					if (xNode != null) {
						taxArrear.setTaxArreaAmount(xNode.getText());
					}

					// [L3] 欠税统计日期
					xNode = taxArrearElement.selectSingleNode("Revenuedate");
					if (xNode != null) {
						taxArrear.setRevenuedate(xNode.getText());
					}

					taxArrearList.add(taxArrear);
					xNode = null;
				}
				publicInfo.setTaxArrearList(taxArrearList);
			}

			// [L2] 民事判决记录（CivilJudgement）
			List<Element> civilJudgementElementList = publicInfoNode.selectNodes("CivilJudgement");
			if (civilJudgementElementList != null && civilJudgementElementList.size() > 0) {
				List<CivilJudgement> civilJudgementList = new ArrayList<CivilJudgement>();
				for (int i = 0; i < civilJudgementElementList.size(); i++) {
					Element civilJudgementElement = civilJudgementElementList.get(i);
					if(civilJudgementElement==null){
						continue;
					}
					CivilJudgement civilJudgement = publicInfo.new CivilJudgement();

					// [L3] 立案法院
					xNode = civilJudgementElement.selectSingleNode("Court");
					if (xNode != null) {
						civilJudgement.setCourt(xNode.getText());
					}

					// [L3] 案由
					xNode = civilJudgementElement.selectSingleNode("CaseReason");
					if (xNode != null) {
						civilJudgement.setCaseReason(xNode.getText());
					}

					// [L3] 立案日期
					xNode = civilJudgementElement.selectSingleNode("RegisterDate");
					if (xNode != null) {
						civilJudgement.setRegisterDate(xNode.getText());
					}

					// [L3] 结案方式
					xNode = civilJudgementElement.selectSingleNode("ClosedType");
					if (xNode != null) {
						civilJudgement.setClosedType(xNode.getText());
					}

					// [L3] 判决/调解结果
					xNode = civilJudgementElement.selectSingleNode("CaseResult");
					if (xNode != null) {
						civilJudgement.setCaseResult(xNode.getText());
					}

					// [L3] 判决/调解生效日期
					xNode = civilJudgementElement.selectSingleNode("CaseValidatedate");
					if (xNode != null) {
						civilJudgement.setCaseValidatedate(xNode.getText());
					}

					// [L3] 诉讼标的
					xNode = civilJudgementElement.selectSingleNode("SuitObject");
					if (xNode != null) {
						civilJudgement.setSuitObject(xNode.getText());
					}

					// [L3] 诉讼标的金额
					xNode = civilJudgementElement.selectSingleNode("SuitObjectMoney");
					if (xNode != null) {
						civilJudgement.setSuitObjectMoney(xNode.getText());
					}

					civilJudgementList.add(civilJudgement);
					xNode = null;
				}
				publicInfo.setCivilJudgementList(civilJudgementList);
			}

			// [L2] 强制执行记录（ForceExecution）
			List<Element> forceExecutionElementList = publicInfoNode.selectNodes("ForceExecution");
			if (forceExecutionElementList != null && forceExecutionElementList.size() > 0) {
				List<ForceExecution> forceExecutionList = new ArrayList<ForceExecution>();
				for (int i = 0; i < forceExecutionElementList.size(); i++) {
					Element forceExecutionElement = forceExecutionElementList.get(i);
					if(forceExecutionElement==null){
						continue;
					}
					ForceExecution forceExecution = publicInfo.new ForceExecution();

					// [L3] 执行法院
					xNode = forceExecutionElement.selectSingleNode("Court");
					if (xNode != null) {
						forceExecution.setCourt(xNode.getText());
					}

					// [L3] 执行案由
					xNode = forceExecutionElement.selectSingleNode("CaseReason");
					if (xNode != null) {
						forceExecution.setCaseReason(xNode.getText());
					}

					// [L3] 立案日期
					xNode = forceExecutionElement.selectSingleNode("RegisterDate");
					if (xNode != null) {
						forceExecution.setRegisterDate(xNode.getText());
					}

					// [L3] 结案方式
					xNode = forceExecutionElement.selectSingleNode("ClosedType");
					if (xNode != null) {
						forceExecution.setClosedType(xNode.getText());
					}

					// [L3] 案件状态
					xNode = forceExecutionElement.selectSingleNode("CaseState");
					if (xNode != null) {
						forceExecution.setCaseState(xNode.getText());
					}

					// [L3] 结案日期
					xNode = forceExecutionElement.selectSingleNode("ClosedDate");
					if (xNode != null) {
						forceExecution.setClosedDate(xNode.getText());
					}

					// [L3] 申请执行标的
					xNode = forceExecutionElement.selectSingleNode("EnforceObject");
					if (xNode != null) {
						forceExecution.setEnforceObject(xNode.getText());
					}

					// [L3] 申请执行标的金额
					xNode = forceExecutionElement.selectSingleNode("EnforceObjectMoney");
					if (xNode != null) {
						forceExecution.setEnforceObjectMoney(xNode.getText());
					}

					// [L3] 已执行标的
					xNode = forceExecutionElement.selectSingleNode("AlreadyEnforceObject");
					if (xNode != null) {
						forceExecution.setAlreadyEnforceObject(xNode.getText());
					}

					// [L3] 已执行标的金额
					xNode = forceExecutionElement.selectSingleNode("AlreadyEnforceObjectMoney");
					if (xNode != null) {
						forceExecution.setAlreadyEnforceObjectMoney(xNode.getText());
					}

					forceExecutionList.add(forceExecution);
					xNode = null;
				}
				publicInfo.setForceExecutionList(forceExecutionList);
			}

			// [L2] 行政处罚记录（AdminPunishment）
			List<Element> adminPunishmentElementList = publicInfoNode.selectNodes("AdminPunishment");
			if (adminPunishmentElementList != null && adminPunishmentElementList.size() > 0) {
				List<AdminPunishment> adminPunishmentList = new ArrayList<AdminPunishment>();
				for (int i = 0; i < adminPunishmentElementList.size(); i++) {
					Element adminPunishmentElement = adminPunishmentElementList.get(i);
					if(adminPunishmentElement==null){
						continue;
					}
					AdminPunishment adminPunishment = publicInfo.new AdminPunishment();

					// [L3] 处罚机构
					xNode = adminPunishmentElement.selectSingleNode("Organname");
					if (xNode != null) {
						adminPunishment.setOrganname(xNode.getText());
					}

					// [L3] 处罚内容
					xNode = adminPunishmentElement.selectSingleNode("Content");
					if (xNode != null) {
						adminPunishment.setContent(xNode.getText());
					}

					// [L3] 处罚金额
					xNode = adminPunishmentElement.selectSingleNode("Money");
					if (xNode != null) {
						adminPunishment.setMoney(xNode.getText());
					}

					// [L3] 生效日期
					xNode = adminPunishmentElement.selectSingleNode("BeginDate");
					if (xNode != null) {
						adminPunishment.setBeginDate(xNode.getText());
					}

					// [L3] 截止日期
					xNode = adminPunishmentElement.selectSingleNode("EndDate");
					if (xNode != null) {
						adminPunishment.setEndDate(xNode.getText());
					}

					// [L3] 行政复议结果
					xNode = adminPunishmentElement.selectSingleNode("Result");
					if (xNode != null) {
						adminPunishment.setResult(xNode.getText());
					}

					adminPunishmentList.add(adminPunishment);
					xNode = null;
				}
				publicInfo.setAdminPunishmentList(adminPunishmentList);
			}

			// [L2] 住房公积金参缴记录（AccFund）
			List<Element> accFundNodeElementList = publicInfoNode.selectNodes("AccFund");
			if (accFundNodeElementList != null && accFundNodeElementList.size() > 0) {
				List<AccFund> accFundList = new ArrayList<AccFund>();
				for (int i = 0; i < accFundNodeElementList.size(); i++) {
					Element accFundNodeElement = accFundNodeElementList.get(i);
					if(accFundNodeElement==null){
						continue;
					}
					AccFund accFund = publicInfo.new AccFund();

					// [L3] 参缴地
					xNode = accFundNodeElement.selectSingleNode("Area");
					if (xNode != null) {
						accFund.setArea(xNode.getText());
					}

					// [L3] 参缴日期
					xNode = accFundNodeElement.selectSingleNode("RegisterDate");
					if (xNode != null) {
						accFund.setRegisterDate(xNode.getText());
					}

					// [L3] 初缴月份
					xNode = accFundNodeElement.selectSingleNode("FirstMonth");
					if (xNode != null) {
						accFund.setFirstMonth(xNode.getText());
					}

					// [L3] 缴至月份
					xNode = accFundNodeElement.selectSingleNode("ToMonth");
					if (xNode != null) {
						accFund.setToMonth(xNode.getText());
					}

					// [L3] 缴费状态
					xNode = accFundNodeElement.selectSingleNode("State");
					if (xNode != null) {
						accFund.setState(xNode.getText());
					}

					// [L3] 月缴存额
					xNode = accFundNodeElement.selectSingleNode("Pay");
					if (xNode != null) {
						accFund.setPay(xNode.getText());
					}

					// [L3] 个人缴存比例
					xNode = accFundNodeElement.selectSingleNode("OwnPercent");
					if (xNode != null) {
						accFund.setOwnPercent(xNode.getText());
					}

					// [L3] 单位缴存比例
					xNode = accFundNodeElement.selectSingleNode("ComPercent");
					if (xNode != null) {
						accFund.setComPercent(xNode.getText());
					}

					// [L3] 缴存单位
					xNode = accFundNodeElement.selectSingleNode("Organname");
					if (xNode != null) {
						accFund.setOrganname(xNode.getText());
					}

					// [L3] 信息更新时间
					xNode = accFundNodeElement.selectSingleNode("GetTime");
					if (xNode != null) {
						accFund.setGetTime(xNode.getText());
					}

					accFundList.add(accFund);
					xNode = null;
				}

				publicInfo.setAccFundList(accFundList);
			}

			// [L2] 养老保险金缴存记录（EndowmentInsuranceDeposit）
			List<Element> endowmentInsuranceDepositElementList = publicInfoNode
					.selectNodes("EndowmentInsuranceDeposit");
			if (endowmentInsuranceDepositElementList != null && endowmentInsuranceDepositElementList.size() > 0) {
				List<EndowmentInsuranceDeposit> endowmentInsuranceDepositList = new ArrayList<EndowmentInsuranceDeposit>();
				for (int i = 0; i < endowmentInsuranceDepositElementList.size(); i++) {
					Element endowmentInsuranceDepositElement = endowmentInsuranceDepositElementList.get(i);
					if(endowmentInsuranceDepositElement==null){
						continue;
					}
					
					EndowmentInsuranceDeposit endowmentInsuranceDeposit = publicInfo.new EndowmentInsuranceDeposit();

					// [L3] 参保地
					xNode = endowmentInsuranceDepositElement.selectSingleNode("Area");
					if (xNode != null) {
						endowmentInsuranceDeposit.setArea(xNode.getText());
					}

					// [L3] 参保年月
					xNode = endowmentInsuranceDepositElement.selectSingleNode("RegisterDate");
					if (xNode != null) {
						endowmentInsuranceDeposit.setRegisterDate(xNode.getText());
					}

					// [L3] 累计缴费月数
					xNode = endowmentInsuranceDepositElement.selectSingleNode("MonthDuration");
					if (xNode != null) {
						endowmentInsuranceDeposit.setMonthDuration(xNode.getText());
					}

					// [L3] 参加工作月份
					xNode = endowmentInsuranceDepositElement.selectSingleNode("WorkDate");
					if (xNode != null) {
						endowmentInsuranceDeposit.setWorkDate(xNode.getText());
					}

					// [L3] 缴费状态
					xNode = endowmentInsuranceDepositElement.selectSingleNode("State");
					if (xNode != null) {
						endowmentInsuranceDeposit.setState(xNode.getText());
					}

					// [L3] 个人缴费基数
					xNode = endowmentInsuranceDepositElement.selectSingleNode("OwnBasicMoney");
					if (xNode != null) {
						endowmentInsuranceDeposit.setOwnBasicMoney(xNode.getText());
					}

					// [L3] 本月缴费金额
					xNode = endowmentInsuranceDepositElement.selectSingleNode("Money");
					if (xNode != null) {
						endowmentInsuranceDeposit.setMoney(xNode.getText());
					}

					// [L3] 缴费单位
					xNode = endowmentInsuranceDepositElement.selectSingleNode("Organname");
					if (xNode != null) {
						endowmentInsuranceDeposit.setOrganname(xNode.getText());
					}

					// [L3] 中断或终止原因
					xNode = endowmentInsuranceDepositElement.selectSingleNode("PauseReason");
					if (xNode != null) {
						endowmentInsuranceDeposit.setPauseReason(xNode.getText());
					}

					// [L3] 信息更新日期
					xNode = endowmentInsuranceDepositElement.selectSingleNode("GetTime");
					if (xNode != null) {
						endowmentInsuranceDeposit.setGetTime(xNode.getText());
					}

					endowmentInsuranceDepositList.add(endowmentInsuranceDeposit);
					xNode = null;
				}
				publicInfo.setEndowmentInsuranceDepositList(endowmentInsuranceDepositList);
			}

			// [L2] 养老保险金发放记录（EndowmentInsuranceDeliver）
			List<Element> endowmentInsuranceDeliverElementList = publicInfoNode
					.selectNodes("EndowmentInsuranceDeliver");
			if (endowmentInsuranceDeliverElementList != null && endowmentInsuranceDeliverElementList.size() > 0) {
				List<EndowmentInsuranceDeliver> endowmentInsuranceDeliverList = new ArrayList<EndowmentInsuranceDeliver>();
				for (int i = 0; i < endowmentInsuranceDeliverElementList.size(); i++) {
					Element endowmentInsuranceDeliverElement = endowmentInsuranceDeliverElementList.get(i);
					if(endowmentInsuranceDeliverElement==null){
						continue;
					}
					EndowmentInsuranceDeliver endowmentInsuranceDeliver = publicInfo.new EndowmentInsuranceDeliver();

					// [L3] 发放地
					xNode = endowmentInsuranceDeliverElement.selectSingleNode("Area");
					if (xNode != null) {
						endowmentInsuranceDeliver.setArea(xNode.getText());
					}

					// [L3] 离退休类别
					xNode = endowmentInsuranceDeliverElement.selectSingleNode("RetireType");
					if (xNode != null) {
						endowmentInsuranceDeliver.setRetireType(xNode.getText());
					}

					// [L3] 离退休月份
					xNode = endowmentInsuranceDeliverElement.selectSingleNode("RetiredDate");
					if (xNode != null) {
						endowmentInsuranceDeliver.setRetiredDate(xNode.getText());
					}

					// [L3] 参加工作月份
					xNode = endowmentInsuranceDeliverElement.selectSingleNode("WorkDate");
					if (xNode != null) {
						endowmentInsuranceDeliver.setWorkDate(xNode.getText());
					}

					// [L3] 本月实发养老金
					xNode = endowmentInsuranceDeliverElement.selectSingleNode("Money");
					if (xNode != null) {
						endowmentInsuranceDeliver.setMoney(xNode.getText());
					}

					// [L3] 停发原因
					xNode = endowmentInsuranceDeliverElement.selectSingleNode("PauseReason");
					if (xNode != null) {
						endowmentInsuranceDeliver.setPauseReason(xNode.getText());
					}

					// [L3] 原单位名称
					xNode = endowmentInsuranceDeliverElement.selectSingleNode("Organname");
					if (xNode != null) {
						endowmentInsuranceDeliver.setOrganname(xNode.getText());
					}

					// [L3] 信息更新日期
					xNode = endowmentInsuranceDeliverElement.selectSingleNode("GetTime");
					if (xNode != null) {
						endowmentInsuranceDeliver.setGetTime(xNode.getText());
					}

					endowmentInsuranceDeliverList.add(endowmentInsuranceDeliver);
					xNode = null;
				}
				publicInfo.setEndowmentInsuranceDeliverList(endowmentInsuranceDeliverList);
			}

			// [L2] 低保救助记录（Salvation）
			List<Element> salvationElementList = publicInfoNode.selectNodes("Salvation");
			if (salvationElementList != null && salvationElementList.size() > 0) {
				List<Salvation> salvationList = new ArrayList<Salvation>();
				for (int i = 0; i < salvationElementList.size(); i++) {
					Element salvationElement = salvationElementList.get(i);
					if(salvationElement==null){
						continue;
					}
					Salvation salvation = publicInfo.new Salvation();

					// [L3] 人员类别
					xNode = salvationElement.selectSingleNode("PersonnelType");
					if (xNode != null) {
						salvation.setPersonnelType(xNode.getText());
					}

					// [L3] 所在地
					xNode = salvationElement.selectSingleNode("Area");
					if (xNode != null) {
						salvation.setArea(xNode.getText());
					}

					// [L3] 工作单位
					xNode = salvationElement.selectSingleNode("Organname");
					if (xNode != null) {
						salvation.setOrganname(xNode.getText());
					}

					// [L3] 家庭月收入
					xNode = salvationElement.selectSingleNode("Money");
					if (xNode != null) {
						salvation.setMoney(xNode.getText());
					}

					// [L3] 申请日期
					xNode = salvationElement.selectSingleNode("RegisterDate");
					if (xNode != null) {
						salvation.setRegisterDate(xNode.getText());
					}

					// [L3] 批准日期
					xNode = salvationElement.selectSingleNode("PassDate");
					if (xNode != null) {
						salvation.setPassDate(xNode.getText());
					}

					// [L3] 信息更新日期
					xNode = salvationElement.selectSingleNode("GetTime");
					if (xNode != null) {
						salvation.setGetTime(xNode.getText());
					}

					salvationList.add(salvation);
					xNode = null;
				}
				publicInfo.setSalvationList(salvationList);
			}

			// [L2] 执业资格记录（Competence）
			List<Element> competenceElementList = publicInfoNode.selectNodes("Competence");
			if (competenceElementList != null && competenceElementList.size() > 0) {
				List<Competence> competenceList = new ArrayList<Competence>();
				for (int i = 0; i < competenceElementList.size(); i++) {
					Element competenceElement = competenceElementList.get(i);
					if(competenceElement==null){
						continue;
					}
					Competence competence = publicInfo.new Competence();

					// [L3] 执业资格名称
					xNode = competenceElement.selectSingleNode("CompetencyName");
					if (xNode != null) {
						competence.setCompetencyName(xNode.getText());
					}

					// [L3] 等级
					xNode = competenceElement.selectSingleNode("Grade");
					if (xNode != null) {
						competence.setGrade(xNode.getText());
					}

					// [L3] 获得日期
					xNode = competenceElement.selectSingleNode("AwardDate");
					if (xNode != null) {
						competence.setAwardDate(xNode.getText());
					}

					// [L3] 到期日期
					xNode = competenceElement.selectSingleNode("EndDate");
					if (xNode != null) {
						competence.setEndDate(xNode.getText());
					}

					// [L3] 吊销日期
					xNode = competenceElement.selectSingleNode("RevokeDate");
					if (xNode != null) {
						competence.setRevokeDate(xNode.getText());
					}

					// [L3] 颁发机构
					xNode = competenceElement.selectSingleNode("Organname");
					if (xNode != null) {
						competence.setOrganname(xNode.getText());
					}

					// [L3] 机构所在地
					xNode = competenceElement.selectSingleNode("Area");
					if (xNode != null) {
						competence.setArea(xNode.getText());
					}

					competenceList.add(competence);
					xNode = null;
				}
				publicInfo.setCompetenceList(competenceList);
			}

			// [L2] 行政奖励记录（AdminAward）
			List<Element> adminAwardElementList = publicInfoNode.selectNodes("AdminAward");
			if (adminAwardElementList != null && adminAwardElementList.size() > 0) {
				List<AdminAward> adminAwardList = new ArrayList<AdminAward>();
				for (int i = 0; i < adminAwardElementList.size(); i++) {
					Element adminAwardElement = adminAwardElementList.get(i);
					if(adminAwardElement==null){
						continue;
					}
					AdminAward adminAward = publicInfo.new AdminAward();

					// [L3] 奖励机构
					xNode = adminAwardElement.selectSingleNode("Organname");
					if (xNode != null) {
						adminAward.setOrganname(xNode.getText());
					}

					// [L3] 奖励内容
					xNode = adminAwardElement.selectSingleNode("Content");
					if (xNode != null) {
						adminAward.setContent(xNode.getText());
					}

					// [L3] 生效日期
					xNode = adminAwardElement.selectSingleNode("BeginDate");
					if (xNode != null) {
						adminAward.setBeginDate(xNode.getText());
					}

					// [L3] 截止日期
					xNode = adminAwardElement.selectSingleNode("EndDate");
					if (xNode != null) {
						adminAward.setEndDate(xNode.getText());
					}

					adminAwardList.add(adminAward);
					xNode = null;
				}
				publicInfo.setAdminAwardList(adminAwardList);
			}

			// [L2] 车辆交易和抵押记录（Vehicle）
			List<Element> vehicleElementList = publicInfoNode.selectNodes("Vehicle");
			if (vehicleElementList != null && vehicleElementList.size() > 0) {
				List<Vehicle> vehicleList = new ArrayList<Vehicle>();
				for (int i = 0; i < vehicleElementList.size(); i++) {
					Element vehicleElement = vehicleElementList.get(i);
					if(vehicleElement==null){
						continue;
					}
					Vehicle vehicle = publicInfo.new Vehicle();

					// [L3] 发动机号
					xNode = vehicleElement.selectSingleNode("EngineCode");
					if (xNode != null) {
						vehicle.setEngineCode(xNode.getText());
					}

					// [L3] 车牌号码
					xNode = vehicleElement.selectSingleNode("LicenseCode");
					if (xNode != null) {
						vehicle.setLicenseCode(xNode.getText());
					}

					// [L3] 品牌
					xNode = vehicleElement.selectSingleNode("Brand");
					if (xNode != null) {
						vehicle.setBrand(xNode.getText());
					}

					// [L3] 车辆类型
					xNode = vehicleElement.selectSingleNode("CarType");
					if (xNode != null) {
						vehicle.setCarType(xNode.getText());
					}

					// [L3] 使用性质
					xNode = vehicleElement.selectSingleNode("UseCharacter");
					if (xNode != null) {
						vehicle.setUseCharacter(xNode.getText());
					}

					// [L3] 车辆状态
					xNode = vehicleElement.selectSingleNode("State");
					if (xNode != null) {
						vehicle.setState(xNode.getText());
					}

					// [L3] 抵押标记
					xNode = vehicleElement.selectSingleNode("PledgeFlag");
					if (xNode != null) {
						vehicle.setPledgeFlag(xNode.getText());
					}

					// [L3] 信息更新日期
					xNode = vehicleElement.selectSingleNode("GetTime");
					if (xNode != null) {
						vehicle.setGetTime(xNode.getText());
					}

					vehicleList.add(vehicle);
					xNode = null;
				}
				publicInfo.setVehicleList(vehicleList);
			}

			// [L2] 电信缴费记录（TelPayment）
			List<Element> telPaymentElementList = publicInfoNode.selectNodes("TelPayment");
			if (telPaymentElementList != null && telPaymentElementList.size() > 0) {
				List<TelPayment> telPaymentList = new ArrayList<TelPayment>();
				for (int i = 0; i < telPaymentElementList.size(); i++) {
					Element telPaymentElement = telPaymentElementList.get(i);
					if(telPaymentElement==null){
						continue;
					}
					TelPayment telPayment = publicInfo.new TelPayment();

					// [L3] 电信运营商
					xNode = telPaymentElement.selectSingleNode("Organname");
					if (xNode != null) {
						telPayment.setOrganname(xNode.getText());
					}

					// [L3] 业务类型
					xNode = telPaymentElement.selectSingleNode("Type");
					if (xNode != null) {
						telPayment.setType(xNode.getText());
					}

					// [L3] 业务开通日期
					xNode = telPaymentElement.selectSingleNode("RegisterDate");
					if (xNode != null) {
						telPayment.setRegisterDate(xNode.getText());
					}

					// [L3] 当前缴费状态
					xNode = telPaymentElement.selectSingleNode("State");
					if (xNode != null) {
						telPayment.setState(xNode.getText());
					}

					// [L3] 当前欠费金额
					xNode = telPaymentElement.selectSingleNode("ArrearMoney");
					if (xNode != null) {
						telPayment.setArrearMoney(xNode.getText());
					}

					// [L3] 当前欠费月数
					xNode = telPaymentElement.selectSingleNode("ArrearMonths");
					if (xNode != null) {
						telPayment.setArrearMonths(xNode.getText());
					}

					// [L3] 最近24个月缴费状态
					xNode = telPaymentElement.selectSingleNode("Status24");
					if (xNode != null) {
						telPayment.setStatus24(xNode.getText());
					}

					// [L3] 记账年月
					xNode = telPaymentElement.selectSingleNode("GetTime");
					if (xNode != null) {
						telPayment.setGetTime(xNode.getText());
					}

					telPaymentList.add(telPayment);
					xNode = null;
				}
				publicInfo.setTelPaymentList(telPaymentList);
			}

			pboc.setPublicInfo(publicInfo);
		}

		// ===============================================
		// == L1 - 声明信息（Announce）
		// ===============================================
		Node announceNode = document.selectSingleNode("/ReportMessage/Announce");
		if (announceNode != null) {
			PBOCAnnounce announce = new PBOCAnnounce();

			// [L2] 本人声明（AnnounceInfo）
			Node announceInfoNode = announceNode.selectSingleNode("AnnounceInfo");
			if (announceInfoNode != null) {
				PBOCAnnounce.AnnounceInfo announceInfo = announce.new AnnounceInfo();

				// [L3] 声明内容
				xNode = announceInfoNode.selectSingleNode("Content");
				if (xNode != null) {
					announceInfo.setContent(xNode.getText());
				}

				// [L3] 添加日期
				xNode = announceInfoNode.selectSingleNode("GetTime");
				if (xNode != null) {
					announceInfo.setGetTime(xNode.getText());
				}

				announce.setAnnounceInfo(announceInfo);
				xNode = null;
			}

			// [L2] 异议标注（DissentInfo）
			Node dissentInfoNode = announceNode.selectSingleNode("DissentInfo");
			if (dissentInfoNode != null) {
				PBOCAnnounce.DissentInfo dissentInfo = announce.new DissentInfo();

				// [L3] 标注内容
				xNode = dissentInfoNode.selectSingleNode("Content");
				if (xNode != null) {
					dissentInfo.setContent(xNode.getText());
				}

				// [L3] 添加日期
				xNode = dissentInfoNode.selectSingleNode("GetTime");
				if (xNode != null) {
					dissentInfo.setGetTime(xNode.getText());
				}

				announce.setDissentInfo(dissentInfo);
				xNode = null;
			}

			pboc.setAnnounce(announce);
		}

		// ===============================================
		// == L1 - 查询记录（QueryRecord）
		// ===============================================
		Node queryRecordNode = document.selectSingleNode("/ReportMessage/QueryRecord");
		if (queryRecordNode != null) {
			PBOCQueryRecord queryRecord = new PBOCQueryRecord();

			// [L2] 查询汇总记录（RecordSummary）
			Node recordSummaryNode = queryRecordNode.selectSingleNode("RecordSummary");
			if (recordSummaryNode != null) {
				RecordSummary recordSummary = queryRecord.new RecordSummary();

				// [L3] 最近一个月内的查询机构数（LatestMonthQueryorgSum）
				List<Element> latestMonthQueryorgSumElementList = recordSummaryNode
						.selectNodes("LatestMonthQueryorgSum");
				if (latestMonthQueryorgSumElementList != null & latestMonthQueryorgSumElementList.size() > 0) {
					List<RecordSummary.LatestMonthQueryorgSum> latestMonthQueryorgSumList = new ArrayList<RecordSummary.LatestMonthQueryorgSum>();
					for (int i = 0; i < latestMonthQueryorgSumElementList.size(); i++) {
						RecordSummary.LatestMonthQueryorgSum latestMonthQueryorgSum = recordSummary.new LatestMonthQueryorgSum();
						xElement = latestMonthQueryorgSumElementList.get(i);
						if(xElement==null){
							continue;
						}
						// [L4] 查询原因
						latestMonthQueryorgSum.setQueryReason(xElement.elementText("QueryReason"));

						// [L4] 数目
						latestMonthQueryorgSum.setSum(xElement.elementText("Sum"));

						latestMonthQueryorgSumList.add(latestMonthQueryorgSum);
						xElement = null;
					}
					recordSummary.setLatestMonthQueryorgSumList(latestMonthQueryorgSumList);
				}

				// [L3] 最近一个月内的查询次数（LatestMonthQueryrecordSum）
				List<Element> latestMonthQueryrecordSumElementList = recordSummaryNode
						.selectNodes("LatestMonthQueryrecordSum");
				if (latestMonthQueryrecordSumElementList != null & latestMonthQueryrecordSumElementList.size() > 0) {
					List<RecordSummary.LatestMonthQueryrecordSum> latestMonthQueryrecordSumList = new ArrayList<RecordSummary.LatestMonthQueryrecordSum>();
					for (int i = 0; i < latestMonthQueryrecordSumElementList.size(); i++) {
						RecordSummary.LatestMonthQueryrecordSum latestMonthQueryrecordSum = recordSummary.new LatestMonthQueryrecordSum();
						xElement = latestMonthQueryrecordSumElementList.get(i);
						if(xElement==null){
							continue;
						}
						// [L4] 查询原因
						latestMonthQueryrecordSum.setQueryReason(xElement.elementText("QueryReason"));

						// [L4] 数目
						latestMonthQueryrecordSum.setSum(xElement.elementText("Sum"));
						latestMonthQueryrecordSumList.add(latestMonthQueryrecordSum);
						xElement = null;
					}
					recordSummary.setLatestMonthQueryrecordSumList(latestMonthQueryrecordSumList);
				}

				// [L3] 最近两年内的查询次数（TwoyearQueryrecordSum）
				List<Element> twoyearQueryrecordSumElementList = recordSummaryNode.selectNodes("TwoyearQueryrecordSum");
				if (twoyearQueryrecordSumElementList != null & twoyearQueryrecordSumElementList.size() > 0) {
					List<RecordSummary.TwoyearQueryrecordSum> twoyearQueryrecordSumList = new ArrayList<RecordSummary.TwoyearQueryrecordSum>();
					for (int i = 0; i < twoyearQueryrecordSumElementList.size(); i++) {
						RecordSummary.TwoyearQueryrecordSum twoyearQueryrecordSum = recordSummary.new TwoyearQueryrecordSum();
						xElement = twoyearQueryrecordSumElementList.get(i);
						if(xElement==null){
							continue;
						}
						// [L4] 查询原因
						twoyearQueryrecordSum.setQueryReason(xElement.elementText("QueryReason"));

						// [L4] 数目
						twoyearQueryrecordSum.setSum(xElement.elementText("Sum"));

						twoyearQueryrecordSumList.add(twoyearQueryrecordSum);
						xElement = null;
					}
					recordSummary.setTwoyearQueryrecordSumList(twoyearQueryrecordSumList);
				}

				queryRecord.setRecordSummary(recordSummary);
			}

			// [L2]查询记录明细（RecordInfo）
			List<Element> recordInfoNodeList = queryRecordNode.selectNodes("RecordInfo");
			//Node recordInfoNode = queryRecordNode.selectSingleNode("RecordInfo");
			if(recordInfoNodeList!=null&&recordInfoNodeList.size()>0){
				List<RecordInfo> recordInfoList = new ArrayList<RecordInfo>();
				for (int j = 0; j < recordInfoNodeList.size(); j++) {
					Node recordInfoNode=recordInfoNodeList.get(j);
					if (recordInfoNode != null) {
						RecordInfo recordInfo = queryRecord.new RecordInfo();

						// [L3] 查询申请方式
						if (recordInfoNode.selectSingleNode("QueryReqFormat") != null) {
							recordInfo.setQueryReqFormat(recordInfoNode.selectSingleNode("QueryReqFormat").getText());
						}
						// [L3] 查询记录明细（RecordDetail）
						List<Element> recordDetailElementList = recordInfoNode.selectNodes("RecordDetail");
						if (recordDetailElementList != null & recordDetailElementList.size() > 0) {
							List<RecordInfo.RecordDetail> recordDetailList = new ArrayList<RecordInfo.RecordDetail>();
							for (int i = 0; i < recordDetailElementList.size(); i++) {
								RecordInfo.RecordDetail recordDetail = recordInfo.new RecordDetail();
								xElement = recordDetailElementList.get(i);
								if(xElement==null){
									continue;
								}
								// [L4] 查询日期
								recordDetail.setQueryDate(xElement.elementText("QueryDate"));

								// [L4] 查询操作员
								recordDetail.setQuerier(xElement.elementText("Querier"));

								// [L4] 查询原因
								recordDetail.setQueryReason(xElement.elementText("QueryReason"));

								recordDetailList.add(recordDetail);
								xElement = null;
							}
							recordInfo.setRecordDetailList(recordDetailList);
						}
						if(recordInfo!=null){
							recordInfoList.add(recordInfo);
						}
					}
					queryRecord.setRecordInfoList(recordInfoList);
				}
			}
			pboc.setQueryRecord(queryRecord);
		}

		return pboc;
	}

}
