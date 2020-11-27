package com.huaxia.opas.cache.common;

import java.util.HashMap;
import java.util.Map;

import com.huateng.neofp.core.CoreException;

public class AntRiskResult {
	/**
	 * @param riskFactorCode
	 * @param infor :id-身份证;name-姓名;phone-电话;address-地址;
	 * 1-多个用户使用;2-一个用户使用;3-近期不活跃;4-近期较活跃;5-未经认证;6-经过认证;7-不匹配;8-存在欺诈记录;
	 * 9-无有效记录;10-不是常用;
	 * L-低风险;M-中风险;H-高风险;N-无风险;U-未知风险;
	 * @return
	 * @throws CoreException 
	 */
	public static Map<String,String> getRiskResult(String infor,String riskFactorCode) throws CoreException{
		Map<String,String> map = new HashMap<String,String>();
		String riskLevel = null;
		String riskResult = null;
		switch(infor){
		case "id":
			if("CERTNO_Match_Sharing_Bad".equals(riskFactorCode)||"CERTNO_Match_Trust_Self_Sharing_Bad".equals(riskFactorCode)){
				riskResult = "1";
				riskLevel = "M";
			}else if("CERTNO_Match_Sharing_Good".equals(riskFactorCode)||"CERTNO_Match_Trust_Self_Sharing_Good".equals(riskFactorCode)){
				riskResult = "2";
				riskLevel = "N";
			}else if("CERTNO_Match_Recency_Bad".equals(riskFactorCode)||"CERTNO_Match_Trust_Self_Recency_Bad".equals(riskFactorCode)){
				riskResult = "3";
				riskLevel = "L";
			}else if("CERTNO_Match_Recency_Good".equals(riskFactorCode)||"CERTNO_Match_Trust_Self_Recency_Good".equals(riskFactorCode)){
				riskResult = "4";
				riskLevel = "N";
			}else if("CERTNO_Match_Reliability_Bad".equals(riskFactorCode)||"CERTNO_Match_Trust_Self_Reliability_Bad".equals(riskFactorCode)){
				riskResult = "5";
				riskLevel = "M";
			}else if("CERTNO_Match_Reliability_Good".equals(riskFactorCode)||"CERTNO_Match_Trust_Self_Reliability_Good".equals(riskFactorCode)){
				riskResult = "6";
				riskLevel = "N";
			}else if("CERTNO_Match_Trust_Other_Sharing_Good".equals(riskFactorCode)||"CERTNO_Match_Trust_Other_Sharing_Bad".equals(riskFactorCode)
					||"CERTNO_Match_Trust_Other_Recency_Good".equals(riskFactorCode)||"CERTNO_Match_Trust_Other_Recency_Bad".equals(riskFactorCode)
					||"CERTNO_Match_Trust_Other_Reliability_Bad".equals(riskFactorCode)||"CERTNO_Match_Trust_Other_Reliability_Good".equals(riskFactorCode)
					||"CERTNO_Mismatch".equals(riskFactorCode)){
				riskResult = "7";
				riskLevel = "H";
			}else if("CERTNO_History_NegativeList".equals(riskFactorCode)){
				riskResult = "8";
				riskLevel = "H";
			}else if("CERTNO_Missing".equals(riskFactorCode)){
				riskResult = "9";
				riskLevel = "U";
			}else{
				riskResult = "-1";
				riskLevel = "U";
			}
			map.put("id_riskLevel", riskLevel);
			map.put("id_riskResult", riskResult);
			break;
		case "name":
			if("NAME_Match_Sharing_Bad".equals(riskFactorCode)){
				riskResult = "1";
				riskLevel = "M";
			}else if("NAME_Match_Sharing_Good".equals(riskFactorCode)){
				riskResult = "2";
				riskLevel = "N";
			}else if("NAME_Match_Recency_Bad".equals(riskFactorCode)){
				riskResult = "3";
				riskLevel = "M";
			}else if("NAME_Match_Recency_Good".equals(riskFactorCode)){
				riskResult = "4";
				riskLevel = "N";
			}else if("NAME_Match_Reliability_Bad".equals(riskFactorCode)){
				riskResult = "5";
				riskLevel = "M";
			}else if("NAME_Match_Reliability_Good".equals(riskFactorCode)){
				riskResult = "6";
				riskLevel = "N";
			}else if("NAME_Mismatch".equals(riskFactorCode)){
				riskResult = "7";
				riskLevel = "H";
			}else{
				riskResult = "-1";
				riskLevel = "U";
			}
			map.put("name_riskLevel", riskLevel);
			map.put("name_riskResult", riskResult);
			break;
		case "phone":
			if("PHONE_Match_Sharing_Bad".equals(riskFactorCode)||"PHONE_Match_Trust_Self_Sharing_Bad".equals(riskFactorCode)){
				riskResult = "1";
				riskLevel = "M";
			}else if("PHONE_Match_Sharing_Good".equals(riskFactorCode)||"PHONE_Match_Trust_Self_Sharing_Good".equals(riskFactorCode)){
				riskResult = "2";
				riskLevel = "N";
			}else if("PHONE_Match_Recency_Bad".equals(riskFactorCode)||"PHONE_Match_Trust_Self_Recency_Bad".equals(riskFactorCode)){
				riskResult = "3";
				riskLevel = "L";
			}else if("PHONE_Match_Recency_Good".equals(riskFactorCode)||"PHONE_Match_Trust_Self_Recency_Good".equals(riskFactorCode)){
				riskResult = "4";
				riskLevel = "N";
			}else if("PHONE_Match_Reliability_Bad".equals(riskFactorCode)||"PHONE_Match_Trust_Self_Reliability_Bad".equals(riskFactorCode)){
				riskResult = "5";
				riskLevel = "M";
			}else if("PHONE_Match_Reliability_Good".equals(riskFactorCode)||"PHONE_Match_Trust_Self_Reliability_Good".equals(riskFactorCode)){
				riskResult = "6";
				riskLevel = "N";
			}else if("PHONE_Match_Trust_Other".equals(riskFactorCode)){
				riskResult = "10";
				riskLevel = "L";
			}else if("PHONE_Mismatch".equals(riskFactorCode)){
				riskResult = "7";
				riskLevel = "H";
			}else if("PHONE_History_NegativeList".equals(riskFactorCode)){
				riskResult = "8";
				riskLevel = "H";
			}else if("PHONE_Missing".equals(riskFactorCode)){
				riskResult = "9";
				riskLevel = "U";
			}else{
				riskResult = "-1";
				riskLevel = "U";
			}
			map.put("phone_riskLevel", riskLevel);
			map.put("phone_riskResult", riskResult);
			break;
		case "address":
			if("ADDR_Match_Recency_Bad".equals(riskFactorCode)||"ADDR_Match_Trust_Self_Recency_Bad".equals(riskFactorCode)){
				riskResult = "3";
				riskLevel = "L";
			}else if("ADDR_Match_Recency_Good".equals(riskFactorCode)||"PHONE__Match_Trust_Self_Recency_Good".equals(riskFactorCode)){
				riskResult = "4";
				riskLevel = "N";
			}else if("ADDR_Match_Trust_Other".equals(riskFactorCode)){
				riskResult = "10";
				riskLevel = "L";
			}else if("ADDR_Mismatch".equals(riskFactorCode)){
				riskResult = "7";
				riskLevel = "H";
			}else{
				riskResult = "-1";
				riskLevel = "U";
			}
			map.put("address_riskLevel", riskLevel);
			map.put("address_riskResult", riskResult);
			break;
		case "email":
			if("EMAIL_History_NegativeList".equals(riskFactorCode)){
				riskResult = "8";
				riskLevel = "H";
			}else{
				riskResult = "-1";
				riskLevel = "U";
			}
			map.put("email_riskLevel", riskLevel);
			map.put("email_riskResult", riskResult);
			break;
		default:
			throw new CoreException("目前不提供该信息的校验!"+infor);
		}
		return map;
	}
}
