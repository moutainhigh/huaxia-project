package com.huaxia.opas.parser;

import com.huaxia.opas.domain.QHZXMsc8005;
import com.huaxia.opas.domain.QHZXMsc8007;
import com.huaxia.opas.domain.QHZXMsc8036;
import com.huaxia.opas.domain.QHZXMsc8037;
import com.huaxia.opas.domain.QHZXMsc8075;
import com.huaxia.opas.domain.QHZXMsc8107;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 前海征信报文解析器
 * 
 * @author zhiguo.li
 *
 */
public class QHZXMessageParser {

	/**
	 * 解析好信度数据信息报文
	 * 
	 * @param message
	 *            报文
	 * @return
	 * @throws Exception
	 */
	public QHZXMsc8005 parseMessageOfMsc8005(String message) throws Exception {
		if (message == null) {
			throw new IllegalArgumentException("前海征信(好信度数据信息)报文为空");
		}

		JSONObject json = JSONObject.fromObject(message);
		if (!json.isNullObject() && json.size() > 0) {
			QHZXMsc8005 msc8005 = new QHZXMsc8005();

			JSONObject headerObject = JSONObject.fromObject(json.get("header"));
			if (!headerObject.isNullObject() && headerObject.size() > 0) {
				String orgCode = headerObject.getString("orgCode");
				if (orgCode != null && !"".equals(orgCode)) {
					msc8005.setOrgCode(orgCode);
				}

				String chnlId = headerObject.getString("chnlId");
				if (chnlId != null && !"".equals(chnlId)) {
					msc8005.setChnlId(chnlId);
				}

				String transNo = headerObject.getString("transNo");
				if (transNo != null && !"".equals(transNo)) {
					msc8005.setTransNo(transNo);
				}

				String transDate = headerObject.getString("transDate");
				if (transDate != null && !"".equals(transDate)) {
					msc8005.setTransDate(transDate);
				}

				String authCode = headerObject.getString("authCode");
				if (authCode != null && !"".equals(authCode)) {
					msc8005.setAuthCode(authCode);
				}

				String authDate = headerObject.getString("authDate");
				if (authDate != null && !"".equals(authDate)) {
					msc8005.setAuthDate(authDate);
				}

				String rtCode = headerObject.getString("rtCode");
				if (rtCode != null && !"".equals(rtCode)) {
					msc8005.setRtCode(rtCode);
				}

				String rtMsg = headerObject.getString("rtMsg");
				if (rtMsg != null && !"".equals(rtMsg)) {
					msc8005.setRtMsg(rtMsg);
				}
			}

			JSONObject securityInfoObject = JSONObject.fromObject(json.get("securityInfo"));
			if (!securityInfoObject.isNullObject() && securityInfoObject.size() > 0) {
				String signatureValue = securityInfoObject.getString("signatureValue");
				if (signatureValue != null && !"".equals(signatureValue)) {
					msc8005.setSignatureValue(signatureValue);
				}
			}

			JSONObject busiDataObject = JSONObject.fromObject(json.get("busiData"));
			if (!busiDataObject.isNullObject() && busiDataObject.size() > 0) {
				String batchNo = busiDataObject.getString("batchNo");
				if (batchNo != null && !"".equals(batchNo)) {
					msc8005.setBatchNo(batchNo);
				}

				JSONArray recordsArray = (JSONArray) busiDataObject.get("records");
				if (recordsArray != null && !recordsArray.isEmpty()) {
					JSONObject recordObject = (JSONObject) recordsArray.get(0);
					String idNo = recordObject.getString("idNo");
					if (idNo != null && !"".equals(idNo)) {
						msc8005.setIdNo(idNo);
					}

					String idType = recordObject.getString("idType");
					if (idType != null && !"".equals(idType)) {
						msc8005.setIdType(idType);
					}

					String name = recordObject.getString("name");
					if (name != null && !"".equals(name)) {
						msc8005.setName(name);
					}

					String mobileNo = recordObject.getString("mobileNo");
					if (mobileNo != null && !"".equals(mobileNo) && !"NULL".equalsIgnoreCase(mobileNo)) {
						msc8005.setMobileNo(mobileNo);
					}

					String seqNo = recordObject.getString("seqNo");
					if (seqNo != null && !"".equals(seqNo)) {
						msc8005.setSeqNo(seqNo);
					}

					String sourceId = recordObject.getString("sourceId");
					if (sourceId != null && !"".equals(sourceId)) {
						msc8005.setSourceId(sourceId);
					}

					String credooScore = recordObject.getString("credooScore");
					if (credooScore != null && !"".equals(credooScore)) {
						msc8005.setCredooScore(credooScore);
					}

					String dataBuildTime = recordObject.getString("dataBuildTime");
					if (dataBuildTime != null && !"".equals(dataBuildTime)) {
						msc8005.setDataBuildTime(dataBuildTime);
					}

					String erCode = recordObject.getString("erCode");
					if (erCode != null && !"".equals(erCode)) {
						msc8005.setErCode(erCode);
					}

					String erMsg = recordObject.getString("erMsg");
					if (erMsg != null && !"".equals(erMsg)) {
						msc8005.setErMsg(erMsg);
					}
				}
			}

			return msc8005;
		}
		return null;
	}

