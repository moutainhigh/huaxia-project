package com.huaxia.opas.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.domain.BRZX;
import com.huaxia.opas.domain.BRZXConsumption;
import com.huaxia.opas.domain.BRZXCreditPoint;
import com.huaxia.opas.domain.BRZXLocation;
import com.huaxia.opas.domain.BRZXSpecialListForCell;
import com.huaxia.opas.domain.BRZXSpecialListForId;
import com.huaxia.opas.domain.BRZXSpecialListForLmCell;
import com.huaxia.opas.domain.brzx.SpecialListForGid;
import com.huaxia.opas.interfaces.in.MessageParser;
import com.huaxia.opas.util.UUIDGen;

import net.sf.json.JSONObject;

/**
 * 百融报文解析器
 * 
 * @author zhiguo.li
 *
 */
public class BRZXMessageParser implements MessageParser<BRZX> {

	private final static Logger logger = LoggerFactory.getLogger(BRZXMessageParser.class);

	@Override
	public BRZX parse(String message) throws Exception {
		
		if (message == null || "".equals(message)) {
			return null;
			//throw new IllegalArgumentException("百融征信报文为空");
		}

		JSONObject json = JSONObject.fromObject(message);
		JSONObject flagObject = null;
		BRZX brzx = new BRZX();
		if (!json.containsKey("Flag") || json.get("Flag") == null || "".equals(json.get("Flag"))) {
			
			if (json.containsKey("success")&&!json.getBoolean("success") &&json.containsKey("error_code")
					&& "999999".equals(json.getString("error_code"))) {
				brzx.setCode("999999");
			}
			
			return brzx;
			//throw new IllegalArgumentException("百融征信报文Flag为空");
		} else {
			flagObject = JSONObject.fromObject(json.get("Flag"));
		}
		if (json.containsKey("swift_number")
				&& json.getString("swift_number") != null && !"".equals(json.getString("swift_number"))) {
			brzx.setSwiftNumber(json.getString("swift_number"));
		}
		if (json.containsKey("code") && json.getString("code") != null && !"".equals(json.getString("code"))) {
			brzx.setCode(json.getString("code"));
		}

		// 百融评分
		if (flagObject.containsKey("score")) {
			String flag_score = "";
			if (flagObject.getString("score") != null && !"".equals(flagObject.getString("score"))) {
				flag_score = flagObject.getString("score");
			}

			BRZXCreditPoint brCreditPoint = new BRZXCreditPoint();
			if ("0".equals(flag_score) || "".equals(flag_score)) {
				if (logger.isWarnEnabled()) {
					logger.warn("[百融数据解析 & 百融评分] 百融评分(Score)匹配为空!");
				}
			} else {
				JSONObject scoreObject = null;
				if ( json.containsKey("Score") && json.get("Score") != null && !"".equals(json.get("Score"))) {
					scoreObject = JSONObject.fromObject(json.get("Score"));
				}
				if ( scoreObject.containsKey("brcreditpoint") && scoreObject.getString("brcreditpoint") != null
						&& !"".equals(scoreObject.getString("brcreditpoint"))) {
					brCreditPoint.setBrCreditPoint(scoreObject.getString("brcreditpoint"));
					brzx.setCreditPoint(brCreditPoint);
				}
			}

			flag_score = null;
		}

		// 地址信息核对
		if (flagObject.containsKey("location")) {

			String flag_location = "";
			if (flagObject.getString("location") != null && !"".equals(flagObject.getString("location"))) {
				flag_location = flagObject.getString("location");
			}
			BRZXLocation location = new BRZXLocation();
			if ("0".equals(flag_location) || "".equals(flag_location)) {
				if (logger.isWarnEnabled()) {
					logger.warn("[百融数据解析 & 地址信息核对] 地址信息(Location)核对匹配为空!");
				}
			} else {
				if (json.containsKey("Location")) {
					JSONObject locationObject = null;
					if (json.get("Location") != null && !"".equals(json.get("Location"))) {
						locationObject = JSONObject.fromObject(json.get("Location"));
					}

					if (locationObject != null) {
						// 距离家庭地址的最小距离
						if (locationObject.containsKey("home_addr") && locationObject.getString("home_addr") != null && !"".equals(locationObject.getString("home_addr"))) {
							JSONObject homeAddrObject = JSONObject.fromObject(locationObject.get("home_addr"));
							parseHomeAddr(location, homeAddrObject);
						}

						// 距离公司地址的最小距离
						if (locationObject.containsKey("biz_addr") && locationObject.getString("biz_addr") != null && !"".equals(locationObject.getString("biz_addr"))) {
							JSONObject bizAddrObject = JSONObject.fromObject(locationObject.get("biz_addr"));
							parseBizAddr(location, bizAddrObject);
						}

						// 距离户籍地址的最小距离
						if (locationObject.containsKey("per_addr") && locationObject.getString("per_addr") != null && !"".equals(locationObject.getString("per_addr"))) {
							JSONObject perAddrObject = JSONObject.fromObject(locationObject.get("per_addr"));
							parsePerAddr(location, perAddrObject);
						}

						// 距离申请地址的最小距离
						if (locationObject.containsKey("apply_addr") && locationObject.getString("apply_addr") != null && !"".equals(locationObject.getString("apply_addr"))) {
							JSONObject applyAddrObject = JSONObject.fromObject(locationObject.get("apply_addr"));
							parseApplyAddr(location, applyAddrObject);
						}

						// 距离其他地址的最小距离
						if (locationObject.containsKey("oth_addr") && locationObject.getString("oth_addr") != null && !"".equals(locationObject.getString("oth_addr"))) {
							JSONObject othAddrObject = JSONObject.fromObject(locationObject.get("oth_addr"));
							parseOthAddr(location, othAddrObject);
						}
						brzx.setLocation(location);
					}
				}
			}
			flag_location = null;
		}

		// 特殊名单核查
		if (flagObject.containsKey("specialList_c")) {
			String flag_specialList_c = "";
			if (flagObject.getString("specialList_c") != null && !"".equals(flagObject.getString("specialList_c"))) {
				flag_specialList_c = flagObject.getString("specialList_c");
			}

			if ("0".equals(flag_specialList_c) || "".equals(flag_specialList_c)) {
				// 如果响应报文对应标签匹配值为"0"，则构建空对象。
				brzx.setSpecialListForId(null);
				brzx.setSpecialListForCell(null);
				brzx.setSpecialListForLmCell(null);
				if (logger.isWarnEnabled()) {
					logger.warn("[百融数据解析 & 特殊名单核查] 特殊名单(SpecialList_c)匹配为空!");
				}
			} else {
				JSONObject specialListObject = null;

				if (json.containsKey("SpecialList_c") && json.get("SpecialList_c") != null && !"".equals(json.get("SpecialList_c"))) {
					specialListObject = JSONObject.fromObject(json.get("SpecialList_c"));
				}

				if (specialListObject != null) {
					// 通过身份证查询
					JSONObject specialListForIdObject = null;
					if (specialListObject.containsKey("id") && specialListObject.get("id") != null && !"".equals(specialListObject.get("id"))) {
						specialListForIdObject = JSONObject.fromObject(specialListObject.get("id"));
						if (specialListForIdObject != null && !specialListForIdObject.isEmpty()) {
							BRZXSpecialListForId specialListForId = parseForId(specialListForIdObject);
							brzx.setSpecialListForId(specialListForId);
						}
					}

					// 通过手机号查询
					JSONObject specialListForCellObject = null;
					if (specialListObject.containsKey("cell") && specialListObject.get("cell") != null && !"".equals(specialListObject.get("cell"))) {
						specialListForCellObject = JSONObject.fromObject(specialListObject.get("cell"));
						if (specialListForCellObject != null && !specialListForCellObject.isEmpty()) {
							BRZXSpecialListForCell specialListForCell = parseForCell(specialListForCellObject);
							brzx.setSpecialListForCell(specialListForCell);
						}
					}

					// 通过联系人手机号查询
					JSONObject specialListForLmCellObject = null;
					if (specialListObject.containsKey("lm_cell") && specialListObject.get("lm_cell") != null && !"".equals(specialListObject.get("lm_cell"))) {
						specialListForLmCellObject = JSONObject.fromObject(specialListObject.get("lm_cell"));
						if (specialListForLmCellObject != null && !specialListForLmCellObject.isEmpty()) {
							BRZXSpecialListForLmCell specialListForLmCell = parseForLmCell(specialListForLmCellObject);
							specialListForLmCell.setUuid(UUIDGen.getUUID());
							brzx.setSpecialListForLmCell(specialListForLmCell);
						}
					}

					// 通过GID查询
					if (specialListObject.containsKey("gid") && specialListObject.get("gid") != null && !"".equals(specialListObject.get("gid"))) {
						JSONObject specialListForGidObject = JSONObject.fromObject(specialListObject.get("gid"));
						if (specialListForGidObject != null && !specialListForGidObject.isEmpty()) {
							SpecialListForGid specialListForGid = parseForGid(specialListForGidObject);
							if (specialListForGid != null) {
								specialListForGid.setUuid(UUIDGen.getUUID());
								brzx.setSpecialListForGid(specialListForGid);
							}
						}
					}
				}
			}
			flag_specialList_c = null;
		}

		// 商品消费评估
		if (flagObject.containsKey("consumption_c")) {
			String flag_consumption_c = "";
			if (flagObject.getString("consumption_c") != null && !"".equals(flagObject.getString("consumption_c"))) {
				flag_consumption_c = flagObject.getString("consumption_c");
			}

			if ("0".equals(flag_consumption_c) || "".equals(flag_consumption_c)) {
				brzx.setConsumption(new BRZXConsumption());

				if (logger.isWarnEnabled()) {
					logger.warn("[百融数据解析 & 商品消费评估] 未匹配上无输出!");
				}
			} else {
				JSONObject consumptionObject = null;
				if (json.containsKey("Consumption_c") && json.get("Consumption_c") != null
						&& !"".equals(json.get("Consumption_c"))) {
					consumptionObject = JSONObject.fromObject(json.get("Consumption_c"));
					BRZXConsumption consumption = parseConsumption(consumptionObject);
					brzx.setConsumption(consumption);
				}
			}
			flag_consumption_c = null;
		}

		return brzx;
	}

