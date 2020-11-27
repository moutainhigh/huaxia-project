package com.huaxia.plaze.ui.unionpay.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huaxia.plaze.ui.unionpay.domain.InpKeyField;
import com.huaxia.plaze.ui.unionpay.domain.UnionInterface3118;
import com.huaxia.plaze.ui.unionpay.mapper.UPInterface3118Mapper;
import com.huaxia.plaze.ui.unionpay.service.Interface3118Service;
import com.huaxia.util.CommonUtil;
import com.huaxia.util.OnlineInterfaceUtil;

/**
 * @description: 联机接口3118查询并入库
 * @author 张志宽
 * @since 2017年4月5日
 * @version 1.0
 * @lastUpdateDate
 * @lastUpdateUser -----------------------------------------------------------
 *                 2017-10-10 qingfeng.xiu v1.1 联机3118第一次查询返回结果先入库。
 *                 如果标准卡或易达金缺一，且有翻页，则继续查询，直到标准卡和易达金 都存在或没有翻页为止。
 *                 -----------------------------------------------------------
 *                 2018-11-01 qingfeng.xiu v2.0
 *                 ⑴当为单办主卡或祝福同申的时候,根据证件类型和证件号查询联机3118接口
 *                 将客户名下所有的卡片信息存入库中（存放卡信息的OPAS_INTERFACE_3118
 *                 表由之前的一个条码一行记录最多五个卡片信息改为一个条码多行记录，所有 的卡片信息）⑶
 *                 ⑵当为单申附卡的时候，只需要递归查出一张标卡和一张易达金的卡信息即可，
 *                 但卡片必须为主卡，否则用查回的卡号查询联机3001（账户）时会报错。
 *                 查询中，有接口报错则更新库中存量标志为2（异常），以便页面显示人工审核。
 *                 -----------------------------------------------------------
 */
@Service("interface3118Service")
public class Interface3118ServiceImpl implements Interface3118Service {

	private static final Logger logger = LoggerFactory.getLogger(Interface3118ServiceImpl.class);

	private OnlineInterfaceUtil<UnionInterface3118> oiUtil = new OnlineInterfaceUtil<UnionInterface3118>();

	@Resource
	private UPInterface3118Mapper upInterface3118Mapper;

	private List<InpKeyField> temList;

	public List<InpKeyField> getTemList() {
		return temList;
	}

	public void setTemList(List<InpKeyField> temList) {
		this.temList = temList;
	}

	@Override
	public boolean saveData(String sendData, String rtnInd, String lastCardNbr) throws Exception {
		int batchNo = upInterface3118Mapper.select3118Sequence();
		String reqMsg = getReqMsg("1", sendData, batchNo, rtnInd, lastCardNbr);
		logger.info("联机接口3118[筛选一张标卡和易达金]查询请求报文>>{}<<", new Object[] { reqMsg });
		String resMsg = null;
		try {
			resMsg = OnlineInterfaceUtil.sendMsgAndReadMsg(reqMsg, 2 * 1024 * 1024);
			logger.info("联机接口3118[筛选一张标卡和易达金]查询响应报文>>{}<<", new Object[] { resMsg });
			String code = resMsg.substring(4, 10);
			if ("000000".equals(code)) {
				UnionInterface3118 ui8 = null;
				ui8 = (UnionInterface3118) oiUtil.analysisResMsg(UnionInterface3118.class, temList, resMsg);
				if (ui8.getCount() == 0) {
					return false;
				}
				if (ui8.getCardnbr1() != null) {
					if (!"Q".equals(ui8.getCardstat1()) || !"WQ".equals(ui8.getCardstat1()) ) {
						return true;
					}

				}

				if (ui8.getCardnbr2() != null) {
					if (!"Q".equals(ui8.getCardstat2()) || !"WQ".equals(ui8.getCardstat2()) ) {
						return true;
					}

				}
				if (ui8.getCardnbr3() != null) {
					if (!"Q".equals(ui8.getCardstat3()) || !"WQ".equals(ui8.getCardstat3()) ) {
						return true;
					}

				}
				if (ui8.getCardnbr4() != null) {
					if (!"Q".equals(ui8.getCardstat4()) || !"WQ".equals(ui8.getCardstat4()) ) {
						return true;
					}

				}
				if (ui8.getCardnbr5() != null) {
					if (!"Q".equals(ui8.getCardstat5()) || !"WQ".equals(ui8.getCardstat5()) ) {
						return true;
					}

				}
				if ("1".equals(ui8.getRtnInd())) {
					return saveData(sendData, "1", ui8.getCardnbr5());
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;

	}

	/**
	 * 按类型生成请求3118报文
	 * 
	 * @param queryType
	 *            请求类型
	 * @param param
	 *            当请求类型为卡号时时卡号，当位证件时时证件类型+证件号
	 * @param batchNo
	 *            请求批次号
	 * @param rtnInd
	 *            翻页标志
	 * @param lastCardNbr
	 *            翻页卡号
	 * @return
	 */
	public static String getReqMsg(String queryType, String param, int batchNo, String rtnInd, String lastCardNbr) {
		StringBuffer buffer = new StringBuffer(104);
		if ("1".equals(queryType)) {
			buffer.append("0103");
			buffer.append("3118"); // 交易代码
			buffer.append("      "); // 响应吗
			buffer.append("6304"); // 银行代号
			buffer.append("CR"); // 交易来源
			buffer.append("000001"); // 网点代号
			buffer.append("000001"); // 操作员号
			buffer.append(CommonUtil.formatNum(String.valueOf(batchNo), 6)); // 流水号
			buffer.append(param); // 请求参数(证件类型+身份证号)
			buffer.append("1"); // 查询方式
			buffer.append("                   "); // 查询卡号
			buffer.append("0"); // 是否检查密码标志
			buffer.append("        "); // 密码
			buffer.append(rtnInd); // 翻页标志
			buffer.append(CommonUtil.formatStr(lastCardNbr, 19)); // 卡号
		} else if ("2".equals(queryType)) {
			buffer.append("0103");
			buffer.append("3118"); // 交易代码
			buffer.append("      "); // 响应吗
			buffer.append("6304"); // 银行代号
			buffer.append("CR"); // 交易来源
			buffer.append("000001"); // 网点代号
			buffer.append("000001"); // 操作员号
			buffer.append(CommonUtil.formatNum(String.valueOf(batchNo), 6));
			buffer.append("  "); // 证件类型
			buffer.append(CommonUtil.checkLen(18)); // 证件号码
			buffer.append("2"); // 查询方式 2卡号
			buffer.append(CommonUtil.formatStr(param, 19)); // 查询卡号
			buffer.append("0"); // 是否检查密码标志
			buffer.append(CommonUtil.checkLen(8)); // 密码
			buffer.append(rtnInd); // 翻页标志
			buffer.append(CommonUtil.formatStr(lastCardNbr, 19)); // 翻页卡号
		}
		return buffer.toString();
	}

	public static String getReqMsg(String queryType, String param, int batchNo) {
		return getReqMsg(queryType, param, batchNo, "", "");
	}
}
