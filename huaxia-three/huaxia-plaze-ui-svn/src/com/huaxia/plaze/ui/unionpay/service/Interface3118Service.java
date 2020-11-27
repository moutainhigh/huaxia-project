package com.huaxia.plaze.ui.unionpay.service;



public interface Interface3118Service {
	
	/**
	 * @description sendData长度为19时，按卡号查询；sendData长度为20时按证件类型和证件号码查询
	 * @param appId
	 * 			申请书流水号
	 * @param sendData
	 * 			查询的参数(目前按证件号)
	 * @param rtnInd
	 * 			翻页标志
	 * @param lastCardNbr
	 * 			上次查询最后一个卡号(仅限递归查询时赋值)
	 * @param ydjFlag
	 * 			是否存在YDJ卡标识
	 * @param bzkFlag
	 * 			是否存在标准卡标识
	 * @param queryCount
	 * 			此申请书第几次查询
	 * @param uif3118
	 * 			第一次查询响应报文实体
	 * @param saveFlag
	 * 			响应报文是否入库 
	 * @param invokebpm
	 * 			报错重发是否调用工作流标志
	 * @param isResend
	 * 			重发还是初次查询
	 * @param onCardList
	 * 			存放筛选出的一张易达金一张标卡的信息
	 */
	public boolean saveData(String sendData, String rtnInd, String lastCardNbr) throws Exception;
			

}