	private BRZXConsumption parseConsumption(JSONObject jsonObject) {
		BRZXConsumption entity = null;
		if (jsonObject != null && !jsonObject.isEmpty()) {
			entity = new BRZXConsumption();

			JSONObject totalObject = null;
			if (jsonObject.containsKey("total") && jsonObject.get("total") != null
					&& !"".equals(jsonObject.get("total"))) {
				totalObject = JSONObject.fromObject(jsonObject.get("total"));
			}

			if (totalObject != null && !totalObject.isEmpty()) {

				JSONObject totalMonth3Object = null;

				if (jsonObject.containsKey("month3") && totalObject.get("month3") != null
						&& !"".equals(totalObject.get("month3"))) {
					totalMonth3Object = JSONObject.fromObject(totalObject.get("month3"));
				}

				if (totalMonth3Object != null && !totalMonth3Object.isEmpty()) {

					if (totalMonth3Object.containsKey("visits") && totalMonth3Object.getString("visits") != null) {
						entity.setConsTotM3Visits(totalMonth3Object.getString("visits"));
					}
					if (totalMonth3Object.containsKey("number") && totalMonth3Object.getString("number") != null) {
						entity.setConsTotM3Num(totalMonth3Object.getString("number"));
					}
					if (totalMonth3Object.containsKey("pay") && totalMonth3Object.getString("pay") != null) {
						entity.setConsTotM3Pay(totalMonth3Object.getString("pay"));
					}
					if (totalMonth3Object.containsKey("p_catenum")
							&& totalMonth3Object.getString("p_catenum") != null) {
						entity.setConsTotM3PCatenum(totalMonth3Object.getString("p_catenum"));
					}
					if (totalMonth3Object.containsKey("v_catenum")
							&& totalMonth3Object.getString("v_catenum") != null) {
						entity.setConsTotM3VCatenum(totalMonth3Object.getString("v_catenum"));
					}
				}

				JSONObject totalMonth12Object = null;
				if (totalObject.containsKey("month12") && totalObject.get("month12") != null
						&& !"".equals(totalObject.get("month12"))) {
					totalMonth12Object = JSONObject.fromObject(totalObject.get("month12"));
				}
				if (totalMonth12Object != null && !totalMonth12Object.isEmpty()) {

					if (totalMonth12Object.containsKey("visits") && totalMonth12Object.getString("visits") != null) {
						entity.setConsTotM12Visits(totalMonth12Object.getString("visits"));
					}
					if (totalMonth12Object.containsKey("number") && totalMonth12Object.getString("number") != null) {
						entity.setConsTotM12Num(totalMonth12Object.getString("number"));
					}
					if (totalMonth12Object.containsKey("pay") && totalMonth12Object.getString("pay") != null) {
						entity.setConsTotM12Pay(totalMonth12Object.getString("pay"));
					}
					if (totalMonth12Object.containsKey("p_catenum")
							&& totalMonth12Object.getString("p_catenum") != null) {
						entity.setConsTotM12PCatenum(totalMonth12Object.getString("p_catenum"));
					}
					if (totalMonth12Object.containsKey("v_catenum")
							&& totalMonth12Object.getString("v_catenum") != null) {
						entity.setConsTotM12VCatenum(totalMonth12Object.getString("v_catenum"));
					}
				}

				JSONObject maxObject = null;
				if (jsonObject.containsKey("max") && jsonObject.get("max") != null
						&& !"".equals(jsonObject.get("max"))) {
					maxObject = JSONObject.fromObject(jsonObject.get("max"));
				}
				if (maxObject != null && !maxObject.isEmpty()) {

					JSONObject maxMonth3Object = null;
					if (maxObject.containsKey("month3") && maxObject.get("month3") != null
							&& !"".equals(maxObject.get("month3"))) {
						maxMonth3Object = JSONObject.fromObject(maxObject.get("month3"));
					}
					if (maxMonth3Object != null && !maxMonth3Object.isEmpty()) {

						if (maxMonth3Object.containsKey("number") && maxMonth3Object.getString("number") != null) {
							entity.setConsMaxM3Num(maxMonth3Object.getString("number"));
						}
						if (maxMonth3Object.containsKey("pay") && maxMonth3Object.getString("pay") != null) {
							entity.setConsMaxM3Pay(maxMonth3Object.getString("pay"));
						}
						if (maxMonth3Object.containsKey("paycate") && maxMonth3Object.getString("paycate") != null) {
							entity.setConsMaxM3Paycate(maxMonth3Object.getString("paycate"));
						}
					}
					JSONObject maxMonth12Object = null;
					if (maxObject.containsKey("month12") && maxObject.get("month12") != null
							&& !"".equals(maxObject.get("month12"))) {
						maxMonth12Object = JSONObject.fromObject(maxObject.get("month12"));
					}
					if (totalMonth12Object != null && !totalMonth12Object.isEmpty()) {

						if (maxObject.containsKey("number") && maxMonth12Object.getString("number") != null) {
							entity.setConsMaxM12Num(maxMonth12Object.getString("number"));
						}
						if (maxObject.containsKey("pay") && maxMonth12Object.getString("pay") != null) {
							entity.setConsMaxM12Pay(maxMonth12Object.getString("pay"));
						}
						if (maxObject.containsKey("paycate") && maxMonth12Object.getString("paycate") != null) {
							entity.setConsMaxM12Paycate(maxMonth12Object.getString("paycate"));
						}

					}
					JSONObject month3Object = null;
					if (jsonObject.containsKey("month3") && jsonObject.get("month3") != null
							&& !"".equals(jsonObject.get("month3"))) {
						month3Object = JSONObject.fromObject(jsonObject.get("month3"));
					}
					if (month3Object != null && !month3Object.isEmpty()) {

						JSONObject month3RYBHObject = null;
						if (month3Object.containsKey("日用百货") && month3Object.get("日用百货") != null
								&& !"".equals(month3Object.get("日用百货"))) {
							month3RYBHObject = JSONObject.fromObject(month3Object.get("日用百货"));
						}
						if (month3RYBHObject != null && !month3RYBHObject.isEmpty()) {
							if (month3RYBHObject.containsKey("number")
									&& month3RYBHObject.getString("number") != null) {
								entity.setConsM3RYBHNum(month3RYBHObject.getString("number"));
							}
							if (month3RYBHObject.containsKey("pay") && month3RYBHObject.getString("pay") != null) {
								entity.setConsM3RYBHPay(month3RYBHObject.getString("pay"));
							}
						}
						JSONObject month3JYDQObject = null;
						if (month3Object.containsKey("家用电器") && month3Object.get("家用电器") != null
								&& !"".equals(month3Object.get("家用电器"))) {
							month3JYDQObject = JSONObject.fromObject(month3Object.get("家用电器"));
						}
						if (month3JYDQObject != null && !month3JYDQObject.isEmpty()) {
							if (month3JYDQObject.containsKey("number")
									&& month3JYDQObject.getString("number") != null) {
								entity.setConsM3JYDQNum(month3JYDQObject.getString("number"));
							}
							if (month3JYDQObject.containsKey("pay") && month3JYDQObject.getString("pay") != null) {
								entity.setConsM3JYDQPay(month3JYDQObject.getString("pay"));
							}
						}
						JSONObject month3MYYPObject = null;
						if (month3Object.containsKey("母婴用品") && month3Object.get("母婴用品") != null
								&& !"".equals(month3Object.get("母婴用品"))) {
							month3MYYPObject = JSONObject.fromObject(month3Object.get("母婴用品"));
						}
						if (month3MYYPObject != null && !month3MYYPObject.isEmpty()) {
							if (month3MYYPObject.containsKey("number")
									&& month3MYYPObject.getString("number") != null) {
								entity.setConsM3MYYPNum(month3MYYPObject.getString("number"));
							}
							if (month3MYYPObject.containsKey("pay") && month3MYYPObject.getString("pay") != null) {
								entity.setConsM3MYYPPay(month3MYYPObject.getString("pay"));
							}
						}
					}

					JSONObject month12Object = null;
					if (jsonObject.containsKey("month12") && jsonObject.get("month12") != null) {
						month12Object = JSONObject.fromObject(jsonObject.get("month12"));
					}
					if (month12Object != null && !month12Object.isEmpty()) {

						JSONObject month12RYBHObject = null;
						if (month12Object.containsKey("日用百货") && month12Object.get("日用百货") != null
								&& !"".equals(month12Object.get("日用百货"))) {
							month12RYBHObject = JSONObject.fromObject(month12Object.get("日用百货"));
						}
						if (month12RYBHObject != null && !month12RYBHObject.isEmpty()) {
							if (month12RYBHObject.containsKey("number")
									&& month12RYBHObject.getString("number") != null) {
								entity.setConsM12RYBHNum(month12RYBHObject.getString("number"));
							}
							if (month12RYBHObject.containsKey("pay") && month12RYBHObject.getString("pay") != null) {
								entity.setConsM12RYBHPay(month12RYBHObject.getString("pay"));
							}
						}
						JSONObject month12JYDQObject = null;
						if (month12Object.containsKey("家用电器") && month12Object.get("家用电器") != null
								&& !"".equals(month12Object.get("家用电器"))) {
							month12JYDQObject = JSONObject.fromObject(month12Object.get("家用电器"));
						}
						if (month12JYDQObject != null && !month12JYDQObject.isEmpty()) {
							if (month12JYDQObject.containsKey("number")
									&& month12JYDQObject.getString("number") != null) {
								entity.setConsM12JYDQNum(month12JYDQObject.getString("number"));
							}
							if (month12JYDQObject.containsKey("pay") && month12JYDQObject.getString("pay") != null) {
								entity.setConsM12JYDQPay(month12JYDQObject.getString("pay"));
							}
						}
						JSONObject month12MYYPObject = null;
						if (month12Object.containsKey("母婴用品") && month12Object.get("母婴用品") != null
								&& !"".equals(month12Object.get("母婴用品"))) {
							month12MYYPObject = JSONObject.fromObject(month12Object.get("母婴用品"));
						}
						if (month12MYYPObject != null && !month12MYYPObject.isEmpty()) {
							if (month12MYYPObject.containsKey("number")
									&& month12MYYPObject.getString("number") != null) {
								entity.setConsM12MYYPNum(month12MYYPObject.getString("number"));
							}
							if (month12MYYPObject.containsKey("pay") && month12MYYPObject.getString("pay") != null) {
								entity.setConsM12MYYPPay(month12MYYPObject.getString("pay"));
							}
						}
					}
				}
			}
		}
		return entity;
	}