	/**
	 * 解析地址通数据信息报文
	 * 
	 * @param message
	 *            报文
	 * @return
	 * @throws Exception
	 */
	public QHZXMsc8007 parseMessageOfMsc8007(String message) throws Exception {
		if (message == null) {
			throw new IllegalArgumentException("前海征信(地址通数据信息)报文为空");
		}

		JSONObject json = JSONObject.fromObject(message);
		if (!json.isNullObject() && json.size() > 0) {
			QHZXMsc8007 msc8007 = new QHZXMsc8007();

			JSONObject headerObject = JSONObject.fromObject(json.get("header"));
			if (!headerObject.isNullObject() && headerObject.size() > 0) {
				String orgCode = headerObject.getString("orgCode");
				if (orgCode != null && !"".equals(orgCode)) {
					msc8007.setOrgCode(orgCode);
				}

				String chnlId = headerObject.getString("chnlId");
				if (chnlId != null && !"".equals(chnlId)) {
					msc8007.setChnlId(chnlId);
				}

				String transNo = headerObject.getString("transNo");
				if (transNo != null && !"".equals(transNo)) {
					msc8007.setTransNo(transNo);
				}

				String transDate = headerObject.getString("transDate");
				if (transDate != null && !"".equals(transDate)) {
					msc8007.setTransDate(transDate);
				}

				String authCode = headerObject.getString("authCode");
				if (authCode != null && !"".equals(authCode)) {
					msc8007.setAuthCode(authCode);
				}

				String authDate = headerObject.getString("authDate");
				if (authDate != null && !"".equals(authDate)) {
					msc8007.setAuthDate(authDate);
				}

				String rtCode = headerObject.getString("rtCode");
				if (rtCode != null && !"".equals(rtCode)) {
					msc8007.setRtCode(rtCode);
				}

				String rtMsg = headerObject.getString("rtMsg");
				if (rtMsg != null && !"".equals(rtMsg)) {
					msc8007.setRtMsg(rtMsg);
				}
			}

			JSONObject securityInfoObject = JSONObject.fromObject(json.get("securityInfo"));
			if (!securityInfoObject.isNullObject() && securityInfoObject.size() > 0) {
				String signatureValue = securityInfoObject.getString("signatureValue");
				if (signatureValue != null && !"".equals(signatureValue)) {
					msc8007.setSignatureValue(signatureValue);
				}
			}

			JSONObject busiDataObject = JSONObject.fromObject(json.get("busiData"));
			if (!busiDataObject.isNullObject() && busiDataObject.size() > 0) {
				String batchNo = busiDataObject.getString("batchNo");
				if (batchNo != null && !"".equals(batchNo)) {
					msc8007.setBatchNo(batchNo);
				}

				JSONArray recordsArray = (JSONArray) busiDataObject.get("records");
				if (recordsArray != null && !recordsArray.isEmpty()) {
					JSONObject recordObject = (JSONObject) recordsArray.get(0);
					String idNo = recordObject.getString("idNo");
					if (idNo != null && !"".equals(idNo)) {
						msc8007.setIdNo(idNo);
					}

					String idType = recordObject.getString("idType");
					if (idType != null && !"".equals(idType)) {
						msc8007.setIdType(idType);
					}

					String name = recordObject.getString("name");
					if (name != null && !"".equals(name)) {
						msc8007.setName(name);
					}

					String seqNo = recordObject.getString("seqNo");
					if (seqNo != null && !"".equals(seqNo)) {
						msc8007.setSeqNo(seqNo);
					}

					String mobileNo = recordObject.getString("mobileNo");
					if (mobileNo != null && !"".equals(mobileNo) && !"NULL".equalsIgnoreCase(mobileNo)) {
						msc8007.setMobileNo(mobileNo);
					}

					String address = recordObject.getString("address");
					if (address != null && !"".equals(address) && !"NULL".equalsIgnoreCase(address)) {
						msc8007.setAddress(address);
					}

					String sourceId = recordObject.getString("sourceId");
					if (sourceId != null && !"".equals(sourceId)) {
						msc8007.setSourceId(sourceId);
					}

					String province = recordObject.getString("province");
					if (province != null && !"".equals(province)) {
						msc8007.setProvince(province);
					}

					String cityInfo = recordObject.getString("cityInfo");
					if (cityInfo != null && !"".equals(cityInfo)) {
						msc8007.setCityInfo(cityInfo);
					}

					String borough = recordObject.getString("borough");
					if (borough != null && !"".equals(borough)) {
						msc8007.setBorough(borough);
					}

					String fmtAddress = recordObject.getString("fmtAddress");
					if (fmtAddress != null && !"".equals(fmtAddress)) {
						msc8007.setFmtAddress(fmtAddress);
					}

					String longitude = recordObject.getString("longitude");
					if (longitude != null && !"".equals(longitude)) {
						msc8007.setLongitude(longitude);
					}

					String latitude = recordObject.getString("latitude");
					if (latitude != null && !"".equals(latitude)) {
						msc8007.setLatitude(latitude);
					}

					String buildingName = recordObject.getString("buildingName");
					if (buildingName != null && !"".equals(buildingName)) {
						msc8007.setBuildingName(buildingName);
					}

					String buildingAddress = recordObject.getString("buildingAddress");
					if (buildingAddress != null && !"".equals(buildingAddress)) {
						msc8007.setBuildingAddress(buildingAddress);
					}

					String houseArodAvgPrice = recordObject.getString("houseArodAvgPrice");
					if (houseArodAvgPrice != null && !"".equals(houseArodAvgPrice)) {
						msc8007.setHouseArodAvgPrice(houseArodAvgPrice);
					}

					String houseAvgPrice = recordObject.getString("houseAvgPrice");
					if (houseAvgPrice != null && !"".equals(houseAvgPrice)) {
						msc8007.setHouseAvgPrice(houseAvgPrice);
					}

					String state = recordObject.getString("state");
					if (state != null && !"".equals(state)) {
						msc8007.setState(state);
					}

					String buildingType = recordObject.getString("buildingType");
					if (buildingType != null && !"".equals(buildingType)) {
						msc8007.setBuildingType(buildingType);

						String dataBuildTime = recordObject.getString("dataBuildTime");
						if (dataBuildTime != null && !"".equals(dataBuildTime)) {
							msc8007.setDataBuildTime(dataBuildTime);
						}

						String isMatch = recordObject.getString("isMatch");
						if (isMatch != null && !"".equals(isMatch)) {
							msc8007.setIsMatch(isMatch);
						}

						String addressPorperty = recordObject.getString("addressPorperty");
						if (addressPorperty != null && !"".equals(addressPorperty)) {
							msc8007.setAddressPorperty(addressPorperty);
						}
					}
				}
			}
			return msc8007;
		}
		return null;
	}

