/**
 * Title: PBOCMapping.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月19日下午2:58:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package com.huaxia.plaze.common;

/**
 * @class_name:PBOCMapping2020年1月19日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月19日下午2:58:05
 */
public class PbocMapping {

	/**
	 * 
	 */
	public PbocMapping() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: ResultbyQueryType
	 *@Description: TODO 人行映射
	 *@param QueryType
	 *@return
	 *@author: zhengguofeng
	 *@Date: 2020年1月19日下午3:09:07
	 */
	public static String ResultbyQueryType(String QueryType) {
		String QueryType1=QueryType;
		if(QueryType == null) return null;
		switch(QueryType){
		case "1":
			QueryType1="03";
			break;
		case "2":
			QueryType1="01";
			break;
		case "3":
			QueryType1="19";
			break;
		}
		return QueryType1;

}
}