	private SpecialListForGid parseForGid(JSONObject jsonObject) {
		SpecialListForGid entity = null;
		if (jsonObject != null && !jsonObject.isEmpty()) {
			entity = new SpecialListForGid();
			if (jsonObject.containsKey("bank_bad") && jsonObject.getString("bank_bad") != null) {
				entity.setSlGidBankBad(jsonObject.getString("bank_bad"));
			}

			if (jsonObject.containsKey("phone_overdue") && jsonObject.getString("phone_overdue") != null) {
				entity.setSlGidPhoneOverdue(jsonObject.getString("phone_overdue"));
			}

			if (jsonObject.containsKey("bank_fraud") && jsonObject.getString("bank_fraud") != null) {
				entity.setSlGidBankFraud(jsonObject.getString("bank_fraud"));
			}

			if (jsonObject.containsKey("bank_lost") && jsonObject.getString("bank_lost") != null) {
				entity.setSlGidBankLost(jsonObject.getString("bank_lost"));
			}

			if (jsonObject.containsKey("bank_refuse") && jsonObject.getString("bank_refuse") != null) {
				entity.setSlGidBankRefuse(jsonObject.getString("bank_refuse"));
			}

			if (jsonObject.containsKey("p2p_bad") && jsonObject.getString("p2p_bad") != null) {
				entity.setSlGidP2pBad(jsonObject.getString("p2p_bad"));
			}

			if (jsonObject.containsKey("p2p_overdue") && jsonObject.getString("p2p_overdue") != null) {
				entity.setSlGidP2pOverdue(jsonObject.getString("p2p_overdue"));
			}

			if (jsonObject.containsKey("p2p_fraud") && jsonObject.getString("p2p_fraud") != null) {
				entity.setSlGidP2pFraud(jsonObject.getString("p2p_fraud"));
			}

			if (jsonObject.containsKey("p2p_lost") && jsonObject.getString("p2p_lost") != null) {
				entity.setSlGidP2pLost(jsonObject.getString("p2p_lost"));
			}

			if (jsonObject.containsKey("p2p_refuse") && jsonObject.getString("p2p_refuse") != null) {
				entity.setSlGidP2pRefuse(jsonObject.getString("p2p_refuse"));
			}

			if (jsonObject.containsKey("nbank_p2p_bad") && jsonObject.getString("nbank_p2p_bad") != null) {
				entity.setSlGidNbankP2pBad(jsonObject.getString("nbank_p2p_bad"));
			}

			if (jsonObject.containsKey("nbank_p2p_overdue") && jsonObject.getString("nbank_p2p_overdue") != null) {
				entity.setSlGidNbankP2pOverdue(jsonObject.getString("nbank_p2p_overdue"));
			}

			if (jsonObject.containsKey("nbank_p2p_fraud") && jsonObject.getString("nbank_p2p_fraud") != null) {
				entity.setSlGidNbankP2pFraud(jsonObject.getString("nbank_p2p_fraud"));
			}

			if (jsonObject.containsKey("nbank_p2p_lost") && jsonObject.getString("nbank_p2p_lost") != null) {
				entity.setSlGidNbankP2pLost(jsonObject.getString("nbank_p2p_lost"));
			}

			if (jsonObject.containsKey("nbank_p2p_refuse") && jsonObject.getString("nbank_p2p_refuse") != null) {
				entity.setSlGidNbankP2pRefuse(jsonObject.getString("nbank_p2p_refuse"));
			}

			if (jsonObject.containsKey("nbank_mc_bad") && jsonObject.getString("nbank_mc_bad") != null) {
				entity.setSlGidNbankMcBad(jsonObject.getString("nbank_mc_bad"));
			}

			if (jsonObject.containsKey("nbank_mc_overdue") && jsonObject.getString("nbank_mc_overdue") != null) {
				entity.setSlGidNbankMcOverdue(jsonObject.getString("nbank_mc_overdue"));
			}

			if (jsonObject.containsKey("nbank_mc_fraud") && jsonObject.getString("nbank_mc_fraud") != null) {
				entity.setSlGidNbankMcFraud(jsonObject.getString("nbank_mc_fraud"));
			}

			if (jsonObject.containsKey("nbank_mc_lost") && jsonObject.getString("nbank_mc_lost") != null) {
				entity.setSlGidNbankMcLost(jsonObject.getString("nbank_mc_lost"));
			}

			if (jsonObject.containsKey("nbank_mc_refuse") && jsonObject.getString("nbank_mc_refuse") != null) {
				entity.setSlGidNbankMcRefuse(jsonObject.getString("nbank_mc_refuse"));
			}

			if (jsonObject.containsKey("nbank_ca_bad") && jsonObject.getString("nbank_ca_bad") != null) {
				entity.setSlGidNbankCaBad(jsonObject.getString("nbank_ca_bad"));
			}

			if (jsonObject.containsKey("nbank_ca_overdue") && jsonObject.getString("nbank_ca_overdue") != null) {
				entity.setSlGidNbankCaOverdue(jsonObject.getString("nbank_ca_overdue"));
			}

			if (jsonObject.containsKey("nbank_ca_fraud") && jsonObject.getString("nbank_ca_fraud") != null) {
				entity.setSlGidNbankCaFraud(jsonObject.getString("nbank_ca_fraud"));
			}

			if (jsonObject.containsKey("nbank_ca_lost") && jsonObject.getString("nbank_ca_lost") != null) {
				entity.setSlGidNbankCaLost(jsonObject.getString("nbank_ca_lost"));
			}

			if (jsonObject.containsKey("nbank_ca_refuse") && jsonObject.getString("nbank_ca_refuse") != null) {
				entity.setSlGidNbankCaRefuse(jsonObject.getString("nbank_ca_refuse"));
			}

			if (jsonObject.containsKey("nbank_com_bad") && jsonObject.getString("nbank_com_bad") != null) {
				entity.setSlGidNbankComBad(jsonObject.getString("nbank_com_bad"));
			}
			
			if (jsonObject.containsKey("nbank_com_overdue") && jsonObject.getString("nbank_com_overdue") != null) {
				entity.setSlGidNbankComOverdue(jsonObject.getString("nbank_com_overdue"));
			}
			
			if (jsonObject.containsKey("nbank_com_fraud") && jsonObject.getString("nbank_com_fraud") != null) {
				entity.setSlGidNbankComFraud(jsonObject.getString("nbank_com_fraud"));
			}
			
			if (jsonObject.containsKey("nbank_com_lost") && jsonObject.getString("nbank_com_lost") != null) {
				entity.setSlGidNbankComLost(jsonObject.getString("nbank_com_lost"));
			}
			
			if (jsonObject.containsKey("nbank_com_refuse") && jsonObject.getString("nbank_com_refuse") != null) {
				entity.setSlGidNbankComRefuse(jsonObject.getString("nbank_com_refuse"));
			}
			
			if (jsonObject.containsKey("nbank_cf_bad") && jsonObject.getString("nbank_cf_bad") != null) {
				entity.setSlGidNbankCfBad(jsonObject.getString("nbank_cf_bad"));
			}
			
			if (jsonObject.containsKey("nbank_cf_overdue") && jsonObject.getString("nbank_cf_overdue") != null) {
				entity.setSlGidNbankCfOverdue(jsonObject.getString("nbank_cf_overdue"));
			}
			
			if (jsonObject.containsKey("nbank_cf_fraud") && jsonObject.getString("nbank_cf_fraud") != null) {
				entity.setSlGidNbankCfFraud(jsonObject.getString("nbank_cf_fraud"));
			}
			
			if (jsonObject.containsKey("nbank_cf_lost") && jsonObject.getString("nbank_cf_lost") != null) {
				entity.setSlGidNbankCfLost(jsonObject.getString("nbank_cf_lost"));
			}
			
			if (jsonObject.containsKey("nbank_cf_refuse") && jsonObject.getString("nbank_cf_refuse") != null) {
				entity.setSlGidNbankCfRefuse(jsonObject.getString("nbank_cf_refuse"));
			}
			
			if (jsonObject.containsKey("nbank_other_bad") && jsonObject.getString("nbank_other_bad") != null) {
				entity.setSlGidNbankOtherBad(jsonObject.getString("nbank_other_bad"));
			}
			
			if (jsonObject.containsKey("nbank_other_overdue") && jsonObject.getString("nbank_other_overdue") != null) {
				entity.setSlGidNbankOtherOverdue(jsonObject.getString("nbank_other_overdue"));
			}
			
			if (jsonObject.containsKey("nbank_other_fraud") && jsonObject.getString("nbank_other_fraud") != null) {
				entity.setSlGidNbankOtherFraud(jsonObject.getString("nbank_other_fraud"));
			}
			
			if (jsonObject.containsKey("nbank_other_lost") && jsonObject.getString("nbank_other_lost") != null) {
				entity.setSlGidNbankOtherLost(jsonObject.getString("nbank_other_lost"));
			}
			
			if (jsonObject.containsKey("nbank_other_refuse") && jsonObject.getString("nbank_other_refuse") != null) {
				entity.setSlGidNbankOtherRefuse(jsonObject.getString("nbank_other_refuse"));
			}
		}
		return entity;
	}