	/**
	 * 解析风险度提示数据信息报文
	 * 
	 * @param message
	 *            报文
	 * @return
	 * @throws Exception
	 */
	public QHZXMsc8036 parseMessageOfMsc8036(String message) throws Exception {
		if (message == null) {
			throw new IllegalArgumentException("前海征信(风险度提示数据信息)报文为空");
		}

		JSONObject json = JSONObject.fromObject(message);
		if (!json.isNullObject() && json.size() > 0) {
			QHZXMsc8036 msc8036 = new QHZXMsc8036();

			JSONObject headerObject = JSONObject.fromObject(json.get("header"));
			if (!headerObject.isNullObject() && headerObject.size() > 0) {
				String orgCode = headerObject.getString("orgCode");
				if (orgCode != null && !"".equals(orgCode)) {
					msc8036.setOrgCode(orgCode);
				}

				String chnlId = headerObject.getString("chnlId");
				if (chnlId != null && !"".equals(chnlId)) {
					msc8036.setChnlId(chnlId);
				}

				String transNo = headerObject.getString("transNo");
				if (transNo != null && !"".equals(transNo)) {
					msc8036.setTransNo(transNo);
				}

				String transDate = headerObject.getString("transDate");
				if (transDate != null && !"".equals(transDate)) {
					msc8036.setTransDate(transDate);
				}

				String authCode = headerObject.getString("authCode");
				if (authCode != null && !"".equals(authCode)) {
					msc8036.setAuthCode(authCode);
				}

				String authDate = headerObject.getString("authDate");
				if (authDate != null && !"".equals(authDate)) {
					msc8036.setAuthDate(authDate);
				}

				String rtCode = headerObject.getString("rtCode");
				if (rtCode != null && !"".equals(rtCode)) {
					msc8036.setRtCode(rtCode);
				}

				String rtMsg = headerObject.getString("rtMsg");
				if (rtMsg != null && !"".equals(rtMsg)) {
					msc8036.setRtMsg(rtMsg);
				}
			}

			JSONObject securityInfoObject = JSONObject.fromObject(json.get("securityInfo"));
			if (!securityInfoObject.isNullObject() && securityInfoObject.size() > 0) {
				String signatureValue = securityInfoObject.getString("signatureValue");
				if (signatureValue != null && !"".equals(signatureValue)) {
					msc8036.setSignatureValue(signatureValue);
				}
			}

			JSONObject busiDataObject = JSONObject.fromObject(json.get("busiData"));
			if (!busiDataObject.isNullObject() && busiDataObject.size() > 0) {
				String batchNo = busiDataObject.getString("batchNo");
				if (batchNo != null && !"".equals(batchNo)) {
					msc8036.setBatchNo(batchNo);
				}

				JSONArray recordsArray = (JSONArray) busiDataObject.get("records");
				if (recordsArray != null && !recordsArray.isEmpty()) {
					JSONObject recordObject = (JSONObject) recordsArray.get(0);
					String idNo = recordObject.getString("idNo");
					if (idNo != null && !"".equals(idNo)) {
						msc8036.setIdNo(idNo);
					}

					String idType = recordObject.getString("idType");
					if (idType != null && !"".equals(idType)) {
						msc8036.setIdType(idType);
					}

					String name = recordObject.getString("name");
					if (name != null && !"".equals(name)) {
						msc8036.setName(name);
					}

					String seqNo = recordObject.getString("seqNo");
					if (seqNo != null && !"".equals(seqNo)) {
						msc8036.setSeqNo(seqNo);
					}

					String sourceId = recordObject.getString("sourceId");
					if (sourceId != null && !"".equals(sourceId)) {
						msc8036.setSourceId(sourceId);
					}

					String rskScore = recordObject.getString("rskScore");
					if (rskScore != null && !"".equals(rskScore)) {
						msc8036.setRskScore(rskScore);
					}

					String rskMark = recordObject.getString("rskMark");
					if (rskMark != null && !"".equals(rskMark)) {
						msc8036.setRskMark(rskMark);
					}

					String dataBuildTime = recordObject.getString("dataBuildTime");
					if (dataBuildTime != null && !"".equals(dataBuildTime)) {
						msc8036.setDataBuildTime(dataBuildTime);
					}

					String dataStatus = recordObject.getString("dataStatus");
					if (dataStatus != null && !"".equals(dataStatus)) {
						msc8036.setDataStatus(dataStatus);
					}

					String erCode = recordObject.getString("erCode");
					if (erCode != null && !"".equals(erCode)) {
						msc8036.setErCode(erCode);
					}

					String erMsg = recordObject.getString("erMsg");
					if (erMsg != null && !"".equals(erMsg)) {
						msc8036.setErMsg(erMsg);
					}
				}
			}

			return msc8036;
		}
		return null;
	}

