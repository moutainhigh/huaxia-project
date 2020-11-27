package com.huaxia.opas.cache.service.riskcheck.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.common.CacheConsts;
import com.huaxia.opas.cache.service.LueneService;
import com.huaxia.opas.cache.service.riskcheck.RiskFieldMachService;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;
import com.huaxia.opas.domain.riskcheck.RiskCheck;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

/**
 * 申请信息逻辑检查
 * 
 * @author jiangming.yang
 *
 */
public class Step2_Field_Opas_Biz_LogicalCheck implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "lueneServiceImplTest")
	private LueneService lueneServiceN;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;


	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 单位名称 LOGICAL
		String compName = "";
		// 证件签发日期
		Date idNumberIssuingDate = null;
		// 证件有效期
		Date idNumberInDate = null;
		// 出生日期
		Date birthDate = null;
		if ("1".equals(appType)) {// 主卡
			compName = this.trimToEmpty(apply_Info1.getC1Coname());
			idNumberIssuingDate = apply_Info1.getC2Iddt1();
			idNumberInDate = apply_Info1.getC5Idte1();
			birthDate = apply_Info1.getC1Birth();
		}
		if ("2".equals(appType)) {// 附卡
			compName = this.trimToEmpty(apply_Info2.getC5Comnm2s());
			idNumberIssuingDate = apply_Info2.getC2Iddt2();
			idNumberInDate = apply_Info2.getC5Idte2();
			birthDate = apply_Info2.getC2Birth();
		}
		if ("3".equals(appType)) {// 主卡
			compName = this.trimToEmpty(apply_Info1.getC1Coname());
			idNumberIssuingDate = apply_Info1.getC2Iddt1();
			idNumberInDate = apply_Info1.getC5Idte1();
			birthDate = apply_Info1.getC1Birth();
		}
		params.put("compName", compName);
		params.put("idNumberIssuingDate", idNumberIssuingDate);
		params.put("idNumberInDate", idNumberInDate);
		params.put("birthDate", birthDate);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		//String compName = (String) initParams.get("compName");
		Date idNumberIssuingDate = (Date) initParams.get("idNumberIssuingDate");
		Date idNumberInDate = (Date) initParams.get("idNumberInDate");
		Date birthDate = (Date) initParams.get("birthDate");
		// 如申请表单位名称为华夏银行，但单位地址填写为某住宅小区几号楼几单元几号
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		RiskCheckResult logicalComName = new RiskCheckResult("LOGICAL", "OPAS_BIZ_INP_APPL_C1",
				CacheConsts.RISK_TYPE_TEAM);
		logicalComName.setApplyType(appType);

		StringBuffer ret = new StringBuffer();
		// 查学历
		String educationBackgroud = riskCheckService.Query_OPAS_BIZ_EDUCATION_BACKGROUD(appId);
		
		if (apply_Info1 != null) {
			// 年龄
			Float age = null;
			if ("01".equals(StringUtil.trimToEmpty(apply_Info1.getC1Idtype()))) { // 证件为身份证
				age = riskCheckService.queryAgeByDateOfIssue(appId);
			} else {
				if (birthDate != null) {
					age = riskCheckService.queryYearBetweenIssuingDate(appId);
				}
			}
			if (idNumberIssuingDate != null && idNumberInDate != null) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				// 发证年龄与身份证有效期限不符
				HashMap<Object, Object> map = new HashMap<>();
				map.put("idNumberIssuingDate", idNumberIssuingDate);
				map.put("idNumberInDate", idNumberInDate);
				// 证件有效期
				Float cardISDate = riskCheckService.Query_OPAS_IDCRAD_ISDate(map);
				// 记录结果
				String formatInDate = sf.format(idNumberInDate);
				// 11、 证件有效期逻辑异常2999-12-31  
				//   id有效期29991231   为长期  
				if ("01".equals(StringUtil.trimToEmpty(apply_Info1.getC1Idtype()))){
					if ("2999-12-31".equals(formatInDate)) {// 如果有效期为长期 判断年龄
						if (age != null && age < 46) {// 不合逻辑
							ret.append("证件有效期异常|");
						}
					} else {
						if (age != null && age < 16 && 5 != cardISDate) {
							ret.append("证件有效期异常|");
						}
	
						if (age != null && age >= 16 && age < 26 && 10 != cardISDate) {
							ret.append("证件有效期异常|");
						}
	
						if (age != null && age >= 26 && age < 46 && 20 != cardISDate) {
							ret.append("证件有效期异常|");
						}
						if (age != null && age >= 46 && cardISDate > 0) {
							ret.append("证件有效期异常|");
						}
					}
				}
				if ("07".equals(StringUtil.trimToEmpty(apply_Info1.getC1Idtype()))){
					
					if (age != null && age < 18 && 5 != cardISDate) {
						ret.append("请排查外国人永久居留身份证有效期|");
					}
					if (age != null && age >= 18 && 10 != cardISDate) {
						ret.append("请排查外国人永久居留身份证有效期|");
					}
				}
			} else {
				ret.append("证件有效期异常[证件签发日期/证件有效期/出生日期]为空!|");
			}
			//当证件类型选择“外国人永久居留身份证”时，以发证日当天的年龄计算：①小于18周岁，有效期5年②大于等于18周岁，有效期10年。
			//若不符验证规则，“申请信息逻辑检查”高亮显示“是”，点击“是”弹窗提示“请排查外国人永久居留身份证有效期（除旧版）”。
			
			// 1.检查申请人年龄、学历、职位是否相符（包括但不限于以下）：
			Float realAge = null;
			if ("01".equals(StringUtil.trimToEmpty(apply_Info1.getC1Idtype()))) { // 证件为身份证
				realAge = riskCheckService.Query_OPAS_BIZ_IsInDate(appId);

			} else {
				if(birthDate != null ){
					realAge = riskCheckService.queryAgeBetweenIssuingDate(birthDate);
				}
			}
			if (realAge != null) {
				//String c1Educls = apply_Info1.getC1Educls();
				if (realAge >= 18 && realAge < 20) {
					String zhiwu = "法人:总经理:总监:经理";
					int jycd = 3;
					ret.append(checkAgeAndEdu(apply_Info1, educationBackgroud, zhiwu, jycd));
				}
				if (realAge >= 20 && realAge < 22) {
					String zhiwu = "法人:总经理:总监";
					int jycd = 4;
					ret.append(checkAgeAndEdu(apply_Info1, educationBackgroud, zhiwu, jycd));
				}
				if (realAge >= 22 && realAge < 24) {
					String zhiwu = "法人:总经理";
					int jycd = 5;
					ret.append(checkAgeAndEdu(apply_Info1, educationBackgroud, zhiwu, jycd));
				}
			} else {
				ret.append("申请人年龄与学历和职位不相符!|");
			}
			// 2.检查申请人单位地址与住宅地址是否相同
			// 家庭地址
			StringBuffer sbaddrHm = new StringBuffer();
			sbaddrHm.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd1()));
			sbaddrHm.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd2()));
			sbaddrHm.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd3()));
			sbaddrHm.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd4()));
			// 公司地址C1_COADD1
			StringBuffer sbaddrCom = new StringBuffer();
			sbaddrCom.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd1()));
			sbaddrCom.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd2()));
			sbaddrCom.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd3()));
			sbaddrCom.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd4()));
			if (lueneService.getMachResult(sbaddrHm.toString(), sbaddrCom.toString(), "")) {
				ret.append("申请人单位地址与家庭地址地址相同,存在异常!|");
			}
			// 4、检查申请人单位（家庭）地址是否为信箱地址、酒店房间：
			if (sbaddrHm.toString().contains("信箱") || sbaddrHm.toString().contains("酒店")
					|| sbaddrCom.toString().contains("信箱") || sbaddrCom.toString().contains("酒店")) {
				ret.append("单位（家庭）地址命中关键字“信箱”或“酒店”!|");
			}
			// 5.检查申请人单位电话与住宅电话是否相同：
			String hmtel = this.trimToEmpty(apply_Info1.getC1Hmare()) + this.trimToEmpty(apply_Info1.getC1Hmtel());
			String c1Cotel = this.trimToEmpty(apply_Info1.getC1Cotel());
			// 查询地区对应的区号
			String c1Hm = this.trimToEmpty(apply_Info1.getC1Hmare());
			if (!"".equals(c1Cotel) && !"".equals(this.trimToEmpty(apply_Info1.getC1Hmtel())) && (!"000".equals(c1Hm) || !"0000".equals(c1Hm)
					|| !"00".equals(c1Hm) || !"0".equals(c1Hm) || !"".equals(c1Hm)) && lueneService.getMachResult(hmtel, c1Cotel, "1")) {
				ret.append("“单位电话”与“住宅电话”相同!|");
			}
			// 6.检查申请人家庭（单位）地址与电话所属地区是否相符：
			String hm = sbaddrHm.toString();
			String cm = sbaddrCom.toString();
			String judgeAddress = judgeAddress(hm);
			String judgeAddress2 = judgeAddress(cm);
			//c1Cotel
			if(c1Hm.length()<3 && (!"0".equals(c1Hm) && !"00".equals(c1Hm) && !"".equals(c1Hm)) || (c1Cotel.length()<11 && !"".equals(c1Cotel))){
				//ret.append("表地址所属区域实际电话区号与表电话区号不一致!|");
				ret.append("地址与电话存在逻辑疑点(目前无法排除该区号下的三级城市)|");
			}else{
				List<String> query_TelNo = riskCheckService.Query_TelNo(c1Hm);
				boolean telHmBoo = false;
				for (int i = 0; i < query_TelNo.size(); i++) {
					if(lueneService.getMachResultTel(query_TelNo.get(i), sbaddrHm.toString(), "")){
						telHmBoo = true;
						break;
					}
				}
				if(telHmBoo || "000".equals(c1Hm) || "0000".equals(c1Hm) || "00".equals(c1Hm) || "0".equals(c1Hm) || "".equals(c1Hm)){
					if(!"".equals(c1Cotel)){
						String cotel_1 = c1Cotel.substring(0, 3);
						String cotel_2 = c1Cotel.substring(0, 4);
						List<String> query_TelNo2 = riskCheckService.Query_TelNo(cotel_1);
						telHmBoo = false;
						for (int i = 0; i < query_TelNo2.size(); i++) {
							if(lueneService.getMachResultTel(query_TelNo2.get(i), sbaddrCom.toString(), "")){
								telHmBoo = true;
								break;
							}
						}
						if(!telHmBoo){
							List<String> query_TelNo3 = riskCheckService.Query_TelNo(cotel_2);
							telHmBoo = false;
							for (int i = 0; i < query_TelNo3.size(); i++) {
								if(lueneService.getMachResultTel(query_TelNo3.get(i), sbaddrCom.toString(), "")){
									telHmBoo = true;
									break;
								}
							}
							if(!telHmBoo){
								//ret.append("表地址所属区域实际电话区号与表电话区号不一致!|");
								ret.append("地址与电话存在逻辑疑点(目前无法排除该区号下的三级城市)|");
							}
						}
					}
				}else{
					//ret.append("表地址所属区域实际电话区号与表电话区号不一致!|");
					ret.append("地址与电话存在逻辑疑点(目前无法排除该区号下的三级城市)|");
				}
			}
			// 3.检查申请人单位地址所属城市与住宅地址所属城市是否一致
			if (!"".equals(judgeAddress) && !"".equals(judgeAddress2) && !judgeAddress.equals(judgeAddress2)) {
				ret.append("单位地址所属城市与住宅地址所属城市不一致!|");
			}
			// 7.检查申请人单位电话是否为移动电话（电信行业除外） 逻辑疑点条件：“单位电话”==“移动电话”
			String c1Coname = this.trimToEmpty(apply_Info1.getC1Coname());
			if (c1Coname.contains("移动") || c1Coname.contains("联通") || c1Coname.contains("电信")
					|| c1Coname.contains("广电")) {

			} else {
				String c1Cote2 = c1Cotel.length() > 11 ? c1Cotel.substring(c1Cotel.length() - 11, c1Cotel.length()) : c1Cotel;
				if (c1Cote2.length() >= 11 && this.checkMobile(c1Cote2)) {
					ret.append("“单位电话”为“移动电话”!|");
				}
			}
			// 8.检查公司电话与联系人电话是否相同
			String c1Retel = this.trimToEmpty(apply_Info1.getC1Retelar()) + this.trimToEmpty(apply_Info1.getC1Retel());
			String C1XTELAR1 = this.trimToEmpty(apply_Info1.getC1Xtelar1()) + this.trimToEmpty(apply_Info1.getC1Xtel1());
			String c1Remobil2 = this.trimToEmpty(apply_Info1.getC1Remobil());
			String c1Xmobil12 = this.trimToEmpty(apply_Info1.getC1Xmobil1());