	private BRZXSpecialListForLmCell parseForLmCell(JSONObject jsonObject) {
		BRZXSpecialListForLmCell entity = null;
		if (jsonObject != null && !jsonObject.isEmpty()) {
			entity = new BRZXSpecialListForLmCell();
			if (jsonObject.containsKey("abnormal") && jsonObject.getString("abnormal") != null) {
				entity.setSlLmCellAbnormal(jsonObject.getString("abnormal"));
			}
			if (jsonObject.containsKey("phone_overdue") && jsonObject.getString("phone_overdue") != null) {
				entity.setSlLmCellPhoneOverdue(jsonObject.getString("phone_overdue"));
			}
			if (jsonObject.containsKey("bank_bad") && jsonObject.getString("bank_bad") != null) {
				entity.setSlLmCellBankBad(jsonObject.getString("bank_bad"));
			}
			if (jsonObject.containsKey("bank_overdue") && jsonObject.getString("bank_overdue") != null) {
				entity.setSlLmCellBankOverdue(jsonObject.getString("bank_overdue"));
			}
			if (jsonObject.containsKey("bank_fraud") && jsonObject.getString("bank_fraud") != null) {
				entity.setSlLmCellBankFraud(jsonObject.getString("bank_fraud"));
			}
			if (jsonObject.containsKey("bank_lost") && jsonObject.getString("bank_lost") != null) {
				entity.setSlLmCellBankLost(jsonObject.getString("bank_lost"));
			}
			if (jsonObject.containsKey("bank_refuse") && jsonObject.getString("bank_refuse") != null) {
				entity.setSlLmCellBankRefuse(jsonObject.getString("bank_refuse"));
			}
			if (jsonObject.containsKey("nbank_p2p_bad") && jsonObject.getString("nbank_p2p_bad") != null) {
				entity.setSlLmCellNbankP2pBad(jsonObject.getString("nbank_p2p_bad"));
			}
			if (jsonObject.containsKey("nbank_p2p_overdue") && jsonObject.getString("nbank_p2p_overdue") != null) {
				entity.setSlLmCellNbankP2pOverdue(jsonObject.getString("nbank_p2p_overdue"));
			}
			if (jsonObject.containsKey("nbank_p2p_fraud") && jsonObject.getString("nbank_p2p_fraud") != null) {
				entity.setSlLmCellNbankP2pFraud(jsonObject.getString("nbank_p2p_fraud"));
			}
			if (jsonObject.containsKey("nbank_p2p_lost") && jsonObject.getString("nbank_p2p_lost") != null) {
				entity.setSlLmCellNbankP2pLost(jsonObject.getString("nbank_p2p_lost"));
			}
			if (jsonObject.containsKey("nbank_p2p_refuse") && jsonObject.getString("nbank_p2p_refuse") != null) {
				entity.setSlLmCellNbankP2pRefuse(jsonObject.getString("nbank_p2p_refuse"));
			}
			if (jsonObject.containsKey("nbank_mc_bad") && jsonObject.getString("nbank_mc_bad") != null) {
				entity.setSlLmCellNbankMcBad(jsonObject.getString("nbank_mc_bad"));
			}
			if (jsonObject.containsKey("nbank_mc_overdue") && jsonObject.getString("nbank_mc_overdue") != null) {
				entity.setSlLmCellNbankMcOverdue(jsonObject.getString("nbank_mc_overdue"));
			}
			if (jsonObject.containsKey("nbank_mc_fraud") && jsonObject.getString("nbank_mc_fraud") != null) {
				entity.setSlLmCellNbankMcFraud(jsonObject.getString("nbank_mc_fraud"));
			}
			if (jsonObject.containsKey("nbank_mc_lost") && jsonObject.getString("nbank_mc_lost") != null) {
				entity.setSlLmCellNbankMcLost(jsonObject.getString("nbank_mc_lost"));
			}
			if (jsonObject.containsKey("nbank_mc_refuse") && jsonObject.getString("nbank_mc_refuse") != null) {
				entity.setSlLmCellNbankMcRefuse(jsonObject.getString("nbank_mc_refuse"));
			}
			if (jsonObject.containsKey("nbank_ca_bad") && jsonObject.getString("nbank_ca_bad") != null) {
				entity.setSlLmCellNbankCaBad(jsonObject.getString("nbank_ca_bad"));
			}
			if (jsonObject.containsKey("nbank_ca_overdue") && jsonObject.getString("nbank_ca_overdue") != null) {
				entity.setSlLmCellNbankCaOverdue(jsonObject.getString("nbank_ca_overdue"));
			}
			if (jsonObject.containsKey("nbank_ca_fraud") && jsonObject.getString("nbank_ca_fraud") != null) {
				entity.setSlLmCellNbankCaFraud(jsonObject.getString("nbank_ca_fraud"));
			}
			if (jsonObject.containsKey("nbank_ca_lost") && jsonObject.getString("nbank_ca_lost") != null) {
				entity.setSlLmCellNbankCaLost(jsonObject.getString("nbank_ca_lost"));
			}
			if (jsonObject.containsKey("nbank_ca_refuse") && jsonObject.getString("nbank_ca_refuse") != null) {
				entity.setSlLmCellNbankCaRefuse(jsonObject.getString("nbank_ca_refuse"));
			}
			if (jsonObject.containsKey("nbank_com_bad") && jsonObject.getString("nbank_com_bad") != null) {
				entity.setSlLmCellNbankComBad(jsonObject.getString("nbank_com_bad"));
			}
			if (jsonObject.containsKey("nbank_com_overdue") && jsonObject.getString("nbank_com_overdue") != null) {
				entity.setSlLmCellNbankComOverdue(jsonObject.getString("nbank_com_overdue"));
			}
			if (jsonObject.containsKey("nbank_com_fraud") && jsonObject.getString("nbank_com_fraud") != null) {
				entity.setSlLmCellNbankComFraud(jsonObject.getString("nbank_com_fraud"));
			}
			if (jsonObject.containsKey("nbank_com_lost") && jsonObject.getString("nbank_com_lost") != null) {
				entity.setSlLmCellNbankComLost(jsonObject.getString("nbank_com_lost"));
			}
			if (jsonObject.containsKey("nbank_com_refuse") && jsonObject.getString("nbank_com_refuse") != null) {
				entity.setSlLmCellNbankComRefuse(jsonObject.getString("nbank_com_refuse"));
			}
			if (jsonObject.containsKey("nbank_cf_bad") && jsonObject.getString("nbank_cf_bad") != null) {
				entity.setSlLmCellNbankCfBad(jsonObject.getString("nbank_cf_bad"));
			}
			if (jsonObject.containsKey("nbank_cf_overdue") && jsonObject.getString("nbank_cf_overdue") != null) {
				entity.setSlLmCellNbankCfOverdue(jsonObject.getString("nbank_cf_overdue"));
			}
			if (jsonObject.containsKey("nbank_cf_fraud") && jsonObject.getString("nbank_cf_fraud") != null) {
				entity.setSlLmCellNbankCfFraud(jsonObject.getString("nbank_cf_fraud"));
			}
			if (jsonObject.containsKey("nbank_cf_lost") && jsonObject.getString("nbank_cf_lost") != null) {
				entity.setSlLmCellNbankCfLost(jsonObject.getString("nbank_cf_lost"));
			}
			if (jsonObject.containsKey("nbank_cf_refuse") && jsonObject.getString("nbank_cf_refuse") != null) {
				entity.setSlLmCellNbankCfRefuse(jsonObject.getString("nbank_cf_refuse"));
			}
			if (jsonObject.containsKey("nbank_other_bad") && jsonObject.getString("nbank_other_bad") != null) {
				entity.setSlLmCellNbankOtherBad(jsonObject.getString("nbank_other_bad"));
			}
			if (jsonObject.containsKey("nbank_other_overdue") && jsonObject.getString("nbank_other_overdue") != null) {
				entity.setSlLmCellNbankOtherOverdue(jsonObject.getString("nbank_other_overdue"));
			}
			if (jsonObject.containsKey("nbank_other_fraud") && jsonObject.getString("nbank_other_fraud") != null) {
				entity.setSlLmCellNbankOtherFraud(jsonObject.getString("nbank_other_fraud"));
			}
			if (jsonObject.containsKey("nbank_other_lost") && jsonObject.getString("nbank_other_lost") != null) {
				entity.setSlLmCellNbankOtherLost(jsonObject.getString("nbank_other_lost"));
			}
			if (jsonObject.containsKey("nbank_other_refuse") && jsonObject.getString("nbank_other_refuse") != null) {
				entity.setSlLmCellNbankOtherRefuse(jsonObject.getString("nbank_other_refuse"));
			}
		}
		return entity;
	}