	/**
	 * 解析常贷客数据信息报文
	 * 
	 * @param message
	 *            报文
	 * @return
	 * @throws Exception
	 */
	public QHZXMsc8037 parseMessageOfMsc8037(String message) throws Exception {
		if (message == null) {
			throw new IllegalArgumentException("前海征信(常贷客数据信息)报文为空");
		}

		JSONObject json = JSONObject.fromObject(message);
		if (!json.isNullObject() && json.size() > 0) {
			QHZXMsc8037 msc8037 = new QHZXMsc8037();

			JSONObject headerObject = JSONObject.fromObject(json.get("header"));
			if (!headerObject.isNullObject() && headerObject.size() > 0) {
				String orgCode = headerObject.getString("orgCode");
				if (orgCode != null && !"".equals(orgCode)) {
					msc8037.setOrgCode(orgCode);
				}

				String chnlId = headerObject.getString("chnlId");
				if (chnlId != null && !"".equals(chnlId)) {
					msc8037.setChnlId(chnlId);
				}

				String transNo = headerObject.getString("transNo");
				if (transNo != null && !"".equals(transNo)) {
					msc8037.setTransNo(transNo);
				}

				String transDate = headerObject.getString("transDate");
				if (transDate != null && !"".equals(transDate)) {
					msc8037.setTransDate(transDate);
				}

				String authCode = headerObject.getString("authCode");
				if (authCode != null && !"".equals(authCode)) {
					msc8037.setAuthCode(authCode);
				}

				String authDate = headerObject.getString("authDate");
				if (authDate != null && !"".equals(authDate)) {
					msc8037.setAuthDate(authDate);
				}

				String rtCode = headerObject.getString("rtCode");
				if (rtCode != null && !"".equals(rtCode)) {
					msc8037.setRtCode(rtCode);
				}

				String rtMsg = headerObject.getString("rtMsg");
				if (rtMsg != null && !"".equals(rtMsg)) {
					msc8037.setRtMsg(rtMsg);
				}
			}

			JSONObject securityInfoObject = JSONObject.fromObject(json.get("securityInfo"));
			if (!securityInfoObject.isNullObject() && securityInfoObject.size() > 0) {
				String signatureValue = securityInfoObject.getString("signatureValue");
				if (signatureValue != null && !"".equals(signatureValue)) {
					msc8037.setSignatureValue(signatureValue);
				}
			}

			JSONObject busiDataObject = JSONObject.fromObject(json.get("busiData"));
			if (!busiDataObject.isNullObject() && busiDataObject.size() > 0) {
				String batchNo = busiDataObject.getString("batchNo");
				if (batchNo != null && !"".equals(batchNo)) {
					msc8037.setBatchNo(batchNo);
				}

				JSONArray recordsArray = (JSONArray) busiDataObject.get("records");
				if (recordsArray != null && !recordsArray.isEmpty()) {
					JSONObject recordObject = (JSONObject) recordsArray.get(0);
					String idNo = recordObject.getString("idNo");
					if (idNo != null && !"".equals(idNo)) {
						msc8037.setIdNo(idNo);
					}

					String idType = recordObject.getString("idType");
					if (idType != null && !"".equals(idType)) {
						msc8037.setIdType(idType);
					}

					String name = recordObject.getString("name");
					if (name != null && !"".equals(name)) {
						msc8037.setName(name);
					}

					String seqNo = recordObject.getString("seqNo");
					if (seqNo != null && !"".equals(seqNo)) {
						msc8037.setSeqNo(seqNo);
					}

					String reasonCode = recordObject.getString("reasonCode");
					if (reasonCode != null && !"".equals(reasonCode)) {
						msc8037.setReasonCode(reasonCode);
					}

					String industry = recordObject.getString("industry");
					if (industry != null && !"".equals(industry)) {
						msc8037.setIndustry(industry);
					}

					String amount = recordObject.getString("amount");
					if (amount != null && !"".equals(amount)) {
						msc8037.setAmount(amount);
					}

					String bnkAmount = recordObject.getString("bnkAmount");
					if (bnkAmount != null && !"".equals(bnkAmount)) {
						msc8037.setBnkAmount(bnkAmount);
					}

					String cnssAmount = recordObject.getString("cnssAmount");
					if (cnssAmount != null && !"".equals(cnssAmount)) {
						msc8037.setCnssAmount(cnssAmount);
					}

					String p2pAmount = recordObject.getString("p2pAmount");
					if (p2pAmount != null && !"".equals(p2pAmount)) {
						msc8037.setP2pAmount(p2pAmount);
					}

					String queryAmt = recordObject.getString("queryAmt");
					if (queryAmt != null && !"".equals(queryAmt)) {
						msc8037.setQueryAmt(queryAmt);
					}

					String queryAmtM3 = recordObject.getString("queryAmtM3");
					if (queryAmtM3 != null && !"".equals(queryAmtM3)) {
						msc8037.setQueryAmtM3(queryAmtM3);
					}

					String queryAmtM6 = recordObject.getString("queryAmtM6");
					if (queryAmtM6 != null && !"".equals(queryAmtM6)) {
						msc8037.setQueryAmtM6(queryAmtM6);
					}

					String busiDate = recordObject.getString("busiDate");
					if (busiDate != null && !"".equals(busiDate)) {
						msc8037.setBusiDate(busiDate);
					}

					String erCode = recordObject.getString("erCode");
					if (erCode != null && !"".equals(erCode)) {
						msc8037.setErCode(erCode);
					}

					String erMsg = recordObject.getString("erMsg");
					if (erMsg != null && !"".equals(erMsg)) {
						msc8037.setErMsg(erMsg);
					}
				}
			}

			return msc8037;
		}
		return null;
	}

