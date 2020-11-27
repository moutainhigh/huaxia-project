package com.huaxia.opas.cache.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;


public class CacheConsts {
	private static final Logger log = Logger.getLogger(CacheConsts.class);
	
	/**
	 * 风险检查对应的表
	 */
	public static final List<String> LISTCHKTABLE=new ArrayList<String>();
	public static final List<String> LISTCHKTABLE_COLUMN=new ArrayList<String>();
	static{
		//身份类风险名单表
		LISTCHKTABLE.add("OPAS_IDENTITY_RISKLIST");
		//单位类风险名单表
		LISTCHKTABLE.add("OPAS_COMPANY_RISKLIST");
		//电话类风险名单表
		LISTCHKTABLE.add("OPAS_TEL_RISKLIST");
		//地址类风险名单表
		LISTCHKTABLE.add("OPAS_ADDR_RISKLIST");
		//推广员风险名单表
		LISTCHKTABLE.add("OPAS_PROMOTER_RISKLIST");
		//同业关注风险名单表
		LISTCHKTABLE.add("OPAS_SAMEINDUSTRY_RISKLIST");
		
		LISTCHKTABLE_COLUMN.add("APPLY_CERTIFI_NO");
		LISTCHKTABLE_COLUMN.add("APPLY_COMPANY_NAME");
		LISTCHKTABLE_COLUMN.add("APPLY_MOBILE_ID"); 
		LISTCHKTABLE_COLUMN.add("CON_MOBILE_ID");   
		LISTCHKTABLE_COLUMN.add("APPLY_COMPANY_TEL");
		LISTCHKTABLE_COLUMN.add("CON_COMPANY_TEL"); 
		LISTCHKTABLE_COLUMN.add("APPLY_LIVING_TEL");
		LISTCHKTABLE_COLUMN.add("CON_LIVING_TEL");  
		LISTCHKTABLE_COLUMN.add("APPLY_OTHER_TEL"); 
		LISTCHKTABLE_COLUMN.add("CON_OTHER_TEL");   
		LISTCHKTABLE_COLUMN.add("APPLY_COMPANY_ADDR");
		LISTCHKTABLE_COLUMN.add("APPLY_ALL_ADDR_LIVING_ADDR");
		LISTCHKTABLE_COLUMN.add("APPLY_CERTIFI_NO");
		LISTCHKTABLE_COLUMN.add("APPLY_CERTIFI_NO");

	}
	/**
	 * fico流水报文类型：01-风险报文
	 */
	public static final String FICO_TYPE_RISK="01";
	
	/**
	 * fico流水报文类型：02-运营报文
	 */
	public static final String FICO_TYPE_DESCI="02";
	
	/**
	 * fico流水报文类型：03-欺诈报文
	 */
	public static final String FICO_TYPE_CHEAT="03";
	
	public static final String RISK = "RISK_";//01

	public static final String DESCI = "DESCI_";//02
	
	public static final String CHEAT = "CHEAT_";//03
	
	/**
	 * 存token的key前缀
	 */
	public static final String TOKEN = "TOKEN_";
	
	/**
	 * 存业务字典的Key前缀
	 */
	public static final String DICT = "DICT_";
	
	public static final String UUID="UUID_";
	/**
	 * 存全国各个省、市、县、镇、街道等各个行政级别的地址
	 */
	public static final String ADDRESS="ADDRESS_";
	
	/**
	 * 1:黑名单匹配
	 */
	public static final String RISK_TYPE_BLACK="1";
	/**
	 * 3:三方数据
	 */
	public static final String RISK_TYPE_THIRDPARTY="3";
	
	/**
	 * 名单数据
	 */
	public static final String RISK_TYPE_TEAM="2";
	
	/**
	 * 申请信息与库表信息成功匹配
	 */
	public static final String RISK_CHECK_SUCCEED="1";
	/**
	 * 规则各个节点对应值
	 * triPartQuery-查询三方数据前的节点
	 * credittactics-征信策略节点
	 */
	public static final String TRIPART_QUERY = "triPartQuery";
	public static final String CREDIT_TACTICS ="credittactics";
	/**
	 * 加载配置文件
	 */
	public static List<String> abcodeList = null;
	public static List<String> abcodeBzkList = null;
	public static List<String> abcodeBzkH1List = null;
	public static List<String> refuseCodeList = null;
	public static List<String> refuseCodeListYdjH1 = null;
	public static List<String> fqzRiskGategoryList = null;
	public static List<String> excludeKeywordList = null;
	public static List<String> abcodeYdjH11List = null;
	public static List<String> abcodeYdjH19List = null;
	public static final Properties prop = new Properties();
	private final static String resourcePath = "config/properties/abnormalcode.properties";
	//20170615  匹配率
	public static float accuracy = 0.0f;

	static{
		InputStream in = CacheConsts.class.getClassLoader().getResourceAsStream(resourcePath);
		try {
			prop.load(in);
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		abcodeList = Arrays.asList(prop.getProperty("abnormalcode").split(","));
		accuracy = Float.valueOf(prop.getProperty("accuracy"));
		refuseCodeList = Arrays.asList(prop.getProperty("sameapprefusecode").split(","));
		refuseCodeListYdjH1 = Arrays.asList(prop.getProperty("sameapprefusecodeydjh1").split(","));
		abcodeBzkList = Arrays.asList(prop.getProperty("abnormalbzkcode").split(","));
		abcodeBzkH1List = Arrays.asList(prop.getProperty("abnormalbzkh1code").split(","));
		abcodeYdjH11List = Arrays.asList(prop.getProperty("abnormalydjh11code").split(","));
		abcodeYdjH19List = Arrays.asList(prop.getProperty("abnormalydjh19code").split(","));
		fqzRiskGategoryList = Arrays.asList(prop.getProperty("fqzRiskGategorycode").split(","));
		excludeKeywordList = Arrays.asList(prop.getProperty("exclude_keyword").split(","));
	}
	
}