	private BRZXSpecialListForCell parseForCell(JSONObject jsonObject) {
		BRZXSpecialListForCell entity = null;
		if (jsonObject != null && !jsonObject.isEmpty()) {
			entity = new BRZXSpecialListForCell();

			if (jsonObject.containsKey("abnormal") && jsonObject.getString("abnormal") != null) {
				entity.setSlCellAbnormal(jsonObject.getString("abnormal"));
			}
			if (jsonObject.containsKey("phone_overdue") && jsonObject.getString("phone_overdue") != null) {
				entity.setSlCellPhoneOverdue(jsonObject.getString("phone_overdue"));
			}
			if (jsonObject.containsKey("bank_bad") && jsonObject.getString("bank_bad") != null) {
				entity.setSlCellBankBad(jsonObject.getString("bank_bad"));
			}
			if (jsonObject.containsKey("bank_overdue") && jsonObject.getString("bank_overdue") != null) {
				entity.setSlCellBankOverdue(jsonObject.getString("bank_overdue"));
			}
			if (jsonObject.containsKey("bank_fraud") && jsonObject.getString("bank_fraud") != null) {
				entity.setSlCellBankFraud(jsonObject.getString("bank_fraud"));
			}
			if (jsonObject.containsKey("bank_lost") && jsonObject.getString("bank_lost") != null) {
				entity.setSlCellBankLost(jsonObject.getString("bank_lost"));
			}
			if (jsonObject.containsKey("bank_refuse") && jsonObject.getString("bank_refuse") != null) {
				entity.setSlCellBankRefuse(jsonObject.getString("bank_refuse"));
			}
			if (jsonObject.containsKey("p2p_bad") && jsonObject.getString("p2p_bad") != null) {
				entity.setSlCellP2pBad(jsonObject.getString("p2p_bad"));
			}
			if (jsonObject.containsKey("p2p_overdue") && jsonObject.getString("p2p_overdue") != null) {
				entity.setSlCellP2pOverdue(jsonObject.getString("p2p_overdue"));
			}
			if (jsonObject.containsKey("p2p_fraud") && jsonObject.getString("p2p_fraud") != null) {
				entity.setSlCellP2pFraud(jsonObject.getString("p2p_fraud"));
			}
			if (jsonObject.containsKey("p2p_lost") && jsonObject.getString("p2p_lost") != null) {
				entity.setSlCellP2pLost(jsonObject.getString("p2p_lost"));
			}
			if (jsonObject.containsKey("p2p_refuse") && jsonObject.getString("p2p_refuse") != null) {
				entity.setSlCellP2pRefuse(jsonObject.getString("p2p_refuse"));
			}
			if (jsonObject.containsKey("nbank_p2p_bad") && jsonObject.getString("nbank_p2p_bad") != null) {
				entity.setSlCellNbankP2pBad(jsonObject.getString("nbank_p2p_bad"));
			}
			if (jsonObject.containsKey("nbank_p2p_overdue") && jsonObject.getString("nbank_p2p_overdue") != null) {
				entity.setSlCellNbankP2pOverdue(jsonObject.getString("nbank_p2p_overdue"));
			}
			if (jsonObject.containsKey("nbank_p2p_fraud") && jsonObject.getString("nbank_p2p_fraud") != null) {
				entity.setSlCellNbankP2pFraud(jsonObject.getString("nbank_p2p_fraud"));
			}
			if (jsonObject.containsKey("nbank_p2p_lost") && jsonObject.getString("nbank_p2p_lost") != null) {
				entity.setSlCellNbankP2pLost(jsonObject.getString("nbank_p2p_lost"));
			}
			if (jsonObject.containsKey("nbank_p2p_refuse") && jsonObject.getString("nbank_p2p_refuse") != null) {
				entity.setSlCellNbankP2pRefuse(jsonObject.getString("nbank_p2p_refuse"));
			}
			if (jsonObject.containsKey("nbank_mc_bad") && jsonObject.getString("nbank_mc_bad") != null) {
				entity.setSlCellNbankMcBad(jsonObject.getString("nbank_mc_bad"));
			}
			if (jsonObject.containsKey("nbank_mc_overdue") && jsonObject.getString("nbank_mc_overdue") != null) {
				entity.setSlCellNbankMcOverdue(jsonObject.getString("nbank_mc_overdue"));
			}
			if (jsonObject.containsKey("nbank_mc_fraud") && jsonObject.getString("nbank_mc_fraud") != null) {
				entity.setSlCellNbankMcFraud(jsonObject.getString("nbank_mc_fraud"));
			}
			if (jsonObject.containsKey("nbank_mc_lost") && jsonObject.getString("nbank_mc_lost") != null) {
				entity.setSlCellNbankMcLost(jsonObject.getString("nbank_mc_lost"));
			}
			if (jsonObject.containsKey("nbank_mc_refuse") && jsonObject.getString("nbank_mc_refuse") != null) {
				entity.setSlCellNbankMcRefuse(jsonObject.getString("nbank_mc_refuse"));
			}
			if (jsonObject.containsKey("nbank_ca_bad") && jsonObject.getString("nbank_ca_bad") != null) {
				entity.setSlCellNbankCaBad(jsonObject.getString("nbank_ca_bad"));
			}
			if (jsonObject.containsKey("nbank_ca_overdue") && jsonObject.getString("nbank_ca_overdue") != null) {
				entity.setSlCellNbankCaOverdue(jsonObject.getString("nbank_ca_overdue"));
			}
			if (jsonObject.containsKey("nbank_ca_fraud") && jsonObject.getString("nbank_ca_fraud") != null) {
				entity.setSlCellNbankCaFraud(jsonObject.getString("nbank_ca_fraud"));
			}
			if (jsonObject.containsKey("nbank_ca_lost") && jsonObject.getString("nbank_ca_lost") != null) {
				entity.setSlCellNbankCaLost(jsonObject.getString("nbank_ca_lost"));
			}
			if (jsonObject.containsKey("nbank_ca_refuse") && jsonObject.getString("nbank_ca_refuse") != null) {
				entity.setSlCellNbankCaRefuse(jsonObject.getString("nbank_ca_refuse"));
			}
			if (jsonObject.containsKey("nbank_com_bad") && jsonObject.getString("nbank_com_bad") != null) {
				entity.setSlCellNbankComBad(jsonObject.getString("nbank_com_bad"));
			}
			if (jsonObject.containsKey("nbank_com_overdue") && jsonObject.getString("nbank_com_overdue") != null) {
				entity.setSlCellNbankComOverdue(jsonObject.getString("nbank_com_overdue"));
			}
			if (jsonObject.containsKey("nbank_com_fraud") && jsonObject.getString("nbank_com_fraud") != null) {
				entity.setSlCellNbankComFraud(jsonObject.getString("nbank_com_fraud"));
			}
			if (jsonObject.containsKey("nbank_com_lost") && jsonObject.getString("nbank_com_lost") != null) {
				entity.setSlCellNbankComLost(jsonObject.getString("nbank_com_lost"));
			}
			if (jsonObject.containsKey("nbank_com_refuse") && jsonObject.getString("nbank_com_refuse") != null) {
				entity.setSlCellNbankComRefuse(jsonObject.getString("nbank_com_refuse"));
			}
			if (jsonObject.containsKey("nbank_cf_bad") && jsonObject.getString("nbank_cf_bad") != null) {
				entity.setSlCellNbankCfBad(jsonObject.getString("nbank_cf_bad"));
			}
			if (jsonObject.containsKey("nbank_cf_overdue") && jsonObject.getString("nbank_cf_overdue") != null) {
				entity.setSlCellNbankCfOverdue(jsonObject.getString("nbank_cf_overdue"));
			}
			if (jsonObject.containsKey("nbank_cf_fraud") && jsonObject.getString("nbank_cf_fraud") != null) {
				entity.setSlCellNbankCfFraud(jsonObject.getString("nbank_cf_fraud"));
			}
			if (jsonObject.containsKey("nbank_cf_lost") && jsonObject.getString("nbank_cf_lost") != null) {
				entity.setSlCellNbankCfLost(jsonObject.getString("nbank_cf_lost"));
			}
			if (jsonObject.containsKey("nbank_cf_refuse") && jsonObject.getString("nbank_cf_refuse") != null) {
				entity.setSlCellNbankCfRefuse(jsonObject.getString("nbank_cf_refuse"));
			}
			if (jsonObject.containsKey("nbank_other_bad") && jsonObject.getString("nbank_other_bad") != null) {
				entity.setSlCellNbankOtherBad(jsonObject.getString("nbank_other_bad"));
			}
			if (jsonObject.containsKey("nbank_other_overdue") && jsonObject.getString("nbank_other_overdue") != null) {
				entity.setSlCellNbankOtherOverdue(jsonObject.getString("nbank_other_overdue"));
			}
			if (jsonObject.containsKey("nbank_other_fraud") && jsonObject.getString("nbank_other_fraud") != null) {
				entity.setSlCellNbankOtherFraud(jsonObject.getString("nbank_other_fraud"));
			}
			if (jsonObject.containsKey("nbank_other_lost") && jsonObject.getString("nbank_other_lost") != null) {
				entity.setSlCellNbankOtherLost(jsonObject.getString("nbank_other_lost"));
			}
			if (jsonObject.containsKey("nbank_other_refuse") && jsonObject.getString("nbank_other_refuse") != null) {
				entity.setSlCellNbankOtherRefuse(jsonObject.getString("nbank_other_refuse"));
			}
		}
		return entity;
	}