	/**
	 * 解析好信欺诈度数据信息报文
	 * 
	 * @param message
	 *            报文
	 * @return
	 * @throws Exception
	 */
	public QHZXMsc8075 parseMessageOfMsc8075(String message) throws Exception {
		if (message == null) {
			throw new IllegalArgumentException("前海征信(好信欺诈度数据信息)报文为空");
		}

		JSONObject json = JSONObject.fromObject(message);
		if (!json.isNullObject() && json.size() > 0) {
			QHZXMsc8075 msc8075 = new QHZXMsc8075();

			JSONObject headerObject = JSONObject.fromObject(json.get("header"));
			if (!headerObject.isNullObject() && headerObject.size() > 0) {
				String orgCode = headerObject.getString("orgCode");
				if (orgCode != null && !"".equals(orgCode)) {
					msc8075.setOrgCode(orgCode);
				}

				String chnlId = headerObject.getString("chnlId");
				if (chnlId != null && !"".equals(chnlId)) {
					msc8075.setChnlId(chnlId);
				}

				String transNo = headerObject.getString("transNo");
				if (transNo != null && !"".equals(transNo)) {
					msc8075.setTransNo(transNo);
				}

				String transDate = headerObject.getString("transDate");
				if (transDate != null && !"".equals(transDate)) {
					msc8075.setTransDate(transDate);
				}

				String authCode = headerObject.getString("authCode");
				if (authCode != null && !"".equals(authCode)) {
					msc8075.setAuthCode(authCode);
				}

				String authDate = headerObject.getString("authDate");
				if (authDate != null && !"".equals(authDate)) {
					msc8075.setAuthDate(authDate);
				}

				String rtCode = headerObject.getString("rtCode");
				if (rtCode != null && !"".equals(rtCode)) {
					msc8075.setRtCode(rtCode);
				}

				String rtMsg = headerObject.getString("rtMsg");
				if (rtMsg != null && !"".equals(rtMsg)) {
					msc8075.setRtMsg(rtMsg);
				}
			}

			JSONObject securityInfoObject = JSONObject.fromObject(json.get("securityInfo"));
			if (!securityInfoObject.isNullObject() && securityInfoObject.size() > 0) {
				String signatureValue = securityInfoObject.getString("signatureValue");
				if (signatureValue != null && !"".equals(signatureValue)) {
					msc8075.setSignatureValue(signatureValue);
				}
			}

			JSONObject busiDataObject = JSONObject.fromObject(json.get("busiData"));
			if (!busiDataObject.isNullObject() && busiDataObject.size() > 0) {
				String batchNo = busiDataObject.getString("batchNo");
				if (batchNo != null && !"".equals(batchNo)) {
					msc8075.setBatchNo(batchNo);
				}

				JSONArray recordsArray = (JSONArray) busiDataObject.get("records");
				if (recordsArray != null && !recordsArray.isEmpty()) {
					JSONObject recordObject = (JSONObject) recordsArray.get(0);
					if (recordObject.containsKey("mobileNo")) {
						String mobileNo = recordObject.getString("mobileNo");
						if (mobileNo != null && !"".equals(mobileNo)) {
							msc8075.setMobileNo(mobileNo);
						}
						mobileNo = null;
					}

					if (recordObject.containsKey("ip")) {
						String ip = recordObject.getString("ip");
						if (ip != null && !"".equals(ip)) {
							msc8075.setIp(ip);
						}
						ip = null;
					}

					if (recordObject.containsKey("seqNo")) {
						String seqNo = recordObject.getString("seqNo");
						if (seqNo != null && !"".equals(seqNo)) {
							msc8075.setSeqNo(seqNo);
						}
						seqNo = null;
					}

					if (recordObject.containsKey("isMachdForce")) {
						String isMachdForce = recordObject.getString("isMachdForce");
						if (isMachdForce != null && !"".equals(isMachdForce)) {
							msc8075.setIsMachdForce(isMachdForce);
						}
						isMachdForce = null;
					}

					if (recordObject.containsKey("isMachdDNS")) {
						String isMachdDNS = recordObject.getString("isMachdDNS");
						if (isMachdDNS != null && !"".equals(isMachdDNS)) {
							msc8075.setIsMachdDNS(isMachdDNS);
						}
						isMachdDNS = null;
					}

					if (recordObject.containsKey("isMachdMailServ")) {
						String isMachdMailServ = recordObject.getString("isMachdMailServ");
						if (isMachdMailServ != null && !"".equals(isMachdMailServ)) {
							msc8075.setIsMachdMailServ(isMachdMailServ);
						}
						isMachdMailServ = null;
					}

					if (recordObject.containsKey("isMachdSEO")) {
						String isMachdSEO = recordObject.getString("isMachdSEO");
						if (isMachdSEO != null && !"".equals(isMachdSEO)) {
							msc8075.setIsMachdSEO(isMachdSEO);
						}
						isMachdSEO = null;
					}

					if (recordObject.containsKey("isMachdOrg")) {
						String isMachdOrg = recordObject.getString("isMachdOrg");
						if (isMachdOrg != null && !"".equals(isMachdOrg)) {
							msc8075.setIsMachdOrg(isMachdOrg);
						}
						isMachdOrg = null;
					}

					if (recordObject.containsKey("isMachdCrawler")) {
						String isMachdCrawler = recordObject.getString("isMachdCrawler");
						if (isMachdCrawler != null && !"".equals(isMachdCrawler)) {
							msc8075.setIsMachdCrawler(isMachdCrawler);
						}
						isMachdCrawler = null;
					}

					if (recordObject.containsKey("isMachdProxy")) {
						String isMachdProxy = recordObject.getString("isMachdProxy");
						if (isMachdProxy != null && !"".equals(isMachdProxy)) {
							msc8075.setIsMachdProxy(isMachdProxy);
						}
						isMachdProxy = null;
					}

					if (recordObject.containsKey("isMachdBlacklist")) {
						String isMachdBlacklist = recordObject.getString("isMachdBlacklist");
						if (isMachdBlacklist != null && !"".equals(isMachdBlacklist)) {
							msc8075.setIsMachdBlacklist(isMachdBlacklist);
						}
						isMachdBlacklist = null;
					}

					if (recordObject.containsKey("isMachdWebServ")) {
						String isMachdWebServ = recordObject.getString("isMachdWebServ");
						if (isMachdWebServ != null && !"".equals(isMachdWebServ)) {
							msc8075.setIsMachdWebServ(isMachdWebServ);
						}
						isMachdWebServ = null;
					}

					if (recordObject.containsKey("isMachdVPN")) {
						String isMachdVPN = recordObject.getString("isMachdVPN");
						if (isMachdVPN != null && !"".equals(isMachdVPN)) {
							msc8075.setIsMachdVPN(isMachdVPN);
						}
						isMachdVPN = null;
					}

					if (recordObject.containsKey("rskScore")) {
						String rskScore = recordObject.getString("rskScore");
						if (rskScore != null && !"".equals(rskScore)) {
							msc8075.setRskScore(rskScore);
						}
						rskScore = null;
					}

					if (recordObject.containsKey("isMachdBlMakt")) {
						String isMachdBlMakt = recordObject.getString("isMachdBlMakt");
						if (isMachdBlMakt != null && !"".equals(isMachdBlMakt)) {
							msc8075.setIsMachdBlMakt(isMachdBlMakt);
						}
						isMachdBlMakt = null;
					}

					if (recordObject.containsKey("isMachCraCall")) {
						String isMachCraCall = recordObject.getString("isMachCraCall");
						if (isMachCraCall != null && !"".equals(isMachCraCall)) {
							msc8075.setIsMachCraCall(isMachCraCall);
						}
						isMachCraCall = null;
					}

					if (recordObject.containsKey("isMachFraud")) {
						String isMachFraud = recordObject.getString("isMachFraud");
						if (isMachFraud != null && !"".equals(isMachFraud)) {
							msc8075.setIsMachFraud(isMachFraud);
						}
						isMachFraud = null;
					}

					if (recordObject.containsKey("isMachEmpty")) {
						String isMachEmpty = recordObject.getString("isMachEmpty");
						if (isMachEmpty != null && !"".equals(isMachEmpty)) {
							msc8075.setIsMachEmpty(isMachEmpty);
						}
						isMachEmpty = null;
					}

					if (recordObject.containsKey("isMachYZmobile")) {
						String isMachYZmobile = recordObject.getString("isMachYZmobile");
						if (isMachYZmobile != null && !"".equals(isMachYZmobile)) {
							msc8075.setIsMachYZmobile(isMachYZmobile);
						}
						isMachYZmobile = null;
					}

					if (recordObject.containsKey("isMachSmallNo")) {
						String isMachSmallNo = recordObject.getString("isMachSmallNo");
						if (isMachSmallNo != null && !"".equals(isMachSmallNo)) {
							msc8075.setIsMachSmallNo(isMachSmallNo);
						}
						isMachSmallNo = null;
					}

					if (recordObject.containsKey("isMachSZNo")) {
						String isMachSZNo = recordObject.getString("isMachSZNo");
						if (isMachSZNo != null && !"".equals(isMachSZNo)) {
							msc8075.setIsMachSZNo(isMachSZNo);
						}
						isMachSZNo = null;
					}

					if (recordObject.containsKey("iUpdateDate")) {
						String iUpdateDate = recordObject.getString("iUpdateDate");
						if (iUpdateDate != null && !"".equals(iUpdateDate)) {
							msc8075.setiUpdateDate(iUpdateDate);
						}
						iUpdateDate = null;
					}

					if (recordObject.containsKey("mUpdateDate")) {
						String mUpdateDate = recordObject.getString("mUpdateDate");
						if (mUpdateDate != null && !"".equals(mUpdateDate)) {
							msc8075.setmUpdateDate(mUpdateDate);
						}
						mUpdateDate = null;
					}

					if (recordObject.containsKey("iRskDesc")) {
						String iRskDesc = recordObject.getString("iRskDesc");
						if (iRskDesc != null && !"".equals(iRskDesc)) {
							msc8075.setiRskDesc(iRskDesc);
						}
						iRskDesc = null;
					}

					if (recordObject.containsKey("mRskDesc")) {
						String mRskDesc = recordObject.getString("mRskDesc");
						if (mRskDesc != null && !"".equals(mRskDesc)) {
							msc8075.setmRskDesc(mRskDesc);
						}
						mRskDesc = null;
					}

					if (recordObject.containsKey("erCode")) {
						String erCode = recordObject.getString("erCode");
						if (erCode != null && !"".equals(erCode)) {
							msc8075.setErCode(erCode);
						}
						erCode = null;
					}

					if (recordObject.containsKey("erMsg")) {
						String erMsg = recordObject.getString("erMsg");
						if (erMsg != null && !"".equals(erMsg)) {
							msc8075.setErMsg(erMsg);
						}
						erMsg = null;
					}
				}
			}

			return msc8075;
		}
		return null;
	}

