package com.huaxia.opas.action.apply;
/**
 * 2017-10-10  修复申请件高级查询批量到处申请件团办号空的问题
 * @author wangtao
 * @version v1.1(初始v1.0)
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.apply.ApplyInfoService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.DownloadUtil;
import com.huaxia.opas.util.ServletProxy;

/**
 * 申请件批量导出下载
 * 
 * @author gezhihui
 *
 */
public class ApplyExportServlet extends ServletProxy {
	private static final long serialVersionUID = -6646481377479124970L;
	@Autowired
	private ApplyInfoService applyInfoService;
	@Autowired
	private ApUserService  apUserService;
	
	public ApplyInfoService getApplyInfoService() {
		return applyInfoService;
	}

	public void setApplyInfoService(ApplyInfoService applyInfoService) {
		this.applyInfoService = applyInfoService;
	}

	public ApUserService getApUserService() {
		return apUserService;
	}

	public void setApUserService(ApUserService apUserService) {
		this.apUserService = apUserService;
	}

	public ApplyExportServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CoreException, ParseException {

		// 获取前台传来的url上的参数
		String url = request.getQueryString();
		String exportFalg = request.getParameter("exportFalg");
		List<String> appIds = new ArrayList<String>();
		// 截取
		String str = url.toString().split("=")[1].split("&")[0];
		String[] appIdsStr = str.split(",");
		for (int i = 0; i < appIdsStr.length; i++) {
			appIds.add(appIdsStr[i]);
		}

		// 创建工作薄对象
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建工作表对象
		HSSFSheet sheet = wb.createSheet();
		// 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 32));

		// 设置列的的宽度
		sheet.setColumnWidth(0, 256 * 20);
		sheet.setColumnWidth(1, 256 * 30);
		sheet.setColumnWidth(2, 256 * 18);
		sheet.setColumnWidth(3, 256 * 20);
		sheet.setColumnWidth(4, 256 * 15);
		sheet.setColumnWidth(5, 256 * 15);
		sheet.setColumnWidth(6, 256 * 20);
		sheet.setColumnWidth(7, 256 * 20);
		sheet.setColumnWidth(8, 256 * 15);
		sheet.setColumnWidth(9, 256 * 15);
		sheet.setColumnWidth(10, 256 * 15);
		sheet.setColumnWidth(11, 256 * 15);
		sheet.setColumnWidth(12, 256 * 15);
		sheet.setColumnWidth(13, 256 * 15);
		sheet.setColumnWidth(14, 256 * 15);
		sheet.setColumnWidth(15, 256 * 15);
		sheet.setColumnWidth(16, 256 * 15);
		sheet.setColumnWidth(17, 256 * 15);
		sheet.setColumnWidth(18, 256 * 15);
		sheet.setColumnWidth(19, 256 * 15);
		sheet.setColumnWidth(20, 256 * 15);
		sheet.setColumnWidth(22, 256 * 15);
		sheet.setColumnWidth(23, 256 * 10);
		sheet.setColumnWidth(24, 256 * 10);
		sheet.setColumnWidth(25, 256 * 10);
		sheet.setColumnWidth(26, 256 * 10);
		sheet.setColumnWidth(27, 256 * 10);
		sheet.setColumnWidth(28, 256 * 10);
		sheet.setColumnWidth(29, 256 * 10);
		sheet.setColumnWidth(30, 256 * 10);
		sheet.setColumnWidth(31, 256 * 10);
		sheet.setColumnWidth(32, 256 * 160);

		// 定义行对象
		Row row = null;
		// 定义单元格
		Cell cell = null;
		// 第一下标,默认从第一行开始
		int rowNo = 0;
		// 定义列
		int cellNo = 0;
		// 创建第一行
		row = sheet.createRow(rowNo++);
		// 设置第一行高
		row.setHeightInPoints(24f);
		// 创建单元格
		cell = row.createCell(cellNo);
		// 设置类容
		cell.setCellValue("申请件批量导出信息");

		// excle中 Title
		String[] array = { "流水号", "单位名称", "系统决策结果", "系统决策建议额度", "征信评分", "税前月收入", "中征信“信用1000”评分", "系统审批结果描述", "授信卡种产品",
				"授信额度", "审批结果", "政策码", "违例码", "拒绝码", "审批备注", "征信过件码", "人工干预额度", "系统决策无抵押DTI", "系统决策综合DTI",
				"系统决策风险敞口MUE", "人工干预无抵押DTI", "人工干预综合DTI", "人工干预风险敞口MUE", "客户类型", "审查员", "征信员","审批员", "IVS分", "芝麻分", "百融分",
				"年龄", "团办号", "挂起情况", "补件内容" };

		// 创建第二行
		row = sheet.createRow(rowNo++);
		row.setHeightInPoints(20f);

		// 遍历插入表头值
		for (int i = 0; i < array.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(array[i]);
		}
		if ("c".equals(exportFalg)) {
			// 当前数据查询
			List<Map<String, Object>> curreList = new ArrayList<>();
			for (String appId : appIds) {
				Map<String,String> bizInpMap =  applyInfoService.selectAllotMap(appId);
				String ydjFlag = bizInpMap.get("YDJ_FLAG");
				String matchingCardFlag = bizInpMap.get("MATCHING_CARD_FLAG");
				Map<String,Object> ydjMap = null;
				Map<String,Object> bzkMap = null;
				if("1".equals(ydjFlag)&&"2".equals(matchingCardFlag)){
					 ydjMap = applyInfoService.selectExprotCurrentInfoYdj(appId);
				}else{
					 bzkMap = applyInfoService.selectExprotCurrentInfoBzk(appId);
				}
				if(null!=bzkMap){
					curreList.add(bzkMap);
				}
				if(null!=ydjMap){
					curreList.add(ydjMap);
				}
			}
			// 当前补件内容查询
			List<Map<String, Object>> curreBJList = applyInfoService.selectExprotCurrentBJInfo(appIds);

			if (!curreList.isEmpty()) {
				// 遍历将数据写入Excle
				for (Map<String, Object> map : curreList) {
					// 开启创建行对象
					row = sheet.createRow(rowNo++);
					row.setHeightInPoints(20f);

					cell = row.createCell(0); // 创建列
					StringBuilder sb = new StringBuilder();
					sb.append(map.get("appId") != null ? map.get("appId") : "");
					cell.setCellValue(sb.toString());// 设置类容

					cell = row.createCell(1);
					StringBuilder sb1 = new StringBuilder();
					sb1.append(map.get("c1Coname") != null ? map.get("c1Coname") : "");
					cell.setCellValue(sb1.toString());

					cell = row.createCell(2);
					StringBuilder sb2 = new StringBuilder();
					sb2.append(map.get("sysDecisionResult") != null ? map.get("sysDecisionResult") : "");
					sb2.append(map.get("sysResultDesc") != null ? map.get("sysResultDesc") : "");
					cell.setCellValue(sb2.toString());

					cell = row.createCell(3);
					StringBuilder sb3 = new StringBuilder();
					sb3.append(map.get("adviseLimit") != null ? map.get("adviseLimit") : "");
					cell.setCellValue(sb3.toString());

					cell = row.createCell(4);
					StringBuilder sb4 = new StringBuilder();
					if(map.get("triggerType")!=null){
						sb4.append(map.get("triggerType") != null ? map.get("triggerType") : "");
					}else{
						sb4.append(map.get("creditScore") != null ? map.get("creditScore") : "");
					}
					cell.setCellValue(sb4.toString());

					cell = row.createCell(5);
					StringBuilder sb5 = new StringBuilder();
					sb5.append(map.get("pretaxIncome") != null ? map.get("pretaxIncome") : "");
					cell.setCellValue(sb5.toString());

					cell = row.createCell(6);
					StringBuilder sb6 = new StringBuilder();
					sb6.append(map.get("numberRead") != null ? map.get("numberRead") : "");
					cell.setCellValue(sb6.toString());

					cell = row.createCell(7);
					StringBuilder sb7 = new StringBuilder();
					sb7.append(map.get("masterCardApproveResult") != null ? map.get("masterCardApproveResult") : "");
					cell.setCellValue(sb7.toString());

					cell = row.createCell(8);
					StringBuilder sb8 = new StringBuilder();
					sb8.append(map.get("approveCreditProduct") != null ? map.get("approveCreditProduct") : "");
					cell.setCellValue(sb8.toString());

					cell = row.createCell(9);
					StringBuilder sb9 = new StringBuilder();
					sb9.append(map.get("approveCreditLimit") != null ? map.get("approveCreditLimit") : "");
					cell.setCellValue(sb9.toString());

					cell = row.createCell(10);
					StringBuilder sb10 = new StringBuilder();
					if(map.get("approveResult")!=null){
						sb10.append("0".equals((String)map.get("approveResult")) ? "拒绝" : "批准");
					}else{
						sb10.append("");
					}
					cell.setCellValue(sb10.toString());

					cell = row.createCell(11);
					StringBuilder sb11 = new StringBuilder();
					sb11.append(map.get("policyCode1") != null ? map.get("policyCode1") : "");
					cell.setCellValue(sb11.toString());

					cell = row.createCell(12);
					StringBuilder sb12 = new StringBuilder();
					sb12.append(map.get("violateCode1") != null ? map.get("violateCode1") : "");
					cell.setCellValue(sb12.toString());

					cell = row.createCell(13);
					StringBuilder sb13 = new StringBuilder();
					sb13.append(map.get("refuseCode1") != null ? map.get("refuseCode1") : "");
					cell.setCellValue(sb13.toString());

					cell = row.createCell(14);
					StringBuilder sb14 = new StringBuilder();
					sb14.append(map.get("meMo") != null ? map.get("meMo") : "");
					cell.setCellValue(sb14.toString());

					cell = row.createCell(15);
					StringBuilder sb15 = new StringBuilder();
					sb15.append(map.get("creditResult") != null ? map.get("creditResult") : "");
					cell.setCellValue(sb15.toString());

					cell = row.createCell(16);
					StringBuilder sb16 = new StringBuilder();
					sb16.append(map.get("manualLimit") != null ? map.get("manualLimit") : "");
					cell.setCellValue(sb16.toString());

					cell = row.createCell(17);
					StringBuilder sb17 = new StringBuilder();
					sb17.append(map.get("unPlgeDti") != null ? map.get("unPlgeDti") : "");
					cell.setCellValue(sb17.toString());

					cell = row.createCell(18);
					StringBuilder sb18 = new StringBuilder();
					sb18.append(map.get("cpDti") != null ? map.get("cpDti") : "");
					cell.setCellValue(sb18.toString());

					cell = row.createCell(19);
					StringBuilder sb19 = new StringBuilder();
					sb19.append(map.get("mue") != null ? map.get("mue") : "");
					cell.setCellValue(sb19.toString());

					cell = row.createCell(20);
					StringBuilder sb20 = new StringBuilder();
					sb20.append(map.get("sysUnmortgageDti") != null ? map.get("sysUnmortgageDti") : "");
					cell.setCellValue(sb20.toString());

					cell = row.createCell(21);
					StringBuilder sb21 = new StringBuilder();
					sb21.append(map.get("sysSyntheticalDti") != null ? map.get("sysSyntheticalDti") : "");
					cell.setCellValue(sb21.toString());

					cell = row.createCell(22);
					StringBuilder sb22 = new StringBuilder();
					sb22.append(map.get("sysRiskOpenMue") != null ? map.get("sysRiskOpenMue") : "");
					cell.setCellValue(sb22.toString());

					cell = row.createCell(23);
					StringBuilder sb23 = new StringBuilder();
					sb23.append(map.get("custType") != null ? map.get("custType") : "");
					cell.setCellValue(sb22.toString().toString());

					cell = row.createCell(24);
					StringBuilder sb24 = new StringBuilder();
					sb24.append(map.get("auditorName") != null ? map.get("auditorName") : "");
					cell.setCellValue(sb24.toString());

					cell = row.createCell(25);
					StringBuilder sb25 = new StringBuilder();
					sb25.append(map.get("crediterName") != null ? map.get("crediterName") : "");
					cell.setCellValue(sb25.toString());
					
					cell = row.createCell(26);
					StringBuilder sb26 = new StringBuilder();
//					sb26.append(map.get("approverName") != null ? map.get("approverName") : "");//审批员
					if(map.get("approver")!=null){
						ApUser apUser = apUserService.queryApUserByUserCode((String)map.get("approver"));
						String userName = "";
						if(apUser!=null){
							userName = apUser.getUserName();
						}else{
							userName=(String)map.get("approver");
						}
						sb26.append(userName);
					}else{
						sb26.append("");
					}
					cell.setCellValue(sb26.toString());
					
					cell = row.createCell(27);
					StringBuilder sb27 = new StringBuilder();
					sb27.append(map.get("ivsScore") != null ? map.get("ivsScore") : "");
					cell.setCellValue(sb27.toString());

					cell = row.createCell(28);
					StringBuilder sb28 = new StringBuilder();
					sb28.append(map.get("zmScore") != null ? map.get("zmScore") : "");
					cell.setCellValue(sb28.toString());

					cell = row.createCell(29);
					StringBuilder sb29 = new StringBuilder();
					sb29.append(map.get("Score") != null ? map.get("Score") : "");
					cell.setCellValue(sb29.toString());

					cell = row.createCell(30);
					StringBuilder sb30 = new StringBuilder();
					sb30.append(map.get("age") != null ? map.get("age") : "");
					cell.setCellValue(sb30.toString());

					cell = row.createCell(31);
					StringBuilder sb31 = new StringBuilder();
					sb31.append(map.get("c4Apbatch") != null ? map.get("c4Apbatch") : "");
					cell.setCellValue(sb31.toString());

					cell = row.createCell(32);
					StringBuilder sb32 = new StringBuilder();
					String currStatus = (String) map.get("currStatus");
					if(currStatus!=null&&!"".equals(currStatus)){
						if("0".equals(currStatus)){
							sb32.append("当前环节池中");
						}
						if("1".equals(currStatus)){
							sb32.append("未分配未挂起");
						}
						if("2".equals(currStatus)){
							sb32.append("未分配已挂起");
						}
						if("3".equals(currStatus)){
							sb32.append("已分配未挂起");
						}
						if("4".equals(currStatus)){
							sb32.append("已分配已挂起");
						}
					}else{
						sb32.append("");
					}
					cell.setCellValue(sb32.toString());

					cell = row.createCell(33);
					StringBuilder sb33 = new StringBuilder();
					if (!curreBJList.isEmpty()) {
						for (Map<String, Object> map1 : curreBJList) {
							if (map.get("appId").equals(map1.get("appId1")) && "1".equals(map.get("ydjFlag"))) {

								if (map1.get("patchCode") != null && !"".equals(map1.get("patchCode"))) {
									sb33.append("补件码值:" + map1.get("patchCode") + ",");
								}

								if (map1.get("identityCard") != null && !"".equals(map1.get("identityCard"))) {
									if ("1".equals(map1.get("identityCard").toString())) {
										sb33.append("身份证文件:" + map1.get("identityCard") + ",");
									}
								}

								if (map1.get("payWater") != null && !"".equals(map1.get("payWater"))) {
									if ("1".equals(map1.get("payWater").toString())) {
										sb33.append("工资流水:" + map1.get("payWater") + ",");
									}
								}

								if (map1.get("workProof") != null && !"".equals(map1.get("workProof"))) {
									if ("1".equals(map1.get("workProof").toString())) {
										sb33.append("工作证明文件:" + map1.get("workProof") + ",");
									}
								}

								if (map1.get("incomeProof") != null && !"".equals(map1.get("incomeProof"))) {
									if ("1".equals(map1.get("incomeProof").toString())) {
										sb33.append("收入证明文件:" + map1.get("incomeProof") + ",");
									}
								}

								if (map1.get("socialSecurity") != null && !"".equals(map1.get("socialSecurity"))) {
									if ("1".equals(map1.get("socialSecurity").toString())) {
										sb33.append("社保:" + map1.get("socialSecurity") + ",");
									}
								}

								if (map1.get("houseProof") != null && !"".equals(map1.get("houseProof"))) {
									if ("1".equals(map1.get("houseProof").toString())) {
										sb33.append("房产证明:" + map1.get("houseProof") + ",");
									}
								}

								if (map1.get("accumulationFund") != null && !"".equals(map1.get("accumulationFund"))) {
									if ("1".equals(map1.get("accumulationFund").toString())) {
										sb33.append("公积金:" + map1.get("accumulationFund") + ",");
									}
								}

								if (map1.get("educationProof") != null && !"".equals(map1.get("educationProof"))) {
									if ("1".equals(map1.get("educationProof").toString())) {
										sb33.append("学历证明:" + map1.get("educationProof") + ",");
									}
								}

								if (map1.get("taxReceipt") != null && !"".equals(map1.get("taxReceipt"))) {
									if ("1".equals(map1.get("taxReceipt").toString())) {
										sb33.append("税单:" + map1.get("taxReceipt") + ",");
									}
								}

								if (map1.get("creditCardBill") != null && !"".equals(map1.get("creditCardBill"))) {
									if ("1".equals(map1.get("creditCardBill").toString())) {
										sb33.append("信用卡账单:" + map1.get("creditCardBill") + ",");
									}
								}

								if (map1.get("ownerBankProof") != null && !"".equals(map1.get("ownerBankProof"))) {
									if ("1".equals(map1.get("ownerBankProof").toString())) {
										sb33.append("业务往来证明:" + map1.get("ownerBankProof") + ",");
									}
								}

								if (map1.get("financalProof") != null && !"".equals(map1.get("financalProof"))) {
									if ("1".equals(map1.get("financalProof").toString())) {
										sb33.append("财力证明:" + map1.get("financalProof") + ",");
									}
								}

								if (map1.get("loanSettleProof") != null && !"".equals(map1.get("loanSettleProof"))) {
									if ("1".equals(map1.get("loanSettleProof").toString())) {
										sb33.append("贷款结清证明:" + map1.get("loanSettleProof") + ",");
									}
								}

								if (map1.get("rprProof") != null && !"".equals(map1.get("rprProof"))) {
									if ("1".equals(map1.get("rprProof").toString())) {
										sb33.append("户口证明:" + map1.get("rprProof") + ",");
									}
								}

								if (map1.get("marriageCertificate") != null
										&& !"".equals(map1.get("marriageCertificate"))) {
									if ("1".equals(map1.get("marriageCertificate").toString())) {
										sb33.append("结婚证:" + map1.get("marriageCertificate") + ",");
									}
								}

								if (map1.get("businessLicense") != null && !"".equals(map1.get("businessLicense"))) {
									if ("1".equals(map1.get("businessLicense").toString())) {
										sb33.append("企业营业执照:" + map1.get("businessLicense") + ",");
									}
								}

								if (map1.get("carProof") != null && !"".equals(map1.get("carProof"))) {
									if ("1".equals(map1.get("carProof").toString())) {
										sb33.append("车产证明:" + map1.get("carProof") + ",");
									}
								}

								if (map1.get("retricalMasterCopy") != null
										&& !"".equals(map1.get("retricalMasterCopy"))) {
									if ("1".equals(map1.get("retricalMasterCopy").toString())) {
										sb33.append("调阅原件:" + map1.get("retricalMasterCopy") + ",");
									}
								}

								if (map1.get("imageRepair") != null && !"".equals(map1.get("imageRepair"))) {
									if ("1".equals(map1.get("imageRepair").toString())) {
										sb33.append("影像修复:" + map1.get("imageRepair") + ",");
									}
								}

								if (map1.get("other") != null && !"".equals(map1.get("other"))) {
									if ("1".equals(map1.get("other").toString())) {
										sb33.append("其他:" + map1.get("other") + ",");
									}
								}

								if (map1.get("crtDate") != null && !"".equals(map1.get("crtDate"))) {
									sb33.append("创建日期:" + map1.get("crtDate") + ",");
								}

								if (map1.get("crtUser") != null && !"".equals(map1.get("crtUser"))) {
									sb33.append("创建人:" + map1.get("crtUser") + ",");
								}
							}
							if (map.get("appId").equals(map1.get("appId2")) && "2".equals(map.get("ydjFlag"))) {
								// 标准卡
								if (map1.get("patchCode1") != null && !"".equals(map1.get("patchCode1"))) {
									sb33.append("标卡补件码值:" + map1.get("patchCode1") + ",");
								}

								if (map1.get("identityCard1") != null && !"".equals(map1.get("identityCard1"))) {
									sb33.append("标卡身份证文件:" + map1.get("identityCard1") + ",");
								}

								if (map1.get("incomeConfirm") != null && !"".equals(map1.get("incomeConfirm"))) {
									if ("1".equals(map1.get("incomeConfirm").toString())) {
										sb33.append("标卡收入证明:" + map1.get("incomeConfirm") + ",");
									}
								}

								if (map1.get("drivngLicense") != null && !"".equals(map1.get("drivngLicense"))) {
									if ("1".equals(map1.get("drivngLicense").toString())) {
										sb33.append("标卡行驶证:" + map1.get("drivngLicense") + ",");
									}
								}

								if (map1.get("otherCreditCard") != null && !"".equals(map1.get("otherCreditCard"))) {
									if ("1".equals(map1.get("otherCreditCard").toString())) {
										sb33.append("标卡他行信用卡:" + map1.get("otherCreditCard") + ",");
									}
								}

								if (map1.get("others") != null && !"".equals(map1.get("others"))) {
									if ("1".equals(map1.get("others").toString())) {
										sb33.append("标卡其他:" + map1.get("others") + ",");
									}
								}

								if (map1.get("isHavesign") != null && !"".equals(map1.get("isHavesign"))) {
									if ("1".equals(map1.get("isHavesign").toString())) {
										sb33.append("标卡是否有签名:" + map1.get("isHavesign") + ",");
									}
								}

								if (map1.get("liveConfirm") != null && !"".equals(map1.get("liveConfirm"))) {
									if ("1".equals(map1.get("liveConfirm").toString())) {
										sb33.append("标卡居住证明:" + map1.get("liveConfirm") + ",");
									}
								}

								if (map1.get("eduConfirm") != null && !"".equals(map1.get("eduConfirm"))) {
									if ("1".equals(map1.get("eduConfirm").toString())) {
										sb33.append("标卡学历证明:" + map1.get("eduConfirm") + ",");
									}
								}

								if (map1.get("ownerBankConfirm") != null && !"".equals(map1.get("ownerBankConfirm"))) {
									if ("1".equals(map1.get("ownerBankConfirm").toString())) {
										sb33.append("标卡业务往来证明:" + map1.get("ownerBankConfirm") + ",");
									}
								}

								if (map1.get("nonAddfiles") != null && !"".equals(map1.get("nonAddfiles"))) {
									if ("1".equals(map1.get("nonAddfiles").toString())) {
										sb33.append("标卡未补充资料:" + map1.get("nonAddfiles") + ",");
									}
								}

								if (map1.get("workConfirm") != null && !"".equals(map1.get("workConfirm"))) {
									if ("1".equals(map1.get("workConfirm").toString())) {
										sb33.append("标卡工作证明:" + map1.get("workConfirm") + ",");
									}
								}

								if (map1.get("houseConfirm") != null && !"".equals(map1.get("houseConfirm"))) {
									if ("1".equals(map1.get("houseConfirm").toString())) {
										sb33.append("标卡房产证明:" + map1.get("houseConfirm") + ",");
									}
								}

								if (map1.get("financalConfirm") != null && !"".equals(map1.get("financalConfirm"))) {
									if ("1".equals(map1.get("financalConfirm").toString())) {
										sb33.append("标卡资格证书:" + map1.get("financalConfirm") + ",");
									}
								}

								if (map1.get("crtDate1") != null && !"".equals(map1.get("crtDate1"))) {
									sb33.append("标卡创建日期:" + map1.get("crtDate1") + ",");
								}

								if (map1.get("crtUser1") != null && !"".equals(map1.get("crtUser1"))) {
									sb33.append("标卡创建人:" + map1.get("crtUser1") + ",");
								}
							}

						}

					}

					String string = sb33.toString();
					int length = string.length();
					if (length != 0) {
						String substring = string.substring(0, length - 1);
						cell.setCellValue(substring);
					} else {
						cell.setCellValue(string);
					}

					cellNo = 0; // 重新开始遍历

				}

				ByteArrayOutputStream os = new ByteArrayOutputStream();
				wb.write(os);
				os.flush();
				os.close();
				// 下载
				DownloadUtil downloadUtil = new DownloadUtil();
				downloadUtil.download(os, request,response, "导出数据.xls");
			}

		} else {
			// 历史数据 查询
			List<Map<String, Object>> historyList = new ArrayList<>();
			for (String appId : appIds) {
				Map<String,String> bizInpMap =  applyInfoService.selectAllotMap(appId);
				String ydjFlag = bizInpMap.get("YDJ_FLAG");
				String matchingCardFlag = bizInpMap.get("MATCHING_CARD_FLAG");
				Map<String,Object> ydjMap = null;
				Map<String,Object> bzkMap = null;
				if("1".equals(ydjFlag)&&"2".equals(matchingCardFlag)){
					 ydjMap = applyInfoService.selectExprotCurrentInfoYdjHis(appId);
				}else{
					 bzkMap = applyInfoService.selectExprotCurrentInfoBzkHis(appId);
				}
				if(null!=bzkMap){
					historyList.add(bzkMap);
				}
				if(null!=ydjMap){
					historyList.add(ydjMap);
				}
			}
			// 历史补件内容查询
			List<Map<String, Object>> historyBJList = applyInfoService.selectExprotHistoryBJInfo(appIds);

			if (!historyList.isEmpty()) {
				// 遍历将数据写入Excle
				for (Map<String, Object> map : historyList) {
					// 开启创建行对象
					row = sheet.createRow(rowNo++);
					row.setHeightInPoints(24f);

					cell = row.createCell(0); // 创建列
					StringBuilder sb = new StringBuilder();
					sb.append(map.get("appId") != null ? map.get("appId") : "");
					cell.setCellValue(sb.toString());// 设置类容

					cell = row.createCell(1);
					StringBuilder sb1 = new StringBuilder();
					sb1.append(map.get("c1Coname") != null ? map.get("c1Coname") : "");
					cell.setCellValue(sb1.toString());

					cell = row.createCell(2);
					StringBuilder sb2 = new StringBuilder();
					sb2.append(map.get("sysDecisionResult") != null ? map.get("sysDecisionResult") : "");
					sb2.append(map.get("sysResultDesc") != null ? map.get("sysResultDesc") : "");
					cell.setCellValue(sb2.toString());

					cell = row.createCell(3);
					StringBuilder sb3 = new StringBuilder();
					sb3.append(map.get("adviseLimit") != null ? map.get("adviseLimit") : "");
					cell.setCellValue(sb3.toString());

					cell = row.createCell(4);
					StringBuilder sb4 = new StringBuilder();
					if(map.get("triggerType")!=null){
						sb4.append(map.get("triggerType") != null ? map.get("triggerType") : "");
					}else{
						sb4.append(map.get("creditScore") != null ? map.get("creditScore") : "");
					}
					cell.setCellValue(sb4.toString());

					cell = row.createCell(5);
					StringBuilder sb5 = new StringBuilder();
					sb5.append(map.get("pretaxIncome") != null ? map.get("pretaxIncome") : "");
					cell.setCellValue(sb5.toString());

					cell = row.createCell(6);
					StringBuilder sb6 = new StringBuilder();
					sb6.append(map.get("numberRead") != null ? map.get("numberRead") : "");
					cell.setCellValue(sb6.toString());

					cell = row.createCell(7);
					StringBuilder sb7 = new StringBuilder();
					sb7.append(map.get("masterCardApproveResult") != null ? map.get("masterCardApproveResult") : "");
					cell.setCellValue(sb7.toString());

					cell = row.createCell(8);
					StringBuilder sb8 = new StringBuilder();
					sb8.append(map.get("approveCreditProduct") != null ? map.get("approveCreditProduct") : "");
					cell.setCellValue(sb8.toString());

					cell = row.createCell(9);
					StringBuilder sb9 = new StringBuilder();
					sb9.append(map.get("approveCreditLimit") != null ? map.get("approveCreditLimit") : "");
					cell.setCellValue(sb9.toString());

					cell = row.createCell(10);
					StringBuilder sb10 = new StringBuilder();
					if(map.get("approveResult")!=null){
						sb10.append("0".equals((String)map.get("approveResult")) ? "拒绝" : "批准");
					}else{
						sb10.append("");
					}
					cell.setCellValue(sb10.toString());

					cell = row.createCell(11);
					StringBuilder sb11 = new StringBuilder();
					sb11.append(map.get("policyCode1") != null ? map.get("policyCode1") : "");
					cell.setCellValue(sb11.toString());

					cell = row.createCell(12);
					StringBuilder sb12 = new StringBuilder();
					sb12.append(map.get("violateCode1") != null ? map.get("violateCode1") : "");
					cell.setCellValue(sb12.toString());

					cell = row.createCell(13);
					StringBuilder sb13 = new StringBuilder();
					sb13.append(map.get("refuseCode1") != null ? map.get("refuseCode1") : "");
					cell.setCellValue(sb13.toString());

					cell = row.createCell(14);
					StringBuilder sb14 = new StringBuilder();
					sb14.append(map.get("meMo") != null ? map.get("meMo") : "");
					cell.setCellValue(sb14.toString());

					cell = row.createCell(15);
					StringBuilder sb15 = new StringBuilder();
					sb15.append(map.get("creditResult") != null ? map.get("creditResult") : "");
					cell.setCellValue(sb15.toString());

					cell = row.createCell(16);
					StringBuilder sb16 = new StringBuilder();
					sb16.append(map.get("manualLimit") != null ? map.get("manualLimit") : "");
					cell.setCellValue(sb16.toString());

					cell = row.createCell(17);
					StringBuilder sb17 = new StringBuilder();
					sb17.append(map.get("unPlgeDti") != null ? map.get("unPlgeDti") : "");
					cell.setCellValue(sb17.toString());

					cell = row.createCell(18);
					StringBuilder sb18 = new StringBuilder();
					sb18.append(map.get("cpDti") != null ? map.get("cpDti") : "");
					cell.setCellValue(sb18.toString());

					cell = row.createCell(19);
					StringBuilder sb19 = new StringBuilder();
					sb19.append(map.get("mue") != null ? map.get("mue") : "");
					cell.setCellValue(sb19.toString());

					cell = row.createCell(20);
					StringBuilder sb20 = new StringBuilder();
					sb20.append(map.get("sysUnmortgageDti") != null ? map.get("sysUnmortgageDti") : "");
					cell.setCellValue(sb20.toString());

					cell = row.createCell(21);
					StringBuilder sb21 = new StringBuilder();
					sb21.append(map.get("sysSyntheticalDti") != null ? map.get("sysSyntheticalDti") : "");
					cell.setCellValue(sb21.toString());

					cell = row.createCell(22);
					StringBuilder sb22 = new StringBuilder();
					sb22.append(map.get("sysRiskOpenMue") != null ? map.get("sysRiskOpenMue") : "");
					cell.setCellValue(sb22.toString());

					cell = row.createCell(23);
					StringBuilder sb23 = new StringBuilder();
					sb23.append(map.get("custType") != null ? map.get("custType") : "");
					cell.setCellValue(sb23.toString());

					cell = row.createCell(24);
					StringBuilder sb24 = new StringBuilder();
					sb24.append(map.get("auditorName") != null ? map.get("auditorName") : "");
					cell.setCellValue(sb24.toString());

					cell = row.createCell(25);
					StringBuilder sb25 = new StringBuilder();
					sb25.append(map.get("crediterName") != null ? map.get("crediterName") : "");
					cell.setCellValue(sb25.toString());

					cell = row.createCell(26);
					StringBuilder sb26 = new StringBuilder();
//					sb26.append(map.get("approverName") != null ? map.get("approverName") : "");//审批员
					if(map.get("approver")!=null){
						ApUser apUser = apUserService.queryApUserByUserCode((String)map.get("approver"));
						String userName = apUser.getUserName();
						sb26.append(userName);
					}else{
						sb26.append("");
					}
					cell.setCellValue(sb26.toString());
					
					cell = row.createCell(27);
					StringBuilder sb27 = new StringBuilder();
					sb27.append(map.get("ivsScore") != null ? map.get("ivsScore") : "");
					cell.setCellValue(sb27.toString());

					cell = row.createCell(28);
					StringBuilder sb28 = new StringBuilder();
					sb28.append(map.get("zmScore") != null ? map.get("zmScore") : "");
					cell.setCellValue(sb28.toString());

					cell = row.createCell(29);
					StringBuilder sb29 = new StringBuilder();
					sb29.append(map.get("Score") != null ? map.get("Score") : "");
					cell.setCellValue(sb29.toString());

					cell = row.createCell(30);
					StringBuilder sb30 = new StringBuilder();
					sb30.append(map.get("age") != null ? map.get("age") : "");
					cell.setCellValue(sb30.toString());

					cell = row.createCell(31);
					StringBuilder sb31 = new StringBuilder();
					sb31.append(map.get("c4Apbatch") != null ? map.get("c4Apbatch") : "");
					cell.setCellValue(sb31.toString());

					cell = row.createCell(32);
					StringBuilder sb32 = new StringBuilder();
					String currStatus = (String) map.get("currStatus");
					if(currStatus!=null&&!"".equals(currStatus)){
						if("0".equals(currStatus)){
							sb32.append("当前环节池中");
						}
						if("1".equals(currStatus)){
							sb32.append("未分配未挂起");
						}
						if("2".equals(currStatus)){
							sb32.append("未分配已挂起");
						}
						if("3".equals(currStatus)){
							sb32.append("已分配未挂起");
						}
						if("4".equals(currStatus)){
							sb32.append("已分配已挂起");
						}
					}else{
						sb32.append("");
					}
					cell.setCellValue(sb32.toString());

					cell = row.createCell(33);
					StringBuilder sb33 = new StringBuilder();

					if (!historyBJList.isEmpty()) {
						for (Map<String, Object> map1 : historyBJList) {
							if (map.get("appId").equals(map1.get("appId1")) && "1".equals(map.get("ydjFlag"))) {

								if (map1.get("patchCode") != null && !"".equals(map1.get("patchCode"))) {
									sb33.append("补件码值:" + map1.get("patchCode") + ",");
								}

								if (map1.get("identityCard") != null && !"".equals(map1.get("identityCard"))) {
									if ("1".equals(map1.get("identityCard").toString())) {
										sb33.append("身份证文件:" + map1.get("identityCard") + ",");
									}
								}

								if (map1.get("payWater") != null && !"".equals(map1.get("payWater"))) {
									if ("1".equals(map1.get("payWater").toString())) {
										sb33.append("工资流水:" + map1.get("payWater") + ",");
									}
								}

								if (map1.get("workProof") != null && !"".equals(map1.get("workProof"))) {
									if ("1".equals(map1.get("workProof").toString())) {
										sb33.append("工作证明文件:" + map1.get("workProof") + ",");
									}
								}

								if (map1.get("incomeProof") != null && !"".equals(map1.get("incomeProof"))) {
									if ("1".equals(map1.get("incomeProof").toString())) {
										sb33.append("收入证明文件:" + map1.get("incomeProof") + ",");
									}
								}

								if (map1.get("socialSecurity") != null && !"".equals(map1.get("socialSecurity"))) {
									if ("1".equals(map1.get("socialSecurity").toString())) {
										sb33.append("社保:" + map1.get("socialSecurity") + ",");
									}
								}

								if (map1.get("houseProof") != null && !"".equals(map1.get("houseProof"))) {
									if ("1".equals(map1.get("houseProof").toString())) {
										sb33.append("房产证明:" + map1.get("houseProof") + ",");
									}
								}

								if (map1.get("accumulationFund") != null && !"".equals(map1.get("accumulationFund"))) {
									if ("1".equals(map1.get("accumulationFund").toString())) {
										sb33.append("公积金:" + map1.get("accumulationFund") + ",");
									}
								}

								if (map1.get("educationProof") != null && !"".equals(map1.get("educationProof"))) {
									if ("1".equals(map1.get("educationProof").toString())) {
										sb33.append("学历证明:" + map1.get("educationProof") + ",");
									}
								}

								if (map1.get("taxReceipt") != null && !"".equals(map1.get("taxReceipt"))) {
									if ("1".equals(map1.get("taxReceipt").toString())) {
										sb33.append("税单:" + map1.get("taxReceipt") + ",");
									}
								}

								if (map1.get("creditCardBill") != null && !"".equals(map1.get("creditCardBill"))) {
									if ("1".equals(map1.get("creditCardBill").toString())) {
										sb33.append("信用卡账单:" + map1.get("creditCardBill") + ",");
									}
								}

								if (map1.get("ownerBankProof") != null && !"".equals(map1.get("ownerBankProof"))) {
									if ("1".equals(map1.get("ownerBankProof").toString())) {
										sb33.append("业务往来证明:" + map1.get("ownerBankProof") + ",");
									}
								}

								if (map1.get("financalProof") != null && !"".equals(map1.get("financalProof"))) {
									if ("1".equals(map1.get("financalProof").toString())) {
										sb33.append("财力证明:" + map1.get("financalProof") + ",");
									}
								}

								if (map1.get("loanSettleProof") != null && !"".equals(map1.get("loanSettleProof"))) {
									if ("1".equals(map1.get("loanSettleProof").toString())) {
										sb33.append("贷款结清证明:" + map1.get("loanSettleProof") + ",");
									}
								}

								if (map1.get("rprProof") != null && !"".equals(map1.get("rprProof"))) {
									if ("1".equals(map1.get("rprProof").toString())) {
										sb33.append("户口证明:" + map1.get("rprProof") + ",");
									}
								}

								if (map1.get("marriageCertificate") != null
										&& !"".equals(map1.get("marriageCertificate"))) {
									if ("1".equals(map1.get("marriageCertificate").toString())) {
										sb33.append("结婚证:" + map1.get("marriageCertificate") + ",");
									}
								}

								if (map1.get("businessLicense") != null && !"".equals(map1.get("businessLicense"))) {
									if ("1".equals(map1.get("businessLicense").toString())) {
										sb33.append("企业营业执照:" + map1.get("businessLicense") + ",");
									}
								}

								if (map1.get("carProof") != null && !"".equals(map1.get("carProof"))) {
									if ("1".equals(map1.get("carProof").toString())) {
										sb33.append("车产证明:" + map1.get("carProof") + ",");
									}
								}

								if (map1.get("retricalMasterCopy") != null
										&& !"".equals(map1.get("retricalMasterCopy"))) {
									if ("1".equals(map1.get("retricalMasterCopy").toString())) {
										sb33.append("调阅原件:" + map1.get("retricalMasterCopy") + ",");
									}
								}

								if (map1.get("imageRepair") != null && !"".equals(map1.get("imageRepair"))) {
									if ("1".equals(map1.get("imageRepair").toString())) {
										sb33.append("影像修复:" + map1.get("imageRepair") + ",");
									}
								}

								if (map1.get("other") != null && !"".equals(map1.get("other"))) {
									if ("1".equals(map1.get("other").toString())) {
										sb33.append("其他:" + map1.get("other") + ",");
									}
								}

								if (map1.get("crtDate") != null && !"".equals(map1.get("crtDate"))) {
									sb33.append("创建日期:" + map1.get("crtDate") + ",");
								}

								if (map1.get("crtUser") != null && !"".equals(map1.get("crtUser"))) {
									sb33.append("创建人:" + map1.get("crtUser") + ",");
								}
							}
							if (map.get("appId").equals(map1.get("appId2")) && "2".equals(map.get("ydjFlag"))) {
								// 标准卡
								if (map1.get("patchCode1") != null && !"".equals(map1.get("patchCode1"))) {
									sb33.append("标卡补件码值:" + map1.get("patchCode1") + ",");
								}

								if (map1.get("identityCard1") != null && !"".equals(map1.get("identityCard1"))) {
									sb33.append("标卡身份证文件:" + map1.get("identityCard1") + ",");
								}

								if (map1.get("incomeConfirm") != null && !"".equals(map1.get("incomeConfirm"))) {
									if ("1".equals(map1.get("incomeConfirm").toString())) {
										sb33.append("标卡收入证明:" + map1.get("incomeConfirm") + ",");
									}
								}

								if (map1.get("drivngLicense") != null && !"".equals(map1.get("drivngLicense"))) {
									if ("1".equals(map1.get("drivngLicense").toString())) {
										sb33.append("标卡行驶证:" + map1.get("drivngLicense") + ",");
									}
								}

								if (map1.get("otherCreditCard") != null && !"".equals(map1.get("otherCreditCard"))) {
									if ("1".equals(map1.get("otherCreditCard").toString())) {
										sb33.append("标卡他行信用卡:" + map1.get("otherCreditCard") + ",");
									}
								}

								if (map1.get("others") != null && !"".equals(map1.get("others"))) {
									if ("1".equals(map1.get("others").toString())) {
										sb33.append("标卡其他:" + map1.get("others") + ",");
									}
								}

								if (map1.get("isHavesign") != null && !"".equals(map1.get("isHavesign"))) {
									if ("1".equals(map1.get("isHavesign").toString())) {
										sb33.append("标卡是否有签名:" + map1.get("isHavesign") + ",");
									}
								}

								if (map1.get("liveConfirm") != null && !"".equals(map1.get("liveConfirm"))) {
									if ("1".equals(map1.get("liveConfirm").toString())) {
										sb33.append("标卡居住证明:" + map1.get("liveConfirm") + ",");
									}
								}

								if (map1.get("eduConfirm") != null && !"".equals(map1.get("eduConfirm"))) {
									if ("1".equals(map1.get("eduConfirm").toString())) {
										sb33.append("标卡学历证明:" + map1.get("eduConfirm") + ",");
									}
								}

								if (map1.get("ownerBankConfirm") != null && !"".equals(map1.get("ownerBankConfirm"))) {
									if ("1".equals(map1.get("ownerBankConfirm").toString())) {
										sb33.append("标卡业务往来证明:" + map1.get("ownerBankConfirm") + ",");
									}
								}

								if (map1.get("nonAddfiles") != null && !"".equals(map1.get("nonAddfiles"))) {
									if ("1".equals(map1.get("nonAddfiles").toString())) {
										sb33.append("标卡未补充资料:" + map1.get("nonAddfiles") + ",");
									}
								}

								if (map1.get("workConfirm") != null && !"".equals(map1.get("workConfirm"))) {
									if ("1".equals(map1.get("workConfirm").toString())) {
										sb33.append("标卡工作证明:" + map1.get("workConfirm") + ",");
									}
								}

								if (map1.get("houseConfirm") != null && !"".equals(map1.get("houseConfirm"))) {
									if ("1".equals(map1.get("houseConfirm").toString())) {
										sb33.append("标卡房产证明:" + map1.get("houseConfirm") + ",");
									}
								}

								if (map1.get("financalConfirm") != null && !"".equals(map1.get("financalConfirm"))) {
									if ("1".equals(map1.get("financalConfirm").toString())) {
										sb33.append("标卡资格证书:" + map1.get("financalConfirm") + ",");
									}
								}

								if (map1.get("crtDate1") != null && !"".equals(map1.get("crtDate1"))) {
									sb33.append("标卡创建日期:" + map1.get("crtDate1") + ",");
								}

								if (map1.get("crtUser1") != null && !"".equals(map1.get("crtUser1"))) {
									sb33.append("标卡创建人:" + map1.get("crtUser1") + ",");
								}
							}

						}

					}

					String string = sb33.toString();
					int length = string.length();
					if (length != 0) {
						String substring = string.substring(0, length - 1);
						cell.setCellValue(substring);
					} else {
						cell.setCellValue(string);
					}

					cellNo = 0; // 重新开始遍历
				}

				ByteArrayOutputStream os = new ByteArrayOutputStream();
				wb.write(os);
				os.flush();
				os.close();
				// 下载
				DownloadUtil downloadUtil = new DownloadUtil();
				downloadUtil.download(os, request , response, "导出数据.xls");
			}
		}
	}

	// 构造类
	class Position {
		int begin;
		int end;

		public Position(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
	// the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}
}