	private BRZXSpecialListForId parseForId(JSONObject jsonObject) {
		BRZXSpecialListForId entity = null;
		if (jsonObject != null && !jsonObject.isEmpty()) {
			entity = new BRZXSpecialListForId();
			if (jsonObject.containsKey("abnormal") && jsonObject.getString("abnormal") != null) {
				entity.setSlIdAbnormal(jsonObject.getString("abnormal"));
			}
			if (jsonObject.containsKey("phone_overdue") && jsonObject.getString("phone_overdue") != null) {
				entity.setSlIdPhoneOverdue(jsonObject.getString("phone_overdue"));
			}
			if (jsonObject.containsKey("court_bad") && jsonObject.getString("court_bad") != null) {
				entity.setSlIdCourtBad(jsonObject.getString("court_bad"));
			}
			if (jsonObject.containsKey("court_executed") && jsonObject.getString("court_executed") != null) {
				entity.setSlIdCourtExecuted(jsonObject.getString("court_executed"));
			}
			if (jsonObject.containsKey("bank_bad") && jsonObject.getString("bank_bad") != null) {
				entity.setSlIdBankBad(jsonObject.getString("bank_bad"));
			}
			if (jsonObject.containsKey("bank_overdue") && jsonObject.getString("bank_overdue") != null) {
				entity.setSlIdBankOverdue(jsonObject.getString("bank_overdue"));
			}
			if (jsonObject.containsKey("bank_fraud") && jsonObject.getString("bank_fraud") != null) {
				entity.setSlIdBankFraud(jsonObject.getString("bank_fraud"));
			}
			if (jsonObject.containsKey("bank_lost") && jsonObject.getString("bank_lost") != null) {
				entity.setSlIdBankLost(jsonObject.getString("bank_lost"));
			}
			if (jsonObject.containsKey("bank_refuse") && jsonObject.getString("bank_refuse") != null) {
				entity.setSlIdBankRefuse(jsonObject.getString("bank_refuse"));
			}
			if (jsonObject.containsKey("p2p_bad") && jsonObject.getString("p2p_bad") != null) {
				entity.setSlIdP2pBad(jsonObject.getString("p2p_bad"));
			}
			if (jsonObject.containsKey("p2p_overdue") && jsonObject.getString("p2p_overdue") != null) {
				entity.setSlIdP2pOverdue(jsonObject.getString("p2p_overdue"));
			}
			if (jsonObject.containsKey("p2p_fraud") && jsonObject.getString("p2p_fraud") != null) {
				entity.setSlIdP2pFraud(jsonObject.getString("p2p_fraud"));
			}
			if (jsonObject.containsKey("p2p_lost") && jsonObject.getString("p2p_lost") != null) {
				entity.setSlIdP2pLost(jsonObject.getString("p2p_lost"));
			}
			if (jsonObject.containsKey("p2p_refuse") && jsonObject.getString("p2p_refuse") != null) {
				entity.setSlIdP2pRefuse(jsonObject.getString("p2p_refuse"));
			}
			if (jsonObject.containsKey("nbank_p2p_bad") && jsonObject.getString("nbank_p2p_bad") != null) {
				entity.setSlIdNbankP2pBad(jsonObject.getString("nbank_p2p_bad"));
			}
			if (jsonObject.containsKey("nbank_p2p_overdue") && jsonObject.getString("nbank_p2p_overdue") != null) {
				entity.setSlIdNbankP2pOverdue(jsonObject.getString("nbank_p2p_overdue"));
			}
			if (jsonObject.containsKey("nbank_p2p_fraud") && jsonObject.getString("nbank_p2p_fraud") != null) {
				entity.setSlIdNbankP2pFraud(jsonObject.getString("nbank_p2p_fraud"));
			}
			if (jsonObject.containsKey("nbank_p2p_lost") && jsonObject.getString("nbank_p2p_lost") != null) {
				entity.setSlIdNbankP2pLost(jsonObject.getString("nbank_p2p_lost"));
			}
			if (jsonObject.containsKey("nbank_p2p_refuse") && jsonObject.getString("nbank_p2p_refuse") != null) {
				entity.setSlIdNbankP2pRefuse(jsonObject.getString("nbank_p2p_refuse"));
			}
			if (jsonObject.containsKey("nbank_mc_bad") && jsonObject.getString("nbank_mc_bad") != null) {
				entity.setSlIdNbankMcBad(jsonObject.getString("nbank_mc_bad"));
			}
			if (jsonObject.containsKey("nbank_mc_overdue") && jsonObject.getString("nbank_mc_overdue") != null) {
				entity.setSlIdNbankMcOverdue(jsonObject.getString("nbank_mc_overdue"));
			}
			if (jsonObject.containsKey("nbank_mc_fraud") && jsonObject.getString("nbank_mc_fraud") != null) {
				entity.setSlIdNbankMcFraud(jsonObject.getString("nbank_mc_fraud"));
			}
			if (jsonObject.containsKey("nbank_mc_lost") && jsonObject.getString("nbank_mc_lost") != null) {
				entity.setSlIdNbankMcLost(jsonObject.getString("nbank_mc_lost"));
			}
			if (jsonObject.containsKey("nbank_mc_refuse") && jsonObject.getString("nbank_mc_refuse") != null) {
				entity.setSlIdNbankMcRefuse(jsonObject.getString("nbank_mc_refuse"));
			}
			if (jsonObject.containsKey("nbank_ca_bad") && jsonObject.getString("nbank_ca_bad") != null) {
				entity.setSlIdNbankCaBad(jsonObject.getString("nbank_ca_bad"));
			}
			if (jsonObject.containsKey("nbank_ca_overdue") && jsonObject.getString("nbank_ca_overdue") != null) {
				entity.setSlIdNbankCaOverdue(jsonObject.getString("nbank_ca_overdue"));
			}
			if (jsonObject.containsKey("nbank_ca_fraud") && jsonObject.getString("nbank_ca_fraud") != null) {
				entity.setSlIdNbankCaFraud(jsonObject.getString("nbank_ca_fraud"));
			}
			if (jsonObject.containsKey("nbank_ca_lost") && jsonObject.getString("nbank_ca_lost") != null) {
				entity.setSlIdNbankCaLost(jsonObject.getString("nbank_ca_lost"));
			}
			if (jsonObject.containsKey("nbank_ca_refuse") && jsonObject.getString("nbank_ca_refuse") != null) {
				entity.setSlIdNbankCaRefuse(jsonObject.getString("nbank_ca_refuse"));
			}
			if (jsonObject.containsKey("nbank_com_bad") && jsonObject.getString("nbank_com_bad") != null) {
				entity.setSlIdNbankComBad(jsonObject.getString("nbank_com_bad"));
			}
			if (jsonObject.containsKey("nbank_com_overdue") && jsonObject.getString("nbank_com_overdue") != null) {
				entity.setSlIdNbankComOverdue(jsonObject.getString("nbank_com_overdue"));
			}
			if (jsonObject.containsKey("nbank_com_fraud") && jsonObject.getString("nbank_com_fraud") != null) {
				entity.setSlIdNbankComFraud(jsonObject.getString("nbank_com_fraud"));
			}
			if (jsonObject.containsKey("nbank_com_lost") && jsonObject.getString("nbank_com_lost") != null) {
				entity.setSlIdNbankComLost(jsonObject.getString("nbank_com_lost"));
			}
			if (jsonObject.containsKey("nbank_com_refuse") && jsonObject.getString("nbank_com_refuse") != null) {
				entity.setSlIdNbankComRefuse(jsonObject.getString("nbank_com_refuse"));
			}
			if (jsonObject.containsKey("nbank_cf_bad") && jsonObject.getString("nbank_cf_bad") != null) {
				entity.setSlIdNbankCfBad(jsonObject.getString("nbank_cf_bad"));
			}
			if (jsonObject.containsKey("nbank_cf_overdue") && jsonObject.getString("nbank_cf_overdue") != null) {
				entity.setSlIdNbankCfOverdue(jsonObject.getString("nbank_cf_overdue"));
			}
			if (jsonObject.containsKey("nbank_cf_fraud") && jsonObject.getString("nbank_cf_fraud") != null) {
				entity.setSlIdNbankCfFraud(jsonObject.getString("nbank_cf_fraud"));
			}
			if (jsonObject.containsKey("nbank_cf_lost") && jsonObject.getString("nbank_cf_lost") != null) {
				entity.setSlIdNbankCfLost(jsonObject.getString("nbank_cf_lost"));
			}
			if (jsonObject.containsKey("nbank_cf_refuse") && jsonObject.getString("nbank_cf_refuse") != null) {
				entity.setSlIdNbankCfRefuse(jsonObject.getString("nbank_cf_refuse"));
			}
			if (jsonObject.containsKey("nbank_other_bad") && jsonObject.getString("nbank_other_bad") != null) {
				entity.setSlIdNbankOtherBad(jsonObject.getString("nbank_other_bad"));
			}
			if (jsonObject.containsKey("nbank_other_overdue") && jsonObject.getString("nbank_other_overdue") != null) {
				entity.setSlIdNbankOtherOverdue(jsonObject.getString("nbank_other_overdue"));
			}
			if (jsonObject.containsKey("nbank_other_fraud") && jsonObject.getString("nbank_other_fraud") != null) {
				entity.setSlIdNbankOtherFraud(jsonObject.getString("nbank_other_fraud"));
			}
			if (jsonObject.containsKey("nbank_other_lost") && jsonObject.getString("nbank_other_lost") != null) {
				entity.setSlIdNbankOtherLost(jsonObject.getString("nbank_other_lost"));
			}
			if (jsonObject.containsKey("nbank_other_refuse") && jsonObject.getString("nbank_other_refuse") != null) {
				entity.setSlIdNbankOtherRefuse(jsonObject.getString("nbank_other_refuse"));
			}
		}
		return entity;
	}