//			if((!"0".equals(c1Retel) &&!"00".equals(c1Retel)&&!"000".equals(c1Retel)&&!"0000".equals(c1Retel) &&!"".equals(c1Retel)) &&
//					(!"0".equals(C1XTELAR1) &&!"00".equals(C1XTELAR1)&&!"000".equals(C1XTELAR1)&&!"0000".equals(C1XTELAR1)&&!"".equals(C1XTELAR1))){
//				if (lueneService.getMachResult(c1Cotel, c1Retel, "1") || lueneService.getMachResult(c1Cotel, C1XTELAR1, "1")
//						|| lueneService.getMachResult(c1Cotel, c1Remobil2, "1")
//						|| lueneService.getMachResult(c1Cotel, c1Xmobil12, "1")) {
//					ret.append("“单位电话”与“联系人电话”一致!|");
//				}
//			}
			if((!"".equals(c1Retel) && !"0".equals(c1Retel) &&!"00".equals(c1Retel)&&!"000".equals(c1Retel)&&!"0000".equals(c1Retel)) || 
					(!"0".equals(C1XTELAR1) &&!"00".equals(C1XTELAR1)&&!"000".equals(C1XTELAR1)&&!"0000".equals(C1XTELAR1)&&!"".equals(C1XTELAR1))
					||!"".equals(c1Remobil2) || !"".equals(c1Remobil2) ){
				if (!"".equals(c1Cotel) && (lueneService.getMachResult(c1Cotel, c1Retel, "1") || lueneService.getMachResult(c1Cotel, C1XTELAR1, "1")
						|| lueneService.getMachResult(c1Cotel, c1Remobil2, "1")
						|| lueneService.getMachResult(c1Cotel, c1Xmobil12, "1"))) {
					ret.append("“单位电话”与“联系人电话”一致!|");
				}
			}
			// 9.检查申请人手机与联系人手机是否相同
			String c1Mobile = this.trimToEmpty(apply_Info1.getC1Mobile());
			String c1Remobil = this.trimToEmpty(apply_Info1.getC1Remobil());
			String c1Xmobil1 = this.trimToEmpty(apply_Info1.getC1Xmobil1());
			c1Mobile = c1Mobile.length() > 11 ? c1Mobile.substring(c1Mobile.length() - 11, c1Mobile.length())
					: c1Mobile;
			c1Remobil = c1Remobil.length() > 11 ? c1Remobil.substring(c1Remobil.length() - 11, c1Remobil.length())
					: c1Remobil;
			c1Xmobil1 = c1Xmobil1.length() > 11 ? c1Xmobil1.substring(c1Xmobil1.length() - 11, c1Xmobil1.length())
					: c1Xmobil1;
			if (!"".equals(c1Mobile) && (!"".equals(c1Remobil) || !"".equals(c1Xmobil1)) && (c1Mobile.equals(c1Remobil) || c1Mobile.equals(c1Xmobil1))) {
				ret.append("“申请人手机号”与“联系人手机号相同”!|");
			}
			// 10.检查同单位同批次多人进件，共用一部单位电话
			/*List<RiskCheck_Apply_C1> conames = riskCheckService.Query_Batch_Bin_Ifo(apply_Info1);
			if (conames != null && conames.size() > 0) {
				for (RiskCheck_Apply_C1 apply : conames) {
					String appId2 = this.trimToEmpty(apply.getAppId());
					if (appId.length() > 15 && appId2.length() > 15
							&& !appId.substring(0, 15).equals(appId2.substring(0, 15))
							&& lueneService.getMachResult(apply.getC1Coname(), apply_Info1.getC1Coname(), "")) {
						ret.append("（同单位同批次多人进件）单位电话相同!|");
						break;
					}
				}
			}*/
			//11.申请件直系亲属联系人手机与其他联系人手机校验是否相同
			//直系亲属手机C1Remobil,其他联系人手机 c1Xmobil1
			if (!"".equals(c1Remobil) && (!"".equals(c1Xmobil1)) && (c1Remobil.equals(c1Xmobil1))) {
				ret.append("“直系联系人手机与其他联系人手机相同”|");
			}
			//12.其他联系人姓名和电话不为空校验（固定电话和手机号任一不为空）
			String C1Xname1 = this.trimToEmpty(apply_Info1.getC1Xname1());
			//电话号码去掉区号校验  --王伟涛
			String C1XTEL1 = this.trimToEmpty(apply_Info1.getC1Xtel1());
			if (("".equals(C1Xname1))||("".endsWith(c1Xmobil1))&&("".equals(C1XTEL1))) {
				ret.append("“其他联系人姓名或电话为空”|");
			}
			//13.宁德地区申请人
			// 算法：申请人身份证号3522或3509开头；或单位地址/住宅地址含“宁德”；或单电/宅电区号为0593/593
			// 家庭地址sbaddrHm 公司地址sbaddrCom c1_idnbr
			String c1Idnbr =this.trimToEmpty(apply_Info1.getC1Idnbr());
			int company = -1, address = -1;
			company = sbaddrHm.indexOf("宁德");
			address = sbaddrCom.indexOf("宁德");
			if(c1Idnbr!=null){
				if((c1Idnbr!=null && (c1Idnbr.indexOf("3522")==0||c1Idnbr.indexOf("3509")==0||
						company > -1|| address > -1))
					||(apply_Info1.getC1Cotel()!=null && (apply_Info1.getC1Cotel().indexOf("0593")==0||apply_Info1.getC1Cotel().indexOf("593")==0))
					||(apply_Info1.getC1Hmare()!=null && (apply_Info1.getC1Hmare().indexOf("0593")==0||apply_Info1.getC1Hmare().indexOf("593")==0))){
					ret.append("“宁德地区申请人”|");
				}
			}
			//14.工作年限异常，关注人行非申请单位在职情况 c1Coname
			//对于非校园卡产品，申请信息逻辑检查新增规则：工作年限异常排查。
			//申请表“单位名称”与人行匹配结果为“否”时，比较：①“人行报告查询时间”减“人行最新一条职业信息的信息更新日期”与
			//②申请表填写的“现职年限”。若②＞①，则“申请信息逻辑检查”命中“是”，点击“是”，弹窗显示“工作年限异常，关注人行非申请单位在职情况”
			// 根据appId获得数据源匹配信息app_Prod
			if (!("0111").equals(apply_Info1.getAppProd())) {
				Map<String, String> jsonMap = getJsonMap(appId);
				String pbocCompany = jsonMap.get("OPAS_PBOC_PROFESSION_INFO-COMPANY");
				Float wordYear = null;
				if (null ==pbocCompany) {
					int C1Coyr = apply_Info1.getC1Coyr();
					//“人行报告查询时间”减“人行最新一条职业信息的信息更新日期”与
					wordYear = riskCheckService.selectWorkYear(appId);
					if (wordYear != null) {
						if (C1Coyr>wordYear){
							ret.append("“工作年限异常，关注人行非申请单位在职情况”!|");
						}
					}
				}
			}
			
			
		}
		if (!"".equals(ret.toString())) {
			logicalComName.setPriKeyValue(ret.toString());
			logicalComName.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
		}
		listriskcheck.add(logicalComName);

		return listriskcheck;
	}

	/**
	 * @param ret
	 * @param hm
	 */
	private String judgeAddress(String hm) {
		String diqu = "";
		if (hm.indexOf("省") != -1) { // 是
			if (hm.indexOf("市") != -1 && hm.indexOf("省") + 1 < hm.indexOf("市")) { // 是
				diqu = hm.substring(hm.indexOf("省") + 1, hm.indexOf("市"));
			}
		} else if (hm.indexOf("区") != -1 && hm.indexOf("区") < hm.indexOf("市")) { // 是
			diqu = hm.substring(hm.indexOf("区") + 1, hm.indexOf("市"));
		} else if (hm.indexOf("市") != -1) { // 是
			diqu = hm.substring(0, hm.indexOf("市"));
		}

		return diqu;
	}

	/**
	 * 
	 * @param apply_Info1
	 * @param educationBackgroud
	 * @param zhiwu
	 * @param jycd
	 * @return
	 * @throws NumberFormatException
	 */
	private StringBuffer checkAgeAndEdu(RiskCheck_Apply_C1 apply_Info1, String educationBackgroud, String zhiwu,
			int jycd) throws NumberFormatException {
		StringBuffer ret = new StringBuffer();
		String parseEdu = parseEdu(apply_Info1.getC1Educls());
		String c1Coduty = apply_Info1.getC1Coduty();
		boolean Flag = true;
		String[] split = zhiwu.split(":");
		for (int i = 0; i < split.length; i++) {
			if (this.trimToEmpty(c1Coduty).contains(split[i]) && Flag) {
				ret.append("申请人年龄与学历和职位不相符!|");
				Flag = false;
			}
		}
		if (Flag) { // 教育程度
			if (!parseEdu.contains(this.trimToEmpty(educationBackgroud))
					|| "".equals(this.trimToEmpty(educationBackgroud))) {
				if (!"".equals(this.trimToEmpty(apply_Info1.getC1Educls()))) {
					int parseInt = Integer.parseInt(apply_Info1.getC1Educls());
					if (parseInt >= jycd) {
						ret.append("申请人年龄与学历和职位不相符!|");
					}

				}
			}
		}
		return ret;
	}

	/**
	 * @param c1Educls
	 *            转换学历
	 */
	private String parseEdu(String c1Educls) {
		String[] edus = { "1:初中及以下", "2:高中及中专", "3:专科/专升本/专科(高职)", "4:本科", "5:硕士研究生/博士研究生" };
		for (int i = 0; i < edus.length; i++) {
			String edu = edus[i];
			String[] split = edu.split(":");
			if (split[0].equals(c1Educls)) {
				c1Educls = split[1];
				return c1Educls;
			}
		}
		return this.trimToEmpty(c1Educls);
	}

	/**
	 * 去掉两端空格，如果传入参数是null，返回是""（空字符串）
	 * 
	 * @param str
	 * @return
	 */
	public String trimToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}
	
	/**
	 * 正则判断手机号
	 * @param str
	 * @return
	 */
	private boolean checkMobile(String str){
		String regEx = "^((1))\\d{10}$";
		Pattern p = Pattern.compile(regEx);
		boolean matches = Pattern.matches(regEx, str);
		return matches;
	}
	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {

	}
	
	//获取到json结果  
	public Map<String, String> getJsonMap(String appId){
		KeyfiledMarchinfo info = riskCheckService.selectKeyfiledMarchinfoByAppId(appId);// 名单库匹配
		Map<String, String> jsonMap = new HashMap<String, String>();
		if (null!=info) {
			// 获取Json
			String marchResultJson = info.getMarchResult();
			// 解析Json串
			JSONArray jarray = JSONArray.fromObject(marchResultJson);
			List<RiskCheck> listrisk = (List<RiskCheck>) JSONArray
					.toCollection(jarray, RiskCheck.class);
			for (RiskCheck riskCheck : listrisk) {
				if ("1".equals(riskCheck.getRiskResult())) {
					// 拼接map中的key规则为表明加字段名
					String key = riskCheck.getTableName() + "-"
							+ riskCheck.getFieldKey();
					jsonMap.put(key, riskCheck.getPriKeyValue());
				}
			}
		}
		return jsonMap;
	}

}
