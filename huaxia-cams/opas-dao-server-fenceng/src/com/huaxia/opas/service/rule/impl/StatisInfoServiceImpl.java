package com.huaxia.opas.service.rule.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.rule.Opasbizinpapplc1Dao;
import com.huaxia.opas.dao.rule.OpasrevieentrycompareinfoDao;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.service.rule.StatisInfoService;

public class StatisInfoServiceImpl implements StatisInfoService, Serializable {
	// 中文
	private static final String CHINESE = "^[\u4e00-\u9fa5]{0,}$";
	// 不能有"x,*,?" 0816 from wjf 排除中文?
	private static final String NOSPECIALCHAR = "[^/*Xx？/?]{1,}";
	// 纯英文
	private static final String ENGLISH = "^(?:(?i)[a-z][A-Z]+)$";
	// 纯数字
	private static final String NUMBER = "^[0-9]*$";
	// 手机号码纯数字11位
	private static final String NUMBER11 = "^[0-9]{11}$";
	// 电话号码纯数字>=7位
	private static final String NUMBER7 = "^[0-9]{7}[0-9]*$";
	// 电话号码纯数字>=10位
	private static final String NUMBER10 = "^[0-9]{10}[0-9]*$";
	// 身份证纯数字可以有X
	private static final String NUMBERANDX = "^\\d{15}$|(^\\d{17}([0-9]|X)$)";
	@Resource(name = "opasbizinpapplc1Dao")
	private Opasbizinpapplc1Dao opasbizinpapplc1Dao;
	@Resource(name = "opasrevieentrycompareinfoDao")
	private OpasrevieentrycompareinfoDao opasrevieentrycompareinfoDaox;