	private void parseHomeAddr(BRZXLocation location, JSONObject jsonObject) {
		if (jsonObject != null && !jsonObject.isEmpty()) {
			if (jsonObject.containsKey("addr11")) {
				String addr11 = "";
				if (jsonObject.getString("addr11") != null) {
					addr11 = jsonObject.getString("addr11");
				}
				if (addr11 != null && !"".equals(addr11)) {
					location.setLocationHomeAddr1(addr11);
					if (jsonObject.containsKey("addr12")) {
						String addr12 = "";
						if (jsonObject.getString("addr12") != null) {
							addr12 = jsonObject.getString("addr12");
						}
						if (addr12 != null && !"".equals(addr12)) {
							location.setLocationHomeAddr2(addr12);
						}
						addr12 = null;
					}
					if (jsonObject.containsKey("addr13")) {
						String addr13 = "";
						if (jsonObject.getString("addr13") != null) {
							addr13 = jsonObject.getString("addr13");
						}
						if (addr13 != null && !"".equals(addr13)) {
							location.setLocationHomeAddr3(addr13);
						}
						addr13 = null;
					}
					if (jsonObject.containsKey("addr14")) {
						String addr14 = "";
						if (jsonObject.getString("addr14") != null) {
							addr14 = jsonObject.getString("addr14");
						}
						if (addr14 != null && !"".equals(addr14)) {
							location.setLocationHomeAddr4(addr14);
						}
						addr14 = null;
					}
					if (jsonObject.containsKey("addr15")) {
						String addr15 = "";
						if (jsonObject.getString("addr15") != null) {
							addr15 = jsonObject.getString("addr15");
						}
						if (addr15 != null && !"".equals(addr15)) {
							location.setLocationHomeAddr5(addr15);
						}
						addr15 = null;
					}
					addr11 = null;
				}
			}
		}
	}