	/**
	 * 解析好信一鉴通数据信息报文
	 * 
	 * @param message
	 *            报文
	 * @return
	 * @throws Exception
	 */
	public QHZXMsc8107 parseMessageOfMsc8107(String message) throws Exception {
		if (message == null) {
			throw new IllegalArgumentException("前海征信(好信一鉴通数据信息)报文为空");
		}

		JSONObject json = JSONObject.fromObject(message);
		if (!json.isNullObject() && json.size() > 0) {
			QHZXMsc8107 msc8107 = new QHZXMsc8107();

			JSONObject headerObject = JSONObject.fromObject(json.get("header"));
			if (!headerObject.isNullObject() && headerObject.size() > 0) {
				String orgCode = headerObject.getString("orgCode");
				if (orgCode != null && !"".equals(orgCode)) {
					msc8107.setOrgCode(orgCode);
				}

				String chnlId = headerObject.getString("chnlId");
				if (chnlId != null && !"".equals(chnlId)) {
					msc8107.setChnlId(chnlId);
				}

				String transNo = headerObject.getString("transNo");
				if (transNo != null && !"".equals(transNo)) {
					msc8107.setTransNo(transNo);
				}

				String transDate = headerObject.getString("transDate");
				if (transDate != null && !"".equals(transDate)) {
					msc8107.setTransDate(transDate);
				}

				String authCode = headerObject.getString("authCode");
				if (authCode != null && !"".equals(authCode)) {
					msc8107.setAuthCode(authCode);
				}

				String authDate = headerObject.getString("authDate");
				if (authDate != null && !"".equals(authDate)) {
					msc8107.setAuthDate(authDate);
				}

				String rtCode = headerObject.getString("rtCode");
				if (rtCode != null && !"".equals(rtCode)) {
					msc8107.setRtCode(rtCode);
				}

				String rtMsg = headerObject.getString("rtMsg");
				if (rtMsg != null && !"".equals(rtMsg)) {
					msc8107.setRtMsg(rtMsg);
				}
			}

			JSONObject busiDataObject = JSONObject.fromObject(json.get("busiData"));
			if (!busiDataObject.isNullObject() && busiDataObject.size() > 0) {
				String batchNo = busiDataObject.getString("batchNo");
				if (batchNo != null && !"".equals(batchNo)) {
					msc8107.setBatchNo(batchNo);
				}

				JSONArray recordsArray = (JSONArray) busiDataObject.get("records");
				if (recordsArray != null && !recordsArray.isEmpty()) {
					JSONObject recordObject = (JSONObject) recordsArray.get(0);
					if (!recordObject.isNullObject()) {
						String idNo = recordObject.getString("idNo");
						if (idNo != null && !"".equals(idNo)) {
							msc8107.setIdNo(idNo);
						}

						String idType = recordObject.getString("idType");
						if (idType != null && !"".equals(idType)) {
							msc8107.setIdType(idType);
						}

						String name = recordObject.getString("name");
						if (name != null && !"".equals(name) && !"NULL".equalsIgnoreCase(name)) {
							msc8107.setName(name);
						}

						String mobileNo = recordObject.getString("mobileNo");
						if (mobileNo != null && !"".equals(mobileNo) && !"NULL".equalsIgnoreCase(mobileNo)) {
							msc8107.setMobileNo(mobileNo);
						}

						String seqNo = recordObject.getString("seqNo");
						if (seqNo != null && !"".equals(seqNo)) {
							msc8107.setSeqNo(seqNo);
						}

						String isRealIdentity = recordObject.getString("isRealIdentity");
						if (isRealIdentity != null && !"".equals(isRealIdentity)) {
							msc8107.setIsRealIdentity(isRealIdentity);
						}

						String isValidAddress = recordObject.getString("isValidAddress");
						if (isValidAddress != null && !"".equals(isValidAddress)) {
							msc8107.setIsValidAddress(isValidAddress);
						}

						String addressType = recordObject.getString("addressType");
						if (addressType != null && !"".equals(addressType)) {
							msc8107.setAddressType(addressType);
						}

						String isRealCompany = recordObject.getString("isRealCompany");
						if (isRealCompany != null && !"".equals(isRealCompany)) {
							msc8107.setIsRealCompany(isRealCompany);
						}

						String companySimDeg = recordObject.getString("companySimDeg");
						if (companySimDeg != null && !"".equals(companySimDeg)) {
							msc8107.setCompanySimDeg(companySimDeg);
						}

						String appear1ThDate = recordObject.getString("appear1ThDate");
						if (appear1ThDate != null && !"".equals(appear1ThDate)) {
							msc8107.setAppear1ThDate(appear1ThDate);
						}

						String appearLastDate = recordObject.getString("appearLastDate");
						if (appearLastDate != null && !"".equals(appearLastDate)) {
							msc8107.setAppearLastDate(appearLastDate);
						}

						String isOwnerMobile = recordObject.getString("isOwnerMobile");
						if (isOwnerMobile != null && !"".equals(isOwnerMobile)) {
							msc8107.setIsOwnerMobile(isOwnerMobile);
						}

						String ownerMobileStatus = recordObject.getString("ownerMobileStatus");
						if (ownerMobileStatus != null && !"".equals(ownerMobileStatus)) {
							msc8107.setOwnerMobileStatus(ownerMobileStatus);
						}

						String useTimeScore = recordObject.getString("useTimeScore");
						if (useTimeScore != null && !"".equals(useTimeScore)) {
							msc8107.setUseTimeScore(useTimeScore);
						}

						String isExistRel = recordObject.getString("isExistRel");
						if (isExistRel != null && !"".equals(isExistRel)) {
							msc8107.setIsExistRel(isExistRel);
						}

						String relLevel = recordObject.getString("relLevel");
						if (relLevel != null && !"".equals(relLevel)) {
							msc8107.setRelLevel(relLevel);
						}

						String carChkResult = recordObject.getString("carChkResult");
						if (carChkResult != null && !"".equals(carChkResult)) {
							msc8107.setCarChkResult(carChkResult);
						}

						String isAsyned = recordObject.getString("isAsyned");
						if (isAsyned != null && !"".equals(isAsyned)) {
							msc8107.setIsAsyned(isAsyned);
						}

						String carTypeInc = recordObject.getString("carTypeInc");
						if (carTypeInc != null && !"".equals(carTypeInc)) {
							msc8107.setCarTypeInc(carTypeInc);
						}

						String carFactoryInc = recordObject.getString("carFactoryInc");
						if (carFactoryInc != null && !"".equals(carFactoryInc)) {
							msc8107.setCarFactoryInc(carFactoryInc);
						}

						String carPrice = recordObject.getString("carPrice");
						if (carPrice != null && !"".equals(carPrice)) {
							msc8107.setCarPrice(carPrice);
						}

						String drvStatusQryId = recordObject.getString("drvStatusQryId");
						if (drvStatusQryId != null && !"".equals(drvStatusQryId)) {
							msc8107.setDrvStatusQryId(drvStatusQryId);
						}

						String houseChkResult = recordObject.getString("houseChkResult");
						if (houseChkResult != null && !"".equals(houseChkResult)) {
							msc8107.setHouseChkResult(houseChkResult);
						}

						String houseDataDate = recordObject.getString("houseDataDate");
						if (houseDataDate != null && !"".equals(houseDataDate)) {
							msc8107.setHouseDataDate(houseDataDate);
						}

						String photoCmpResult = recordObject.getString("photoCmpResult");
						if (photoCmpResult != null && !"".equals(photoCmpResult)) {
							msc8107.setPhotoCmpResult(photoCmpResult);
						}

						String photoSimDeg = recordObject.getString("photoSimDeg");
						if (photoSimDeg != null && !"".equals(photoSimDeg)) {
							msc8107.setPhotoSimDeg(photoSimDeg);
						}

						String isHighestEduBkg = recordObject.getString("isHighestEduBkg");
						if (isHighestEduBkg != null && !"".equals(isHighestEduBkg)) {
							msc8107.setIsHighestEduBkg(isHighestEduBkg);
						}

						String dataDate = recordObject.getString("dataDate");
						if (dataDate != null && !"".equals(dataDate)) {
							msc8107.setDataDate(dataDate);
						}

						String graduateSchool = recordObject.getString("graduateSchool");
						if (graduateSchool != null && !"".equals(graduateSchool)) {
							msc8107.setGraduateSchool(graduateSchool);
						}

						String graduateMajor = recordObject.getString("graduateMajor");
						if (graduateMajor != null && !"".equals(graduateMajor)) {
							msc8107.setGraduateMajor(graduateMajor);
						}

						String graduateYear = recordObject.getString("graduateYear");
						if (graduateYear != null && !"".equals(graduateYear)) {
							msc8107.setGraduateYear(graduateYear);
						}

						if (recordObject.containsKey("isOwnerMobile2")) {
							String isOwnerMobile2 = recordObject.getString("isOwnerMobile2");
							if (isOwnerMobile2 != null && !"".equals(isOwnerMobile2)) {
								msc8107.setIsOwnerMobile2(isOwnerMobile2);
							}
						}

						if (recordObject.containsKey("ownerMobileStatus2")) {
							String ownerMobileStatus2 = recordObject.getString("ownerMobileStatus2");
							if (ownerMobileStatus2 != null && !"".equals(ownerMobileStatus2)) {
								msc8107.setOwnerMobileStatus2(ownerMobileStatus2);
							}
						}

						if (recordObject.containsKey("useTimeScore2")) {
							String useTimeScore2 = recordObject.getString("useTimeScore2");
							if (useTimeScore2 != null && !"".equals(useTimeScore2)) {
								msc8107.setUseTimeScore2(useTimeScore2);
							}
						}

						String errorInfo = recordObject.getString("errorInfo");
						if (errorInfo != null && !"".equals(errorInfo)) {
							msc8107.setErrorInfo(errorInfo);
						}
					}
				}
			}

			return msc8107;
		}
		return null;
	}

}