	/**
	 * 计算当前件是否为设定值的倍数
	 */
	@Override
	public boolean getInsideAppNum(Opasbizinpapplc1 on) throws CoreException {
		int appNum = 0;
		boolean flag = false;
		if (on.getC4Apbatch() != null && on.getC4Apbatch().length() >= 2 && !"".equals(on.getC4Apbatch().trim())) {
			String c4Apbatch = on.getC4Apbatch();
			if ("DG".equals(c4Apbatch.substring(0, 2)) || c4Apbatch.length() < 2) {
				on.setC1Cobuscd("1");
			} else {
				on.setC1Cobuscd(null);
			}
		}
		List<Opasbizinpapplc1> opasbizinpapplc1s = null;
		if (on.getC1Coname() != null && !"".equals(on.getC1Coname())) {
			opasbizinpapplc1s = opasbizinpapplc1Dao.queryAppCount(on);
		}
		if (opasbizinpapplc1s != null) {
			for (Opasbizinpapplc1 opasbizinpapplc1 : opasbizinpapplc1s) {
				if (opasbizinpapplc1.getAppId().equals(on.getAppId())) {
					appNum = opasbizinpapplc1.getRowNum();
					break;
				}
			}
		}
		int sampleNum = 5;
		try {
			sampleNum = Integer.parseInt(opasbizinpapplc1Dao.querySampleNum());
		} catch (Exception e) {
			sampleNum = 5;
		}
		if (appNum != 0 && appNum % sampleNum == 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 判断申请件信息的完整性
	 */
	@Override
	public String getFullInfo(Opasbizinpapplc1 on) throws CoreException {

		if (on == null) {
			throw new CoreException("数据异常——主卡件信息为空");
		}
		// String C2_SIGNCK = on.getC2Signck();//申请人签名
		// String STATE_MATTER = null;//申明事项
		// String MCARD_SIGN = null;//主卡人签名
		// String ACARD_SIGN = null;//附卡人签名
		// if(C2_SIGNCK!=null&&C2_SIGNCK.length()>0){
		// C2_SIGNCK.substring(0, 1);
		// C2_SIGNCK.substring(1, 2);
		// C2_SIGNCK.substring(2, 3);
		// }
		// 对于单个字段f-完整，n-不完整
		List<String> list = new ArrayList<String>();
		// 1为独立主卡，2为主附同申请，3为独立附卡 old from huang
		// 1为主附同申请,2为独立附卡,3为独立主卡
		// if("1".equals(on.getAppFlag())||"3".equals(on.getAppFlag())){
		String chineseName = on.getC1Cname();// 中文姓名
		if (nullOrSpace(chineseName) && patteRegex(chineseName, CHINESE)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String englishName = on.getC1Ename();// 英文姓名
		if (nullOrSpace(englishName) && patteRegex(englishName.replace(" ", ""), ENGLISH)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String sex = on.getC1Gender();// 性别
		if (nullOrSpace(sex)) {
			list.add("f");
		} else {
			list.add("n");
		}
		Date birthDate = on.getC1Birth();// 出生日期
		if (birthDate != null) {
			if (nullOrSpace(dateToString(birthDate)) && patteRegex(dateToString(birthDate), NUMBER)) {
				list.add("f");
			} else {
				list.add("n");
			}
		} else {
			list.add("n");
		}
		String nation = on.getC1Nation();// 国家地区
		if (nullOrSpace(nation)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String idType = on.getC1Idtype();// 证件类别
		if (nullOrSpace(idType)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String idNbr = on.getC1Idnbr();// 证件号码
		if (nullOrSpace(idNbr) && patteRegex(idNbr, NUMBERANDX)) {
			list.add("f");
		} else {
			list.add("n");
		}
		Date c2Ddt1 = on.getC2Iddt1();// 证件签发日期
		if (c2Ddt1 != null) {
			if (nullOrSpace(dateToString(c2Ddt1)) && patteRegex(dateToString(c2Ddt1), NUMBER)) {
				list.add("f");
			} else {
				list.add("n");
			}
		} else {
			list.add("n");
		}
		Date c5Idte1 = on.getC5Idte1();// 证件有效期
		if (c5Idte1 != null) {
			if (nullOrSpace(dateToString(c5Idte1)) && patteRegex(dateToString(c5Idte1), NUMBER)) {
				list.add("f");
			} else {
				list.add("n");
			}
		} else {
			list.add("n");
		}
		String c1Marst = on.getC1Marst();// 婚姻状况
		if (nullOrSpace(c1Marst)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String c1Educls = on.getC1Educls();// 教育程度
		if (nullOrSpace(c1Educls)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String c1Mobile = on.getC1Mobile();// 手机号码
		if (nullOrSpace(c1Mobile) && patteRegex(c1Mobile, NUMBER11)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String c1Email = on.getC1Email();// 主卡申请人Email地址
		if (nullOrSpace(c1Email)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String C1_HMADD1 = on.getC1Hmadd1();// 家庭地址区段1
		if (nullOrSpace(C1_HMADD1) && patteRegex(C1_HMADD1, NOSPECIALCHAR)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String C1_HMADD2 = on.getC1Hmadd2();// 家庭地址区段2
		if (nullOrSpace(C1_HMADD2) && patteRegex(C1_HMADD2, NOSPECIALCHAR)) {
			list.add("f");
		} else {
			list.add("n");
		}
		// 20170801修改
		/*
		 * String C1_HMADD3 = on.getC1Hmadd3();//家庭地址区段3
		 * if(nullOrSpace(C1_HMADD3)&&patteRegex(C1_HMADD3, NOSPECIALCHAR)){
		 * list.add("f"); }else{ list.add("n"); } String C1_HMADD4 =
		 * on.getC1Hmadd4();//家庭地址区段4
		 * if(nullOrSpace(C1_HMADD4)&&patteRegex(C1_HMADD4, NOSPECIALCHAR)){
		 * list.add("f"); }else{ list.add("n"); }
		 */
		String C1_HMTEL = on.getC1Hmtel();// 家庭电话
		if (nullOrSpace(C1_HMTEL) && (patteRegex(C1_HMTEL, NUMBER7) || "空".equals(C1_HMTEL) || "无".equals(C1_HMTEL))) {
			list.add("f");
		} else {
			list.add("n");
		}
		String C1_HMST = on.getC1Hmst();// 住宅状况
		if (nullOrSpace(C1_HMST)) {
			list.add("f");
		} else {
			list.add("n");
		}
		// 20170801 需要车辆信息
		// 自购车辆四个有一个即为
		String C1_CARBRND = on.getC1Carbrnd();// 自购车辆品牌
		Date C1_CARDATE = on.getC1Cardate();// 自购车辆购买时间
		String C1_CARNBR = on.getC1Carnbr();// 自购车辆车牌号
		String C1_CARST = on.getC1Carst();// 自购车辆情况

		String C1_CONAME = on.getC1Coname();// 现单位全称
		if (nullOrSpace(C1_CONAME) && patteRegex(C1_CONAME, NOSPECIALCHAR)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String C1_COADD1 = on.getC1Coadd1();// 公司地址1
		if (nullOrSpace(C1_COADD1) && patteRegex(C1_COADD1, NOSPECIALCHAR)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String C1_COADD2 = on.getC1Coadd2();// 公司地址2
		if (nullOrSpace(C1_COADD2) && patteRegex(C1_COADD2, NOSPECIALCHAR)) {
			list.add("f");
		} else {
			list.add("n");
		}
		/*
		 * String C1_COADD3 = on.getC1Coadd3();
		 * if(nullOrSpace(C1_COADD3)&&patteRegex(C1_COADD3, NOSPECIALCHAR)){
		 * list.add("f"); }else{ list.add("n"); } String C1_COADD4 =
		 * on.getC1Coadd3(); if(nullOrSpace(C1_COADD4)&&patteRegex(C1_COADD4,
		 * NOSPECIALCHAR)){ list.add("f"); }else{ list.add("n"); }
		 */
		String C1_COTEL = on.getC1Cotel();// 公司电话
		if (nullOrSpace(C1_COTEL) && (patteRegex(C1_COTEL, NUMBER10))) {
			list.add("f");
		} else {
			list.add("n");
		}
		// 7/24 from wjf
		Short C1_COYR = on.getC1Coyr();// 现职年限
		String c1Coyr = C1_COYR == null ? "" : C1_COYR.toString();
		if (nullOrSpace(c1Coyr)) {
			list.add("f");
		} else {
			list.add("n");
		}
		BigDecimal C1_EARN = on.getC1Earn();// 税前年收入
		if (C1_EARN != null) {
			if (nullOrSpace(C1_EARN.toString())) {
				list.add("f");
			} else {
				list.add("n");
			}
		} else {
			list.add("n");
		}
		String C1_RENAME = on.getC1Rename();// 直系亲属姓名
		if (nullOrSpace(C1_RENAME) && patteRegex(C1_RENAME, CHINESE)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String C1_RETELAR = on.getC1Retelar();// 直系亲属电话区号
		String C1_RETEL = on.getC1Retel();// 直系亲属电话
		String C1_REMOBIL = on.getC1Remobil();// 直系亲属手机
		if (nullOrSpace(C1_RETEL) && patteRegex(C1_RETEL, NUMBER7)) {
			list.add("f");
		} else if (nullOrSpace(C1_REMOBIL) && patteRegex(C1_REMOBIL, NUMBER11)) {
			list.add("f");
		} else {
			list.add("n");
		}
		String C1_RESHIP = on.getC1Reship();// 直系亲属关系
		if (nullOrSpace(C1_RESHIP)) {
			list.add("f");
		} else {
			list.add("n");
		}
		// 联系人1和联系人2任取其一
		String C1_XNAME1 = on.getC1Xname1();// 联系人1姓名
		String C1_XTELAR1 = on.getC1Xtelar1();// 联系人1电话区号
		String C1_XTEL1 = on.getC1Xtel1();// 联系人1电话
		String C1_XSHIP1 = on.getC1Xship1();// 联系人1关系
		String C1_XMOBIL1 = on.getC1Xmobil1();// 联系人1手机
		String C1_XNAME2 = on.getC1Xname2();// 联系人2姓名
		String C1_XTELAR2 = on.getC1Xtelar2();// 联系人2电话区号
		String C1_XTEL2 = on.getC1Xtel2();// 联系人2电话
		String C1_XSHIP2 = on.getC1Xship2();// 联系人2关系
		String C1_XMOBIL2 = on.getC1Xmobil2();// 联系人2手机
		if (nullOrSpace(C1_XNAME1) && patteRegex(C1_XNAME1, NOSPECIALCHAR)) {
			list.add("f");
			if (nullOrSpace(C1_XTEL1) && patteRegex(C1_XTEL1, NUMBER7)) {
				list.add("f");
			} else if (nullOrSpace(C1_XMOBIL1) && patteRegex(C1_XMOBIL1, NUMBER11)) {
				list.add("f");
			} else {
				list.add("n");
			}
			if (nullOrSpace(C1_XSHIP1)) {
				list.add("f");
			} else {
				list.add("n");
			}
		}
		/*
		 * else if (nullOrSpace(C1_XNAME2)&&patteRegex(C1_XNAME2,
		 * NOSPECIALCHAR)){ list.add("f");
		 * if(nullOrSpace(C1_XTEL2)&&patteRegex(C1_XTEL2, NUMBER7)){
		 * list.add("f"); }else if
		 * (nullOrSpace(C1_XMOBIL2)&&patteRegex(C1_XMOBIL2, NUMBER11)){
		 * list.add("f"); }else{ list.add("n"); } if(nullOrSpace(C1_XSHIP2)){
		 * list.add("f"); }else{ list.add("n"); } }
		 */
		else {
			list.add("n");
		}
		// 非网申才会有签名
		if ("0".equals(on.getInputChannel())) {
			// 20170801 from wjf亲见签名
			String C4_ABTYPE = on.getC4Abtype();
			if ("1".equals(C4_ABTYPE)) {
				list.add("f");
			} else {
				list.add("n");
			}
			// 获取主附卡申请人签名情况
			Map<String, String> signFullInfoMap = opasrevieentrycompareinfoDaox.selectSignFullInfo(on.getAppId());
			String signFullInfo = signFullInfoMap.get("signFull") == null ? "" : signFullInfoMap.get("signFull");
			String applyInputfullInfo = signFullInfoMap.get("applyInputfull") == null ? ""
					: signFullInfoMap.get("applyInputfull");
			// 20170627 修改为从审查录入表查询签名完整性
			if ("1".equals(signFullInfo)) {
				list.add("f");
			} else {
				list.add("n");
			}
			// 20170801抄录文字完整有效
			if ("1".equals(applyInputfullInfo)) {
				list.add("f");
			} else {
				list.add("n");
			}
		}
		// System.out.println("list==========="+list.toString());

		//
		if (!list.contains("n")) {
			return "F";
		} else if (list.contains("f")) {
			return "H";
		} else {
			return "N";
		}
	}

	public boolean patteRegex(String field, String regex) {
		boolean rs = false;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(field);
		rs = matcher.matches();
		return rs;
	}

	public boolean nullOrSpace(String field) {
		if (field == null || field.trim().length() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateString = sdf.format(date);
		return dateString;
	}

}