	private void parseBizAddr(BRZXLocation location, JSONObject jsonObject) {
		if (jsonObject != null && !jsonObject.isEmpty()) {
			if (jsonObject.containsKey("addr21")) {
				String addr21 = null;
				if (jsonObject.getString("addr21") != null) {
					addr21 = jsonObject.getString("addr21");
				}
				if (addr21 != null && !"".equals(addr21)) {
					location.setLocationBizAddr1(addr21);
					if (jsonObject.containsKey("addr22")) {
						String addr22 = null;
						if (jsonObject.getString("addr22") != null) {
							addr22 = jsonObject.getString("addr22");
						}
						if (addr22 != null && !"".equals(addr22)) {
							location.setLocationBizAddr2(addr22);
						}
						addr22 = null;
					}
					if (jsonObject.containsKey("addr23")) {
						String addr23 = null;
						if (jsonObject.getString("addr23") != null) {
							addr23 = jsonObject.getString("addr23");
						}
						if (addr23 != null && !"".equals(addr23)) {
							location.setLocationBizAddr3(addr23);
						}
						addr23 = null;
					}
					if (jsonObject.containsKey("addr24")) {
						String addr24 = "";
						if (jsonObject.getString("addr24") != null) {
							addr24 = jsonObject.getString("addr24");
						}
						if (addr24 != null && !"".equals(addr24)) {
							location.setLocationBizAddr4(addr24);
						}
						addr24 = null;
					}
					if (jsonObject.containsKey("addr25")) {
						String addr25 = "";
						if (jsonObject.getString("addr25") != null) {
							addr25 = jsonObject.getString("addr25");
						}
						if (addr25 != null && !"".equals(addr25)) {
							location.setLocationBizAddr5(addr25);
						}
						addr25 = null;
					}
					addr21 = null;
				}
			}
		}
	}

	private void parsePerAddr(BRZXLocation location, JSONObject jsonObject) {
		if (jsonObject != null && !jsonObject.isEmpty()) {
			if (jsonObject.containsKey("addr31")) {
				String addr31 = "";
				if (jsonObject.getString("addr31") != null) {
					addr31 = jsonObject.getString("addr31");

				}
				if (addr31 != null && !"".equals(addr31)) {
					location.setLocationPerAddr1(addr31);

					if (jsonObject.containsKey("addr32")) {
						String addr32 = null;
						if (jsonObject.getString("addr32") != null) {
							addr32 = jsonObject.getString("addr32");
						}
						if (addr32 != null && !"".equals(addr32)) {
							location.setLocationPerAddr2(addr32);
						}
						addr32 = null;
					}
					if (jsonObject.containsKey("addr33")) {
						String addr33 = null;
						if (jsonObject.getString("addr33") != null) {
							addr33 = jsonObject.getString("addr33");
						}
						if (addr33 != null && !"".equals(addr33)) {
							location.setLocationPerAddr3(addr33);
						}
						addr33 = null;
					}
					if (jsonObject.containsKey("addr34")) {
						String addr34 = jsonObject.getString("addr34");
						if (jsonObject.getString("addr34") != null) {
							addr34 = jsonObject.getString("addr34");
						}
						if (addr34 != null && !"".equals(addr34)) {
							location.setLocationPerAddr4(addr34);
						}
						addr34 = null;
					}
					if (jsonObject.containsKey("addr35")) {
						String addr35 = "";
						if (jsonObject.getString("addr35") != null) {
							addr35 = jsonObject.getString("addr35");
						}
						if (addr35 != null && !"".equals(addr35)) {
							location.setLocationPerAddr5(addr35);
						}
						addr35 = null;
					}
					addr31 = null;
				}
			}
		}
	}

	private void parseApplyAddr(BRZXLocation location, JSONObject jsonObject) {
		if (jsonObject != null && !jsonObject.isEmpty()) {
			if (jsonObject.containsKey("addr41")) {
				String addr41 = "";
				if (jsonObject.getString("addr41") != null) {
					addr41 = jsonObject.getString("addr41");
				}
				if (addr41 != null && !"".equals(addr41)) {
					location.setLocationApplyAddr1(addr41);
					if (jsonObject.containsKey("addr42")) {
						String addr42 = "";
						if (jsonObject.getString("addr42") != null) {
							addr42 = jsonObject.getString("addr42");
						}
						if (addr42 != null && !"".equals(addr42)) {
							location.setLocationApplyAddr2(addr42);
						}
						addr42 = null;
					}
					if (jsonObject.containsKey("addr43")) {
						String addr43 = "";
						if (jsonObject.getString("addr43") != null) {
							addr43 = jsonObject.getString("addr43");
						}
						if (addr43 != null && !"".equals(addr43)) {
							location.setLocationApplyAddr3(addr43);
						}
						addr43 = null;
					}
					if (jsonObject.containsKey("addr44")) {
						String addr44 = "";
						if (jsonObject.getString("addr44") != null) {
							addr44 = jsonObject.getString("addr44");
						}
						if (addr44 != null && !"".equals(addr44)) {
							location.setLocationApplyAddr4(addr44);
						}
						addr44 = null;
					}
					if (jsonObject.containsKey("addr45")) {
						String addr45 = "";
						if (jsonObject.getString("addr45") != null) {
							addr45 = jsonObject.getString("addr45");
						}
						if (addr45 != null && !"".equals(addr45)) {
							location.setLocationApplyAddr5(addr45);
						}
						addr45 = null;
					}
				}
				addr41 = null;
			}
		}
	}

	private void parseOthAddr(BRZXLocation location, JSONObject jsonObject) {
		if (jsonObject != null && !jsonObject.isEmpty()) {
			if (jsonObject.containsKey("addr51")) {
				String addr51 = "";
				if (jsonObject.getString("addr51") != null) {
					addr51 = jsonObject.getString("addr51");
				}
				if (addr51 != null && !"".equals(addr51)) {
					location.setLocationOthAddr1(addr51);
					if (jsonObject.containsKey("addr52")) {
						String addr52 = "";
						if (jsonObject.getString("addr52") != null) {
							addr52 = jsonObject.getString("addr52");
						}
						if (addr52 != null && !"".equals(addr52)) {
							location.setLocationOthAddr2(addr52);
						}
						addr52 = null;
					}
					if (jsonObject.containsKey("addr53")) {
						String addr53 = "";
						if (jsonObject.getString("addr53") != null) {
							addr53 = jsonObject.getString("addr53");
						}
						if (addr53 != null && !"".equals(addr53)) {
							location.setLocationOthAddr3(addr53);
						}
						addr53 = null;
					}
					if (jsonObject.containsKey("addr54")) {
						String addr54 = "";
						if (jsonObject.getString("addr54") != null) {
							addr54 = jsonObject.getString("addr54");
						}
						if (addr54 != null && !"".equals(addr54)) {
							location.setLocationOthAddr4(addr54);
						}
						addr54 = null;
					}
					if (jsonObject.containsKey("addr55")) {
						String addr55 = "";
						if (jsonObject.getString("addr55") != null) {
							addr55 = jsonObject.getString("addr55");
						}
						if (addr55 != null && !"".equals(addr55)) {
							location.setLocationOthAddr5(addr55);
						}
						addr55 = null;
					}
				}
				addr51 = null;
			}
		}
	}

}
